/**
 * 
 */
package com.bayee.political.domain;

/**
 * @author shawnkuo
 * 
 */
public class ResponseStatus {
	
	public final static int ERROR = 0;
	public final static int OK = 1;

	private int status;
	private int errorCode;
	private String errorMessage;
	private Object result;
	
	public ResponseStatus() {
		
	}
	
	public ResponseStatus(int status) {
		this.status = status;
	}
	
	public ResponseStatus(int status, String errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
	}
	
	public ResponseStatus(int status, int errorCode, String errorMessage) {
		this.status = status;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
