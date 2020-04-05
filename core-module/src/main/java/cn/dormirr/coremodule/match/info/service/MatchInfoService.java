package cn.dormirr.coremodule.match.info.service;

import cn.dormirr.coremodule.match.info.service.dto.MatchInfoDto;
import cn.dormirr.coremodule.match.result.service.dto.MatchResultDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ZhangTianCi
 */
public interface MatchInfoService {

    /**
     * 创建比赛
     *
     * @param matchInfoDto 请求创建的比赛信息
     */
    void saveMatchInfo(MatchInfoDto matchInfoDto);

    /**
     * 查询比赛
     *
     * @param matchInfoDto 查询条件
     * @param pageSize     每页数量
     * @param current      第几页
     * @param sorter       排序规则
     * @return 查询结果
     */
    Page<MatchInfoDto> findMatchInfo(MatchInfoDto matchInfoDto, int pageSize, int current, String sorter);

    /**
     * 根据比赛 ID 查询比赛
     *
     * @param id 比赛 ID
     * @return 查询结果
     */
    MatchInfoDto findId(Long id);

    /**
     * 查询进行中的比赛
     *
     * @return 查询结果
     */
    List<MatchInfoDto> ongoingMatchInfo();
}
