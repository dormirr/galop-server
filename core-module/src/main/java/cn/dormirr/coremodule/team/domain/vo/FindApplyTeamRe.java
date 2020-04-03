package cn.dormirr.coremodule.team.domain.vo;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class FindApplyTeamRe implements Serializable {
    private Long id;
    private String teamName;
    private String teamState;
    private Long teamId;
    private Long userId;
    private String userName;
    private Integer userFightingCapacity;

    public FindApplyTeamRe(Long id, String teamName, String teamState, Long teamId, Long userId, String userName, Integer userFightingCapacity) {
        this.id = id;
        this.teamName = teamName;
        this.teamState = teamState;
        this.teamId = teamId;
        this.userId = userId;
        this.userName = userName;
        this.userFightingCapacity = userFightingCapacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamState() {
        return teamState;
    }

    public void setTeamState(String teamState) {
        this.teamState = teamState;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserFightingCapacity() {
        return userFightingCapacity;
    }

    public void setUserFightingCapacity(Integer userFightingCapacity) {
        this.userFightingCapacity = userFightingCapacity;
    }
}
