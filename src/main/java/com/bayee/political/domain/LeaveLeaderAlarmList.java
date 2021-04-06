package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年9月22日 下午2:52:03
 */
public class LeaveLeaderAlarmList {

	private int num;

	private List<LeaveLeaderAlarm> alarmList;// 加班预警,办案积分预警

	private List<LeaveCompensatoryAlarm> compensatoryAlarmList;// 调休预警

	private List<LeaveCompensatoryRecord> needDealtList;// 待办事项

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @return the alarmList
	 */
	public List<LeaveLeaderAlarm> getAlarmList() {
		return alarmList;
	}

	/**
	 * @param alarmList the alarmList to set
	 */
	public void setAlarmList(List<LeaveLeaderAlarm> alarmList) {
		this.alarmList = alarmList;
	}

	/**
	 * @return the compensatoryAlarmList
	 */
	public List<LeaveCompensatoryAlarm> getCompensatoryAlarmList() {
		return compensatoryAlarmList;
	}

	/**
	 * @param compensatoryAlarmList the compensatoryAlarmList to set
	 */
	public void setCompensatoryAlarmList(List<LeaveCompensatoryAlarm> compensatoryAlarmList) {
		this.compensatoryAlarmList = compensatoryAlarmList;
	}

	/**
	 * @return the needDealtList
	 */
	public List<LeaveCompensatoryRecord> getNeedDealtList() {
		return needDealtList;
	}

	/**
	 * @param needDealtList the needDealtList to set
	 */
	public void setNeedDealtList(List<LeaveCompensatoryRecord> needDealtList) {
		this.needDealtList = needDealtList;
	}

}
