package cn.dormirr.coremodule.announcement.rest;

import cn.dormirr.commonmodule.utils.PageUtils;
import cn.dormirr.coremodule.announcement.domain.vo.ApplyAnnouncement;
import cn.dormirr.coremodule.announcement.domain.vo.FindAnnouncement;
import cn.dormirr.coremodule.announcement.domain.vo.SaveAnnouncement;
import cn.dormirr.coremodule.announcement.service.AnnouncementService;
import cn.dormirr.coremodule.announcement.service.dto.AnnouncementDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangTianCi
 */
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping("/save-announcement")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> saveAnnouncement(@RequestBody SaveAnnouncement saveAnnouncement) {
        if (saveAnnouncement.getAnnouncementTitle() == null || saveAnnouncement.getAnnouncementContent() == null) {
            Map<String, Object> result = new HashMap<>(3) {
                {
                    put("code", 400);
                    put("success", false);
                    put("message", "标题或内容为空！");
                }
            };
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        AnnouncementDto announcementDto = new AnnouncementDto();
        announcementDto.setAnnouncementTitle(saveAnnouncement.getAnnouncementTitle());
        announcementDto.setAnnouncementContent(saveAnnouncement.getAnnouncementContent());

        announcementService.saveAnnouncement(announcementDto);

        Map<String, Object> result = new HashMap<>(3) {
            {
                put("code", 201);
                put("success", true);
                put("message", "公告创建成功！");
            }
        };
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/find-announcement")
    @PreAuthorize("hasAnyAuthority('老师','学生')")
    public ResponseEntity<Object> findAnnouncement(FindAnnouncement findAnnouncement) {
        AnnouncementDto announcementDto = new AnnouncementDto();
        if (findAnnouncement.getAnnouncementTitle() != null) {
            announcementDto.setAnnouncementTitle(findAnnouncement.getAnnouncementTitle());
        }
        int pageSize = findAnnouncement.getPageSize();
        int current = findAnnouncement.getCurrent();
        String sorter = null;
        if (!"".equals(findAnnouncement.getSorter())) {
            sorter = findAnnouncement.getSorter();
        }

        PageUtils<AnnouncementDto> data = announcementService.findAnnouncement(announcementDto, pageSize, current, sorter);

        // 返回成功信息
        Map<String, Object> result = new HashMap<>(5) {{
            put("status", 200);
            put("data", data.getContent());
            put("total", data.getTotalElements());
            put("current", data.getTotalPages());
            put("pageSize", pageSize);
        }};
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete-announcement")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> deleteAnnouncement(@RequestBody ApplyAnnouncement applyAnnouncement) {
        AnnouncementDto announcementDto = new AnnouncementDto();
        announcementDto.setId(applyAnnouncement.getId());

        announcementService.deleteAnnouncement(announcementDto);

        // 返回成功信息
        Map<String, Object> result = new HashMap<>(3) {{
            put("code", 201);
            put("success", true);
            put("message", "删除成功！");
        }};
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/save-apply-announcement")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> saveApplyAnnouncement(@RequestBody ApplyAnnouncement applyAnnouncement) {
        AnnouncementDto announcementDto = new AnnouncementDto();
        announcementDto.setId(applyAnnouncement.getId());
        announcementDto.setAnnouncementTitle(applyAnnouncement.getAnnouncementTitle());
        announcementDto.setAnnouncementContent(applyAnnouncement.getAnnouncementContent());

        announcementService.saveApplyAnnouncement(announcementDto);

        // 返回成功信息
        Map<String, Object> result = new HashMap<>(3) {{
            put("code", 201);
            put("success", true);
            put("message", "修改成功！");
        }};
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/find-one-announcement")
    @PreAuthorize("hasAnyAuthority('老师','学生')")
    public ResponseEntity<Object> findOneAnnouncement(ApplyAnnouncement applyAnnouncement) {
        AnnouncementDto announcementDto = new AnnouncementDto();
        announcementDto.setId(applyAnnouncement.getId());

        AnnouncementDto oneAnnouncementDto = announcementService.findOneAnnouncement(announcementDto);

        if (oneAnnouncementDto == null) {
            Map<String, Object> result = new HashMap<>(3) {
                {
                    put("code", 400);
                    put("success", false);
                    put("message", "没有这条公告！");
                }
            };
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> result = new HashMap<>(4) {{
            put("code", 200);
            put("success", true);
            put("announcementDto", oneAnnouncementDto);
            put("message", "查询成功！");
        }};
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/find-ten-announcement")
    @PreAuthorize("hasAnyAuthority('老师','学生')")
    public ResponseEntity<Object> findTenAnnouncement() {
        List<AnnouncementDto> announcementDto = announcementService.findTenAnnouncement();

        Map<String, Object> result = new HashMap<>(4) {{
            put("code", 200);
            put("success", true);
            put("announcementDto", announcementDto);
            put("message", "查询成功！");
        }};

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
