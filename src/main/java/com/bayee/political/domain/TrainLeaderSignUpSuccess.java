package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年10月22日 下午7:41:25 
*/

import java.util.List;

public class TrainLeaderSignUpSuccess {

	private String name;//训练名称

	private String place;//训练地点
	
	private Integer policeNum;//部门报名人数
	
	private Integer status;
	
	private Integer departmentId;
	
	private String creationDateStr;
	
	private List<TrainRecommendPolice> policeList;

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
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * @return the policeNum
	 */
	public Integer getPoliceNum() {
		return policeNum;
	}

	/**
	 * @param policeNum the policeNum to set
	 */
	public void setPoliceNum(Integer policeNum) {
		this.policeNum = policeNum;
	}

	/**
	 * @return the policeList
	 */
	public List<TrainRecommendPolice> getPoliceList() {
		return policeList;
	}

	/**
	 * @param policeList the policeList to set
	 */
	public void setPoliceList(List<TrainRecommendPolice> policeList) {
		this.policeList = policeList;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
}
