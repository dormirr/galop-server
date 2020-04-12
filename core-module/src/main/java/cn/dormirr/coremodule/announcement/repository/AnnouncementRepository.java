package cn.dormirr.coremodule.announcement.repository;

import cn.dormirr.coremodule.announcement.domain.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ZhangTianCi
 */
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long>, JpaSpecificationExecutor<AnnouncementEntity> {

}
