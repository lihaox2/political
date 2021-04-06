package com.bayee.political.domain;

public class AlarmConfig {

	private Integer id;

	private Integer type;// 预警类型（1月预警2年预警）
	private Double score;
	private Integer thresholdType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getThresholdType() {
		return thresholdType;
	}

	public void setThresholdType(Integer thresholdType) {
		this.thresholdType = thresholdType;
	}

	@Override
	public String toString() {
		return "AlarmConfig [id=" + id + ", score=" + score + ", thresholdType=" + thresholdType + "]";
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

}
