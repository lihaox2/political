package com.bayee.political.pojo.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class ConductVisitDetailsResult {

    private String policeId;

    private String policeName;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 问题情况和来源
     */
    private String content;

    /**
     * 扣除分数
     */
    private Double deductScore;

    /**
     * 时间
     */
    private String date;

    /**
     * 备注
     */
    private String remarks;

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getDeductScore() {
        return deductScore;
    }

    public void setDeductScore(Double deductScore) {
        this.deductScore = deductScore;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
