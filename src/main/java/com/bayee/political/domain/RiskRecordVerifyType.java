package com.bayee.political.domain;

import java.util.Date;

/**
 * 申诉类型表
 */
public class RiskRecordVerifyType {
    private Integer id;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}