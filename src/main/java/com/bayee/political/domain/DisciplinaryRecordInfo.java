package com.bayee.political.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 纪律处分记录表
 */
@ApiModel("纪律处分记录表")
public class DisciplinaryRecordInfo {
    /**
     * 表序列
     */
    @ApiModelProperty(value = "表序列")
    private Integer id;

    /**
     * 警号
     */
    @ApiModelProperty(value = "警号")
    private String policeId;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 处分类型id
     */
    @ApiModelProperty(value = "处分类型id")
    private Integer punishTypeId;

    /**
     * 处分原因
     */
    @ApiModelProperty(value = "处分原因")
    private String punishPunish;

    /**
     * 处分机关
     */
    @ApiModelProperty(value = "处分机关")
    private Integer punishOfficeId;

    /**
     * 处分级别id
     */
    @ApiModelProperty(value = "处分级别id")
    private Integer punishLevelId;

    /**
     * 晋升影响期
     */
    @ApiModelProperty(value = "晋升影响期")
    private Integer promotionInfluencePeriod;

    /**
     * 处分时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "处分时间")
    private Date punishTime;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 创建时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date modifyTime;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String createUser;

    /**
     * 创建者id
     */
    @ApiModelProperty(value = "创建者id")
    private Integer createUserId;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者")
    private String modifyUser;

    /**
     * 更新者id
     */
    @ApiModelProperty(value = "更新者id")
    private Integer modifyUserId;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId == null ? null : policeId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPunishTypeId() {
        return punishTypeId;
    }

    public void setPunishTypeId(Integer punishTypeId) {
        this.punishTypeId = punishTypeId;
    }

    public String getPunishPunish() {
        return punishPunish;
    }

    public void setPunishPunish(String punishPunish) {
        this.punishPunish = punishPunish == null ? null : punishPunish.trim();
    }

    public Integer getPunishOfficeId() {
        return punishOfficeId;
    }

    public void setPunishOfficeId(Integer punishOfficeId) {
        this.punishOfficeId = punishOfficeId;
    }

    public Integer getPunishLevelId() {
        return punishLevelId;
    }

    public void setPunishLevelId(Integer punishLevelId) {
        this.punishLevelId = punishLevelId;
    }

    public Integer getPromotionInfluencePeriod() {
        return promotionInfluencePeriod;
    }

    public void setPromotionInfluencePeriod(Integer promotionInfluencePeriod) {
        this.promotionInfluencePeriod = promotionInfluencePeriod =promotionInfluencePeriod;
    }

    public Date getPunishTime() {
        return punishTime;
    }

    public void setPunishTime(Date punishTime) {
        this.punishTime = punishTime;
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