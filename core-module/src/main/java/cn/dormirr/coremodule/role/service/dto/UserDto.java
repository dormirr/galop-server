package cn.dormirr.coremodule.role.service.dto;

import cn.dormirr.coremodule.role.domain.Role;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
@Data
public class UserDto implements Serializable {
    private Long id;
    private String userNumber;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPortrait;
    private Integer userFightingCapacity;
    private Role roleByRoleId;
}
