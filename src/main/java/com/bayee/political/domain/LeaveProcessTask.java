/**
 * 
 */
package com.bayee.political.domain;

import java.util.Date;

/**
 * @author seanguo
 *
 */
public class LeaveProcessTask {

	private long id;
	private long leaveProcessId;
	private String businessId;
	
	private String taskId;
	private String result;
	private String status;
	private String userId;
	private Date taskCreateTime;
	private Date taskFinishTime;
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
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
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
	 * @return the taskCreateTime
	 */
	public Date getTaskCreateTime() {
		return taskCreateTime;
	}

	/**
	 * @param taskCreateTime the taskCreateTime to set
	 */
	public void setTaskCreateTime(Date taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}

	/**
	 * @return the taskFinishTime
	 */
	public Date getTaskFinishTime() {
		return taskFinishTime;
	}

	/**
	 * @param taskFinishTime the taskFinishTime to set
	 */
	public void setTaskFinishTime(Date taskFinishTime) {
		this.taskFinishTime = taskFinishTime;
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
