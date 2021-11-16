package com.bayee.political.json;

/**
 * @author tlt
 * @date 2021/10/25
 */
public class EvaluationPageQueryParam {

    /**
     * 活动id
     */
    private Integer activityId;

    /**
     * 模糊查询字段(评价人、评价对象)
     */
    private String key;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
