package com.bayee.political.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lichenghu
 * @Title: 下拉框，联动选择器的返回
 * @Description: )
 * @date 2021/11/15 17:02
 */
@Data
@ApiModel("下拉框，联动选择器的返回")
public class LinkageResult {

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String label;

    /**
     * 选项值
     */
    @ApiModelProperty(value = "选项值")
    private Integer value;

    @ApiModelProperty(value = "子级")
    private List<LinkageResult> children;
}
