package com.bayee.political.task;

import com.bayee.political.domain.RiskDuty;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.User;
import com.bayee.political.service.DutyRiskService;
import com.bayee.political.service.RiskReportRecordService;
import com.bayee.political.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
public class DutyRiskTask {

    @Autowired
    UserService userService;

    @Autowired
    RiskReportRecordService riskReportRecordService;

    @Autowired
    DutyRiskService dutyRiskService;

//    @Scheduled(cron = "0 0/10 * * * ?") // 每10分钟
    public void dutyRiskTaskDetails() {
        LocalDate localDate = LocalDate.now();
        List<User> userList = userService.userAllList();

        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String year = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
        String month = localDate.format(DateTimeFormatter.ofPattern("MM"));

        List<RiskDuty> riskDutyList = new ArrayList<>();

        for(User user : userList) {
            RiskReportRecord reportRecord = riskReportRecordService.findRiskReportRecord(user.getPoliceId(), year, month);
            //接警执勤
            RiskDuty riskDuty = dutyRiskService.dutyRiskDetails(user, date);
            if (riskDuty == null) {
                continue;
            }

            if (riskDuty.getId() == null) {
                riskDutyList.add(riskDuty);
            }

            if (reportRecord != null) {
                reportRecord.setDutyNum(riskDuty.getIndexNum());
                riskReportRecordService.updateRiskReportRecord(reportRecord);
            }else {
                reportRecord = new RiskReportRecord(0d);
                reportRecord.setPoliceId(user.getPoliceId());
                reportRecord.setCreationDate(new Date());
                reportRecord.setYear(year);
                reportRecord.setMonth(month);
                reportRecord.setDutyNum(riskDuty.getIndexNum());
                reportRecord.setTotalNum(reportRecord.getDutyNum());
                riskReportRecordService.insert(reportRecord);
            }
        }

        if (riskDutyList.size() > 0) {
            dutyRiskService.addRiskDutyList(riskDutyList);
        }
    }

}
