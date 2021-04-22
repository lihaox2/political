package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

public class RiskCase {
    private Integer id;

    private String policeId;

    private Double indexNum;

    private Double abilityNum;

    private Double lawEnforcementNum;

    private Double testNum;
    
    private List<ScreenDoubeChart> list;

    private Date creationDate;

    private Date updateDate;

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

    public Double getIndexNum() {
        if (null == indexNum) {
            return null;
        }
        return Math.min(indexNum, 20);
    }

    public void setIndexNum(Double indexNum) {
        this.indexNum = indexNum;
    }

    public Double getAbilityNum() {
        return abilityNum;
    }

    public void setAbilityNum(Double abilityNum) {
        this.abilityNum = abilityNum;
    }

    public Double getLawEnforcementNum() {
        return lawEnforcementNum;
    }

    public void setLawEnforcementNum(Double lawEnforcementNum) {
        this.lawEnforcementNum = lawEnforcementNum;
    }

    public Double getTestNum() {
        return testNum;
    }

    public void setTestNum(Double testNum) {
        this.testNum = testNum;
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
	 * @return the list
	 */
	public List<ScreenDoubeChart> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<ScreenDoubeChart> list) {
		this.list = list;
	}
    
}