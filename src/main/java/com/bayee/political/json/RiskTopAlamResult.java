package com.bayee.political.json;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @author
 * @Title:
 * @Description: )
 * @date 2021/11/22 18:19
 */
@Data
public class RiskTopAlamResult {

    /**
     * 新本月新增预警人数
     */
    private Integer toMonth;

    /**
     * 较上月新增预警人数
     */
    private Integer upMonth;
}
