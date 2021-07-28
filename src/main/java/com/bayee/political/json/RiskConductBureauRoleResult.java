package com.bayee.political.json;

import com.bayee.political.domain.ScreenDoubeChart;

import java.util.List;

/**
 * @author xxl
 * @date 2021/4/6
 */
public class RiskConductBureauRoleResult {

    /**
     * 风险指数
     */
    private Double indexNum;

    /**
     * 扣分项数
     */
    private Integer deductionScoreCount;

    /**
     * 累计扣分
     */
    private Double totalDeductionScore;

    /**
     * 本月 & 上月计分比例
     */
    private List<ScreenDoubeChart> monthList;

    public Double getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Double indexNum) {
        this.indexNum = indexNum;
    }

    public Integer getDeductionScoreCount() {
        return deductionScoreCount;
    }

    public void setDeductionScoreCount(Integer deductionScoreCount) {
        this.deductionScoreCount = deductionScoreCount;
    }

    public Double getTotalDeductionScore() {
        return totalDeductionScore;
    }

    public void setTotalDeductionScore(Double totalDeductionScore) {
        this.totalDeductionScore = totalDeductionScore;
    }

    public List<ScreenDoubeChart> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<ScreenDoubeChart> monthList) {
        this.monthList = monthList;
    }
}
