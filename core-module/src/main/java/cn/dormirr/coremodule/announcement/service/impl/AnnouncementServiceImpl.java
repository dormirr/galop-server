package cn.dormirr.coremodule.announcement.service.impl;

import cn.dormirr.coremodule.announcement.domain.AnnouncementEntity;
import cn.dormirr.coremodule.announcement.repository.AnnouncementRepository;
import cn.dormirr.coremodule.announcement.service.AnnouncementService;
import cn.dormirr.coremodule.announcement.service.dto.AnnouncementDto;
import cn.dormirr.coremodule.announcement.service.mapper.AnnouncementMapper;
import org.springframework.data.domain.*;
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
    @Transactional(rollbackFor=Exception.class)
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
    public Page<AnnouncementDto> findAnnouncement(AnnouncementDto announcementDto, int pageSize, int current, String sorter) {
        String descend = "descend";
        String[] sort = sorter != null ? sorter.split("_") : new String[]{"id", ""};
        Pageable pageable = descend.equals(sort[1]) ?
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.DESC, sort[0])) :
                PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, sort[0]));

        Specification<AnnouncementEntity> specification = (Specification<AnnouncementEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> andQuery = new ArrayList<>();

            if (announcementDto.getAnnouncementTitle() != null) {
                Path<Long> id = root.get("announcementTitle");
                Predicate idEqual = criteriaBuilder.equal(id, announcementDto.getAnnouncementTitle());
                andQuery.add(idEqual);
            }

            Predicate[] andPredicates = andQuery.toArray(new Predicate[0]);
            return criteriaBuilder.and(andPredicates);
        };

        Page<AnnouncementEntity> data = announcementRepository.findAll(specification, pageable);

        List<AnnouncementDto> list = announcementMapper.toDto(data.getContent());

        return new PageImpl<>(list, data.getPageable(), data.getTotalElements());
    }

    /**
     * 删除公告
     *
     * @param announcementDto 待删除公告 ID
     */
    @Override
    @Async
    @Transactional(rollbackFor=Exception.class)
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
    @Transactional(rollbackFor=Exception.class)
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
    public List<AnnouncementDto> findTenAnnouncement() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createTime"));

        Page<AnnouncementEntity> data = announcementRepository.findAll(pageable);

        return announcementMapper.toDto(data.getContent());
    }
}
