package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年11月26日 下午9:18:55 首页各模块统计
*/

import java.util.List;

public class HomePageModularStatistics {

	private int workPoliceNum;// 今日上班民警人数
	private int onDutyPoliceNum;// 今日在外执勤民警总数
	private int askForLeavePoliceNum;// 今日请假中人数
	private int leavePoliceNum;// 今日离杭人数

	private int trainNum;// 今日训练活动总数
	private int trainPoliceNum;// 今日训练民警总数
	private int trainPassNum;// 今日合格人数
	private int trainFailNum;// 今日不合格人数

	private int alarmNum;// 今日预警人数
	private int alarmCheckNum;// 今日考核预警民警总数
	private int alarmEntryAndExitNum;// 今日出入境预警人数
	private int alarmTalkNum;// 今日约谈人数

	private int matchOngoingNum;// 今日进行中的赛事数量
	private int matchJoinNum;// 今日参赛人数
	private int matchDepNum;// 今日单位赛事个数
	private int matchBranchOfficeNum;// 今日分局赛事个数

	private List<Calculation> policeList;// 今日警力

	/**
	 * @return the workPoliceNum
	 */
	public int getWorkPoliceNum() {
		return workPoliceNum;
	}

	/**
	 * @param workPoliceNum the workPoliceNum to set
	 */
	public void setWorkPoliceNum(int workPoliceNum) {
		this.workPoliceNum = workPoliceNum;
	}

	/**
	 * @return the onDutyPoliceNum
	 */
	public int getOnDutyPoliceNum() {
		return onDutyPoliceNum;
	}

	/**
	 * @param onDutyPoliceNum the onDutyPoliceNum to set
	 */
	public void setOnDutyPoliceNum(int onDutyPoliceNum) {
		this.onDutyPoliceNum = onDutyPoliceNum;
	}

	/**
	 * @return the askForLeavePoliceNum
	 */
	public int getAskForLeavePoliceNum() {
		return askForLeavePoliceNum;
	}

	/**
	 * @param askForLeavePoliceNum the askForLeavePoliceNum to set
	 */
	public void setAskForLeavePoliceNum(int askForLeavePoliceNum) {
		this.askForLeavePoliceNum = askForLeavePoliceNum;
	}

	/**
	 * @return the leavePoliceNum
	 */
	public int getLeavePoliceNum() {
		return leavePoliceNum;
	}

	/**
	 * @param leavePoliceNum the leavePoliceNum to set
	 */
	public void setLeavePoliceNum(int leavePoliceNum) {
		this.leavePoliceNum = leavePoliceNum;
	}

	/**
	 * @return the trainNum
	 */
	public int getTrainNum() {
		return trainNum;
	}

	/**
	 * @param trainNum the trainNum to set
	 */
	public void setTrainNum(int trainNum) {
		this.trainNum = trainNum;
	}

	/**
	 * @return the trainPoliceNum
	 */
	public int getTrainPoliceNum() {
		return trainPoliceNum;
	}

	/**
	 * @param trainPoliceNum the trainPoliceNum to set
	 */
	public void setTrainPoliceNum(int trainPoliceNum) {
		this.trainPoliceNum = trainPoliceNum;
	}

	/**
	 * @return the trainPassNum
	 */
	public int getTrainPassNum() {
		return trainPassNum;
	}

	/**
	 * @param trainPassNum the trainPassNum to set
	 */
	public void setTrainPassNum(int trainPassNum) {
		this.trainPassNum = trainPassNum;
	}

	/**
	 * @return the trainFailNum
	 */
	public int getTrainFailNum() {
		return trainFailNum;
	}

	/**
	 * @param trainFailNum the trainFailNum to set
	 */
	public void setTrainFailNum(int trainFailNum) {
		this.trainFailNum = trainFailNum;
	}

	/**
	 * @return the alarmNum
	 */
	public int getAlarmNum() {
		return alarmNum;
	}

	/**
	 * @param alarmNum the alarmNum to set
	 */
	public void setAlarmNum(int alarmNum) {
		this.alarmNum = alarmNum;
	}

	/**
	 * @return the alarmCheckNum
	 */
	public int getAlarmCheckNum() {
		return alarmCheckNum;
	}

	/**
	 * @param alarmCheckNum the alarmCheckNum to set
	 */
	public void setAlarmCheckNum(int alarmCheckNum) {
		this.alarmCheckNum = alarmCheckNum;
	}

	/**
	 * @return the alarmEntryAndExitNum
	 */
	public int getAlarmEntryAndExitNum() {
		return alarmEntryAndExitNum;
	}

	/**
	 * @param alarmEntryAndExitNum the alarmEntryAndExitNum to set
	 */
	public void setAlarmEntryAndExitNum(int alarmEntryAndExitNum) {
		this.alarmEntryAndExitNum = alarmEntryAndExitNum;
	}

	/**
	 * @return the alarmTalkNum
	 */
	public int getAlarmTalkNum() {
		return alarmTalkNum;
	}

	/**
	 * @param alarmTalkNum the alarmTalkNum to set
	 */
	public void setAlarmTalkNum(int alarmTalkNum) {
		this.alarmTalkNum = alarmTalkNum;
	}

	/**
	 * @return the matchOngoingNum
	 */
	public int getMatchOngoingNum() {
		return matchOngoingNum;
	}

	/**
	 * @param matchOngoingNum the matchOngoingNum to set
	 */
	public void setMatchOngoingNum(int matchOngoingNum) {
		this.matchOngoingNum = matchOngoingNum;
	}

	/**
	 * @return the matchJoinNum
	 */
	public int getMatchJoinNum() {
		return matchJoinNum;
	}

	/**
	 * @param matchJoinNum the matchJoinNum to set
	 */
	public void setMatchJoinNum(int matchJoinNum) {
		this.matchJoinNum = matchJoinNum;
	}

	/**
	 * @return the matchDepNum
	 */
	public int getMatchDepNum() {
		return matchDepNum;
	}

	/**
	 * @param matchDepNum the matchDepNum to set
	 */
	public void setMatchDepNum(int matchDepNum) {
		this.matchDepNum = matchDepNum;
	}

	/**
	 * @return the matchBranchOfficeNum
	 */
	public int getMatchBranchOfficeNum() {
		return matchBranchOfficeNum;
	}

	/**
	 * @param matchBranchOfficeNum the matchBranchOfficeNum to set
	 */
	public void setMatchBranchOfficeNum(int matchBranchOfficeNum) {
		this.matchBranchOfficeNum = matchBranchOfficeNum;
	}

	/**
	 * @return the policeList
	 */
	public List<Calculation> getPoliceList() {
		return policeList;
	}

	/**
	 * @param policeList the policeList to set
	 */
	public void setPoliceList(List<Calculation> policeList) {
		this.policeList = policeList;
	}

}
