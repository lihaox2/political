package com.bayee.political.pojo.json;

import com.bayee.political.domain.ScreenChart;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/20
 */
public class IndexCaseChartResult {

    /**
     * 存在问题人数
     */
    private Integer existsErrorPeopleNum;

    /**
     * 本月新增
     */
    private Double thisMonthNewRatio;

    /**
     * 重复问题数
     */
    private Integer replaceErrorNum;

    /**
     * 趋势图
     */
    private List<ScreenChart> chartList;

    public Integer getExistsErrorPeopleNum() {
        return existsErrorPeopleNum;
    }

    public Double getThisMonthNewRatio() {
        return thisMonthNewRatio;
    }

    public void setExistsErrorPeopleNum(Integer existsErrorPeopleNum) {
        this.existsErrorPeopleNum = existsErrorPeopleNum;
    }

    public void setThisMonthNewRatio(Double thisMonthNewRatio) {
        this.thisMonthNewRatio = thisMonthNewRatio;
    }

    public Integer getReplaceErrorNum() {
        return replaceErrorNum;
    }

    public void setReplaceErrorNum(Integer replaceErrorNum) {
        this.replaceErrorNum = replaceErrorNum;
    }

    public List<ScreenChart> getChartList() {
        return chartList;
    }

    public void setChartList(List<ScreenChart> chartList) {
        this.chartList = chartList;
    }
}
