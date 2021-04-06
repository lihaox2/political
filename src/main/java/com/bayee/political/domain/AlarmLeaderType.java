package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年7月29日 下午3:16:10
 */
public class AlarmLeaderType {

	private AlarmLeaderStatistics alarmLeaderItem;// 局领导预警统计

	private AlarmLeaderStatistics alarmEntryAndExitItem;// 出入境预警统计

	private AlarmLeaderStatistics alarmLeaderTalkItem;// 局领导会谈统计

	/**
	 * @return the alarmLeaderItem
	 */
	public AlarmLeaderStatistics getAlarmLeaderItem() {
		return alarmLeaderItem;
	}

	/**
	 * @param alarmLeaderItem the alarmLeaderItem to set
	 */
	public void setAlarmLeaderItem(AlarmLeaderStatistics alarmLeaderItem) {
		this.alarmLeaderItem = alarmLeaderItem;
	}

	/**
	 * @return the alarmLeaderTalkItem
	 */
	public AlarmLeaderStatistics getAlarmLeaderTalkItem() {
		return alarmLeaderTalkItem;
	}

	/**
	 * @param alarmLeaderTalkItem the alarmLeaderTalkItem to set
	 */
	public void setAlarmLeaderTalkItem(AlarmLeaderStatistics alarmLeaderTalkItem) {
		this.alarmLeaderTalkItem = alarmLeaderTalkItem;
	}

	/**
	 * @return the alarmEntryAndExitItem
	 */
	public AlarmLeaderStatistics getAlarmEntryAndExitItem() {
		return alarmEntryAndExitItem;
	}

	/**
	 * @param alarmEntryAndExitItem the alarmEntryAndExitItem to set
	 */
	public void setAlarmEntryAndExitItem(AlarmLeaderStatistics alarmEntryAndExitItem) {
		this.alarmEntryAndExitItem = alarmEntryAndExitItem;
	}

}
