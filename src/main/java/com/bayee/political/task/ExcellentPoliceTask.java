package com.bayee.political.task;

import cn.hutool.core.date.DateUtil;
import com.bayee.political.domain.ExcellentPoliceInfo;
import com.bayee.political.domain.RiskCaseTestRecord;
import com.bayee.political.domain.User;
import com.bayee.political.mapper.RiskCaseMapper;
import com.bayee.political.service.*;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 人才库计算task
 * @author xxl
 * @date 2021/8/14
 */
@Component
@EnableScheduling
@RestController
public class ExcellentPoliceTask {

    @Autowired
    UserService userService;

    @Autowired
    ExcellentPoliceInfoService excellentPoliceInfoService;

    @Autowired
    TrainFirearmAchievementService trainFirearmAchievementService;

    @Autowired
    RiskCaseTestRecordService riskCaseTestRecordService;

    @Autowired
    TrainPhysicalAchievementService trainPhysicalAchievementService;

    /**
     * 执法能力
     */
    @Autowired
    RiskCaseAbilityService riskCaseAbilityService;

    /**
     * 执法管理
     */
    @Autowired
    RiskCaseLawEnforcementRecordService riskCaseLawEnforcementRecordService;

    @GetMapping("/test/excellent")
    @Scheduled(cron = "0 0 2 * * ?")
    public void excellentPoliceCompute() {
        Integer serviceCount = 2;

        List<ExcellentPoliceInfo> excellentPoliceInfos = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 6);
        String beginDate = DateUtils.formatDate(calendar.getTime(), "yyyy-MM");
        String endDate = DateUtils.formatDate(new Date(), "yyyy-MM");

        List<User> userList = userService.userAllList();
        for (User user : userList) {
            //神枪手
            Integer avgAchievement = trainFirearmAchievementService.policeFirearmAchievementAvg(user.getPoliceId(), beginDate, endDate);
            if (avgAchievement != null && avgAchievement > 95) {
                excellentPoliceInfos.add(createExcellentPoliceInfo(user.getPoliceId(), 1, 1));
            }

            //法制专家
            Integer testAvgScore = riskCaseTestRecordService.riskCaseTestScoreAvg(user.getPoliceId(), beginDate, endDate);
            if (testAvgScore != null && testAvgScore > 90) {
                excellentPoliceInfos.add(createExcellentPoliceInfo(user.getPoliceId(), 4, 1));
            }

            //训练标兵
            if (trainPhysicalAchievementService.physicalTrainingRecordStatistics(user.getPoliceId(), serviceCount, beginDate, endDate)) {
                excellentPoliceInfos.add(createExcellentPoliceInfo(user.getPoliceId(), 2, 1));
            }

            //办案能手
            /*boolean caseFlag = riskCaseAbilityService.checkPoliceDeductionStatus(user.getPoliceId(), beginDate, endDate)
                    &&
                    riskCaseLawEnforcementRecordService.checkPoliceDeductionScoreStatus(user.getPoliceId(), beginDate, endDate);
            if (caseFlag) {
                excellentPoliceInfos.add(createExcellentPoliceInfo(user.getPoliceId(), 3, 1));
            }*/
        }
        excellentPoliceInfoService.excellentPoliceInfoListDetails(excellentPoliceInfos);
    }

    /**
     * 创建人才库信息
     * @param policeId 警号
     * @param label 标签
     * @param score 记分值
     * @return
     */
    private ExcellentPoliceInfo createExcellentPoliceInfo(String policeId, Integer label, Integer score) {
        ExcellentPoliceInfo excellentPoliceInfo = new ExcellentPoliceInfo();
        excellentPoliceInfo.setPoliceId(policeId);
        excellentPoliceInfo.setLabel(label);
        excellentPoliceInfo.setScore(score);
        excellentPoliceInfo.setCreationDate(new Date());
        return excellentPoliceInfo;
    }

}
