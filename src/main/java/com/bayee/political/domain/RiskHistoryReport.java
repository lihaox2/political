package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2021年3月30日 下午5:47:36
 */
public class RiskHistoryReport {

	private String policeId;

	private Double totalNum;

	private String dateTimeMonth;
	
	private String dateTime;

	/**
	 * @return the policeId
	 */
	public String getPoliceId() {
		return policeId;
	}

	/**
	 * @param policeId the policeId to set
	 */
	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}

	/**
	 * @return the totalNum
	 */
	public Double getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(Double totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * @return the dateTime
	 */
	public String getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @return the dateTimeMonth
	 */
	public String getDateTimeMonth() {
		return dateTimeMonth;
	}

	/**
	 * @param dateTimeMonth the dateTimeMonth to set
	 */
	public void setDateTimeMonth(String dateTimeMonth) {
		this.dateTimeMonth = dateTimeMonth;
	}
	
	
}
