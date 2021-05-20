package com.bayee.political.domain;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/5/19
 */
public class RiskDutyInformationType {

    /**
     * 警情id
     */
    private Integer id;

    /**
     * 警情名称
     */
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
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
