package com.bayee.political.domain;

import java.util.Date;

public class PoliceRelevantType {
    /**
     * code
     */
    private String code;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 父级code
     */
    private String pCode;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 创建时间
     */
    private Date creationDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode == null ? null : pCode.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}