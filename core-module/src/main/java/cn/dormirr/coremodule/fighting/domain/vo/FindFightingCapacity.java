package cn.dormirr.coremodule.fighting.domain.vo;

import cn.dormirr.coremodule.match.info.domain.MatchInfoEntity;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class FindFightingCapacity implements Serializable {
    private MatchInfoEntity matchInfoByMatchInfoId;
    private Integer pageSize;
    private Integer current;
    private String sorter;

    public MatchInfoEntity getMatchInfoByMatchInfoId() {
        return matchInfoByMatchInfoId;
    }

    public void setMatchInfoByMatchInfoId(MatchInfoEntity matchInfoByMatchInfoId) {
        this.matchInfoByMatchInfoId = matchInfoByMatchInfoId;
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
