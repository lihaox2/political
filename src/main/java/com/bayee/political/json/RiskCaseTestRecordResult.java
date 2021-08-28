package com.bayee.political.json;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/6/14
 */
public class RiskCaseTestRecordResult {

    /**
     * 类型5-执法考试
     */
    private Integer type = 5;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 考试名称
     */
    private String name;

    /**
     * 考试得分
     */
    private Double score;

    /**
     * sort
     */
    private Date date;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
