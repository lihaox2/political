package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskConductVisitType;
import com.bayee.political.mapper.RiskConductVisitTypeMapper;
import com.bayee.political.service.RiskConductVisitTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/8
 */
@Service
public class RiskConductVisitTypeServiceImpl implements RiskConductVisitTypeService {

    @Autowired
    RiskConductVisitTypeMapper riskConductVisitTypeMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return riskConductVisitTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RiskConductVisitType record) {
        return riskConductVisitTypeMapper.insert(record);
    }

    @Override
    public int insertSelective(RiskConductVisitType record) {
        return riskConductVisitTypeMapper.insertSelective(record);
    }

    @Override
    public RiskConductVisitType selectByPrimaryKey(Integer id) {
        return riskConductVisitTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RiskConductVisitType record) {
        return riskConductVisitTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RiskConductVisitType record) {
        return riskConductVisitTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public Integer selectByName(String name) {
        return riskConductVisitTypeMapper.selectByName(name);
    }

    @Override
    public List<RiskConductVisitType> riskConductVisitTypePage(Integer pageIndex, Integer pageSize) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        pageIndex = (pageIndex - 1) * pageSize;
        return riskConductVisitTypeMapper.riskConductVisitTypePage(pageIndex, pageSize);
    }

    @Override
    public Integer getRiskConductVisitTypePageCount() {
        return riskConductVisitTypeMapper.getRiskConductVisitTypePageCount();
    }

    @Override
    public List<RiskConductVisitType> getAllRiskConductVisitType() {
        return riskConductVisitTypeMapper.getAllRiskConductVisitType();
    }

    @Override
    public Integer countByNameAndParentId(String name, Integer parentId, Integer id) {
        return riskConductVisitTypeMapper.countByNameAndParentId(name, parentId, id);
    }
}
