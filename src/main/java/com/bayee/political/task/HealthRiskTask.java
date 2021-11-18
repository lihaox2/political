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
public class HealthRiskTask {

    @Autowired
    RiskReportRecordService riskReportRecordService;

//    @Scheduled(cron = "0 0/10 * * * ?") // 每10分钟
    public void healthRiskTaskDetails() {
        LocalDate localDate = LocalDate.now();

        riskReportRecordService.health(localDate);
    }

    @Scheduled(cron="0 1 1 1 1 * 2021-2999")
    public void healthRiskTask(){
        LocalDate localDate = LocalDate.now();

        riskReportRecordService.health(localDate);
    }

}
