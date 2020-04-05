package cn.dormirr.coremodule.fighting.repository;

import cn.dormirr.coremodule.fighting.domain.FightingCapacityEntity;
import cn.dormirr.coremodule.match.info.domain.MatchInfoEntity;
import cn.dormirr.coremodule.role.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ZhangTianCi
 */
public interface FightingCapacityRepository extends JpaRepository<FightingCapacityEntity, Long>, JpaSpecificationExecutor<FightingCapacityEntity> {

    /**
     * 根据用户查询积分表
     *
     * @param userEntity 用户
     * @return 查询结果
     */
    List<FightingCapacityEntity> findAllByUserByUserId(UserEntity userEntity);

    /**
     * 根据比赛 ID 查询人数
     *
     * @param matchInfoEntity 比赛 ID
     * @return 查询结果
     */
    int countAllByMatchInfoByMatchInfoId(MatchInfoEntity matchInfoEntity);
}
