package com.bayee.political.domain;

import java.util.Date;

public class TrainFirearm {
	private Integer id;

	private Integer type;// (训练项目归属)1单位2分局

	private Integer departmentId;
	
	private String departmentName;

	private String name;

	private String place;

	private Date registrationStartDate;

	private Date registrationEndDate;

	private Date trainStartDate;

	private Date trainEndDate;

	private Integer trainFirearmType;

	//是否全部人员
	private Integer isAll;
	
	private String involvementPoliceIds;
	
	private String involvementPoliceNames;

	private String scorerPoliceId;

	private Integer signUpStatus;// 状态(1报名未开始2报名进行中3报名已结束)

	private Integer status;// 状态(1训练未开始2训练进行中3训练已结束)

	private String coverImg;

	private String trainContent;

	private String trainImg;

	private Integer isSubmit;
	
	private Date submitDate;
	
	private Integer symbol;// 1>= 2> 3<= 4< 5=

	private Integer isLimit;// 是否限制(0否1是)

	private Integer limitType;// 限制类型（1按比例2按人数）

	private Double limitNum;// 限制人数或（比例1-100）

	private Date creationDate;

	private Date updateDate;

	private String trainFirearmTypeName;
	
	private String projectName;
	
	private String policeName;

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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place == null ? null : place.trim();
	}

	public Date getRegistrationStartDate() {
		return registrationStartDate;
	}

	public void setRegistrationStartDate(Date registrationStartDate) {
		this.registrationStartDate = registrationStartDate;
	}

	public Date getRegistrationEndDate() {
		return registrationEndDate;
	}

	public void setRegistrationEndDate(Date registrationEndDate) {
		this.registrationEndDate = registrationEndDate;
	}

	public Date getTrainStartDate() {
		return trainStartDate;
	}

	public void setTrainStartDate(Date trainStartDate) {
		this.trainStartDate = trainStartDate;
	}

	public Date getTrainEndDate() {
		return trainEndDate;
	}

	public void setTrainEndDate(Date trainEndDate) {
		this.trainEndDate = trainEndDate;
	}

	/**
	 * @return the trainFirearmType
	 */
	public Integer getTrainFirearmType() {
		return trainFirearmType;
	}

	/**
	 * @param trainFirearmType the trainFirearmType to set
	 */
	public void setTrainFirearmType(Integer trainFirearmType) {
		this.trainFirearmType = trainFirearmType;
	}

	public String getInvolvementPoliceIds() {
		return involvementPoliceIds;
	}

	public void setInvolvementPoliceIds(String involvementPoliceIds) {
		this.involvementPoliceIds = involvementPoliceIds == null ? null : involvementPoliceIds.trim();
	}

	public String getScorerPoliceId() {
		return scorerPoliceId;
	}

	public void setScorerPoliceId(String scorerPoliceId) {
		this.scorerPoliceId = scorerPoliceId == null ? null : scorerPoliceId.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg == null ? null : coverImg.trim();
	}

	public String getTrainImg() {
		return trainImg;
	}

	public void setTrainImg(String trainImg) {
		this.trainImg = trainImg == null ? null : trainImg.trim();
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

	public String getTrainContent() {
		return trainContent;
	}

	public void setTrainContent(String trainContent) {
		this.trainContent = trainContent == null ? null : trainContent.trim();
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
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

	public String getTrainFirearmTypeName() {
		return trainFirearmTypeName;
	}

	public void setTrainFirearmTypeName(String trainFirearmTypeName) {
		this.trainFirearmTypeName = trainFirearmTypeName;
	}

	/**
	 * @return the isSubmit
	 */
	public Integer getIsSubmit() {
		return isSubmit;
	}

	/**
	 * @param isSubmit the isSubmit to set
	 */
	public void setIsSubmit(Integer isSubmit) {
		this.isSubmit = isSubmit;
	}

	/**
	 * @return the signUpStatus
	 */
	public Integer getSignUpStatus() {
		return signUpStatus;
	}

	/**
	 * @param signUpStatus the signUpStatus to set
	 */
	public void setSignUpStatus(Integer signUpStatus) {
		this.signUpStatus = signUpStatus;
	}

	/**
	 * @return the symbol
	 */
	public Integer getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(Integer symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the isLimit
	 */
	public Integer getIsLimit() {
		return isLimit;
	}

	/**
	 * @param isLimit the isLimit to set
	 */
	public void setIsLimit(Integer isLimit) {
		this.isLimit = isLimit;
	}

	/**
	 * @return the limitType
	 */
	public Integer getLimitType() {
		return limitType;
	}

	/**
	 * @param limitType the limitType to set
	 */
	public void setLimitType(Integer limitType) {
		this.limitType = limitType;
	}

	/**
	 * @return the limitNum
	 */
	public Double getLimitNum() {
		return limitNum;
	}

	/**
	 * @param limitNum the limitNum to set
	 */
	public void setLimitNum(Double limitNum) {
		this.limitNum = limitNum;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPoliceName() {
		return policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	public String getInvolvementPoliceNames() {
		return involvementPoliceNames;
	}

	public void setInvolvementPoliceNames(String involvementPoliceNames) {
		this.involvementPoliceNames = involvementPoliceNames;
	}

	public Integer getIsAll() {
		return isAll;
	}

	public void setIsAll(Integer isAll) {
		this.isAll = isAll;
	}

	/**
	 * @return the submitDate
	 */
	public Date getSubmitDate() {
		return submitDate;
	}

	/**
	 * @param submitDate the submitDate to set
	 */
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}