package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

//预警记录
public class AlarmRecordItem {

	private List<AlarmRecordChart> list;

	private String policeId;// 被记分人(警号)

	private String name;// 被记分人名字

	private String departmentName;// 被记分人部门名称

	private String positionName;// 被记分人职位名称
	
	private Date creationDate;// 创建时间

	private AlarmTalk talkItem;

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
	 * @return the list
	 */
	public List<AlarmRecordChart> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<AlarmRecordChart> list) {
		this.list = list;
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