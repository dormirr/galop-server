package cn.dormirr.coremodule.team.domain.vo;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class FindTeam implements Serializable {
    private Long id;
    private String teamName;
    private Integer teamFightingCapacity;
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

    public Integer getTeamFightingCapacity() {
        return teamFightingCapacity;
    }

    public void setTeamFightingCapacity(Integer teamFightingCapacity) {
        this.teamFightingCapacity = teamFightingCapacity;
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
