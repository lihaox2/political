package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

//评价参与人
public class EvaluateTaskParticipant {

	private Integer id;

	private Integer taskId;

	private Integer type;

	private String policeId;
	
	private String policeName;

	private String headImage;// 头像

	private Integer departmentId;// 部门id

	private String departmentName;// 部门名称

	private Integer positionId;// 职位id

	private String positionName;// 职位名称
	
	private Integer num;// 数量

	private String objectId;

	private String objectName;

	private String description;

	private Integer rankId;

	private String rankName;

	private Integer rankDetailId;

	private String detailName;

	private Double score;

	private Integer rankNum;// 得分排名

	private Double proportion;// 评价参与人评分占比

	private Integer participantId; // 评价参与人id
	
	private String participantName; // 评价参与人名称

	private Integer status;

	private String reason;
	
	private Integer operation;
	
	private Integer isIgnore; // 是否忽略（1忽略0不忽略null不忽略）

	private List<EvaluateRankDetail> evaluateRankDetailList;

	private Date creationDate;

	private Date updateDate;

	private String creationDateStr;

	private String updateDateStr;
	
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the rankNum
	 */
	public Integer getRankNum() {
		return rankNum;
	}

	/**
	 * @param rankNum the rankNum to set
	 */
	public void setRankNum(Integer rankNum) {
		this.rankNum = rankNum;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
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
		this.policeId = policeId;
	}

	/**
	 * @return the objectId
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @return the evaluateRankDetailList
	 */
	public List<EvaluateRankDetail> getEvaluateRankDetailList() {
		return evaluateRankDetailList;
	}

	/**
	 * @param evaluateRankDetailList the evaluateRankDetailList to set
	 */
	public void setEvaluateRankDetailList(List<EvaluateRankDetail> evaluateRankDetailList) {
		this.evaluateRankDetailList = evaluateRankDetailList;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Integer getRankId() {
		return rankId;
	}

	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason == null ? null : reason.trim();
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
	 * @return the objectName
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * @param objectName the objectName to set
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
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
	 * @return the rankName
	 */
	public String getRankName() {
		return rankName;
	}

	/**
	 * @param rankName the rankName to set
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	/**
	 * @return the rankDetailId
	 */
	public Integer getRankDetailId() {
		return rankDetailId;
	}

	/**
	 * @param rankDetailId the rankDetailId to set
	 */
	public void setRankDetailId(Integer rankDetailId) {
		this.rankDetailId = rankDetailId;
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

	public String getCreationDateStr() {
		return creationDateStr;
	}

	public void setCreationDateStr(String creationDateStr) {
		this.creationDateStr = creationDateStr;
	}

	public String getUpdateDateStr() {
		return updateDateStr;
	}

	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}

	/**
	 * @return the proportion
	 */
	public Double getProportion() {
		return proportion;
	}

	/**
	 * @param proportion the proportion to set
	 */
	public void setProportion(Double proportion) {
		this.proportion = proportion;
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
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * @return the participantName
	 */
	public String getParticipantName() {
		return participantName;
	}

	/**
	 * @param participantName the participantName to set
	 */
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	/**
	 * @return the operation
	 */
	public Integer getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(Integer operation) {
		this.operation = operation;
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
	 * @return the isIgnore
	 */
	public Integer getIsIgnore() {
		return isIgnore;
	}

	/**
	 * @param isIgnore the isIgnore to set
	 */
	public void setIsIgnore(Integer isIgnore) {
		this.isIgnore = isIgnore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}