package cn.dormirr.coremodule.match.result.service.impl;

import cn.dormirr.coremodule.announcement.repository.AnnouncementRepository;
import cn.dormirr.coremodule.announcement.service.dto.AnnouncementDto;
import cn.dormirr.coremodule.announcement.service.mapper.AnnouncementMapper;
import cn.dormirr.coremodule.fighting.repository.FightingCapacityRepository;
import cn.dormirr.coremodule.fighting.service.dto.FightingCapacityDto;
import cn.dormirr.coremodule.fighting.service.mapper.FightingCapacityMapper;
import cn.dormirr.coremodule.match.info.service.MatchInfoService;
import cn.dormirr.coremodule.match.info.service.dto.MatchInfoDto;
import cn.dormirr.coremodule.match.info.service.mapper.MatchInfoMapper;
import cn.dormirr.coremodule.match.result.repository.MatchResultRepository;
import cn.dormirr.coremodule.match.result.service.MatchResultService;
import cn.dormirr.coremodule.match.result.service.dto.MatchResultDto;
import cn.dormirr.coremodule.match.result.service.mapper.MatchResultMapper;
import cn.dormirr.coremodule.role.repository.UserRepository;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    public MatchResultServiceImpl(MatchResultRepository matchResultRepository, MatchResultMapper matchResultMapper, MatchInfoService matchInfoService, TeamService teamService, MatchInfoMapper matchInfoMapper, TeamMapper teamMapper, TeamRepository teamRepository, UserMapper userMapper, FightingCapacityRepository fightingCapacityRepository, FightingCapacityMapper fightingCapacityMapper, UserRepository userRepository, AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper) {
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

                int reward = matchInfoDto.getChampionAward() -
                        (matchResultDto.getRanking() - 1) *
                                matchInfoDto.getDecrementParameter();

                List<TeamDto> listUser = teamMapper.toDto(teamRepository.findAllByTeamId(teamDto.getTeamId()));
                for (TeamDto teamUserDto : listUser) {
                    teamUserDto.setTeamFightingCapacity(teamUserDto.getTeamFightingCapacity() + reward*listUser.size());
                    teamRepository.save(teamMapper.toEntity(teamUserDto));
                }
                List<TeamDto> listUserAndState = teamMapper.toDto(teamRepository.findAllByTeamIdAndTeamState(teamDto.getTeamId(),"通过"));
                for (TeamDto teamUserDto : listUserAndState) {
                    FightingCapacityDto fightingCapacityDto = new FightingCapacityDto();
                    fightingCapacityDto.setMatchInfoByMatchInfoId(matchResultDto.getMatchInfoByMatchInfoId());
                    fightingCapacityDto.setReward(reward);
                    fightingCapacityDto.setUserByUserId(teamUserDto.getUserByUserId());
                    fightingCapacityRepository.save(fightingCapacityMapper.toEntity(fightingCapacityDto));

                    UserDto userDto = userMapper.toDto(teamUserDto.getUserByUserId());
                    userDto.setUserFightingCapacity(userDto.getUserFightingCapacity() + reward);
                    userRepository.save(userMapper.toEntity(userDto));
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
