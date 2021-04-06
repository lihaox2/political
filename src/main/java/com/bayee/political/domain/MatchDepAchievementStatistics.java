package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2021年1月12日 下午4:52:15
 */
public class MatchDepAchievementStatistics {

	private int totalNum;// 总训练次数

	private int getMedalNum;// 得奖次数

	private double getMedalRate;// 得奖率

	private int personalNum;// 个人参赛次数

	private int personalGetMedalNum;// 个人得奖次数

	private double personalGetMedalRate;// 个人得奖率

	private int groupNum;// 团体赛次数

	private int groupGetMedalNum;// 团体得奖次数

	private double groupGetMedalRate;// 团体得奖率

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
	 * @return the getMedalNum
	 */
	public int getGetMedalNum() {
		return getMedalNum;
	}

	/**
	 * @param getMedalNum the getMedalNum to set
	 */
	public void setGetMedalNum(int getMedalNum) {
		this.getMedalNum = getMedalNum;
	}

	/**
	 * @return the getMedalRate
	 */
	public double getGetMedalRate() {
		return getMedalRate;
	}

	/**
	 * @param getMedalRate the getMedalRate to set
	 */
	public void setGetMedalRate(double getMedalRate) {
		this.getMedalRate = getMedalRate;
	}

	/**
	 * @return the personalNum
	 */
	public int getPersonalNum() {
		return personalNum;
	}

	/**
	 * @param personalNum the personalNum to set
	 */
	public void setPersonalNum(int personalNum) {
		this.personalNum = personalNum;
	}

	/**
	 * @return the personalGetMedalNum
	 */
	public int getPersonalGetMedalNum() {
		return personalGetMedalNum;
	}

	/**
	 * @param personalGetMedalNum the personalGetMedalNum to set
	 */
	public void setPersonalGetMedalNum(int personalGetMedalNum) {
		this.personalGetMedalNum = personalGetMedalNum;
	}

	/**
	 * @return the personalGetMedalRate
	 */
	public double getPersonalGetMedalRate() {
		return personalGetMedalRate;
	}

	/**
	 * @param personalGetMedalRate the personalGetMedalRate to set
	 */
	public void setPersonalGetMedalRate(double personalGetMedalRate) {
		this.personalGetMedalRate = personalGetMedalRate;
	}

	/**
	 * @return the groupNum
	 */
	public int getGroupNum() {
		return groupNum;
	}

	/**
	 * @param groupNum the groupNum to set
	 */
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	/**
	 * @return the groupGetMedalNum
	 */
	public int getGroupGetMedalNum() {
		return groupGetMedalNum;
	}

	/**
	 * @param groupGetMedalNum the groupGetMedalNum to set
	 */
	public void setGroupGetMedalNum(int groupGetMedalNum) {
		this.groupGetMedalNum = groupGetMedalNum;
	}

	/**
	 * @return the groupGetMedalRate
	 */
	public double getGroupGetMedalRate() {
		return groupGetMedalRate;
	}

	/**
	 * @param groupGetMedalRate the groupGetMedalRate to set
	 */
	public void setGroupGetMedalRate(double groupGetMedalRate) {
		this.groupGetMedalRate = groupGetMedalRate;
	}
	
}
