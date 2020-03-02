package cn.dormirr.coremodule.role.service.impl;

import cn.dormirr.coremodule.role.domain.User;
import cn.dormirr.coremodule.role.repository.UserRepository;
import cn.dormirr.coremodule.role.service.UserService;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.role.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author ZhangTianCi
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * @param userNumber 学号
     * @return 用户 DTO
     */
    @Override
    public UserDto findByUserNumber(String userNumber) {
        User user = userRepository.findByUserNumber(userNumber);
        if (user != null) {
            return userMapper.toDto(user);
        }
        // TODO 抛异常
        return null;
    }
}
