package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年10月26日 下午1:19:06 
*/

import java.util.List;

public class MatchStatistics {

	private int depNum;// 赛事数量

	private int officeNum;// 我的赛事数量
	
	private List<TrainMatch> matchList;

	/**
	 * @return the depNum
	 */
	public int getDepNum() {
		return depNum;
	}

	/**
	 * @param depNum the depNum to set
	 */
	public void setDepNum(int depNum) {
		this.depNum = depNum;
	}

	/**
	 * @return the officeNum
	 */
	public int getOfficeNum() {
		return officeNum;
	}

	/**
	 * @param officeNum the officeNum to set
	 */
	public void setOfficeNum(int officeNum) {
		this.officeNum = officeNum;
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