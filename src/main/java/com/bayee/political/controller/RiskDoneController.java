package com.bayee.political.controller;

import com.bayee.political.domain.*;
import com.bayee.political.mapper.RiskReportRecordMapper;
import com.bayee.political.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xxl
 * @date 2021/4/6
 */
@Controller
public class RiskDoneController {

    @Autowired
    RiskSkillService riskSkillService;

    @Autowired
    RiskConductBureauRuleService riskConductBureauRuleService;

    @Autowired
    UserService userService;

    @Autowired
    RiskReportRecordService riskReportRecordService;

    @Autowired
    DutyRiskService dutyRiskService;

    @Autowired
    HandlingCasesRiskService handlingCasesRiskService;

    @GetMapping("/risk/done/test")
    public ResponseEntity<?> test() {
        List<User> userList = userService.userAllList();
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String year = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
        String month = localDate.format(DateTimeFormatter.ofPattern("MM"));

        List<RiskReportRecord> riskReportRecordList = new ArrayList<>();
        List<RiskCase> riskCaseList = new ArrayList<>();
        List<RiskDuty> riskDutyList = new ArrayList<>();

        for (User user : userList) {
            RiskReportRecord record = new RiskReportRecord();
            record.setPoliceId(user.getPoliceId());
            record.setYear(year);
            record.setMonth(month);

            record.setHandlingCaseNum(0d);
            record.setDutyNum(0d);
            record.setSocialContactNum(0d);
            record.setAmilyEvaluationNum(0d);
            record.setHealthNum(0d);
            record.setDrinkNum(0d);
            record.setStudyNum(0d);
            record.setConductNum(0d);
            record.setTrainNum(0d);

            //执法办案
            RiskCase riskCase = handlingCasesRiskService.handlingCasesRiskDetails(user, localDate);
            if (riskCase != null) {
                if (riskCase.getId() == null) {
                    riskCaseList.add(riskCase);
                }
                record.setHandlingCaseNum(riskCase.getIndexNum());
            }

            //接警执勤
            RiskDuty riskDuty = dutyRiskService.dutyRiskDetails(user, localDate);
            if (riskDuty != null) {
                if (riskDuty.getId() == null) {
                    riskDutyList.add(riskDuty);
                }
                record.setDutyNum(riskDuty.getIndexNum());
            }

            //警务技能
            RiskTrain riskTrain = riskSkillService.riskSkillDetails(user);
            if (riskTrain != null && riskTrain.getIndexNum() != null) {
                record.setTrainNum(riskTrain.getIndexNum());
            }

            //行为规范
            RiskConduct riskConduct = riskConductBureauRuleService.riskConductBureauRuleDetails(user);
            if (riskConduct != null && riskConduct.getIndexNum() != null) {
                record.setConductNum(riskConduct.getIndexNum());
            }
            record.setTotalNum(Math.min(record.getConductNum() + record.getHandlingCaseNum() + record.getDutyNum()
                    + record.getTrainNum() + record.getSocialContactNum() + record.getAmilyEvaluationNum() +
                    record.getHealthNum() + record.getStudyNum() + record.getDrinkNum(), 100));
            record.setCreationDate(new Date());

            RiskReportRecord oldRecord = riskReportRecordService.findRiskReportRecord(user.getPoliceId(), year, month);
            if (oldRecord != null && oldRecord.getId() != null) {
                oldRecord.setTrainNum(record.getTrainNum());
                oldRecord.setConductNum(record.getConductNum());
                oldRecord.setTotalNum(record.getTotalNum());
                oldRecord.setUpdateDate(new Date());

                riskReportRecordService.updateByPrimaryKey(oldRecord);
            } else {
                riskReportRecordList.add(record);
            }
        }

        //批量添加数据
        if (riskCaseList.size() > 0) {
            handlingCasesRiskService.addRiskCaseList(riskCaseList);
        }
        if (riskDutyList.size() > 0) {
            dutyRiskService.addRiskDutyList(riskDutyList);
        }
        if (riskReportRecordList.size() > 0) {
            riskReportRecordService.insertRiskReportRecordList(riskReportRecordList);
        }

        double casesManageRiskAvgScore = handlingCasesRiskService.findPoliceAvgDeductionScoreByDate(date);
        double dutyRiskAvgScore = dutyRiskService.findPoliceAvgDeductionScoreByDate(date);

        //处理 无办案量和无接处警的警员数据
        for (User user : userList) {
            RiskReportRecord oldRiskReportRecord = riskReportRecordService.getByPoliceIdMonth(year, month, user.getPoliceId());
            if (oldRiskReportRecord == null || oldRiskReportRecord.getId() == null
                    || (dutyRiskAvgScore <= 0 && casesManageRiskAvgScore <= 0)) {
                continue;
            }

            //处理无办案的警员信息
            if (casesManageRiskAvgScore > 0) {
                RiskCase riskCase = handlingCasesRiskService.handlingCasesRiskDetailsByCasesManageRisk(user, localDate, casesManageRiskAvgScore);
                if (riskCase != null) {
                    oldRiskReportRecord.setHandlingCaseNum(riskCase.getIndexNum());
                }
            }

            //处理无接警的警员数据
            if (dutyRiskAvgScore > 0) {
                RiskDuty riskDuty = dutyRiskService.dutyRiskNoDeductionScoreCountDetails(user, localDate, dutyRiskAvgScore);
                if (riskDuty != null) {
                    oldRiskReportRecord.setDutyNum(riskDuty.getIndexNum());
                }
            }

            oldRiskReportRecord.setTotalNum(Math.min(oldRiskReportRecord.getConductNum() + oldRiskReportRecord.getHandlingCaseNum() +
                    oldRiskReportRecord.getDutyNum() + oldRiskReportRecord.getTrainNum() + oldRiskReportRecord.getStudyNum() +
                    oldRiskReportRecord.getSocialContactNum() + oldRiskReportRecord.getAmilyEvaluationNum() +
                    oldRiskReportRecord.getHealthNum() + oldRiskReportRecord.getDrinkNum(), 100));
            oldRiskReportRecord.setUpdateDate(new Date());

            riskReportRecordService.updateByPrimaryKey(oldRiskReportRecord);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
