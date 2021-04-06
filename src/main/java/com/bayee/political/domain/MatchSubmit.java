package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年10月27日 下午7:08:27
 */
public class MatchSubmit {

	private String name;

	private int isMoreUnit;// 是否多个单位（0否1是）
	
	private int sort;

	private int enterNum;// 已录项目数

	private int totalEnterNum;// 总项目数

	private double proportion;// 百分比

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the enterNum
	 */
	public int getEnterNum() {
		return enterNum;
	}

	/**
	 * @param enterNum the enterNum to set
	 */
	public void setEnterNum(int enterNum) {
		this.enterNum = enterNum;
	}

	/**
	 * @return the totalEnterNum
	 */
	public int getTotalEnterNum() {
		return totalEnterNum;
	}

	/**
	 * @param totalEnterNum the totalEnterNum to set
	 */
	public void setTotalEnterNum(int totalEnterNum) {
		this.totalEnterNum = totalEnterNum;
	}

	/**
	 * @return the proportion
	 */
	public double getProportion() {
		return proportion;
	}

	/**
	 * @param proportion the proportion to set
	 */
	public void setProportion(double proportion) {
		this.proportion = proportion;
	}

	/**
	 * @return the isMoreUnit
	 */
	public int getIsMoreUnit() {
		return isMoreUnit;
	}

	/**
	 * @param isMoreUnit the isMoreUnit to set
	 */
	public void setIsMoreUnit(int isMoreUnit) {
		this.isMoreUnit = isMoreUnit;
	}

	/**
	 * @return the sort
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

}
