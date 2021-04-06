/**
 * 
 */
package com.bayee.political.domain;

/**
 * @author seanguo
 *
 */
public class Series {

	private String type;  // pie, line, bar
	private float[] data;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the data
	 */
	public float[] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(float[] data) {
		this.data = data;
	}

}
