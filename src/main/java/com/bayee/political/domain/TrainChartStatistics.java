package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年11月1日 上午10:45:50 
*/

import java.util.List;

public class TrainChartStatistics {

	private int totalNum;// 训练总数

	private int passNum;// 合格次数

	private int passProjectNum;// 合格项目数

	private int failProjectNum;// 不合格项目数
	
	private int goodNum;// 优秀次数
	
	private int justNum;// 良好次数
	
	private int failNum;// 不合格次数

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
	 * @return the passNum
	 */
	public int getPassNum() {
		return passNum;
	}

	/**
	 * @param passNum the passNum to set
	 */
	public void setPassNum(int passNum) {
		this.passNum = passNum;
	}

	/**
	 * @return the passProjectNum
	 */
	public int getPassProjectNum() {
		return passProjectNum;
	}

	/**
	 * @param passProjectNum the passProjectNum to set
	 */
	public void setPassProjectNum(int passProjectNum) {
		this.passProjectNum = passProjectNum;
	}

	/**
	 * @return the failProjectNum
	 */
	public int getFailProjectNum() {
		return failProjectNum;
	}

	/**
	 * @param failProjectNum the failProjectNum to set
	 */
	public void setFailProjectNum(int failProjectNum) {
		this.failProjectNum = failProjectNum;
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

	/**
	 * @return the goodNum
	 */
	public int getGoodNum() {
		return goodNum;
	}

	/**
	 * @param goodNum the goodNum to set
	 */
	public void setGoodNum(int goodNum) {
		this.goodNum = goodNum;
	}

	/**
	 * @return the justNum
	 */
	public int getJustNum() {
		return justNum;
	}

	/**
	 * @param justNum the justNum to set
	 */
	public void setJustNum(int justNum) {
		this.justNum = justNum;
	}

	/**
	 * @return the failNum
	 */
	public int getFailNum() {
		return failNum;
	}

	/**
	 * @param failNum the failNum to set
	 */
	public void setFailNum(int failNum) {
		this.failNum = failNum;
	}

}
