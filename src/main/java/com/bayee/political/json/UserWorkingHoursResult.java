package com.bayee.political.json;

import lombok.Data;

import java.util.Date;

/**
 * @author
 * @Title:
 * @Description: )
 * @date 2021/11/25 10:40
 */
@Data
public class UserWorkingHoursResult {

    /**
     * 警员id
     */
    private String policeId;

    /**
     * 参加工作的时间
     */
    private Date workingStartDate;
    /**
     * 职务id
     */
    private Integer positionId;
}
