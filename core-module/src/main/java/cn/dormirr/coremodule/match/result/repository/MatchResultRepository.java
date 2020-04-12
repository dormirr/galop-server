package cn.dormirr.coremodule.match.result.repository;

import cn.dormirr.coremodule.match.result.domain.MatchResultEntity;
import cn.dormirr.coremodule.team.domain.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ZhangTianCi
 */
public interface MatchResultRepository extends JpaRepository<MatchResultEntity, Long>, JpaSpecificationExecutor<MatchResultEntity> {
    /**
     * 根据团队查询比赛结果
     *
     * @param teamEntity 团队
     * @return 比赛结果
     */
    List<MatchResultEntity> findAllByTeamByTeamId(TeamEntity teamEntity);
}
