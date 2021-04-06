package com.bayee.political.domain;

import java.util.Date;

public class EvaluateRankDetail {
    private Integer id;

    private Integer rankId;

    private String detailName;

    private Double detailValue;

    private Integer isMax;

    private Integer maxNum;
    
    private Integer isSelect;
    
    private int num;
    
    private int totalNum;

    private Date creationDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName == null ? null : detailName.trim();
    }

    public Double getDetailValue() {
        return detailValue;
    }

    public void setDetailValue(Double detailValue) {
        this.detailValue = detailValue;
    }

    public Integer getIsMax() {
        return isMax;
    }

    public void setIsMax(Integer isMax) {
        this.isMax = isMax;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
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
	 * @return the totalNum
	 */
	public int getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * @return the isSelect
	 */
	public Integer getIsSelect() {
		return isSelect;
	}

	/**
	 * @param isSelect the isSelect to set
	 */
	public void setIsSelect(Integer isSelect) {
		this.isSelect = isSelect;
	}
    
}