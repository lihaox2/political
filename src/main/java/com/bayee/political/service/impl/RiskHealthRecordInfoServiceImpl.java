package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskHealthRecordInfo;
import com.bayee.political.mapper.RiskHealthRecordInfoMapper;
import com.bayee.political.service.RiskHealthRecordInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/8
 */
@Service
public class RiskHealthRecordInfoServiceImpl implements RiskHealthRecordInfoService {

    @Autowired
    RiskHealthRecordInfoMapper riskHealthRecordInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return riskHealthRecordInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RiskHealthRecordInfo record) {
        return riskHealthRecordInfoMapper.insert(record);
    }

    @Override
    public RiskHealthRecordInfo selectByPrimaryKey(Integer id) {
        return riskHealthRecordInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RiskHealthRecordInfo> selectAll() {
        return riskHealthRecordInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(RiskHealthRecordInfo record) {
        return riskHealthRecordInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public RiskHealthRecordInfo findByRecordId(Integer recordId) {
        return riskHealthRecordInfoMapper.findByRecordId(recordId);
    }

    @Override
    public void deleteByRecordId(Integer recordId) {
        riskHealthRecordInfoMapper.deleteByRecordId(recordId);
    }
}
