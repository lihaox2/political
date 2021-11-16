package com.bayee.political.json;

/**
 * @author tlt
 * @date 2021/11/4
 */
public class ActivityStartResult {

    /**
     * id
     */
    private Integer id;

    /**
     *活动名称
     */
    private String activityName;

    /**
     *年份（yyyy）
     */
    private String creationYear;

    /**
     *评价对象姓名
     */
    private String objectName;

    /**
     *题目数
     */
    private Integer topicCount;

    /**
     *评价id
     */
    private Integer evaluationId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(String creationYear) {
        this.creationYear = creationYear;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Integer getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(Integer topicCount) {
        this.topicCount = topicCount;
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }
}
