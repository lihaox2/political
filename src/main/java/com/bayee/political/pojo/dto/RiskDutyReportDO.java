package com.bayee.political.pojo.dto;

/**
 * @author xxl
 * @date 2021/8/1
 */
public class RiskDutyReportDO {

    /**
     * 最高扣除分数
     */
    private Double maxDeductionScore;

    /**
     * 最低扣除分数
     */
    private Double minDeductionScore;

    public Double getMaxDeductionScore() {
        return maxDeductionScore;
    }

    public void setMaxDeductionScore(Double maxDeductionScore) {
        this.maxDeductionScore = maxDeductionScore;
    }

    public Double getMinDeductionScore() {
        return minDeductionScore;
    }

    public void setMinDeductionScore(Double minDeductionScore) {
        this.minDeductionScore = minDeductionScore;
    }
}
