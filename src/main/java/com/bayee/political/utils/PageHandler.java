package com.bayee.political.utils;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author xxl
 * @Date 2021/2/24
 */
public class PageHandler<T> {

    /**
     * 当前页码数
     */
    @ApiModelProperty(value = "当前页码数", example = "1")
    @Getter
    private Integer pageIndex;

    /**
     * 数据条数
     */
    @ApiModelProperty(value = "显示数据条数", example = "10")
    @Getter
    private Integer pageSize;

    /**
     * 总数据条数
     */
    @ApiModelProperty(value = "总数据条数", example = "10")
    @Getter
    @Setter
    private Integer totalCount;

    /**
     * 数据集
     */
    @ApiModelProperty(value = "数据集", example = "")
    @Getter
    @Setter
    private List<T> data;

    /*public PageHandler(Page<T> page) {
        this.pageIndex = (int) page.getCurrent();
        this.pageSize = (int) page.getSize();
        this.totalCount = (int) page.getTotal();
        this.data = page.getRecords();
    }*/

    public PageHandler(PageInfo<T> pageInfo) {
        this.pageIndex = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.data = pageInfo.getList();
        this.totalCount = (int)pageInfo.getTotal();
    }

    public PageHandler(PageParam pageParam) {
        this.pageIndex = pageParam.getPageIndex();
        this.pageSize = pageParam.getPageSize();
    }

    public PageHandler(Integer pageIndex, Integer pageSize, Integer totalCount, List<T> data) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.data = data;
    }

}
