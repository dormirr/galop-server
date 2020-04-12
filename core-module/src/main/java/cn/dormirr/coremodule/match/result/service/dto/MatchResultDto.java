package cn.dormirr.coremodule.match.result.service.dto;

import cn.dormirr.coremodule.match.info.domain.MatchInfoEntity;
import cn.dormirr.coremodule.team.domain.TeamEntity;

import java.sql.Timestamp;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchResultDto that = (MatchResultDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCreateTime(), that.getCreateTime()) &&
                Objects.equals(getUpdateTime(), that.getUpdateTime()) &&
                Objects.equals(getRanking(), that.getRanking()) &&
                Objects.equals(getMatchInfoByMatchInfoId(), that.getMatchInfoByMatchInfoId()) &&
                Objects.equals(getTeamByTeamId(), that.getTeamByTeamId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreateTime(), getUpdateTime(), getRanking(), getMatchInfoByMatchInfoId(), getTeamByTeamId());
    }

    @Override
    public String toString() {
        return "MatchResultDto{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", ranking=" + ranking +
                ", matchInfoByMatchInfoId=" + matchInfoByMatchInfoId +
                ", teamByTeamId=" + teamByTeamId +
                '}';
    }
}
