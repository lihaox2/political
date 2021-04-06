package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年10月29日 下午2:53:03
 */
public class TrainMedalPerson {

	private String headImage;

	private String policeId;

	private String name;

	private int medalNum;// 奖章数量

	private double exceedRate;// 奖章超过率

	/**
	 * @return the medalNum
	 */
	public int getMedalNum() {
		return medalNum;
	}

	/**
	 * @param medalNum the medalNum to set
	 */
	public void setMedalNum(int medalNum) {
		this.medalNum = medalNum;
	}

	/**
	 * @return the exceedRate
	 */
	public double getExceedRate() {
		return exceedRate;
	}

	/**
	 * @param exceedRate the exceedRate to set
	 */
	public void setExceedRate(double exceedRate) {
		this.exceedRate = exceedRate;
	}

	/**
	 * @return the headImage
	 */
	public String getHeadImage() {
		return headImage;
	}

	/**
	 * @param headImage the headImage to set
	 */
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
