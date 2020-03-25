package cn.dormirr.coremodule.match.repository;

import cn.dormirr.coremodule.match.domain.MatchInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ZhangTianCi
 */
public interface MatchInformationRepository extends JpaRepository<MatchInformation, Long>, JpaSpecificationExecutor<MatchInformation> {
}
