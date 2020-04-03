package cn.dormirr.coremodule.match.info.service.impl;

import cn.dormirr.coremodule.match.info.domain.MatchInfoEntity;
import cn.dormirr.coremodule.match.info.repository.MatchInfoRepository;
import cn.dormirr.coremodule.match.info.service.MatchInfoService;
import cn.dormirr.coremodule.match.info.service.dto.MatchInfoDto;
import cn.dormirr.coremodule.match.info.service.mapper.MatchInfoMapper;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangTianCi
 */
@Service
public class MatchInfoServiceImpl implements MatchInfoService {
    private final MatchInfoRepository matchInfoRepository;
    private final MatchInfoMapper matchInfoMapper;

    public MatchInfoServiceImpl(MatchInfoRepository matchInfoRepository, MatchInfoMapper matchInfoMapper) {
        this.matchInfoRepository = matchInfoRepository;
        this.matchInfoMapper = matchInfoMapper;
    }

    /**
     * @param matchInfoDto 请求创建的比赛信息
     */
    @Override
    public void saveMatchInfo(MatchInfoDto matchInfoDto) {
        matchInfoRepository.save(matchInfoMapper.toEntity(matchInfoDto));
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
    public Page<MatchInfoDto> findMatchInfo(MatchInfoDto matchInfoDto, int pageSize, int current, String sorter) {
        String descend = "descend";
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
                Predicate matchNameEqual = criteriaBuilder.like(matchName, "%" + matchInfoDto.getMatchName() + "%");
                andQuery.add(matchNameEqual);
            }

            Predicate[] andPredicates = andQuery.toArray(new Predicate[0]);
            return criteriaBuilder.and(andPredicates);
        };

        Page<MatchInfoEntity> data = matchInfoRepository.findAll(specification, pageable);

        List<MatchInfoDto> list = new ArrayList<>();
        for (MatchInfoEntity matchInfoEntity : data.getContent()) {
            list.add(matchInfoMapper.toDto(matchInfoEntity));
        }
        return new PageImpl<>(list, data.getPageable(), data.getTotalElements());
    }

    /**
     * 根据比赛 ID 查询比赛
     *
     * @param id 比赛 ID
     * @return 查询结果
     */
    @Override
    public MatchInfoDto findId(Long id) {
        return matchInfoRepository.findById(id).isPresent() ? matchInfoMapper.toDto(matchInfoRepository.findById(id).get()) : null;
    }

    /**
     * 查询进行中的比赛
     *
     * @return 查询结果
     */
    @Override
    public List<MatchInfoDto> ongoingMatchInfo() {
        return matchInfoMapper.toDto(
                matchInfoRepository.findAllByEndTimeGreaterThan(
                        new Timestamp(System.currentTimeMillis()
                        )
                )
        );
    }
}
