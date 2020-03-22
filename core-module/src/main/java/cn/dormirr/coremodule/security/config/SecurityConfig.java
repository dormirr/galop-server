package cn.dormirr.coremodule.security.config;

import cn.dormirr.coremodule.security.security.JwtAccessDeniedHandler;
import cn.dormirr.coremodule.security.security.JwtAuthenticationEntryPoint;
import cn.dormirr.coremodule.security.security.TokenConfigurer;
import cn.dormirr.coremodule.security.security.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * @author ZhangTianCi
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint authenticationErrorHandler;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(TokenProvider tokenProvider, CorsFilter corsFilter, JwtAuthenticationEntryPoint authenticationErrorHandler, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.authenticationErrorHandler = authenticationErrorHandler;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 密码加密方式
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 禁用 CSRF
                .csrf()
                .disable()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                // 授权异常
                .exceptionHandling()
                // 身份验证错误处理
                .authenticationEntryPoint(authenticationErrorHandler)
                // 访问被拒绝处理
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 防止 iframe 造成跨域
                .and()
                .headers()
                .frameOptions()
                .disable()

                // 不创建 session 会话
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 访问控制
                .and()
                .authorizeRequests()

                // 放行
                .antMatchers(
                        HttpMethod.GET,
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                // Druid
                .antMatchers("/druid/**").permitAll()
                // 授权
                .antMatchers("/auth/login").permitAll()
                // 退出
                .antMatchers("/auth/logout").permitAll()
                // 头像
                .antMatchers("/avatar/**").permitAll()
                // 模板
                .antMatchers("/file/**").permitAll()

                // 其他请求都需要认证
                .anyRequest()
                .authenticated()
                .and().apply(securityConfigurerAdapter());
    }

    private TokenConfigurer securityConfigurerAdapter() {
        return new TokenConfigurer(tokenProvider);
    }
}
