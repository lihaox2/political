package com.bayee.political.service;

import com.bayee.political.domain.PoliceWorkingDeptLog;

import java.util.List;

/**
 * @author xxl
 * @date 2021/6/5
 */
public interface PoliceWorkingDeptLogService {

    /**
     * 查询警员工作记录
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    List<PoliceWorkingDeptLog> findPoliceWorkingLog(String policeId, String year, String month);

}
