package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年10月27日 下午5:53:26 
*/
public class TrainMatchLimitReport {

	private Integer isLimit;
	private int manNum;
	private String manStr;
	private int womanNum;
	private String womanStr;
	/**
	 * @return the manNum
	 */
	public int getManNum() {
		return manNum;
	}
	/**
	 * @param manNum the manNum to set
	 */
	public void setManNum(int manNum) {
		this.manNum = manNum;
	}
	/**
	 * @return the manStr
	 */
	public String getManStr() {
		return manStr;
	}
	/**
	 * @param manStr the manStr to set
	 */
	public void setManStr(String manStr) {
		this.manStr = manStr;
	}
	/**
	 * @return the womanNum
	 */
	public int getWomanNum() {
		return womanNum;
	}
	/**
	 * @param womanNum the womanNum to set
	 */
	public void setWomanNum(int womanNum) {
		this.womanNum = womanNum;
	}
	/**
	 * @return the womanStr
	 */
	public String getWomanStr() {
		return womanStr;
	}
	/**
	 * @param womanStr the womanStr to set
	 */
	public void setWomanStr(String womanStr) {
		this.womanStr = womanStr;
	}
	/**
	 * @return the isLimit
	 */
	public Integer getIsLimit() {
		return isLimit;
	}
	/**
	 * @param isLimit the isLimit to set
	 */
	public void setIsLimit(Integer isLimit) {
		this.isLimit = isLimit;
	}
	
}
