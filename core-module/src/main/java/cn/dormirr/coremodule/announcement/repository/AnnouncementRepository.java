package cn.dormirr.coremodule.announcement.repository;

import cn.dormirr.coremodule.announcement.domain.AnnouncementEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ZhangTianCi
 */
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long>, JpaSpecificationExecutor<AnnouncementEntity> {

}
