package cn.dormirr.coremodule.match.result.service.mapper;

import cn.dormirr.commonmodule.base.BaseMapper;
import cn.dormirr.coremodule.match.result.domain.MatchResultEntity;
import cn.dormirr.coremodule.match.result.service.dto.MatchResultDto;
import org.mapstruct.Mapper;

/**
 * @author ZhangTianCi
 */
@Mapper(componentModel = "spring")
public interface MatchResultMapper extends BaseMapper<MatchResultDto, MatchResultEntity> {
}
