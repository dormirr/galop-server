package cn.dormirr.coremodule.registration.rest;

import cn.dormirr.coremodule.registration.domain.vo.ApplyRegistrationInfo;
import cn.dormirr.coremodule.registration.domain.vo.DownloadRegistrationInfo;
import cn.dormirr.coremodule.registration.domain.vo.FindRegistrationInfo;
import cn.dormirr.coremodule.registration.domain.vo.SaveRegistrationInfo;
import cn.dormirr.coremodule.registration.service.RegistrationInfoService;
import cn.dormirr.coremodule.registration.service.dto.RegistrationInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangTianCi
 */
@Slf4j
@RestController
@RequestMapping("/registration-info")
public class RegistrationInfoController {
    private final RegistrationInfoService registrationInfoService;

    public RegistrationInfoController(RegistrationInfoService registrationInfoService) {
        this.registrationInfoService = registrationInfoService;
    }

    @PostMapping("/save-registration-info")
    @PreAuthorize("hasAnyAuthority('学生')")
    public ResponseEntity<Object> saveRegistrationInfo(@RequestBody SaveRegistrationInfo saveRegistrationInfo) {
        RegistrationInfoDto registrationInfoDto = new RegistrationInfoDto();

        boolean success = registrationInfoService.saveRegistrationInfo(registrationInfoDto, saveRegistrationInfo.getMatchId(), saveRegistrationInfo.getTeamId());

        if (success) {
            Map<String, Object> result = new HashMap<>(3) {
                {
                    put("code", 201);
                    put("success", true);
                    put("message", "成功发送申请！");
                }
            };
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        Map<String, Object> result = new HashMap<>(3) {
            {
                put("code", 400);
                put("success", false);
                put("message", "你已发送过申请或你已报名或你不是队长！");
            }
        };
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/find-registration-info")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> findRegistrationInfo(FindRegistrationInfo findRegistrationInfo) {
        RegistrationInfoDto registrationInfoDto = new RegistrationInfoDto();
        if (findRegistrationInfo.getRegistrationStatus() != null) {
            registrationInfoDto.setRegistrationStatus(findRegistrationInfo.getRegistrationStatus());
        }
        if (findRegistrationInfo.getMatchInfoByMatchInfoId() != null) {
            if (findRegistrationInfo.getMatchInfoByMatchInfoId().getId() != null) {
                registrationInfoDto.getMatchInfoByMatchInfoId().setId(findRegistrationInfo.getMatchInfoByMatchInfoId().getId());
            }
            if (findRegistrationInfo.getMatchInfoByMatchInfoId().getMatchName() != null) {
                registrationInfoDto.getMatchInfoByMatchInfoId().setMatchName(findRegistrationInfo.getMatchInfoByMatchInfoId().getMatchName());
            }
        }
        if (findRegistrationInfo.getTeamByTeamId() != null) {
            if (findRegistrationInfo.getTeamByTeamId().getId() != null) {
                registrationInfoDto.getTeamByTeamId().setId(findRegistrationInfo.getTeamByTeamId().getId());
            }
            if (findRegistrationInfo.getTeamByTeamId().getTeamName() != null) {
                registrationInfoDto.getTeamByTeamId().setTeamName(findRegistrationInfo.getTeamByTeamId().getTeamName());
            }
        }
        int pageSize = findRegistrationInfo.getPageSize();
        int current = findRegistrationInfo.getCurrent();
        String sorter = null;
        if (!"".equals(findRegistrationInfo.getSorter())) {
            sorter = findRegistrationInfo.getSorter();
        }

        Page<RegistrationInfoDto> data = registrationInfoService.findRegistrationInfo(registrationInfoDto, pageSize, current, sorter);

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

    @PutMapping("/save-registration-info")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> saveApplyRegistrationInfo(@RequestBody ApplyRegistrationInfo applyRegistrationInfo) {
        boolean success = registrationInfoService.saveApplyRegistrationInfo(applyRegistrationInfo.getId());
        if (success) {
            Map<String, Object> result = new HashMap<>(3) {
                {
                    put("code", 201);
                    put("success", true);
                    put("message", "已通过！");
                }
            };
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            Map<String, Object> result = new HashMap<>(3) {
                {
                    put("code", 400);
                    put("success", false);
                    put("message", "此申请已通过或已拒绝！");
                }
            };
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/delete-registration-info")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> deleteApplyRegistrationInfo(@RequestBody ApplyRegistrationInfo applyRegistrationInfo) {
        boolean success = registrationInfoService.deleteApplyRegistrationInfo(applyRegistrationInfo.getId());
        if (success) {
            Map<String, Object> result = new HashMap<>(3) {
                {
                    put("code", 201);
                    put("success", true);
                    put("message", "已拒绝！");
                }
            };
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            Map<String, Object> result = new HashMap<>(3) {
                {
                    put("code", 400);
                    put("success", false);
                    put("message", "此申请已通过或已拒绝！！");
                }
            };
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/download-registration-info")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> downloadRegistrationInfo(@RequestBody DownloadRegistrationInfo downloadRegistrationInfo) {
        String fileName = registrationInfoService.downloadRegistrationInfo(downloadRegistrationInfo.getMatchId());
        if (fileName != null) {
            Map<String, Object> result = new HashMap<>(3) {
                {
                    put("code", 201);
                    put("success", true);
                    put("message", "成功导出报名表！");
                    put("download", fileName);
                }
            };
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            Map<String, Object> result = new HashMap<>(3) {
                {
                    put("code", 400);
                    put("success", false);
                    put("message", "暂无审核通过的人或比赛不存在！");
                }
            };
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
