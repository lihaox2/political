package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2021年3月30日 下午6:08:19
 */
public class RiskIndexMonitorChild {

	private int id;// 存在的风险指id

	private String name;// 存在的风险名称

	private int indexPoliceNum;// 存在的风险指数人数

	private int alarmPoliceNum;// 发出预警人数

	private double alarmPoliceRate;// 预警人数/存在的风险指数人数占比

	private int talkPoliceNum;// 约谈人数

	private int isDisplay;// 是否显示红点（0否1是）

	/**
	 * @return the indexPoliceNum
	 */
	public int getIndexPoliceNum() {
		return indexPoliceNum;
	}

	/**
	 * @param indexPoliceNum the indexPoliceNum to set
	 */
	public void setIndexPoliceNum(int indexPoliceNum) {
		this.indexPoliceNum = indexPoliceNum;
	}

	/**
	 * @return the alarmPoliceNum
	 */
	public int getAlarmPoliceNum() {
		return alarmPoliceNum;
	}

	/**
	 * @param alarmPoliceNum the alarmPoliceNum to set
	 */
	public void setAlarmPoliceNum(int alarmPoliceNum) {
		this.alarmPoliceNum = alarmPoliceNum;
	}

	/**
	 * @return the alarmPoliceRate
	 */
	public double getAlarmPoliceRate() {
		return alarmPoliceRate;
	}

	/**
	 * @param alarmPoliceRate the alarmPoliceRate to set
	 */
	public void setAlarmPoliceRate(double alarmPoliceRate) {
		this.alarmPoliceRate = alarmPoliceRate;
	}

	/**
	 * @return the talkPoliceNum
	 */
	public int getTalkPoliceNum() {
		return talkPoliceNum;
	}

	/**
	 * @param talkPoliceNum the talkPoliceNum to set
	 */
	public void setTalkPoliceNum(int talkPoliceNum) {
		this.talkPoliceNum = talkPoliceNum;
	}

	/**
	 * @return the isDisplay
	 */
	public int getIsDisplay() {
		return isDisplay;
	}

	/**
	 * @param isDisplay the isDisplay to set
	 */
	public void setIsDisplay(int isDisplay) {
		this.isDisplay = isDisplay;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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

}
