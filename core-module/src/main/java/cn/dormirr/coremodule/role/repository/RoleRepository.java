package cn.dormirr.coremodule.role.repository;

import cn.dormirr.coremodule.role.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ZhangTianCi
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    /**
     * 根据角色名查询角色
     *
     * @param roleName 角色名
     * @return 角色
     */
    Role findByRoleName(String roleName);
}
