package com.bayee.political.service.impl;

import com.bayee.political.domain.*;
import com.bayee.political.mapper.RiskAlarmMapper;
import com.bayee.political.mapper.RiskConductBureauRuleMapper;
import com.bayee.political.mapper.RiskConductBureauRuleRecordMapper;
import com.bayee.political.mapper.RiskConductMapper;
import com.bayee.political.service.RiskConductBureauRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    RiskAlarmMapper riskAlarmMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RiskConduct riskConductBureauRuleDetails(User user) {
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        List<RiskConductBureauRuleRecord> recordList = riskConductBureauRuleRecordMapper.findByPoliceIdAndDate(user.getPoliceId(), date);
        RiskConductBureauRule riskConductBureauRule = new RiskConductBureauRule();
        RiskConduct riskConduct = new RiskConduct();

        riskConductBureauRule.setPoliceId(user.getPoliceId());

        double riskConductMaxScore = 25;

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
        }else{
            riskConductBureauRule.setIndexNum(0d);
            riskConductBureauRule.setDeductionScoreCount(0);
            riskConductBureauRule.setTotalDeductionScore(0d);
            riskConductBureauRule.setCreationDate(new Date());
        }

        //处理老数据
        RiskConductBureauRule oldRiskConductBureauRule = riskConductBureauRuleMapper.findRiskConductBureauRole(user.getPoliceId(), date);
        if (oldRiskConductBureauRule != null && oldRiskConductBureauRule.getId() != null) {
            //表示该riskConductBureauRule不用新增
            riskConductBureauRule.setId(oldRiskConductBureauRule.getId());

            oldRiskConductBureauRule.setIndexNum(riskConductBureauRule.getIndexNum());
            oldRiskConductBureauRule.setDeductionScoreCount(riskConductBureauRule.getDeductionScoreCount());
            oldRiskConductBureauRule.setTotalDeductionScore(riskConductBureauRule.getTotalDeductionScore());
            oldRiskConductBureauRule.setUpdateDate(new Date());

            riskConductBureauRuleMapper.updateByPrimaryKey(oldRiskConductBureauRule);
        }

        //处理总行为规范数据
        riskConduct.setPoliceId(user.getPoliceId());
        riskConduct.setBureauRuleScore(riskConductBureauRule.getIndexNum());
        riskConduct.setVisitScore(0d);
        riskConduct.setTrafficViolationScore(0d);
        riskConduct.setIndexNum(Math.min(riskConduct.getVisitScore() + riskConduct.getTrafficViolationScore() +
                riskConduct.getBureauRuleScore(), riskConductMaxScore));
        riskConduct.setCreationDate(new Date());
        //处理旧的总行为规范数据
        RiskConduct oldRiskConduct = riskConductMapper.findRiskConductByPoliceIdAndDate(user.getPoliceId(), date);
        if (oldRiskConduct != null && oldRiskConduct.getId() != null) {
            //表示该riskConduct不用添加
            riskConduct.setId(oldRiskConduct.getId());

            oldRiskConduct.setBureauRuleScore(riskConductBureauRule.getIndexNum());
            oldRiskConduct.setIndexNum(Math.min(riskConduct.getVisitScore() + riskConduct.getTrafficViolationScore() +
                    riskConduct.getBureauRuleScore(), riskConductMaxScore));
            oldRiskConduct.setUpdateDate(new Date());

            riskConductMapper.updateByPrimaryKey(oldRiskConduct);
        }

        //产生预警数据
        RiskAlarm riskAlarm = new RiskAlarm();
        riskAlarm.setPoliceId(user.getPoliceId());
        riskAlarm.setAlarmType(11002);
        riskAlarm.setAlarmScore(riskConduct.getIndexNum());
        riskAlarm.setCreationDate(new Date());
        riskAlarm.setIsTalk(0);
        riskAlarmMapper.insertSelective(riskAlarm);

        if (riskConductBureauRule.getId() == null) {
            riskConductBureauRuleMapper.insert(riskConductBureauRule);
        }
        if (riskConduct.getId() == null) {
            riskConductMapper.insert(riskConduct);
        }

        return riskConduct;
    }

    @Override
    public void addRiskConductBureauRuleList(List<RiskConductBureauRule> riskConductBureauRuleList) {
        riskConductBureauRuleMapper.insertRiskConductBureauRuleList(riskConductBureauRuleList);
    }
}
