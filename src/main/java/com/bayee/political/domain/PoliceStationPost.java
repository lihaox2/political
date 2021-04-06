package com.bayee.political.domain;

import java.util.List;

public class PoliceStationPost {

	private Integer id;

	private String postName;// 岗位名称

	private Integer postType;// 1民警岗位2辅警岗位

	private List<Calculation> calculationOverList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName == null ? null : postName.trim();
	}

	/**
	 * @return the calculationOverList
	 */
	public List<Calculation> getCalculationOverList() {
		return calculationOverList;
	}

	/**
	 * @param calculationOverList the calculationOverList to set
	 */
	public void setCalculationOverList(List<Calculation> calculationOverList) {
		this.calculationOverList = calculationOverList;
	}

	/**
	 * @return the postType
	 */
	public Integer getPostType() {
		return postType;
	}

	/**
	 * @param postType the postType to set
	 */
	public void setPostType(Integer postType) {
		this.postType = postType;
	}
	
}