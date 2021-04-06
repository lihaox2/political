package com.bayee.political.domain;

import java.util.Date;

public class AlarmUrgeRecord {
	private Integer id;

	private Integer entryId;

	private String policeId;

	private String urgeNotice;

	private String urgeContent;

	private Integer readStatus;

	private Integer urgeReadStatus;

	private String urgeHeadImage;

	private String urgePoliceId;

	private String urgeName;

	private String urgeDepartmentName;

	private String urgePositionName;

	private Double overdueDays;

	private String destination;

	private Date departureTime;

	private Date returnTime;
	
	private Integer isReturn;

	private Integer timeChange;

	private String strStartTime;

	private String creationDateStr;

	private Date creationDate;

	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEntryId() {
		return entryId;
	}

	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}

	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId == null ? null : policeId.trim();
	}

	public String getUrgeNotice() {
		return urgeNotice;
	}

	public void setUrgeNotice(String urgeNotice) {
		this.urgeNotice = urgeNotice == null ? null : urgeNotice.trim();
	}

	public Integer getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

	public Integer getUrgeReadStatus() {
		return urgeReadStatus;
	}

	public void setUrgeReadStatus(Integer urgeReadStatus) {
		this.urgeReadStatus = urgeReadStatus;
	}

	public String getUrgePoliceId() {
		return urgePoliceId;
	}

	public void setUrgePoliceId(String urgePoliceId) {
		this.urgePoliceId = urgePoliceId == null ? null : urgePoliceId.trim();
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
	 * @return the urgeHeadImage
	 */
	public String getUrgeHeadImage() {
		return urgeHeadImage;
	}

	/**
	 * @param urgeHeadImage the urgeHeadImage to set
	 */
	public void setUrgeHeadImage(String urgeHeadImage) {
		this.urgeHeadImage = urgeHeadImage;
	}

	/**
	 * @return the urgeName
	 */
	public String getUrgeName() {
		return urgeName;
	}

	/**
	 * @param urgeName the urgeName to set
	 */
	public void setUrgeName(String urgeName) {
		this.urgeName = urgeName;
	}

	/**
	 * @return the urgeDepartmentName
	 */
	public String getUrgeDepartmentName() {
		return urgeDepartmentName;
	}

	/**
	 * @param urgeDepartmentName the urgeDepartmentName to set
	 */
	public void setUrgeDepartmentName(String urgeDepartmentName) {
		this.urgeDepartmentName = urgeDepartmentName;
	}

	/**
	 * @return the urgePositionName
	 */
	public String getUrgePositionName() {
		return urgePositionName;
	}

	/**
	 * @param urgePositionName the urgePositionName to set
	 */
	public void setUrgePositionName(String urgePositionName) {
		this.urgePositionName = urgePositionName;
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
	 * @return the urgeContent
	 */
	public String getUrgeContent() {
		return urgeContent;
	}

	/**
	 * @param urgeContent the urgeContent to set
	 */
	public void setUrgeContent(String urgeContent) {
		this.urgeContent = urgeContent;
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
	 * @return the overdueDays
	 */
	public Double getOverdueDays() {
		return overdueDays;
	}

	/**
	 * @param overdueDays the overdueDays to set
	 */
	public void setOverdueDays(Double overdueDays) {
		this.overdueDays = overdueDays;
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
	 * @return the departureTime
	 */
	public Date getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * @return the returnTime
	 */
	public Date getReturnTime() {
		return returnTime;
	}

	/**
	 * @param returnTime the returnTime to set
	 */
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	/**
	 * @return the isReturn
	 */
	public Integer getIsReturn() {
		return isReturn;
	}

	/**
	 * @param isReturn the isReturn to set
	 */
	public void setIsReturn(Integer isReturn) {
		this.isReturn = isReturn;
	}

}