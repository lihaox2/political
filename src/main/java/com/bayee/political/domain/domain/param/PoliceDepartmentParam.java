package com.bayee.political.domain.domain.param;

import lombok.Data;

/**
 * @author lichenghu
 * @Title: 获取部门传参
 * @Description: )
 * @date 2021/11/17 10:59
 */
@Data
public class PoliceDepartmentParam {
    /**
     * 请求码
     */
    private String REQUESTID;

    /**
     *开始标识
     */
    private Integer BEGINID;

    /**
     * 获取数据的最大条数
     */
    private Integer MAXROWS;
}
