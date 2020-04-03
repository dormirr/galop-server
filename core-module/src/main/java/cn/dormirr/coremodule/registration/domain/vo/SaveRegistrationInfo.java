package cn.dormirr.coremodule.registration.domain.vo;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class SaveRegistrationInfo implements Serializable {
    private Long matchId;
    private Long teamId;

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
