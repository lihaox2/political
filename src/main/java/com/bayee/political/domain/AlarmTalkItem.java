package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年12月3日 上午10:08:25
 */
public class AlarmTalkItem {

	private AlarmRecord alarmItem;

	private AlarmTalk talkItem;

	private AlarmEntryAndExitRecord entryItem;

	/**
	 * @return the talkItem
	 */
	public AlarmTalk getTalkItem() {
		return talkItem;
	}

	/**
	 * @param talkItem the talkItem to set
	 */
	public void setTalkItem(AlarmTalk talkItem) {
		this.talkItem = talkItem;
	}

	/**
	 * @return the entryItem
	 */
	public AlarmEntryAndExitRecord getEntryItem() {
		return entryItem;
	}

	/**
	 * @param entryItem the entryItem to set
	 */
	public void setEntryItem(AlarmEntryAndExitRecord entryItem) {
		this.entryItem = entryItem;
	}

	/**
	 * @return the alarmItem
	 */
	public AlarmRecord getAlarmItem() {
		return alarmItem;
	}

	/**
	 * @param alarmItem the alarmItem to set
	 */
	public void setAlarmItem(AlarmRecord alarmItem) {
		this.alarmItem = alarmItem;
	}

}
