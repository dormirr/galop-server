package cn.dormirr.coremodule.announcement.domain.vo;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class ApplyAnnouncement implements Serializable {
    private Long id;
    private String announcementTitle;
    private String announcementContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }
}
