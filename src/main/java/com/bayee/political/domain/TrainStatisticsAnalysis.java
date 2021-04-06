package com.bayee.political.domain;

/**
 * @author shentuqiwei 统计分析
 * @version 2020年10月24日 上午11:56:33
 */
public class TrainStatisticsAnalysis {
	private int depTrainNum;// 单位训练次数
	private double depTrainAveNum;// 单位训练每月平均次数
	private int officeTrainNum;// 分局训练次数
	private double officeTrainAveNum;// 分局训练每月平均次数
	private double officeTrainSignRate;// 分局训练签到率
	private int depMatchNum;// 单位赛事次数
	private double depMatchAveNum;// 单位赛事每月平均次数
	private int officeMatchNum;// 分局赛事次数
	private double officeMatchAveNum;// 分局赛事每月平均次数
	private double officeMatchSignRate;// 分局赛事签到率
	private double depRecentTrainPassRate;// 最近一次单位训练总体合格率
	private double officeRecentTrainPassRate;// 最近一次参加分局训练合格率
	private int officeRecentTrainRank;// 最近一次参加分局训练名次
	private int depRecentMatchNum;// 近一月单位组织赛事次数
	private double officeRecentMatchSignRate;// //近一月参加分局赛事平均签到率
	private String matchName;// 最近一次第一名的比赛名称(// 钱塘分局2020年4*50米游泳男女混合比赛 金沙湖派出所获得名次第 1)
	private String departmentName;// 最近一次第一名的比赛名称
	private int rankId;// 最近一次第一名

	/**
	 * @return the depTrainNum
	 */
	public int getDepTrainNum() {
		return depTrainNum;
	}

	/**
	 * @param depTrainNum the depTrainNum to set
	 */
	public void setDepTrainNum(int depTrainNum) {
		this.depTrainNum = depTrainNum;
	}

	/**
	 * @return the depTrainAveNum
	 */
	public double getDepTrainAveNum() {
		return depTrainAveNum;
	}

	/**
	 * @param depTrainAveNum the depTrainAveNum to set
	 */
	public void setDepTrainAveNum(double depTrainAveNum) {
		this.depTrainAveNum = depTrainAveNum;
	}

	/**
	 * @return the officeTrainNum
	 */
	public int getOfficeTrainNum() {
		return officeTrainNum;
	}

	/**
	 * @param officeTrainNum the officeTrainNum to set
	 */
	public void setOfficeTrainNum(int officeTrainNum) {
		this.officeTrainNum = officeTrainNum;
	}

	/**
	 * @return the officeTrainAveNum
	 */
	public double getOfficeTrainAveNum() {
		return officeTrainAveNum;
	}

	/**
	 * @param officeTrainAveNum the officeTrainAveNum to set
	 */
	public void setOfficeTrainAveNum(double officeTrainAveNum) {
		this.officeTrainAveNum = officeTrainAveNum;
	}

	/**
	 * @return the officeTrainSignRate
	 */
	public double getOfficeTrainSignRate() {
		return officeTrainSignRate;
	}

	/**
	 * @param officeTrainSignRate the officeTrainSignRate to set
	 */
	public void setOfficeTrainSignRate(double officeTrainSignRate) {
		this.officeTrainSignRate = officeTrainSignRate;
	}

	/**
	 * @return the depMatchNum
	 */
	public int getDepMatchNum() {
		return depMatchNum;
	}

	/**
	 * @param depMatchNum the depMatchNum to set
	 */
	public void setDepMatchNum(int depMatchNum) {
		this.depMatchNum = depMatchNum;
	}

	/**
	 * @return the depMatchAveNum
	 */
	public double getDepMatchAveNum() {
		return depMatchAveNum;
	}

	/**
	 * @param depMatchAveNum the depMatchAveNum to set
	 */
	public void setDepMatchAveNum(double depMatchAveNum) {
		this.depMatchAveNum = depMatchAveNum;
	}

	/**
	 * @return the officeMatchNum
	 */
	public int getOfficeMatchNum() {
		return officeMatchNum;
	}

	/**
	 * @param officeMatchNum the officeMatchNum to set
	 */
	public void setOfficeMatchNum(int officeMatchNum) {
		this.officeMatchNum = officeMatchNum;
	}

	/**
	 * @return the officeMatchAveNum
	 */
	public double getOfficeMatchAveNum() {
		return officeMatchAveNum;
	}

	/**
	 * @param officeMatchAveNum the officeMatchAveNum to set
	 */
	public void setOfficeMatchAveNum(double officeMatchAveNum) {
		this.officeMatchAveNum = officeMatchAveNum;
	}

	/**
	 * @return the officeMatchSignRate
	 */
	public double getOfficeMatchSignRate() {
		return officeMatchSignRate;
	}

	/**
	 * @param officeMatchSignRate the officeMatchSignRate to set
	 */
	public void setOfficeMatchSignRate(double officeMatchSignRate) {
		this.officeMatchSignRate = officeMatchSignRate;
	}

	/**
	 * @return the depRecentTrainPassRate
	 */
	public double getDepRecentTrainPassRate() {
		return depRecentTrainPassRate;
	}

	/**
	 * @param depRecentTrainPassRate the depRecentTrainPassRate to set
	 */
	public void setDepRecentTrainPassRate(double depRecentTrainPassRate) {
		this.depRecentTrainPassRate = depRecentTrainPassRate;
	}

	/**
	 * @return the officeRecentTrainPassRate
	 */
	public double getOfficeRecentTrainPassRate() {
		return officeRecentTrainPassRate;
	}

	/**
	 * @param officeRecentTrainPassRate the officeRecentTrainPassRate to set
	 */
	public void setOfficeRecentTrainPassRate(double officeRecentTrainPassRate) {
		this.officeRecentTrainPassRate = officeRecentTrainPassRate;
	}

	/**
	 * @return the officeRecentTrainRank
	 */
	public int getOfficeRecentTrainRank() {
		return officeRecentTrainRank;
	}

	/**
	 * @param officeRecentTrainRank the officeRecentTrainRank to set
	 */
	public void setOfficeRecentTrainRank(int officeRecentTrainRank) {
		this.officeRecentTrainRank = officeRecentTrainRank;
	}

	/**
	 * @return the depRecentMatchNum
	 */
	public int getDepRecentMatchNum() {
		return depRecentMatchNum;
	}

	/**
	 * @param depRecentMatchNum the depRecentMatchNum to set
	 */
	public void setDepRecentMatchNum(int depRecentMatchNum) {
		this.depRecentMatchNum = depRecentMatchNum;
	}

	/**
	 * @return the officeRecentMatchSignRate
	 */
	public double getOfficeRecentMatchSignRate() {
		return officeRecentMatchSignRate;
	}

	/**
	 * @param officeRecentMatchSignRate the officeRecentMatchSignRate to set
	 */
	public void setOfficeRecentMatchSignRate(double officeRecentMatchSignRate) {
		this.officeRecentMatchSignRate = officeRecentMatchSignRate;
	}

	/**
	 * @return the matchName
	 */
	public String getMatchName() {
		return matchName;
	}

	/**
	 * @param matchName the matchName to set
	 */
	public void setMatchName(String matchName) {
		this.matchName = matchName;
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
	 * @return the rankId
	 */
	public int getRankId() {
		return rankId;
	}

	/**
	 * @param rankId the rankId to set
	 */
	public void setRankId(int rankId) {
		this.rankId = rankId;
	}
}
