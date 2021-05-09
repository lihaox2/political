package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskCaseLawEnforcementType;
import com.bayee.political.mapper.RiskCaseLawEnforcementTypeMapper;
import com.bayee.political.service.RiskCaseLawEnforcementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/8
 */
@Service
public class RiskCaseLawEnforcementTypeServiceImpl implements RiskCaseLawEnforcementTypeService {

    @Autowired
    RiskCaseLawEnforcementTypeMapper riskCaseLawEnforcementTypeMapper;

    @Override
    public List<RiskCaseLawEnforcementType> getLawEnforcementType() {
        return riskCaseLawEnforcementTypeMapper.getLawEnforcementType();
    }

    @Override
    public List<RiskCaseLawEnforcementType> getDutyType() {
        return riskCaseLawEnforcementTypeMapper.getDutyType();
    }
}
