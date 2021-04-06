package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年12月1日 下午1:36:35
 */
public class AlarmRecordChart {

	private String name;// 名称
	private double num;// 数量/占比
	private int frequencys;// 统计次数
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
	 * @return the num
	 */
	public double getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(double num) {
		this.num = num;
	}
	/**
	 * @return the frequencys
	 */
	public int getFrequencys() {
		return frequencys;
	}
	/**
	 * @param frequencys the frequencys to set
	 */
	public void setFrequencys(int frequencys) {
		this.frequencys = frequencys;
	}

}
