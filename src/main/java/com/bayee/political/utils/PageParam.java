package com.bayee.political.utils;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * 分页查询参数
 * @Author xxl
 * @Date 2021/2/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam {

    /**
     * 当前页码数
     */
    @ApiModelProperty(value = "页码数", example = "1", required = true)
    @NonNull
    private Integer pageIndex;

    /**
     * 数据条数
     */
    @ApiModelProperty(value = "数据条数", example = "5", required = true)
    @NotNull
    private Integer pageSize;

    public Integer getPageIndex() {
        if (this.pageIndex == null || this.pageIndex <= 0) {
            this.pageIndex = 1;
        }
        return this.pageIndex;
    }

    public Integer getPageSize() {
        if (this.pageSize == null || this.pageSize <= 0) {
            this.pageSize = 10;
        }
        return this.pageSize;
    }

}
