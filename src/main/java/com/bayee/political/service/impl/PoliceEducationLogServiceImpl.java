package com.bayee.political.service.impl;

import com.bayee.political.domain.PoliceEducationLog;
import com.bayee.political.mapper.PoliceEducationLogMapper;
import com.bayee.political.service.PoliceEducationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/6/7
 */
@Service
public class PoliceEducationLogServiceImpl implements PoliceEducationLogService {

    @Autowired
    PoliceEducationLogMapper policeEducationLogMapper;

    @Override
    public List<PoliceEducationLog> findEducationLodByPoliceId(String policeId, String year, String month) {
        return policeEducationLogMapper.findEducationLodByPoliceId(policeId, year, month);
    }
}
