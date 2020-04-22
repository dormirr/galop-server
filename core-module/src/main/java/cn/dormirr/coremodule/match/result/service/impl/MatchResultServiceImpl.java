package cn.dormirr.coremodule.match.result.service.impl;

import cn.dormirr.commonmodule.utils.PageUtils;
import cn.dormirr.commonmodule.utils.SecurityUtils;
import cn.dormirr.coremodule.announcement.repository.AnnouncementRepository;
import cn.dormirr.coremodule.announcement.service.dto.AnnouncementDto;
import cn.dormirr.coremodule.announcement.service.mapper.AnnouncementMapper;
import cn.dormirr.coremodule.fighting.repository.FightingCapacityRepository;
import cn.dormirr.coremodule.fighting.service.dto.FightingCapacityDto;
import cn.dormirr.coremodule.fighting.service.mapper.FightingCapacityMapper;
import cn.dormirr.coremodule.match.info.service.MatchInfoService;
import cn.dormirr.coremodule.match.info.service.dto.MatchInfoDto;
import cn.dormirr.coremodule.match.info.service.mapper.MatchInfoMapper;
import cn.dormirr.coremodule.match.result.domain.MatchResultEntity;
import cn.dormirr.coremodule.match.result.repository.MatchResultRepository;
import cn.dormirr.coremodule.match.result.service.MatchResultService;
import cn.dormirr.coremodule.match.result.service.dto.MatchResultDto;
import cn.dormirr.coremodule.match.result.service.mapper.MatchResultMapper;
import cn.dormirr.coremodule.role.repository.UserRepository;
import cn.dormirr.coremodule.role.service.UserService;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.role.service.mapper.UserMapper;
import cn.dormirr.coremodule.team.repository.TeamRepository;
import cn.dormirr.coremodule.team.service.TeamService;
import cn.dormirr.coremodule.team.service.dto.TeamDto;
import cn.dormirr.coremodule.team.service.mapper.TeamMapper;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author ZhangTianCi
 */
@Service
@CacheConfig(cacheNames = "matchResult")
public class MatchResultServiceImpl implements MatchResultService {
    private final MatchResultRepository matchResultRepository;
    private final MatchResultMapper matchResultMapper;
    private final MatchInfoService matchInfoService;
    private final TeamService teamService;
    private final MatchInfoMapper matchInfoMapper;
    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;
    private final UserMapper userMapper;
    private final FightingCapacityRepository fightingCapacityRepository;
    private final FightingCapacityMapper fightingCapacityMapper;
    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final UserService userService;

    public MatchResultServiceImpl(MatchResultRepository matchResultRepository, MatchResultMapper matchResultMapper, MatchInfoService matchInfoService, TeamService teamService, MatchInfoMapper matchInfoMapper, TeamMapper teamMapper, TeamRepository teamRepository, UserMapper userMapper, FightingCapacityRepository fightingCapacityRepository, FightingCapacityMapper fightingCapacityMapper, UserRepository userRepository, AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper, UserService userService) {
        this.matchResultRepository = matchResultRepository;
        this.matchResultMapper = matchResultMapper;
        this.matchInfoService = matchInfoService;
        this.teamService = teamService;
        this.matchInfoMapper = matchInfoMapper;
        this.teamMapper = teamMapper;
        this.teamRepository = teamRepository;
        this.userMapper = userMapper;
        this.fightingCapacityRepository = fightingCapacityRepository;
        this.fightingCapacityMapper = fightingCapacityMapper;
        this.userRepository = userRepository;
        this.announcementRepository = announcementRepository;
        this.announcementMapper = announcementMapper;
        this.userService = userService;
    }

    /**
     * 生成比赛结果文件地址
     *
     * @param matchResultDtoList 比赛结果
     * @return 文件地址
     */
    @Override
    public String downloadMatchResult(List<MatchResultDto> matchResultDtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(CollUtil.newArrayList("团队 ID", "团队名称", "比赛名次"));
        for (MatchResultDto matchResultDto : matchResultDtoList) {
            List<String> row = CollUtil.newArrayList(
                    matchResultDto.getTeamByTeamId().getId().toString(),
                    matchResultDto.getTeamByTeamId().getTeamName(),
                    matchResultDto.getRanking().toString()
            );
            rows.add(row);
        }
        String matchName = matchResultDtoList.get(0).getMatchInfoByMatchInfoId().getMatchName();
        String reFileName = matchName + IdUtil.fastSimpleUUID() + ".xlsx";
        String fileName = "D:\\IdeaProjects\\galop-server\\file\\" + reFileName;
        File file = new File(fileName);
        BigExcelWriter writer = ExcelUtil.getBigWriter(file);
        writer.merge(2, matchName + "的比赛排名");
        writer.write(rows, true);
        writer.close();
        return reFileName;
    }

    /**
     * 查询比赛结果
     *
     * @param pageSize 每页数量
     * @param current  第几页
     * @param sorter   排序规则
     * @return 查询结果
     */
    @Override
    @Cacheable
    public PageUtils<MatchResultDto> findMatchResult(int pageSize, int current, String sorter) {
        String descend = "ascend";
        String[] sort = sorter != null ? sorter.replace(",", ".").split("_") : new String[]{"createTime", ""};
        Pageable pageable = descend.equals(sort[1]) ?
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, sort[0])) :
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.DESC, sort[0]));

        UserDto userDto = userService.findByUserNumber(SecurityUtils.getUsername());
        List<TeamDto> teamDtoList = teamMapper.toDto(teamRepository.findAllByUserByUserId(userMapper.toEntity(userDto)));

        Specification<MatchResultEntity> specification = (Specification<MatchResultEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> orQuery = new ArrayList<>();

            if (teamDtoList != null) {
                Path<Long> teamByTeamId = root.get("teamByTeamId");
                for (TeamDto teamDto : teamDtoList) {
                    Predicate teamByTeamIdEqual = criteriaBuilder.equal(teamByTeamId, teamMapper.toEntity(teamDto));
                    orQuery.add(teamByTeamIdEqual);
                }
            }

            Predicate[] andPredicates = orQuery.toArray(new Predicate[0]);
            return criteriaBuilder.or(andPredicates);
        };

        Page<MatchResultEntity> data = matchResultRepository.findAll(specification, pageable);

        return new PageUtils<>(matchResultMapper.toDto(data.getContent()), data.getTotalElements(), data.getTotalPages());
    }

    /**
     * 导入比赛结果
     *
     * @param multipartFile 比赛结果文件
     * @return 导入数量
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    @Caching(evict = {@CacheEvict(allEntries = true), @CacheEvict(cacheNames = "announcement", allEntries = true), @CacheEvict(cacheNames = "registrationInfo", allEntries = true), @CacheEvict(cacheNames = "team", allEntries = true), @CacheEvict(cacheNames = "user", allEntries = true), @CacheEvict(cacheNames = "fightingCapacity", allEntries = true)})
    public Future<Integer> saveMatchResult(MultipartFile multipartFile) {
        final int[] count = {0};
        List<MatchResultDto> matchResultDtoList = new ArrayList<>();
        RowHandler rowHandler = (sheetIndex, rowIndex, rowlist) -> {
            if (rowIndex >= 1 && rowlist.get(0) != null && rowlist.get(1) != null && rowlist.get(2) != null) {
                MatchResultDto matchResultDto = new MatchResultDto();
                MatchInfoDto matchInfoDto = matchInfoService.findId(Long.parseLong((String) rowlist.get(0)));
                matchResultDto.setMatchInfoByMatchInfoId(matchInfoMapper.toEntity(matchInfoDto));
                TeamDto teamDto = teamService.findId(Long.parseLong((String) rowlist.get(1)));
                matchResultDto.setTeamByTeamId(teamMapper.toEntity(teamDto));
                matchResultDto.setRanking(Integer.parseInt((String) rowlist.get(2)));
                matchResultDtoList.add(matchResultDto);
                matchResultRepository.save(matchResultMapper.toEntity(matchResultDto));

                count[0]++;

                int reward = matchInfoDto.getChampionAward() - (matchResultDto.getRanking() - 1)
                        * matchInfoDto.getDecrementParameter();

                List<TeamDto> listUserAndState = teamMapper.toDto(teamRepository.findAllByTeamIdAndTeamState(teamDto.getTeamId(), "通过"));
                for (TeamDto teamUserDto : listUserAndState) {
                    FightingCapacityDto fightingCapacityDto = new FightingCapacityDto();
                    fightingCapacityDto.setMatchInfoByMatchInfoId(matchResultDto.getMatchInfoByMatchInfoId());
                    fightingCapacityDto.setReward(reward);
                    fightingCapacityDto.setUserByUserId(teamUserDto.getUserByUserId());
                    fightingCapacityRepository.save(fightingCapacityMapper.toEntity(fightingCapacityDto));

                    UserDto userDto = userMapper.toDto(teamUserDto.getUserByUserId());
                    int rewardUser = userDto.getUserFightingCapacity() + reward;
                    userDto.setUserFightingCapacity(Math.max(rewardUser, 0));
                    userRepository.save(userMapper.toEntity(userDto));

                    List<TeamDto> listUser = teamMapper.toDto(teamRepository.findAllByUserByUserId(teamUserDto.getUserByUserId()));
                    for (TeamDto teamUser : listUser) {
                        List<TeamDto> team = teamMapper.toDto(teamRepository.findAllByTeamId(teamUser.getTeamId()));
                        for (TeamDto teamDto1 : team) {
                            teamDto1.setTeamFightingCapacity(teamDto1.getTeamFightingCapacity() + reward);
                            teamRepository.save(teamMapper.toEntity(teamDto1));
                        }
                    }
                }

                for (TeamDto teamUserDto : listUserAndState) {
                    List<TeamDto> listUser = teamMapper.toDto(teamRepository.findAllByUserByUserId(teamUserDto.getUserByUserId()));
                    for (TeamDto teamUser : listUser) {
                        List<TeamDto> team = teamMapper.toDto(teamRepository.findAllByTeamId(teamUser.getTeamId()));
                        for (TeamDto teamDto1 : team) {
                            if (teamDto1.getTeamFightingCapacity() >= 0) {
                                continue;
                            }
                            teamDto1.setTeamFightingCapacity(0);
                            teamRepository.save(teamMapper.toEntity(teamDto1));
                        }
                    }
                }
            }
        };

        try (InputStream inputStream = multipartFile.getInputStream()) {
            ExcelUtil.readBySax(inputStream, 1, rowHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String reFileName = downloadMatchResult(matchResultDtoList);

        AnnouncementDto announcementDto = new AnnouncementDto();

        String announcementTitle = matchResultDtoList.get(0).getMatchInfoByMatchInfoId().getMatchName() + "比赛结果公布啦";
        announcementDto.setAnnouncementTitle(announcementTitle);

        String announcementContent = "{\"blocks\":[{\"key\":\"3gt4r\",\"text\":\"点击下载比赛结果\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[{\"offset\":0,\"length\":8,\"style\":\"COLOR-000000\"}],\"entityRanges\":[{\"offset\":0,\"length\":8,\"key\":0}],\"data\":{}},{\"key\":\"711lj\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"9eijd\",\"text\":\"本条公告发自机器人管理员 \uD83D\uDE07 ~\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[{\"offset\":0,\"length\":13,\"style\":\"COLOR-07A9FE\"},{\"offset\":15,\"length\":1,\"style\":\"COLOR-07A9FE\"}],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{\"0\":{\"type\":\"LINK\",\"mutability\":\"MUTABLE\",\"data\":{\"href\":\"https://localhost:8080/file/" + reFileName + "\",\"target\":\"_blank\"}}}}";
        announcementDto.setAnnouncementContent(announcementContent);

        announcementRepository.save(announcementMapper.toEntity(announcementDto));

        return new AsyncResult<>(count[0]);
    }
}
