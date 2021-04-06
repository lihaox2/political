package com.bayee.political.domain;

import java.util.Date;

public class TrainProjectURule {
    private Integer id;

    private Integer ruleId;
    
    private Integer gradeId;

    private String name;

    private Double minNum;

    private Double maxNum;

    private Integer symbolA;

    private Integer symbolB;

    private Date creationDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getMinNum() {
        return minNum;
    }

    public void setMinNum(Double minNum) {
        this.minNum = minNum;
    }

    public Double getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Double maxNum) {
        this.maxNum = maxNum;
    }

    public Integer getSymbolA() {
        return symbolA;
    }

    public void setSymbolA(Integer symbolA) {
        this.symbolA = symbolA;
    }

    public Integer getSymbolB() {
        return symbolB;
    }

    public void setSymbolB(Integer symbolB) {
        this.symbolB = symbolB;
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

	/**
	 * @return the gradeId
	 */
	public Integer getGradeId() {
		return gradeId;
	}

	/**
	 * @param gradeId the gradeId to set
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
    
}