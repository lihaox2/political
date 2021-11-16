package com.bayee.political.json;

import java.util.List;

/**
 * @author tlt
 * @date 2021/10/27
 */
public class EvaluationSaveParam {

    /**
     * 活动id
     */
    private Integer activityId;

    /**
     *评价人id
     */
    private Integer userId;

    /**
     *题目
     */
    private List<EvaluationTopicSaveParam> topicList;

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

    public List<EvaluationTopicSaveParam> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<EvaluationTopicSaveParam> topicList) {
        this.topicList = topicList;
    }
}
