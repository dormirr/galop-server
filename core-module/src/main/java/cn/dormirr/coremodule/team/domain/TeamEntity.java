package cn.dormirr.coremodule.team.domain;

import cn.dormirr.coremodule.role.domain.RoleEntity;
import cn.dormirr.coremodule.role.domain.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ZhangTianCi
 */
@Entity
@Table(name = "team", schema = "galop", catalog = "galop")
public class TeamEntity implements Serializable {
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

    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "create_time", nullable = false, insertable = false, updatable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false, insertable = false, updatable = false)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "team_name", nullable = false, length = 48)
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Basic
    @Column(name = "team_profile", nullable = true, length = -1)
    public String getTeamProfile() {
        return teamProfile;
    }

    public void setTeamProfile(String teamProfile) {
        this.teamProfile = teamProfile;
    }

    @Basic
    @Column(name = "team_state", nullable = false, length = 8)
    public String getTeamState() {
        return teamState;
    }

    public void setTeamState(String teamState) {
        this.teamState = teamState;
    }

    @Basic
    @Column(name = "team_fighting_capacity", nullable = false)
    public Integer getTeamFightingCapacity() {
        return teamFightingCapacity;
    }

    public void setTeamFightingCapacity(Integer teamFightingCapacity) {
        this.teamFightingCapacity = teamFightingCapacity;
    }

    @Basic
    @Column(name = "team_id", nullable = false)
    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamEntity that = (TeamEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCreateTime(), that.getCreateTime()) &&
                Objects.equals(getUpdateTime(), that.getUpdateTime()) &&
                Objects.equals(getTeamName(), that.getTeamName()) &&
                Objects.equals(getTeamProfile(), that.getTeamProfile()) &&
                Objects.equals(getTeamState(), that.getTeamState()) &&
                Objects.equals(getTeamFightingCapacity(), that.getTeamFightingCapacity()) &&
                Objects.equals(getTeamId(), that.getTeamId()) &&
                Objects.equals(getUserByUserId(), that.getUserByUserId()) &&
                Objects.equals(getRoleByRoleId(), that.getRoleByRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreateTime(), getUpdateTime(), getTeamName(), getTeamProfile(), getTeamState(), getTeamFightingCapacity(), getTeamId(), getUserByUserId(), getRoleByRoleId());
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public RoleEntity getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(RoleEntity roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @Override
    public String toString() {
        return "TeamEntity{" +
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
