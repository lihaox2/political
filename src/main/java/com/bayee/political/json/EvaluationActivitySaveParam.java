package com.bayee.political.json;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/16
 */
public class EvaluationActivitySaveParam {

    /**
     * id
     */
    private Integer id;

    /**
     *活动名称
     */
    private String activityName;

    /**
     *所属月份
     */
    private String belongMonth;

    /**
     *题目
     */
    private List<EvaluationTopicSaveParam> topicSaveParamsList;

    /**
     *警号
     */
    private String policeId;

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

    public String getBelongMonth() {
        return belongMonth;
    }

    public void setBelongMonth(String belongMonth) {
        this.belongMonth = belongMonth;
    }

    public List<EvaluationTopicSaveParam> getTopicSaveParamsList() {
        return topicSaveParamsList;
    }

    public void setTopicSaveParamsList(List<EvaluationTopicSaveParam> topicSaveParamsList) {
        this.topicSaveParamsList = topicSaveParamsList;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }
}
