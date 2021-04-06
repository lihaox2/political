package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年10月14日 下午5:26:06
 */
public class TrainRecordPolice {

	private int enterNum;// 已录人数

	private int totalEnterNum;// 总人数

	private Double proportion;// 已录人数比例

	private List<User> userList;
	
	private List<TrainRank> scoreList;

	/**
	 * @return the enterNum
	 */
	public int getEnterNum() {
		return enterNum;
	}

	/**
	 * @param enterNum the enterNum to set
	 */
	public void setEnterNum(int enterNum) {
		this.enterNum = enterNum;
	}

	/**
	 * @return the totalEnterNum
	 */
	public int getTotalEnterNum() {
		return totalEnterNum;
	}

	/**
	 * @param totalEnterNum the totalEnterNum to set
	 */
	public void setTotalEnterNum(int totalEnterNum) {
		this.totalEnterNum = totalEnterNum;
	}

	/**
	 * @return the proportion
	 */
	public Double getProportion() {
		return proportion;
	}

	/**
	 * @param proportion the proportion to set
	 */
	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

	/**
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * @return the scoreList
	 */
	public List<TrainRank> getScoreList() {
		return scoreList;
	}

	/**
	 * @param scoreList the scoreList to set
	 */
	public void setScoreList(List<TrainRank> scoreList) {
		this.scoreList = scoreList;
	}

}
