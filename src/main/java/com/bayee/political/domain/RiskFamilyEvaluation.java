package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

public class RiskFamilyEvaluation {
    private Integer id;

    private String policeId;

    private Double indexNum;
    
    private String year;
    
    private String month;

    private Integer rankId;

    private String comment;

    private Date creationDate;

    private Date updateDate;
    
    private List<ScreenDoubeChart> list;

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
        if (null == indexNum) {
            return null;
        }
        return Math.min(indexNum, 5);
    }

    public void setIndexNum(Double indexNum) {
        this.indexNum = indexNum;
    }

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
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

	public List<ScreenDoubeChart> getList() {
		return list;
	}

	public void setList(List<ScreenDoubeChart> list) {
		this.list = list;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	
}