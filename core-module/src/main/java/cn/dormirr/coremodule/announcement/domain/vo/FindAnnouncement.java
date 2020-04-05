package cn.dormirr.coremodule.announcement.domain.vo;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class FindAnnouncement implements Serializable {
    private String announcementTitle;
    private Integer pageSize;
    private Integer current;
    private String sorter;

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
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
