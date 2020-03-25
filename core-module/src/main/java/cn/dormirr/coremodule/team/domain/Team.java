package cn.dormirr.coremodule.team.domain;


import cn.dormirr.coremodule.role.domain.Role;
import cn.dormirr.coremodule.role.domain.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ZhangTianCi
 */
@Entity
@Table(name = "team", schema = "galop", catalog = "galop")
public class Team {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String teamName;
    private Integer totalTeamFightingPower;
    private String teamState;
    private String teamProfile;
    private User userByUserId;
    private Role roleByRoleId;

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
    @Column(name = "team_name", nullable = false, length = 50)
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Basic
    @Column(name = "total_team_fighting_power", nullable = false)
    public Integer getTotalTeamFightingPower() {
        return totalTeamFightingPower;
    }

    public void setTotalTeamFightingPower(Integer totalTeamFightingPower) {
        this.totalTeamFightingPower = totalTeamFightingPower;
    }

    @Basic
    @Column(name = "team_state", nullable = false, length = 12)
    public String getTeamState() {
        return teamState;
    }

    public void setTeamState(String teamState) {
        this.teamState = teamState;
    }

    @Basic
    @Column(name = "team_profile", nullable = true, length = -1)
    public String getTeamProfile() {
        return teamProfile;
    }

    public void setTeamProfile(String teamProfile) {
        this.teamProfile = teamProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Team team = (Team) o;
        return Objects.equals(id, team.id) &&
                Objects.equals(createTime, team.createTime) &&
                Objects.equals(updateTime, team.updateTime) &&
                Objects.equals(teamName, team.teamName) &&
                Objects.equals(totalTeamFightingPower, team.totalTeamFightingPower) &&
                Objects.equals(teamState, team.teamState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, teamName, totalTeamFightingPower, teamState);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }
}
