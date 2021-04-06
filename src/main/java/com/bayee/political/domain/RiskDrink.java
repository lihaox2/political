package com.bayee.political.domain;

import java.util.Date;

public class RiskDrink {
    private Integer id;

    private String policeId;

    private Double indexNum;

    private Double threeTimesScore;

    private Double monthScore;

    private Double workDayScore;

    private Double friendScore;

    private Date creationDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId == null ? null : policeId.trim();
    }

    public Double getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Double indexNum) {
        this.indexNum = indexNum;
    }

    public Double getThreeTimesScore() {
        return threeTimesScore;
    }

    public void setThreeTimesScore(Double threeTimesScore) {
        this.threeTimesScore = threeTimesScore;
    }

    public Double getMonthScore() {
        return monthScore;
    }

    public void setMonthScore(Double monthScore) {
        this.monthScore = monthScore;
    }

    public Double getWorkDayScore() {
        return workDayScore;
    }

    public void setWorkDayScore(Double workDayScore) {
        this.workDayScore = workDayScore;
    }

    public Double getFriendScore() {
        return friendScore;
    }

    public void setFriendScore(Double friendScore) {
        this.friendScore = friendScore;
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