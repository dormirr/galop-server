package cn.dormirr.coremodule.registration.domain.vo;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class DownloadMatchResult implements Serializable {
    Long teamId;
    String teamName;
    Integer ranking;

    public DownloadMatchResult() {
    }

    public DownloadMatchResult(Long teamId, String teamName, Integer ranking) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.ranking = ranking;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}
