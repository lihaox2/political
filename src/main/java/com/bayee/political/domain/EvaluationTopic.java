package com.bayee.political.domain;

import java.util.Date;

public class EvaluationTopic {
    /**
     * id
     */
    private Integer id;

    /**
     * 题目名称
     */
    private String topicName;

    /**
     * 选择（1为是，2为否）
     */
    private Integer isChoose;

    /**
     * 活动信息id
     */
    private Integer activityId;

    /**
     * 评价人id
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date creationDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName == null ? null : topicName.trim();
    }

    public Integer getIsChoose() {
        return isChoose;
    }

    public void setIsChoose(Integer isChoose) {
        this.isChoose = isChoose;
    }

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}