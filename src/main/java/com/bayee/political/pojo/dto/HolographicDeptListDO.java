package com.bayee.political.pojo.dto;

/**
 * @author xxl
 * @date 2021/7/13
 */
public class HolographicDeptListDO {

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门人员数
     */
    private Integer policeCount;

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
        this.deptName = deptName;
    }

    public Integer getPoliceCount() {
        return policeCount;
    }

    public void setPoliceCount(Integer policeCount) {
        this.policeCount = policeCount;
    }
}
