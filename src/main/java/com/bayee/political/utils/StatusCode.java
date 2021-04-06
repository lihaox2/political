package com.bayee.political.utils;

/**
 * 返回数字.
 */
public class StatusCode {

	static final int SUCCESSCODE = 200;// 返回成功状态码

	static final int FAILCODE = 100;// 返回失败状态码

	static final int ACCOUNTDISABLECODE = 300;// 账号被禁用状态码

	static final String ADDEDSUCCESSFULLY = "新增成功";// 新增成功
	
	static final String ADDEDFAILED = "新增失败";// 新增失败

	/**
	 * @return the successcode
	 */
	public static int getSuccesscode() {
		return SUCCESSCODE;
	}

	/**
	 * @return the failcode
	 */
	public static int getFailcode() {
		return FAILCODE;
	}

	/**
	 * @return the accountdisablecode
	 */
	public static int getAccountdisablecode() {
		return ACCOUNTDISABLECODE;
	}

	/**
	 * @return the addedsuccessfully
	 */
	public static String getAddedsuccessfully() {
		return ADDEDSUCCESSFULLY;
	}

	/**
	 * @return the addedfailed
	 */
	public static String getAddedfailed() {
		return ADDEDFAILED;
	}
}
