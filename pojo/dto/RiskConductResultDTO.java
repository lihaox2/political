package com.bayee.political.pojo.dto;

import com.bayee.political.domain.ScreenDoubeChart;

import java.util.List;

/**
 * @author xxl
 * @date 2021/4/2
 */
public class RiskConductResultDTO {

    /**
     * 风险指数
     */
    private Integer totalCount;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 局规计分
     */
    private Integer bureauCount;

    /**
     * 信访计分
     */
    private Integer lettersCount;

    /**
     * 交通计分
     */
    private Integer trafficCount;

    /**
     * 本月 & 上月计分比例
     */
    private List<ScreenDoubeChart> monthList;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getBureauCount() {
        return bureauCount;
    }

    public void setBureauCount(Integer bureauCount) {
        this.bureauCount = bureauCount;
    }

    public Integer getLettersCount() {
        return lettersCount;
    }

    public void setLettersCount(Integer lettersCount) {
        this.lettersCount = lettersCount;
    }

    public Integer getTrafficCount() {
        return trafficCount;
    }

    public void setTrafficCount(Integer trafficCount) {
        this.trafficCount = trafficCount;
    }

    public List<ScreenDoubeChart> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<ScreenDoubeChart> monthList) {
        this.monthList = monthList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
