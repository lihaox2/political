package com.bayee.political.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author
 * @Title:
 * @Description: )
 * @date 2021/11/25 16:56
 */
@Data
public class PolicePromotionRecordResult {

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
    @JsonFormat( pattern="yyyy-MM")
    private Date lastTime;

    /**
     * 现在晋升时间
     */
    @JsonFormat( pattern="yyyy-MM")
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
     * 任职分
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
     * 影响晋升期数
     */
    private Integer sum;

}
