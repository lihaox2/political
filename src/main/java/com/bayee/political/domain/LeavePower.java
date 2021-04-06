package com.bayee.political.domain;

import java.util.Date;

public class LeavePower {
	private Integer id;

	private Integer moduleId;// 模块id（1AI预警2AI休假）

	private String checkerId;// 审核人

	private String checkerName;// 审核人名称

	private Integer departmentId;// 审核人单位

	private String departmentName;// 审核人单位名称

	private String departmentObjectIds;// 被审核单位ids

	private String departmentObjectNames;// 被审核单位名字s

	private String policeObjectIds;// 被审核民警ids

	private String policeObjectNames;// 被审核民警名字s

	private String policeObjectIdsArr; // "-"连接的各部门人员
	private String policeObjectNamesArr; // "-"连接的各部门人员姓名

	private Integer conditions;// 1约谈对象是预警人员2约谈对象既可以是预警人员，也可以是非预警人员

	private Date creationDate;

	private Date updateDate;

	private Integer count;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the moduleId
	 */
	public Integer getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return the checkerId
	 */
	public String getCheckerId() {
		return checkerId;
	}

	/**
	 * @param checkerId the checkerId to set
	 */
	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}

	/**
	 * @return the checkerName
	 */
	public String getCheckerName() {
		return checkerName;
	}

	/**
	 * @param checkerName the checkerName to set
	 */
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	/**
	 * @return the departmentId
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the departmentObjectIds
	 */
	public String getDepartmentObjectIds() {
		return departmentObjectIds;
	}

	/**
	 * @param departmentObjectIds the departmentObjectIds to set
	 */
	public void setDepartmentObjectIds(String departmentObjectIds) {
		this.departmentObjectIds = departmentObjectIds;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	public String getDepartmentObjectNames() {
		return departmentObjectNames;
	}

	public void setDepartmentObjectNames(String departmentObjectNames) {
		this.departmentObjectNames = departmentObjectNames;
	}

	public String getPoliceObjectIds() {
		return policeObjectIds;
	}

	public void setPoliceObjectIds(String policeObjectIds) {
		this.policeObjectIds = policeObjectIds;
	}

	public String getPoliceObjectNames() {
		return policeObjectNames;
	}

	public void setPoliceObjectNames(String policeObjectNames) {
		this.policeObjectNames = policeObjectNames;
	}

	public String getPoliceObjectIdsArr() {
		return policeObjectIdsArr;
	}

	public void setPoliceObjectIdsArr(String policeObjectIdsArr) {
		this.policeObjectIdsArr = policeObjectIdsArr;
	}

	public String getPoliceObjectNamesArr() {
		return policeObjectNamesArr;
	}

	public void setPoliceObjectNamesArr(String policeObjectNamesArr) {
		this.policeObjectNamesArr = policeObjectNamesArr;
	}

	/**
	 * @return the condition
	 */
	public Integer getConditions() {
		return conditions;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setConditions(Integer conditions) {
		this.conditions = conditions;
	}


}