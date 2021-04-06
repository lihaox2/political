package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.mapper.RiskReportRecordMapper;
import com.bayee.political.service.RiskReportRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xxl
 * @date 2021/4/6
 */
@Service
public class RiskReportRecordServiceImpl implements RiskReportRecordService {

    @Autowired
    RiskReportRecordMapper riskReportRecordMapper;

    @Override
    public int updateByPrimaryKey(RiskReportRecord record) {
        return riskReportRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public int insert(RiskReportRecord record) {
        return riskReportRecordMapper.insert(record);
    }

    @Override
    public RiskReportRecord findRiskReportRecord(String policeId, String year, String month) {
        return riskReportRecordMapper.findRiskReportRecord(policeId, year, month);
    }
}
