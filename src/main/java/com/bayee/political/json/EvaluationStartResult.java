package com.bayee.political.json;

import com.bayee.political.domain.EvaluationTopic;

import java.util.List;

/**
 * @author tlt
 * @date 2021/10/27
 */
public class EvaluationStartResult {

    /**
     * 评价活动id
     */
    private Integer activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 题目数
     */
    private Integer topicCount;

    /**
     * 评价对象
     */
    private String objectName;

    /**
     * 题目
     */
    private List<EvaluationTopic> topicList;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(Integer topicCount) {
        this.topicCount = topicCount;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public List<EvaluationTopic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<EvaluationTopic> topicList) {
        this.topicList = topicList;
    }
}
