package cn.dormirr.coremodule.fighting.service.impl;

import cn.dormirr.commonmodule.utils.SecurityUtils;
import cn.dormirr.coremodule.fighting.domain.FightingCapacityEntity;
import cn.dormirr.coremodule.fighting.domain.vo.ChangeMatchRe;
import cn.dormirr.coremodule.fighting.repository.FightingCapacityRepository;
import cn.dormirr.coremodule.fighting.service.FightingCapacityService;
import cn.dormirr.coremodule.fighting.service.dto.FightingCapacityDto;
import cn.dormirr.coremodule.fighting.service.mapper.FightingCapacityMapper;
import cn.dormirr.coremodule.match.info.repository.MatchInfoRepository;
import cn.dormirr.coremodule.match.info.service.dto.MatchInfoDto;
import cn.dormirr.coremodule.match.info.service.mapper.MatchInfoMapper;
import cn.dormirr.coremodule.role.service.UserService;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.role.service.mapper.UserMapper;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangTianCi
 */
@Service
public class FightingCapacityServiceImpl implements FightingCapacityService {
    private final UserService userService;
    private final FightingCapacityRepository fightingCapacityRepository;
    private final FightingCapacityMapper fightingCapacityMapper;
    private final UserMapper userMapper;
    private final MatchInfoMapper matchInfoMapper;
    private final MatchInfoRepository matchInfoRepository;

    public FightingCapacityServiceImpl(UserService userService, FightingCapacityRepository fightingCapacityRepository, FightingCapacityMapper fightingCapacityMapper, UserMapper userMapper, MatchInfoMapper matchInfoMapper, MatchInfoRepository matchInfoRepository) {
        this.userService = userService;
        this.fightingCapacityRepository = fightingCapacityRepository;
        this.fightingCapacityMapper = fightingCapacityMapper;
        this.userMapper = userMapper;
        this.matchInfoMapper = matchInfoMapper;
        this.matchInfoRepository = matchInfoRepository;
    }

    /**
     * 动态查询战斗力变化
     *
     * @param pageSize            每页数量
     * @param current             第几页
     * @param sorter              排序规则
     * @return 查询结果
     */
    @Override
    public Page<FightingCapacityDto> findFightingCapacity(int pageSize, int current, String sorter) {
        String descend = "ascend";
        String[] sort = sorter != null ? sorter.split("_") : new String[]{"id", ""};
        Pageable pageable = descend.equals(sort[1]) ?
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, sort[0])) :
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.DESC, sort[0]));

        Specification<FightingCapacityEntity> specification = (Specification<FightingCapacityEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> andQuery = new ArrayList<>();

            UserDto userDto = userService.findByUserNumber(SecurityUtils.getUsername());
            Path<Long> userByUserId = root.get("userByUserId");
            Predicate userByUserIdEqual = criteriaBuilder.equal(userByUserId, userMapper.toEntity(userDto));
            andQuery.add(userByUserIdEqual);

            Predicate[] andPredicates = andQuery.toArray(new Predicate[0]);
            return criteriaBuilder.and(andPredicates);
        };

        Page<FightingCapacityEntity> data = fightingCapacityRepository.findAll(specification, pageable);

        List<FightingCapacityDto> list = fightingCapacityMapper.toDto(data.getContent());

        return new PageImpl<>(list, data.getPageable(), data.getTotalElements());
    }

    /**
     * 查询战斗力变化
     *
     * @return 查询结果
     */
    @Override
    public List<FightingCapacityDto> changeFightingCapacity() {
        Specification<FightingCapacityEntity> specification = (Specification<FightingCapacityEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> andQuery = new ArrayList<>();

            UserDto userDto = userService.findByUserNumber(SecurityUtils.getUsername());
            Path<Long> userByUserId = root.get("userByUserId");
            Predicate userByUserIdEqual = criteriaBuilder.equal(userByUserId, userMapper.toEntity(userDto));
            andQuery.add(userByUserIdEqual);

            Predicate[] andPredicates = andQuery.toArray(new Predicate[0]);
            return criteriaBuilder.and(andPredicates);
        };

        List<FightingCapacityEntity> data = fightingCapacityRepository.findAll(specification, Sort.by(Sort.Direction.ASC, "createTime"));

        return fightingCapacityMapper.toDto(data);
    }

    /**
     * 查询所有比赛参与人数
     *
     * @return 查询结果
     */
    @Override
    public List<ChangeMatchRe> changeMatch() {
        List<MatchInfoDto> data = matchInfoMapper.toDto(matchInfoRepository.findAll());

        if (data == null) {
            return null;
        }

        List<ChangeMatchRe> list = new ArrayList<>();
        for (MatchInfoDto matchInfoDto : data) {
            int count = fightingCapacityRepository.countAllByMatchInfoByMatchInfoId(matchInfoMapper.toEntity(matchInfoDto));
            if (count > 0) {
                list.add(new ChangeMatchRe(matchInfoDto.getMatchName(), count));
            }
        }

        return list;
    }
}
