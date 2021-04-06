package com.bayee.political.domain;

import java.util.Date;

public class EvaluateAuthority {
	private Integer id;

	private String name;

	private Integer participantId;
	
	private String participantName;
	
	private String targetListName;

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


	/**
	 * @return the participantId
	 */
	public Integer getParticipantId() {
		return participantId;
	}

	/**
	 * @param participantId the participantId to set
	 */
	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}

	/**
	 * @return the participantName
	 */
	public String getParticipantName() {
		return participantName;
	}

	/**
	 * @param participantName the participantName to set
	 */
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
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
	 * @return the targetListName
	 */
	public String getTargetListName() {
		return targetListName;
	}

	/**
	 * @param targetListName the targetListName to set
	 */
	public void setTargetListName(String targetListName) {
		this.targetListName = targetListName;
	}

}