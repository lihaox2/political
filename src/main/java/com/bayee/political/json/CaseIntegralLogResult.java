package com.bayee.political.json;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/8/29 14:43
 */
public class CaseIntegralLogResult {

    /**
     * 类型15-办案积分
     */
    private Integer type = 15;

    private Integer id;

    /**
     * 办案积分
     */
    private Double score;

    /**
     * 所属月份
     */
    private String month;

    /**
     * 所属时间（yyyy-MM-dd）
     */
    private String dateStr;

    /**
     * sort
     */
    private Date date;

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
