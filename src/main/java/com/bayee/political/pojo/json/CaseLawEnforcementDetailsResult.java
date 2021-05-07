package com.bayee.political.pojo.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class CaseLawEnforcementDetailsResult {

    /**
     * 警员编号
     */
    private String policeId;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 问题分类
     */
    private Integer type;

    /**
     * 问题描述
     */
    private String desc;

    /**
     * 扣除分数
     */
    private Double deductScore;

    /**
     * 时间
     */
    private String date;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
}
