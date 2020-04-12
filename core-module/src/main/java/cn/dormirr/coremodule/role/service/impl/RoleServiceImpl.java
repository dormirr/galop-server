package cn.dormirr.coremodule.role.service.impl;

import cn.dormirr.coremodule.role.domain.RoleEntity;
import cn.dormirr.coremodule.role.repository.RoleRepository;
import cn.dormirr.coremodule.role.service.RoleService;
import cn.dormirr.coremodule.role.service.dto.RoleDto;
import cn.dormirr.coremodule.role.service.mapper.RoleMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author ZhangTianCi
 */
@Service
@CacheConfig(cacheNames = "role")
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
     * @return 角色 DTO
     */
    @Override
    @Cacheable
    public RoleDto findByRoleName(String roleName) {
        RoleEntity role = roleRepository.findByRoleName(roleName);
        if (role != null) {
            return roleMapper.toDto(role);
        }
        return null;
    }
}
