package com.bayee.political.domain;

public class LeaveOvertimeRule {
    private Integer id;

    private Double hour;

    private Double day1;

    private Double day2;
    
    private Double day3;

    public LeaveOvertimeRule(Integer id, Double hour, Double day1, Double day2, Double day3) {
		super();
		this.id = id;
		this.hour = hour;
		this.day1 = day1;
		this.day2 = day2;
		this.day3 = day3;
	}

	public LeaveOvertimeRule() {
		super();
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getHour() {
        return hour;
    }

    public void setHour(Double hour) {
        this.hour = hour;
    }

    public Double getDay1() {
        return day1;
    }

    public void setDay1(Double day1) {
        this.day1 = day1;
    }

    public Double getDay2() {
        return day2;
    }

    public void setDay2(Double day2) {
        this.day2 = day2;
    }

	public Double getDay3() {
		return day3;
	}

	public void setDay3(Double day3) {
		this.day3 = day3;
	}
    
}