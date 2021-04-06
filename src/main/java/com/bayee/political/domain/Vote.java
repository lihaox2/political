package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年6月12日 下午6:23:38
 */
public class Vote {

	private int policeNum;// 评价参与人数量

	private int overNum;// 已投票数量

	private int notNum;// 未投票数量

	private int invalidNum;// 无效票

	/**
	 * @return the policeNum
	 */
	public int getPoliceNum() {
		return policeNum;
	}

	/**
	 * @param policeNum the policeNum to set
	 */
	public void setPoliceNum(int policeNum) {
		this.policeNum = policeNum;
	}

	/**
	 * @return the overNum
	 */
	public int getOverNum() {
		return overNum;
	}

	/**
	 * @param overNum the overNum to set
	 */
	public void setOverNum(int overNum) {
		this.overNum = overNum;
	}

	/**
	 * @return the notNum
	 */
	public int getNotNum() {
		return notNum;
	}

	/**
	 * @param notNum the notNum to set
	 */
	public void setNotNum(int notNum) {
		this.notNum = notNum;
	}

	/**
	 * @return the invalidNum
	 */
	public int getInvalidNum() {
		return invalidNum;
	}

	/**
	 * @param invalidNum the invalidNum to set
	 */
	public void setInvalidNum(int invalidNum) {
		this.invalidNum = invalidNum;
	}

}
