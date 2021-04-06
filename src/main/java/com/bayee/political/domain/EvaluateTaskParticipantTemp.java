package com.bayee.political.domain;

import java.util.Date;

public class EvaluateTaskParticipantTemp {
    private Integer id;

    private Integer taskId;

    private Integer type;

    private String policeId;

    private String headImage;

    private Integer departmentId;

    private Integer positionId;

    private String objectId;

    private String objectName;

    private String description;

    private Integer rankId;

    private Integer rankDetailId;

    private Double score;

    private Double proportion;

    private Integer participantId;

    private Integer status;

    private String reason;

    private Integer operation;

    private Integer isIgnore;

    private Date creationDate;

    private Date updateDate;
    
    private Integer num;
    
    private Integer maxNum;
    
    private String detailName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName == null ? null : objectName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public Integer getRankDetailId() {
        return rankDetailId;
    }

    public void setRankDetailId(Integer rankDetailId) {
        this.rankDetailId = rankDetailId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Integer getIsIgnore() {
        return isIgnore;
    }

    public void setIsIgnore(Integer isIgnore) {
        this.isIgnore = isIgnore;
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
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * @return the maxNum
	 */
	public Integer getMaxNum() {
		return maxNum;
	}

	/**
	 * @param maxNum the maxNum to set
	 */
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	/**
	 * @return the detailName
	 */
	public String getDetailName() {
		return detailName;
	}

	/**
	 * @param detailName the detailName to set
	 */
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}
    
}