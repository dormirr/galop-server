package cn.dormirr.coremodule.role.service.mapper;

import cn.dormirr.commonmodule.base.BaseMapper;
import cn.dormirr.coremodule.role.domain.Role;
import cn.dormirr.coremodule.role.service.dto.RoleDto;
import org.mapstruct.Mapper;

/**
 * @author ZhangTianCi
 */
@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<RoleDto, Role> {

    /**
     * DTO 转实体
     *
     * @param roleDto DTO
     * @return 实体
     */
    @Override
    Role toEntity(RoleDto roleDto);

    /**
     * Role 实体转 RoleDTO
     *
     * @param role Role 实体
     * @return RoleDTO
     */
    @Override
    RoleDto toDto(Role role);
}
