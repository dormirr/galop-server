package cn.dormirr.coremodule.security.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ZhangTianCi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUser {
    private String userNumber;
    private String userName;
    private String userRole;
    private String key;
    private Date loginTime;
}
