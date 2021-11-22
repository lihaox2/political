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
    UserService userService;

    @Autowired
    RiskReportRecordService riskReportRecordService;


    @GetMapping("/risk/done/test")
    public ResponseEntity<?> test() {
        String[] dateArr = new String[]{"2021-11-01", "2021-10-01", "2021-09-01", "2021-08-01", "2021-07-01", "2021-06-01", "2021-05-01", "2021-04-01",
                "2021-03-01", "2021-02-01", "2021-01-01", "2020-12-01", "2020-11-01"};

        List<User> userList = userService.userAllList();

        for (String date : dateArr) {
//            riskReportRecordService.policeRiskDetails(userList, LocalDate.parse(date));
            riskReportRecordService.policeRiskDetailsV2(userList, LocalDate.parse(date));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
