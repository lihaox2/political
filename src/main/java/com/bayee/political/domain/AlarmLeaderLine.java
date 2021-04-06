package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年7月30日 下午3:47:43 
*    预警人数趋势
*/

import java.util.List;

public class AlarmLeaderLine {

	private Integer totalNum;// 当月总数

	private Double proportion;// 环比

	private Integer identifier;// 标识符(1显示具体数据2显示“--”)

	private List<CalculationChart> chartList;

	/**
	 * @return the totalNum
	 */
	public Integer getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
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
	 * @return the identifier
	 */
	public Integer getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
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
