package com.bayee.political.service.impl;

import com.bayee.political.domain.PoliceWorkingPositionLog;
import com.bayee.political.mapper.PoliceWorkingPositionLogMapper;
import com.bayee.political.service.PoliceWorkingPositionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/6/5
 */
@Service
public class PoliceWorkingPositionLogServiceImpl implements PoliceWorkingPositionLogService {

    @Autowired
    PoliceWorkingPositionLogMapper policeWorkingPositionLogMapper;

    @Override
    public List<PoliceWorkingPositionLog> findByDeptLogIdAndPoliceId(Integer deptLogId, String policeId) {
        return policeWorkingPositionLogMapper.findByDeptLogIdAndPoliceId(deptLogId, policeId);
    }
}
