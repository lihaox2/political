package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年10月26日 下午11:51:47
 */
public class MatchLeaderStatistics {

	private int signUpNum;// 报名中数量

	private int matchNotStartedNum;// 比赛未开始数量

	private int matchOngoingNum;// 比赛进行中数量

	private int matchOverNum;// 比赛已结束数量

	private List<TrainMatch> matchList;

	/**
	 * @return the signUpNum
	 */
	public int getSignUpNum() {
		return signUpNum;
	}

	/**
	 * @param signUpNum the signUpNum to set
	 */
	public void setSignUpNum(int signUpNum) {
		this.signUpNum = signUpNum;
	}

	/**
	 * @return the matchNotStartedNum
	 */
	public int getMatchNotStartedNum() {
		return matchNotStartedNum;
	}

	/**
	 * @param matchNotStartedNum the matchNotStartedNum to set
	 */
	public void setMatchNotStartedNum(int matchNotStartedNum) {
		this.matchNotStartedNum = matchNotStartedNum;
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
	 * @return the matchOverNum
	 */
	public int getMatchOverNum() {
		return matchOverNum;
	}

	/**
	 * @param matchOverNum the matchOverNum to set
	 */
	public void setMatchOverNum(int matchOverNum) {
		this.matchOverNum = matchOverNum;
	}

	/**
	 * @return the matchList
	 */
	public List<TrainMatch> getMatchList() {
		return matchList;
	}

	/**
	 * @param matchList the matchList to set
	 */
	public void setMatchList(List<TrainMatch> matchList) {
		this.matchList = matchList;
	}
	
}
