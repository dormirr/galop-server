package cn.dormirr.coremodule.match.info.domain.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * @author ZhangTianCi
 */
public class SaveMatchInfo implements Serializable {
    private String matchName;
    private Timestamp[] date;
    private Integer teamSize;
    private Integer championAward;
    private Integer decrementParameter;
    private String matchType;

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public Timestamp[] getDate() {
        return date;
    }

    public void setDate(Timestamp[] date) {
        this.date = date;
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

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }
}
