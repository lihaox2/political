package com.bayee.political.json;

import lombok.Data;

import java.util.Date;

/**
 * @author
 * @Title:
 * @Description: )
 * @date 2021/11/25 10:54
 */
@Data
public class PolicePromotionsResult {
    /**
     * 处分年份
     */
    private Date punishTime;

    /**
     * 处分时间
     */
    private Integer sum;
}
