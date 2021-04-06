package com.bayee.political.utils;

public class DataListCalculation {
	// 返回信息 success fail
	String message;

	// 返回状态 true false
	boolean status;

	// 返回状态码
	int code;

	// 返回数据集合
	Object actualResult;

	// 返回数据集合
	Object needResult;

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
	 * @return the actualResult
	 */
	public Object getActualResult() {
		return actualResult;
	}

	/**
	 * @param actualResult the actualResult to set
	 */
	public void setActualResult(Object actualResult) {
		this.actualResult = actualResult;
	}

	/**
	 * @return the needResult
	 */
	public Object getNeedResult() {
		return needResult;
	}

	/**
	 * @param needResult the needResult to set
	 */
	public void setNeedResult(Object needResult) {
		this.needResult = needResult;
	}

}
