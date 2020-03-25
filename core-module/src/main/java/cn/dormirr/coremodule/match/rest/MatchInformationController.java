package cn.dormirr.coremodule.match.rest;

import cn.dormirr.commonmodule.utils.SecurityUtils;
import cn.dormirr.coremodule.match.service.MatchInformationService;
import cn.dormirr.coremodule.role.service.dto.RoleDto;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.team.domain.Team;
import cn.dormirr.coremodule.team.domain.vo.TeamNameAndProfile;
import cn.dormirr.coremodule.team.service.dto.TeamDto;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/matchInformation")
public class MatchInformationController {
    private final MatchInformationService matchInformationService;

    public MatchInformationController(MatchInformationService matchInformationService) {
        this.matchInformationService = matchInformationService;
    }

}
