package com.bayee.political.json;

import lombok.Data;

import java.util.Date;

/**
 * @author lichenghu
 * @Title: 纪律处理的详情返回
 * @Description: )
 * @date 2021/11/15 14:47
 */
@Data
public class DisciplinaryActionInfoResult {

    /**
     * 表序列
     */
    private Integer id;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 处分类型id
     */
    private Integer punishTypeId;

    /**
     * 处分类型名称
     */
    private String punishTypeName;

    /**
     * 处分原因
     */
    private String punishPunish;

    /**
     * 处分机关
     */
    private Integer punishOfficeId;

    /**
     * 处分机关名称
     */
    private String punishOfficeName;

    /**
     * 处分级别id
     */
    private Integer punishLevelId;

    /**
     * 处分级别名称
     */
    private String punishLevelName;

    /**
     * 晋升影响期
     */
    private Integer promotionInfluencePeriod;

    /**
     * 处分时间
     */
    private Date punishTime;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
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
     * 说明
     */
    private String remarks;
}
