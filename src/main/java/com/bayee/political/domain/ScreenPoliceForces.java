package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2021年2月24日 下午10:35:16 
*/

import java.util.List;

public class ScreenPoliceForces {

	private List<CalculationChart> strikeList;// 打击岗list
	
	private List<CalculationChart> basicList;// 基础防控list
	
	private List<CalculationChart> comprehensiveList;// 综合岗list
	
	private List<CalculationChart> backOfficeList;// 户籍内勤list
	
	private List<CalculationChart> leaderList;// 所领导list

	/**
	 * @return the strikeList
	 */
	public List<CalculationChart> getStrikeList() {
		return strikeList;
	}

	/**
	 * @param strikeList the strikeList to set
	 */
	public void setStrikeList(List<CalculationChart> strikeList) {
		this.strikeList = strikeList;
	}

	/**
	 * @return the basicList
	 */
	public List<CalculationChart> getBasicList() {
		return basicList;
	}

	/**
	 * @param basicList the basicList to set
	 */
	public void setBasicList(List<CalculationChart> basicList) {
		this.basicList = basicList;
	}

	/**
	 * @return the comprehensiveList
	 */
	public List<CalculationChart> getComprehensiveList() {
		return comprehensiveList;
	}

	/**
	 * @param comprehensiveList the comprehensiveList to set
	 */
	public void setComprehensiveList(List<CalculationChart> comprehensiveList) {
		this.comprehensiveList = comprehensiveList;
	}

	/**
	 * @return the backOfficeList
	 */
	public List<CalculationChart> getBackOfficeList() {
		return backOfficeList;
	}

	/**
	 * @param backOfficeList the backOfficeList to set
	 */
	public void setBackOfficeList(List<CalculationChart> backOfficeList) {
		this.backOfficeList = backOfficeList;
	}

	/**
	 * @return the leaderList
	 */
	public List<CalculationChart> getLeaderList() {
		return leaderList;
	}

	/**
	 * @param leaderList the leaderList to set
	 */
	public void setLeaderList(List<CalculationChart> leaderList) {
		this.leaderList = leaderList;
	}
	
}
