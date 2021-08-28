package com.bayee.political.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 警员全息最近搜索
 */
public class PoliceRecentlySearch {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 警员头像
     */
    private String policeHeadPhoto;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 警员部门名称
     */
    private String policeDeptName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoliceHeadPhoto() {
        return policeHeadPhoto;
    }

    public void setPoliceHeadPhoto(String policeHeadPhoto) {
        this.policeHeadPhoto = policeHeadPhoto;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getPoliceDeptName() {
        return policeDeptName;
    }

    public void setPoliceDeptName(String policeDeptName) {
        this.policeDeptName = policeDeptName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}