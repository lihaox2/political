package com.bayee.political.json;

import java.util.List;

/**
 * @author xxl
 * @date 2021/11/23
 */
public class RiskRelevantItemResult {

    /**
     * 风险值
     */
    private Double indexNum;

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

    /**
     * 月份图表
     */
    private List<ChartResult> thisMonthChart;

    /**
     * 近六个月图表
     */
    private List<ChartResult> nearSixMonthChart;

    public Double getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Double indexNum) {
        this.indexNum = indexNum;
    }

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

    public List<ChartResult> getThisMonthChart() {
        return thisMonthChart;
    }

    public void setThisMonthChart(List<ChartResult> thisMonthChart) {
        this.thisMonthChart = thisMonthChart;
    }

    public List<ChartResult> getNearSixMonthChart() {
        return nearSixMonthChart;
    }

    public void setNearSixMonthChart(List<ChartResult> nearSixMonthChart) {
        this.nearSixMonthChart = nearSixMonthChart;
    }
}
