package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskRecordVerify;
import com.bayee.political.mapper.RiskRecordVerifyMapper;
import com.bayee.political.pojo.dto.RiskRecordVerifyDetailsDO;
import com.bayee.political.pojo.dto.RiskRecordVerifyPageResultDO;
import com.bayee.political.pojo.json.RiskRecordVerifyPageQueryParam;
import com.bayee.political.service.RiskRecordVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/7/20
 */
@Service
public class RiskRecordVerifyServiceImpl implements RiskRecordVerifyService {

    @Autowired
    RiskRecordVerifyMapper riskRecordVerifyMapper;

    @Override
    public List<RiskRecordVerifyPageResultDO> riskRecordVerifyPage(RiskRecordVerifyPageQueryParam queryParam) {
        if (queryParam == null) {
            queryParam = new RiskRecordVerifyPageQueryParam();
            queryParam.setPageIndex(1);
            queryParam.setPageSize(5);
        }
        if (queryParam.getPageIndex() < 1) {
            queryParam.setPageIndex(1);
        }
        queryParam.setPageIndex((queryParam.getPageIndex() - 1) * queryParam.getPageSize());

        return riskRecordVerifyMapper.riskRecordVerifyPage(queryParam);
    }

    @Override
    public RiskRecordVerify findById(Integer id) {
        return riskRecordVerifyMapper.selectByPrimaryKey(id);
    }

    @Override
    public RiskRecordVerifyDetailsDO findVerifyDOById(Integer id) {
        return riskRecordVerifyMapper.findVerifyDOById(id);
    }

    @Override
    public void addAppeal(RiskRecordVerify verify) {
        riskRecordVerifyMapper.insert(verify);
    }

    @Override
    public void cancelAppeal(Integer typeId, Integer moduleId) {
        riskRecordVerifyMapper.cancelAppeal(typeId, moduleId);
    }

    @Override
    public RiskRecordVerifyDetailsDO appealDetails(Integer typeId, Integer moduleId) {
        return riskRecordVerifyMapper.appealDetails(typeId, moduleId);
    }
}
