package com.bayee.political.json;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/6/14
 */
public class RiskFirearmRecordResult {

    /**
     * 类型9-射击训练
     */
    private Integer type = 9;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 快速射击 合格状态（合格，不合格，未参加）
     */
    private String rapidFireFlag;

    /**
     * 快速射击成绩
     */
    private Double rapidFireScore;

    /**
     * sort
     */
    private Date date;

    public Double getRapidFireScore() {
        return rapidFireScore;
    }

    public void setRapidFireScore(Double rapidFireScore) {
        this.rapidFireScore = rapidFireScore;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getRapidFireFlag() {
        return rapidFireFlag;
    }

    public void setRapidFireFlag(String rapidFireFlag) {
        this.rapidFireFlag = rapidFireFlag;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
