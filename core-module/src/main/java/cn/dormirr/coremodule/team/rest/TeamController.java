package cn.dormirr.coremodule.team.rest;

import cn.dormirr.commonmodule.utils.PageUtils;
import cn.dormirr.commonmodule.utils.SecurityUtils;
import cn.dormirr.coremodule.role.service.UserService;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.team.domain.vo.*;
import cn.dormirr.coremodule.team.service.TeamService;
import cn.dormirr.coremodule.team.service.dto.TeamDto;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;
    private final UserService userService;

    public TeamController(TeamService teamService, UserService userService) {
        this.teamService = teamService;
        this.userService = userService;
    }

    @PostMapping("/save-team")
    @PreAuthorize("hasAnyAuthority('学生')")
    public ResponseEntity<Object> saveTeam(@RequestBody SaveTeam saveTeam) {
        UserDto userDto = userService.findByUserNumber(SecurityUtils.getUsername());
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamName(saveTeam.getTeamName());
        if (saveTeam.getTeamProfile() != null) {
            teamDto.setTeamProfile(saveTeam.getTeamProfile());
        }

        teamService.saveTeam(teamDto, userDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/find-team")
    @PreAuthorize("hasAnyAuthority('学生')")
    public ResponseEntity<Object> findTeam(FindTeam findTeam) {
        TeamDto teamDto = new TeamDto();
        if (findTeam.getId() != null) {
            teamDto.setId(findTeam.getId());
        }
        if (findTeam.getTeamName() != null) {
            teamDto.setTeamName(findTeam.getTeamName());
        }
        if (findTeam.getTeamFightingCapacity() != null) {
            teamDto.setTeamFightingCapacity(findTeam.getTeamFightingCapacity());
        }
        int pageSize = findTeam.getPageSize();
        int current = findTeam.getCurrent();
        String sorter = null;
        if (!"".equals(findTeam.getSorter())) {
            sorter = findTeam.getSorter();
        }

        PageUtils<TeamDto> data = teamService.findTeam(teamDto, pageSize, current, sorter);

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

    @PostMapping("/apply-team")
    @PreAuthorize("hasAnyAuthority('学生')")
    public ResponseEntity<Object> applyTeam(@RequestBody ApplyTeam applyTeam) {
        boolean success = teamService.applyTeam(applyTeam.getId());
        if (success) {
            Map<String, Object> result = new HashMap<>(3) {
                {
                    put("code", 201);
                    put("success", true);
                    put("message", "成功发送申请！");
                }
            };
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            Map<String, Object> result = new HashMap<>(3) {
                {
                    put("code", 400);
                    put("success", false);
                    put("message", "你已发送过申请或你已在队伍中！");
                }
            };
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find-apply-team")
    @PreAuthorize("hasAnyAuthority('学生')")
    public ResponseEntity<Object> findApplyTeam(FindApplyTeam findApplyTeam) {
        TeamDto teamDto = new TeamDto();
        if (findApplyTeam.getTeamName() != null) {
            teamDto.setTeamName(findApplyTeam.getTeamName());
        }
        if (findApplyTeam.getTeamState() != null) {
            teamDto.setTeamState(findApplyTeam.getTeamState());
        }
        if (findApplyTeam.getTeamId() != null) {
            teamDto.setTeamId(findApplyTeam.getTeamId());
        }

        int pageSize = findApplyTeam.getPageSize();
        int current = findApplyTeam.getCurrent();
        String sorter = null;
        if (!"".equals(findApplyTeam.getSorter())) {
            sorter = findApplyTeam.getSorter();
        }

        PageUtils<TeamDto> data = teamService.findApplyTeam(teamDto, pageSize, current, sorter);

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

    @PutMapping("/save-apply-team")
    @PreAuthorize("hasAnyAuthority('学生')")
    public ResponseEntity<Object> saveApplyTeam(@RequestBody ApplyTeam applyTeam) {
        boolean success = teamService.saveApplyTeam(applyTeam.getId());
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

    @PutMapping("/delete-apply-team")
    @PreAuthorize("hasAnyAuthority('学生')")
    public ResponseEntity<Object> deleteApplyTeam(@RequestBody ApplyTeam applyTeam) {
        boolean success = teamService.deleteApplyTeam(applyTeam.getId());
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

    @GetMapping("/find-my-team")
    @PreAuthorize("hasAnyAuthority('学生')")
    public ResponseEntity<Object> findMyTeam(FindTeam findTeam) {
        TeamDto teamDto = new TeamDto();
        if (findTeam.getId() != null) {
            teamDto.setId(findTeam.getId());
        }
        if (findTeam.getTeamName() != null) {
            teamDto.setTeamName(findTeam.getTeamName());
        }
        if (findTeam.getTeamFightingCapacity() != null) {
            teamDto.setTeamFightingCapacity(findTeam.getTeamFightingCapacity());
        }
        int pageSize = findTeam.getPageSize();
        int current = findTeam.getCurrent();
        String sorter = null;
        if (!"".equals(findTeam.getSorter())) {
            sorter = findTeam.getSorter();
        }

        PageUtils<TeamDto> data = teamService.findMyTeam(teamDto, pageSize, current, sorter);

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

    @GetMapping("/find-one-team")
    @PreAuthorize("hasAnyAuthority('学生')")
    public ResponseEntity<Object> findOneTeam(FindOneTeam findOneTeam) {
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamId(findOneTeam.getTeamId());
        int pageSize = findOneTeam.getPageSize();
        int current = findOneTeam.getCurrent();
        String sorter = null;
        if (!"".equals(findOneTeam.getSorter())) {
            sorter = findOneTeam.getSorter();
        }

        PageUtils<TeamDto> data = teamService.findOneTeam(teamDto, pageSize, current, sorter);

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

    @PutMapping("/save-my-team")
    @PreAuthorize("hasAnyAuthority('学生')")
    public ResponseEntity<Object> saveMyTeam(@RequestBody SaveMyTeam saveMyTeam) {
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamId(saveMyTeam.getTeamId());
        if (saveMyTeam.getTeamName() != null) {
            teamDto.setTeamName(saveMyTeam.getTeamName());
        }
        if (saveMyTeam.getTeamProfile() != null) {
            teamDto.setTeamProfile(saveMyTeam.getTeamProfile());
        }

        teamService.saveMyTeam(teamDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
