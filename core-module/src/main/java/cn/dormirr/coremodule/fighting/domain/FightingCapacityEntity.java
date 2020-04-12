package cn.dormirr.coremodule.fighting.domain;

import cn.dormirr.coremodule.match.info.domain.MatchInfoEntity;
import cn.dormirr.coremodule.role.domain.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ZhangTianCi
 */
@Entity
@Table(name = "fighting_capacity", schema = "galop", catalog = "galop")
public class FightingCapacityEntity implements Serializable {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer reward;
    private MatchInfoEntity matchInfoByMatchInfoId;
    private UserEntity userByUserId;

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
    @Column(name = "reward", nullable = false)
    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FightingCapacityEntity that = (FightingCapacityEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCreateTime(), that.getCreateTime()) &&
                Objects.equals(getUpdateTime(), that.getUpdateTime()) &&
                Objects.equals(getReward(), that.getReward()) &&
                Objects.equals(getMatchInfoByMatchInfoId(), that.getMatchInfoByMatchInfoId()) &&
                Objects.equals(getUserByUserId(), that.getUserByUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreateTime(), getUpdateTime(), getReward(), getMatchInfoByMatchInfoId(), getUserByUserId());
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
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @Override
    public String toString() {
        return "FightingCapacityEntity{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", reward=" + reward +
                ", matchInfoByMatchInfoId=" + matchInfoByMatchInfoId +
                ", userByUserId=" + userByUserId +
                '}';
    }
}
