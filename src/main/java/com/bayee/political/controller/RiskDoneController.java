package com.bayee.political.controller;

import com.bayee.political.domain.*;
import com.bayee.political.mapper.RiskReportRecordMapper;
import com.bayee.political.service.RiskConductBureauRuleService;
import com.bayee.political.service.RiskReportRecordService;
import com.bayee.political.service.RiskSkillService;
import com.bayee.political.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("/risk/done/test")
    public ResponseEntity<?> test() {
        List<User> userList = userService.userAllList();
        LocalDate localDate = LocalDate.now();
        String year = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
        String month = localDate.format(DateTimeFormatter.ofPattern("MM"));

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

            //警务技能
            RiskTrain riskTrain = riskSkillService.riskSkillDetails(user);
            if (riskTrain != null && riskTrain.getIndexNum() != null) {
                record.setTrainNum(riskTrain.getIndexNum());
            }

            //局规计分
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
                riskReportRecordService.insert(record);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
