package cn.dormirr.coremodule.team.service.dto;

import cn.dormirr.coremodule.role.domain.RoleEntity;
import cn.dormirr.coremodule.role.domain.UserEntity;

import java.sql.Timestamp;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDto teamDto = (TeamDto) o;
        return Objects.equals(getId(), teamDto.getId()) &&
                Objects.equals(getCreateTime(), teamDto.getCreateTime()) &&
                Objects.equals(getUpdateTime(), teamDto.getUpdateTime()) &&
                Objects.equals(getTeamName(), teamDto.getTeamName()) &&
                Objects.equals(getTeamProfile(), teamDto.getTeamProfile()) &&
                Objects.equals(getTeamState(), teamDto.getTeamState()) &&
                Objects.equals(getTeamFightingCapacity(), teamDto.getTeamFightingCapacity()) &&
                Objects.equals(getTeamId(), teamDto.getTeamId()) &&
                Objects.equals(getUserByUserId(), teamDto.getUserByUserId()) &&
                Objects.equals(getRoleByRoleId(), teamDto.getRoleByRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreateTime(), getUpdateTime(), getTeamName(), getTeamProfile(), getTeamState(), getTeamFightingCapacity(), getTeamId(), getUserByUserId(), getRoleByRoleId());
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", teamName='" + teamName + '\'' +
                ", teamProfile='" + teamProfile + '\'' +
                ", teamState='" + teamState + '\'' +
                ", teamFightingCapacity=" + teamFightingCapacity +
                ", teamId=" + teamId +
                ", userByUserId=" + userByUserId +
                ", roleByRoleId=" + roleByRoleId +
                '}';
    }
}
