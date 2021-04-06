package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

public class TrainPhysical {
	private Integer id;

	private Integer objectId;// 1综合体能2枪械

	private String objectType;

	private Integer type;// (训练项目归属)1单位2分局

	private Integer departmentId;
	
	private String departmentName;

	private String name;

	private String place;

	private Integer policeNum;// 参与人数

	private String headImage;// 头像

	private String policeId;

	private String policeName;

	private Date registrationStartDate;

	private Date registrationEndDate;

	private Date trainStartDate;

	private Date trainEndDate;

	private String trainGroupIds;

	private String trainGroupNames;

	private Integer trainFirearmType;

	private String trainFirearmTypeName;

	private Integer isU;// 是否是U型靶

	private Integer signUpStatus;// 状态(1报名未开始2报名进行中3报名已结束)

	private Integer status;// 状态(1训练未开始2训练进行中3训练已结束)

	private String achievementName;

	private String qrCode;// 二维码路径

	private String scorerPoliceId;// 记分员

	private String scorerName;

	private String coverImg;

	private String trainContent;

	private String trainImg;

	private Integer isWholeSign;// 是否完全签到（0否1是）

	private Integer isSubmit;// 是否提交（0否1是）
	
	private Date submitDate;

	private Integer symbol;// 1>= 2> 3<= 4< 5=

	private Integer isLimit;// 是否限制(0否1是)

	private Integer limitType;// 限制类型（1按比例2按人数）

	private Double limitNum;// 限制人数或（比例1-100）

	private Date creationDate;

	private Integer timeChange;

	private Integer startTimeChange;

	private String creationDateStr;

	private Date updateDate;

	private List<CalculationChart> headImageList;

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

	public String getTrainGroupIds() {
		return trainGroupIds;
	}

	public void setTrainGroupIds(String trainGroupIds) {
		this.trainGroupIds = trainGroupIds == null ? null : trainGroupIds.trim();
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
	 * @return the policeNum
	 */
	public Integer getPoliceNum() {
		return policeNum;
	}

	/**
	 * @param policeNum the policeNum to set
	 */
	public void setPoliceNum(Integer policeNum) {
		this.policeNum = policeNum;
	}

	/**
	 * @return the trainGroupNames
	 */
	public String getTrainGroupNames() {
		return trainGroupNames;
	}

	/**
	 * @param trainGroupNames the trainGroupNames to set
	 */
	public void setTrainGroupNames(String trainGroupNames) {
		this.trainGroupNames = trainGroupNames;
	}

	/**
	 * @return the objectType
	 */
	public String getObjectType() {
		return objectType;
	}

	/**
	 * @param objectType the objectType to set
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
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
	 * @return the objectId
	 */
	public Integer getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
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

	/**
	 * @return the trainFirearmTypeName
	 */
	public String getTrainFirearmTypeName() {
		return trainFirearmTypeName;
	}

	/**
	 * @param trainFirearmTypeName the trainFirearmTypeName to set
	 */
	public void setTrainFirearmTypeName(String trainFirearmTypeName) {
		this.trainFirearmTypeName = trainFirearmTypeName;
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

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
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
	 * @return the policeId
	 */
	public String getPoliceId() {
		return policeId;
	}

	/**
	 * @param policeId the policeId to set
	 */
	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}

	/**
	 * @return the policeName
	 */
	public String getPoliceName() {
		return policeName;
	}

	/**
	 * @param policeName the policeName to set
	 */
	public void setPoliceName(String policeName) {
		this.policeName = policeName;
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
	 * @return the scorerPoliceId
	 */
	public String getScorerPoliceId() {
		return scorerPoliceId;
	}

	/**
	 * @param scorerPoliceId the scorerPoliceId to set
	 */
	public void setScorerPoliceId(String scorerPoliceId) {
		this.scorerPoliceId = scorerPoliceId;
	}

	public String getScorerName() {
		return scorerName;
	}

	public void setScorerName(String scorerName) {
		this.scorerName = scorerName;
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

	/**
	 * @return the isU
	 */
	public Integer getIsU() {
		return isU;
	}

	/**
	 * @param isU the isU to set
	 */
	public void setIsU(Integer isU) {
		this.isU = isU;
	}

	/**
	 * @return the achievementName
	 */
	public String getAchievementName() {
		return achievementName;
	}

	/**
	 * @param achievementName the achievementName to set
	 */
	public void setAchievementName(String achievementName) {
		this.achievementName = achievementName;
	}

	/**
	 * @return the headImageList
	 */
	public List<CalculationChart> getHeadImageList() {
		return headImageList;
	}

	/**
	 * @param headImageList the headImageList to set
	 */
	public void setHeadImageList(List<CalculationChart> headImageList) {
		this.headImageList = headImageList;
	}

	/**
	 * @return the startTimeChange
	 */
	public Integer getStartTimeChange() {
		return startTimeChange;
	}

	/**
	 * @param startTimeChange the startTimeChange to set
	 */
	public void setStartTimeChange(Integer startTimeChange) {
		this.startTimeChange = startTimeChange;
	}

	/**
	 * @return the isWholeSign
	 */
	public Integer getIsWholeSign() {
		return isWholeSign;
	}

	/**
	 * @param isWholeSign the isWholeSign to set
	 */
	public void setIsWholeSign(Integer isWholeSign) {
		this.isWholeSign = isWholeSign;
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