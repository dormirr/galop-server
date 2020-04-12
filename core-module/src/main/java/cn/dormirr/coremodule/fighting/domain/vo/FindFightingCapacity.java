package cn.dormirr.coremodule.fighting.domain.vo;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class FindFightingCapacity implements Serializable {
    private Integer pageSize;
    private Integer current;
    private String sorter;

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
