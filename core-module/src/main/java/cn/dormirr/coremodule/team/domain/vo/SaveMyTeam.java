package cn.dormirr.coremodule.team.domain.vo;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class SaveMyTeam implements Serializable {
    private String teamName;
    private String teamProfile;
    private Long teamId;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamProfile() {
        return teamProfile;
    }

    public void setTeamProfile(String teamProfile) {
        this.teamProfile = teamProfile;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
