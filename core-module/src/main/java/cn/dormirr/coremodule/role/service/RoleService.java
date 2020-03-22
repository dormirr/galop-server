package cn.dormirr.coremodule.role.service;

import cn.dormirr.coremodule.role.service.dto.RoleDto;

/**
 * @author ZhangTianCi
 */
public interface RoleService {

    /**
     * 根据角色名查询角色
     *
     * @param roleName 角色名
     * @return 角色
     */
    RoleDto findByRoleName(String roleName);
}
