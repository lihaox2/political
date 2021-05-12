package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskConductBureauRuleType;
import com.bayee.political.mapper.RiskConductBureauRuleTypeMapper;
import com.bayee.political.service.RiskConductBureauRuleTypeService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/8
 */
@Service
public class RiskConductBureauRuleTypeServiceImpl implements RiskConductBureauRuleTypeService {

    @Autowired
    RiskConductBureauRuleTypeMapper riskConductBureauRuleTypeMapper;

    @Override
    public RiskConductBureauRuleType selectByPrimaryKey(Integer id) {
        return riskConductBureauRuleTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteByPrimaryKey(Integer id) {
        riskConductBureauRuleTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(RiskConductBureauRuleType ruleType) {
        riskConductBureauRuleTypeMapper.insert(ruleType);
    }

    @Override
    public void insertSelective(RiskConductBureauRuleType ruleType) {
        riskConductBureauRuleTypeMapper.insertSelective(ruleType);
    }

    @Override
    public void updateByPrimaryKeySelective(RiskConductBureauRuleType ruleType) {
        riskConductBureauRuleTypeMapper.updateByPrimaryKeySelective(ruleType);
    }

    @Override
    public void updateByPrimaryKey(RiskConductBureauRuleType ruleType) {
        riskConductBureauRuleTypeMapper.updateByPrimaryKey(ruleType);
    }

    @Override
    public List<RiskConductBureauRuleType> riskConductBureauRuleTypePage(Integer pageIndex, Integer pageSize, Integer type,String key) {
        if(pageIndex < 1) {
            pageIndex = 1;
        }
        pageIndex = (pageIndex - 1) * pageSize;
        return riskConductBureauRuleTypeMapper.riskConductBureauRuleTypePage(pageIndex, pageSize,type,key);
    }

    @Override
    public Integer getRiskConductBureauRuleTypePageCount(Integer type,String key) {
        return riskConductBureauRuleTypeMapper.getRiskConductBureauRuleTypePageCount(type,key);
    }

    @Override
    public List<RiskConductBureauRuleType> getAllRiskConductBureauRuleType() {
        return riskConductBureauRuleTypeMapper.getAllRiskConductBureauRuleType();
    }

    @Override
    public Integer countRuleTypeByNameAndRuleType(String name, Integer parentId, Integer id) {
        return riskConductBureauRuleTypeMapper.countRuleTypeByNameAndRuleType(name, parentId, id);
    }
}
