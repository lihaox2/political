/**
 * 
 */
package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

/**
 * @author seanguo
 *
 */
public class Department {

	private int id;
	private String name;
	private int parentId;
	private Department parentDepartment;
	List<EvaluateTaskParticipant> evaluateTaskParticipantList;
	private Date creationDate;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the parentDepartment
	 */
	public Department getParentDepartment() {
		return parentDepartment;
	}

	/**
	 * @param parentDepartment the parentDepartment to set
	 */
	public void setParentDepartment(Department parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the evaluateTaskParticipantList
	 */
	public List<EvaluateTaskParticipant> getEvaluateTaskParticipantList() {
		return evaluateTaskParticipantList;
	}

	/**
	 * @param evaluateTaskParticipantList the evaluateTaskParticipantList to set
	 */
	public void setEvaluateTaskParticipantList(List<EvaluateTaskParticipant> evaluateTaskParticipantList) {
		this.evaluateTaskParticipantList = evaluateTaskParticipantList;
	}

}
