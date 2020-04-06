package cn.dormirr.coremodule.registration.service.impl;

import cn.dormirr.commonmodule.utils.SecurityUtils;
import cn.dormirr.coremodule.match.info.service.MatchInfoService;
import cn.dormirr.coremodule.match.info.service.dto.MatchInfoDto;
import cn.dormirr.coremodule.match.info.service.mapper.MatchInfoMapper;
import cn.dormirr.coremodule.registration.domain.RegistrationInfoEntity;
import cn.dormirr.coremodule.registration.repository.RegistrationInfoRepository;
import cn.dormirr.coremodule.registration.service.RegistrationInfoService;
import cn.dormirr.coremodule.registration.service.dto.RegistrationInfoDto;
import cn.dormirr.coremodule.registration.service.mapper.RegistrationInfoMapper;
import cn.dormirr.coremodule.role.service.UserService;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.team.repository.TeamRepository;
import cn.dormirr.coremodule.team.service.TeamService;
import cn.dormirr.coremodule.team.service.dto.TeamDto;
import cn.dormirr.coremodule.team.service.mapper.TeamMapper;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationInfoServiceImpl implements RegistrationInfoService {
    private final UserService userService;
    private final MatchInfoService matchInfoService;
    private final TeamService teamService;
    private final RegistrationInfoRepository registrationInfoRepository;
    private final RegistrationInfoMapper registrationInfoMapper;
    private final MatchInfoMapper matchInfoMapper;
    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;

    public RegistrationInfoServiceImpl(UserService userService, MatchInfoService matchInfoService, TeamService teamService, RegistrationInfoRepository registrationInfoRepository, RegistrationInfoMapper registrationInfoMapper, MatchInfoMapper matchInfoMapper, TeamMapper teamMapper, TeamRepository teamRepository) {
        this.userService = userService;
        this.matchInfoService = matchInfoService;
        this.teamService = teamService;
        this.registrationInfoRepository = registrationInfoRepository;
        this.registrationInfoMapper = registrationInfoMapper;
        this.matchInfoMapper = matchInfoMapper;
        this.teamMapper = teamMapper;
        this.teamRepository = teamRepository;
    }

    /**
     * 报名比赛
     *
     * @param registrationInfoDto 报名比赛
     * @param matchId             比赛 ID
     * @param teamId              团队 ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRegistrationInfo(RegistrationInfoDto registrationInfoDto, Long matchId, Long teamId) {
        MatchInfoDto matchInfoDto = matchInfoService.findId(matchId);
        if (matchInfoDto == null) {
            return false;
        }

        TeamDto teamDto = teamService.findId(teamId);
        if (teamDto == null) {
            return false;
        }

        int teamSize = teamRepository.countAllByTeamId(teamId);
        if (teamSize != matchInfoDto.getTeamSize()) {
            return false;
        }

        UserDto userDto = userService.findByUserNumber(SecurityUtils.getUsername());
        if (!teamDto.getUserByUserId().getId().equals(userDto.getId())) {
            return false;
        }

        long adopt = registrationInfoRepository.countByMatchInfoByMatchInfoIdAndTeamByTeamIdAndRegistrationStatus(
                matchInfoMapper.toEntity(matchInfoDto),
                teamMapper.toEntity(teamDto),
                "通过"
        );
        long review = registrationInfoRepository.countByMatchInfoByMatchInfoIdAndTeamByTeamIdAndRegistrationStatus(
                matchInfoMapper.toEntity(matchInfoDto),
                teamMapper.toEntity(teamDto),
                "审核"
        );
        if (adopt > 0 || review > 0) {
            return false;
        }

        registrationInfoDto.setMatchInfoByMatchInfoId(matchInfoMapper.toEntity(matchInfoDto));
        registrationInfoDto.setTeamByTeamId(teamMapper.toEntity(teamDto));
        registrationInfoDto.setRegistrationStatus("审核");
        registrationInfoRepository.save(registrationInfoMapper.toEntity(registrationInfoDto));
        return true;
    }

    /**
     * 动态查询申请
     *
     * @param registrationInfoDto 查询条件
     * @param pageSize            每页数量
     * @param current             第几页
     * @param sorter              排序规则
     * @return 查询结果
     */
    @Override
    public Page<RegistrationInfoDto> findRegistrationInfo(RegistrationInfoDto registrationInfoDto, int pageSize, int current, String sorter) {
        String descend = "descend";
        String[] sort = sorter != null ? sorter.split("_") : new String[]{"id", ""};
        Pageable pageable = descend.equals(sort[1]) ?
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.DESC, sort[0])) :
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, sort[0]));

        Specification<RegistrationInfoEntity> specification = (Specification<RegistrationInfoEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> andQuery = new ArrayList<>();

            if (registrationInfoDto.getRegistrationStatus() != null) {
                Path<Long> registrationStatus = root.get("registrationStatus");
                Predicate registrationStatusEqual = criteriaBuilder.equal(registrationStatus, registrationInfoDto.getRegistrationStatus());
                andQuery.add(registrationStatusEqual);
            }

            if (registrationInfoDto.getMatchInfoByMatchInfoId() != null) {
                MatchInfoDto matchInfoDtoQuery = new MatchInfoDto();
                if (registrationInfoDto.getMatchInfoByMatchInfoId().getId() != null) {
                    matchInfoDtoQuery.setId(registrationInfoDto.getMatchInfoByMatchInfoId().getId());
                }
                if (registrationInfoDto.getMatchInfoByMatchInfoId().getMatchName() != null) {
                    matchInfoDtoQuery.setMatchName(registrationInfoDto.getMatchInfoByMatchInfoId().getMatchName());
                }
                Path<Long> matchInfoByMatchInfoId = root.get("matchInfoByMatchInfoId");
                Predicate matchInfoByMatchInfoIdEqual = criteriaBuilder.equal(matchInfoByMatchInfoId, matchInfoMapper.toEntity(matchInfoDtoQuery));
                andQuery.add(matchInfoByMatchInfoIdEqual);
            }

            if (registrationInfoDto.getTeamByTeamId() != null) {
                TeamDto teamDtoQuery = new TeamDto();
                if (registrationInfoDto.getTeamByTeamId().getId() != null) {
                    teamDtoQuery.setId(registrationInfoDto.getTeamByTeamId().getId());
                }
                if (registrationInfoDto.getTeamByTeamId().getTeamName() != null) {
                    teamDtoQuery.setTeamName(registrationInfoDto.getTeamByTeamId().getTeamName());
                }
                Path<Long> teamByTeamId = root.get("teamByTeamId");
                Predicate teamByTeamIdEqual = criteriaBuilder.equal(teamByTeamId, teamMapper.toEntity(teamDtoQuery));
                andQuery.add(teamByTeamIdEqual);
            }

            Predicate[] andPredicates = andQuery.toArray(new Predicate[0]);
            return criteriaBuilder.and(andPredicates);
        };

        Page<RegistrationInfoEntity> data = registrationInfoRepository.findAll(specification, pageable);

        List<RegistrationInfoDto> list = new ArrayList<>();
        for (RegistrationInfoEntity registrationInfoEntity : data.getContent()) {
            list.add(registrationInfoMapper.toDto(registrationInfoEntity));
        }
        return new PageImpl<>(list, data.getPageable(), data.getTotalElements());
    }

    /**
     * 同意申请
     *
     * @param id 申请处理 ID
     * @return 申请处理结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveApplyRegistrationInfo(Long id) {
        String registrationStatusWait = "审核";
        long countWait = registrationInfoRepository.countByIdAndRegistrationStatus(id, registrationStatusWait);
        if (countWait == 0) {
            return false;
        }
        RegistrationInfoDto registrationInfoDto = registrationInfoMapper.toDto(
                registrationInfoRepository.findById(id).isPresent() ?
                        registrationInfoRepository.findById(id).get() : null);
        registrationInfoDto.setRegistrationStatus("通过");
        registrationInfoRepository.save(registrationInfoMapper.toEntity(registrationInfoDto));
        return true;
    }

    /**
     * 拒绝申请
     *
     * @param id 申请处理 ID
     * @return 申请处理结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteApplyRegistrationInfo(Long id) {
        String registrationStatusWait = "审核";
        long countWait = registrationInfoRepository.countByIdAndRegistrationStatus(id, registrationStatusWait);
        if (countWait == 0) {
            return false;
        }
        RegistrationInfoDto registrationInfoDto = registrationInfoMapper.toDto(
                registrationInfoRepository.findById(id).isPresent() ?
                        registrationInfoRepository.findById(id).get() : null);
        registrationInfoDto.setRegistrationStatus("拒绝");
        registrationInfoRepository.save(registrationInfoMapper.toEntity(registrationInfoDto));
        return true;
    }

    /**
     * 根据比赛 ID 导出报名表
     *
     * @param matchId 比赛 ID
     * @return 文件地址
     */
    @Override
    public String downloadRegistrationInfo(Long matchId) {
        MatchInfoDto matchInfoDto = matchInfoService.findId(matchId);
        if (matchInfoDto == null) {
            return null;
        }

        List<RegistrationInfoDto> registrationInfoDtoList =
                registrationInfoMapper.toDto(
                        registrationInfoRepository.findAllByMatchInfoByMatchInfoIdAndRegistrationStatus(
                                matchInfoMapper.toEntity(matchInfoDto), "通过"
                        )
                );
        if (registrationInfoDtoList.size() == 0) {
            return null;
        }

        List<List<String>> rows = new ArrayList<>();
        rows.add(CollUtil.newArrayList("团队 ID", "团队名称", "团队简介", "团队战斗力", "学号", "姓名"));
        for (RegistrationInfoDto registrationInfoDto : registrationInfoDtoList) {
            List<TeamDto> list = teamMapper.toDto(teamRepository.findAllByTeamId(registrationInfoDto.getTeamByTeamId().getTeamId()));
            for (TeamDto teamDto : list) {
                List<String> row = CollUtil.newArrayList(
                        teamDto.getId().toString(),
                        teamDto.getTeamName(),
                        teamDto.getTeamProfile(),
                        teamDto.getTeamFightingCapacity().toString(),
                        teamDto.getUserByUserId().getUserNumber(),
                        teamDto.getUserByUserId().getUserName()
                );
                rows.add(row);
            }
        }

        String reFileName = IdUtil.fastSimpleUUID() + ".xlsx";
        String fileName = "D:\\IdeaProjects\\galop-server\\file\\" + reFileName;
        File file = new File(fileName);
        BigExcelWriter writer = ExcelUtil.getBigWriter(file);
        writer.merge(5, matchInfoDto.getMatchName());
        writer.write(rows, true);
        writer.close();
        return reFileName;
    }
}
