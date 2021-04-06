package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年7月20日 下午2:54:06
 */
public class LeaveDepAnnualChart {

	private double averageNum;// 年假平均值
	private List<LeaveChart> leaveDepAnnualChartList; // 各部门年假使用率

	/**
	 * @return the averageNum
	 */
	public double getAverageNum() {
		return averageNum;
	}

	/**
	 * @param averageNum the averageNum to set
	 */
	public void setAverageNum(double averageNum) {
		this.averageNum = averageNum;
	}

	/**
	 * @return the leaveDepAnnualChartList
	 */
	public List<LeaveChart> getLeaveDepAnnualChartList() {
		return leaveDepAnnualChartList;
	}

	/**
	 * @param leaveDepAnnualChartList the leaveDepAnnualChartList to set
	 */
	public void setLeaveDepAnnualChartList(List<LeaveChart> leaveDepAnnualChartList) {
		this.leaveDepAnnualChartList = leaveDepAnnualChartList;
	}

}
