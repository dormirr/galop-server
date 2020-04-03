package cn.dormirr.coremodule.team.domain.vo;

import cn.dormirr.coremodule.role.domain.RoleEntity;
import cn.dormirr.coremodule.role.domain.UserEntity;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class FindApplyTeam implements Serializable {
    private Long id;
    private String teamName;
    private String teamState;
    private Long teamId;
    private UserEntity userByUserId;
    private Integer pageSize;
    private Integer current;
    private String sorter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamState() {
        return teamState;
    }

    public void setTeamState(String teamState) {
        this.teamState = teamState;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public String getSorter() {
        return sorter;
    }

    public void setSorter(String sorter) {
        this.sorter = sorter;
    }
}
