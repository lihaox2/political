package com.bayee.political.task;

import com.bayee.political.mapper.RiskReportRecordMapper;
import com.bayee.political.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xxl
 * @date 2021/4/22
 */
public class CaseRiskTask {

    @Autowired
    UserService userService;

    @Autowired
    RiskReportRecordMapper riskReportRecordMapper;

}
