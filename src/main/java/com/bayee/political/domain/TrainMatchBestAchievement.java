package com.bayee.political.domain;

import java.util.Date;

public class TrainMatchBestAchievement {
    private Integer id;

    private Integer trainMatchId;

    private Integer type;

    private String policeId;

    private Integer departmentId;

    private Date trainStartDate;

    private Date trainEndDate;

    private String achievementStr;

    private Integer rankId;

    private Date creationDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainMatchId() {
        return trainMatchId;
    }

    public void setTrainMatchId(Integer trainMatchId) {
        this.trainMatchId = trainMatchId;
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

    public Date getTrainStartDate() {
        return trainStartDate;
    }

    public void setTrainStartDate(Date trainStartDate) {
        this.trainStartDate = trainStartDate;
    }

    public Date getTrainEndDate() {
        return trainEndDate;
    }

    public void setTrainEndDate(Date trainEndDate) {
        this.trainEndDate = trainEndDate;
    }

    public String getAchievementStr() {
        return achievementStr;
    }

    public void setAchievementStr(String achievementStr) {
        this.achievementStr = achievementStr == null ? null : achievementStr.trim();
    }

    /**
	 * @return the rankId
	 */
	public Integer getRankId() {
		return rankId;
	}

	/**
	 * @param rankId the rankId to set
	 */
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
}