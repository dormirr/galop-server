package cn.dormirr.coremodule.announcement.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author ZhangTianCi
 */
@Entity
@Table(name = "announcement", schema = "galop", catalog = "galop")
public class AnnouncementEntity implements Serializable {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String announcementTitle;
    private String announcementContent;

    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "create_time", nullable = false, insertable = false, updatable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false, insertable = false, updatable = false)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "announcement_title", nullable = false, length = -1)
    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    @Basic
    @Column(name = "announcement_content", nullable = false, length = -1)
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

        AnnouncementEntity that = (AnnouncementEntity) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) {
            return false;
        }
        if (getCreateTime() != null ? !getCreateTime().equals(that.getCreateTime()) : that.getCreateTime() != null) {
            return false;
        }
        if (getUpdateTime() != null ? !getUpdateTime().equals(that.getUpdateTime()) : that.getUpdateTime() != null) {
            return false;
        }
        if (getAnnouncementTitle() != null ? !getAnnouncementTitle().equals(that.getAnnouncementTitle()) : that.getAnnouncementTitle() != null) {
            return false;
        }
        return getAnnouncementContent() != null ? getAnnouncementContent().equals(that.getAnnouncementContent()) : that.getAnnouncementContent() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCreateTime() != null ? getCreateTime().hashCode() : 0);
        result = 31 * result + (getUpdateTime() != null ? getUpdateTime().hashCode() : 0);
        result = 31 * result + (getAnnouncementTitle() != null ? getAnnouncementTitle().hashCode() : 0);
        result = 31 * result + (getAnnouncementContent() != null ? getAnnouncementContent().hashCode() : 0);
        return result;
    }
}
