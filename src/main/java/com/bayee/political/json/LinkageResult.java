package com.bayee.political.json;

import lombok.Data;

import java.util.List;

/**
 * @author lichenghu
 * @Title: 下拉框，联动选择器的返回
 * @Description: )
 * @date 2021/11/15 17:02
 */
@Data
public class LinkageResult {

    /**
     * 名称
     */
    private String label;

    /**
     * 选项值
     */
    private Integer value;

    private List<LinkageResult> children;
}
