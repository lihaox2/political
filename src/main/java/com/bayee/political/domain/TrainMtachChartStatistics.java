package com.bayee.political.domain;

import java.util.List;

/** 
* @author  shentuqiwei 
* @version 2020年11月19日 下午10:43:18 
*/
public class TrainMtachChartStatistics {

	private int totalNum;// 赛事总数量

	private int firstNum;// 赛事第一名数量

	private int secondNum;// 赛事第二名数量

	private int thirdNum;// 赛事第三名数量
	
	private List<CalculationChart> chartList;

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
	 * @return the firstNum
	 */
	public int getFirstNum() {
		return firstNum;
	}

	/**
	 * @param firstNum the firstNum to set
	 */
	public void setFirstNum(int firstNum) {
		this.firstNum = firstNum;
	}

	/**
	 * @return the secondNum
	 */
	public int getSecondNum() {
		return secondNum;
	}

	/**
	 * @param secondNum the secondNum to set
	 */
	public void setSecondNum(int secondNum) {
		this.secondNum = secondNum;
	}

	/**
	 * @return the thirdNum
	 */
	public int getThirdNum() {
		return thirdNum;
	}

	/**
	 * @param thirdNum the thirdNum to set
	 */
	public void setThirdNum(int thirdNum) {
		this.thirdNum = thirdNum;
	}

	/**
	 * @return the chartList
	 */
	public List<CalculationChart> getChartList() {
		return chartList;
	}

	/**
	 * @param chartList the chartList to set
	 */
	public void setChartList(List<CalculationChart> chartList) {
		this.chartList = chartList;
	}
}
