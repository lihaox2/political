package com.bayee.political.domain;

import java.util.Date;

public class RiskDrinkRecord {
    private Integer id;

    private Integer type;

    private String policeId;

    private Integer isWorkDay;
    
    private Date drinkDate;

    private Date creationDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId == null ? null : policeId.trim();
    }

    public Integer getIsWorkDay() {
        return isWorkDay;
    }

    public void setIsWorkDay(Integer isWorkDay) {
        this.isWorkDay = isWorkDay;
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

	public Date getDrinkDate() {
		return drinkDate;
	}

	public void setDrinkDate(Date drinkDate) {
		this.drinkDate = drinkDate;
	}
    
}