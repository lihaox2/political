package com.bayee.political.json;

/**
 * @author tlt
 * @date 2021/10/27
 */
public class EvaluationStartParam {

    /**
     * 活动id
     */
    private Integer activityId;

    /**
     * 评价人id
     */
    private Integer userId;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
