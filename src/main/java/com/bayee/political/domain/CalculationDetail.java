package com.bayee.political.domain;

import java.util.Date;

public class CalculationDetail {
    private Integer id;

    private Integer policeType;

    private Integer policeStationId;
    
    private String policeStationName;

    private Integer caseId;

    private Integer caseNum;

    private Date creationDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoliceType() {
        return policeType;
    }

    public void setPoliceType(Integer policeType) {
        this.policeType = policeType;
    }

    public Integer getPoliceStationId() {
        return policeStationId;
    }

    public void setPoliceStationId(Integer policeStationId) {
        this.policeStationId = policeStationId;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(Integer caseNum) {
        this.caseNum = caseNum;
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
	 * @return the policeStationName
	 */
	public String getPoliceStationName() {
		return policeStationName;
	}

	/**
	 * @param policeStationName the policeStationName to set
	 */
	public void setPoliceStationName(String policeStationName) {
		this.policeStationName = policeStationName;
	}
    
}