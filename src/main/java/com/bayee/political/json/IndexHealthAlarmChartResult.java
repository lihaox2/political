package com.bayee.political.json;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/20
 */
public class IndexHealthAlarmChartResult {

    /**
     * 体检人数
     */
    private Integer healthTotalCount;

    /**
     * 健康人数
     */
    private Integer healthyCount;

    /**
     * 风险人数
     */
    private Integer alarmCount;

    /**
     * 健康图表
     */
    List<IndexHealthChart> chartList;

    public Integer getHealthTotalCount() {
        return healthTotalCount;
    }

    public void setHealthTotalCount(Integer healthTotalCount) {
        this.healthTotalCount = healthTotalCount;
    }

    public Integer getHealthyCount() {
        return healthyCount;
    }

    public void setHealthyCount(Integer healthyCount) {
        this.healthyCount = healthyCount;
    }

    public Integer getAlarmCount() {
        return alarmCount;
    }

    public void setAlarmCount(Integer alarmCount) {
        this.alarmCount = alarmCount;
    }

    public List<IndexHealthChart> getChartList() {
        return chartList;
    }

    public void setChartList(List<IndexHealthChart> chartList) {
        this.chartList = chartList;
    }
}
