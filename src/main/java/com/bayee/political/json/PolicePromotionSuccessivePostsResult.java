package com.bayee.political.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author
 * @Title:历任职级
 * @Description: )
 * @date 2021/11/26 11:53
 */
@Data
public class PolicePromotionSuccessivePostsResult {


    /**
     * 部门id
     */
    private Integer depId;

    /**
     * 部门名称
     */
    private String depName;

    /**
     * 当前警员级别名称
     */
    private String nowPoliceLevelName;


    /**
     * 现在晋升时间
     */
    @JsonFormat( pattern="yyyy-MM-dd")
    private Date nowTime;
}
