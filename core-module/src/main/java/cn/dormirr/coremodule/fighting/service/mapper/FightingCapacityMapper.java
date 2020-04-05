package cn.dormirr.coremodule.fighting.service.mapper;

import cn.dormirr.commonmodule.base.BaseMapper;
import cn.dormirr.coremodule.fighting.domain.FightingCapacityEntity;
import cn.dormirr.coremodule.fighting.service.dto.FightingCapacityDto;
import org.mapstruct.Mapper;

/**
 * @author ZhangTianCi
 */
@Mapper(componentModel = "spring")
public interface FightingCapacityMapper extends BaseMapper<FightingCapacityDto, FightingCapacityEntity> {
}
