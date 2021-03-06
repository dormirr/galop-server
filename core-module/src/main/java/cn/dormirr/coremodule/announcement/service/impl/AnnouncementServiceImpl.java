package cn.dormirr.coremodule.announcement.service.impl;

import cn.dormirr.commonmodule.utils.PageUtils;
import cn.dormirr.coremodule.announcement.domain.AnnouncementEntity;
import cn.dormirr.coremodule.announcement.repository.AnnouncementRepository;
import cn.dormirr.coremodule.announcement.service.AnnouncementService;
import cn.dormirr.coremodule.announcement.service.dto.AnnouncementDto;
import cn.dormirr.coremodule.announcement.service.mapper.AnnouncementMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangTianCi
 */
@Service
@CacheConfig(cacheNames = "announcement")
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper) {
        this.announcementRepository = announcementRepository;
        this.announcementMapper = announcementMapper;
    }

    /**
     * 创建公告
     *
     * @param announcementDto 公告
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void saveAnnouncement(AnnouncementDto announcementDto) {
        announcementRepository.save(announcementMapper.toEntity(announcementDto));
    }

    /**
     * 动态查询公告
     *
     * @param announcementDto 查询条件
     * @param pageSize        每页数量
     * @param current         第几页
     * @param sorter          排序规则
     * @return 查询结果
     */
    @Override
    @Cacheable
    public PageUtils<AnnouncementDto> findAnnouncement(AnnouncementDto announcementDto, int pageSize, int current, String sorter) {
        String descend = "ascend";
        String[] sort = sorter != null ? sorter.split("_") : new String[]{"id", ""};
        Pageable pageable = descend.equals(sort[1]) ?
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, sort[0])) :
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.DESC, sort[0]));

        Specification<AnnouncementEntity> specification = (Specification<AnnouncementEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> andQuery = new ArrayList<>();

            if (announcementDto.getAnnouncementTitle() != null) {
                Path<String> announcementTitle = root.get("announcementTitle");
                Predicate announcementTitleLike = criteriaBuilder.like(announcementTitle, "%" + announcementDto.getAnnouncementTitle() + "%");
                andQuery.add(announcementTitleLike);
            }

            Predicate[] andPredicates = andQuery.toArray(new Predicate[0]);
            return criteriaBuilder.and(andPredicates);
        };

        Page<AnnouncementEntity> data = announcementRepository.findAll(specification, pageable);

        List<AnnouncementDto> list = announcementMapper.toDto(data.getContent());

        return new PageUtils<>(list, data.getTotalElements(), data.getTotalPages());
    }

    /**
     * 删除公告
     *
     * @param announcementDto 待删除公告 ID
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void deleteAnnouncement(AnnouncementDto announcementDto) {
        announcementRepository.deleteById(announcementDto.getId());
    }

    /**
     * 修改公告
     *
     * @param announcementDto 待修改内容
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void saveApplyAnnouncement(AnnouncementDto announcementDto) {
        announcementRepository.save(announcementMapper.toEntity(announcementDto));
    }

    /**
     * 查询单条公告
     *
     * @param announcementDto 公告 ID
     * @return 查询结果
     */
    @Override
    @Cacheable
    public AnnouncementDto findOneAnnouncement(AnnouncementDto announcementDto) {
        return announcementRepository.findById(announcementDto.getId()).isPresent() ?
                announcementMapper.toDto(announcementRepository.findById(announcementDto.getId()).get()) :
                null;
    }

    /**
     * 查询最新的十条公告
     *
     * @return 查询结果
     */
    @Override
    @Cacheable
    public List<AnnouncementDto> findTenAnnouncement() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createTime"));

        Page<AnnouncementEntity> data = announcementRepository.findAll(pageable);

        return announcementMapper.toDto(data.getContent());
    }
}
