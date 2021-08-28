package com.bayee.political.pojo.dto;

import com.bayee.political.domain.PublicityUserInfo;

public class PublicityUserInfoDO extends PublicityUserInfo {

    /**
     * 姓名
     */
    private String userName;
    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 头像
     */
    private String headImg;


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

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
