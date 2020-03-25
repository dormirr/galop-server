package cn.dormirr.coremodule.team.service.mapper;

import cn.dormirr.commonmodule.base.BaseMapper;
import cn.dormirr.coremodule.team.domain.Team;
import cn.dormirr.coremodule.team.service.dto.TeamDto;
import org.mapstruct.Mapper;

/**
 * @author ZhangTianCi
 */
@Mapper(componentModel = "spring")
public interface TeamMapper extends BaseMapper<TeamDto, Team> {

    /**
     * Team 实体转 TeamDTO
     *
     * @param team Team 实体
     * @return TeamDTO
     */
    @Override
    TeamDto toDto(Team team);

    /**
     * TeamDTO 转 Team 实体
     *
     * @param teamDto TeamDTO
     * @return Team 实体
     */
    @Override
    Team toEntity(TeamDto teamDto);
}
