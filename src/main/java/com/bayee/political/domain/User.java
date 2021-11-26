/**
 * 
 */
package com.bayee.political.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Transient;

/**
 * @author seanguo
 *
 */
public class User {
	private Integer id;// id
	private String headImage;// 头像
	private String policeId;// 警号
	private String name;// 警员姓名
	private String phone;// 手机号
	private Integer gender;// 1: 男，2：女
	private String idCard;// 身份证号
	private String birthday;// 生日(yyyyMMdd)
	private Integer positionLevel;
	private Integer departmentId;// 部门id
	private String departmentName;// 部门名
	private Integer departmentType;// 部门类型
	private Integer positionId;// 职位
	private String positionName;// 职位名称
	private Integer isUnitLeader;// 是否单位负责人（0否1是）
	private Integer isCadre;// 是否干部（0否1是）
	private Integer isBlock;// 0不允许1允许评价
	private String alarmDepartmentIds;// 管理部门
	private String alarmDepartmentNames;// 管理部门名称

	private Integer scorer;// 是否是记分员
	private Integer trainScorer;// 是否是单位训练创建者(1是2否)
	private Double policeHeight;

	private Double policeWeight;

	/**
	 * 健康风险是否允许全局查看（1.允许，2.不允许）
	 */
	private Integer riskHealthShowFlag;

	@Transient
	private String password;// 密码
	private String ddUserId; // 钉钉用户ID
	private Integer policeType;// 警员类型（1民警，2辅警）
	private Integer stationPostId;// 所在派出所岗位id
	private Integer participantId;// 参与评价角色id
	private Integer roleId;// 角色id
	private Integer groupId;// 群组id

	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;// 最后登录时间
	private Integer loginTimes;// 登录次数
	private Integer isActive;// 是否首次登录
	private Integer isDisable;// 是否禁用（1是0否）
	private String policeLabel;// 警员标签
	private double creditPoints;// 调休积分
	private String nation;// 民族
	private String nativePlace;// 籍贯
	private String birthPlace;// 出生地
	private String bloodType;// 血型
	private String workCompany;// 工作单位

	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
	private Date workingStartDate;// 参加工作日期
	private String workingStartDateStr;// 参加工作日期字符串
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
	private Date employmentDate;// 参加公安工作日期
	private String politicalStatus;// 政治面貌
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
	private Date joiningPartyTime;// 参加组织时间
	private Integer policePosition;//职务序列任职id
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
	private Date policePositionAssignDate;//职务序列任职时间
	@Transient
	private String residentId; // 居民身份证
	private String policeRank;// 警衔
	private String degree;// 学历
	private String major;// 学位
	private String title;// 身份或职务
	private Date joinTheCompanyTime;//入职本单位时间
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
	private Date creationDate;// 创建时间
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateDate;// 修改时间

	List<UserReward> rewards;

	List<UserEvaluation> evaluations;

	/**
	 * 入党时间
	 */
	private Date joiningPartyDate;


	/**
	 * 授衔时间
	 */
	private Date policeRankAssignDate;


	/**
	 * 职务缩写
	 */
	private String titleShort;


	/**
	 * 警种id
	 */
	private Integer kindId;

	/**
	 * 婚姻状况（1、未婚 2、已婚、3.离婚，4.丧偶、5.再婚）
	 */
	private Integer marriageStatus;


	public Integer getPositionLevel() {
		return positionLevel;
	}

	public void setPositionLevel(Integer positionLevel) {
		this.positionLevel = positionLevel;
	}

	public Date getJoiningPartyDate() {
		return joiningPartyDate;
	}

	public void setJoiningPartyDate(Date joiningPartyDate) {
		this.joiningPartyDate = joiningPartyDate;
	}

	public Date getPoliceRankAssignDate() {
		return policeRankAssignDate;
	}

	public void setPoliceRankAssignDate(Date policeRankAssignDate) {
		this.policeRankAssignDate = policeRankAssignDate;
	}

	public String getTitleShort() {
		return titleShort;
	}

	public void setTitleShort(String titleShort) {
		this.titleShort = titleShort;
	}

	public Integer getKindId() {
		return kindId;
	}

	public void setKindId(Integer kindId) {
		this.kindId = kindId;
	}

	public Integer getMarriageStatus() {
		return marriageStatus;
	}

	public void setMarriageStatus(Integer marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	public Integer getRiskHealthShowFlag() {
		return riskHealthShowFlag;
	}

	public void setRiskHealthShowFlag(Integer riskHealthShowFlag) {
		this.riskHealthShowFlag = riskHealthShowFlag;
	}

	public Integer getPolicePosition() {
		return policePosition;
	}

	public void setPolicePosition(Integer policePosition) {
		this.policePosition = policePosition;
	}

	public Date getPolicePositionAssignDate() {
		return policePositionAssignDate;
	}

	public void setPolicePositionAssignDate(Date policePositionAssignDate) {
		this.policePositionAssignDate = policePositionAssignDate;
	}

	public Date getJoinTheCompanyTime() {
		return joinTheCompanyTime;
	}

	public void setJoinTheCompanyTime(Date joinTheCompanyTime) {
		this.joinTheCompanyTime = joinTheCompanyTime;
	}

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
	 * @return the ddUserId
	 */
	public String getDdUserId() {
		return ddUserId;
	}

	/**
	 * @param ddUserId the ddUserId to set
	 */
	public void setDdUserId(String ddUserId) {
		this.ddUserId = ddUserId;
	}

	/**
	 * @return the isDisable
	 */
	public Integer getIsDisable() {
		return isDisable;
	}

	/**
	 * @param isDisable the isDisable to set
	 */
	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the creditPoints
	 */
	public double getCreditPoints() {
		return creditPoints;
	}

	/**
	 * @param creditPoints the creditPoints to set
	 */
	public void setCreditPoints(double creditPoints) {
		this.creditPoints = creditPoints;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
	 * @return the nation
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * @param nation the nation to set
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}

	/**
	 * @return the nativePlace
	 */
	public String getNativePlace() {
		return nativePlace;
	}

	/**
	 * @param nativePlace the nativePlace to set
	 */
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	/**
	 * @return the birthPlace
	 */
	public String getBirthPlace() {
		return birthPlace;
	}

	/**
	 * @param birthPlace the birthPlace to set
	 */
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	/**
	 * @return the bloodType
	 */
	public String getBloodType() {
		return bloodType;
	}

	/**
	 * @param bloodType the bloodType to set
	 */
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	/**
	 * @return the workCompany
	 */
	public String getWorkCompany() {
		return workCompany;
	}

	/**
	 * @param workCompany the workCompany to set
	 */
	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}

	/**
	 * @return the workingStartDate
	 */
	public Date getWorkingStartDate() {
		return workingStartDate;
	}

	/**
	 * @param workingStartDate the workingStartDate to set
	 */
	public void setWorkingStartDate(Date workingStartDate) {
		this.workingStartDate = workingStartDate;
	}

	/**
	 * @return the employmentDate
	 */
	public Date getEmploymentDate() {
		return employmentDate;
	}

	/**
	 * @param employmentDate the employmentDate to set
	 */
	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	/**
	 * @return the joiningPartyTime
	 */
	public Date getJoiningPartyTime() {
		return joiningPartyTime;
	}

	/**
	 * @param joiningPartyTime the joiningPartyTime to set
	 */
	public void setJoiningPartyTime(Date joiningPartyTime) {
		this.joiningPartyTime = joiningPartyTime;
	}

	/**
	 * @return the residentId
	 */
	public String getResidentId() {
		return residentId;
	}

	/**
	 * @param residentId the residentId to set
	 */
	public void setResidentId(String residentId) {
		this.residentId = residentId;
	}

	/**
	 * @return the policeRank
	 */
	public String getPoliceRank() {
		return policeRank;
	}

	/**
	 * @param policeRank the policeRank to set
	 */
	public void setPoliceRank(String policeRank) {
		this.policeRank = policeRank;
	}

	/**
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * @param degree the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the gendar
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * @param gendar the gendar to set
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * @return the politicalStatus
	 */
	public String getPoliticalStatus() {
		return politicalStatus;
	}

	/**
	 * @param politicalStatus the politicalStatus to set
	 */
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	/**
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * @return the positionId
	 */
	public Integer getPositionId() {
		return positionId;
	}

	/**
	 * @param positionId the positionId to set
	 */
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	/**
	 * @return the isUnitLeader
	 */
	public Integer getIsUnitLeader() {
		return isUnitLeader;
	}

	/**
	 * @param isUnitLeader the isUnitLeader to set
	 */
	public void setIsUnitLeader(Integer isUnitLeader) {
		this.isUnitLeader = isUnitLeader;
	}

	/**
	 * @return the isCadre
	 */
	public Integer getIsCadre() {
		return isCadre;
	}

	/**
	 * @param isCadre the isCadre to set
	 */
	public void setIsCadre(Integer isCadre) {
		this.isCadre = isCadre;
	}

	/**
	 * @return the policeType
	 */
	public Integer getPoliceType() {
		return policeType;
	}

	/**
	 * @param policeType the policeType to set
	 */
	public void setPoliceType(Integer policeType) {
		this.policeType = policeType;
	}

	/**
	 * @return the stationPostId
	 */
	public Integer getStationPostId() {
		return stationPostId;
	}

	/**
	 * @param stationPostId the stationPostId to set
	 */
	public void setStationPostId(Integer stationPostId) {
		this.stationPostId = stationPostId;
	}

	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the groupId
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the lastLoginTime
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime the lastLoginTime to set
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * @return the rewards
	 */
	public List<UserReward> getRewards() {
		return rewards;
	}

	/**
	 * @param rewards the rewards to set
	 */
	public void setRewards(List<UserReward> rewards) {
		this.rewards = rewards;
	}

	/**
	 * @return the evaluations
	 */
	public List<UserEvaluation> getEvaluations() {
		return evaluations;
	}

	/**
	 * @param evaluations the evaluations to set
	 */
	public void setEvaluations(List<UserEvaluation> evaluations) {
		this.evaluations = evaluations;
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
	 * @return the positionName
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * @return the policeLabel
	 */
	public String getPoliceLabel() {
		return policeLabel;
	}

	/**
	 * @param policeLabel the policeLabel to set
	 */
	public void setPoliceLabel(String policeLabel) {
		this.policeLabel = policeLabel;
	}

	/**
	 * @return the participantId
	 */
	public Integer getParticipantId() {
		return participantId;
	}

	/**
	 * @param participantId the participantId to set
	 */
	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}

	/**
	 * @return the departmentType
	 */
	public Integer getDepartmentType() {
		return departmentType;
	}

	/**
	 * @param departmentType the departmentType to set
	 */
	public void setDepartmentType(Integer departmentType) {
		this.departmentType = departmentType;
	}

	/**
	 * @return the loginTimes
	 */
	public Integer getLoginTimes() {
		return loginTimes;
	}

	/**
	 * @param loginTimes the loginTimes to set
	 */
	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	/**
	 * @return the isActive
	 */
	public Integer getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getAlarmDepartmentIds() {
		return alarmDepartmentIds;
	}

	public void setAlarmDepartmentIds(String alarmDepartmentIds) {
		this.alarmDepartmentIds = alarmDepartmentIds;
	}

	public String getAlarmDepartmentNames() {
		return alarmDepartmentNames;
	}

	public void setAlarmDepartmentNames(String alarmDepartmentNames) {
		this.alarmDepartmentNames = alarmDepartmentNames;
	}

	public Integer getScorer() {
		return scorer;
	}

	public void setScorer(Integer scorer) {
		this.scorer = scorer;
	}

	public String getWorkingStartDateStr() {
		return workingStartDateStr;
	}

	public void setWorkingStartDateStr(String workingStartDateStr) {
		this.workingStartDateStr = workingStartDateStr;
	}

	/**
	 * @return the policeHeight
	 */
	public Double getPoliceHeight() {
		return policeHeight;
	}

	/**
	 * @param policeHeight the policeHeight to set
	 */
	public void setPoliceHeight(Double policeHeight) {
		this.policeHeight = policeHeight;
	}

	/**
	 * @return the policeWeight
	 */
	public Double getPoliceWeight() {
		return policeWeight;
	}

	/**
	 * @param policeWeight the policeWeight to set
	 */
	public void setPoliceWeight(Double policeWeight) {
		this.policeWeight = policeWeight;
	}

	/**
	 * @return the isBlock
	 */
	public Integer getIsBlock() {
		return isBlock;
	}

	/**
	 * @param isBlock the isBlock to set
	 */
	public void setIsBlock(Integer isBlock) {
		this.isBlock = isBlock;
	}

	/**
	 * @return the trainScorer
	 */
	public Integer getTrainScorer() {
		return trainScorer;
	}

	/**
	 * @param trainScorer the trainScorer to set
	 */
	public void setTrainScorer(Integer trainScorer) {
		this.trainScorer = trainScorer;
	}

}
