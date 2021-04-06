/**
 * 
 */
package com.bayee.political.domain;

/**
 * @author seanguo
 *
 */
public class DepartmentAnnualLeaveRatio {

	private long id;
	private String name;
	private int deptAnnualLeaveCount;
	private int deptTotal;
	private float deptAnnualLeaveRatio;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
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
	 * @return the deptAnnualLeaveCount
	 */
	public int getDeptAnnualLeaveCount() {
		return deptAnnualLeaveCount;
	}

	/**
	 * @param deptAnnualLeaveCount the deptAnnualLeaveCount to set
	 */
	public void setDeptAnnualLeaveCount(int deptAnnualLeaveCount) {
		this.deptAnnualLeaveCount = deptAnnualLeaveCount;
	}

	/**
	 * @return the deptTotal
	 */
	public int getDeptTotal() {
		return deptTotal;
	}

	/**
	 * @param deptTotal the deptTotal to set
	 */
	public void setDeptTotal(int deptTotal) {
		this.deptTotal = deptTotal;
	}

	/**
	 * @return the deptAnnualLeaveRatio
	 */
	public float getDeptAnnualLeaveRatio() {
		return deptAnnualLeaveRatio;
	}

	/**
	 * @param deptAnnualLeaveRatio the deptAnnualLeaveRatio to set
	 */
	public void setDeptAnnualLeaveRatio(float deptAnnualLeaveRatio) {
		this.deptAnnualLeaveRatio = deptAnnualLeaveRatio;
	}

}
