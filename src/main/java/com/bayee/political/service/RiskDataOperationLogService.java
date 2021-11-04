package com.bayee.political.service;

import com.bayee.political.domain.RiskDataOperationLog;

/**
 * @author xxl
 * @date 2021/11/1
 */
public interface RiskDataOperationLogService {

    /**
     * 创建操作记录
     * @param operationLog
     */
    void insertOperationLog(RiskDataOperationLog operationLog);

}
