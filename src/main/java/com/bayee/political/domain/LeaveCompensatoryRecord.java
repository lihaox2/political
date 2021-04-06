package com.bayee.political.domain;

import java.util.Date;

public class LeaveCompensatoryRecord {
	private Integer id;

	private Integer itemId;

	private Integer type;

	private String policeId;

	private String policeName;

	private String headImage;

	private Integer departmentId;

	private String departmentName;

	private Integer positionId;

	private String positionName;

	private Date startTime;

	private Date endTime;
	
	private Double leaveDays;

	private String approvedId;

	private String approvedName;

	private String approvedHeadImage;

	private String approvedDepartmentName;

	private String approvedPositionName;

	private String remarks;

	private String leaveReason;// 强制调休原因

	private String result;

	private String approvedResult;// 审批员处理结果

	private Integer isRest;

	private Integer status;

	private Integer readStatus;// 调休人员读取状态（0未读1已读）

	private Integer approvedReadStatus;// 审批人员读取状态（0未读1已读）

	private Integer timeChange;
	
	private Date dealTime;

	private Date creationDate;

	private String creationDateStr;

	private Date updateDate;
	
	private Integer gender;//被调休人性别

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		this.policeId = policeId == null ? null : policeId.trim();
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
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

	public String getApprovedId() {
		return approvedId;
	}

	public void setApprovedId(String approvedId) {
		this.approvedId = approvedId == null ? null : approvedId.trim();
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	 * @return the approvedName
	 */
	public String getApprovedName() {
		return approvedName;
	}

	/**
	 * @param approvedName the approvedName to set
	 */
	public void setApprovedName(String approvedName) {
		this.approvedName = approvedName;
	}

	/**
	 * @return the approvedHeadImage
	 */
	public String getApprovedHeadImage() {
		return approvedHeadImage;
	}

	/**
	 * @param approvedHeadImage the approvedHeadImage to set
	 */
	public void setApprovedHeadImage(String approvedHeadImage) {
		this.approvedHeadImage = approvedHeadImage;
	}

	/**
	 * @return the approvedDepartmentName
	 */
	public String getApprovedDepartmentName() {
		return approvedDepartmentName;
	}

	/**
	 * @param approvedDepartmentName the approvedDepartmentName to set
	 */
	public void setApprovedDepartmentName(String approvedDepartmentName) {
		this.approvedDepartmentName = approvedDepartmentName;
	}

	/**
	 * @return the approvedPositionName
	 */
	public String getApprovedPositionName() {
		return approvedPositionName;
	}

	/**
	 * @param approvedPositionName the approvedPositionName to set
	 */
	public void setApprovedPositionName(String approvedPositionName) {
		this.approvedPositionName = approvedPositionName;
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
	 * @return the isRest
	 */
	public Integer getIsRest() {
		return isRest;
	}

	/**
	 * @param isRest the isRest to set
	 */
	public void setIsRest(Integer isRest) {
		this.isRest = isRest;
	}

	/**
	 * @return the itemId
	 */
	public Integer getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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
	 * @return the approvedResult
	 */
	public String getApprovedResult() {
		return approvedResult;
	}

	/**
	 * @param approvedResult the approvedResult to set
	 */
	public void setApprovedResult(String approvedResult) {
		this.approvedResult = approvedResult;
	}

	/**
	 * @return the readStatus
	 */
	public Integer getReadStatus() {
		return readStatus;
	}

	/**
	 * @param readStatus the readStatus to set
	 */
	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

	/**
	 * @return the approvedReadStatus
	 */
	public Integer getApprovedReadStatus() {
		return approvedReadStatus;
	}

	/**
	 * @param approvedReadStatus the approvedReadStatus to set
	 */
	public void setApprovedReadStatus(Integer approvedReadStatus) {
		this.approvedReadStatus = approvedReadStatus;
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
	 * @return the leaveDays
	 */
	public Double getLeaveDays() {
		return leaveDays;
	}

	/**
	 * @param leaveDays the leaveDays to set
	 */
	public void setLeaveDays(Double leaveDays) {
		this.leaveDays = leaveDays;
	}

	/**
	 * @return the dealTime
	 */
	public Date getDealTime() {
		return dealTime;
	}

	/**
	 * @param dealTime the dealTime to set
	 */
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

}