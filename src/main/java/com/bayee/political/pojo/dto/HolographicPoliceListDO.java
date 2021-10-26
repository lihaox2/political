package com.bayee.political.pojo.dto;

/**
 * @author xxl
 * @date 2021/6/5
 */
public class HolographicPoliceListDO {

    /**
     * 警号
     */
    private String policeId;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 人才标签
     */
    private String labelName;

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

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

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
