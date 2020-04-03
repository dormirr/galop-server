package cn.dormirr.coremodule.match.info.service.mapper;

import cn.dormirr.commonmodule.base.BaseMapper;
import cn.dormirr.coremodule.match.info.domain.MatchInfoEntity;
import cn.dormirr.coremodule.match.info.service.dto.MatchInfoDto;
import org.mapstruct.Mapper;

/**
 * @author ZhangTianCi
 */
@Mapper(componentModel = "spring")
public interface MatchInfoMapper extends BaseMapper<MatchInfoDto, MatchInfoEntity> {

    /**
     * MatchInfoDto 转 MatchInfoEntity
     *
     * @param matchInfoDto MatchInfoDTO
     * @return MatchInfoEntity
     */
    @Override
    MatchInfoEntity toEntity(MatchInfoDto matchInfoDto);

    /**
     * MatchInfoEntity 转 MatchInfoDTO
     *
     * @param matchInfoEntity MatchInfoEntity
     * @return MatchInfoDTO
     */
    @Override
    MatchInfoDto toDto(MatchInfoEntity matchInfoEntity);
}
