package com.bayee.political.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 警员晋升历史记录表
 */
public class PolicePromotionRecordInfo {
    /**
     * 表序列
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 头像
     */
    private String headImage;

    /**
     * 部门id
     */
    private Integer depId;

    /**
     * 部门名称
     */
    private String depName;

    /**
     * 警员职务id
     */
    private Integer postId;

    /**
     * 警员职务
     */
    private String postName;

    /**
     * 当前警员级别id
     */
    private Integer nowPoliceLevelId;

    /**
     * 当前警员级别名称
     */
    private String nowPoliceLevelName;

    /**
     * 晋升的警员级别id
     */
    private Integer nextPoliceLevelId;

    /**
     * 晋升的警员级别名称
     */
    private String nextPoliceLevelName;

    /**
     * 晋升类型（0是一般晋升，1是量化晋升）
     */
    private Integer type;

    /**
     * 上一次晋升的时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastTime;

    /**
     * 现在晋升时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date nowTime;

    /**
     * 距离上一次晋升的时间（按月计算）
     */
    private Integer interval;

    /**
     * 履历分
     */
    private Double resumeScore;

    /**
     * 入职分
     */
    private Double holdOfficeScore;

    /**
     * 考评总分（履历分+入职分）
     */
    private Double totalScore;

    /**
     * 是否被评为公务员（0不是，1是）
     */
    private Integer isCivilServant;

    /**
     * 是否被纪律处分
     */
    private Integer isDisciplinaryAction;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     * 创建者
     */
    private String createUser;

    /**
     * 创建者id
     */
    private Integer createUserId;

    /**
     * 更新者
     */
    private String modifyUser;

    /**
     * 更新者id
     */
    private Integer modifyUserId;

    /**
     * 备注
     */
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId == null ? null : policeId.trim();
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName == null ? null : depName.trim();
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    public Integer getNowPoliceLevelId() {
        return nowPoliceLevelId;
    }

    public void setNowPoliceLevelId(Integer nowPoliceLevelId) {
        this.nowPoliceLevelId = nowPoliceLevelId;
    }

    public String getNowPoliceLevelName() {
        return nowPoliceLevelName;
    }

    public void setNowPoliceLevelName(String nowPoliceLevelName) {
        this.nowPoliceLevelName = nowPoliceLevelName == null ? null : nowPoliceLevelName.trim();
    }

    public Integer getNextPoliceLevelId() {
        return nextPoliceLevelId;
    }

    public void setNextPoliceLevelId(Integer nextPoliceLevelId) {
        this.nextPoliceLevelId = nextPoliceLevelId;
    }

    public String getNextPoliceLevelName() {
        return nextPoliceLevelName;
    }

    public void setNextPoliceLevelName(String nextPoliceLevelName) {
        this.nextPoliceLevelName = nextPoliceLevelName == null ? null : nextPoliceLevelName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Double getResumeScore() {
        return resumeScore;
    }

    public void setResumeScore(Double resumeScore) {
        this.resumeScore = resumeScore;
    }

    public Double getHoldOfficeScore() {
        return holdOfficeScore;
    }

    public void setHoldOfficeScore(Double holdOfficeScore) {
        this.holdOfficeScore = holdOfficeScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getIsCivilServant() {
        return isCivilServant;
    }

    public void setIsCivilServant(Integer isCivilServant) {
        this.isCivilServant = isCivilServant;
    }

    public Integer getIsDisciplinaryAction() {
        return isDisciplinaryAction;
    }

    public void setIsDisciplinaryAction(Integer isDisciplinaryAction) {
        this.isDisciplinaryAction = isDisciplinaryAction;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}