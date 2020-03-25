package cn.dormirr.coremodule.match.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author ZhangTianCi
 */
@Data
public class MatchInformationDto implements Serializable {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String matchName;
    private Integer teamSize;
    private Integer firstPlaceCombatEffectiveness;
    private Integer decrementParameter;
}
