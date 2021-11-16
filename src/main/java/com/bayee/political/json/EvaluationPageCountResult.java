package com.bayee.political.json;

/**
 * @author tlt
 * @date 2021/10/28
 */
public class EvaluationPageCountResult {

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 评价对象
     */
    private Integer objectCount;

    /**
     * 已评价人
     */
    private Integer haveEvaluationCount;

    /**
     * 未评价人
     */
    private Integer noEvaluationCount;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(Integer objectCount) {
        this.objectCount = objectCount;
    }

    public Integer getHaveEvaluationCount() {
        return haveEvaluationCount;
    }

    public void setHaveEvaluationCount(Integer haveEvaluationCount) {
        this.haveEvaluationCount = haveEvaluationCount;
    }

    public Integer getNoEvaluationCount() {
        return noEvaluationCount;
    }

    public void setNoEvaluationCount(Integer noEvaluationCount) {
        this.noEvaluationCount = noEvaluationCount;
    }
}
