package com.bayee.political.domain;

import java.util.Date;

public class EvaluateTask {

	private Integer id;

	private String policeId;

	private String name;

	private Integer type;

	private Date startTime;

	private String startTimeStr;// 字符串时间

	private Date endTime;

	private String endTimeStr;// 字符串时间
	
	private Date overTime;//任务完成时间

	private Integer timeChange;

	private String timeName;

	private Integer templateId;

	private Integer rankId;

	private Integer isTemplate;

	private Integer process;

	private Integer status;

	private Integer operation;

	private Integer totalNum;// 评价参与人总数

	private Integer overNum;// 已评价参与人总数

	private Double completionRate;// 已评价参与人占比

	private Integer objectNum;// 评价对象数量
	
	private Integer operationStatus;//判断当前任务是否存在审批评价，0不存在1存在

	private Date creationDate;

	private Date updateDate;
	
	private String strCreationDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	/**
	 * @return the completionRate
	 */
	public Double getCompletionRate() {
		return completionRate;
	}

	/**
	 * @param completionRate the completionRate to set
	 */
	public void setCompletionRate(Double completionRate) {
		this.completionRate = completionRate;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
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

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getIsTemplate() {
		return isTemplate;
	}

	public void setIsTemplate(Integer isTemplate) {
		this.isTemplate = isTemplate;
	}

	public Integer getProcess() {
		return process;
	}

	public void setProcess(Integer process) {
		this.process = process;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
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

	public Integer getTimeChange() {
		return timeChange;
	}

	public void setTimeChange(Integer timeChange) {
		this.timeChange = timeChange;
	}

	/**
	 * @return the timeName
	 */
	public String getTimeName() {
		return timeName;
	}

	/**
	 * @param timeName the timeName to set
	 */
	public void setTimeName(String timeName) {
		this.timeName = timeName;
	}

	/**
	 * @return the totalNum
	 */
	public Integer getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * @return the overNum
	 */
	public Integer getOverNum() {
		return overNum;
	}

	/**
	 * @param overNum the overNum to set
	 */
	public void setOverNum(Integer overNum) {
		this.overNum = overNum;
	}

	/**
	 * @return the rankId
	 */
	public Integer getRankId() {
		return rankId;
	}

	/**
	 * @param rankId the rankId to set
	 */
	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	/**
	 * @return the objectNum
	 */
	public Integer getObjectNum() {
		return objectNum;
	}

	/**
	 * @param objectNum the objectNum to set
	 */
	public void setObjectNum(Integer objectNum) {
		this.objectNum = objectNum;
	}

	/**
	 * @return the strCreationDate
	 */
	public String getStrCreationDate() {
		return strCreationDate;
	}

	/**
	 * @param strCreationDate the strCreationDate to set
	 */
	public void setStrCreationDate(String strCreationDate) {
		this.strCreationDate = strCreationDate;
	}

	/**
	 * @return the operationStatus
	 */
	public Integer getOperationStatus() {
		return operationStatus;
	}

	/**
	 * @param operationStatus the operationStatus to set
	 */
	public void setOperationStatus(Integer operationStatus) {
		this.operationStatus = operationStatus;
	}

	/**
	 * @return the overTime
	 */
	public Date getOverTime() {
		return overTime;
	}

	/**
	 * @param overTime the overTime to set
	 */
	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

}