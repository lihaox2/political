package com.bayee.political.domain;

import java.util.Date;

public class AlarmPoliceMonth {
    private Integer id;

    private String policeMonth;

    private Date creationDate;

    private Date updateDate;
    
    private Integer count;//数量

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoliceMonth() {
        return policeMonth;
    }

    public void setPoliceMonth(String policeMonth) {
        this.policeMonth = policeMonth == null ? null : policeMonth.trim();
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
    
}