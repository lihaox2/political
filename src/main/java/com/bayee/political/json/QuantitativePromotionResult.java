package com.bayee.political.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author lichenghu
 * @Title: 量化晋升返回数据
 * @Description: )
 * @date 2021/11/16 18:17
 */
@Data
@ApiModel("量化晋升返回数据")
public class QuantitativePromotionResult {


    /**
     * 是否已经晋升(0未晋升，1晋升)
     */
    private Integer isPromotion;

    /**
     * 表序列
     */
    @ApiModelProperty("表序列")
    private Integer id;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 警号
     */
    @ApiModelProperty("警号")
    private String policeId;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String headImage;

    /**
     * 部门id
     */
    @ApiModelProperty("部门id")
    private Integer depId;

    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    private String depName;

    /**
     * 警员职务id
     */
    @ApiModelProperty("警员职务id")
    private Integer postId;

    /**
     * 警员职务
     */
    @ApiModelProperty("警员职务")
    private String postName;

    /**
     * 当前警员级别id
     */
    @ApiModelProperty("当前警员级别id")
    private Integer nowPoliceLevelId;

    /**
     * 当前警员级别名称
     */
    @ApiModelProperty("当前警员级别名称")
    private String nowPoliceLevelName;

    /**
     * 晋升的警员级别id
     */
    @ApiModelProperty("晋升的警员级别id")
    private Integer nextPoliceLevelId;

    /**
     * 晋升的警员级别名称
     */
    @ApiModelProperty("晋升的警员级别名称")
    private String nextPoliceLevelName;

    /**
     * 上一次晋升的时间
     */
    @ApiModelProperty("上一次晋升的时间")
    @JsonFormat( pattern="yyyy-MM")
    private Date lastTime;

    /**
     * 现在晋升时间
     */
    @ApiModelProperty("当前晋升时间")
    @JsonFormat( pattern="yyyy-MM")
    private Date nowTime;

    /**
     * 距离上一次晋升的时间（按月计算）
     */
    @ApiModelProperty("距离上一次晋升的时间（按月计算）")
    private Integer interval;

    /**
     * 履历分
     */
    @ApiModelProperty("履历分")
    private Double resumeScore;

    /**
     * 任职分
     */
    @ApiModelProperty("任职分")
    private Double holdOfficeScore;

    /**
     * 考评总分（履历分+入职分）
     */
    @ApiModelProperty("考评总分（履历分+入职分）")
    private Double totalScore;

    /**
     * 是否被评为公务员（0不是，1是）
     */
    @ApiModelProperty("是否被评为公务员（0不是，1是）")
    private Integer isCivilServant;

    /**
     * 是否被纪律处分
     */
    @ApiModelProperty("是否被纪律处分")
    private Integer isDisciplinaryAction;

    /**
     * 排名
     */
    @ApiModelProperty(value = "排名")
    private Integer ranking;
}
