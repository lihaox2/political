package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2021年1月12日 下午3:27:06 
*/
public class MatchAchievementStatistics {

	private String dateTime;// 时间

	private int totalNum;// 总赛事次数

	private int depNum;// 单位赛事次数

	private int branchOfficeNum;// 分局赛事次数

	private int getMedalNum;// 得奖次数

	private double getMedalRate;// 得奖率

	/**
	 * @return the dateTime
	 */
	public String getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

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
	 * @return the depNum
	 */
	public int getDepNum() {
		return depNum;
	}

	/**
	 * @param depNum the depNum to set
	 */
	public void setDepNum(int depNum) {
		this.depNum = depNum;
	}

	/**
	 * @return the branchOfficeNum
	 */
	public int getBranchOfficeNum() {
		return branchOfficeNum;
	}

	/**
	 * @param branchOfficeNum the branchOfficeNum to set
	 */
	public void setBranchOfficeNum(int branchOfficeNum) {
		this.branchOfficeNum = branchOfficeNum;
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
}
