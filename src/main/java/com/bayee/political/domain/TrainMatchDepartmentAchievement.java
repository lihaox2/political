package com.bayee.political.domain;

import java.util.Date;

public class TrainMatchDepartmentAchievement {
    private Integer id;

    private Integer trainMatchId;

    private Integer departmentId;

    private Date achievementDate;

    private Double achievement;

    private String achievementGrade;

    private Integer isSign;

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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Date getAchievementDate() {
        return achievementDate;
    }

    public void setAchievementDate(Date achievementDate) {
        this.achievementDate = achievementDate;
    }

    public Double getAchievement() {
        return achievement;
    }

    public void setAchievement(Double achievement) {
        this.achievement = achievement;
    }

    public String getAchievementGrade() {
        return achievementGrade;
    }

    public void setAchievementGrade(String achievementGrade) {
        this.achievementGrade = achievementGrade == null ? null : achievementGrade.trim();
    }

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
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