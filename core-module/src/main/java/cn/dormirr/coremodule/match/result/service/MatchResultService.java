package cn.dormirr.coremodule.match.result.service;

import cn.dormirr.commonmodule.utils.PageUtils;
import cn.dormirr.coremodule.match.result.service.dto.MatchResultDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author ZhangTianCi
 */
public interface MatchResultService {

    /**
     * 导入比赛结果
     *
     * @param multipartFile 比赛结果文件
     * @return 导入数量
     */
    Future<Integer> saveMatchResult(MultipartFile multipartFile);


    /**
     * 生成比赛结果文件地址
     *
     * @param matchResultDtoList 比赛结果
     * @return 文件地址
     */
    String downloadMatchResult(List<MatchResultDto> matchResultDtoList);

    /**
     * 查询比赛结果
     *
     * @param pageSize 每页数量
     * @param current  第几页
     * @param sorter   排序规则
     * @return 查询结果
     */
    PageUtils<MatchResultDto> findMatchResult(int pageSize, int current, String sorter);
}
