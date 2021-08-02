package com.bayee.political.pojo.dto;

/**
 * @author xxl
 * @date 2021/8/1
 */
public class RiskConductTrafficViolationReportDO {

    /**
     * 扣分次数
     */
    private Integer totalCount;

    /**
     * 最高扣除分数
     */
    private Double maxDeductionScore;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Double getMaxDeductionScore() {
        return maxDeductionScore;
    }

    public void setMaxDeductionScore(Double maxDeductionScore) {
        this.maxDeductionScore = maxDeductionScore;
    }
}
