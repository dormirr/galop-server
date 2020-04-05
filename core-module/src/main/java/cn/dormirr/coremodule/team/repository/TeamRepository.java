package cn.dormirr.coremodule.team.repository;

import cn.dormirr.coremodule.role.domain.RoleEntity;
import cn.dormirr.coremodule.role.domain.UserEntity;
import cn.dormirr.coremodule.team.domain.TeamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ZhangTianCi
 */
public interface TeamRepository extends JpaRepository<TeamEntity, Long>, JpaSpecificationExecutor<TeamEntity> {

    /**
     * 根据队伍 ID、用户、审核状态查询队伍
     *
     * @param teamId     队伍 ID
     * @param userEntity 用户
     * @param teamState  审核状态
     * @return 查询结果
     */

    TeamEntity findByTeamIdAndUserByUserIdAndTeamStateIsNot(Long teamId, UserEntity userEntity, String teamState);

    /**
     * 根据用户和角色查询队伍
     *
     * @param userEntity 用户
     * @param pageable   分页排序
     * @return 队伍
     */
    Page<TeamEntity> findByUserByUserId(UserEntity userEntity, Pageable pageable);

    /**
     * 根据用户和权限查询团队
     *
     * @param userEntity 用户
     * @param roleEntity 权限
     * @return 此用户在团队中是此权限的团队列表
     */
    List<TeamEntity> findByUserByUserIdAndRoleByRoleId(UserEntity userEntity, RoleEntity roleEntity);

    /**
     * 根据申请 ID 和申请状态查询申请
     *
     * @param id            申请 ID
     * @param teamStateWait 申请状态
     * @return 申请
     */
    TeamEntity findByIdAndTeamState(Long id, String teamStateWait);

    /**
     * 根据角色查询队伍
     *
     * @param role     用户权限
     * @param pageable 分页排序
     * @return 队伍
     */
    Page<TeamEntity> findAllByRoleByRoleId(RoleEntity role, Pageable pageable);

    /**
     * 根据团队 ID 查询团队
     *
     * @param teamId 团队 ID
     * @return 查询结果
     */
    List<TeamEntity> findAllByTeamId(Long teamId);

    /**
     * 查询用户的队伍
     *
     * @param userEntity 用户
     * @param teamState  状态
     * @return 用户的队伍
     */
    List<TeamEntity> findAllByUserByUserIdAndTeamState(UserEntity userEntity, String teamState);

    /**
     * 根据团队查询人数
     *
     * @param teamId 团队
     * @return 查询结果
     */
    int countAllByTeamId(Long teamId);

    /**
     * 根据团队和审核状态查询团队
     *
     * @param teamId    团队
     * @param teamState 审核状态
     * @return 查询结果
     */
    List<TeamEntity> findAllByTeamIdAndTeamState(Long teamId, String teamState);
}
