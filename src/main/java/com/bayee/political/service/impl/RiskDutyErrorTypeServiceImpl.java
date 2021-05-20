package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskDutyErrorType;
import com.bayee.political.mapper.RiskDutyErrorTypeMapper;
import com.bayee.political.service.RiskDutyErrorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/19
 */
@Service
public class RiskDutyErrorTypeServiceImpl implements RiskDutyErrorTypeService {

    @Autowired
    RiskDutyErrorTypeMapper riskDutyErrorTypeMapper;

    @Override
    public List<RiskDutyErrorType> getAll() {
        return riskDutyErrorTypeMapper.getAll();
    }

    @Override
    public Integer findIdByName(String name) {
        return riskDutyErrorTypeMapper.findIdByName(name);
    }
}
