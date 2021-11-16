package com.bayee.political.domain;

import java.util.Date;

public class EvaluationObject {
    /**
     * id
     */
    private Integer id;

    /**
     * 评价对象姓名
     */
    private String objectName;

    /**
     * 警员id
     */
    private String policeId;

    /**
     * 创建时间
     */
    private Date creationDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName == null ? null : objectName.trim();
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId == null ? null : policeId.trim();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}