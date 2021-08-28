package com.bayee.political.json;

import java.util.Date;
import java.util.List;

/**
 *
 * @author xxl
 */
public class PoliceWorkingDeptLogResult {

    /**
     * 类型8-工作经历
     */
    private Integer type = 8;

    /**
     * id
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门图标
     */
    private String deptIcon;

    /**
     * 时间信息(yyyy - yyyy)
     */
    private String dateInfo;

    /**
     * 开始时间
     */
    private String beginDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 职务记录
     */
    private List<PoliceWorkingPositionLogResult> positionLogResults;

    /**
     * sort
     */
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptIcon() {
        return deptIcon;
    }

    public void setDeptIcon(String deptIcon) {
        this.deptIcon = deptIcon;
    }

    public String getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(String dateInfo) {
        this.dateInfo = dateInfo;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<PoliceWorkingPositionLogResult> getPositionLogResults() {
        return positionLogResults;
    }

    public void setPositionLogResults(List<PoliceWorkingPositionLogResult> positionLogResults) {
        this.positionLogResults = positionLogResults;
    }
}
