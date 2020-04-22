package cn.dormirr.coremodule.match.result.domain.vo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author ZhangTianCi
 */
public class FindMatchResult implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FindMatchResult that = (FindMatchResult) o;
        return Objects.equals(getPageSize(), that.getPageSize()) &&
                Objects.equals(getCurrent(), that.getCurrent()) &&
                Objects.equals(getSorter(), that.getSorter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPageSize(), getCurrent(), getSorter());
    }

    @Override
    public String toString() {
        return "FindMatchResult{" +
                "pageSize=" + pageSize +
                ", current=" + current +
                ", sorter='" + sorter + '\'' +
                '}';
    }
}
