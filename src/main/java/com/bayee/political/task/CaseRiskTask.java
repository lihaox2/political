package com.bayee.political.task;

import com.bayee.political.domain.RiskCase;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.User;
import com.bayee.political.mapper.RiskReportRecordMapper;
import com.bayee.political.service.HandlingCasesRiskService;
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
public class CaseRiskTask {

    @Autowired
    UserService userService;

    @Autowired
    RiskReportRecordService riskReportRecordService;

    @Autowired
    HandlingCasesRiskService handlingCasesRiskService;

    @Scheduled(cron = "0 0/10 * * * ?") // 每10分钟
    public void caseRiskTaskDetails() {
        LocalDate localDate = LocalDate.now();
        List<User> userList = userService.userAllList();

        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String year = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
        String month = localDate.format(DateTimeFormatter.ofPattern("MM"));

        List<RiskReportRecord> riskReportRecordList = new ArrayList<>();
        List<RiskCase> riskCaseList = new ArrayList<>();

        for (User user : userList) {
            RiskReportRecord reportRecord = riskReportRecordService.findRiskReportRecord(user.getPoliceId(), year, month);
            //执法办案
            RiskCase riskCase = handlingCasesRiskService.handlingCasesRiskDetails(user, date);
            if (riskCase == null) {
                continue;
            }

            if (riskCase.getId() == null) {
                riskCaseList.add(riskCase);
            }

            if (reportRecord != null) {
                reportRecord.setHandlingCaseNum(riskCase.getIndexNum());
                riskReportRecordService.updateRiskReportRecord(reportRecord);
            }else {
                reportRecord = new RiskReportRecord(0d);
                reportRecord.setCreationDate(new Date());
                reportRecord.setHandlingCaseNum(riskCase.getIndexNum());
                reportRecord.setTotalNum(reportRecord.getHandlingCaseNum());
                riskReportRecordList.add(reportRecord);
            }
        }

        if (riskCaseList.size() > 0) {
            handlingCasesRiskService.addRiskCaseList(riskCaseList);
        }
        if (riskReportRecordList.size() > 0) {
            riskReportRecordService.insertRiskReportRecordList(riskReportRecordList);
        }
    }

}