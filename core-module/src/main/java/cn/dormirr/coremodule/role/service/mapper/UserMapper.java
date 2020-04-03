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

    /**
     * UserEntity 转 UserDTO
     *
     * @param userEntity UserEntity
     * @return UserDTO
     */
    @Override
    UserDto toDto(UserEntity userEntity);
}
