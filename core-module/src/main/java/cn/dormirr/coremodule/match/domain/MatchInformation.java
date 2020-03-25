package cn.dormirr.coremodule.match.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ZhangTianCi
 */
@Entity
@Table(name = "match_information", schema = "galop", catalog = "galop")
public class MatchInformation {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String matchName;
    private Integer teamSize;
    private Integer firstPlaceCombatEffectiveness;
    private Integer decrementParameter;

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
    @Column(name = "match_name", nullable = false, length = 50)
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
    @Column(name = "first_place_combat_effectiveness", nullable = false)
    public Integer getFirstPlaceCombatEffectiveness() {
        return firstPlaceCombatEffectiveness;
    }

    public void setFirstPlaceCombatEffectiveness(Integer firstPlaceCombatEffectiveness) {
        this.firstPlaceCombatEffectiveness = firstPlaceCombatEffectiveness;
    }

    @Basic
    @Column(name = "decrement_parameter", nullable = false)
    public Integer getDecrementParameter() {
        return decrementParameter;
    }

    public void setDecrementParameter(Integer decrementParameter) {
        this.decrementParameter = decrementParameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MatchInformation that = (MatchInformation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(matchName, that.matchName) &&
                Objects.equals(teamSize, that.teamSize) &&
                Objects.equals(firstPlaceCombatEffectiveness, that.firstPlaceCombatEffectiveness) &&
                Objects.equals(decrementParameter, that.decrementParameter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, matchName, teamSize, firstPlaceCombatEffectiveness, decrementParameter);
    }
}
