package com.bayee.political.utils;

public class DataListReturn {
	// 返回信息 success fail
	String message;
	
	// 返回状态 true false
	boolean status;

	//返回状态码
	int code;
	
	// 返回数据集合
	Object result;

	/**
	 * 成功返回
	 * @param result
	 * @return
	 */
	public static DataListReturn ok(Object result) {
		DataListReturn data = new DataListReturn();
		data.setCode(200);
		data.setMessage("success");
		data.setStatus(true);
		data.setResult(result);
		return data;
	}

	/**
	 * 成功返回
	 * @return
	 */
	public static DataListReturn ok() {
		DataListReturn data = new DataListReturn();
		data.setCode(200);
		data.setMessage("success");
		data.setStatus(true);
		return data;
	}

	/**
	 * 成功返回
	 * @return
	 */
	public static DataListReturn error(String desc) {
		DataListReturn data = new DataListReturn();
		data.setCode(500);
		data.setMessage("error");
		data.setStatus(false);
		data.setResult(desc);
		return data;
	}

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
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}

}
