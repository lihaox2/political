package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskHonour;
import com.bayee.political.json.RiskHonourPageQueryParam;
import com.bayee.political.mapper.RiskHonourMapper;
import com.bayee.political.pojo.RiskHonourPageResultDO;
import com.bayee.political.service.RiskHonourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/28 15:25
 */
@Service
public class RiskHonourServiceImpl implements RiskHonourService {

    @Autowired
    RiskHonourMapper riskHonourMapper;

    @Override
    public List<RiskHonourPageResultDO> riskHonourPage(RiskHonourPageQueryParam queryParam) {
        if (queryParam.getPageIndex() == null || queryParam.getPageIndex() < 1) {
            queryParam.setPageIndex(1);
        }
        if (queryParam.getPageSize() == null) {
            queryParam.setPageSize(10);
        }
        queryParam.setPageIndex((queryParam.getPageIndex() - 1) * queryParam.getPageSize());

        return riskHonourMapper.riskHonourPage(queryParam);
    }

    @Override
    public RiskHonour riskHonourDetails(Integer id) {
        return riskHonourMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addRiskHonour(RiskHonour riskHonour) {
        riskHonourMapper.insert(riskHonour);
    }

    @Override
    public void updateRiskHonour(RiskHonour riskHonour) {
        riskHonourMapper.updateByPrimaryKey(riskHonour);
    }

    @Override
    public void deleteRiskHonour(Integer id) {
        riskHonourMapper.deleteByPrimaryKey(id);
    }
}
