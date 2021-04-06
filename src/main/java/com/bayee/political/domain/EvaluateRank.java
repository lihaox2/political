package com.bayee.political.domain;

import java.util.Date;

public class EvaluateRank {
	
	private Integer id;

    private String rankName;

    private Integer type;

    private Integer childType;
    
    private String detailName;

    private Double detailValue;

    private Integer isMax;

    private Integer maxNum;

    private String remarks;

    private Date creationDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName == null ? null : rankName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getChildType() {
        return childType;
    }

    public void setChildType(Integer childType) {
        this.childType = childType;
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

	/**
	 * @return the detailName
	 */
	public String getDetailName() {
		return detailName;
	}

	/**
	 * @param detailName the detailName to set
	 */
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	/**
	 * @return the detailValue
	 */
	public Double getDetailValue() {
		return detailValue;
	}

	/**
	 * @param detailValue the detailValue to set
	 */
	public void setDetailValue(Double detailValue) {
		this.detailValue = detailValue;
	}

	/**
	 * @return the isMax
	 */
	public Integer getIsMax() {
		return isMax;
	}

	/**
	 * @param isMax the isMax to set
	 */
	public void setIsMax(Integer isMax) {
		this.isMax = isMax;
	}

	/**
	 * @return the maxNum
	 */
	public Integer getMaxNum() {
		return maxNum;
	}

	/**
	 * @param maxNum the maxNum to set
	 */
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
    
}