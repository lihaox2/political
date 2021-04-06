package com.bayee.political.domain;

import java.util.Date;

public class AlarmEntryAndExitRecord {
	private Integer id;

	private String headImage;

	private String policeId;

	private String name;

	private String departmentName;

	private String positionName;

	private String leavePlace;

	private String destination;

	private Date departureTime;

	private Date returnTime;
	
	private Integer isTalk;

	private Integer urgeNum;

	private String urgePoliceId;

	private Integer isReturn;

	private Integer timeChange;

	private Double overdueDays;

	private Date passportReturnTime;

	private Date creationDate;

	private String creationDateStr;

	private String strTime;

	private Date updateDate;
	
	private AlarmTalk talkItem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId == null ? null : policeId.trim();
	}

	public String getLeavePlace() {
		return leavePlace;
	}

	public void setLeavePlace(String leavePlace) {
		this.leavePlace = leavePlace == null ? null : leavePlace.trim();
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination == null ? null : destination.trim();
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public Integer getUrgeNum() {
		return urgeNum;
	}

	public void setUrgeNum(Integer urgeNum) {
		this.urgeNum = urgeNum;
	}

	public String getUrgePoliceId() {
		return urgePoliceId;
	}

	public void setUrgePoliceId(String urgePoliceId) {
		this.urgePoliceId = urgePoliceId == null ? null : urgePoliceId.trim();
	}

	public Integer getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(Integer isReturn) {
		this.isReturn = isReturn;
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
	 * @return the passportReturnTime
	 */
	public Date getPassportReturnTime() {
		return passportReturnTime;
	}

	/**
	 * @param passportReturnTime the passportReturnTime to set
	 */
	public void setPassportReturnTime(Date passportReturnTime) {
		this.passportReturnTime = passportReturnTime;
	}

	/**
	 * @return the strTime
	 */
	public String getStrTime() {
		return strTime;
	}

	/**
	 * @param strTime the strTime to set
	 */
	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}

	/**
	 * @return the isTalk
	 */
	public Integer getIsTalk() {
		return isTalk;
	}

	/**
	 * @param isTalk the isTalk to set
	 */
	public void setIsTalk(Integer isTalk) {
		this.isTalk = isTalk;
	}

	/**
	 * @return the talkItem
	 */
	public AlarmTalk getTalkItem() {
		return talkItem;
	}

	/**
	 * @param talkItem the talkItem to set
	 */
	public void setTalkItem(AlarmTalk talkItem) {
		this.talkItem = talkItem;
	}

}