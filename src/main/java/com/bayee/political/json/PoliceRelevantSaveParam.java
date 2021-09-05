package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/9/4 20:09
 */
public class PoliceRelevantSaveParam {

    /**
     * 警号
     */
    private String policeId;

    /**
     * 类型code
     */
    private String typeCode;

    /**
     * 扣除分数
     */
    private Double deductionScore;

    /**
     * 记录时间(yyyy-MM-dd)
     */
    private String businessDate;

    /**
     * 备注
     */
    private String remark;

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Double getDeductionScore() {
        return deductionScore;
    }

    public void setDeductionScore(Double deductionScore) {
        this.deductionScore = deductionScore;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
