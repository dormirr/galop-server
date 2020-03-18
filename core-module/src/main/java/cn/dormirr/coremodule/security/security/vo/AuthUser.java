package cn.dormirr.coremodule.security.security.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhangTianCi
 */
@Getter
@Setter
public class AuthUser {

    private String username;
    private String password;

    @Override
    public String toString() {
        return "AuthUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
