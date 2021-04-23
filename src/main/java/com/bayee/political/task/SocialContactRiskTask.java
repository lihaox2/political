package com.bayee.political.task;

import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.RiskSocialContact;
import com.bayee.political.domain.User;
import com.bayee.political.service.RiskReportRecordService;
import com.bayee.political.service.RiskSocialContactService;
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
public class SocialContactRiskTask {

    @Autowired
    UserService userService;

    @Autowired
    RiskReportRecordService riskReportRecordService;

    @Autowired
    RiskSocialContactService riskSocialContactService;

    @Scheduled(cron = "0 0/10 * * * ?") // 每10分钟
    public void socialContactRiskTaskDetails() {
        LocalDate localDate = LocalDate.now();
        List<User> userList = userService.userAllList();

        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String year = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
        String month = localDate.format(DateTimeFormatter.ofPattern("MM"));

        List<RiskSocialContact> riskSocialContactList = new ArrayList<>();

        for (User user : userList) {
            RiskReportRecord reportRecord = riskReportRecordService.findRiskReportRecord(user.getPoliceId(), year, month);
            //社交风险
            RiskSocialContact riskSocialContact = riskSocialContactService.riskSocialContactDetails(user, date);
            if (riskSocialContact == null) {
                continue;
            }

            if (riskSocialContact.getId() == null) {
                riskSocialContactList.add(riskSocialContact);
            }

            if (reportRecord != null) {
                reportRecord.setSocialContactNum(riskSocialContact.getIndexNum());
                riskReportRecordService.updateRiskReportRecord(reportRecord);
            }else {
                reportRecord = new RiskReportRecord(0d);
                reportRecord.setPoliceId(user.getPoliceId());
                reportRecord.setCreationDate(new Date());
                reportRecord.setYear(year);
                reportRecord.setMonth(month);
                reportRecord.setSocialContactNum(riskSocialContact.getIndexNum());
                reportRecord.setTotalNum(reportRecord.getSocialContactNum());
                riskReportRecordService.insert(reportRecord);
            }
        }

        if (riskSocialContactList.size() > 0) {
            riskSocialContactService.addRiskSocialContactList(riskSocialContactList);
        }
    }

}
