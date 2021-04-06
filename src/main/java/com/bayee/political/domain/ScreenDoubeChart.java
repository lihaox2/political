package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2021年3月18日 下午5:51:13 
*/
public class ScreenDoubeChart {

	private int id;// 岗位id
	private String name;// 名称
	private double value;// 数量
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
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
	 * @return the value
	 */
	public double getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
}
