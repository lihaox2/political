package com.bayee.political.pojo.json;

import com.bayee.political.domain.Chart;
import com.bayee.political.domain.ScreenChart;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/20
 */
public class IndexRiskAlarmChartResult {

    /**
     * 风险人数
     */
    private Integer alarmNum;

    /**
     * 全局占比
     */
    private Double globalRatio;

    /**
     * 本月占比
     */
    private Double thisMonthNewRatio;

    /**
     * 趋势图
     */
    private List<ScreenChart> chartList;

    public Integer getAlarmNum() {
        return alarmNum;
    }

    public void setAlarmNum(Integer alarmNum) {
        this.alarmNum = alarmNum;
    }

    public Double getGlobalRatio() {
        return globalRatio;
    }

    public void setGlobalRatio(Double globalRatio) {
        this.globalRatio = globalRatio;
    }

    public Double getThisMonthNewRatio() {
        return thisMonthNewRatio;
    }

    public void setThisMonthNewRatio(Double thisMonthNewRatio) {
        this.thisMonthNewRatio = thisMonthNewRatio;
    }

    public List<ScreenChart> getChartList() {
        return chartList;
    }

    public void setChartList(List<ScreenChart> chartList) {
        this.chartList = chartList;
    }
}
