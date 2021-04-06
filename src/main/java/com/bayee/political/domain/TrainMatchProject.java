package com.bayee.political.domain;

import java.util.Date;

public class TrainMatchProject {
	private Integer id;

	private String name;

	private Integer type;
	
	private Integer nature;//赛事类型

	private String typeName;

	private Integer unitId;// 计量单位id

	private String unitName;// 计量单位名称
	
	private Integer isMoreUnit;
	
	private Integer sort;//排序规则
	
	private Integer gender;

	private Date creationDate;

	private Date updateDate;

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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the unitId
	 */
	public Integer getUnitId() {
		return unitId;
	}

	/**
	 * @param unitId the unitId to set
	 */
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * @return the isMoreUnit
	 */
	public Integer getIsMoreUnit() {
		return isMoreUnit;
	}

	/**
	 * @param isMoreUnit the isMoreUnit to set
	 */
	public void setIsMoreUnit(Integer isMoreUnit) {
		this.isMoreUnit = isMoreUnit;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getNature() {
		return nature;
	}

	public void setNature(Integer nature) {
		this.nature = nature;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}