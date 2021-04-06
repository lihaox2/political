/**
 * 
 */
package com.bayee.political.domain;

/**
 * @author seanguo
 *
 */
public class UserEvaluation {

	private long id;
	private long userId;
	private String ddUserId;
	private String policeId;
	private String result;
	private String evaluateYear;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the ddUserId
	 */
	public String getDdUserId() {
		return ddUserId;
	}

	/**
	 * @param ddUserId the ddUserId to set
	 */
	public void setDdUserId(String ddUserId) {
		this.ddUserId = ddUserId;
	}

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
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the evaluateYear
	 */
	public String getEvaluateYear() {
		return evaluateYear;
	}

	/**
	 * @param evaluateYear the evaluateYear to set
	 */
	public void setEvaluateYear(String evaluateYear) {
		this.evaluateYear = evaluateYear;
	}

}
