package com.bayee.political.domain;

import java.util.Date;

public class EvaluateAuthorityTarget {
	
	private Integer id;// 评价对象列表id

	private Integer authorityId;// 评价权限id

	private Integer targetId;// 评价对象id

	private Double proportion;// 评价参与人占比

	private Date creationDate;// 创建时间

	private Date updateDate;// 修改时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Double getProportion() {
		return proportion;
	}

	public void setProportion(Double proportion) {
		this.proportion = proportion;
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
	 * @return the authorityId
	 */
	public Integer getAuthorityId() {
		return authorityId;
	}

	/**
	 * @param authorityId the authorityId to set
	 */
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}
	
}