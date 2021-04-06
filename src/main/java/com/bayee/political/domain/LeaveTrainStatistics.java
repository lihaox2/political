package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年9月25日 上午11:32:23
 */
public class LeaveTrainStatistics {

	private int awardsLeaveNum;// 立功受奖优先安排人数
	private int continuousNotLeaveNum;// 连续三年未休养人数
	private List<LeaveTrain> leaveTrainList;

	/**
	 * @return the awardsLeaveNum
	 */
	public int getAwardsLeaveNum() {
		return awardsLeaveNum;
	}

	/**
	 * @return the leaveTrainList
	 */
	public List<LeaveTrain> getLeaveTrainList() {
		return leaveTrainList;
	}

	/**
	 * @param leaveTrainList the leaveTrainList to set
	 */
	public void setLeaveTrainList(List<LeaveTrain> leaveTrainList) {
		this.leaveTrainList = leaveTrainList;
	}

	/**
	 * @param awardsLeaveNum the awardsLeaveNum to set
	 */
	public void setAwardsLeaveNum(int awardsLeaveNum) {
		this.awardsLeaveNum = awardsLeaveNum;
	}

	/**
	 * @return the continuousNotLeaveNum
	 */
	public int getContinuousNotLeaveNum() {
		return continuousNotLeaveNum;
	}

	/**
	 * @param continuousNotLeaveNum the continuousNotLeaveNum to set
	 */
	public void setContinuousNotLeaveNum(int continuousNotLeaveNum) {
		this.continuousNotLeaveNum = continuousNotLeaveNum;
	}

}
