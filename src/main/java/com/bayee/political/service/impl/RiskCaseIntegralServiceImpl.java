package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskCaseIntegral;
import com.bayee.political.json.CaseIntegralPageQueryParam;
import com.bayee.political.mapper.RiskCaseIntegralMapper;
import com.bayee.political.pojo.CaseIntegralPageResultDO;
import com.bayee.political.service.RiskCaseIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/28 19:09
 */
@Service
public class RiskCaseIntegralServiceImpl implements RiskCaseIntegralService {

    @Autowired
    RiskCaseIntegralMapper riskCaseIntegralMapper;

    @Override
    public List<CaseIntegralPageResultDO> caseIntegralPage(CaseIntegralPageQueryParam queryParam) {
        if (queryParam.getPageIndex() == null || queryParam.getPageIndex() < 1) {
            queryParam.setPageIndex(1);
        }
        if (queryParam.getPageSize() == null) {
            queryParam.setPageSize(10);
        }
        queryParam.setPageIndex((queryParam.getPageIndex() - 1) * queryParam.getPageSize());

        return riskCaseIntegralMapper.caseIntegralPage(queryParam);
    }

    @Override
    public Integer caseIntegralPageCount(CaseIntegralPageQueryParam queryParam) {
        return riskCaseIntegralMapper.caseIntegralPageCount(queryParam);
    }

    @Override
    public void addRiskCaseIntegral(RiskCaseIntegral caseIntegral) {
        riskCaseIntegralMapper.insert(caseIntegral);
    }

    @Override
    public void updateRiskCaseIntegral(RiskCaseIntegral caseIntegral) {
        riskCaseIntegralMapper.updateByPrimaryKey(caseIntegral);
    }

    @Override
    public RiskCaseIntegral findById(Integer id) {
        return riskCaseIntegralMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteRiskCaseIntegral(Integer id) {
        riskCaseIntegralMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<RiskCaseIntegral> findCaseIntegralByPoliceIdAndYear(String policeId, String year, String month) {
        return riskCaseIntegralMapper.findCaseIntegralByPoliceIdAndYear(policeId, year, month);
    }

}
