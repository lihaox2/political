package com.bayee.political.json;

import java.util.List;
import java.util.Map;

public class RiskTrendResult {

	/**
	 * 在线警员数
	 */
	private Integer policeTotal;

	/**
	 * 当前风险人数
	 */
	private Integer risktotal ;

	/**
	 * 风险比例
	 */
	private Double proportion;
	
	private List<Map<String,Object>>  riskTrends;

	/**
	 * 年度总预警人数
	 */
	private Integer alarmPoliceCount;

	public Integer getAlarmPoliceCount() {
		return alarmPoliceCount;
	}

	public void setAlarmPoliceCount(Integer alarmPoliceCount) {
		this.alarmPoliceCount = alarmPoliceCount;
	}

	public Integer getPoliceTotal() {
		return policeTotal;
	}

	public void setPoliceTotal(Integer policeTotal) {
		this.policeTotal = policeTotal;
	}

	public Integer getRisktotal() {
		return risktotal;
	}

	public void setRisktotal(Integer risktotal) {
		this.risktotal = risktotal;
	}

	public List<Map<String, Object>> getRiskTrends() {
		return riskTrends;
	}

	public void setRiskTrends(List<Map<String, Object>> riskTrends) {
		this.riskTrends = riskTrends;
	}

	public Double getProportion() {
		return proportion;
	}

	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

}
