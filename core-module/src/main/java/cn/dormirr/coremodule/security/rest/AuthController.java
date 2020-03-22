package cn.dormirr.coremodule.security.rest;

import cn.dormirr.commonmodule.utils.RedisUtils;
import cn.dormirr.commonmodule.utils.SecurityUtils;
import cn.dormirr.coremodule.security.config.SecurityProperties;
import cn.dormirr.coremodule.security.security.TokenProvider;
import cn.dormirr.coremodule.security.security.vo.AuthUser;
import cn.dormirr.coremodule.security.security.vo.JwtUser;
import cn.dormirr.coremodule.security.service.OnlineUserService;
import cn.hutool.core.util.IdUtil;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 系统授权接口
 *
 * @author ZhangTianCi
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Value("${loginCode.expiration}")
    private Long expiration;
    private Boolean singleLogin = false;
    private final SecurityProperties properties;
    private final RedisUtils redisUtils;
    private final UserDetailsService userDetailsService;
    private final OnlineUserService onlineUserService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(SecurityProperties properties, RedisUtils redisUtils, UserDetailsService userDetailsService, OnlineUserService onlineUserService, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.properties = properties;
        this.redisUtils = redisUtils;
        this.userDetailsService = userDetailsService;
        this.onlineUserService = onlineUserService;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    /**
     * 登录授权
     */
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody(required = false) AuthUser authUser, HttpServletRequest request) {
        String username = authUser.getUsername();
        String password = authUser.getPassword();

        // 获取认证令牌并保存登录信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 根据令牌生成 Token
        String token = tokenProvider.createToken(authentication);
        final JwtUser jwtUser = (JwtUser) authentication.getPrincipal();

        // 保存在线信息
        onlineUserService.save(jwtUser, token, request);

        // 返回 Token 与 用户信息
        Map<String, Object> authInfo = new HashMap<>(3) {{
            put("token", properties.getTokenStartWith() + token);
            put("authority",jwtUser.getRoleByRoleId().getRoleName());
            put("status", 200);
        }};

        if (singleLogin) {
            // 如果之前已登录 踢掉之前已经登录的 Token
            onlineUserService.checkLoginOnUser(authUser.getUsername(), token);
        }

        // 设置登录状态为已登录
        singleLogin = true;

        return ResponseEntity.ok(authInfo);
    }

    /**
     * 获取用户信息
     */
    @GetMapping(value = "/info")
    @PreAuthorize("hasAnyAuthority('老师','学生')")
    public ResponseEntity<Object> getUserInfo() {
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        return ResponseEntity.ok(jwtUser);
    }

    /**
     * 获取验证码
     */
    @GetMapping(value = "/code")
    public ResponseEntity<Object> getCode() {
        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);

        // 几位数运算，默认是两位
        captcha.setLen(2);

        // 获取运算的结果
        String result = captcha.text();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();

        // 保存
        redisUtils.set(uuid, result, expiration, TimeUnit.MINUTES);

        // 返回验证码信息
        Map<String, Object> imgResult = new HashMap<>(3) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
            put("status", 200);
        }};
        return ResponseEntity.ok(imgResult);
    }

    /**
     * 退出登录
     */
    @DeleteMapping(value = "/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        // 退出登录
        onlineUserService.logout(tokenProvider.getToken(request));

        // 改变登录状态
        singleLogin = false;

        Map<String, Object> result = new HashMap<>(1) {{
            put("status", 204);
        }};
        return ResponseEntity.ok(result);
    }
}
