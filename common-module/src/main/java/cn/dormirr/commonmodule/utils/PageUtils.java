package cn.dormirr.commonmodule.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZhangTianCi
 */
public class PageUtils<T> implements Serializable {
    private List<T> content;
    private long totalElements;
    private long totalPages;

    public PageUtils() {
    }

    public PageUtils(List<T> content, long totalElements, long totalPages) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }
}
