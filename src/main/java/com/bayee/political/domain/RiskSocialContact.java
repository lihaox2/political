package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

public class RiskSocialContact {
    private Integer id;

    private String policeId;

    private Double indexNum;

    private Integer num;

    private Date creationDate;

    private Date updateDate;
    
    private List<RiskSocialContactRecord> recordList;
    
    private List<ScreenDoubeChart> list;

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
        return indexNum;
    }

    public void setIndexNum(Double indexNum) {
        this.indexNum = indexNum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

	/**
	 * @return the recordList
	 */
	public List<RiskSocialContactRecord> getRecordList() {
		return recordList;
	}

	/**
	 * @param recordList the recordList to set
	 */
	public void setRecordList(List<RiskSocialContactRecord> recordList) {
		this.recordList = recordList;
	}
    
}