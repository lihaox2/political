package com.bayee.political.json;

import com.bayee.political.domain.EvaluationTopic;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/16
 */
public class EvaluationDetailsResult {

    /**
     * 活动名称
     */
    private String activityName;

    /**
     *评价人姓名
     */
    private String name;

    /**
     *评价对象
     */
    private String objectName;

    /**
     *警号
     */
    private String policeId;

    /**
     *总得分
     */
    private Integer totalScore;

    /**
     *评价时间
     */
    private String businessTime;

    /**
     *题目数
     */
    private Integer topicCount;

    /**
     *题目
     */
    private List<EvaluationTopic> topicList;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public Integer getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(Integer topicCount) {
        this.topicCount = topicCount;
    }

    public List<EvaluationTopic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<EvaluationTopic> topicList) {
        this.topicList = topicList;
    }
}
