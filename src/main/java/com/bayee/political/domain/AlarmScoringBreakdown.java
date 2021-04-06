package com.bayee.political.domain;

import java.util.Date;

public class AlarmScoringBreakdown {
    private Integer id;

    private Integer scoreItems;

    private String scoringBreakdown;

    private Date creationDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScoreItems() {
        return scoreItems;
    }

    public void setScoreItems(Integer scoreItems) {
        this.scoreItems = scoreItems;
    }

    public String getScoringBreakdown() {
        return scoringBreakdown;
    }

    public void setScoringBreakdown(String scoringBreakdown) {
        this.scoringBreakdown = scoringBreakdown == null ? null : scoringBreakdown.trim();
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