package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年7月29日 下午3:16:10
 */
public class AlarmLeaderStatistics {

	private Integer totalNum;// 当月总数
	
	private Double totalScore;// 当月总分

	private Double proportion;// 环比

	private Integer identifier;// 标识符(1显示具体数据2显示“--”)

	private Integer receiveNum;// 约谈已同意数量

	private Integer overNum;// 约谈已完成数量

	/**
	 * @return the totalNum
	 */
	public Integer getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * @return the proportion
	 */
	public Double getProportion() {
		return proportion;
	}

	/**
	 * @param proportion the proportion to set
	 */
	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

	/**
	 * @return the identifier
	 */
	public Integer getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the receiveNum
	 */
	public Integer getReceiveNum() {
		return receiveNum;
	}

	/**
	 * @param receiveNum the receiveNum to set
	 */
	public void setReceiveNum(Integer receiveNum) {
		this.receiveNum = receiveNum;
	}

	/**
	 * @return the overNum
	 */
	public Integer getOverNum() {
		return overNum;
	}

	/**
	 * @param overNum the overNum to set
	 */
	public void setOverNum(Integer overNum) {
		this.overNum = overNum;
	}

	/**
	 * @return the totalScore
	 */
	public Double getTotalScore() {
		return totalScore;
	}

	/**
	 * @param totalScore the totalScore to set
	 */
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

}
