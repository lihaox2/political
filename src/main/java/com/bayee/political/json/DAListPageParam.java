package com.bayee.political.json;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @author lichenghu
 * @Title: 纪律处分的分页查询
 * @Description: )
 * @date 2021/11/15 14:40
 */
@Data
@ApiModel("纪律处分的分页查询")
public class DAListPageParam {

    /**
     * 当前页数
     */
    private Integer pageIndex;

    /**
     * 当前页数据条数
     */
    private Integer pageSize;

    /**
     * 处分级别id
     */
    private Integer punishLevelId;

    /**
     * 处分机关
     */
    private Integer punishOfficeId;

    /**
     * 处分类型id
     */
    private Integer punishTypeId;

    /**
     * 处分开始时间
     */
    private Date beginTime;

    /**
     * 处分结束时间
     */
    private Date end;

    /**
     * 关键词
     */
    private String keyword;
}
