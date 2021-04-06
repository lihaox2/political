package com.bayee.political.domain;

import java.util.Date;

public class TrainMedalManage {
	private Integer id;

	private String name;

	private String image;

	private String greyImage;

	private Integer type;

	private Integer getNum;// 个人得到奖牌数量

	private Integer totalNum;

	private Integer issueNum;

	private Integer isBright;// 是否亮起0否1是

	private String remark;

	private Date getDate;

	private Date creationDate;

	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image == null ? null : image.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIssueNum() {
		return issueNum;
	}

	public void setIssueNum(Integer issueNum) {
		this.issueNum = issueNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the isBright
	 */
	public Integer getIsBright() {
		return isBright;
	}

	/**
	 * @param isBright the isBright to set
	 */
	public void setIsBright(Integer isBright) {
		this.isBright = isBright;
	}

	/**
	 * @return the getNum
	 */
	public Integer getGetNum() {
		return getNum;
	}

	/**
	 * @param getNum the getNum to set
	 */
	public void setGetNum(Integer getNum) {
		this.getNum = getNum;
	}

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
	 * @return the getDate
	 */
	public Date getGetDate() {
		return getDate;
	}

	/**
	 * @param getDate the getDate to set
	 */
	public void setGetDate(Date getDate) {
		this.getDate = getDate;
	}

	/**
	 * @return the greyImage
	 */
	public String getGreyImage() {
		return greyImage;
	}

	/**
	 * @param greyImage the greyImage to set
	 */
	public void setGreyImage(String greyImage) {
		this.greyImage = greyImage;
	}

}