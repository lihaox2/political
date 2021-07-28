package com.bayee.political.json;

import com.bayee.political.domain.ScreenDoubeChart;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/20
 */
public class IndexTrainChartResult {

    /**
     * 训练次数
     */
    private Integer trainCount;

    /**
     * 合格数
     */
    private Integer eligibleCount;

    /**
     * 合格率
     */
    private Double eligibleRatio;

    /**
     * 趋势图
     */
    private List<ScreenDoubeChart> chartList;

    public Integer getTrainCount() {
        return trainCount;
    }

    public void setTrainCount(Integer trainCount) {
        this.trainCount = trainCount;
    }

    public Integer getEligibleCount() {
        return eligibleCount;
    }

    public void setEligibleCount(Integer eligibleCount) {
        this.eligibleCount = eligibleCount;
    }

    public Double getEligibleRatio() {
        return eligibleRatio;
    }

    public void setEligibleRatio(Double eligibleRatio) {
        this.eligibleRatio = eligibleRatio;
    }

    public List<ScreenDoubeChart> getChartList() {
        return chartList;
    }

    public void setChartList(List<ScreenDoubeChart> chartList) {
        this.chartList = chartList;
    }
}
