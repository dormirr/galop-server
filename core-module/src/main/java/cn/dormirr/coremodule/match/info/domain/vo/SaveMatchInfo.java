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

    public String getMatchName() {
        return matchName;
    }

    public Timestamp[] getDate() {
        return date;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public Integer getChampionAward() {
        return championAward;
    }

    public Integer getDecrementParameter() {
        return decrementParameter;
    }

    @Override
    public String toString() {
        return "SaveMatch{" +
                "matchName='" + matchName + '\'' +
                ", date=" + Arrays.toString(date) +
                ", teamSize=" + teamSize +
                ", championAward=" + championAward +
                ", decrementParameter=" + decrementParameter +
                '}';
    }
}
