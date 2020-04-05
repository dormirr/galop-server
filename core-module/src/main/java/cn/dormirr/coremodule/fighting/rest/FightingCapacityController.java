package cn.dormirr.coremodule.fighting.rest;

import cn.dormirr.coremodule.fighting.domain.vo.ChangeFightingCapacityRe;
import cn.dormirr.coremodule.fighting.domain.vo.ChangeMatchRe;
import cn.dormirr.coremodule.fighting.domain.vo.FindFightingCapacity;
import cn.dormirr.coremodule.fighting.service.FightingCapacityService;
import cn.dormirr.coremodule.fighting.service.dto.FightingCapacityDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangTianCi
 */
@RestController
@RequestMapping("/fighting-capacity")
public class FightingCapacityController {
    private final FightingCapacityService fightingCapacityService;

    public FightingCapacityController(FightingCapacityService fightingCapacityService) {
        this.fightingCapacityService = fightingCapacityService;
    }

    @GetMapping("/find-fighting-capacity")
    @PreAuthorize("hasAnyAuthority('学生')")
    public ResponseEntity<Object> findFightingCapacity(FindFightingCapacity findFightingCapacity) {
        FightingCapacityDto fightingCapacityDto = new FightingCapacityDto();

        if (findFightingCapacity.getMatchInfoByMatchInfoId() != null) {
            if (findFightingCapacity.getMatchInfoByMatchInfoId().getId() != null) {
                fightingCapacityDto.getMatchInfoByMatchInfoId().setId(findFightingCapacity.getMatchInfoByMatchInfoId().getId());
            }
            if (findFightingCapacity.getMatchInfoByMatchInfoId().getMatchName() != null) {
                fightingCapacityDto.getMatchInfoByMatchInfoId().setMatchName(findFightingCapacity.getMatchInfoByMatchInfoId().getMatchName());
            }
        }
        int pageSize = findFightingCapacity.getPageSize();
        int current = findFightingCapacity.getCurrent();
        String sorter = null;
        if (!"".equals(findFightingCapacity.getSorter())) {
            sorter = findFightingCapacity.getSorter();
        }

        Page<FightingCapacityDto> data = fightingCapacityService.findFightingCapacity(fightingCapacityDto, pageSize, current, sorter);

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

    @GetMapping("/change-fighting-capacity")
    @PreAuthorize("hasAnyAuthority('学生')")
    public ResponseEntity<Object> changeFightingCapacity() {
        List<FightingCapacityDto> data = fightingCapacityService.changeFightingCapacity();
        List<ChangeFightingCapacityRe> list = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd");
        if (data != null) {
            long sum = 0;
            for (FightingCapacityDto fightingCapacityDto : data) {
                sum += fightingCapacityDto.getReward();
                list.add(new ChangeFightingCapacityRe(
                        dateFormat.format(fightingCapacityDto.getCreateTime()), sum
                ));
            }
        }

        // 返回成功信息
        Map<String, Object> result = new HashMap<>(4) {{
            put("code", 200);
            put("success", true);
            put("message", "查询成功！");
            put("list", list);
        }};
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/change-match")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> changeMatch() {
        List<ChangeMatchRe> list = fightingCapacityService.changeMatch();

        // 返回成功信息
        Map<String, Object> result = new HashMap<>(4) {{
            put("code", 200);
            put("success", true);
            put("message", "查询成功！");
            put("list", list);
        }};
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
