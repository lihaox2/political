package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskConductBureauRuleType;
import com.bayee.political.domain.RiskConductVisitType;
import com.bayee.political.mapper.RiskConductBureauRuleTypeMapper;
import com.bayee.political.pojo.dto.ConductBureauRuleTypeDetailsDO;
import com.bayee.political.service.RiskConductBureauRuleTypeService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xxl
 * @date 2021/5/8
 */
@Service
public class RiskConductBureauRuleTypeServiceImpl implements RiskConductBureauRuleTypeService {

    @Autowired
    RiskConductBureauRuleTypeMapper riskConductBureauRuleTypeMapper;

    @Override
    public RiskConductBureauRuleType findTypeByCode(String typeCode) {
        return riskConductBureauRuleTypeMapper.findTypeByCode(typeCode);
    }

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
    public List<RiskConductBureauRuleType> riskConductBureauRuleTypePage(Integer pageIndex, Integer pageSize, String type,String key) {
        if(pageIndex < 1) {
            pageIndex = 1;
        }
        pageIndex = (pageIndex - 1) * pageSize;
        List<Integer> typeList = new ArrayList<>();
        if (type != null && !"".equals(type)) {
            typeList = Arrays.asList(type.split(",")).stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
        }

        return riskConductBureauRuleTypeMapper.riskConductBureauRuleTypePage(pageIndex, pageSize, typeList, key);
    }

    @Override
    public Integer getRiskConductBureauRuleTypePageCount(String type,String key) {
        List<Integer> typeList = new ArrayList<>();
        if (type != null && !"".equals(type)) {
            typeList = Arrays.asList(type.split(",")).stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
        }

        return riskConductBureauRuleTypeMapper.getRiskConductBureauRuleTypePageCount(typeList, key);
    }

    @Override
    public List<RiskConductBureauRuleType> getAllRiskConductBureauRuleType() {
        return riskConductBureauRuleTypeMapper.getAllRiskConductBureauRuleType();
    }

    @Override
    public Integer countRuleTypeByNameAndRuleType(String name, Integer parentId, Integer id) {
        return riskConductBureauRuleTypeMapper.countRuleTypeByNameAndRuleType(name, parentId, id);
    }

    @Override
    public List<RiskConductBureauRuleType> getMeasuresType() {
        return riskConductBureauRuleTypeMapper.getMeasuresType();
    }

    @Override
    public ConductBureauRuleTypeDetailsDO findById(Integer id) {
        return riskConductBureauRuleTypeMapper.findById(id);
    }

    @Override
    public List<Map<String, Object>> getTotalTypeByScoringOptionName(String name) {
        return riskConductBureauRuleTypeMapper.getTotalTypeByScoringOptionName(name);
    }

    @Override
    public RiskConductBureauRuleType findLevelTwoObjByCode(String code) {
        return riskConductBureauRuleTypeMapper.findLevelTwoObjByCode(code);
    }

}
