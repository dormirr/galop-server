package cn.dormirr.coremodule.role.service;

import cn.dormirr.coremodule.role.service.dto.UserDto;

import java.util.List;

/**
 * @author ZhangTianCi
 */
public interface UserService {

    /**
     * 根据学号查询用户 DTO
     *
     * @param userNumber 学号
     * @return 用户 DTO
     */
    UserDto findByUserNumber(String userNumber);

    /**
     * 添加用户
     *
     * @param user 用户
     */
    void saveUser(List<Object> user);
}
