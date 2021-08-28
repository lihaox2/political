package com.bayee.political.json;

import com.bayee.political.utils.DateUtils;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/6/14
 */
public class RiskPoliceCommentResult {

    /**
     * 类型13-评价
     */
    private Integer type = 13;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 评价内容
     */
    private String comment;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
