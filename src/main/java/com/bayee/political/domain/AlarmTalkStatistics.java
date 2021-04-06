package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年7月22日 下午6:44:07 数据统计
 */
public class AlarmTalkStatistics {

	private double completionRate;// 完成率
	private double unfinishedRate;// 未完成率
	private AlarmTalkPoliceNum completionPoliceNum;// 完成约谈人数
	private AlarmTalkPoliceNum unfinishedNum;// 未完成约谈人数

	/**
	 * @return the completionRate
	 */
	public double getCompletionRate() {
		return completionRate;
	}

	/**
	 * @param completionRate the completionRate to set
	 */
	public void setCompletionRate(double completionRate) {
		this.completionRate = completionRate;
	}

	/**
	 * @return the unfinishedRate
	 */
	public double getUnfinishedRate() {
		return unfinishedRate;
	}

	/**
	 * @param unfinishedRate the unfinishedRate to set
	 */
	public void setUnfinishedRate(double unfinishedRate) {
		this.unfinishedRate = unfinishedRate;
	}

	/**
	 * @return the completionPoliceNum
	 */
	public AlarmTalkPoliceNum getCompletionPoliceNum() {
		return completionPoliceNum;
	}

	/**
	 * @param completionPoliceNum the completionPoliceNum to set
	 */
	public void setCompletionPoliceNum(AlarmTalkPoliceNum completionPoliceNum) {
		this.completionPoliceNum = completionPoliceNum;
	}

	/**
	 * @return the unfinishedNum
	 */
	public AlarmTalkPoliceNum getUnfinishedNum() {
		return unfinishedNum;
	}

	/**
	 * @param unfinishedNum the unfinishedNum to set
	 */
	public void setUnfinishedNum(AlarmTalkPoliceNum unfinishedNum) {
		this.unfinishedNum = unfinishedNum;
	}


}
