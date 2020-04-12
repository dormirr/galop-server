package cn.dormirr.coremodule.match.info.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ZhangTianCi
 */
@Entity
@Table(name = "match_info", schema = "galop", catalog = "galop")
public class MatchInfoEntity implements Serializable {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String matchName;
    private Integer teamSize;
    private Integer championAward;
    private Integer decrementParameter;
    private Timestamp startTime;
    private Timestamp endTime;
    private String matchType;

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
    @Column(name = "match_name", nullable = false, length = 80)
    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    @Basic
    @Column(name = "team_size", nullable = false)
    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }

    @Basic
    @Column(name = "champion_award", nullable = false)
    public Integer getChampionAward() {
        return championAward;
    }

    public void setChampionAward(Integer championAward) {
        this.championAward = championAward;
    }

    @Basic
    @Column(name = "decrement_parameter", nullable = false)
    public Integer getDecrementParameter() {
        return decrementParameter;
    }

    public void setDecrementParameter(Integer decrementParameter) {
        this.decrementParameter = decrementParameter;
    }

    @Basic
    @Column(name = "start_time", nullable = false)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = false)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "match_type", nullable = false, length = 20)
    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MatchInfoEntity that = (MatchInfoEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCreateTime(), that.getCreateTime()) &&
                Objects.equals(getUpdateTime(), that.getUpdateTime()) &&
                Objects.equals(getMatchName(), that.getMatchName()) &&
                Objects.equals(getTeamSize(), that.getTeamSize()) &&
                Objects.equals(getChampionAward(), that.getChampionAward()) &&
                Objects.equals(getDecrementParameter(), that.getDecrementParameter()) &&
                Objects.equals(getStartTime(), that.getStartTime()) &&
                Objects.equals(getEndTime(), that.getEndTime()) &&
                Objects.equals(getMatchType(), that.getMatchType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreateTime(), getUpdateTime(), getMatchName(), getTeamSize(), getChampionAward(), getDecrementParameter(), getStartTime(), getEndTime(), getMatchType());
    }

    @Override
    public String toString() {
        return "MatchInfoEntity{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", matchName='" + matchName + '\'' +
                ", teamSize=" + teamSize +
                ", championAward=" + championAward +
                ", decrementParameter=" + decrementParameter +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", matchType='" + matchType + '\'' +
                '}';
    }
}
