package com.bayee.political.pojo.dto;

public class UserDO {

    /**
     * 民警名称
     */
    private String userName;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 总数
     */
    private Integer countNum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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
