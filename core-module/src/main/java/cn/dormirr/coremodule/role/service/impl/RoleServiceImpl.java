package cn.dormirr.coremodule.role.service.impl;

import cn.dormirr.coremodule.role.domain.Role;
import cn.dormirr.coremodule.role.repository.RoleRepository;
import cn.dormirr.coremodule.role.service.RoleService;
import cn.dormirr.coremodule.role.service.dto.RoleDto;
import cn.dormirr.coremodule.role.service.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
 * @author ZhangTianCi
 */
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    /**
     * 根据角色名查询角色
     *
     * @param roleName 角色名
     * @return 角色DTO
     */
    @Override
    public RoleDto findByRoleName(String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        if (role != null) {
            return roleMapper.toDto(role);
        }
        return null;
    }
}
