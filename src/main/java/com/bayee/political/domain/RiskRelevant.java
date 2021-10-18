package com.bayee.political.domain;

import java.util.Date;

public class RiskRelevant {
    private Integer id;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 风险值
     */
    private Double indexNum;

    /**
     * 总扣除分数
     */
    private Double deductionScore;

    /**
     * 扣除分数次数
     */
    private Integer deductionCount;

    /**
     * 创建时间
     */
    private Date creationDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId == null ? null : policeId.trim();
    }

    public Double getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Double indexNum) {
        this.indexNum = indexNum;
    }

    public Double getDeductionScore() {
        return deductionScore;
    }

    public void setDeductionScore(Double deductionScore) {
        this.deductionScore = deductionScore;
    }

    public Integer getDeductionCount() {
        return deductionCount;
    }

    public void setDeductionCount(Integer deductionCount) {
        this.deductionCount = deductionCount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}