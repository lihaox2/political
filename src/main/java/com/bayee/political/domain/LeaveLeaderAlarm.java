package com.bayee.political.domain;

/** 
* @author  shentuqiwei 
* @version 2020年9月22日 下午2:29:14 
*/
public class LeaveLeaderAlarm {

	private Integer id;
	
	private String headImage;

    private String policeId;

    private String name;

    private Integer departmentId;
    
    private String departmentName;

    private Integer positionId;
    
    private String positionName;

    private Double residualPoints;
    
    private double dateTime;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the positionId
	 */
	public Integer getPositionId() {
		return positionId;
	}

	/**
	 * @param positionId the positionId to set
	 */
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
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
	 * @return the residualPoints
	 */
	public Double getResidualPoints() {
		return residualPoints;
	}

	/**
	 * @param residualPoints the residualPoints to set
	 */
	public void setResidualPoints(Double residualPoints) {
		this.residualPoints = residualPoints;
	}

	/**
	 * @return the dateTime
	 */
	public double getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(double dateTime) {
		this.dateTime = dateTime;
	}

}
