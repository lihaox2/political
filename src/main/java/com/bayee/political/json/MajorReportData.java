package com.bayee.political.json;

/**
 * @author tlt
 * @date 2021/11/8
 */
public class MajorReportData {

    /**
     * 报告人
     */
    private String policeName;

    /**
     * 单位名称
     */
    private String deptName;

    /**
     * 职位
     */
    private String position;

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
