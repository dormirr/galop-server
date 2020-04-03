package cn.dormirr.coremodule.role.service.mapper;

import cn.dormirr.commonmodule.base.BaseMapper;
import cn.dormirr.coremodule.role.domain.RoleEntity;
import cn.dormirr.coremodule.role.service.dto.RoleDto;
import org.mapstruct.Mapper;

/**
 * @author ZhangTianCi
 */
@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<RoleDto, RoleEntity> {

    /**
     * DTO 转 RoleEntity
     *
     * @param roleDto RoleDTO
     * @return RoleEntity
     */
    @Override
    RoleEntity toEntity(RoleDto roleDto);

    /**
     * RoleEntity 转 RoleDTO
     *
     * @param roleEntity roleEntity
     * @return RoleDTO
     */
    @Override
    RoleDto toDto(RoleEntity roleEntity);
}
