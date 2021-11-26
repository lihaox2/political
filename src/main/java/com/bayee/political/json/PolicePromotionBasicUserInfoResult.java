package com.bayee.political.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author
 * @Title: 用户信息
 * @Description: )
 * @date 2021/11/26 11:48
 */
@Data
public class PolicePromotionBasicUserInfoResult {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户头像
     */
    private String headImage;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 职务id
     */
    private Integer positionLevel;

    /**
     * 职务名称
     */
    private String positionLevelName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 族别
     */
    private String nation;

    /**
     *政治面貌
     */
    private String politicalStatus;

    /**
     * 参加工作时间
     */
    @JsonFormat( pattern="yyyy-MM-dd")
    private Date workingStartDate;

    /**
     * 参加公安时间
     */
    @JsonFormat( pattern="yyyy-MM-dd")
    private Date employmentDate;

    /**
     * 部门id
     */
    private Integer depId;

    /**
     * 部门id
     */
    private String depName;

    /**
     * 现职级 id
     */
    private Integer policePosition;

    /**
     * 现职级名称
     */
    private String positionName;

    /**
     * 加入本单位时间
     */
    @JsonFormat( pattern="yyyy-MM-dd")
    private  Date joiningPartyTime;

    /**
     * 警种id
     */
    private Integer kindId;

    /**
     * 警种名称
     */
    private String policeKingName;

    /**
     * 现职级批准时间
     */
    @JsonFormat( pattern="yyyy-MM-dd")
    private Date policePositionAssignDate;

    /**
     * 岗位id
     */
    private Integer stationPostId;

    /**
     * 是否婚配
     */
    private Integer marriageStatus;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 要晋升的职级id
     */
    private Integer nextPoliceLevelId;


    /**
     * 要晋升的职级
     */
    private String nextPoliceLevelName;
}
