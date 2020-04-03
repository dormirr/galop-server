package cn.dormirr.coremodule.team.service.dto;

import cn.dormirr.coremodule.role.domain.RoleEntity;
import cn.dormirr.coremodule.role.domain.UserEntity;

import java.sql.Timestamp;

/**
 * @author ZhangTianCi
 */
public class TeamDto {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String teamName;
    private String teamProfile;
    private String teamState;
    private Integer teamFightingCapacity;
    private Long teamId;
    private UserEntity userByUserId;
    private RoleEntity roleByRoleId;

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

    public String getTeamState() {
        return teamState;
    }

    public void setTeamState(String teamState) {
        this.teamState = teamState;
    }

    public Integer getTeamFightingCapacity() {
        return teamFightingCapacity;
    }

    public void setTeamFightingCapacity(Integer teamFightingCapacity) {
        this.teamFightingCapacity = teamFightingCapacity;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    public RoleEntity getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(RoleEntity roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }
}
