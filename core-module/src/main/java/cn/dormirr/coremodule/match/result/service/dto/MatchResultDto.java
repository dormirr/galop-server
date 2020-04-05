package cn.dormirr.coremodule.match.result.service.dto;

import cn.dormirr.coremodule.match.info.domain.MatchInfoEntity;
import cn.dormirr.coremodule.team.domain.TeamEntity;

import java.sql.Timestamp;

/**
 * @author ZhangTianCi
 */
public class MatchResultDto {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer ranking;
    private MatchInfoEntity matchInfoByMatchInfoId;
    private TeamEntity teamByTeamId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public MatchInfoEntity getMatchInfoByMatchInfoId() {
        return matchInfoByMatchInfoId;
    }

    public void setMatchInfoByMatchInfoId(MatchInfoEntity matchInfoByMatchInfoId) {
        this.matchInfoByMatchInfoId = matchInfoByMatchInfoId;
    }

    public TeamEntity getTeamByTeamId() {
        return teamByTeamId;
    }

    public void setTeamByTeamId(TeamEntity teamByTeamId) {
        this.teamByTeamId = teamByTeamId;
    }
}
