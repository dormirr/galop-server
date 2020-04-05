package cn.dormirr.coremodule.fighting.domain.vo;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class ChangeMatchRe implements Serializable {
    private  String match;
    private  Integer value;

    public ChangeMatchRe() {
    }

    public ChangeMatchRe(String match, Integer value) {
        this.match = match;
        this.value = value;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
