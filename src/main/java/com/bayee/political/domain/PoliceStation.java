package com.bayee.political.domain;

import java.util.List;

public class PoliceStation {

	private Integer id;

	private String policeStationName;// 派出所名称

	private Double areaCoefficient;// 面积系数

	private Double industryCoefficient;// 行业场所系数

	private Double populationCoefficient;// 常住人口系数

	private Double studentCoefficient;// 外来人口系数

	private List<PoliceStationPost> policeStationPostList;// 岗位表
	private List<CalculationProject> policeProjectList;// 案件类型测算表

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPoliceStationName() {
		return policeStationName;
	}

	public void setPoliceStationName(String policeStationName) {
		this.policeStationName = policeStationName == null ? null : policeStationName.trim();
	}

	/**
	 * @return the policeStationPostList
	 */
	public List<PoliceStationPost> getPoliceStationPostList() {
		return policeStationPostList;
	}

	/**
	 * @param policeStationPostList the policeStationPostList to set
	 */
	public void setPoliceStationPostList(List<PoliceStationPost> policeStationPostList) {
		this.policeStationPostList = policeStationPostList;
	}

	/**
	 * @return the areaCoefficient
	 */
	public Double getAreaCoefficient() {
		return areaCoefficient;
	}

	/**
	 * @param areaCoefficient the areaCoefficient to set
	 */
	public void setAreaCoefficient(Double areaCoefficient) {
		this.areaCoefficient = areaCoefficient;
	}

	/**
	 * @return the industryCoefficient
	 */
	public Double getIndustryCoefficient() {
		return industryCoefficient;
	}

	/**
	 * @param industryCoefficient the industryCoefficient to set
	 */
	public void setIndustryCoefficient(Double industryCoefficient) {
		this.industryCoefficient = industryCoefficient;
	}

	/**
	 * @return the populationCoefficient
	 */
	public Double getPopulationCoefficient() {
		return populationCoefficient;
	}

	/**
	 * @param populationCoefficient the populationCoefficient to set
	 */
	public void setPopulationCoefficient(Double populationCoefficient) {
		this.populationCoefficient = populationCoefficient;
	}

	/**
	 * @return the studentCoefficient
	 */
	public Double getStudentCoefficient() {
		return studentCoefficient;
	}

	/**
	 * @param studentCoefficient the studentCoefficient to set
	 */
	public void setStudentCoefficient(Double studentCoefficient) {
		this.studentCoefficient = studentCoefficient;
	}

	/**
	 * @return the policeProjectList
	 */
	public List<CalculationProject> getPoliceProjectList() {
		return policeProjectList;
	}

	/**
	 * @param policeProjectList the policeProjectList to set
	 */
	public void setPoliceProjectList(List<CalculationProject> policeProjectList) {
		this.policeProjectList = policeProjectList;
	}

}