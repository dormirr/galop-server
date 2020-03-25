package cn.dormirr.coremodule.role.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
@Data
public class RoleDto implements Serializable {
    private Long id;
    private String roleName;
}
