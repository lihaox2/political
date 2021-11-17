package com.bayee.political.json;

/**
 * @author tlt
 * @date 2021/11/16
 */
public class ActivityDetailsResult {

    /**
     * id
     */
    private Integer id;

    /**
     *ID编号
     */
    private String idNum;

    /**
     *活动名称
     */
    private String activityName;

    /**
     *所属月份
     */
    private String belongMonth;

    /**
     *活动状态
     */
    private Integer activityStatus;

    /**
     *题目数
     */
    private Integer TopicCount;

    /**
     *已评价数
     */
    private Integer haveEvaluationCount;

    /**
     *创建时间
     */
    private String creationDate;

    /**
     *创建人
     */
    private String name;

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

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Integer getTopicCount() {
        return TopicCount;
    }

    public void setTopicCount(Integer topicCount) {
        TopicCount = topicCount;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
