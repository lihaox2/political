package com.bayee.political.service;

import com.bayee.political.domain.PoliceEducationLog;

import java.util.List;

/**
 * @author xxl
 * @date 2021/6/7
 */
public interface PoliceEducationLogService {

    /**
     * 通过警号查询警员学历记录
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    List<PoliceEducationLog> findEducationLodByPoliceId(String policeId, String year, String month);

}
