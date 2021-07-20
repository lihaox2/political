package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskConductVisitOrigin;
import com.bayee.political.mapper.RiskConductVisitOriginMapper;
import com.bayee.political.service.RiskConductVisitOriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/7/20
 */
@Service
public class RiskConductVisitOriginServiceImpl implements RiskConductVisitOriginService {

    @Autowired
    RiskConductVisitOriginMapper riskConductVisitOriginMapper;

    @Override
    public List<RiskConductVisitOrigin> findAllVisitOrigin() {
        return riskConductVisitOriginMapper.findAllVisitOrigin();
    }

    @Override
    public RiskConductVisitOrigin findById(Integer id) {
        return riskConductVisitOriginMapper.selectByPrimaryKey(id);
    }
}
