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
     * 批量添加用户
     *
     * @param user 用户
     */
    void saveUser(List<Object> user);

    /**
     * 修改用户头像
     *
     * @param userDto 用户
     */
    void saveUserPortrait(UserDto userDto);

    /**
     * 修改用户姓名和邮箱
     *
     * @param userDto 用户
     */
    void saveUserNameAndEmail(UserDto userDto);

    /**
     * 修改用户密码
     *
     * @param userDto 用户
     */
    void saveUserPassword(UserDto userDto);

    /**
     * 修改用户角色
     *
     * @param userDto 用户
     */
    void saveUserRole(UserDto userDto);

    /**
     * 查询积分前十的人
     *
     * @return 查询结果
     */
    List<UserDto> findUserFightingCapacity();
}
