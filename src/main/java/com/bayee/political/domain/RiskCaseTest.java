package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

public class RiskCaseTest {
    private Integer id;

    private String policeId;

    private Integer cumulativeNum;

    private Integer passNum;

    private Integer failNum;

    private Double indexNum;

    private String year;

    private Integer semester;

    private Double deductionScore;

    private Date creationDate;

    private Date updateDate;

    private Integer isDisplay;// 是否显示详情（0否1是）

    private List<ScreenDoubeChart> list;

    public List<ScreenDoubeChart> getList() {
        return list;
    }

    public void setList(List<ScreenDoubeChart> list) {
        this.list = list;
    }

    public Integer getPassNum() {
        return passNum;
    }

    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }

    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }

    public Integer getCumulativeNum() {
        return cumulativeNum;
    }

    public void setCumulativeNum(Integer cumulativeNum) {
        this.cumulativeNum = cumulativeNum;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

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

    public Double getDeductionScore() {
        return deductionScore;
    }

    public void setDeductionScore(Double deductionScore) {
        this.deductionScore = deductionScore;
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