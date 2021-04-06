package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年7月22日 下午6:46:27
 */
public class AlarmTalkPoliceNum {

	private int totalNum;// 总约谈人数

	private int addNum;// 加分型约谈人数

	private int buckleNum;// 扣分型约谈人数

	/**
	 * @return the totalNum
	 */
	public int getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * @return the addNum
	 */
	public int getAddNum() {
		return addNum;
	}

	/**
	 * @param addNum the addNum to set
	 */
	public void setAddNum(int addNum) {
		this.addNum = addNum;
	}

	/**
	 * @return the buckleNum
	 */
	public int getBuckleNum() {
		return buckleNum;
	}

	/**
	 * @param buckleNum the buckleNum to set
	 */
	public void setBuckleNum(int buckleNum) {
		this.buckleNum = buckleNum;
	}

	
}
