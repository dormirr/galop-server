package cn.dormirr.coremodule.announcement.service.dto;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ZhangTianCi
 */
public class AnnouncementDto {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String announcementTitle;
    private String announcementContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AnnouncementDto that = (AnnouncementDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCreateTime(), that.getCreateTime()) &&
                Objects.equals(getUpdateTime(), that.getUpdateTime()) &&
                Objects.equals(getAnnouncementTitle(), that.getAnnouncementTitle()) &&
                Objects.equals(getAnnouncementContent(), that.getAnnouncementContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreateTime(), getUpdateTime(), getAnnouncementTitle(), getAnnouncementContent());
    }

    @Override
    public String toString() {
        return "AnnouncementDto{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", announcementTitle='" + announcementTitle + '\'' +
                ", announcementContent='" + announcementContent + '\'' +
                '}';
    }
}
