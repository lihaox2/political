package com.bayee.political.domain;

import java.util.Date;

public class LeaveCompensatoryAlarm {
	private Integer id;

	private Integer ruleId;

	private String ruleName;

	private Integer type;

	private String typeName;

	private String headImage;

	private String policeId;

	private String name;

	private Integer departmentId;

	private String departmentName;

	private Integer positionId;

	private String positionName;

	private String gender;

	private Integer age;

	private String year;

	private Double workDays;

	private Double days;

	private String colour;

	private Integer isRest;

	private Integer identification;

	private Integer isDeal;// 是否处理(0未处理1已处理)
	
	private Integer timeChange;
	
	private String reason;
	
	private String creationDateStr;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName == null ? null : departmentName.trim();
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName == null ? null : positionName.trim();
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year == null ? null : year.trim();
	}

	public Double getWorkDays() {
		return workDays;
	}

	public void setWorkDays(Double workDays) {
		this.workDays = workDays;
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
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * @param colour the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * @return the headImage
	 */
	public String getHeadImage() {
		return headImage;
	}

	/**
	 * @param headImage the headImage to set
	 */
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the isRest
	 */
	public Integer getIsRest() {
		return isRest;
	}

	/**
	 * @param isRest the isRest to set
	 */
	public void setIsRest(Integer isRest) {
		this.isRest = isRest;
	}

	/**
	 * @return the ruleId
	 */
	public Integer getRuleId() {
		return ruleId;
	}

	/**
	 * @param ruleId the ruleId to set
	 */
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * @return the ruleName
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * @param ruleName the ruleName to set
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * @return the identification
	 */
	public Integer getIdentification() {
		return identification;
	}

	/**
	 * @param identification the identification to set
	 */
	public void setIdentification(Integer identification) {
		this.identification = identification;
	}

	/**
	 * @return the days
	 */
	public Double getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(Double days) {
		this.days = days;
	}

	/**
	 * @return the isDeal
	 */
	public Integer getIsDeal() {
		return isDeal;
	}

	/**
	 * @param isDeal the isDeal to set
	 */
	public void setIsDeal(Integer isDeal) {
		this.isDeal = isDeal;
	}

	/**
	 * @return the timeChange
	 */
	public Integer getTimeChange() {
		return timeChange;
	}

	/**
	 * @param timeChange the timeChange to set
	 */
	public void setTimeChange(Integer timeChange) {
		this.timeChange = timeChange;
	}

	/**
	 * @return the creationDateStr
	 */
	public String getCreationDateStr() {
		return creationDateStr;
	}

	/**
	 * @param creationDateStr the creationDateStr to set
	 */
	public void setCreationDateStr(String creationDateStr) {
		this.creationDateStr = creationDateStr;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
}