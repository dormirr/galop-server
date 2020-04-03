package cn.dormirr.coremodule.match.info.repository;

import cn.dormirr.coremodule.match.info.domain.MatchInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author ZhangTianCi
 */
public interface MatchInfoRepository extends JpaRepository<MatchInfoEntity, Long>, JpaSpecificationExecutor<MatchInfoEntity> {

    /**
     * 查询结束时间大于当前时间的比赛
     *
     * @param endTime 当前时间
     * @return 查询结果
     */
    List<MatchInfoEntity> findAllByEndTimeGreaterThan(Timestamp endTime);
}
