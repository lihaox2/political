package com.bayee.political.domain;

import java.util.Date;

/**
 * 奖励类型表
 */
public class RiskHonourType {
    private Integer id;

    /**
     * 奖励类型名称
     */
    private String name;

    /**
     * 分值
     */
    private Double score;

    /**
     * 奖励类型编码
     */
    private String code;

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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}