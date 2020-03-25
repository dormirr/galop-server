package cn.dormirr.coremodule.team.service;

import cn.dormirr.coremodule.role.service.dto.RoleDto;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.team.domain.Team;
import cn.dormirr.coremodule.team.service.dto.TeamDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author ZhangTianCi
 */
public interface TeamService {

    /**
     * 创建队伍
     *
     * @param teamDto 队伍
     */
    void saveTeam(TeamDto teamDto);

    /**
     * 根据用户查询队伍
     *
     * @param userDto 用户
     * @return 队伍
     */
    Page<Team> findByUser(UserDto userDto);


    /**
     * 根据角色动态查询队伍
     *
     * @param roleDto 角色
     * @param teamDto 动态查询条件
     * @return 队伍
     */
    List<Team> findAllByRole(RoleDto roleDto, TeamDto teamDto);

    /**
     * 根据队伍 ID 查询队伍
     *
     * @param id 队伍 ID
     * @return 队伍
     */
    TeamDto findById(Long id);

    /**
     * 添加申请信息
     *
     * @param teamDto 队伍申请 DTO
     */
    void saveAddUserTeam(TeamDto teamDto);
}
