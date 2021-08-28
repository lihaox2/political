package com.bayee.political.pojo.dto;

public class DepartmentDO {

    /**
     * 民警部门名称
     */
    private String deptName;

    /**
     * 数量
     */
    private Integer countNum;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }
}
