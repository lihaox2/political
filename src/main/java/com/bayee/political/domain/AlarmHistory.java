package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年8月21日 下午4:37:00
 */
public class AlarmHistory {

	private int buckleNum;// 扣分项个数

	private int addNum;// 加分项个数
	
	private List<AlarmEvaluation> alarmEvaluationList;

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
	 * @return the alarmEvaluationList
	 */
	public List<AlarmEvaluation> getAlarmEvaluationList() {
		return alarmEvaluationList;
	}

	/**
	 * @param alarmEvaluationList the alarmEvaluationList to set
	 */
	public void setAlarmEvaluationList(List<AlarmEvaluation> alarmEvaluationList) {
		this.alarmEvaluationList = alarmEvaluationList;
	}
}
