package com.bayee.political.controller.admin;

import com.bayee.political.domain.ScreenChart;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.pojo.json.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.DataListReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xxl
 * @date 2021/5/20
 */
@RestController
@RequestMapping("/index")
public class AdminIndexController {

    @Autowired
    UserService userService;

    @Autowired
    RiskCaseLawEnforcementRecordService riskCaseLawEnforcementRecordService;

    @Autowired
    RiskDutyDealPoliceRecordService riskDutyDealPoliceRecordService;

    @Autowired
    RiskHealthRecordService riskHealthRecordService;

    @Autowired
    RiskConductVisitRecordService riskConductVisitRecordService;

    @Autowired
    RiskConductBureauRuleRecordService riskConductBureauRuleRecordService;

    @Autowired
    TrainFirearmService trainFirearmService;

    @Autowired
    TrainPhysicalService trainPhysicalService;

    /**
     * 头部
     * @return
     */
    @GetMapping("/head/data")
    public ResponseEntity<?> headData() {
        IndexHeadDataResult result = new IndexHeadDataResult();
        result.setPoliceNum(userService.countAllPolice());
        result.setCaseManageNum(riskCaseLawEnforcementRecordService.countAll());
        result.setDutyNum(riskDutyDealPoliceRecordService.countAll());
        result.setHealthNum(riskHealthRecordService.countAll());
        result.setConductNum(riskConductVisitRecordService.countAll() + riskConductBureauRuleRecordService.countAll());
        result.setTrainNum(trainFirearmService.countAll() + trainPhysicalService.countAll());

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 警员风险
     * @return
     */
    @GetMapping("/risk/alarm/chart")
    public ResponseEntity<?> riskAlarmChart() {
        IndexRiskAlarmChartResult result = new IndexRiskAlarmChartResult();
        result.setAlarmNum(10);
        result.setGlobalRatio(10d);
        result.setThisMonthNewRatio(11d);
        result.setChartList(Arrays.asList(new ScreenChart()));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 执法办案
     * @return
     */
    @GetMapping("/case/chart")
    public ResponseEntity<?> caseChart() {
        IndexCaseChartResult result = new IndexCaseChartResult();
        result.setExistsErrorPeopleNum(8);
        result.setThisMonthNewRatio(8d);
        result.setReplaceErrorNum(6);
        result.setChartList(Arrays.asList(new ScreenChart()));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 接警执勤
     * @return
     */
    @GetMapping("/duty/chart")
    public ResponseEntity<?> dutyChart() {
        IndexDutyChartResult result = new IndexDutyChartResult();
        result.setExistsErrorPeopleNum(9);
        result.setThisMonthNewRatio(9d);
        result.setReplaceErrorNum(9);
        result.setChartList(Arrays.asList(new ScreenChart()));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 警务技能
     * @return
     */
    @GetMapping("/train/chart")
    public ResponseEntity<?> trainChart() {
        IndexTrainChartResult result = new IndexTrainChartResult();
        result.setTrainCount(9);
        result.setEligibleCount(9);
        result.setEligibleRatio(9d);
        result.setChartList(Arrays.asList(new ScreenDoubeChart()));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 行为规范
     * @return
     */
    @GetMapping("/conduct/chart")
    public ResponseEntity<?> conductChart() {
        List<ScreenChart> result = riskConductBureauRuleRecordService.getConductBureauChart();

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 健康风险
     * @return
     */
    @GetMapping("/health/alarm/chart")
    public ResponseEntity<?> healthAlarmChart() {
        IndexHealthAlarmChartResult result = new IndexHealthAlarmChartResult();
        result.setHealthTotalCount(8);
        result.setHealthyCount(8);
        result.setAlarmCount(8);
        result.setChartList(Arrays.asList(new IndexHealthChart()));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

}
