package com.bayee.political.pojo.json;

/**
 * @author xxl
 * @date 2021/7/21
 */
public class CheckRiskRecordParam {

    /**
     * 数据项id
     */
    private Integer id;

    /**
     * 是否同意（1.已同意，2.不同意）
     */
    private Integer isAgree;

    /**
     * 审核人警号
     */
    private String checkPoliceId;

    /**
     * 审核 内容
     */
    private String checkContent;

    /**
     * 审核 扣除分数
     */
    private Double checkDeductionScore;

    /**
     * 审核 扣除对象
     */
    private String checkDeductionPolice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(Integer isAgree) {
        this.isAgree = isAgree;
    }

    public String getCheckPoliceId() {
        return checkPoliceId;
    }

    public void setCheckPoliceId(String checkPoliceId) {
        this.checkPoliceId = checkPoliceId;
    }

    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
    }

    public Double getCheckDeductionScore() {
        return checkDeductionScore;
    }

    public void setCheckDeductionScore(Double checkDeductionScore) {
        this.checkDeductionScore = checkDeductionScore;
    }

    public String getCheckDeductionPolice() {
        return checkDeductionPolice;
    }

    public void setCheckDeductionPolice(String checkDeductionPolice) {
        this.checkDeductionPolice = checkDeductionPolice;
    }
}
