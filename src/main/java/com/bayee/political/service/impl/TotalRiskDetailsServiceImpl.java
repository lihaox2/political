package com.bayee.political.service.impl;

import cn.hutool.core.date.DateUtil;
import com.bayee.political.algorithm.RiskCompute;
import com.bayee.political.domain.*;
import com.bayee.political.pojo.GlobalIndexNumResultDO;
import com.bayee.political.service.*;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author xxl
 * @date 2021/5/17
 */
@Service
public class TotalRiskDetailsServiceImpl implements TotalRiskDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    private RiskService riskService;

    @Autowired
    RiskReportRecordService riskReportRecordService;

    @Autowired
    HandlingCasesRiskService handlingCasesRiskService;

    @Autowired
    RiskConductBureauRuleService riskConductBureauRuleService;

    @Autowired
    RiskConductService riskConductService;

    @Autowired
    DutyRiskService dutyRiskService;

    @Autowired
    RiskSkillService riskSkillService;

    @Override
    @Async
    public void caseRiskDetails(LocalDate localDate) {
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String year = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
        String month = localDate.format(DateTimeFormatter.ofPattern("MM"));

        List<User> userList = userService.userAllList();
        for (User user : userList) {
            RiskReportRecord reportRecord = riskReportRecordService.findRiskReportRecord(user.getPoliceId(), year, month);
            //执法办案
            RiskCase riskCase = handlingCasesRiskService.handlingCasesRiskDetailsV2(user, date);
            if (riskCase == null) {
                return;
            }

            if (riskCase.getId() == null) {
                handlingCasesRiskService.addRiskCaseList(Arrays.asList(riskCase));
            }

            if (reportRecord != null) {
                reportRecord.setHandlingCaseNum(riskCase.getIndexNum());
                reportRecord.setUpdateDate(new Date());
                riskReportRecordService.updateRiskReportRecord(reportRecord);
            }else {
                reportRecord = new RiskReportRecord(0d);
                GlobalIndexNumResultDO resultDO = riskReportRecordService.findRiskReportRecordGlobalIndexNum(date);
                reportRecord.setPoliceId(user.getPoliceId());
                reportRecord.setCreationDate(DateUtil.parseDate(date));
                reportRecord.setYear(year);
                reportRecord.setMonth(month);
                reportRecord.setHandlingCaseNum(riskCase.getIndexNum());
                reportRecord.setTotalNum(RiskCompute.normalizationCompute(resultDO.getMaxNum(), resultDO.getMinNum(), reportRecord.getHandlingCaseNum()));
                riskReportRecordService.insert(reportRecord);
            }
        }
    }

    @Override
//    @Async
    public void conductRiskDetails(LocalDate localDate) {
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String year = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
        String month = localDate.format(DateTimeFormatter.ofPattern("MM"));

        List<User> userList = userService.userAllList();
        for (User user : userList) {
            RiskReportRecord reportRecord = riskReportRecordService.findRiskReportRecord(user.getPoliceId(), year, month);
            //行为规范
            RiskConduct riskConduct = riskConductBureauRuleService.riskConductDetailsV2(user, date);
            if (riskConduct == null) {
                return;
            }

            if (riskConduct.getId() == null) {
                riskConductService.insertRiskConductList(Arrays.asList(riskConduct));
            }

            if (reportRecord != null) {
                reportRecord.setConductNum(riskConduct.getIndexNum());
                riskReportRecordService.updateRiskReportRecord(reportRecord);
            }else {
                reportRecord = new RiskReportRecord(0d);
                GlobalIndexNumResultDO resultDO = riskReportRecordService.findRiskReportRecordGlobalIndexNum(date);
                reportRecord.setPoliceId(user.getPoliceId());
                reportRecord.setCreationDate(DateUtil.parseDate(date));
                reportRecord.setYear(year);
                reportRecord.setMonth(month);
                reportRecord.setConductNum(riskConduct.getIndexNum());
                reportRecord.setTotalNum(RiskCompute.normalizationCompute(resultDO.getMaxNum(), resultDO.getMinNum(), reportRecord.getConductNum()));
                riskReportRecordService.insert(reportRecord);
            }
        }
    }

    @Override
    @Async
    public void dutyRiskDetails(LocalDate localDate) {
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String year = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
        String month = localDate.format(DateTimeFormatter.ofPattern("MM"));

        List<User> userList = userService.userAllList();
        for (User user : userList) {
            RiskReportRecord reportRecord = riskReportRecordService.findRiskReportRecord(user.getPoliceId(), year, month);
            //接警执勤
            RiskDuty riskDuty = dutyRiskService.dutyRiskDetailsV2(user, date);
            if (riskDuty == null) {
                return;
            }

            if (riskDuty.getId() == null) {
                dutyRiskService.addRiskDutyList(Arrays.asList(riskDuty));
            }

            if (reportRecord != null) {
                reportRecord.setDutyNum(riskDuty.getIndexNum());
                riskReportRecordService.updateRiskReportRecord(reportRecord);
            } else {
                reportRecord = new RiskReportRecord(0d);
                GlobalIndexNumResultDO resultDO = riskReportRecordService.findRiskReportRecordGlobalIndexNum(date);
                reportRecord.setPoliceId(user.getPoliceId());
                reportRecord.setCreationDate(DateUtil.parseDate(date));
                reportRecord.setYear(year);
                reportRecord.setMonth(month);
                reportRecord.setDutyNum(riskDuty.getIndexNum());
                reportRecord.setTotalNum(RiskCompute.normalizationCompute(resultDO.getMaxNum(), resultDO.getMinNum(), reportRecord.getDutyNum()));
                riskReportRecordService.insert(reportRecord);
            }
        }
    }

    @Override
    @Async
    public void healthRiskDetails(LocalDate localDate) {
        //TODO 没有到人
        riskReportRecordService.health(localDate);
    }

    @Override
    @Async
    public void skillRiskDetails(LocalDate localDate) {
        String dateTime = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String year = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
        String month = localDate.format(DateTimeFormatter.ofPattern("MM"));

        List<User> userList = userService.userInfoAllList();
        for (int i = 0; i < userList.size(); i++) {
            // 警员警务技能指数查询
            RiskTrain ritem = riskService.riskTrainIndexItem(userList.get(i).getPoliceId(), dateTime,null,2);
            // 警员警务技能统计查询
            RiskTrain item = riskService.riskTrainStatisticsItem(userList.get(i).getPoliceId(), dateTime);

            if (ritem == null) {
                item.setPoliceId(userList.get(i).getPoliceId());
                item.setIndexNum(0.0);
                item.setPhysicalStandardStatus(0);
                item.setFirearmStandardStatus(0);
                item.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
                riskService.riskTrainCreat(item);
            } else {
                ritem.setPhysicalNum(item.getPhysicalNum());
                ritem.setPhysicalPassNum(item.getPhysicalPassNum());
                ritem.setPhysicalFailNum(item.getPhysicalFailNum());
                ritem.setFirearmNum(item.getFirearmNum());
                ritem.setFirearmPassNum(item.getFirearmPassNum());
                ritem.setFirearmFailNum(item.getFirearmFailNum());
                ritem.setUpdateDate(new Date());
                riskService.riskTrainUpdate(ritem);
            }

            RiskTrain riskTrain = riskSkillService.riskSkillDetailsV2(userList.get(i), date);
            if (riskTrain == null || riskTrain.getIndexNum() == null) {
                continue;
            }

            RiskReportRecord reportRecord = riskReportRecordService.findRiskReportRecord(userList.get(i).getPoliceId(), year, month);
            if (reportRecord != null) {
                reportRecord.setTrainNum(riskTrain.getIndexNum());
                riskReportRecordService.updateRiskReportRecord(reportRecord);
            }else {
                reportRecord = new RiskReportRecord(0d);
                GlobalIndexNumResultDO resultDO = riskReportRecordService.findRiskReportRecordGlobalIndexNum(date);
                reportRecord.setPoliceId(userList.get(i).getPoliceId());
                reportRecord.setCreationDate(DateUtil.parseDate(date));
                reportRecord.setYear(year);
                reportRecord.setMonth(month);
                reportRecord.setTrainNum(riskTrain.getIndexNum());
                reportRecord.setTotalNum(RiskCompute.normalizationCompute(resultDO.getMaxNum(), resultDO.getMinNum(), reportRecord.getTrainNum()));
                riskReportRecordService.insert(reportRecord);
            }
        }
    }

}
