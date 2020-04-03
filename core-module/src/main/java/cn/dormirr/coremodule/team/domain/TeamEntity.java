package cn.dormirr.coremodule.team.domain;

import cn.dormirr.coremodule.role.domain.RoleEntity;
import cn.dormirr.coremodule.role.domain.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TeamEntity that = (TeamEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) {
            return false;
        }
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) {
            return false;
        }
        if (teamName != null ? !teamName.equals(that.teamName) : that.teamName != null) {
            return false;
        }
        if (teamProfile != null ? !teamProfile.equals(that.teamProfile) : that.teamProfile != null) {
            return false;
        }
        if (teamState != null ? !teamState.equals(that.teamState) : that.teamState != null) {
            return false;
        }
        if (teamFightingCapacity != null ? !teamFightingCapacity.equals(that.teamFightingCapacity) : that.teamFightingCapacity != null) {
            return false;
        }
        return teamId != null ? teamId.equals(that.teamId) : that.teamId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
        result = 31 * result + (teamProfile != null ? teamProfile.hashCode() : 0);
        result = 31 * result + (teamState != null ? teamState.hashCode() : 0);
        result = 31 * result + (teamFightingCapacity != null ? teamFightingCapacity.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        return result;
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
