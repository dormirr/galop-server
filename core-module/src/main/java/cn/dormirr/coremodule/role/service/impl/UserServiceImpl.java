package cn.dormirr.coremodule.role.service.impl;

import cn.dormirr.coremodule.role.domain.User;
import cn.dormirr.coremodule.role.repository.UserRepository;
import cn.dormirr.coremodule.role.service.RoleService;
import cn.dormirr.coremodule.role.service.UserService;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.role.service.mapper.RoleMapper;
import cn.dormirr.coremodule.role.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhangTianCi
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleMapper roleMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.roleService = roleService;
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
        return null;
    }

    /**
     * 批量添加用户
     *
     * @param user 用户
     */
    @Override
    public void saveUser(List<Object> user) {
        User users = new User();
        users.setUserNumber((String) user.get(0));
        users.setUserName((String) user.get(1));
        if (user.get(2) != null) {
            users.setUserEmail((String) user.get(2));
        }
        if ("学生".equals(user.get(3))) {
            users.setRoleByRoleId(roleMapper.toEntity(roleService.findByRoleName("学生")));
        } else if ("老师".equals(user.get(3))) {
            users.setRoleByRoleId(roleMapper.toEntity(roleService.findByRoleName("老师")));
        }
        userRepository.save(users);
    }

    /**
     * 修改用户头像
     *
     * @param userDto 用户
     */
    @Override
    public void saveUserPortrait(UserDto userDto) {
        userRepository.save(userMapper.toEntity(userDto));
    }
}
