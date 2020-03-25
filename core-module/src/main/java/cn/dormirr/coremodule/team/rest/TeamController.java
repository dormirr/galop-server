package cn.dormirr.coremodule.team.rest;

import cn.dormirr.commonmodule.utils.SecurityUtils;
import cn.dormirr.coremodule.role.service.RoleService;
import cn.dormirr.coremodule.role.service.UserService;
import cn.dormirr.coremodule.role.service.dto.RoleDto;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.role.service.mapper.RoleMapper;
import cn.dormirr.coremodule.role.service.mapper.UserMapper;
import cn.dormirr.coremodule.team.domain.Team;
import cn.dormirr.coremodule.team.domain.vo.TeamNameAndProfile;
import cn.dormirr.coremodule.team.service.TeamService;
import cn.dormirr.coremodule.team.service.dto.TeamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
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
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final RoleService roleService;

    public TeamController(TeamService teamService, UserService userService, UserMapper userMapper, RoleMapper roleMapper, RoleService roleService) {
        this.teamService = teamService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.roleService = roleService;
    }

    @PostMapping("/addTeam")
    @PreAuthorize("hasAnyAuthority('老师','学生','队长')")
    public ResponseEntity<Object> addTeam(@RequestBody TeamNameAndProfile teamNameAndProfile) {
        UserDto userDto = userService.findByUserNumber(SecurityUtils.getUsername());
        RoleDto roleDto = roleService.findByRoleName("队长");
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamName(teamNameAndProfile.getTeamName());
        if (teamNameAndProfile.getTeamProfile() != null) {
            teamDto.setTeamProfile(teamNameAndProfile.getTeamProfile());
        }
        teamDto.setTotalTeamFightingPower(userDto.getUserFightingCapacity());
        teamDto.setRoleByRoleId(roleMapper.toEntity(roleDto));
        teamDto.setUserByUserId(userMapper.toEntity(userDto));
        teamDto.setTeamState("通过");
        teamService.saveTeam(teamDto);
        userDto.setRoleByRoleId(roleMapper.toEntity(roleDto));
        userService.saveUserRole(userDto);

        // 返回成功信息
        Map<String, Object> status = new HashMap<>(1) {{
            put("status", 201);
        }};
        return ResponseEntity.ok(status);
    }

    @GetMapping("/selectTeam")
    @PreAuthorize("hasAnyAuthority('老师','学生','队长')")
    public ResponseEntity<Object> selectTeam() {
        UserDto userDto = userService.findByUserNumber(SecurityUtils.getUsername());
        List<Team> list = teamService.findByUser(userDto).getContent();

        // 返回成功信息
        Map<String, Object> status = new HashMap<>(2) {{
            put("status", 200);
            put("list", list);
        }};
        return ResponseEntity.ok(status);
    }

    @GetMapping("/selectAllTeam")
    @PreAuthorize("hasAnyAuthority('老师','学生','队长')")
    public ResponseEntity<Object> selectAllTeam(TeamDto teamDto) {
        RoleDto roleDto = roleService.findByRoleName("队长");
        List<Team> data = teamService.findAllByRole(roleDto, teamDto);

        // 返回成功信息
        Map<String, Object> status = new HashMap<>(5) {{
            put("status", 200);
            put("data", data);
        }};
        return ResponseEntity.ok(status);
    }

    @PostMapping("/addUserTeam")
    @PreAuthorize("hasAnyAuthority('老师','学生','队长')")
    public ResponseEntity<Object> addUserTeam(Long id) {
        UserDto userDto = userService.findByUserNumber(SecurityUtils.getUsername());
        TeamDto teamDto = teamService.findById(id);
        RoleDto roleDto = roleService.findByRoleName("学生");
        teamDto.setUserByUserId(userMapper.toEntity(userDto));
        teamDto.setRoleByRoleId(roleMapper.toEntity(roleDto));
        teamDto.setTeamState("审核");
        teamService.saveAddUserTeam(teamDto);

        // 返回成功信息
        Map<String, Object> status = new HashMap<>(5) {{
            put("status", 201);
        }};
        return ResponseEntity.ok(status);
    }
}
