package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskConduct;
import com.bayee.political.domain.RiskConductBureauRule;
import com.bayee.political.domain.RiskConductBureauRuleRecord;
import com.bayee.political.domain.User;
import com.bayee.political.mapper.RiskConductBureauRuleMapper;
import com.bayee.political.mapper.RiskConductBureauRuleRecordMapper;
import com.bayee.political.mapper.RiskConductMapper;
import com.bayee.political.service.RiskConductBureauRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author xxl
 * @date 2021/4/6
 */
@Service
public class RiskConductBureauRuleServiceImpl implements RiskConductBureauRuleService {

    @Autowired
    RiskConductBureauRuleRecordMapper riskConductBureauRuleRecordMapper;

    @Autowired
    RiskConductBureauRuleMapper riskConductBureauRuleMapper;

    @Autowired
    RiskConductMapper riskConductMapper;

    @Override
    public RiskConductBureauRule riskConductBureauRuleDetails(User user) {
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        List<RiskConductBureauRuleRecord> recordList = riskConductBureauRuleRecordMapper.findByPoliceIdAndDate(user.getPoliceId(), date);
        RiskConductBureauRule riskConductBureauRule = new RiskConductBureauRule();
        riskConductBureauRule.setPoliceId(user.getPoliceId());

        if (recordList.size() > 0) {
            int totalCount = 0;
            double totalScore = 0;

            for (RiskConductBureauRuleRecord record : recordList){
                totalCount++;
                if (record.getDeductionScore() != null) {
                    totalScore += record.getDeductionScore();
                }
            }
            riskConductBureauRule.setIndexNum(totalScore);
            riskConductBureauRule.setDeductionScoreCount(totalCount);
            riskConductBureauRule.setTotalDeductionScore(totalScore);
            riskConductBureauRule.setCreationDate(new Date());

            //处理老数据
            RiskConductBureauRule oldRiskConductBureauRule = riskConductBureauRuleMapper.findRiskConductBureauRole(user.getPoliceId(), date);
            if (oldRiskConductBureauRule != null && oldRiskConductBureauRule.getId() != null) {
                oldRiskConductBureauRule.setIndexNum(riskConductBureauRule.getIndexNum());
                oldRiskConductBureauRule.setDeductionScoreCount(riskConductBureauRule.getDeductionScoreCount());
                oldRiskConductBureauRule.setTotalDeductionScore(riskConductBureauRule.getTotalDeductionScore());
                oldRiskConductBureauRule.setUpdateDate(new Date());

                riskConductBureauRuleMapper.updateByPrimaryKey(oldRiskConductBureauRule);
            }

            //处理总行为规范数据
            RiskConduct riskConduct = riskConductMapper.findRiskConductByPoliceIdAndDate(user.getPoliceId(), date);


        } else {
            riskConductBureauRule.setIndexNum(0d);
            riskConductBureauRule.setDeductionScoreCount(0);
            riskConductBureauRule.setTotalDeductionScore(0d);
            riskConductBureauRule.setCreationDate(new Date());
        }

        return riskConductBureauRule;
    }
}
