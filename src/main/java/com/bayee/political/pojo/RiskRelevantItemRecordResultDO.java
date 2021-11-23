package com.bayee.political.pojo;

/**
 * @author xxl
 * @date 2021/11/23
 */
public class RiskRelevantItemRecordResultDO {

    /**
     * 重大事项
     */
    private Double majorEventsNum;

    /**
     * 勤政廉政
     */
    private Double politicalAction;

    /**
     * 思想动态
     */
    private Double ideologicalDynamicsNum;

    /**
     * 安全隐患
     */
    private Double potentialRisk;

    public Double getMajorEventsNum() {
        return majorEventsNum;
    }

    public void setMajorEventsNum(Double majorEventsNum) {
        this.majorEventsNum = majorEventsNum;
    }

    public Double getPoliticalAction() {
        return politicalAction;
    }

    public void setPoliticalAction(Double politicalAction) {
        this.politicalAction = politicalAction;
    }

    public Double getIdeologicalDynamicsNum() {
        return ideologicalDynamicsNum;
    }

    public void setIdeologicalDynamicsNum(Double ideologicalDynamicsNum) {
        this.ideologicalDynamicsNum = ideologicalDynamicsNum;
    }

    public Double getPotentialRisk() {
        return potentialRisk;
    }

    public void setPotentialRisk(Double potentialRisk) {
        this.potentialRisk = potentialRisk;
    }
}
