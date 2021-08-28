package com.bayee.political.json;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/6/13
 */
public class RiskAchievementsRecordResult {

    /**
     * 类型1-绩效考核
     */
    private Integer type = 1;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 本序列排名
     */
    private Integer sequenceRanking;

    /**
     * 本序列人数
     */
    private Integer sequencePersonCount;

    /**
     * 等次(A B C D)
     */
    private String grade;

    /**
     * 排名年度
     */
    private String year;

    /**
     * 排名季度
     */
    private Integer quarter;

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

    public Integer getSequenceRanking() {
        return sequenceRanking;
    }

    public void setSequenceRanking(Integer sequenceRanking) {
        this.sequenceRanking = sequenceRanking;
    }

    public Integer getSequencePersonCount() {
        return sequencePersonCount;
    }

    public void setSequencePersonCount(Integer sequencePersonCount) {
        this.sequencePersonCount = sequencePersonCount;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
