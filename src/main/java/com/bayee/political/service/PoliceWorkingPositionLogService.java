package com.bayee.political.service;

import com.bayee.political.domain.PoliceWorkingPositionLog;

import java.util.List;

/**
 * @author xxl
 * @date 2021/6/5
 */
public interface PoliceWorkingPositionLogService {

    /**
     * 查询警员部门职务记录
     * @param deptLogId
     * @param policeId
     * @return
     */
    List<PoliceWorkingPositionLog> findByDeptLogIdAndPoliceId(Integer deptLogId, String policeId);

}
