package com.bayee.political.json;

public class RiskProportionResult {
	
	private Integer value;
	
	private String name;
	
	private Double alarmPoliceRate;// 预警人数/存在的风险指数人数占比

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAlarmPoliceRate() {
		return alarmPoliceRate;
	}

	public void setAlarmPoliceRate(Double alarmPoliceRate) {
		this.alarmPoliceRate = alarmPoliceRate;
	}
}
