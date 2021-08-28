package com.bayee.political.service.impl;

import com.bayee.political.domain.PoliceWorkingDeptLog;
import com.bayee.political.mapper.PoliceWorkingDeptLogMapper;
import com.bayee.political.service.PoliceWorkingDeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/6/5
 */
@Service
public class PoliceWorkingDeptLogServiceImpl implements PoliceWorkingDeptLogService {

    @Autowired
    PoliceWorkingDeptLogMapper policeWorkingDeptLogMapper;

    @Override
    public List<PoliceWorkingDeptLog> findPoliceWorkingLog(String policeId, String year, String month) {
        return policeWorkingDeptLogMapper.findPoliceWorkingLog(policeId, year, month);
    }
}
