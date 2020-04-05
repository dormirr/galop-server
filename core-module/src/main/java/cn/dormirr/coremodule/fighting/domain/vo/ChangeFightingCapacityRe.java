package cn.dormirr.coremodule.fighting.domain.vo;

/**
 * @author ZhangTianCi
 */
public class ChangeFightingCapacityRe {
    private String time;
    private Long value;

    public ChangeFightingCapacityRe(String time, Long value) {
        this.time = time;
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
