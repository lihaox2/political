package com.bayee.political.json;

import java.util.List;

/**
 * @author tlt
 * @date 2021/10/29
 */
public class ActivityParticipationResult {

    /**
     * 已参与活动
     */
    private List<ActivityStart> havaParticipationActivityList;

    /**
     *未参与活动
     */
    private List<ActivityStart> noParticipationActivityList;

    public List<ActivityStart> getHavaParticipationActivityList() {
        return havaParticipationActivityList;
    }

    public void setHavaParticipationActivityList(List<ActivityStart> havaParticipationActivityList) {
        this.havaParticipationActivityList = havaParticipationActivityList;
    }

    public List<ActivityStart> getNoParticipationActivityList() {
        return noParticipationActivityList;
    }

    public void setNoParticipationActivityList(List<ActivityStart> noParticipationActivityList) {
        this.noParticipationActivityList = noParticipationActivityList;
    }
}
