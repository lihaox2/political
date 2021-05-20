package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskDutyInformationType;
import com.bayee.political.mapper.RiskDutyInformationTypeMapper;
import com.bayee.political.service.RiskDutyInformationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/19
 */
@Service
public class RiskDutyInformationTypeServiceImpl implements RiskDutyInformationTypeService {

    @Autowired
    RiskDutyInformationTypeMapper riskDutyInformationTypeMapper;

    @Override
    public List<RiskDutyInformationType> getAll() {
        return riskDutyInformationTypeMapper.getAll();
    }

    @Override
    public Integer findIdByName(String name) {
        return riskDutyInformationTypeMapper.findIdByName(name);
    }
}
