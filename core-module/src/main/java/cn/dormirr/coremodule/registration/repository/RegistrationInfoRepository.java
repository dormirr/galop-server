package cn.dormirr.coremodule.registration.repository;

import cn.dormirr.coremodule.match.info.domain.MatchInfoEntity;
import cn.dormirr.coremodule.registration.domain.RegistrationInfoEntity;
import cn.dormirr.coremodule.team.domain.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ZhangTianCi
 */
public interface RegistrationInfoRepository extends JpaRepository<RegistrationInfoEntity, Long>, JpaSpecificationExecutor<RegistrationInfoEntity> {

    /**
     * 查询本比赛本团队符合此状态的数量
     *
     * @param matchInfoEntity    比赛 ID
     * @param teamEntity         团队 ID
     * @param registrationStatus 状态
     * @return 数量
     */
    long countByMatchInfoByMatchInfoIdAndTeamByTeamIdAndRegistrationStatus(
            MatchInfoEntity matchInfoEntity,
            TeamEntity teamEntity,
            String registrationStatus
    );

    /**
     * 根据申请 ID 和申请状态查询申请
     *
     * @param id                 申请 ID
     * @param registrationStatus 申请状态
     * @return 申请
     */
    long countByIdAndRegistrationStatus(Long id, String registrationStatus);

    /**
     * 根据比赛信息和审核状态查询报名表
     *
     * @param matchInfoEntity    比赛信息
     * @param registrationStatus 审核状态
     * @return 查询结果
     */
    List<RegistrationInfoEntity> findAllByMatchInfoByMatchInfoIdAndRegistrationStatus(MatchInfoEntity matchInfoEntity, String registrationStatus);

    /**
     * 根据团队查询报名信息
     *
     * @param teamEntity 团队
     * @return 查询结果
     */
    List<RegistrationInfoEntity> findAllByTeamByTeamId(TeamEntity teamEntity);
}
