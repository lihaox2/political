package com.bayee.political.domain;

import java.util.List;

/** 
* @author  shentuqiwei 
* @version 2020年8月3日 下午2:52:26 
*/
public class AlarmList {

	private AlarmPersonalStatistics personalStatistics;
	
	private List<AlarmRecord> alarmList;

	/**
	 * @return the personalStatistics
	 */
	public AlarmPersonalStatistics getPersonalStatistics() {
		return personalStatistics;
	}

	/**
	 * @param personalStatistics the personalStatistics to set
	 */
	public void setPersonalStatistics(AlarmPersonalStatistics personalStatistics) {
		this.personalStatistics = personalStatistics;
	}

	/**
	 * @return the alarmList
	 */
	public List<AlarmRecord> getAlarmList() {
		return alarmList;
	}

	/**
	 * @param alarmList the alarmList to set
	 */
	public void setAlarmList(List<AlarmRecord> alarmList) {
		this.alarmList = alarmList;
	}
	
}
