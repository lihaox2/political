package com.bayee.political.domain;

import java.util.Date;

public class EvaluateTemplate {

	private Integer id;// 评价模板id

	private EvaluateAuthority authority;//权限表id
	
	private String templateName;// 模板类型（1个人2单位3项目）

	private Integer templateType;// 模板名称

	private Integer targetId;// 评价对象id
	
	private String targetName;// 评价对象名称

	private Integer rankId;// 评价等级id
	
	private String rankName;// 评价等级名称

	private Integer departmentId;// 部门id
	private String departmentName;// 部门名
	private Integer parentId;// 父id
	private Integer type;//
	private Date creationDate;// 创建时间

	private Date updateDate;// 修改时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName == null ? null : templateName.trim();
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Integer getRankId() {
		return rankId;
	}

	public void setRankId(Integer rankId) {
		this.rankId = rankId;
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
	 * @return the departmentId
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
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
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the templateType
	 */
	public Integer getTemplateType() {
		return templateType;
	}

	/**
	 * @param templateType the templateType to set
	 */
	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	/**
	 * @return the targetName
	 */
	public String getTargetName() {
		return targetName;
	}

	/**
	 * @param targetName the targetName to set
	 */
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	/**
	 * @return the rankName
	 */
	public String getRankName() {
		return rankName;
	}

	/**
	 * @param rankName the rankName to set
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public EvaluateAuthority getAuthority() {
		return authority;
	}

	public void setAuthority(EvaluateAuthority authority) {
		this.authority = authority;
	}

}