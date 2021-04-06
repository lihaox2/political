package com.bayee.political.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AlarmTalk {
	private Integer id;

	private Integer alarmRecordId;

	private String title;

	private Integer alarmType;// 约谈预警类型（1考核预警2出入境预警）

	private Integer talkType;

	private String policeId;

	private String policeName;

	private String headImage;// 头像

	private Integer departmentId;

	private String departmentName;

	private Double score;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startTime;

	private String strStartTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;

	private String content;

	private String message;

	private String feedback;

	private String reason;

	private String hostId;

	private String hostName;

	private String hostHeadImage;

	private String positionName;

	private Integer status;

	private Integer isReceive;

	private Integer viewType;

	private Date creationDate;

	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public Integer getTalkType() {
		return talkType;
	}

	public void setTalkType(Integer talkType) {
		this.talkType = talkType;
	}

	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId == null ? null : policeId.trim();
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId == null ? null : hostId.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return the isReceive
	 */
	public Integer getIsReceive() {
		return isReceive;
	}

	/**
	 * @param isReceive the isReceive to set
	 */
	public void setIsReceive(Integer isReceive) {
		this.isReceive = isReceive;
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the hostHeadImage
	 */
	public String getHostHeadImage() {
		return hostHeadImage;
	}

	/**
	 * @param hostHeadImage the hostHeadImage to set
	 */
	public void setHostHeadImage(String hostHeadImage) {
		this.hostHeadImage = hostHeadImage;
	}

	/**
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback the feedback to set
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
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

	/**
	 * @return the viewType
	 */
	public Integer getViewType() {
		return viewType;
	}

	/**
	 * @param viewType the viewType to set
	 */
	public void setViewType(Integer viewType) {
		this.viewType = viewType;
	}

	/**
	 * @return the strStartTime
	 */
	public String getStrStartTime() {
		return strStartTime;
	}

	/**
	 * @param strStartTime the strStartTime to set
	 */
	public void setStrStartTime(String strStartTime) {
		this.strStartTime = strStartTime;
	}

	/**
	 * @return the alarmType
	 */
	public Integer getAlarmType() {
		return alarmType;
	}

	/**
	 * @param alarmType the alarmType to set
	 */
	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}

	/**
	 * @return the alarmRecordId
	 */
	public Integer getAlarmRecordId() {
		return alarmRecordId;
	}

	/**
	 * @param alarmRecordId the alarmRecordId to set
	 */
	public void setAlarmRecordId(Integer alarmRecordId) {
		this.alarmRecordId = alarmRecordId;
	}

	/**
	 * @return the score
	 */
	public Double getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Double score) {
		this.score = score;
	}

}