package cn.dormirr.coremodule.role.service.mapper;

import cn.dormirr.coremodule.role.domain.User;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import org.mapstruct.Mapper;

/**
 * @author ZhangTianCi
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDto, User> {

    /**
     * User 实体转 UserDTO
     *
     * @param user User 实体
     * @return UserDTO
     */
    @Override
    UserDto toDto(User user);
}
