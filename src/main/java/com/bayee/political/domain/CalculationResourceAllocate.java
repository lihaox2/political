package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年11月23日 下午6:15:18
 */
public class CalculationResourceAllocate {
	
	private Integer policeType;
	
	private Integer exceedDepId;

	private String exceedDepName;// 超过的派出所名称
	
	private String exceedNumStr;
	
	private Integer lackDepId;

	private String lackDepName;// 缺少的派出所名称
	
	private String lackNumStr;

	/**
	 * @return the policeType
	 */
	public Integer getPoliceType() {
		return policeType;
	}

	/**
	 * @param policeType the policeType to set
	 */
	public void setPoliceType(Integer policeType) {
		this.policeType = policeType;
	}

	/**
	 * @return the exceedDepId
	 */
	public Integer getExceedDepId() {
		return exceedDepId;
	}

	/**
	 * @param exceedDepId the exceedDepId to set
	 */
	public void setExceedDepId(Integer exceedDepId) {
		this.exceedDepId = exceedDepId;
	}

	/**
	 * @return the exceedDepName
	 */
	public String getExceedDepName() {
		return exceedDepName;
	}

	/**
	 * @param exceedDepName the exceedDepName to set
	 */
	public void setExceedDepName(String exceedDepName) {
		this.exceedDepName = exceedDepName;
	}

	/**
	 * @return the exceedNumStr
	 */
	public String getExceedNumStr() {
		return exceedNumStr;
	}

	/**
	 * @param exceedNumStr the exceedNumStr to set
	 */
	public void setExceedNumStr(String exceedNumStr) {
		this.exceedNumStr = exceedNumStr;
	}

	/**
	 * @return the lackNumStr
	 */
	public String getLackNumStr() {
		return lackNumStr;
	}

	/**
	 * @param lackNumStr the lackNumStr to set
	 */
	public void setLackNumStr(String lackNumStr) {
		this.lackNumStr = lackNumStr;
	}

	/**
	 * @return the lackDepId
	 */
	public Integer getLackDepId() {
		return lackDepId;
	}

	/**
	 * @param lackDepId the lackDepId to set
	 */
	public void setLackDepId(Integer lackDepId) {
		this.lackDepId = lackDepId;
	}

	/**
	 * @return the lackDepName
	 */
	public String getLackDepName() {
		return lackDepName;
	}

	/**
	 * @param lackDepName the lackDepName to set
	 */
	public void setLackDepName(String lackDepName) {
		this.lackDepName = lackDepName;
	}

	
}
