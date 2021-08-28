package com.bayee.political.domain;

import java.util.Date;

/**
 * 警员工作部门记录
 * @author xxl
 */
public class PoliceWorkingDeptLog {
    private Integer id;

    private String policeId;

    private Integer deptId;

    private String deptName;

    private String deptIcon;

    private Date workingBeginDate;

    private Date workingEndDate;

    private Date creationDate;

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

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDeptIcon() {
        return deptIcon;
    }

    public void setDeptIcon(String deptIcon) {
        this.deptIcon = deptIcon == null ? null : deptIcon.trim();
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