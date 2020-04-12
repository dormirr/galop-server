package cn.dormirr.coremodule.role.service;

import cn.dormirr.coremodule.role.service.dto.UserDto;
import org.springframework.data.domain.Page;

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


    /**
     * 给用户发送密码邮件
     *
     * @param userDto 用户
     */
    void email(UserDto userDto);

    /**
     * 重置密码
     *
     * @param uuid uuid
     */
    void forget(String uuid);


    /**
     * 重置密码
     *
     * @param userDto 用户
     */
    void forgetUser(UserDto userDto);

    /**
     * 删除用户
     *
     * @param userNumber 学号
     */
    void removeUser(String userNumber);

    /**
     * 动态查询用户
     *
     * @param userDto  查询条件
     * @param pageSize 每页数量
     * @param current  第几页
     * @param sorter   排序规则
     * @return 查询结果
     */
    Page<UserDto> findUser(UserDto userDto, int pageSize, int current, String sorter);
}
