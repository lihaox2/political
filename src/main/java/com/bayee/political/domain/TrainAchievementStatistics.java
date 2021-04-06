package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2021年1月11日 下午3:01:20
 */
public class TrainAchievementStatistics {

	private String dateTime;// 时间

	private int totalNum;// 总训练次数

	private int depNum;// 单位训练次数

	private int branchOfficeNum;// 分局训练次数

	private int passNum;// 合格次数

	private double passRate;// 合格率

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
	 * @return the passNum
	 */
	public int getPassNum() {
		return passNum;
	}

	/**
	 * @param passNum the passNum to set
	 */
	public void setPassNum(int passNum) {
		this.passNum = passNum;
	}

	/**
	 * @return the passRate
	 */
	public double getPassRate() {
		return passRate;
	}

	/**
	 * @param passRate the passRate to set
	 */
	public void setPassRate(double passRate) {
		this.passRate = passRate;
	}
	
}
