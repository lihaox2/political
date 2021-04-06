package com.bayee.political.domain;

import java.util.Date;

//预警记录
public class AlarmRecord {

	private Integer id;// 预警记录id
	
	private Integer alarmConfigType;// 预警时间类型（1月预警2年预警）

	private Integer type;// 考评类型（1负面考评2正面考评）

	private String typeName;// 考评类型名称

	private String headImage;// 被记分人头像

	private String policeId;// 被记分人(警号)

	private String name;// 被记分人名字

	private Integer departmentId;// 被记分人部门id

	private String departmentName;// 被记分人部门名称

	private Integer policeMonthId;// 公安月id

	private String policeMonthName;// 公安月名称

	private Double score;// 预警分值

	private Integer frequency;// 累计预警次数

	private Date firstAlarmTime;// 首次预警时间

	private Date finishAlarmTime;// 最终预警时间
	
	private Integer alarmNum;// 预警次数
	
	private Integer status;

	private Date creationDate;// 创建时间
	
	private String strTime;

	private Date updateDate;// 修改时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId == null ? null : policeId.trim();
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getPoliceMonthId() {
		return policeMonthId;
	}

	public void setPoliceMonthId(Integer policeMonthId) {
		this.policeMonthId = policeMonthId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Date getFirstAlarmTime() {
		return firstAlarmTime;
	}

	public void setFirstAlarmTime(Date firstAlarmTime) {
		this.firstAlarmTime = firstAlarmTime;
	}

	public Date getFinishAlarmTime() {
		return finishAlarmTime;
	}

	public void setFinishAlarmTime(Date finishAlarmTime) {
		this.finishAlarmTime = finishAlarmTime;
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
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	 * @return the policeMonthName
	 */
	public String getPoliceMonthName() {
		return policeMonthName;
	}

	/**
	 * @param policeMonthName the policeMonthName to set
	 */
	public void setPoliceMonthName(String policeMonthName) {
		this.policeMonthName = policeMonthName;
	}

	/**
	 * @return the frequency
	 */
	public Integer getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	/**
	 * @return the alarmNum
	 */
	public Integer getAlarmNum() {
		return alarmNum;
	}

	/**
	 * @param alarmNum the alarmNum to set
	 */
	public void setAlarmNum(Integer alarmNum) {
		this.alarmNum = alarmNum;
	}

	/**
	 * @return the alarmConfigType
	 */
	public Integer getAlarmConfigType() {
		return alarmConfigType;
	}

	/**
	 * @param alarmConfigType the alarmConfigType to set
	 */
	public void setAlarmConfigType(Integer alarmConfigType) {
		this.alarmConfigType = alarmConfigType;
	}

	/**
	 * @return the strTime
	 */
	public String getStrTime() {
		return strTime;
	}

	/**
	 * @param strTime the strTime to set
	 */
	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}