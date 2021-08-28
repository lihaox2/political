package com.bayee.political.pojo.dto;

import com.bayee.political.domain.PublicityInfo;

import java.util.List;

public class PublicityInfoDO extends PublicityInfo {

    /**
     * 类型名称
     */
    private String typeName;
    /**
     * 民警姓名
     */
    private String userName;
    /**
     * 民警部门名称
     */
    private String deptName;

    /**
     * 图片集合
     */
    private List<String> imgUrls;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

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

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }
}
