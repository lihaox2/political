package com.bayee.political.domain;

/**
 * 职务序列改革后职务表
 */
public class Position {
    /**
     * id
     */
    private Integer id;

    /**
     * 职务序列名称
     */
    private String positionName;

    /**
     * 职务类型（1执法序列，2技术序列）
     */
    private Integer positionType;

    /**
     * 下一晋升等级
     */
    private Integer pId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    public Integer getPositionType() {
        return positionType;
    }

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}