package cn.dormirr.coremodule.match.info.service.impl;

import cn.dormirr.commonmodule.utils.PageUtils;
import cn.dormirr.coremodule.announcement.repository.AnnouncementRepository;
import cn.dormirr.coremodule.announcement.service.dto.AnnouncementDto;
import cn.dormirr.coremodule.announcement.service.mapper.AnnouncementMapper;
import cn.dormirr.coremodule.match.info.domain.MatchInfoEntity;
import cn.dormirr.coremodule.match.info.repository.MatchInfoRepository;
import cn.dormirr.coremodule.match.info.service.MatchInfoService;
import cn.dormirr.coremodule.match.info.service.dto.MatchInfoDto;
import cn.dormirr.coremodule.match.info.service.mapper.MatchInfoMapper;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangTianCi
 */
@Service
@CacheConfig(cacheNames = "matchInfo")
public class MatchInfoServiceImpl implements MatchInfoService {
    private final MatchInfoRepository matchInfoRepository;
    private final MatchInfoMapper matchInfoMapper;
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;

    public MatchInfoServiceImpl(MatchInfoRepository matchInfoRepository, MatchInfoMapper matchInfoMapper, AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper) {
        this.matchInfoRepository = matchInfoRepository;
        this.matchInfoMapper = matchInfoMapper;
        this.announcementRepository = announcementRepository;
        this.announcementMapper = announcementMapper;
    }

    /**
     * @param matchInfoDto 请求创建的比赛信息
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    @Caching(evict = {@CacheEvict(cacheNames = "matchInfo", allEntries = true), @CacheEvict(cacheNames = "announcement", allEntries = true)})
    public void saveMatchInfo(MatchInfoDto matchInfoDto) {
        matchInfoRepository.save(matchInfoMapper.toEntity(matchInfoDto));

        AnnouncementDto announcementDto = new AnnouncementDto();

        String announcementTitle = matchInfoDto.getMatchName() + "开始报名啦";
        announcementDto.setAnnouncementTitle(announcementTitle);

        String announcementContent1 = "{\"blocks\":[{\"key\":\"f79uh\",\"text\":\"比赛类型\",\"type\":\"header-one\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"1r9uj\",\"text\":\"";
        String announcementContent2 = matchInfoDto.getMatchType() + "\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"7u1pm\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"cc81f\",\"text\":\"比赛团队规模\",\"type\":\"header-one\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"6m26a\",\"text\":\"";
        String announcementContent3 = matchInfoDto.getTeamSize() + "人\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"32rdr\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"5qi5s\",\"text\":\"第一所得奖励\",\"type\":\"header-one\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"fm5gp\",\"text\":\"";
        String announcementContent4 = matchInfoDto.getChampionAward() + "分\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"26evh\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"5mrqr\",\"text\":\"递减梯度\",\"type\":\"header-one\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"cg2l9\",\"text\":\"";
        String announcementContent5 = matchInfoDto.getDecrementParameter() + "分\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"1pr9n\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"1jrr3\",\"text\":\"比赛时间\",\"type\":\"header-one\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"c7kt1\",\"text\":\"";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String announcementContent6 = dateFormat.format(matchInfoDto.getStartTime()) + " - " + dateFormat.format(matchInfoDto.getEndTime()) + "\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{\"nodeAttributes\":{}}},{\"key\":\"e9cfr\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"49nfp\",\"text\":\"请大家踊跃报名哦~\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"9j6i6\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"fmuu5\",\"text\":\"本条公告发自机器人管理员 \uD83D\uDE07 ~\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[{\"offset\":0,\"length\":13,\"style\":\"COLOR-07A9FE\"},{\"offset\":15,\"length\":1,\"style\":\"COLOR-07A9FE\"}],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
        String announcementContent = announcementContent1 + announcementContent2 + announcementContent3 + announcementContent4 + announcementContent5 + announcementContent6;
        announcementDto.setAnnouncementContent(announcementContent);

        announcementRepository.save(announcementMapper.toEntity(announcementDto));
    }

    /**
     * 查询比赛
     *
     * @param matchInfoDto 查询条件
     * @param pageSize     每页数量
     * @param current      第几页
     * @param sorter       排序规则
     * @return 查询结果
     */
    @Override
    @Cacheable
    public PageUtils<MatchInfoDto> findMatchInfo(MatchInfoDto matchInfoDto, int pageSize, int current, String sorter) {
        String descend = "ascend";
        String[] sort = sorter != null ? sorter.split("_") : new String[]{"id", ""};
        Pageable pageable = descend.equals(sort[1]) ?
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, sort[0])) :
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.DESC, sort[0]));

        Specification<MatchInfoEntity> specification = (Specification<MatchInfoEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> andQuery = new ArrayList<>();

            if (matchInfoDto.getId() != null) {
                Path<Long> id = root.get("id");
                Predicate idEqual = criteriaBuilder.equal(id, matchInfoDto.getId());
                andQuery.add(idEqual);
            }

            if (matchInfoDto.getMatchName() != null) {
                Path<String> matchName = root.get("matchName");
                Predicate matchNameLike = criteriaBuilder.like(matchName, "%" + matchInfoDto.getMatchName() + "%");
                andQuery.add(matchNameLike);
            }

            if (matchInfoDto.getMatchType() != null) {
                Path<String> matchType = root.get("matchType");
                Predicate matchTypeLike = criteriaBuilder.like(matchType, "%" + matchInfoDto.getMatchType() + "%");
                andQuery.add(matchTypeLike);
            }

            Predicate[] andPredicates = andQuery.toArray(new Predicate[0]);
            return criteriaBuilder.and(andPredicates);
        };

        Page<MatchInfoEntity> data = matchInfoRepository.findAll(specification, pageable);

        return new PageUtils<>(matchInfoMapper.toDto(data.getContent()), data.getTotalElements(), data.getTotalPages());
    }

    /**
     * 根据比赛 ID 查询比赛
     *
     * @param id 比赛 ID
     * @return 查询结果
     */
    @Override
    @Cacheable
    public MatchInfoDto findId(Long id) {
        return matchInfoRepository.findById(id).isPresent() ? matchInfoMapper.toDto(matchInfoRepository.findById(id).get()) : null;
    }

    /**
     * 查询进行中的比赛
     *
     * @return 查询结果
     */
    @Override
    @Cacheable
    public List<MatchInfoDto> ongoingMatchInfo() {
        return matchInfoMapper.toDto(
                matchInfoRepository.findAllByEndTimeGreaterThan(
                        new Timestamp(System.currentTimeMillis()
                        )
                )
        );
    }
}
