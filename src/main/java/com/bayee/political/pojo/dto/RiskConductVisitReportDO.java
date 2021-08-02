package com.bayee.political.pojo.dto;

/**
 * @author xxl
 * @date 2021/8/1
 */
public class RiskConductVisitReportDO {

    /**
     * 总扣分次数
     */
    private Integer totalCount;

    /**
     * 最高扣除分数
     */
    private Double maxDeductionScore;

    /**
     * 最多扣分类型
     */
    private String typeName;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
