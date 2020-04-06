package cn.dormirr.coremodule.team.service;

import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.team.service.dto.TeamDto;
import org.springframework.data.domain.Page;

/**
 * @author ZhangTianCi
 */
public interface TeamService {

    /**
     * 创建队伍
     *
     * @param teamDto 创建队伍的信息
     * @param userDto 创建人
     */
    void saveTeam(TeamDto teamDto, UserDto userDto);

    /**
     * 动态查询队伍
     *
     * @param teamDto  查询条件
     * @param pageSize 每页数量
     * @param current  第几页
     * @param sorter   排序规则
     * @return 查询结果
     */
    Page<TeamDto> findTeam(TeamDto teamDto, int pageSize, int current, String sorter);

    /**
     * 申请加入团队
     *
     * @param id 团队 ID
     * @return 申请结果
     */
    boolean applyTeam(Long id);

    /**
     * 动态查询申请
     *
     * @param teamDto  查询条件
     * @param pageSize 每页数量
     * @param current  第几页
     * @param sorter   排序规则
     * @return 查询结果
     */
    Page<TeamDto> findApplyTeam(TeamDto teamDto, int pageSize, int current, String sorter);

    /**
     * 同意申请
     *
     * @param id 申请处理 ID
     * @return 申请处理结果
     */
    boolean saveApplyTeam(Long id);

    /**
     * 拒绝申请
     *
     * @param id 申请处理 ID
     * @return 申请处理结果
     */
    boolean deleteApplyTeam(Long id);

    /**
     * 根据团队 ID 查询团队
     *
     * @param id 团队 ID
     * @return 查询结果
     */
    TeamDto findId(Long id);

    /**
     * 动态查询我的队伍
     *
     * @param teamDto  查询条件
     * @param pageSize 每页数量
     * @param current  第几页
     * @param sorter   排序规则
     * @return 查询结果
     */
    Page<TeamDto> findMyTeam(TeamDto teamDto, int pageSize, int current, String sorter);

    /**
     * 修改团队信息
     *
     * @param teamDto 修改信息
     */
    void saveMyTeam(TeamDto teamDto);

    /**
     * 查询一个团队
     *
     * @param teamDto  团队 ID
     * @param pageSize 每页数量
     * @param current  第几页
     * @param sorter   排序规则
     * @return 查询结果
     */
    Page<TeamDto> findOneTeam(TeamDto teamDto, int pageSize, int current, String sorter);
}
