package com.bayee.political.json;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    /**
     * 处分结束时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 关键词
     */
    private String keyword;
}
