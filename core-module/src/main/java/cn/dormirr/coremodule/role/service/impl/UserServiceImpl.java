package cn.dormirr.coremodule.role.service.impl;

import cn.dormirr.coremodule.announcement.domain.AnnouncementEntity;
import cn.dormirr.coremodule.role.domain.UserEntity;
import cn.dormirr.coremodule.role.repository.UserRepository;
import cn.dormirr.coremodule.role.service.RoleService;
import cn.dormirr.coremodule.role.service.UserService;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.role.service.mapper.RoleMapper;
import cn.dormirr.coremodule.role.service.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhangTianCi
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleMapper roleMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.roleService = roleService;
    }

    /**
     * @param userNumber 学号
     * @return 用户 DTO
     */
    @Override
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
    public void saveUser(List<Object> user) {
        UserEntity users = new UserEntity();
        users.setUserNumber((String) user.get(0));
        users.setUserName((String) user.get(1));
        if (user.get(2) != null) {
            users.setUserEmail((String) user.get(2));
        }
        if ("学生".equals(user.get(3))) {
            users.setRoleByRoleId(roleMapper.toEntity(roleService.findByRoleName("学生")));
        } else if ("老师".equals(user.get(3))) {
            users.setRoleByRoleId(roleMapper.toEntity(roleService.findByRoleName("老师")));
        }
        userRepository.save(users);
    }

    /**
     * 修改用户头像
     *
     * @param userDto 用户
     */
    @Override
    public void saveUserPortrait(UserDto userDto) {
        userRepository.save(userMapper.toEntity(userDto));
    }

    /**
     * 修改用户姓名和邮箱
     *
     * @param userDto 用户
     */
    @Override
    public void saveUserNameAndEmail(UserDto userDto) {
        userRepository.save(userMapper.toEntity(userDto));
    }

    /**
     * 修改用户密码
     *
     * @param userDto 用户
     */
    @Override
    public void saveUserPassword(UserDto userDto) {
        userRepository.save(userMapper.toEntity(userDto));
    }

    /**
     * 修改用户角色
     *
     * @param userDto 用户
     */
    @Override
    public void saveUserRole(UserDto userDto) {
        userRepository.save(userMapper.toEntity(userDto));
    }

    /**
     * 查询积分前十的人
     *
     * @return 查询结果
     */
    @Override
    public List<UserDto> findUserFightingCapacity() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "userFightingCapacity"));

        Page<UserEntity> data = userRepository.findAll(pageable);

        return userMapper.toDto(data.getContent());
    }
}
