package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

public class RiskCaseTestRecord {
    private Integer id;

    private String policeId;

    private String year;

    private Integer semester;

    private Double score;

    private Date creationDate;

    private Date updateDate;
    
    private Double indexNum;
    
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

	public Double getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(Double indexNum) {
		this.indexNum = indexNum;
	}

	public List<ScreenDoubeChart> getList() {
		return list;
	}

	public void setList(List<ScreenDoubeChart> list) {
		this.list = list;
	}
    
}