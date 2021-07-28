package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class ConductVisitSaveParam {

    private String policeId;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 扣除分数
     */
    private Double deductScore;

    /**
     * 问题情况与描述
     */
    private String desc;

    /**
     * 时间
     */
    private String date;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 是否属实（1.属实，2.不属实）
     */
    private Integer isReally;

    /**
     * 信访来源id
     */
    private Integer originId;

    public Integer getIsReally() {
        return isReally;
    }

    public void setIsReally(Integer isReally) {
        this.isReally = isReally;
    }

    public Integer getOriginId() {
        return originId;
    }

    public void setOriginId(Integer originId) {
        this.originId = originId;
    }

    public Double getDeductScore() {
        return deductScore;
    }

    public void setDeductScore(Double deductScore) {
        this.deductScore = deductScore;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
