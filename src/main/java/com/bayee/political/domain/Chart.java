/**
 * 
 */
package com.bayee.political.domain;

/**
 * @author seanguo
 *
 */
public class Chart {

	private String title;
	private String[] categories; // 柱状图或折线图等的分类名
	private String yAxisTitle; // y轴名称
	private Series[] series;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the categories
	 */
	public String[] getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	/**
	 * @return the yAxisTitle
	 */
	public String getyAxisTitle() {
		return yAxisTitle;
	}

	/**
	 * @param yAxisTitle the yAxisTitle to set
	 */
	public void setyAxisTitle(String yAxisTitle) {
		this.yAxisTitle = yAxisTitle;
	}

	/**
	 * @return the series
	 */
	public Series[] getSeries() {
		return series;
	}

	/**
	 * @param series the series to set
	 */
	public void setSeries(Series[] series) {
		this.series = series;
	}

}
