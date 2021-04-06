package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年11月23日 上午11:37:15
 */
public class DepOvertimeDutypoliceNum {

	private Integer id;// 部门id
	private String name;// 部门名称
	private Integer overtimeNum;// 加班人数
	private Integer dutyNum;// 值班人数
	private Integer totalNum;// 总人数

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
	 * @return the overtimeNum
	 */
	public Integer getOvertimeNum() {
		return overtimeNum;
	}

	/**
	 * @param overtimeNum the overtimeNum to set
	 */
	public void setOvertimeNum(Integer overtimeNum) {
		this.overtimeNum = overtimeNum;
	}

	/**
	 * @return the dutyNum
	 */
	public Integer getDutyNum() {
		return dutyNum;
	}

	/**
	 * @param dutyNum the dutyNum to set
	 */
	public void setDutyNum(Integer dutyNum) {
		this.dutyNum = dutyNum;
	}

	/**
	 * @return the totalNum
	 */
	public Integer getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

}
