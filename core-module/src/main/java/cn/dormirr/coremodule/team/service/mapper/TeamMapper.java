package cn.dormirr.coremodule.team.service.mapper;

import cn.dormirr.commonmodule.base.BaseMapper;
import cn.dormirr.coremodule.team.domain.TeamEntity;
import cn.dormirr.coremodule.team.service.dto.TeamDto;
import org.mapstruct.Mapper;

/**
 * @author ZhangTianCi
 */
@Mapper(componentModel = "spring")
public interface TeamMapper extends BaseMapper<TeamDto, TeamEntity> {

}
