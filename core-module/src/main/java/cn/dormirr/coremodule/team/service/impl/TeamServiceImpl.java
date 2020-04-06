package cn.dormirr.coremodule.team.service.impl;

import cn.dormirr.commonmodule.utils.SecurityUtils;
import cn.dormirr.coremodule.role.service.RoleService;
import cn.dormirr.coremodule.role.service.UserService;
import cn.dormirr.coremodule.role.service.dto.RoleDto;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.role.service.mapper.RoleMapper;
import cn.dormirr.coremodule.role.service.mapper.UserMapper;
import cn.dormirr.coremodule.team.domain.TeamEntity;
import cn.dormirr.coremodule.team.repository.TeamRepository;
import cn.dormirr.coremodule.team.service.TeamService;
import cn.dormirr.coremodule.team.service.dto.TeamDto;
import cn.dormirr.coremodule.team.service.mapper.TeamMapper;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangTianCi
 */
@Service
public class TeamServiceImpl implements TeamService {
    private final UserService userService;
    private final RoleService roleService;
    private final TeamMapper teamMapper;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final TeamRepository teamRepository;

    public TeamServiceImpl(UserService userService, RoleService roleService, TeamMapper teamMapper, UserMapper userMapper, RoleMapper roleMapper, TeamRepository teamRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.teamMapper = teamMapper;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.teamRepository = teamRepository;
    }

    /**
     * 创建队伍
     *
     * @param teamDto 创建队伍的信息
     * @param userDto 创建人
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void saveTeam(TeamDto teamDto, UserDto userDto) {
        RoleDto roleDto = roleService.findByRoleName("队长");

        teamDto.setTeamFightingCapacity(userDto.getUserFightingCapacity());
        teamDto.setTeamState("通过");
        teamDto.setUserByUserId(userMapper.toEntity(userDto));
        teamDto.setRoleByRoleId(roleMapper.toEntity(roleDto));

        TeamEntity teamEntity = teamRepository.save(teamMapper.toEntity(teamDto));
        teamEntity.setTeamId(teamEntity.getId());
        teamRepository.save(teamEntity);
    }

    /**
     * 动态查询队伍
     *
     * @param teamDto  查询条件
     * @param pageSize 每页数量
     * @param current  第几页
     * @param sorter   排序规则
     * @return 查询结果
     */
    @Override
    public Page<TeamDto> findTeam(TeamDto teamDto, int pageSize, int current, String sorter) {
        String descend = "ascend";
        String[] sort = sorter != null ? sorter.split("_") : new String[]{"teamFightingCapacity", ""};
        Pageable pageable = descend.equals(sort[1]) ?
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, sort[0])) :
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.DESC, sort[0]));

        Specification<TeamEntity> specification = (Specification<TeamEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> andQuery = new ArrayList<>();

            RoleDto roleDto = roleService.findByRoleName("队长");
            Path<Long> roleByRoleId = root.get("roleByRoleId");
            Predicate roleByRoleIdEqual = criteriaBuilder.equal(roleByRoleId, roleMapper.toEntity(roleDto));
            andQuery.add(roleByRoleIdEqual);

            if (teamDto.getId() != null) {
                Path<Long> id = root.get("id");
                Predicate idEqual = criteriaBuilder.equal(id, teamDto.getId());
                andQuery.add(idEqual);
            }

            if (teamDto.getTeamName() != null) {
                Path<String> teamName = root.get("teamName");
                Predicate teamNameLike = criteriaBuilder.like(teamName, "%" + teamDto.getTeamName() + "%");
                andQuery.add(teamNameLike);
            }

            if (teamDto.getTeamFightingCapacity() != null) {
                Path<Integer> totalTeamFightingPower = root.get("teamFightingCapacity");
                Predicate totalTeamFightingPowerEqual = criteriaBuilder.equal(totalTeamFightingPower, teamDto.getTeamFightingCapacity());
                andQuery.add(totalTeamFightingPowerEqual);
            }

            Predicate[] andPredicates = andQuery.toArray(new Predicate[0]);
            return criteriaBuilder.and(andPredicates);
        };

        Page<TeamEntity> data = teamRepository.findAll(specification, pageable);

        List<TeamDto> list = new ArrayList<>();
        for (
                TeamEntity teamEntity : data.getContent()) {
            list.add(teamMapper.toDto(teamEntity));
        }
        return new PageImpl<>(list, data.getPageable(), data.getTotalElements());
    }

    /**
     * 申请加入团队
     *
     * @param id 团队 ID
     * @return 申请结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean applyTeam(Long id) {
        UserDto userDto = userService.findByUserNumber(SecurityUtils.getUsername());
        String teamStateError = "拒绝";
        if (teamRepository.
                findByTeamIdAndUserByUserIdAndTeamStateIsNot(id, userMapper.toEntity(userDto), teamStateError) == null) {
            TeamDto teamDto = teamMapper.toDto(teamRepository.findById(id).isPresent() ?
                    teamRepository.findById(id).get() : null);
            teamDto.setId(null);
            teamDto.setCreateTime(null);
            teamDto.setUpdateTime(null);
            teamDto.setTeamState("审核");
            teamDto.setUserByUserId(userMapper.toEntity(userDto));
            RoleDto roleDto = roleService.findByRoleName("学生");
            teamDto.setRoleByRoleId(roleMapper.toEntity(roleDto));
            teamRepository.save(teamMapper.toEntity(teamDto));
            return true;
        }

        return false;
    }

    /**
     * 动态查询申请
     *
     * @param teamDto  查询条件
     * @param pageSize 每页数量
     * @param current  第几页
     * @param sorter   排序规则
     * @return 查询结果
     */
    @Override
    public Page<TeamDto> findApplyTeam(TeamDto teamDto, int pageSize, int current, String sorter) {
        String descend = "ascend";
        String[] sort = sorter != null ? sorter.split("_") : new String[]{"id", ""};
        Pageable pageable = descend.equals(sort[1]) ?
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, sort[0])) :
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.DESC, sort[0]));

        UserDto userDto = userService.findByUserNumber(SecurityUtils.getUsername());
        RoleDto roleDto = roleService.findByRoleName("队长");
        List<TeamEntity> teamIdList = teamRepository.findByUserByUserIdAndRoleByRoleId(
                userMapper.toEntity(userDto), roleMapper.toEntity(roleDto)
        );

        Specification<TeamEntity> specification = (Specification<TeamEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> andQuery = new ArrayList<>();
            ArrayList<Predicate> orQuery = new ArrayList<>();

            if (teamIdList != null) {
                Path<Long> teamId = root.get("teamId");
                for (TeamEntity teamEntity : teamIdList) {
                    Predicate teamIdEqual = criteriaBuilder.equal(teamId, teamEntity.getTeamId());
                    orQuery.add(teamIdEqual);
                }
            }

            if (teamDto.getId() != null) {
                Path<Long> id = root.get("id");
                Predicate idEqual = criteriaBuilder.equal(id, teamDto.getId());
                andQuery.add(idEqual);
            }

            if (teamDto.getTeamName() != null) {
                Path<String> teamName = root.get("teamName");
                Predicate teamNameEqual = criteriaBuilder.like(teamName, "%" + teamDto.getTeamName() + "%");
                andQuery.add(teamNameEqual);
            }

            if (teamDto.getTeamState() != null) {
                Path<String> teamState = root.get("teamState");
                Predicate teamStateEqual = criteriaBuilder.like(teamState, "%" + teamDto.getTeamState() + "%");
                andQuery.add(teamStateEqual);
            }

            if (teamDto.getTeamId() != null) {
                Path<Long> teamId = root.get("teamId");
                Predicate teamIdEqual = criteriaBuilder.equal(teamId, teamDto.getTeamId());
                andQuery.add(teamIdEqual);
            }

            if (teamDto.getUserByUserId() != null) {
                UserDto userDtoQuery = new UserDto();
                if (teamDto.getUserByUserId().getId() != null) {
                    userDtoQuery.setId(teamDto.getUserByUserId().getId());
                }
                if (teamDto.getUserByUserId().getUserName() != null) {
                    userDtoQuery.setUserName(teamDto.getUserByUserId().getUserName());
                }
                if (teamDto.getUserByUserId().getUserFightingCapacity() != null) {
                    userDtoQuery.setUserFightingCapacity(teamDto.getUserByUserId().getUserFightingCapacity());
                }
                Path<Long> userByUserId = root.get("userByUserId");
                Predicate userByUserIdEqual = criteriaBuilder.equal(userByUserId, userMapper.toEntity(userDtoQuery));
                andQuery.add(userByUserIdEqual);
            }

            Predicate[] andPredicates = andQuery.toArray(new Predicate[0]);
            Predicate[] orPredicates = orQuery.toArray(new Predicate[0]);

            Predicate[] predicates = {criteriaBuilder.and(andPredicates), criteriaBuilder.or(orPredicates)};
            return criteriaBuilder.and(predicates);
        };

        Page<TeamEntity> data = teamRepository.findAll(specification, pageable);

        List<TeamDto> list = new ArrayList<>();
        for (TeamEntity teamEntity : data.getContent()) {
            list.add(teamMapper.toDto(teamEntity));
        }
        return new PageImpl<>(list, data.getPageable(), data.getTotalElements());
    }

    /**
     * 同意申请
     *
     * @param id 申请处理 ID
     * @return 申请处理结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveApplyTeam(Long id) {
        String teamStateWait = "审核";
        if (teamRepository.findByIdAndTeamState(id, teamStateWait) != null) {
            TeamDto teamDto = teamMapper.toDto(teamRepository.findById(id).isPresent() ?
                    teamRepository.findById(id).get() : null);
            teamDto.setTeamState("通过");
            teamRepository.save(teamMapper.toEntity(teamDto));
            return true;
        }
        return false;
    }

    /**
     * 拒绝申请
     *
     * @param id 申请处理 ID
     * @return 申请处理结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteApplyTeam(Long id) {
        String teamStateWait = "审核";
        if (teamRepository.findByIdAndTeamState(id, teamStateWait) != null) {
            TeamDto teamDto = teamMapper.toDto(teamRepository.findById(id).isPresent() ?
                    teamRepository.findById(id).get() : null);
            teamDto.setTeamState("拒绝");
            teamRepository.save(teamMapper.toEntity(teamDto));
            return true;
        }
        return false;
    }

    /**
     * 根据团队 ID 查询团队
     *
     * @param id 团队 ID
     * @return 查询结果
     */
    @Override
    public TeamDto findId(Long id) {
        return teamRepository.findById(id).isPresent() ? teamMapper.toDto(teamRepository.findById(id).get()) : null;
    }

    /**
     * 动态查询我的队伍
     *
     * @param teamDto  查询条件
     * @param pageSize 每页数量
     * @param current  第几页
     * @param sorter   排序规则
     * @return 查询结果
     */
    @Override
    public Page<TeamDto> findMyTeam(TeamDto teamDto, int pageSize, int current, String sorter) {
        String descend = "descend";
        String[] sort = sorter != null ? sorter.split("_") : new String[]{"id", ""};
        Pageable pageable = descend.equals(sort[1]) ?
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.DESC, sort[0])) :
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, sort[0]));

        Specification<TeamEntity> specification = (Specification<TeamEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> andQuery = new ArrayList<>();

            UserDto userDto = userService.findByUserNumber(SecurityUtils.getUsername());
            Path<Long> userByUserId = root.get("userByUserId");
            Predicate userByUserIdEqual = criteriaBuilder.equal(userByUserId, userMapper.toEntity(userDto));
            andQuery.add(userByUserIdEqual);

            Path<Long> teamState = root.get("teamState");
            Predicate teamStateEqual = criteriaBuilder.equal(teamState, "通过");
            andQuery.add(teamStateEqual);

            if (teamDto.getId() != null) {
                Path<Long> id = root.get("id");
                Predicate idEqual = criteriaBuilder.equal(id, teamDto.getId());
                andQuery.add(idEqual);
            }

            if (teamDto.getTeamName() != null) {
                Path<String> teamName = root.get("teamName");
                Predicate teamNameEqual = criteriaBuilder.like(teamName, "%" + teamDto.getTeamName() + "%");
                andQuery.add(teamNameEqual);
            }

            if (teamDto.getTeamFightingCapacity() != null) {
                Path<Integer> totalTeamFightingPower = root.get("teamFightingCapacity");
                Predicate totalTeamFightingPowerEqual = criteriaBuilder.equal(totalTeamFightingPower, teamDto.getTeamFightingCapacity());
                andQuery.add(totalTeamFightingPowerEqual);
            }

            Predicate[] andPredicates = andQuery.toArray(new Predicate[0]);
            return criteriaBuilder.and(andPredicates);
        };

        Page<TeamEntity> data = teamRepository.findAll(specification, pageable);

        List<TeamDto> list = new ArrayList<>();
        for (
                TeamEntity teamEntity : data.getContent()) {
            list.add(teamMapper.toDto(teamEntity));
        }
        return new PageImpl<>(list, data.getPageable(), data.getTotalElements());
    }

    /**
     * 修改团队信息
     *
     * @param teamDto 修改信息
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void saveMyTeam(TeamDto teamDto) {
        List<TeamDto> list = teamMapper.toDto(teamRepository.findAllByTeamId(teamDto.getTeamId()));
        for (TeamDto findTeamDto : list) {
            if (teamDto.getTeamName() != null) {
                findTeamDto.setTeamName(teamDto.getTeamName());
            }
            if (teamDto.getTeamProfile() != null) {
                findTeamDto.setTeamProfile(teamDto.getTeamProfile());
            }
            teamRepository.save(teamMapper.toEntity(findTeamDto));
        }
    }

    /**
     * 查询一个团队
     *
     * @param teamDto  团队 ID
     * @param pageSize 每页数量
     * @param current  第几页
     * @param sorter   排序规则
     * @return 查询结果
     */
    @Override
    public Page<TeamDto> findOneTeam(TeamDto teamDto, int pageSize, int current, String sorter) {
        String descend = "descend";
        String[] sort = sorter != null ? sorter.split("_") : new String[]{"id", ""};
        Pageable pageable = descend.equals(sort[1]) ?
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.DESC, sort[0])) :
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, sort[0]));

        Specification<TeamEntity> specification = (Specification<TeamEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> andQuery = new ArrayList<>();

            Path<Long> teamState = root.get("teamState");
            Predicate teamStateEqual = criteriaBuilder.equal(teamState, "通过");
            andQuery.add(teamStateEqual);

            Path<Long> teamId = root.get("teamId");
            Predicate teamIdEqual = criteriaBuilder.equal(teamId, teamDto.getTeamId());
            andQuery.add(teamIdEqual);

            Predicate[] andPredicates = andQuery.toArray(new Predicate[0]);
            return criteriaBuilder.and(andPredicates);
        };

        Page<TeamEntity> data = teamRepository.findAll(specification, pageable);

        List<TeamDto> list = new ArrayList<>();
        for (
                TeamEntity teamEntity : data.getContent()) {
            list.add(teamMapper.toDto(teamEntity));
        }
        return new PageImpl<>(list, data.getPageable(), data.getTotalElements());
    }
}
