package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2021年3月19日 下午12:15:08
 */
public class BigScreenPoliceForces {

	private List<ScreenChart> strikeList;// 打击岗list

	private List<ScreenChart> basicList;// 基础防控list

	private List<ScreenChart> comprehensiveList;// 综合岗list

	private List<ScreenChart> backOfficeList;// 户籍内勤list

	private List<ScreenChart> leaderList;// 所领导list

	/**
	 * @return the strikeList
	 */
	public List<ScreenChart> getStrikeList() {
		return strikeList;
	}

	/**
	 * @param strikeList the strikeList to set
	 */
	public void setStrikeList(List<ScreenChart> strikeList) {
		this.strikeList = strikeList;
	}

	/**
	 * @return the basicList
	 */
	public List<ScreenChart> getBasicList() {
		return basicList;
	}

	/**
	 * @param basicList the basicList to set
	 */
	public void setBasicList(List<ScreenChart> basicList) {
		this.basicList = basicList;
	}

	/**
	 * @return the comprehensiveList
	 */
	public List<ScreenChart> getComprehensiveList() {
		return comprehensiveList;
	}

	/**
	 * @param comprehensiveList the comprehensiveList to set
	 */
	public void setComprehensiveList(List<ScreenChart> comprehensiveList) {
		this.comprehensiveList = comprehensiveList;
	}

	/**
	 * @return the backOfficeList
	 */
	public List<ScreenChart> getBackOfficeList() {
		return backOfficeList;
	}

	/**
	 * @param backOfficeList the backOfficeList to set
	 */
	public void setBackOfficeList(List<ScreenChart> backOfficeList) {
		this.backOfficeList = backOfficeList;
	}

	/**
	 * @return the leaderList
	 */
	public List<ScreenChart> getLeaderList() {
		return leaderList;
	}

	/**
	 * @param leaderList the leaderList to set
	 */
	public void setLeaderList(List<ScreenChart> leaderList) {
		this.leaderList = leaderList;
	}
	
}
