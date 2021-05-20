package com.bayee.political.domain;

import java.util.Date;

public class RiskDutyDealPoliceRecord {
    private Integer id;

    private String policeId;

    private Integer type;

    /**
     * 警情类别
     */
    private Integer informationId;

    /**
     * 错误类别
     */
    private Integer errorId;

    /**
     * 警单号
     */
    private String policeListCode;

    /**
     * 辖区
     */
    private String jurisdiction;

    /**
     * 警单详情
     */
    private String policeListInfo;

    /**
     * 是否核实（1 已核实，0 未核实）
     */
    private Integer isVerified;

    private String content;

    private Date inputTime;
    
    private Double deductionScore;

    private Date creationDate;

    private Date updateDate;

    private String typeName;

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public Integer getErrorId() {
        return errorId;
    }

    public void setErrorId(Integer errorId) {
        this.errorId = errorId;
    }

    public String getPoliceListCode() {
        return policeListCode;
    }

    public void setPoliceListCode(String policeListCode) {
        this.policeListCode = policeListCode;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getPoliceListInfo() {
        return policeListInfo;
    }

    public void setPoliceListInfo(String policeListInfo) {
        this.policeListInfo = policeListInfo;
    }

    public Integer getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
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
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the deductionScore
	 */
	public Double getDeductionScore() {
		return deductionScore;
	}

	/**
	 * @param deductionScore the deductionScore to set
	 */
	public void setDeductionScore(Double deductionScore) {
		this.deductionScore = deductionScore;
	}
    
}