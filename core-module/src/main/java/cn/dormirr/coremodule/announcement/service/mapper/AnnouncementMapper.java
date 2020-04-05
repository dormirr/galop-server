package cn.dormirr.coremodule.announcement.service.mapper;

import cn.dormirr.commonmodule.base.BaseMapper;
import cn.dormirr.coremodule.announcement.domain.AnnouncementEntity;
import cn.dormirr.coremodule.announcement.service.dto.AnnouncementDto;
import org.mapstruct.Mapper;

/**
 * @author ZhangTianCi
 */
@Mapper(componentModel = "spring")
public interface AnnouncementMapper extends BaseMapper<AnnouncementDto, AnnouncementEntity> {
}
