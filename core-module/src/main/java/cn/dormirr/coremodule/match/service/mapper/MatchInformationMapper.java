package cn.dormirr.coremodule.match.service.mapper;

import cn.dormirr.commonmodule.base.BaseMapper;
import cn.dormirr.coremodule.match.domain.MatchInformation;
import cn.dormirr.coremodule.match.service.dto.MatchInformationDto;
import org.mapstruct.Mapper;

/**
 * @author ZhangTianCi
 */
@Mapper(componentModel = "spring")
public interface MatchInformationMapper extends BaseMapper<MatchInformationDto, MatchInformation> {

    /**
     * DTO 转实体
     *
     * @param matchInformationDto MatchInformationDTO
     * @return 实体
     */
    @Override
    MatchInformation toEntity(MatchInformationDto matchInformationDto);

    /**
     * MatchInformation 实体转 MatchInformationDTO
     *
     * @param matchInformation MatchInformation 实体
     * @return MatchInformationDTO
     */
    @Override
    MatchInformationDto toDto(MatchInformation matchInformation);
}
