package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年10月23日 下午6:54:46 
*/

import java.util.List;

public class TrainLeaderMoreAchievement {

	private int yesNum;// 已签到人数

	private int noNum;// 未签到人数

	private int totalNum;// 总人数

	private List<TrainRecommendPolice> yesList;// 已签到人

	private List<TrainRecommendPolice> noList;// 未签到人

	/**
	 * @return the yesNum
	 */
	public int getYesNum() {
		return yesNum;
	}

	/**
	 * @param yesNum the yesNum to set
	 */
	public void setYesNum(int yesNum) {
		this.yesNum = yesNum;
	}

	/**
	 * @return the noNum
	 */
	public int getNoNum() {
		return noNum;
	}

	/**
	 * @param noNum the noNum to set
	 */
	public void setNoNum(int noNum) {
		this.noNum = noNum;
	}

	/**
	 * @return the totalNum
	 */
	public int getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * @return the yesList
	 */
	public List<TrainRecommendPolice> getYesList() {
		return yesList;
	}

	/**
	 * @param yesList the yesList to set
	 */
	public void setYesList(List<TrainRecommendPolice> yesList) {
		this.yesList = yesList;
	}

	/**
	 * @return the noList
	 */
	public List<TrainRecommendPolice> getNoList() {
		return noList;
	}

	/**
	 * @param noList the noList to set
	 */
	public void setNoList(List<TrainRecommendPolice> noList) {
		this.noList = noList;
	}
}
