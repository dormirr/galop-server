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

}
