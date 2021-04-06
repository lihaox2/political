package com.bayee.political.utils;

public class DataListChart {
	// 返回信息 success fail
	String message;

	// 返回状态 true false
	boolean status;

	// 返回状态码
	int code;

	// 返回数据集合
	Object totalResult;

	// 返回数据集合
	Object personResult;

	// 返回数据集合
	Object companyResult;

	// 返回数据集合
	Object objectResult;

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the totalResult
	 */
	public Object getTotalResult() {
		return totalResult;
	}

	/**
	 * @param totalResult the totalResult to set
	 */
	public void setTotalResult(Object totalResult) {
		this.totalResult = totalResult;
	}

	/**
	 * @return the personResult
	 */
	public Object getPersonResult() {
		return personResult;
	}

	/**
	 * @param personResult the personResult to set
	 */
	public void setPersonResult(Object personResult) {
		this.personResult = personResult;
	}

	/**
	 * @return the companyResult
	 */
	public Object getCompanyResult() {
		return companyResult;
	}

	/**
	 * @param companyResult the companyResult to set
	 */
	public void setCompanyResult(Object companyResult) {
		this.companyResult = companyResult;
	}

	/**
	 * @return the objectResult
	 */
	public Object getObjectResult() {
		return objectResult;
	}

	/**
	 * @param objectResult the objectResult to set
	 */
	public void setObjectResult(Object objectResult) {
		this.objectResult = objectResult;
	}

	

}
