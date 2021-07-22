package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskRecordVerifyType;
import com.bayee.political.mapper.RiskRecordVerifyTypeMapper;
import com.bayee.political.service.RiskRecordVerifyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/7/20
 */
@Service
public class RiskRecordVerifyTypeServiceImpl implements RiskRecordVerifyTypeService {

    @Autowired
    RiskRecordVerifyTypeMapper riskRecordVerifyTypeMapper;

    @Override
    public List<RiskRecordVerifyType> findAllVerifyType(Integer scorer) {
        return riskRecordVerifyTypeMapper.findAllVerifyType(scorer);
    }

    @Override
    public RiskRecordVerifyType findById(Integer id) {
        return riskRecordVerifyTypeMapper.selectByPrimaryKey(id);
    }
}
