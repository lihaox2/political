package com.bayee.political.domain;

import java.util.List;

public class CalculationProject {
    private Integer id;

    private String caseName;
    
    private List<CalculationDetail> calculationDetailList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName == null ? null : caseName.trim();
    }

	/**
	 * @return the calculationDetailList
	 */
	public List<CalculationDetail> getCalculationDetailList() {
		return calculationDetailList;
	}

	/**
	 * @param calculationDetailList the calculationDetailList to set
	 */
	public void setCalculationDetailList(List<CalculationDetail> calculationDetailList) {
		this.calculationDetailList = calculationDetailList;
	}

    
}