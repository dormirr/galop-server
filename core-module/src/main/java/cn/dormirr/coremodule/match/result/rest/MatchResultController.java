package cn.dormirr.coremodule.match.result.rest;

import cn.dormirr.coremodule.match.result.service.MatchResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
