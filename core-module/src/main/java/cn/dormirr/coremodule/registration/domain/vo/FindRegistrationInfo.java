package cn.dormirr.coremodule.registration.domain.vo;

import cn.dormirr.coremodule.match.info.domain.MatchInfoEntity;
import cn.dormirr.coremodule.team.domain.TeamEntity;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class FindRegistrationInfo implements Serializable {
    private String registrationStatus;
    private Integer pageSize;
    private Integer current;
    private String sorter;

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
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
