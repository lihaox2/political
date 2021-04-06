package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年11月23日 下午8:38:21 警力预警趋势
*/

import java.util.List;

public class CalculationAlarmTrend {

	private Integer policeStationId;

	private String policeStationName;
	
	private List<CalculationChart> lackList;// 紧缺预警
	
	private List<CalculationChart> exceedList;// 超出预警

	/**
	 * @return the policeStationId
	 */
	public Integer getPoliceStationId() {
		return policeStationId;
	}

	/**
	 * @param policeStationId the policeStationId to set
	 */
	public void setPoliceStationId(Integer policeStationId) {
		this.policeStationId = policeStationId;
	}

	/**
	 * @return the policeStationName
	 */
	public String getPoliceStationName() {
		return policeStationName;
	}

	/**
	 * @param policeStationName the policeStationName to set
	 */
	public void setPoliceStationName(String policeStationName) {
		this.policeStationName = policeStationName;
	}

	/**
	 * @return the lackList
	 */
	public List<CalculationChart> getLackList() {
		return lackList;
	}

	/**
	 * @param lackList the lackList to set
	 */
	public void setLackList(List<CalculationChart> lackList) {
		this.lackList = lackList;
	}

	/**
	 * @return the exceedList
	 */
	public List<CalculationChart> getExceedList() {
		return exceedList;
	}

	/**
	 * @param exceedList the exceedList to set
	 */
	public void setExceedList(List<CalculationChart> exceedList) {
		this.exceedList = exceedList;
	}

}
