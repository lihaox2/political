package com.bayee.political.task;

import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.RiskTrain;
import com.bayee.political.domain.User;
import com.bayee.political.service.RiskReportRecordService;
import com.bayee.political.service.RiskSkillService;
import com.bayee.political.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xxl
 * @date 2021/4/22
 */
@Component
@EnableScheduling
@RestController
public class SkillRiskTask {

    @Autowired
    UserService userService;

    @Autowired
    RiskReportRecordService riskReportRecordService;

    @Autowired
    RiskSkillService riskSkillService;

//    @Scheduled(cron = "0 0/10 * * * ?") // 每10分钟
//    @RequestMapping("/skill/task")
    public void skillRiskTaskDetails() {
        LocalDate localDate = LocalDate.now();
        List<User> userList = userService.userAllList();

        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String year = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
        String month = localDate.format(DateTimeFormatter.ofPattern("MM"));

        for (User user : userList) {
            RiskReportRecord reportRecord = riskReportRecordService.findRiskReportRecord(user.getPoliceId(), year, month);
            //警务技能
            RiskTrain riskTrain = riskSkillService.riskSkillDetailsV2(user, date);
            if (riskTrain == null || riskTrain.getIndexNum() == null) {
                continue;
            }

            if (reportRecord != null) {
                reportRecord.setTrainNum(riskTrain.getIndexNum());
                riskReportRecordService.updateRiskReportRecord(reportRecord);
            }else {
                reportRecord = new RiskReportRecord(0d);
                reportRecord.setPoliceId(user.getPoliceId());
                reportRecord.setCreationDate(new Date());
                reportRecord.setYear(year);
                reportRecord.setMonth(month);
                reportRecord.setTrainNum(riskTrain.getIndexNum());
                reportRecord.setTotalNum(reportRecord.getTrainNum());
                riskReportRecordService.insert(reportRecord);
            }
        }
    }
}
