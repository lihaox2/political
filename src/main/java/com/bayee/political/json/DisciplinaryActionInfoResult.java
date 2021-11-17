package com.bayee.political.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author lichenghu
 * @Title: 纪律处理的详情返回
 * @Description: )
 * @date 2021/11/15 14:47
 */
@Data
@ApiModel("纪律处理的详情返回")
public class DisciplinaryActionInfoResult {

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
     * 处分类型名称
     */
    @ApiModelProperty(value = "处分类型名称")
    private String punishTypeName;

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
     * 处分机关名称
     */
    @ApiModelProperty(value = "处分机关名称")
    private String punishOfficeName;

    /**
     * 处分级别id
     */
    @ApiModelProperty(value = "处分级别id")
    private Integer punishLevelId;

    /**
     * 处分级别名称
     */
    @ApiModelProperty(value = "处分级别名称")
    private String punishLevelName;

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
}
