package cn.dormirr.coremodule.match.info.rest;

import cn.dormirr.coremodule.match.info.domain.vo.FindMatchInfo;
import cn.dormirr.coremodule.match.info.domain.vo.SaveMatchInfo;
import cn.dormirr.coremodule.match.info.service.MatchInfoService;
import cn.dormirr.coremodule.match.info.service.dto.MatchInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
@Slf4j
@RestController
@RequestMapping("/match-info")
public class MatchInfoController {
    private final MatchInfoService matchInfoService;

    public MatchInfoController(MatchInfoService matchInfoService) {
        this.matchInfoService = matchInfoService;
    }

    @PostMapping("/save-match-info")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> saveMatchInfo(@RequestBody SaveMatchInfo saveMatchInfo) {
        MatchInfoDto matchInfoDto = new MatchInfoDto();
        matchInfoDto.setMatchType(saveMatchInfo.getMatchType());
        matchInfoDto.setMatchName(saveMatchInfo.getMatchName());
        matchInfoDto.setStartTime(saveMatchInfo.getDate()[0]);
        matchInfoDto.setEndTime(saveMatchInfo.getDate()[1]);
        matchInfoDto.setTeamSize(saveMatchInfo.getTeamSize());
        matchInfoDto.setChampionAward(saveMatchInfo.getChampionAward());
        matchInfoDto.setDecrementParameter(saveMatchInfo.getDecrementParameter());
        matchInfoService.saveMatchInfo(matchInfoDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/find-match-info")
    @PreAuthorize("hasAnyAuthority('老师','学生')")
    public ResponseEntity<Object> findMatchInfo(FindMatchInfo findMatchInfo) {
        MatchInfoDto matchInfoDto = new MatchInfoDto();
        if (findMatchInfo.getId() != null) {
            matchInfoDto.setId(findMatchInfo.getId());
        }
        if (findMatchInfo.getMatchName() != null) {
            matchInfoDto.setMatchName(findMatchInfo.getMatchName());
        }
        int pageSize = findMatchInfo.getPageSize();
        int current = findMatchInfo.getCurrent();
        String sorter = null;
        if (!"".equals(findMatchInfo.getSorter())) {
            sorter = findMatchInfo.getSorter();
        }

        Page<MatchInfoDto> data = matchInfoService.findMatchInfo(matchInfoDto, pageSize, current, sorter);

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


    @GetMapping("/ongoing-match-info")
    @PreAuthorize("hasAnyAuthority('老师','学生')")
    public ResponseEntity<Object> ongoingMatchInfo() {
        List<MatchInfoDto> data = matchInfoService.ongoingMatchInfo();

        // 返回成功信息
        Map<String, Object> result = new HashMap<>(4) {{
            put("code", 200);
            put("success", true);
            put("message", "查询成功！");
            put("data", data);
        }};
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
