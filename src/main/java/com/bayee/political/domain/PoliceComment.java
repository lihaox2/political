package com.bayee.political.domain;

import java.util.Date;

/**
 * 警员评价
 *
 * @author xxl
 */
public class PoliceComment {
    private Integer id;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 评价内容
     */
    private String comment;

    /**
     * 添加人警号
     */
    private String createPoliceId;

    /**
     * 创建时间
     */
    private Date creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId == null ? null : policeId.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getCreatePoliceId() {
        return createPoliceId;
    }

    public void setCreatePoliceId(String createPoliceId) {
        this.createPoliceId = createPoliceId == null ? null : createPoliceId.trim();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}