package com.bayee.political.domain;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/8/19
 */
public class ConductBureauRecordViews {

    /**
     * 主键id
     */
    private String id;

    /**
     * 警号
     */
    private String policeNo;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 局规记分类型id
     */
    private String flag;

    /**
     * 记分类型名称
     */
    private String name;

    /**
     * 记分层级 0-自主记分 1-非自主记分
     */
    private Integer markType;

    /**
     * 记分单位编号
     */
    private String markOrgId;

    /**
     * 记分单位名称
     */
    private String shortName;

    /**
     * 记分依据
     */
    private String hfyj;

    /**
     * 记分条款
     */
    private String jftk;

    /**
     * 记分内容
     */
    private String jfnr;

    /**
     * 扣除分数
     */
    private Double markScore;

    /**
     * 记分备注
     */
    private String markDesc;

    /**
     * 记分时间
     */
    private Date markTime;

    /**
     * 采取措施
     */
    private String cqcs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoliceNo() {
        return policeNo;
    }

    public void setPoliceNo(String policeNo) {
        this.policeNo = policeNo;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMarkType() {
        return markType;
    }

    public void setMarkType(Integer markType) {
        this.markType = markType;
    }

    public String getMarkOrgId() {
        return markOrgId;
    }

    public void setMarkOrgId(String markOrgId) {
        this.markOrgId = markOrgId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getHfyj() {
        return hfyj;
    }

    public void setHfyj(String hfyj) {
        this.hfyj = hfyj;
    }

    public String getJftk() {
        return jftk;
    }

    public void setJftk(String jftk) {
        this.jftk = jftk;
    }

    public String getJfnr() {
        return jfnr;
    }

    public void setJfnr(String jfnr) {
        this.jfnr = jfnr;
    }

    public Double getMarkScore() {
        return markScore;
    }

    public void setMarkScore(Double markScore) {
        this.markScore = markScore;
    }

    public String getMarkDesc() {
        return markDesc;
    }

    public void setMarkDesc(String markDesc) {
        this.markDesc = markDesc;
    }

    public Date getMarkTime() {
        return markTime;
    }

    public void setMarkTime(Date markTime) {
        this.markTime = markTime;
    }

    public String getCqcs() {
        return cqcs;
    }

    public void setCqcs(String cqcs) {
        this.cqcs = cqcs;
    }
}
