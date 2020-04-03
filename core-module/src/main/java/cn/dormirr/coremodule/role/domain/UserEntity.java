package cn.dormirr.coremodule.role.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ZhangTianCi
 */
@Entity
@Table(name = "user", schema = "galop", catalog = "galop")
public class UserEntity implements Serializable {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String userNumber;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPortrait;
    private Integer userFightingCapacity;
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
    @Column(name = "user_number", nullable = false, length = 12, updatable = false)
    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 24)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_password", nullable = false, length = 172, insertable = false)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "user_email", nullable = true, length = 320)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "user_portrait", nullable = false, length = 2083, insertable = false)
    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    @Basic
    @Column(name = "user_fighting_capacity", nullable = false, insertable = false)
    public Integer getUserFightingCapacity() {
        return userFightingCapacity;
    }

    public void setUserFightingCapacity(Integer userFightingCapacity) {
        this.userFightingCapacity = userFightingCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(userNumber, that.userNumber) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userPassword, that.userPassword) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(userPortrait, that.userPortrait) &&
                Objects.equals(userFightingCapacity, that.userFightingCapacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, userNumber, userName, userPassword, userEmail, userPortrait, userFightingCapacity);
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public RoleEntity getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(RoleEntity roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }
}
