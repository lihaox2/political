package com.bayee.political.json;

import java.util.List;
import java.util.Map;

public class RiskTrendResult {
	
	private Integer policeTotal;
	
	private Integer risktotal ;
	
	private Double proportion;
	
	private List<Map<String,Object>>  riskTrends;

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
