package cn.dormirr.coremodule.role.domain.vo;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class UserPassword implements Serializable {
    private String userPassword;

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}


