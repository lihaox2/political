package com.bayee.political.domain;

public class LeaveRestAlarmRule {
    private Integer id;

    private Double day;

    private String value;

    private Integer type;

    public LeaveRestAlarmRule() {
    }

    public LeaveRestAlarmRule(Integer id, Double day, String value) {
		super();
		this.id = id;
		this.day = day;
		this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}