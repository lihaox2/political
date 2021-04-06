package com.bayee.political.domain;

import java.util.Date;

public class Calculation {
	
    private Integer id;

    private Integer policeType;

    private Integer policeStationId;
    
    private String policeStationName;

    private int stationPostId;

    private Integer actualPoliceNum;

    private Integer getPoliceNum;

    private Integer differPoliceNum;

    private Integer isLack;
    
    private String lackStr;
    
    private int num;

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

    public Integer getActualPoliceNum() {
        return actualPoliceNum;
    }

    public void setActualPoliceNum(Integer actualPoliceNum) {
        this.actualPoliceNum = actualPoliceNum;
    }

    public Integer getGetPoliceNum() {
        return getPoliceNum;
    }

    public void setGetPoliceNum(Integer getPoliceNum) {
        this.getPoliceNum = getPoliceNum;
    }

    public Integer getDifferPoliceNum() {
        return differPoliceNum;
    }

    public void setDifferPoliceNum(Integer differPoliceNum) {
        this.differPoliceNum = differPoliceNum;
    }

    public Integer getIsLack() {
        return isLack;
    }

    public void setIsLack(Integer isLack) {
        this.isLack = isLack;
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
	 * @return the stationPostId
	 */
	public int getStationPostId() {
		return stationPostId;
	}

	/**
	 * @param stationPostId the stationPostId to set
	 */
	public void setStationPostId(int stationPostId) {
		this.stationPostId = stationPostId;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
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

	/**
	 * @return the lackStr
	 */
	public String getLackStr() {
		return lackStr;
	}

	/**
	 * @param lackStr the lackStr to set
	 */
	public void setLackStr(String lackStr) {
		this.lackStr = lackStr;
	}
    
}