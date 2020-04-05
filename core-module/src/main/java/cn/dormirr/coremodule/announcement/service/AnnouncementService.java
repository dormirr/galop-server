package cn.dormirr.coremodule.announcement.service;

import cn.dormirr.coremodule.announcement.service.dto.AnnouncementDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ZhangTianCi
 */
public interface AnnouncementService {

    /**
     * 创建公告
     *
     * @param announcementDto 公告
     */
    void saveAnnouncement(AnnouncementDto announcementDto);


    /**
     * 动态查询公告
     *
     * @param announcementDto 查询条件
     * @param pageSize        每页数量
     * @param current         第几页
     * @param sorter          排序规则
     * @return 查询结果
     */
    Page<AnnouncementDto> findAnnouncement(AnnouncementDto announcementDto, int pageSize, int current, String sorter);

    /**
     * 删除公告
     *
     * @param announcementDto 待删除公告 ID
     */
    void deleteAnnouncement(AnnouncementDto announcementDto);

    /**
     * 修改公告
     *
     * @param announcementDto 待修改内容
     */
    void saveApplyAnnouncement(AnnouncementDto announcementDto);

    /**
     * 查询单条公告
     *
     * @param announcementDto 公告 ID
     * @return 查询结果
     */
    AnnouncementDto findOneAnnouncement(AnnouncementDto announcementDto);

    /**
     * 查询最新的十条公告
     *
     * @return 查询结果
     */
    List<AnnouncementDto> findTenAnnouncement();
}
