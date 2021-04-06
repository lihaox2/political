package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年6月5日 上午10:34:46
 */
public class CalculationColumn {
	
	private String updateDate;// 最后更新时间

	private List<CalculationChart> actualList;// 实际警力

	private List<CalculationChart> getList;// 所需警力

	/**
	 * @return the actualList
	 */
	public List<CalculationChart> getActualList() {
		return actualList;
	}

	/**
	 * @param actualList the actualList to set
	 */
	public void setActualList(List<CalculationChart> actualList) {
		this.actualList = actualList;
	}

	/**
	 * @return the getList
	 */
	public List<CalculationChart> getGetList() {
		return getList;
	}

	/**
	 * @param getList the getList to set
	 */
	public void setGetList(List<CalculationChart> getList) {
		this.getList = getList;
	}

	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}
