package com.bayee.political.pojo;

/**
 * @author tlt
 * @date 2021/11/15
 */
public class ActivityPageQueryResultDO {

    /**
     * id
     */
    private Integer id;

    /**
     * ID编号
     */
    private String idNum;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 所属月份
     */
    private String belongMonth;

    /**
     * 活动状态
     */
    private String activityStatus;

    /**
     * 题目数
     */
    private Integer topicCount;

    /**
     * 已评价数
     */
    private Integer haveEvaluationCount;

    /**
     * 创建时间
     */
    private String creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getBelongMonth() {
        return belongMonth;
    }

    public void setBelongMonth(String belongMonth) {
        this.belongMonth = belongMonth;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Integer getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(Integer topicCount) {
        this.topicCount = topicCount;
    }

    public Integer getHaveEvaluationCount() {
        return haveEvaluationCount;
    }

    public void setHaveEvaluationCount(Integer haveEvaluationCount) {
        this.haveEvaluationCount = haveEvaluationCount;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
