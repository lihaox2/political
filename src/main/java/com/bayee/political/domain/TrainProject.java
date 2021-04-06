package com.bayee.political.domain;

import java.util.Date;

public class TrainProject {
	private Integer id;

	private String name;

	private Integer type;

	private Integer unitId;

	/**
	 * 是否是U型射击
	 */
	private Integer isU;

	private Integer sort;// 排序方式（1升序2降序）
	
	private Integer enterNum;// 已录项目数

	private Integer totalEnterNum;// 总项目数

	private Date creationDate;

	private Date updateDate;

	private String unitName;
	
	private String statusName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsU() {
		return isU;
	}

	public void setIsU(Integer isU) {
		this.isU = isU;
	}

	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @return the enterNum
	 */
	public Integer getEnterNum() {
		return enterNum;
	}

	/**
	 * @param enterNum the enterNum to set
	 */
	public void setEnterNum(Integer enterNum) {
		this.enterNum = enterNum;
	}

	/**
	 * @return the totalEnterNum
	 */
	public Integer getTotalEnterNum() {
		return totalEnterNum;
	}

	/**
	 * @param totalEnterNum the totalEnterNum to set
	 */
	public void setTotalEnterNum(Integer totalEnterNum) {
		this.totalEnterNum = totalEnterNum;
	}

}