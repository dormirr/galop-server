package cn.dormirr.coremodule.role.service.dto;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ZhangTianCi
 */
public class RoleDto{
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String roleName;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(getId(), roleDto.getId()) &&
                Objects.equals(getCreateTime(), roleDto.getCreateTime()) &&
                Objects.equals(getUpdateTime(), roleDto.getUpdateTime()) &&
                Objects.equals(getRoleName(), roleDto.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreateTime(), getUpdateTime(), getRoleName());
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
