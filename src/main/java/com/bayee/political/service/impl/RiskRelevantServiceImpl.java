package com.bayee.political.service.impl;

import com.bayee.political.algorithm.RiskCompute;
import com.bayee.political.domain.RiskRelevant;
import com.bayee.political.domain.RiskRelevantRecord;
import com.bayee.political.domain.User;
import com.bayee.political.json.ChartResult;
import com.bayee.political.mapper.RiskRelevantMapper;
import com.bayee.political.mapper.RiskRelevantRecordMapper;
import com.bayee.political.pojo.GlobalIndexNumResultDO;
import com.bayee.political.service.RiskRelevantService;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xxl
 * @date 2021/11/19
 */
@Service
public class RiskRelevantServiceImpl implements RiskRelevantService {

    @Autowired
    RiskRelevantMapper riskRelevantMapper;

    @Autowired
    RiskRelevantRecordMapper riskRelevantRecordMapper;

    @Override
    public RiskRelevant relevantRiskDetails(User user, String date) {
        RiskRelevant riskRelevant = new RiskRelevant();
        riskRelevant.setPoliceId(user.getPoliceId());
        riskRelevant.setIndexNum(0d);
        riskRelevant.setDeductionScore(0d);
        riskRelevant.setDeductionCount(0);
        riskRelevant.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
        riskRelevant.setUpdateDate(new Date());

        List<RiskRelevantRecord> recordList = riskRelevantRecordMapper.findPoliceRelevantRecord(user.getPoliceId(), date);
        /*if (recordList == null || recordList.size() <= 0) {
            return riskRelevant;
        }*/

        double deductionScore = 0;
        int deductionCount = 0;
        for (RiskRelevantRecord record : recordList) {
            deductionCount++;
            deductionScore += record.getDeductionScore();
        }

        GlobalIndexNumResultDO resultDO = riskRelevantMapper.findGlobalIndexNum(date, "deduction_score", 2);
        riskRelevant.setIndexNum(RiskCompute.normalizationCompute(resultDO.getMaxNum(), resultDO.getMinNum(), deductionScore));
        riskRelevant.setDeductionScore(deductionScore);
        riskRelevant.setDeductionCount(deductionCount);

        //旧数据处理
        RiskRelevant oldRiskRelevant = riskRelevantMapper.findByPoliceIdAndDate(user.getPoliceId(), date);
        if (oldRiskRelevant != null && oldRiskRelevant.getId() != null) {
            riskRelevant.setId(oldRiskRelevant.getId());
            riskRelevantMapper.updateByPrimaryKey(riskRelevant);
        } else {
            riskRelevantMapper.insert(riskRelevant);
        }

        return riskRelevant;
    }

    @Override
    public RiskRelevant riskRelevantItem(String policeId, String dateTime, Integer timeType) {
        RiskRelevant relevant = riskRelevantMapper.riskRelevantItem(policeId, dateTime, timeType);
        if (timeType == 1) {
            GlobalIndexNumResultDO indexResultDO = riskRelevantMapper.findGlobalIndexNum(dateTime+"-01", "index_num", 1);

            relevant.setIndexNum(RiskCompute.normalizationCompute(indexResultDO.getMaxNum(), indexResultDO.getMinNum(), relevant.getIndexNum()));
        }
        return relevant;
    }

    @Override
    public List<ChartResult> riskNearSixMonthChart(String policeId) {
        return riskRelevantMapper.riskNearSixMonthChart(policeId);
    }
}
