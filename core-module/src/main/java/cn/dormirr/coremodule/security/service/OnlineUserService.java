package cn.dormirr.coremodule.security.service;

import cn.dormirr.commonmodule.utils.EncryptUtils;
import cn.dormirr.commonmodule.utils.RedisUtils;
import cn.dormirr.commonmodule.utils.StringUtils;
import cn.dormirr.coremodule.security.config.SecurityProperties;
import cn.dormirr.coremodule.security.security.vo.JwtUser;
import cn.dormirr.coremodule.security.security.vo.OnlineUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author ZhangTianCi
 */
@Service
@Slf4j
public class OnlineUserService {
    private final SecurityProperties properties;
    private final RedisUtils redisUtils;

    public OnlineUserService(SecurityProperties properties, RedisUtils redisUtils) {
        this.properties = properties;
        this.redisUtils = redisUtils;
    }

    /**
     * 保存在线用户信息
     */
    public void save(JwtUser jwtUser, String token, HttpServletRequest request) {
        OnlineUser onlineUser = null;
        try {
            onlineUser = new OnlineUser(jwtUser.getUsername(), jwtUser.getUserName(), jwtUser.getRoleByRoleId().getRoleName(), EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisUtils.set(properties.getOnlineKey() + token, onlineUser, properties.getTokenValidityInSeconds() / 1000);
    }

    /**
     * 查询全部数据，不分页
     */
    public List<OnlineUser> getAll(String filter) {
        List<String> keys = redisUtils.scan(properties.getOnlineKey() + "*");
        Collections.reverse(keys);
        List<OnlineUser> onlineUsers = new ArrayList<>();
        for (String key : keys) {
            OnlineUser onlineUser = (OnlineUser) redisUtils.get(key);
            if (StringUtils.isNotBlank(filter)) {
                if (onlineUser.toString().contains(filter)) {
                    onlineUsers.add(onlineUser);
                }
            } else {
                onlineUsers.add(onlineUser);
            }
        }
        onlineUsers.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUsers;
    }

    /**
     * 踢出用户
     */
    public void kickOut(String key) throws Exception {
        key = properties.getOnlineKey() + EncryptUtils.desDecrypt(key);
        redisUtils.del(key);
    }

    /**
     * 退出登录
     */
    public void logout(String token) {
        String key = properties.getOnlineKey() + token;
        redisUtils.del(key);
    }

    /**
     * 查询用户
     */
    public OnlineUser getOne(String key) {
        return (OnlineUser) redisUtils.get(key);
    }

    /**
     * 检测用户是否在之前已经登录，已经登录踢下线
     */
    public void checkLoginOnUser(String userName, String ignoreToken) {
        List<OnlineUser> onlineUsers = getAll(userName);
        if (onlineUsers == null || onlineUsers.isEmpty()) {
            return;
        }
        for (OnlineUser onlineUser : onlineUsers) {
            if (onlineUser.getUserName().equals(userName)) {
                try {
                    String token = EncryptUtils.desDecrypt(onlineUser.getKey());
                    if (StringUtils.isNotBlank(ignoreToken) && !ignoreToken.equals(token)) {
                        this.kickOut(onlineUser.getKey());
                    } else if (StringUtils.isBlank(ignoreToken)) {
                        this.kickOut(onlineUser.getKey());
                    }
                } catch (Exception e) {
                    log.error("check User 错误", e);
                }
            }
        }
    }
}
