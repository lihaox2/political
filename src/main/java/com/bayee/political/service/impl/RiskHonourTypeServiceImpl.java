package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskHonourType;
import com.bayee.political.mapper.RiskHonourTypeMapper;
import com.bayee.political.service.RiskHonourTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/28 15:32
 */
@Service
public class RiskHonourTypeServiceImpl implements RiskHonourTypeService {

    @Autowired
    RiskHonourTypeMapper riskHonourTypeMapper;

    @Override
    public List<RiskHonourType> queryAllHonourType() {
        return riskHonourTypeMapper.queryAll();
    }

    @Override
    public RiskHonourType findByTypeCode(String typeCode) {
        return riskHonourTypeMapper.findByTypeCode(typeCode);
    }
}
