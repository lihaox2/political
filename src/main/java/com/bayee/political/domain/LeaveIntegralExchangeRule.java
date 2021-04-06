package com.bayee.political.domain;

public class LeaveIntegralExchangeRule {
    private Integer id;

    private Integer integralValue;
    
    private Double day;

    public LeaveIntegralExchangeRule(Integer id, Integer integralValue, Double day) {
		super();
		this.id = id;
		this.integralValue = integralValue;
		this.day = day;
	}
    
	public LeaveIntegralExchangeRule() {
		super();
	}
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

	public Integer getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(Integer integralValue) {
		this.integralValue = integralValue;
	}
	
}