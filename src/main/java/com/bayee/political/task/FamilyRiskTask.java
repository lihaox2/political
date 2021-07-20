package com.bayee.political.task;

import com.bayee.political.service.RiskReportRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author xxl
 * @date 2021/4/22
 */
@Component
@EnableScheduling
public class FamilyRiskTask {

    @Autowired
    RiskReportRecordService riskReportRecordService;

//    @Scheduled(cron = "0 0/10 * * * ?") // 每10分钟
    public void familyRiskTaskDetails() {
        LocalDate localDate = LocalDate.now();

        riskReportRecordService.family(localDate);
    }

}
