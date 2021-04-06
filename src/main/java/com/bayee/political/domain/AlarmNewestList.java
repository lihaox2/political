package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年7月31日 下午2:40:24 最新预警实体类
 */
public class AlarmNewestList {

	private AlarmLeaderStatistics alarmStatistics;// 数据统计

	private List<AlarmRecord> alarmNewestList;// 预警list

	private List<AlarmTalk> alarmTalkList;// 约谈list
	
	private List<AlarmEntryAndExitRecord> alarmEntryList;// 出入境list

	/**
	 * @return the alarmStatistics
	 */
	public AlarmLeaderStatistics getAlarmStatistics() {
		return alarmStatistics;
	}

	/**
	 * @param alarmStatistics the alarmStatistics to set
	 */
	public void setAlarmStatistics(AlarmLeaderStatistics alarmStatistics) {
		this.alarmStatistics = alarmStatistics;
	}

	/**
	 * @return the alarmNewestList
	 */
	public List<AlarmRecord> getAlarmNewestList() {
		return alarmNewestList;
	}

	/**
	 * @param alarmNewestList the alarmNewestList to set
	 */
	public void setAlarmNewestList(List<AlarmRecord> alarmNewestList) {
		this.alarmNewestList = alarmNewestList;
	}

	/**
	 * @return the alarmTalkList
	 */
	public List<AlarmTalk> getAlarmTalkList() {
		return alarmTalkList;
	}

	/**
	 * @param alarmTalkList the alarmTalkList to set
	 */
	public void setAlarmTalkList(List<AlarmTalk> alarmTalkList) {
		this.alarmTalkList = alarmTalkList;
	}

	/**
	 * @return the alarmEntryList
	 */
	public List<AlarmEntryAndExitRecord> getAlarmEntryList() {
		return alarmEntryList;
	}

	/**
	 * @param alarmEntryList the alarmEntryList to set
	 */
	public void setAlarmEntryList(List<AlarmEntryAndExitRecord> alarmEntryList) {
		this.alarmEntryList = alarmEntryList;
	}

}
