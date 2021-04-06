package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年7月29日 下午1:17:59 
*/

import java.util.List;

public class AlarmYear {

	private int num;
	
	private List<CalculationChart> yearList;

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @return the yearList
	 */
	public List<CalculationChart> getYearList() {
		return yearList;
	}

	/**
	 * @param yearList the yearList to set
	 */
	public void setYearList(List<CalculationChart> yearList) {
		this.yearList = yearList;
	}
	
}
