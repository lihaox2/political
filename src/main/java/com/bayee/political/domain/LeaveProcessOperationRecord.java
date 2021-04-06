/**
 * 
 */
package com.bayee.political.domain;

import java.util.Date;

/**
 * @author seanguo
 *
 */
public class LeaveProcessOperationRecord {

	private long id;
	private long leaveProcessId;
	private String businessId;

	private String userId; // 操作人
	private Date operationDate; // 操作时间
	// 操作类型，分为EXECUTE_TASK_NORMAL（正常执行任务），EXECUTE_TASK_AGENT（代理人执行任务），APPEND_TASK_BEFORE（前加签任务），
	// APPEND_TASK_AFTER（后加签任务），REDIRECT_TASK（转交任务），START_PROCESS_INSTANCE（发起流程实例），TERMINATE_PROCESS_INSTANCE（终止(撤销)流程实例），
	// FINISH_PROCESS_INSTANCE（结束流程实例），ADD_REMARK（添加评论）
	private String operationType;
	private String operationResult; // 操作结果，分为NONE（无），AGREE（同意），REFUSE（拒绝）
	private String remark; // 评论内容。审批操作附带评论时才返回该字段。

	private Date creationDate;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the leaveProcessId
	 */
	public long getLeaveProcessId() {
		return leaveProcessId;
	}

	/**
	 * @param leaveProcessId the leaveProcessId to set
	 */
	public void setLeaveProcessId(long leaveProcessId) {
		this.leaveProcessId = leaveProcessId;
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
	 * @return the operationDate
	 */
	public Date getOperationDate() {
		return operationDate;
	}

	/**
	 * @param operationDate the operationDate to set
	 */
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	/**
	 * @return the operationType
	 */
	public String getOperationType() {
		return operationType;
	}

	/**
	 * @param operationType the operationType to set
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	/**
	 * @return the operationResult
	 */
	public String getOperationResult() {
		return operationResult;
	}

	/**
	 * @param operationResult the operationResult to set
	 */
	public void setOperationResult(String operationResult) {
		this.operationResult = operationResult;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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

}
