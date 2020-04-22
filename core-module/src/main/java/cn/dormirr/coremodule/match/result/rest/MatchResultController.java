package cn.dormirr.coremodule.match.result.rest;

import cn.dormirr.commonmodule.utils.PageUtils;
import cn.dormirr.coremodule.match.result.domain.vo.FindMatchResult;
import cn.dormirr.coremodule.match.result.service.MatchResultService;
import cn.dormirr.coremodule.match.result.service.dto.MatchResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author ZhangTianCi
 */
@Slf4j
@RestController
@RequestMapping("/match-result")
public class MatchResultController {
    private final MatchResultService matchResultService;

    public MatchResultController(MatchResultService matchResultService) {
        this.matchResultService = matchResultService;
    }

    @PostMapping("/save-match-result")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> saveMatchResult(@RequestParam("file") MultipartFile file) throws ExecutionException, InterruptedException {
        int count = matchResultService.saveMatchResult(file).get();

        if (count < 0) {
            Map<String, Object> result = new HashMap<>(3) {
                {
                    put("code", 400);
                    put("success", false);
                    put("message", "Excel 中无录入比赛结果或格式不正确！");
                }
            };
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> result = new HashMap<>(4) {
            {
                put("code", 201);
                put("success", true);
                put("suc", count);
                put("message", "录入成功！");
            }
        };
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/find-match-result")
    @PreAuthorize("hasAnyAuthority('学生')")
    public ResponseEntity<Object> findMatchResult(FindMatchResult findMatchResult) {
        int pageSize = findMatchResult.getPageSize();
        int current = findMatchResult.getCurrent();
        String sorter = null;
        if (!"".equals(findMatchResult.getSorter())) {
            sorter = findMatchResult.getSorter();
        }

        PageUtils<MatchResultDto> data = matchResultService.findMatchResult(pageSize, current, sorter);

        // 返回成功信息
        Map<String, Object> status = new HashMap<>(5) {{
            put("status", 200);
            put("data", data.getContent());
            put("total", data.getTotalElements());
            put("current", data.getTotalPages());
            put("pageSize", pageSize);
        }};
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
