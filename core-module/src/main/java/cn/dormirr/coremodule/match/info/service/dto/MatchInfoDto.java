package cn.dormirr.coremodule.match.info.service.dto;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ZhangTianCi
 */
public class MatchInfoDto {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String matchName;
    private Integer teamSize;
    private Integer championAward;
    private Integer decrementParameter;
    private Timestamp startTime;
    private Timestamp endTime;
    private String matchType;

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

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }

    public Integer getChampionAward() {
        return championAward;
    }

    public void setChampionAward(Integer championAward) {
        this.championAward = championAward;
    }

    public Integer getDecrementParameter() {
        return decrementParameter;
    }

    public void setDecrementParameter(Integer decrementParameter) {
        this.decrementParameter = decrementParameter;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchInfoDto that = (MatchInfoDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCreateTime(), that.getCreateTime()) &&
                Objects.equals(getUpdateTime(), that.getUpdateTime()) &&
                Objects.equals(getMatchName(), that.getMatchName()) &&
                Objects.equals(getTeamSize(), that.getTeamSize()) &&
                Objects.equals(getChampionAward(), that.getChampionAward()) &&
                Objects.equals(getDecrementParameter(), that.getDecrementParameter()) &&
                Objects.equals(getStartTime(), that.getStartTime()) &&
                Objects.equals(getEndTime(), that.getEndTime()) &&
                Objects.equals(getMatchType(), that.getMatchType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreateTime(), getUpdateTime(), getMatchName(), getTeamSize(), getChampionAward(), getDecrementParameter(), getStartTime(), getEndTime(), getMatchType());
    }

    @Override
    public String toString() {
        return "MatchInfoDto{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", matchName='" + matchName + '\'' +
                ", teamSize=" + teamSize +
                ", championAward=" + championAward +
                ", decrementParameter=" + decrementParameter +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", matchType='" + matchType + '\'' +
                '}';
    }
}
