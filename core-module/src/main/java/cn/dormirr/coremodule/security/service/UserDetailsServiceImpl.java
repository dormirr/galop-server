package cn.dormirr.coremodule.security.service;

import cn.dormirr.commonmodule.exception.BadRequestException;
import cn.dormirr.coremodule.role.service.UserService;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.security.security.vo.JwtUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author ZhangTianCi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userNumber) {

        UserDto userDto = userService.findByUserNumber(userNumber);
        if (userDto == null) {
            throw new BadRequestException("账号或密码错误");
        }

        return createJwtUser(userDto);
    }

    private JwtUser createJwtUser(UserDto userDto) {
        return new JwtUser(userDto);
    }
}
