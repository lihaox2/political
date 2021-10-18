package com.bayee.political.task;

import com.bayee.political.domain.User;
import com.bayee.political.service.RiskReportRecordService;
import com.bayee.political.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author xxl
 * @date 2021/7/19
 */
@Component
@EnableScheduling
public class RiskReportRecordTask {

    @Autowired
    UserService userService;

    @Autowired
    RiskReportRecordService riskReportRecordService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void riskReportTask() {
        List<User> userList = userService.userAllList();

        riskReportRecordService.policeRiskDetailsV2(userList, LocalDate.now());

    }

}
