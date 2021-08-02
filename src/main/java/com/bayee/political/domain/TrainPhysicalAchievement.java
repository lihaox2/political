package com.bayee.political.domain;

import java.util.Date;

public class TrainPhysicalAchievement {
	private Integer id;

	private Integer trainPhysicalId;

	private Integer type;

	private String trainPhysicalName;// 训练名称

	private String headImage;// 头像

	private String policeId;
	
	private String name;
	
	private String departmentName;
	
	private Double policeHeight;
	
	private Double policeWeight;

	private String groupName;// 组名

	private String projectName;// 项目名
	
	private Date trainStartDate;// 训练开始时间

	private Date trainEndDate;// 训练结束时间

	private Date registrationDate;

	private Date achievementDate;
	
	private Date signDate;// 签到时间

	private Integer trainGroupId;
	
	private String qrCode;
	
	private Integer achievementGrade;

	private Integer isSign;

    /**
     * 是否免测（1.免测，2.正常测试）
     */
    private Integer isTestFree;
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getAchievementDate() {
		return achievementDate;
	}

	public void setAchievementDate(Date achievementDate) {
		this.achievementDate = achievementDate;
	}

	public Integer getTrainPhysicalId() {
		return trainPhysicalId;
	}

	public void setTrainPhysicalId(Integer trainPhysicalId) {
		this.trainPhysicalId = trainPhysicalId;
	}

	public Integer getTrainGroupId() {
		return trainGroupId;
	}

	public void setTrainGroupId(Integer trainGroupId) {
		this.trainGroupId = trainGroupId;
	}

	public Integer getIsSign() {
		return isSign;
	}

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
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
	 * @return the trainPhysicalName
	 */
	public String getTrainPhysicalName() {
		return trainPhysicalName;
	}

	/**
	 * @param trainPhysicalName the trainPhysicalName to set
	 */
	public void setTrainPhysicalName(String trainPhysicalName) {
		this.trainPhysicalName = trainPhysicalName;
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
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the trainEndDate
	 */
	public Date getTrainEndDate() {
		return trainEndDate;
	}

	/**
	 * @param trainEndDate the trainEndDate to set
	 */
	public void setTrainEndDate(Date trainEndDate) {
		this.trainEndDate = trainEndDate;
	}

	/**
	 * @return the qrCode
	 */
	public String getQrCode() {
		return qrCode;
	}

	/**
	 * @param qrCode the qrCode to set
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Double getPoliceHeight() {
		return policeHeight;
	}

	public void setPoliceHeight(Double policeHeight) {
		this.policeHeight = policeHeight;
	}

	public Double getPoliceWeight() {
		return policeWeight;
	}

	public void setPoliceWeight(Double policeWeight) {
		this.policeWeight = policeWeight;
	}

	/**
	 * @return the achievementGrade
	 */
	public Integer getAchievementGrade() {
		return achievementGrade;
	}

	/**
	 * @param achievementGrade the achievementGrade to set
	 */
	public void setAchievementGrade(Integer achievementGrade) {
		this.achievementGrade = achievementGrade;
	}

	/**
	 * @return the signDate
	 */
	public Date getSignDate() {
		return signDate;
	}

	/**
	 * @param signDate the signDate to set
	 */
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the trainStartDate
	 */
	public Date getTrainStartDate() {
		return trainStartDate;
	}

	/**
	 * @param trainStartDate the trainStartDate to set
	 */
	public void setTrainStartDate(Date trainStartDate) {
		this.trainStartDate = trainStartDate;
	}

}