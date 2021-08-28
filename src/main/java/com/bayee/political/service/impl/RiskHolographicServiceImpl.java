package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskHealth;
import com.bayee.political.domain.User;
import com.bayee.political.json.*;
import com.bayee.political.mapper.RiskHealthMapper;
import com.bayee.political.pojo.dto.RiskPhysicalTrainingRecordDO;
import com.bayee.political.pojo.dto.RiskPoliceDutyResultDO;
import com.bayee.political.service.*;
import com.bayee.political.utils.DateUtils;
import com.bayee.political.utils.SorterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xxl
 * @date 2021/6/14
 */
@Service
public class RiskHolographicServiceImpl implements RiskHolographicService {

    /**
     * 工作经历
     */
    @Autowired
    PoliceWorkingDeptLogService policeWorkingDeptLogService;
    @Autowired
    PoliceWorkingPositionLogService policeWorkingPositionLogService;

    /**
     * 健康风险
     */
    @Autowired
    RiskHealthMapper riskHealthMapper;

    /**
     * 学习经历
     */
    @Autowired
    PoliceEducationLogService policeEducationLogService;

    /**
     * 接警执勤
     */
    @Autowired
    RiskDutyDealPoliceRecordService riskDutyDealPoliceRecordService;

    /**
     * 执法能力
     */
    @Autowired
    RiskCaseAbilityRecordService riskCaseAbilityRecordService;

    /**
     * 执法管理
     */
    @Autowired
    RiskCaseLawEnforcementRecordService riskCaseLawEnforcementRecordService;

    /**
     * 执法考试
     */
    @Autowired
    RiskCaseTestRecordService riskCaseTestRecordService;

    /**
     * 局规积分
     */
    @Autowired
    RiskConductBureauRuleRecordService riskConductBureauRuleRecordService;

    /**
     * 信访投诉
     */
    @Autowired
    RiskConductVisitRecordService riskConductVisitRecordService;

    /**
     * 枪械训练
     */
    @Autowired
    TrainFirearmAchievementService trainFirearmAchievementService;

    /**
     * 综合体能
     */
    @Autowired
    TrainPhysicalAchievementDetailsService trainPhysicalAchievementDetailsService;

    @Autowired
    TrainPhysicalAchievementService trainPhysicalAchievementService;

    /**
     * 警员评价
     */
    @Autowired
    PoliceCommentService policeCommentService;

    @Autowired
    UserService userService;

    @Override
    public List<Map<String, Serializable>> getPoliceCareerType() {
        List<Map<String, Serializable>> result = new ArrayList<>();
        result.add(createTypeMap("接警执勤", 1));
        result.add(createTypeMap("执法能力", 2));
        result.add(createTypeMap("学习经历", 3));
        result.add(createTypeMap("执法管理", 4));
        result.add(createTypeMap("执法考试", 5));
        result.add(createTypeMap("局规记分", 6));
        result.add(createTypeMap("信访投诉", 7));
        result.add(createTypeMap("工作经历", 8));
        result.add(createTypeMap("射击训练", 9));
        result.add(createTypeMap("健康风险", 10));
        result.add(createTypeMap("综合训练", 11));
        result.add(createTypeMap("警员评价", 13));
        return result;
    }

    private Map<String, Serializable> createTypeMap(String key, Integer value) {
        Map<String, Serializable> type = new HashMap<>();
        type.put("key", key);
        type.put("value", value);
        return type;
    }

    @Override
    public List<Object> getPoliceCareer(String policeId, String year, String month, Integer type) {
        User user = userService.findByPoliceId(policeId);

        ArrayList<Object> allResult = new ArrayList<>();
        if (type != null && type > 0) {
            switch (type) {
                case 1 : allResult.addAll(getDutyList(policeId, year, month, user.getName())); break;
                case 2 : allResult.addAll(getCaseAbilityList(policeId, year, month, user.getName())); break;
                case 3 : allResult.addAll(holographicEducationLogList(policeId, year, month)); break;
                case 4 : allResult.addAll(getCaseLawEnforcementList(policeId, year, month, user.getName())); break;
                case 5 : allResult.addAll(getCaseTestList(policeId, year, month, user.getName())); break;
                case 6 : allResult.addAll(getConductBureauRuleList(policeId, year, month, user.getName())); break;
                case 7 : allResult.addAll(getConductVisitList(policeId, year, month, user.getName())); break;
                case 8 : allResult.addAll(holographicPoliceWorkingLogList(policeId, year, month)); break;
                case 9 : allResult.addAll(getFirearmList(policeId, year, month, user.getName())); break;
                case 10 : allResult.add(getHealthList(policeId, year, month)); break;
                case 11 : allResult.addAll(getPhysicalTrainingList(policeId, year, month, user.getName())); break;
                case 13 : allResult.addAll(getCommentList(policeId, year, month, user.getName())); break;
            }
        } else {
            allResult.addAll(getDutyList(policeId, year, month, user.getName()));
            allResult.addAll(getCaseAbilityList(policeId, year, month, user.getName()));
            allResult.addAll(holographicEducationLogList(policeId, year, month));
            allResult.addAll(getCaseLawEnforcementList(policeId, year, month, user.getName()));
            allResult.addAll(getCaseTestList(policeId, year, month, user.getName()));
            allResult.addAll(getConductBureauRuleList(policeId, year, month, user.getName()));
            allResult.addAll(getConductVisitList(policeId, year, month, user.getName()));
            allResult.addAll(holographicPoliceWorkingLogList(policeId, year, month));
            allResult.addAll(getFirearmList(policeId, year, month, user.getName()));
            allResult.add(getHealthList(policeId, year, month));
            allResult.addAll(getPhysicalTrainingList(policeId, year, month, user.getName()));
//        allResult.addAll(getStudyTrainList(policeId, year, month, user.getName()));
            allResult.addAll(getCommentList(policeId, year, month, user.getName()));
        }

        SorterUtil sorter = new SorterUtil();
        allResult = sorter.sortList(allResult);

        return allResult;
    }

    /**
     * 学习培训记录
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    public List<RiskHolographicResult> holographicEducationLogList(String policeId, String year, String month) {
        Map<Date, List<PoliceEducationLogResult>> groupData = policeEducationLogService.findEducationLodByPoliceId(policeId, year, month).stream().map(e -> {
            PoliceEducationLogResult result = new PoliceEducationLogResult();
            result.setId(e.getId());
            result.setSchoolName(e.getSchoolName());
            result.setSchoolIcon(e.getSchoolIcon());
            result.setMajor(e.getMajor());
            result.setAcademicDegree(e.getAcademicDegree());

            String beginDateStr = "";
            String endDateStr = "至今";
            if (e.getBeginDate() != null) {
                beginDateStr = DateUtils.formatDate(e.getBeginDate(), "yyyy年");
            }
            if (e.getEndDate() != null) {
                endDateStr = DateUtils.formatDate(e.getEndDate(), "yyyy年");
            }
            result.setDateInfo(beginDateStr +" - "+ endDateStr);
            result.setDate(e.getBeginDate());
            return result;
        }).collect(Collectors.groupingBy(e -> DateUtils.parseDate(DateUtils.formatDate(e.getDate(),
                "yyyy-MM-dd"), "yyyy-MM-dd")));

        List<RiskHolographicResult> results = new ArrayList<>();
        for (Date date : groupData.keySet()) {
            RiskHolographicResult result = new RiskHolographicResult();
            result.setType(3);
            result.setDate(date);
            result.setResult(Arrays.asList(groupData.get(date).toArray()));
            results.add(result);
        }
        return results;
    }

    /**
     * 工作经历记录
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    public List<RiskHolographicResult> holographicPoliceWorkingLogList(String policeId, String year, String month) {
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        Map<Date, List<PoliceWorkingDeptLogResult>> groupData = policeWorkingDeptLogService.findPoliceWorkingLog(policeId, year, month).parallelStream().map(e -> {
            PoliceWorkingDeptLogResult deptLogResult = new PoliceWorkingDeptLogResult();
            deptLogResult.setId(e.getId());
            deptLogResult.setDeptName(e.getDeptName());
            deptLogResult.setDeptIcon(e.getDeptIcon());
            deptLogResult.setBeginDate(DateUtils.formatDate(e.getWorkingBeginDate(), "yyyy年MM月"));
            deptLogResult.setEndDate(DateUtils.formatDate(e.getWorkingEndDate(), "yyyy年MM月"));
            if (deptLogResult.getEndDate() == null) {
                deptLogResult.setEndDate("至今");
            }
            if (e.getWorkingBeginDate() != null && e.getWorkingEndDate() != null) {
                startCalendar.setTime(e.getWorkingBeginDate());
                endCalendar.setTime(e.getWorkingEndDate());
                int yearInt = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
                int monthInt = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
                if (monthInt < 0) {
                    monthInt += 12;
                    yearInt--;
                }

                deptLogResult.setDateInfo(yearInt+"年"+monthInt+"个月");
            }

            deptLogResult.setDate(e.getWorkingBeginDate());
            deptLogResult.setPositionLogResults(policeWorkingPositionLogService.findByDeptLogIdAndPoliceId(e.getId(), policeId).stream().map(k -> {
                PoliceWorkingPositionLogResult positionLogResult = new PoliceWorkingPositionLogResult();
                positionLogResult.setId(k.getId());
                positionLogResult.setPosition(k.getPosition());
                positionLogResult.setPositionLabel(k.getPositionLabel());
                positionLogResult.setWorkingDesc(k.getWorkingDesc());
                positionLogResult.setWorkingPlace(k.getWorkingPlace());
                positionLogResult.setBeginDate(DateUtils.formatDate(k.getWorkingBeginDate(), "yyyy年MM月"));
                positionLogResult.setEndDate((DateUtils.formatDate(k.getWorkingEndDate(), "yyyy年MM月")));
                if (positionLogResult.getEndDate() == null) {
                    positionLogResult.setEndDate("至今");
                }
                if (k.getWorkingBeginDate() != null && k.getWorkingEndDate() != null) {
                    startCalendar.setTime(k.getWorkingBeginDate());
                    endCalendar.setTime(k.getWorkingEndDate());
                    int yearInt = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
                    int monthInt = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
                    if (monthInt < 0) {
                        monthInt += 12;
                        yearInt--;
                    }

                    positionLogResult.setDateInfo(yearInt+"年"+monthInt+"个月");
                }
                return positionLogResult;
            }).collect(Collectors.toList()));
            return deptLogResult;
        }).collect(Collectors.groupingBy(e -> DateUtils.parseDate(DateUtils.formatDate(e.getDate(),
                "yyyy-MM-dd"), "yyyy-MM-dd")));

        List<RiskHolographicResult> results = new ArrayList<>();
        for (Date date : groupData.keySet()) {
            RiskHolographicResult result = new RiskHolographicResult();
            result.setType(8);
            result.setDate(date);
            result.setResult(Arrays.asList(groupData.get(date).toArray()));
            results.add(result);
        }
        return results;
    }

    /**
     * 接警执勤
     * @param policeId
     * @param year
     * @param month
     * @param policeName
     * @return
     */
    public List<RiskHolographicResult> getDutyList(String policeId, String year, String month, String policeName) {
        Map<Date, List<RiskPoliceDutyResultDO>> groupData = riskDutyDealPoliceRecordService.findDutyRecordYearAndMont(year,
                month, policeId).parallelStream().collect(Collectors.groupingBy(e -> DateUtils.parseDate(DateUtils.formatDate(e.getDate(),
                "yyyy-MM-dd"), "yyyy-MM-dd")));

        List<RiskHolographicResult> results = new ArrayList<>();
        for (Date date : groupData.keySet()) {
            RiskHolographicResult result = new RiskHolographicResult();
            result.setType(1);
            result.setDate(date);
            result.setResult(Arrays.asList(groupData.get(date).toArray()));
            result.setPoliceName(policeName);
            results.add(result);
        }
        return results;
    }

    /**
     * 执法能力
     * @param policeId
     * @param year
     * @param month
     * @param policeName
     * @return
     */
    public List<RiskHolographicResult> getCaseAbilityList(String policeId, String year, String month, String policeName) {
        Map<Date, List<RiskCaseAbilityRecordResult>> groupData = riskCaseAbilityRecordService.
                findCaseAbilityRecordYearAndMont(year, month, policeId).parallelStream().map(e -> {
            RiskCaseAbilityRecordResult result = new RiskCaseAbilityRecordResult();
            result.setPoliceName(policeName);
            result.setReconsiderationLitigationStatus(e.getReconsiderationLitigationStatus() == null ? "未参加" :
                    e.getReconsiderationLitigationStatus() == 1 ? "是" : "否");
            result.setLetterVisitStatus(e.getLetterVisitStatus() == null ? "未参加" : e.getLetterVisitStatus() == 1 ? "是" : "否");
            result.setLawEnforcementFaultStatus(e.getLawEnforcementFaultStatus() == null ? "未参加" :
                    e.getLawEnforcementFaultStatus() == 1 ? "是" : "否");
            result.setJudicialSupervisionStatus(e.getJudicialSupervisionStatus() == null ? "未参加" :
                    e.getJudicialSupervisionStatus() == 1 ? "是" : "否");
            result.setCaseExpertStatus(e.getCaseExpertStatus() == null ? "未参加" : e.getCaseExpertStatus() == 1 ? "是" : "否");
            result.setExcellentLegalOfficerStatus(e.getExcellentLegalOfficerStatus() == null ? "未参加" :
                    e.getExcellentLegalOfficerStatus() == 1 ? "是" : "否");
            result.setBasicTestStatus(e.getBasicTestStatus() == null ? "未参加" : e.getBasicTestStatus() == 1 ? "是" : "否");
            result.setHighTestStatus(e.getHighTestStatus() == null ? "未参加" : e.getHighTestStatus() == 1 ? "是" : "否");
            result.setJudicialTestStatus(e.getJudicialTestStatus() == null ? "未参加" : e.getJudicialTestStatus() == 1 ? "是" : "否");
            result.setDate(e.getCreationDate());
            return result;
        }).collect(Collectors.groupingBy(e -> DateUtils.parseDate(DateUtils.formatDate(e.getDate(),
                "yyyy-MM-dd"), "yyyy-MM-dd")));

        List<RiskHolographicResult> results = new ArrayList<>();
        for (Date date : groupData.keySet()) {
            RiskHolographicResult result = new RiskHolographicResult();
            result.setType(2);
            result.setDate(date);
            result.setResult(Arrays.asList(groupData.get(date).toArray()));
            result.setPoliceName(policeName);
            results.add(result);
        }
        return results;
    }

    /**
     * 执法管理
     * @param policeId
     * @param year
     * @param month
     * @param policeName
     * @return
     */
    public List<RiskHolographicResult> getCaseLawEnforcementList(String policeId, String year, String month,
                                                                 String policeName) {
        Map<Date, List<RiskCaseLawEnforcementRecordResult>> groupData = riskCaseLawEnforcementRecordService.
                findCaseLawEnforcementRecordYearAndMonth(year, month, policeId).parallelStream().map(e -> {
                RiskCaseLawEnforcementRecordResult result = new RiskCaseLawEnforcementRecordResult();
                result.setPoliceName(policeName);
                result.setContent(e.getContent());
                result.setDeductionScore(e.getDeductionScore());
                result.setDate(e.getCreationDate());
                return result;
        }).collect(Collectors.groupingBy(e -> DateUtils.parseDate(DateUtils.formatDate(e.getDate(),
                "yyyy-MM-dd"), "yyyy-MM-dd")));

        List<RiskHolographicResult> results = new ArrayList<>();
        for (Date date : groupData.keySet()) {
            RiskHolographicResult result = new RiskHolographicResult();
            result.setType(4);
            result.setDate(date);
            result.setResult(Arrays.asList(groupData.get(date).toArray()));
            result.setPoliceName(policeName);
            results.add(result);
        }
        return results;
    }

    /**
     * 执法考试
     * @param policeId
     * @param year
     * @param month
     * @param policeName
     * @return
     */
    public List<RiskHolographicResult> getCaseTestList(String policeId, String year, String month, String policeName) {
        Map<Date, List<RiskCaseTestRecordResult>> groupData = riskCaseTestRecordService.
                findCaseTestRecordYearAndMont(year, month, policeId).parallelStream().map(e -> {
            RiskCaseTestRecordResult result = new RiskCaseTestRecordResult();
            result.setPoliceName(policeName);
            result.setName(e.getName());
            result.setScore(e.getScore());
            result.setDate(e.getCreationDate());
            return result;
        }).collect(Collectors.groupingBy(e -> DateUtils.parseDate(DateUtils.formatDate(e.getDate(),
                "yyyy-MM-dd"), "yyyy-MM-dd")));

        List<RiskHolographicResult> results = new ArrayList<>();
        for (Date date : groupData.keySet()) {
            RiskHolographicResult result = new RiskHolographicResult();
            result.setType(5);
            result.setDate(date);
            result.setResult(Arrays.asList(groupData.get(date).toArray()));
            result.setPoliceName(policeName);
            results.add(result);
        }
        return results;
    }

    /**
     * 局规记分
     * @param policeId
     * @param year
     * @param month
     * @param policeName
     * @return
     */
    public List<RiskHolographicResult> getConductBureauRuleList(String policeId, String year, String month, String policeName) {
        Map<Date, List<RiskConductBureauRuleRecordResult>> groupData = riskConductBureauRuleRecordService.
                findConductBureauRuleRecordYearAndMont(year, month, policeId).parallelStream().map(e -> {
            RiskConductBureauRuleRecordResult result = new RiskConductBureauRuleRecordResult();
            result.setPoliceName(policeName);
            result.setReason(e.getContent());
            result.setDeductionScore(e.getDeductionScore());
            result.setDate(e.getCreationDate());
            return result;
        }).collect(Collectors.groupingBy(e -> DateUtils.parseDate(DateUtils.formatDate(e.getDate(),
                "yyyy-MM-dd"), "yyyy-MM-dd")));

        List<RiskHolographicResult> results = new ArrayList<>();
        for (Date date : groupData.keySet()) {
            RiskHolographicResult result = new RiskHolographicResult();
            result.setType(6);
            result.setDate(date);
            result.setResult(Arrays.asList(groupData.get(date).toArray()));
            result.setPoliceName(policeName);
            results.add(result);
        }
        return results;
    }

    /**
     * 信访投诉
     * @param policeId
     * @param year
     * @param month
     * @param policeName
     * @return
     */
    public List<RiskHolographicResult>  getConductVisitList(String policeId, String year, String month, String policeName) {
        Map<Date, List<RiskConductVisitRecordResult>> groupData = riskConductVisitRecordService.
                findConductVisitRecordYearAndMont(year, month, policeId).parallelStream().map(e -> {
            RiskConductVisitRecordResult result = new RiskConductVisitRecordResult();
            result.setPoliceName(policeName);
            result.setContent(e.getContent());
            result.setDeductionScore(e.getDeductionScore());
            result.setDate(e.getCreationDate());
            return result;
        }).collect(Collectors.groupingBy(e -> DateUtils.parseDate(DateUtils.formatDate(e.getDate(),
                "yyyy-MM-dd"), "yyyy-MM-dd")));

        List<RiskHolographicResult> results = new ArrayList<>();
        for (Date date : groupData.keySet()) {
            RiskHolographicResult result = new RiskHolographicResult();
            result.setType(7);
            result.setDate(date);
            result.setResult(Arrays.asList(groupData.get(date).toArray()));
            result.setPoliceName(policeName);
            results.add(result);
        }
        return results;
    }

    /**
     * 射击训练
     * @param policeId
     * @param year
     * @param month
     * @param policeName
     * @return
     */
    public List<RiskHolographicResult> getFirearmList(String policeId, String year, String month, String policeName) {
        Map<Date, List<RiskFirearmRecordResult>> groupData = trainFirearmAchievementService.
                findFirearmRecordYearAndMont(year, month, policeId).parallelStream().map(e -> {
            RiskFirearmRecordResult result = new RiskFirearmRecordResult();
            result.setPoliceName(policeName);
            result.setRapidFireScore(e.getAchievement());
            result.setRapidFireFlag(e.getAchievementGrade() == null ? "--" : e.getAchievementGrade() == 1 ? "不合格" :
                    e.getAchievementGrade() == 2 ? "合格" : "未参加");
            result.setDate(e.getSignDate());
            return result;
        }).collect(Collectors.groupingBy(e -> DateUtils.parseDate(DateUtils.formatDate(e.getDate(),
                "yyyy-MM-dd"), "yyyy-MM-dd")));

        List<RiskHolographicResult> results = new ArrayList<>();
        for (Date date : groupData.keySet()) {
            RiskHolographicResult result = new RiskHolographicResult();
            result.setType(9);
            result.setDate(date);
            result.setResult(Arrays.asList(groupData.get(date).toArray()));
            result.setPoliceName(policeName);
            results.add(result);
        }
        return results;
    }

    /**
     * 综合训练
     * @param policeId
     * @param year
     * @param month
     * @param policeName
     * @return
     */
    public List<RiskHolographicResult> getPhysicalTrainingList(String policeId, String year, String month, String policeName) {
        Map<Date, List<RiskPhysicalTrainingRecordResult>> groupData = trainPhysicalAchievementService.
                findPhysicalTrainingRecordYearAndMonth(year, month, policeId).parallelStream().map(e -> {
            List<RiskPhysicalTrainingRecordDO> physicalTrainingRecordDOList = trainPhysicalAchievementDetailsService.
                    physicalTrainingRecordCareerQuery(e.getId());

            RiskPhysicalTrainingRecordResult result = new RiskPhysicalTrainingRecordResult();
            result.setPoliceName(policeName);

            int comprehensiveFlag = 2;
            for (RiskPhysicalTrainingRecordDO t : physicalTrainingRecordDOList) {
                if ("2000米跑".equals(t.getTrainProjectName())) {
                    result.setLongDistanceRunFlag(t.getTrainAchievement() == null ? "--" : t.getTrainAchievement() == 2 ? "合格" :
                            t.getTrainAchievement() == 1 ? "不合格" : "未参加");
                }
                if ("仰卧起坐".equals(t.getTrainProjectName())) {
                    result.setAbdominalCurlFlag(t.getTrainAchievement() == null ? "--" : t.getTrainAchievement() == 2 ? "合格" :
                            t.getTrainAchievement() == 1 ? "不合格" : "未参加");
                }
                if ("引体向上".equals(t.getTrainProjectName())) {
                    result.setPullUpFlag(t.getTrainAchievement() == null ? "--" : t.getTrainAchievement() == 2 ? "合格" :
                            t.getTrainAchievement() == 1 ? "不合格" : "未参加");
                }
                if ("俯卧撑".equals(t.getTrainProjectName())) {
                    result.setPushUpFlag(t.getTrainAchievement() == null ? "--" : t.getTrainAchievement() == 2 ? "合格" :
                            t.getTrainAchievement() == 1 ? "不合格" : "未参加");
                }
                if ("立定跳远".equals(t.getTrainProjectName())) {
                    result.setSargentJumpFlag(t.getTrainAchievement() == null ? "--" : t.getTrainAchievement() == 2 ? "合格" :
                            t.getTrainAchievement() == 1 ? "不合格" : "未参加");
                }
                if ("握力".equals(t.getTrainProjectName())) {
                    result.setGripFlag(t.getTrainAchievement() == null ? "--" : t.getTrainAchievement() == 2 ? "合格" :
                            t.getTrainAchievement() == 1 ? "不合格" : "未参加");
                }
                if ("50米跑".equals(t.getTrainProjectName())) {
                    result.setSprintFlag(t.getTrainAchievement() == null ? "--" : t.getTrainAchievement() == 2 ? "合格" :
                            t.getTrainAchievement() == 1 ? "不合格" : "未参加");
                }
                if ("坐位体前屈".equals(t.getTrainProjectName())) {
                    result.setSitAndReachFlag(t.getTrainAchievement() == null ? "--" : t.getTrainAchievement() == 2 ? "合格" :
                            t.getTrainAchievement() == 1 ? "不合格" : "未参加");
                }
                if (t.getTrainAchievement() != null && t.getTrainAchievement() == 1) {
                    comprehensiveFlag = 1;
                }
            }
            result.setComprehensiveFlag(comprehensiveFlag == 2 ? "合格" : comprehensiveFlag == 1 ? "不合格" : "未参加");
            result.setDate(e.getCreationDate());
            return result;
        }).collect(Collectors.groupingBy(e -> DateUtils.parseDate(DateUtils.formatDate(e.getDate(),
                "yyyy-MM-dd"), "yyyy-MM-dd")));

        List<RiskHolographicResult> results = new ArrayList<>();
        for (Date date : groupData.keySet()) {
            RiskHolographicResult result = new RiskHolographicResult();
            result.setType(11);
            result.setDate(date);
            result.setResult(Arrays.asList(groupData.get(date).toArray()));
            result.setPoliceName(policeName);
            results.add(result);
        }
        return results;
    }

    /**
     * 评价
     * @param policeId
     * @param year
     * @param month
     * @param policeName
     * @return
     */
    public List<RiskHolographicResult> getCommentList(String policeId, String year, String month, String policeName) {
        Map<Date, List<RiskPoliceCommentResult>> groupData = policeCommentService.
                findCommentByPoliceIdAndYearAndMonth(policeId, year, month).parallelStream().map(e -> {
            RiskPoliceCommentResult result = new RiskPoliceCommentResult();
            result.setPoliceName(policeName);
            result.setComment(e.getComment());
            result.setDate(e.getCreationDate());
            return result;
        }).collect(Collectors.groupingBy(e -> DateUtils.parseDate(DateUtils.formatDate(e.getDate(),
                "yyyy-MM-dd"), "yyyy-MM-dd")));

        List<RiskHolographicResult> results = new ArrayList<>();
        for (Date date : groupData.keySet()) {
            RiskHolographicResult result = new RiskHolographicResult();
            result.setType(13);
            result.setDate(date);
            result.setResult(Arrays.asList(groupData.get(date).toArray()));
            result.setPoliceName(policeName);
            results.add(result);
        }
        return results;
    }

    /**
     * 健康风险
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    public RiskPoliceHealthResult getHealthList(String policeId, String year, String month) {
        RiskHealth health = riskHealthMapper.riskHealthIndexItem(policeId, year);
        RiskPoliceHealthResult result = new RiskPoliceHealthResult();
        result.setHeight(health.getHeight());
        result.setWeight(health.getWeight());
        result.setBmiValue(health.getBmi());
        result.setBmiName(health.getBmiName());
        result.setOverWeightScore(health.getOverweightNum());
        result.setHyperlipidemiaScore(health.getHyperlipidemiaNum());
        result.setHypertensionScore(health.getHypertensionNum());
        result.setHyperglycemiaScore(health.getHyperglycemiaNum());
        result.setHyperuricemiaScore(health.getHyperuricemiaNum());
        result.setProstateScore(health.getProstateNum());
        result.setMajorDiseasesScore(health.getMajorDiseasesNum());
        result.setHeartScore(health.getHeartNum());
        result.setTumorAntigenScore(health.getTumorAntigenNum());
        result.setOrthopaedicsScore(health.getOrthopaedicsNum());
        result.setDate(health.getCreationDate());
        return result;
    }

}
