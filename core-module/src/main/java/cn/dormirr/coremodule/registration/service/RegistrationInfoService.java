package cn.dormirr.coremodule.registration.service;

import cn.dormirr.commonmodule.utils.PageUtils;
import cn.dormirr.coremodule.registration.service.dto.RegistrationInfoDto;

/**
 * @author ZhangTianCi
 */
public interface RegistrationInfoService {

    /**
     * 报名比赛
     *
     * @param registrationInfoDto 报名比赛
     * @param matchId             比赛 ID
     * @param teamId              团队 ID
     * @return 结果
     */
    boolean saveRegistrationInfo(RegistrationInfoDto registrationInfoDto, Long matchId, Long teamId);

    /**
     * 动态查询申请
     *
     * @param registrationInfoDto 查询条件
     * @param pageSize            每页数量
     * @param current             第几页
     * @param sorter              排序规则
     * @return 查询结果
     */
    PageUtils<RegistrationInfoDto> findRegistrationInfo(RegistrationInfoDto registrationInfoDto, int pageSize, int current, String sorter);

    /**
     * 同意申请
     *
     * @param id 申请处理 ID
     * @return 申请处理结果
     */
    boolean saveApplyRegistrationInfo(Long id);

    /**
     * 拒绝申请
     *
     * @param id 申请处理 ID
     * @return 申请处理结果
     */
    boolean deleteApplyRegistrationInfo(Long id);

    /**
     * 根据比赛 ID 导出报名表
     *
     * @param matchId 比赛 ID
     * @return 文件地址
     */
    String downloadRegistrationInfo(Long matchId);
}
