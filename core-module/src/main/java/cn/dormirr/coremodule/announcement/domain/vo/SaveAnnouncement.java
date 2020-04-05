package cn.dormirr.coremodule.announcement.domain.vo;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class SaveAnnouncement implements Serializable {
    private String announcementTitle;
    private String announcementContent;

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

    @Override
    public String toString() {
        return "SaveAnnouncement{" +
                "announcementTitle='" + announcementTitle + '\'' +
                ", announcementContent='" + announcementContent + '\'' +
                '}';
    }
}
