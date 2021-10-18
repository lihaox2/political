package com.bayee.political.task;

import com.bayee.political.domain.RiskConduct;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.User;
import com.bayee.political.service.RiskConductBureauRuleService;
import com.bayee.political.service.RiskConductService;
import com.bayee.political.service.RiskReportRecordService;
import com.bayee.political.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
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
public class ConductRiskTask {

    @Autowired
    UserService userService;

    @Autowired
    RiskReportRecordService riskReportRecordService;

    @Autowired
    RiskConductBureauRuleService riskConductBureauRuleService;

    @Autowired
    RiskConductService riskConductService;

//    @Scheduled(cron = "0 0/10 * * * ?") // 每10分钟
    public void conductRiskTaskDetails() {
        LocalDate localDate = LocalDate.now();
        List<User> userList = userService.userAllList();

        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String year = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
        String month = localDate.format(DateTimeFormatter.ofPattern("MM"));

        List<RiskConduct> riskConductList = new ArrayList<>();

        for (User user : userList) {
            RiskReportRecord reportRecord = riskReportRecordService.findRiskReportRecord(user.getPoliceId(), year, month);
            //行为规范
            RiskConduct riskConduct = riskConductBureauRuleService.riskConductDetails(user, date);
            if (riskConduct == null) {
                continue;
            }

            if (riskConduct.getId() == null) {
                riskConductList.add(riskConduct);
            }

            if (reportRecord != null) {
                reportRecord.setConductNum(riskConduct.getIndexNum());
                riskReportRecordService.updateRiskReportRecord(reportRecord);
            }else {
                reportRecord = new RiskReportRecord(0d);
                reportRecord.setPoliceId(user.getPoliceId());
                reportRecord.setCreationDate(new Date());
                reportRecord.setYear(year);
                reportRecord.setMonth(month);
                reportRecord.setConductNum(riskConduct.getIndexNum());
                reportRecord.setTotalNum(reportRecord.getConductNum());
                riskReportRecordService.insert(reportRecord);
            }
        }

        if (riskConductList.size() > 0) {
            riskConductService.insertRiskConductList(riskConductList);
        }
    }

}
