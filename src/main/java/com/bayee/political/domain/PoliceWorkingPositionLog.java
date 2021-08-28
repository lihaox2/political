package com.bayee.political.domain;

import java.util.Date;

/**
 * 警员工作职务记录
 * @author xxl
 */
public class PoliceWorkingPositionLog {
    private Integer id;

    private Integer workingDeptLogId;

    private String policeId;

    private String position;

    private String positionLabel;

    private String workingDesc;

    private String workingPlace;

    private Date workingBeginDate;

    private Date workingEndDate;

    private Date creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkingDeptLogId() {
        return workingDeptLogId;
    }

    public void setWorkingDeptLogId(Integer workingDeptLogId) {
        this.workingDeptLogId = workingDeptLogId;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId == null ? null : policeId.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getPositionLabel() {
        return positionLabel;
    }

    public void setPositionLabel(String positionLabel) {
        this.positionLabel = positionLabel == null ? null : positionLabel.trim();
    }

    public String getWorkingDesc() {
        return workingDesc;
    }

    public void setWorkingDesc(String workingDesc) {
        this.workingDesc = workingDesc == null ? null : workingDesc.trim();
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace == null ? null : workingPlace.trim();
    }

    public Date getWorkingBeginDate() {
        return workingBeginDate;
    }

    public void setWorkingBeginDate(Date workingBeginDate) {
        this.workingBeginDate = workingBeginDate;
    }

    public Date getWorkingEndDate() {
        return workingEndDate;
    }

    public void setWorkingEndDate(Date workingEndDate) {
        this.workingEndDate = workingEndDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}