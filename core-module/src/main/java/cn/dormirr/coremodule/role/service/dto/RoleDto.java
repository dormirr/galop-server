package cn.dormirr.coremodule.role.service.dto;

/**
 * @author ZhangTianCi
 */
public class RoleDto {
    private Long id;
    private String roleName;

    public RoleDto() {
    }

    public RoleDto(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        if (!getId().equals(roleDto.getId())) {
            return false;
        }
        return getRoleName().equals(roleDto.getRoleName());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getRoleName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
