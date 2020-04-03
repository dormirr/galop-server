package cn.dormirr.coremodule.registration.domain.vo;

import java.io.Serializable;

/**
 * @author ZhangTianCi
 */
public class DownloadRegistrationInfo  implements Serializable {
    private Long matchId;

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
}
