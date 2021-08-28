package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/6/5
 */
public class HolographicPoliceCommentResult {

    /**
     * id
     */
    private Integer id;

    /**
     * 评价内容
     */
    private String comment;

    /**
     * 评价时间
     */
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
