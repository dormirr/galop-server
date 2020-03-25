package cn.dormirr.coremodule.team.service.impl;

import cn.dormirr.coremodule.role.service.dto.RoleDto;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.role.service.mapper.RoleMapper;
import cn.dormirr.coremodule.role.service.mapper.UserMapper;
import cn.dormirr.coremodule.team.domain.Team;
import cn.dormirr.coremodule.team.repository.TeamRepository;
import cn.dormirr.coremodule.team.service.TeamService;
import cn.dormirr.coremodule.team.service.dto.TeamDto;
import cn.dormirr.coremodule.team.service.mapper.TeamMapper;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ZhangTianCi
 */
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    public TeamServiceImpl(TeamRepository teamRepository, TeamMapper teamMapper, UserMapper userMapper, RoleMapper roleMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    /**
     * 创建队伍
     *
     * @param teamDto 队伍
     */
    @Override
    public void saveTeam(TeamDto teamDto) {
        teamRepository.save(teamMapper.toEntity(teamDto));
    }

    /**
     * 根据用户查询队伍
     *
     * @param userDto 用户
     * @return
     */
    @Override
    public Page<Team> findByUser(UserDto userDto) {
        return teamRepository.findByUserByUserId(userMapper.toEntity(userDto), PageRequest.of(0, 3));
    }

    /**
     * 根据角色动态查询队伍
     *
     * @param roleDto 角色
     * @param teamDto 动态查询条件
     * @return 队伍
     */
    @Override
    public List<Team> findAllByRole(RoleDto roleDto, TeamDto teamDto) {
        Specification<Team> specification = (Specification<Team>) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> query = new ArrayList<>();
            if (teamDto.getId() != null) {
                Path<Long> id = root.get("id");
                Predicate idEqual = criteriaBuilder.equal(id, teamDto.getId());
                query.add(idEqual);
            }
            if (teamDto.getCreateTime() != null) {
                Path<Timestamp> createTime = root.get("createTime");
                Predicate createTimeEqual = criteriaBuilder.equal(createTime, teamDto.getCreateTime());
                query.add(createTimeEqual);
            }
            if (teamDto.getTeamName() != null) {
                Path<String> teamName = root.get("teamName");
                Predicate teamNameEqual = criteriaBuilder.like(teamName, "%" + teamDto.getTeamName() + "%");
                query.add(teamNameEqual);
            }
            if (teamDto.getTotalTeamFightingPower() != null) {
                Path<Integer> totalTeamFightingPower = root.get("totalTeamFightingPower");
                Predicate totalTeamFightingPowerEqual = criteriaBuilder.equal(totalTeamFightingPower, teamDto.getTotalTeamFightingPower());
                query.add(totalTeamFightingPowerEqual);
            }
            if (teamDto.getTeamProfile() != null) {
                Path<String> teamProfile = root.get("teamProfile");
                Predicate teamProfileLike = criteriaBuilder.like(teamProfile, "%" + teamDto.getTeamProfile() + "%");
                query.add(teamProfileLike);
            }
            Predicate[] predicates = query.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicates);
        };

        return teamRepository.findAll(specification, Sort.by(Sort.Direction.ASC, "id"));
    }

    /**
     * 根据队伍 ID 查询队伍
     *
     * @param id 队伍 ID
     * @return 队伍
     */
    @Override
    public TeamDto findById(Long id) {
        return teamMapper.toDto(teamRepository.findById(id).isPresent() ? teamRepository.findById(id).get() : null);
    }

    /**
     * 添加申请信息
     *
     * @param teamDto 队伍申请 DTO
     */
    @Override
    public void saveAddUserTeam(TeamDto teamDto) {
        teamRepository.save(teamMapper.toEntity(teamDto));
    }
}
