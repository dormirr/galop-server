package cn.dormirr.coremodule.match.info.service.dto;

import java.sql.Timestamp;

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
}
