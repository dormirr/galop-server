package cn.dormirr.coremodule.role.domain.vo;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class UserNumber implements Serializable {
    String userNumber;

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }
}
