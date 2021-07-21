package com.bayee.political.domain;

import java.util.Date;

public class RiskConductVisitRecord {
    private Integer id;

    private String policeId;

    private Integer type;

    private Date inputTime;

    private String content;

    private String remarks;
    
    private Double deductionScore;

    private Date creationDate;

    private Date updateDate;
    
    private String typeName;

    private Integer bigType;

    private Integer smallType;

    private String bigTypeName;

    private String smallTypeName;

    /**
     * 状态 0.可申诉，1.申诉中，2.申诉通过，3.申诉不通过，4.已撤销申诉
     */
    private Integer appealState;

    /**
     * 信访来源
     */
    private Integer originId;

    /**
     * 是否属实（1.属实，2.不属实）
     */
    private Integer isReally;

    /**
     * 是否有效（1.有效，2.无效）
     */
    private Integer isEffective;

    public Integer getAppealState() {
        return appealState;
    }

    public void setAppealState(Integer appealState) {
        this.appealState = appealState;
    }

    public Integer getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Integer isEffective) {
        this.isEffective = isEffective;
    }

    public Integer getOriginId() {
        return originId;
    }

    public void setOriginId(Integer originId) {
        this.originId = originId;
    }

    public Integer getIsReally() {
        return isReally;
    }

    public void setIsReally(Integer isReally) {
        this.isReally = isReally;
    }

    public Integer getBigType() {
        return bigType;
    }

    public void setBigType(Integer bigType) {
        this.bigType = bigType;
    }

    public Integer getSmallType() {
        return smallType;
    }

    public void setSmallType(Integer smallType) {
        this.smallType = smallType;
    }

    public String getBigTypeName() {
        return bigTypeName;
    }

    public void setBigTypeName(String bigTypeName) {
        this.bigTypeName = bigTypeName;
    }

    public String getSmallTypeName() {
        return smallTypeName;
    }

    public void setSmallTypeName(String smallTypeName) {
        this.smallTypeName = smallTypeName;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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

	public Double getDeductionScore() {
		return deductionScore;
	}

	public void setDeductionScore(Double deductionScore) {
		this.deductionScore = deductionScore;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
    
}