package com.bayee.political.domain;

import java.util.Date;

public class AlarmScoringSonBreakdown {
    private Integer id;

    private Integer scoringBreakdownId;

    private String scoringSonBreakdown;

    private Date creationDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScoringBreakdownId() {
        return scoringBreakdownId;
    }

    public void setScoringBreakdownId(Integer scoringBreakdownId) {
        this.scoringBreakdownId = scoringBreakdownId;
    }

    public String getScoringSonBreakdown() {
        return scoringSonBreakdown;
    }

    public void setScoringSonBreakdown(String scoringSonBreakdown) {
        this.scoringSonBreakdown = scoringSonBreakdown == null ? null : scoringSonBreakdown.trim();
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