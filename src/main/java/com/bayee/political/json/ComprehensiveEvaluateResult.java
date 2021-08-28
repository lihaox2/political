package com.bayee.political.json;

import java.util.List;

/**
 * @author xxl
 * @date 2021/6/16
 */
public class ComprehensiveEvaluateResult {

    /**
     * 总分数
     */
    private Double indexNum;

    /**
     * 总分数颜色值(1.绿色，2.橙色，3.红色)
     */
    private Integer indexNumFlag;

    /**
     * 正面分数
     */
    private Double awardScore;

    /**
     * 负面分数
     */
    private Double deductionScore;

    /**
     * 健康描述
     */
    private String healthDesc;

    /**
     * 警务数据蜘蛛网
     */
    private List<ChartResult> chartResultList;

    public Double getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Double indexNum) {
        this.indexNum = indexNum;
    }

    public Integer getIndexNumFlag() {
        return indexNumFlag;
    }

    public void setIndexNumFlag(Integer indexNumFlag) {
        this.indexNumFlag = indexNumFlag;
    }

    public Double getAwardScore() {
        return awardScore;
    }

    public void setAwardScore(Double awardScore) {
        this.awardScore = awardScore;
    }

    public Double getDeductionScore() {
        return deductionScore;
    }

    public void setDeductionScore(Double deductionScore) {
        this.deductionScore = deductionScore;
    }

    public String getHealthDesc() {
        return healthDesc;
    }

    public void setHealthDesc(String healthDesc) {
        this.healthDesc = healthDesc;
    }

    public List<ChartResult> getChartResultList() {
        return chartResultList;
    }

    public void setChartResultList(List<ChartResult> chartResultList) {
        this.chartResultList = chartResultList;
    }
}
