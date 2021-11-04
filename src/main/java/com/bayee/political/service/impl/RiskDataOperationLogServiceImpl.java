package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskDataOperationLog;
import com.bayee.political.mapper.RiskDataOperationLogMapper;
import com.bayee.political.service.RiskDataOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xxl
 * @date 2021/11/1
 */
@Service
public class RiskDataOperationLogServiceImpl implements RiskDataOperationLogService {

    @Autowired
    RiskDataOperationLogMapper riskDataOperationLogMapper;

    @Override
    public void insertOperationLog(RiskDataOperationLog operationLog) {
        riskDataOperationLogMapper.insert(operationLog);
    }
}
