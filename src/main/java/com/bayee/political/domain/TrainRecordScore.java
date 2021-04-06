package com.bayee.political.domain;

import java.util.Date;

/** 
* @author  shentuqiwei 
* @version 2020年10月13日 下午5:07:11 
*/
public class TrainRecordScore {
	
	private Integer trainPhysicalId;
	
	private Integer objectId;
	
	private Integer type;
	
	private String name;
	
	private Double proportion;
	
	private String statusName;
	
	private Integer departmentId;
	
	private Integer isReport;//是否报名（0否1是）
	
	private Date trainStartDate;

	private Date trainEndDate;
	
	private Date creationDate;
	
	/**
	 * @return the trainPhysicalId
	 */
	public Integer getTrainPhysicalId() {
		return trainPhysicalId;
	}

	/**
	 * @param trainPhysicalId the trainPhysicalId to set
	 */
	public void setTrainPhysicalId(Integer trainPhysicalId) {
		this.trainPhysicalId = trainPhysicalId;
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
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
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
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @return the objectId
	 */
	public Integer getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
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
	 * @return the trainStartDate
	 */
	public Date getTrainStartDate() {
		return trainStartDate;
	}

	/**
	 * @param trainStartDate the trainStartDate to set
	 */
	public void setTrainStartDate(Date trainStartDate) {
		this.trainStartDate = trainStartDate;
	}

	/**
	 * @return the trainEndDate
	 */
	public Date getTrainEndDate() {
		return trainEndDate;
	}

	/**
	 * @param trainEndDate the trainEndDate to set
	 */
	public void setTrainEndDate(Date trainEndDate) {
		this.trainEndDate = trainEndDate;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the isReport
	 */
	public Integer getIsReport() {
		return isReport;
	}

	/**
	 * @param isReport the isReport to set
	 */
	public void setIsReport(Integer isReport) {
		this.isReport = isReport;
	}
	
}
