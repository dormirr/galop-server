package cn.dormirr.coremodule.role.service.impl;

import cn.dormirr.commonmodule.utils.PageUtils;
import cn.dormirr.commonmodule.utils.RedisUtils;
import cn.dormirr.coremodule.fighting.repository.FightingCapacityRepository;
import cn.dormirr.coremodule.match.result.repository.MatchResultRepository;
import cn.dormirr.coremodule.registration.repository.RegistrationInfoRepository;
import cn.dormirr.coremodule.role.domain.RoleEntity;
import cn.dormirr.coremodule.role.domain.UserEntity;
import cn.dormirr.coremodule.role.repository.UserRepository;
import cn.dormirr.coremodule.role.service.RoleService;
import cn.dormirr.coremodule.role.service.UserService;
import cn.dormirr.coremodule.role.service.dto.RoleDto;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.role.service.mapper.RoleMapper;
import cn.dormirr.coremodule.role.service.mapper.UserMapper;
import cn.dormirr.coremodule.security.config.SecurityProperties;
import cn.dormirr.coremodule.team.repository.TeamRepository;
import cn.dormirr.coremodule.team.service.dto.TeamDto;
import cn.dormirr.coremodule.team.service.mapper.TeamMapper;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangTianCi
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final RoleService roleService;
    private final JavaMailSender javaMailSender;
    private final SecurityProperties securityProperties;
    private final RedisUtils redisUtils;
    private final MatchResultRepository matchResultRepository;
    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;
    private final FightingCapacityRepository fightingCapacityRepository;
    private final RegistrationInfoRepository registrationInfoRepository;
    @Value("${code.expiration}")
    private Long expiration;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleMapper roleMapper, RoleService roleService, JavaMailSender javaMailSender, SecurityProperties securityProperties, RedisUtils redisUtils, MatchResultRepository matchResultRepository, TeamMapper teamMapper, TeamRepository teamRepository, FightingCapacityRepository fightingCapacityRepository, RegistrationInfoRepository registrationInfoRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.roleService = roleService;
        this.javaMailSender = javaMailSender;
        this.securityProperties = securityProperties;
        this.redisUtils = redisUtils;
        this.matchResultRepository = matchResultRepository;
        this.teamMapper = teamMapper;
        this.teamRepository = teamRepository;
        this.fightingCapacityRepository = fightingCapacityRepository;
        this.registrationInfoRepository = registrationInfoRepository;
    }

    /**
     * @param userNumber 学号
     * @return 用户 DTO
     */
    @Override
    @Cacheable
    public UserDto findByUserNumber(String userNumber) {
        UserEntity userEntity = userRepository.findByUserNumber(userNumber);
        if (userEntity != null) {
            return userMapper.toDto(userEntity);
        }
        return null;
    }

    /**
     * 批量添加用户
     *
     * @param user 用户
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void saveUser(List<Object> user) {
        UserDto users = new UserDto();
        users.setUserNumber((String) user.get(0));
        users.setUserName((String) user.get(1));
        if (!"".equals(user.get(2))) {
            users.setUserEmail((String) user.get(2));
        }
        if ("学生".equals(user.get(3))) {
            users.setRoleByRoleId(roleMapper.toEntity(roleService.findByRoleName("学生")));
        } else if ("老师".equals(user.get(3))) {
            users.setRoleByRoleId(roleMapper.toEntity(roleService.findByRoleName("老师")));
        }
        userRepository.save(userMapper.toEntity(users));
    }

    /**
     * 修改用户头像
     *
     * @param userDto 用户
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void saveUserPortrait(UserDto userDto) {
        userRepository.save(userMapper.toEntity(userDto));
    }

    /**
     * 修改用户姓名和邮箱
     *
     * @param userDto 用户
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    @Caching(evict = {@CacheEvict(allEntries = true), @CacheEvict(cacheNames = "team", allEntries = true)})
    public void saveUserNameAndEmail(UserDto userDto) {
        userRepository.save(userMapper.toEntity(userDto));
    }

    /**
     * 修改用户密码
     *
     * @param userDto 用户
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void saveUserPassword(UserDto userDto) {
        userRepository.save(userMapper.toEntity(userDto));
    }

    /**
     * 修改用户角色
     *
     * @param userDto 用户
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void saveUserRole(UserDto userDto) {
        userRepository.save(userMapper.toEntity(userDto));
    }

    /**
     * 查询积分前十的人
     *
     * @return 查询结果
     */
    @Override
    @Cacheable
    public List<UserDto> findUserFightingCapacity() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "userFightingCapacity"));

        Specification<UserEntity> specification = (Specification<UserEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> andQuery = new ArrayList<>();

            RoleDto roleDto = roleService.findByRoleName("学生");
            Path<RoleEntity> roleByRoleId = root.get("roleByRoleId");
            Predicate roleByRoleIdEqual = criteriaBuilder.equal(roleByRoleId, roleMapper.toEntity(roleDto));
            andQuery.add(roleByRoleIdEqual);

            Predicate[] andPredicates = andQuery.toArray(new Predicate[0]);
            return criteriaBuilder.and(andPredicates);
        };

        Page<UserEntity> data = userRepository.findAll(specification, pageable);

        return userMapper.toDto(data.getContent());
    }

    /**
     * 给用户发送密码邮件
     *
     * @param userDto 用户
     */
    @Override
    @Async
    public void email(UserDto userDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 邮件发送人
        simpleMailMessage.setFrom("1018729292@qq.com");
        // 邮件接收人
        simpleMailMessage.setTo(userDto.getUserEmail());
        // 邮件主题
        simpleMailMessage.setSubject("重置密码链接");

        String uuid = securityProperties.getCodeKey() + IdUtil.simpleUUID();
        redisUtils.set(uuid, userDto.getUserNumber(), expiration, TimeUnit.MINUTES);
        // 邮件内容
        simpleMailMessage.setText("你的密码重置链接为：" + "https://localhost:8080/role/forget-code?uuid=" + uuid);

        javaMailSender.send(simpleMailMessage);
    }

    /**
     * 重置密码
     *
     * @param uuid uuid
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void forget(String uuid) {
        String userNumber = (String) redisUtils.get(uuid);
        if (userNumber == null) {
            return;
        }
        UserDto userDto = findByUserNumber(userNumber);
        userDto.setUserPassword("$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om");
        userRepository.save(userMapper.toEntity(userDto));
    }

    /**
     * 重置密码
     *
     * @param userDto 用户
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void forgetUser(UserDto userDto) {
        userRepository.save(userMapper.toEntity(userDto));
    }

    /**
     * 删除用户
     *
     * @param userNumber 学号
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    @Caching(evict = {@CacheEvict(allEntries = true), @CacheEvict(cacheNames = "fightingCapacity", allEntries = true), @CacheEvict(cacheNames = "matchResult", allEntries = true), @CacheEvict(cacheNames = "registrationInfo", allEntries = true), @CacheEvict(cacheNames = "team", allEntries = true)})
    public void removeUser(String userNumber) {
        UserDto userDto = findByUserNumber(userNumber);
        fightingCapacityRepository.deleteAll(fightingCapacityRepository.findAllByUserByUserId(userMapper.toEntity(userDto)));

        List<TeamDto> teamDtoList1 = teamMapper.toDto(teamRepository.findAllByUserByUserIdAndTeamState(userMapper.toEntity(userDto), "通过"));
        List<TeamDto> teamDtoList2 = teamMapper.toDto(teamRepository.findAllByUserByUserIdAndTeamState(userMapper.toEntity(userDto), "审核"));
        for (TeamDto teamDto : teamDtoList1) {
            matchResultRepository.deleteAll(matchResultRepository.findAllByTeamByTeamId(teamMapper.toEntity(teamDto)));
            registrationInfoRepository.deleteAll(registrationInfoRepository.findAllByTeamByTeamId(teamMapper.toEntity(teamDto)));
        }

        teamRepository.deleteAll(teamMapper.toEntity(teamDtoList1));
        teamRepository.deleteAll(teamMapper.toEntity(teamDtoList2));

        userRepository.delete(userMapper.toEntity(userDto));
    }

    /**
     * 动态查询用户
     *
     * @param userDto  查询条件
     * @param pageSize 每页数量
     * @param current  第几页
     * @param sorter   排序规则
     * @return 查询结果
     */
    @Override
    @Cacheable
    public PageUtils<UserDto> findUser(UserDto userDto, int pageSize, int current, String sorter) {
        String descend = "ascend";
        String[] sort = sorter != null ? sorter.split("_") : new String[]{"userFightingCapacity", ""};
        Pageable pageable = descend.equals(sort[1]) ?
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, sort[0])) :
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.DESC, sort[0]));

        Specification<UserEntity> specification = (Specification<UserEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> andQuery = new ArrayList<>();

            if (userDto.getUserName() != null) {
                Path<String> userName = root.get("userName");
                Predicate userNameLike = criteriaBuilder.like(userName, "%" + userDto.getUserName() + "%");
                andQuery.add(userNameLike);
            }

            if (userDto.getUserNumber() != null) {
                Path<String> userNumber = root.get("userNumber");
                Predicate userNumberEqual = criteriaBuilder.equal(userNumber, userDto.getUserNumber());
                andQuery.add(userNumberEqual);
            }

            Predicate[] andPredicates = andQuery.toArray(new Predicate[0]);
            return criteriaBuilder.and(andPredicates);
        };

        Page<UserEntity> data = userRepository.findAll(specification, pageable);

        return new PageUtils<>(userMapper.toDto(data.getContent()), data.getTotalElements(), data.getTotalPages());
    }
}
