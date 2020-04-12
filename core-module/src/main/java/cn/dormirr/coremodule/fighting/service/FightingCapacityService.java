package cn.dormirr.coremodule.fighting.service;

import cn.dormirr.commonmodule.utils.PageUtils;
import cn.dormirr.coremodule.fighting.domain.vo.ChangeMatchRe;
import cn.dormirr.coremodule.fighting.service.dto.FightingCapacityDto;

import java.util.List;

/**
 * @author ZhangTianCi
 */
public interface FightingCapacityService {

    /**
     * 动态查询战斗力变化
     *
     * @param pageSize 每页数量
     * @param current  第几页
     * @param sorter   排序规则
     * @return 查询结果
     */
    PageUtils<FightingCapacityDto> findFightingCapacity(int pageSize, int current, String sorter);

    /**
     * 查询战斗力变化
     *
     * @return 查询结果
     */
    List<FightingCapacityDto> changeFightingCapacity();

    /**
     * 查询所有比赛参与人数
     *
     * @return 查询结果
     */
    List<ChangeMatchRe> changeMatch();
}
