package cn.dormirr.coremodule.team.service.dto;

import cn.dormirr.coremodule.role.domain.Role;
import cn.dormirr.coremodule.role.domain.User;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author ZhangTianCi
 */
@Data
public class TeamDto implements Serializable {
    private Long id;
    private Timestamp createTime;
    private String teamName;
    private Integer totalTeamFightingPower;
    private String teamState;
    private String teamProfile;
    private User userByUserId;
    private Role roleByRoleId;
}
