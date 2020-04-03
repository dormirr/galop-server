package cn.dormirr.coremodule.registration.domain;

import cn.dormirr.coremodule.match.info.domain.MatchInfoEntity;
import cn.dormirr.coremodule.team.domain.TeamEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "registration_info", schema = "galop", catalog = "galop")
public class RegistrationInfoEntity implements Serializable {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String registrationStatus;
    private MatchInfoEntity matchInfoByMatchInfoId;
    private TeamEntity teamByTeamId;

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
    @Column(name = "registration_status", nullable = false, length = 8)
    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RegistrationInfoEntity that = (RegistrationInfoEntity) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) {
            return false;
        }
        if (getCreateTime() != null ? !getCreateTime().equals(that.getCreateTime()) : that.getCreateTime() != null) {
            return false;
        }
        if (getUpdateTime() != null ? !getUpdateTime().equals(that.getUpdateTime()) : that.getUpdateTime() != null) {
            return false;
        }
        if (getRegistrationStatus() != null ? !getRegistrationStatus().equals(that.getRegistrationStatus()) : that.getRegistrationStatus() != null) {
            return false;
        }
        if (getMatchInfoByMatchInfoId() != null ? !getMatchInfoByMatchInfoId().equals(that.getMatchInfoByMatchInfoId()) : that.getMatchInfoByMatchInfoId() != null) {
            return false;
        }
        return getTeamByTeamId() != null ? getTeamByTeamId().equals(that.getTeamByTeamId()) : that.getTeamByTeamId() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCreateTime() != null ? getCreateTime().hashCode() : 0);
        result = 31 * result + (getUpdateTime() != null ? getUpdateTime().hashCode() : 0);
        result = 31 * result + (getRegistrationStatus() != null ? getRegistrationStatus().hashCode() : 0);
        result = 31 * result + (getMatchInfoByMatchInfoId() != null ? getMatchInfoByMatchInfoId().hashCode() : 0);
        result = 31 * result + (getTeamByTeamId() != null ? getTeamByTeamId().hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "match_info_id", referencedColumnName = "id", nullable = false)
    public MatchInfoEntity getMatchInfoByMatchInfoId() {
        return matchInfoByMatchInfoId;
    }

    public void setMatchInfoByMatchInfoId(MatchInfoEntity matchInfoByMatchInfoId) {
        this.matchInfoByMatchInfoId = matchInfoByMatchInfoId;
    }

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    public TeamEntity getTeamByTeamId() {
        return teamByTeamId;
    }

    public void setTeamByTeamId(TeamEntity teamByTeamId) {
        this.teamByTeamId = teamByTeamId;
    }
}
