package cn.dormirr.coremodule.role.service.mapper;

import cn.dormirr.commonmodule.base.BaseMapper;
import cn.dormirr.coremodule.role.domain.UserEntity;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import org.mapstruct.Mapper;

/**
 * @author ZhangTianCi
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDto, UserEntity> {

}
