package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

public class TrainActivityStyle {

	private Integer id;

	private Integer objectId;// 1活动风采2训练标兵3训练章程

	private String objectName;// 1活动风采2训练标兵3训练章程

	private String name;// 活动名称、章程名称、训练名称

	private Integer view;// 浏览量

	private String filePath;// 文件路径

	private String headImg;// 警员头像

	private String policeId;// 警员id

	private String policeName;// 警员姓名

	private String departmentName;

	private String coverImg;// 封面

	private Integer likeNum;// 点赞数量

	private List<TrainLikeRecord> likeList;// 点赞人列表

	private int evaluateNum;// 评论数量
	
	private List<TrainEvaluateRecord> evaluateList;// 评论列表

	private Integer isRecommend;// 是否推荐(1否2是)

	private Integer status;// 是否上架(1已上架2已下架)

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

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg == null ? null : coverImg.trim();
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	 * @return the view
	 */
	public Integer getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(Integer view) {
		this.view = view;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the headImg
	 */
	public String getHeadImg() {
		return headImg;
	}

	/**
	 * @param headImg the headImg to set
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
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
	 * @return the policeName
	 */
	public String getPoliceName() {
		return policeName;
	}

	/**
	 * @param policeName the policeName to set
	 */
	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	/**
	 * @return the objectId
	 */
	public Integer getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the objectName
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * @param objectName the objectName to set
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the evaluateNum
	 */
	public int getEvaluateNum() {
		return evaluateNum;
	}

	/**
	 * @param evaluateNum the evaluateNum to set
	 */
	public void setEvaluateNum(int evaluateNum) {
		this.evaluateNum = evaluateNum;
	}

	/**
	 * @return the likeList
	 */
	public List<TrainLikeRecord> getLikeList() {
		return likeList;
	}

	/**
	 * @param likeList the likeList to set
	 */
	public void setLikeList(List<TrainLikeRecord> likeList) {
		this.likeList = likeList;
	}

	/**
	 * @return the evaluateList
	 */
	public List<TrainEvaluateRecord> getEvaluateList() {
		return evaluateList;
	}

	/**
	 * @param evaluateList the evaluateList to set
	 */
	public void setEvaluateList(List<TrainEvaluateRecord> evaluateList) {
		this.evaluateList = evaluateList;
	}

}