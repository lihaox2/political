/**
 * 
 */
package com.bayee.political.domain;

import java.util.Date;

/**
 * @author seanguo
 *
 */
public class LeaveProcess {

	private long id;
	private String processCode;
	private String processInstanceId;
	private String businessId; // 审批实例业务编号
	private String title;
	private Date createTime;
	private Date finishTime;
	private String bizAction; // 审批实例业务动作，MODIFY表示该审批实例是基于原来的实例修改而来，REVOKE表示该审批实例对原来的实例进行撤销，NONE表示正常发起
	private String departmentId; // originator_dept_id 发起部门
	private String userId; // originator_userid 发起人
	private String result; // 审批结果，分为 agree 和 refuse
	private String status; // 审批状态，分为NEW（新创建） RUNNING（运行中）TERMINATED（被终止）COMPLETED（完成）
	private String ccUserIds;
	private String leaveType;
	private String leaveReason;
	private Date leaveStartDate;
	private Date leaveEndDate;
	private double leaveDuarationDays;
	private String destination; // 外出目的地
	private String transportation; // 出行方式
	private Integer identification;//标志（1未查询，2已查询）
	private Integer alarmItem;//标志（1未查询，2已查询）
	private Integer overtimeItem;//加班调休查询标志（1未查询，2已查询）
	private Integer pointItem;//积分兑换查询标志（1未查询，2已查询）
	private Date creationDate;
	private Date updateDate;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the alarmItem
	 */
	public Integer getAlarmItem() {
		return alarmItem;
	}

	/**
	 * @param alarmItem the alarmItem to set
	 */
	public void setAlarmItem(Integer alarmItem) {
		this.alarmItem = alarmItem;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the businessId
	 */
	public String getBusinessId() {
		return businessId;
	}

	/**
	 * @param businessId the businessId to set
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
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
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the finishTime
	 */
	public Date getFinishTime() {
		return finishTime;
	}

	/**
	 * @param finishTime the finishTime to set
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * @return the bizAction
	 */
	public String getBizAction() {
		return bizAction;
	}

	/**
	 * @param bizAction the bizAction to set
	 */
	public void setBizAction(String bizAction) {
		this.bizAction = bizAction;
	}

	/**
	 * @return the departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the ccUserIds
	 */
	public String getCcUserIds() {
		return ccUserIds;
	}

	/**
	 * @param ccUserIds the ccUserIds to set
	 */
	public void setCcUserIds(String ccUserIds) {
		this.ccUserIds = ccUserIds;
	}

	/**
	 * @return the leaveType
	 */
	public String getLeaveType() {
		return leaveType;
	}

	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	/**
	 * @return the leaveReason
	 */
	public String getLeaveReason() {
		return leaveReason;
	}

	/**
	 * @param leaveReason the leaveReason to set
	 */
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	/**
	 * @return the leaveStartDate
	 */
	public Date getLeaveStartDate() {
		return leaveStartDate;
	}

	/**
	 * @param leaveStartDate the leaveStartDate to set
	 */
	public void setLeaveStartDate(Date leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	/**
	 * @return the leaveEndDate
	 */
	public Date getLeaveEndDate() {
		return leaveEndDate;
	}

	/**
	 * @param leaveEndDate the leaveEndDate to set
	 */
	public void setLeaveEndDate(Date leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	/**
	 * @return the leaveDuarationDays
	 */
	public double getLeaveDuarationDays() {
		return leaveDuarationDays;
	}

	/**
	 * @param leaveDuarationDays the leaveDuarationDays to set
	 */
	public void setLeaveDuarationDays(double leaveDuarationDays) {
		this.leaveDuarationDays = leaveDuarationDays;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the transportation
	 */
	public String getTransportation() {
		return transportation;
	}

	/**
	 * @param transportation the transportation to set
	 */
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	/**
	 * @return the processCode
	 */
	public String getProcessCode() {
		return processCode;
	}

	/**
	 * @param processCode the processCode to set
	 */
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	/**
	 * @return the processInstanceId
	 */
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	/**
	 * @param processInstanceId the processInstanceId to set
	 */
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
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
	 * @return the overtimeItem
	 */
	public Integer getOvertimeItem() {
		return overtimeItem;
	}

	/**
	 * @param overtimeItem the overtimeItem to set
	 */
	public void setOvertimeItem(Integer overtimeItem) {
		this.overtimeItem = overtimeItem;
	}

	/**
	 * @return the pointItem
	 */
	public Integer getPointItem() {
		return pointItem;
	}

	/**
	 * @param pointItem the pointItem to set
	 */
	public void setPointItem(Integer pointItem) {
		this.pointItem = pointItem;
	}

}
