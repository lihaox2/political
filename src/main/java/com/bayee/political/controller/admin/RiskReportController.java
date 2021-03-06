package com.bayee.political.controller.admin;

import cn.hutool.core.util.StrUtil;
import com.bayee.political.domain.*;
import com.bayee.political.json.RiskReportTrainResult;
import com.bayee.political.pojo.RiskReportTypeStatisticsDO;
import com.bayee.political.pojo.dto.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import com.bayee.political.utils.HttpIPUtil;
import com.itextpdf.text.pdf.BaseFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.fragment.IFragmentSpec;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xxl
 * @date 2021/8/1
 */
@RestController
public class RiskReportController {

    @Autowired
    UserService userService;

    @Autowired
    RiskService riskService;

    @Autowired
    RiskCaseLawEnforcementTypeService riskCaseLawEnforcementTypeService;

    @Autowired
    RiskConductBureauRuleTypeService riskConductBureauRuleTypeService;

    @Autowired
    RiskConductVisitTypeService riskConductVisitTypeService;

    @Autowired
    MeasuresService measuresService;

    @Autowired
    RiskConductVisitService riskConductVisitService;

    @Autowired
    RiskConductTrafficViolationService riskConductTrafficViolationService;

    @Autowired
    RiskFamilyEvaluationService riskFamilyEvaluationService;

    @Autowired
    RiskConductService riskConductService;

    @Autowired
    RiskSkillService riskSkillService;

    @Autowired
    RiskHealthRecordService riskHealthRecordService;

    @Autowired
    RiskHealthRecordInfoService riskHealthRecordInfoService;

    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
    static Map<String, String> colorMap = new HashMap<>();
    // 2021.11.17 tlt???
//      final String HOST = "http://8.136.146.186:9097";
//    final String HOST = "http://41.190.128.250:8080";


    static {
        //?????????#C72928????????????#F59A23????????????#42B72A
        colorMap.put("??????", "#F59A23");
        colorMap.put("??????", "#42B72A");
        colorMap.put("??????", "#F59A23");
        colorMap.put("??????", "#C72928");
    }

    /**
     *
     * @param policeId ??????
     * @param timeType ???????????? 1.??????2.??????3.?????????
     * @param dateTime ?????????????????? yyyy-MM
     * @param beginDate ?????????????????? yyyy-MM
     * @param endDate ?????????????????? yyyy-MM
     * @return ??????????????????
     * @throws Exception
     */
    @GetMapping("/create/policeRisk/report")
    public ResponseEntity<?> createPoliceRiskReport(@RequestParam("policeId") String policeId,
                                                    @RequestParam("timeType") Integer timeType,
                                                    @RequestParam("dateTime") String dateTime,
                                                    @RequestParam(value = "desc", required = false) String desc,
                                                    @RequestParam(value = "beginDate", required = false) String beginDate,
                                                    @RequestParam(value = "endDate", required = false) String endDate) throws Exception {
        String lastMonthTime = DateUtils.getCurrentYearFirstMonthTime();
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
        if (dateTime == null) {
            dateTime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        }else {
            date = dateTime+"-01";
            year = dateTime.substring(0, 4);
        }

//        String lastDateTime = "";
        if (timeType != null && timeType == 1) {
            timeType = 1;
            lastMonthTime = year+"-01";
            dateTime = year+"-12";
        } else if(timeType == 2) {
            timeType = 2;

        } else if(timeType == 3) {
            timeType = 1;
            lastMonthTime = beginDate;
            dateTime = endDate;
        }

        //????????????
        User user = userService.findByPoliceId(policeId);
        RiskConduct conduct = riskConductService.riskConductItem(policeId, dateTime, lastMonthTime, timeType);
        if (conduct == null) {
            conduct = new RiskConduct();
        }
        RiskDuty duty = riskService.riskDutyIndexItem(policeId, dateTime, lastMonthTime, timeType);
        if (duty == null) {
            duty = new RiskDuty();
        }
        RiskSocialContact socialContact = riskService.riskSocialContactIndexItem(policeId, dateTime, lastMonthTime, timeType);
        if (socialContact == null) {
            socialContact = new RiskSocialContact();
        }
        RiskFamilyEvaluation familyEvaluation = riskFamilyEvaluationService.riskFamilyEvaluationItem(policeId, dateTime);
        if (familyEvaluation == null) {
            familyEvaluation = new RiskFamilyEvaluation();
        }
        RiskCase riskCase = riskService.riskCaseIndexItem(policeId, dateTime, lastMonthTime, timeType);
        if (riskCase == null) {
            riskCase = new RiskCase();
        }
        RiskCaseTest caseTest = riskService.riskCaseTestIndexItem(policeId, dateTime, lastMonthTime, timeType);
        if (caseTest == null) {
            caseTest = new RiskCaseTest();
        }
        RiskCaseAbility caseAbility = riskService.riskCaseAbilityIndexItem(policeId, dateTime, lastMonthTime, timeType);
        if (caseAbility == null) {
            caseAbility = new RiskCaseAbility();
        }
        RiskHealth health = riskService.riskHealthIndexItem(policeId, year);
        if (health == null) {
            health = new RiskHealth();
        }
        RiskTrain train = riskService.riskTrainIndexItem(policeId, dateTime, lastMonthTime, timeType);
        if (train == null) {
            train = new RiskTrain();
        }
        RiskHealth riskHealth = riskService.riskHealthIndexItem(policeId, year);
        if (riskHealth == null) {
            riskHealth = new RiskHealth();
        }
        RiskHealthRecord healthRecord = riskHealthRecordService.findByPoliceIdAndYear(policeId, year);
        if (healthRecord == null) {
            healthRecord = new RiskHealthRecord();
        }
        RiskHealthRecordInfo healthRecordInfo = riskHealthRecordInfoService.findByRecordId(healthRecord.getId());
        if (healthRecordInfo == null) {
            healthRecordInfo = new RiskHealthRecordInfo();
        }
        RiskCaseLawEnforcementReportDO lawEnforcementReportDO = riskService.lawEnforcementReportDOQuery(policeId,
                dateTime, lastMonthTime, timeType);
        if (lawEnforcementReportDO == null) {
            lawEnforcementReportDO = new RiskCaseLawEnforcementReportDO();
        }
        RiskConductVisitReportDO visitReportDO = riskService.visitReportDOQuery(policeId, dateTime, lastMonthTime, timeType);
        if (visitReportDO == null) {
            visitReportDO = new RiskConductVisitReportDO();
        }
        RiskConductBureauRuleReportDO bureauRuleReportDO = riskService.bureauRuleReportDOQuery(policeId, dateTime,
                lastMonthTime, timeType);
        if (bureauRuleReportDO == null) {
            bureauRuleReportDO = new RiskConductBureauRuleReportDO();
        }
        RiskConductTrafficViolationReportDO trafficViolationReportDO = riskService.trafficViolationReportDOQuery(policeId,
                dateTime, lastMonthTime, timeType);
        if (trafficViolationReportDO == null) {
            trafficViolationReportDO = new RiskConductTrafficViolationReportDO();
        }
        RiskDutyReportDO dutyReportDO = riskService.dutyReportDOQuery(policeId, dateTime, lastMonthTime, timeType);
        if (dutyReportDO == null) {
            dutyReportDO = new RiskDutyReportDO();
        }
        RiskHealthReportDO healthReportDO = riskService.healthReportDOQuery(policeId);
        if (healthReportDO == null) {
            healthReportDO = new RiskHealthReportDO();
        }
        RiskReportRecord reportRecord = riskService.riskReportRecordItem(null, policeId, dateTime, lastMonthTime,
                lastMonthTime, timeType);
        if (reportRecord == null) {
            reportRecord = new RiskReportRecord();
        }

        //????????????-?????????????????????????????????????????????????????????????????????????????????????????????
        List<RiskReportTypeStatisticsDO> lawEnforcementTypeList = riskService.lawEnforcementReportTypeDOQuery(policeId, dateTime, lastMonthTime, timeType);
        List<RiskReportTypeStatisticsDO> conductVisitTypeList = riskService.conductVisitReportTypeDOQuery(policeId, dateTime, lastMonthTime, timeType);
        List<RiskReportTypeStatisticsDO> conductBureauRuleTypeList = riskService.conductBureauRuleReportTypeDOQuery(policeId, dateTime, lastMonthTime, timeType);
        List<RiskReportTypeStatisticsDO> dutyTypeList = riskService.dutyReportTypeDOQuery(policeId, dateTime, lastMonthTime, timeType);

        //????????????-????????????????????????????????????????????????
        List<TrainFirearm> trainFirearmList = riskService.findPoliceUnQualifiedTrainFirearm(policeId);
        List<TrainPhysical> trainPhysicalList = riskService.findPoliceUnQualifiedTrainPhysical(policeId);

        Double totalNum = nullToZero(conduct.getIndexNum()) + nullToZero(riskCase.getIndexNum()) + nullToZero(duty.getIndexNum()) +
                nullToZero(train.getIndexNum()) + nullToZero(socialContact.getIndexNum()) + nullToZero(familyEvaluation.getIndexNum()) +
                nullToZero(health.getIndexNum());

        String dateUnit = "???";
        String inputDate = DateUtils.formatDate(reportRecord.getCreationDate(), "yyyy???MM???");
        if (timeType == 1) {
            dateUnit = "???";
            inputDate = DateUtils.formatDate(DateUtils.parseDate(lastMonthTime, "yyyy-MM"), "yyyy???MM???")
                    + "~" +
                    DateUtils.formatDate(DateUtils.parseDate(dateTime, "yyyy-MM"), "yyyy???MM???");
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("riskTitle", user.getName()+"-????????????");
        map.put("dateType", dateUnit);
        map.put("policeImg", HttpIPUtil.HOST + "/static"+ user.getHeadImage());
        map.put("totalScore", "???????????????"+totalNum);
        map.put("policeName", user.getName());
        map.put("policeId", user.getPoliceId());
        map.put("deptName", user.getDepartmentName());
        map.put("positionName", user.getPositionName());
        map.put("thisMonthScore", nullToZero(reportRecord.getCurrentTotalNum()));
        map.put("lastMonthScore", nullToZero(reportRecord.getLastTotalNum()));

        map.put("belongingDate", "???????????????"+inputDate);

        //????????????
        map.put("conductTotalScore", nullToZero(conduct.getIndexNum()));
        //????????????
        map.put("bureauRuleTotalScore", nullToZero(conduct.getBureauRuleScore()));
        map.put("bureauRuleTotalCount", nullToZero(bureauRuleReportDO.getTotalCount()));
        map.put("maxBureauRuleDeductionScore", nullToZero(bureauRuleReportDO.getMaxDeductionScore()));
        map.put("minBureauRuleDeductionScore", nullToZero(bureauRuleReportDO.getMinDeductionScore()));
        map.put("bureauRuleDeductionTypeName", bureauRuleReportDO.getTypeName());
        //????????????
        map.put("visitTotalScore", nullToZero(conduct.getVisitScore()));
        map.put("visitTotalCount", nullToZero(visitReportDO.getTotalCount()));
        map.put("visitTypeName", visitReportDO.getTypeName() == null ? "" : visitReportDO.getTypeName());
        map.put("maxVisitDeductionScore", nullToZero(visitReportDO.getMaxDeductionScore()));
        //????????????
        map.put("trafficViolationTotalScore", nullToZero(conduct.getTrafficViolationScore()));
        map.put("trafficViolationTotalCount", nullToZero(trafficViolationReportDO.getTotalCount()));
        map.put("maxTrafficViolationScore", nullToZero(trafficViolationReportDO.getMaxDeductionScore()));
        map.put("trafficViolationContinueText", "");

        //????????????
        map.put("caseTotalScore", nullToZero(riskCase.getIndexNum()));
        //????????????
        map.put("caseAbilityTotalScore", nullToZero(riskCase.getAbilityNum()));
        map.put("reconsiderationLitigationTotalScore", nullToZero(caseAbility.getReconsiderationLitigationScore()));
        map.put("letterVisitTotalScore", nullToZero(caseAbility.getLetterVisitScore()));
        map.put("lawEnforcementFaultTotalScore", nullToZero(caseAbility.getLawEnforcementFaultScore()));
        map.put("judicialSupervisionTotalScore", nullToZero(caseAbility.getJudicialSupervisionScore()));
        //????????????
        map.put("lawEnforcementTotalScore", nullToZero(riskCase.getLawEnforcementNum()));
        map.put("lawEnforcementCount", nullToZero(lawEnforcementReportDO.getTotalCount()));
        map.put("lawEnforcementMaxScore", nullToZero(lawEnforcementReportDO.getMaxDeductionScore()));
        map.put("lawEnforcementMinScore", nullToZero(lawEnforcementReportDO.getMinDeductionScore()));
        map.put("lawEnforcementTypeName", lawEnforcementReportDO.getTypeName() == null ? "" : lawEnforcementReportDO.getTypeName());
        //????????????
        int unQualifiedCount = nullToZero(caseTest.getCumulativeNum()) - nullToZero(caseTest.getPassNum());
        map.put("caseTestTotalScore", nullToZero(riskCase.getTestNum()));
        map.put("caseTestCount", nullToZero(caseTest.getCumulativeNum()));
        map.put("caseTestQualifiedCount", nullToZero(caseTest.getPassNum()));
        map.put("caseTestUnQualifiedCount", unQualifiedCount);

        //????????????
        map.put("dutyTotalScore", nullToZero(duty.getIndexNum()));
        map.put("dutyTotalCount", nullToZero(duty.getDeductionScoreCount()));
        map.put("dutyMaxScore", nullToZero(dutyReportDO.getMaxDeductionScore()));
        map.put("dutyMinScore", nullToZero(dutyReportDO.getMinDeductionScore()));

        //????????????
        map.put("skillTotalScore", nullToZero(train.getIndexNum()));
        //????????????
        map.put("physicalAchievementTotalScore", nullToZero(train.getPhysicalScore()));
        map.put("physicalAchievementCount", nullToZero(train.getPhysicalNum()));
        map.put("physicalAchievementQualifiedCount", nullToZero(train.getPhysicalPassNum()));
        map.put("physicalAchievementUnQualifiedCount", nullToZero(train.getPhysicalFailNum()));
        //TODO ???????????????????????????
        map.put("physicalAchievementUnTestCount", "0");
        map.put("physicalAchievementDrawCount", "0");
        map.put("physicalUnQualifiedList", trainPhysicalList.parallelStream().map(e -> {
            RiskReportTrainResult result = new RiskReportTrainResult();
            result.setName(textParser(e.getName(), 11));
            result.setUnQualifiedProject(textParser(riskService.findPoliceUnQualifiedProject(e.getId(), policeId)
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",")), 9));
            result.setDate(DateUtils.formatDate(e.getTrainStartDate(), "yyyy???MM???dd???") +"-"+
                    DateUtils.formatDate(e.getTrainEndDate(), "yyyy???MM???dd???"));
            return result;
        }).collect(Collectors.toList()));

        //????????????
        map.put("firearmTotalScore", nullToZero(train.getFirearmScore()));
        map.put("firearmCount", nullToZero(train.getFirearmNum()));
        map.put("firearmQualifiedCount", nullToZero(train.getFirearmPassNum()));
        map.put("firearmUnQualifiedCount", nullToZero(train.getFirearmFailNum()));
        map.put("firearmUnQualifiedList", trainFirearmList.parallelStream().map(e -> {
            RiskReportTrainResult result = new RiskReportTrainResult();
            result.setName(textParser(e.getName(), 15));
            result.setDate(DateUtils.formatDate(e.getTrainStartDate(), "yyyy???MM???dd???") +"-"+
                    DateUtils.formatDate(e.getTrainEndDate(), "yyyy???MM???dd???"));
            return result;
        }).collect(Collectors.toList()));

        //????????????
        map.put("socialContactTotalScore", nullToZero(socialContact.getIndexNum()));
        map.put("socialContactCount", nullToZero(socialContact.getNum()));
        map.put("socialContactContinueText", nullToZero(socialContact.getNum()) == 0 ? ""
                : nullToZero(socialContact.getNum())+"???/"+dateUnit);

        //????????????
        map.put("familyCommentTotalScore", nullToZero(familyEvaluation.getIndexNum()));
        map.put("familyComment", "??????????????????");

        map.put("lawEnforcementTypeList", lawEnforcementTypeList);
        map.put("conductVisitTypeList", conductVisitTypeList);
        map.put("conductBureauRuleTypeList", conductBureauRuleTypeList);
        map.put("dutyTypeList", dutyTypeList);

        //????????????
        map.put("healthTotalScore", nullToZero(health.getIndexNum()));
        //??????
        map.put("weightScore", nullToZero(riskHealth.getOverweightNum()));
        map.put("height", nullToZero(healthRecordInfo.getHeight())+"cm");
        map.put("weight", nullToZero(healthRecordInfo.getWeight()) + "kg");
        map.put("bmiValue", nullToZero(riskHealth.getBmi()));
        map.put("weightText", riskHealth.getBmiName() == null ? "" : riskHealth.getBmiName());
        map.put("weightStyle", "background-color: "+ colorMap.get(riskHealth.getBmiName()) +";");
        //?????????
        map.put("hyperlipidemiaScore", nullToZero(riskHealth.getHyperlipidemiaNum()));
        map.put("highDensityLipoprotein", nullToZero(healthRecordInfo.getHighDensityLipoprotein()));
        map.put("hyperlipidemiaText", nullToZero(riskHealth.getHyperlipidemiaNum()) <= 0 ? "" :
                healthReportDO.getHyperlipidemia() > 1 ? "??????"+healthReportDO.getHyperlipidemia()+"???" : "????????????");
        //?????????
        map.put("hypertensionScore", nullToZero(riskHealth.getHypertensionNum()));
        map.put("receiveCompression", nullToZero(healthRecordInfo.getReceiveCompression()));
        map.put("diastolicPressure", nullToZero(healthRecordInfo.getDiastolicPressure()));
        map.put("hypertensionText", nullToZero(riskHealth.getHypertensionNum()) <= 0 ? "" :
                healthReportDO.getHypertension() > 1 ? "??????"+healthReportDO.getHypertension()+"???" : "????????????");
        //?????????
        map.put("hyperglycemiaScore", nullToZero(riskHealth.getHyperglycemiaNum()));
        map.put("hyperglycemia", nullToZero(healthRecordInfo.getBloodSugar()));
        map.put("hyperglycemiaText", nullToZero(riskHealth.getHyperglycemiaNum()) <= 0 ? "" :
                healthReportDO.getHyperglycemia() > 1 ? "??????"+healthReportDO.getHyperglycemia()+"???" : "????????????");
        //????????????
        map.put("hyperuricemiaScore", nullToZero(riskHealth.getHyperuricemiaNum()));
        map.put("hyperuricemia", nullToZero(healthRecordInfo.getSerumUricAcid()));
        map.put("hyperuricemiaText", nullToZero(riskHealth.getHyperuricemiaNum()) <= 0 ? "" :
                healthReportDO.getHyperuricemia() > 1 ? "??????"+healthReportDO.getHyperuricemia()+"???" : "????????????");

        map.put("prostateNum", nullToZero(riskHealth.getProstateNum()));
        map.put("majorDiseasesNum", nullToZero(riskHealth.getMajorDiseasesNum()));
        map.put("heartNum", nullToZero(riskHealth.getHeartNum()));
        map.put("tumorAntigenNum", nullToZero(riskHealth.getTumorAntigenNum()));
        map.put("orthopaedicsNum", nullToZero(riskHealth.getOrthopaedicsNum()));

        map.put("prostateDesc", textParser(healthRecordInfo.getProstateDesc(), 36));
        map.put("majorDiseasesDesc", textParser(healthRecordInfo.getMajorDiseasesDesc(), 36));
        map.put("heartDesc", textParser(healthRecordInfo.getHeartDesc(), 36));
        map.put("tumorAntigenDesc", textParser(healthRecordInfo.getTumorAntigenDesc(), 36));
        map.put("orthopaedicsDesc", textParser(healthRecordInfo.getOrthopaedicsDesc(), 36));

        String summary = "???????????????";
        if (nullToZero(bureauRuleReportDO.getTotalCount()) > 0 || nullToZero(visitReportDO.getTotalCount()) > 0) {
            if (nullToZero(bureauRuleReportDO.getTotalCount()) > 0) {
                summary += "???????????????????????????"+bureauRuleReportDO.getTotalCount()+"???";
            }
            if (nullToZero(visitReportDO.getTotalCount()) > 0) {
                summary += "?????????????????????"+visitReportDO.getTotalCount()+"???";
            }
        } else {
            summary += "???????????????";
        }

        summary += "??????????????????";
        if (nullToZero(riskCase.getAbilityNum()) > 0 || nullToZero(lawEnforcementReportDO.getTotalCount()) > 0
                || unQualifiedCount > 0) {
            if (nullToZero(riskCase.getAbilityNum()) > 0) {
                summary += "?????????????????????????????????"+riskCase.getAbilityNum()+"???";
            }
            if (nullToZero(lawEnforcementReportDO.getTotalCount()) > 0) {
                summary += "?????????????????????????????????"+lawEnforcementReportDO.getTotalCount()+"???";
            }
            if (unQualifiedCount > 0) {
                summary += "??????????????????????????????"+unQualifiedCount+"???";
            }
        } else {
            summary += "???????????????";
        }

        summary += "??????????????????";
        if (nullToZero(duty.getIndexNum()) > 0) {
            summary += "????????????"+duty.getDeductionScoreCount()+"?????????????????????"+duty.getIndexNum()+"???";
        } else {
            summary += "???????????????";
        }

        summary += "??????????????????";
        if (nullToZero(train.getIndexNum()) > 0) {
            summary += "???????????????"+train.getIndexNum()+"???????????????????????????????????????"+nullToZero(train.getPhysicalScore())+"??????" +
                    "???????????????????????????"+nullToZero(train.getFirearmScore())+"???";
        } else {
            summary += "???????????????";
        }

        summary += "??????????????????";
        if ( nullToZero(socialContact.getNum()) > 0) {
            summary += "???????????????????????????"+socialContact.getNum()+"???";
        } else {
            summary += "???????????????";
        }

        //TODO ???????????????????????????????????????????????????????????????????????????????????????
//        if (nullToZero(familyEvaluation.getIndexNum()) > 0) {
//            summary += "???????????????";
//            if ()
//        }
        int healthCount = healthCount(healthRecord);
        summary += "????????????????????????";
        if (healthCount > 0) {
            summary += "??????"+healthCount+"?????????????????????";
        } else {
            summary += "???????????????";
        }

        if (StrUtil.isNotBlank(riskHealth.getBmiName())) {
            summary += "?????????"+riskHealth.getBmiName();
            if (!"??????".equals(riskHealth.getBmiName())) {
                summary += "???????????????????????????";
            }
        }
        summary += "???";

        map.put("summary", textParser(summary, 48));
//        map.put("desc", textParser(desc, 48));
        map.put("createDate", DateUtils.formatDate(new Date(), "yyyy???MM???dd???"));

        String fileName = policeId+"_"+dateTime+"_riskReport_"+System.currentTimeMillis();

        return new ResponseEntity<>(DataListReturn.ok(createPDF(fileName, map)), HttpStatus.OK);
    }

    private String createPDF(String fileName, Map<String, Object> data) throws Exception {
        String htmlString = getTemplateContent("/mnt/qiantang/", "risk_report_template_nth", data);
//        String htmlString = getTemplateContent("D:\\codes\\work_web\\web_\\map_point\\", "risk_report_template_nth", data);

        DocumentBuilder builder =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(htmlString.getBytes("UTF-8")));

        ITextRenderer renderer = new ITextRenderer();

        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("/usr/share/font/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//        fontResolver.addFont("C:\\Windows\\Fonts\\simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        OutputStream out = new FileOutputStream(new File("/mnt/qiantang/policeRiskReport/"+fileName+".pdf"));

        renderer.setDocument(doc, null);
        renderer.layout();
        renderer.createPDF(out);
        out.flush();
        out.close();

        return HttpIPUtil.HOST + "/static/policeRiskReport/"+fileName+".pdf";
    }

    public String getTemplateContent(String filePath, String template, Map<String, Object> variables) throws Exception {
        //?????????????????????
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setPrefix(filePath);
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode("XHTML");
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
        templateResolver.setCacheable(true);

        //???????????????????????????????????????
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(templateResolver);
        engine.isInitialized();

        //?????????
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);

        //???????????????
        Context ctx = new Context();
        ctx.setVariables(variables);
        engine.process(template,  ctx, writer);

        stringWriter.flush();
        stringWriter.close();
        writer.flush();
        writer.close();

        //??????html
        return stringWriter.toString();
    }

    private String textParser(String str, int fontLength) {
        String result = "";
        if (str == null || str.length() < fontLength) {
            return str;
        }

        for (int i=0; i<Math.ceil((double)str.length() / fontLength); i++) {
            int lastIndex = fontLength * (i + 1);
            if (i+1 == Math.ceil((double)str.length() / fontLength)) {
                lastIndex = str.length();
            }

            result += str.substring((fontLength * i), lastIndex) + "\n";
        }

        return result;
    }

    private int healthCount(RiskHealthRecord healthRecord) {
        int count = 0;
        if (nullToZero(healthRecord.getIsOverweight()) == 1) {
            count++;
        }
        if (nullToZero(healthRecord.getIsHeart()) == 1) {
            count++;
        }
        if (nullToZero(healthRecord.getIsHyperglycemia()) == 1) {
            count++;
        }
        if (nullToZero(healthRecord.getIsHyperlipidemia()) == 1) {
            count++;
        }
        if (nullToZero(healthRecord.getIsHypertension()) == 1) {
            count++;
        }
        if (nullToZero(healthRecord.getIsHyperuricemia()) == 1) {
            count++;
        }
        if (nullToZero(healthRecord.getIsMajorDiseases()) == 1) {
            count++;
        }
        if (nullToZero(healthRecord.getIsOrthopaedics()) == 1) {
            count++;
        }
        if (nullToZero(healthRecord.getIsProstate()) == 1) {
            count++;
        }
        if (nullToZero(healthRecord.getIsTumorAntigen()) == 1) {
            count++;
        }
        return count;
    }

    /**
     * ??????????????? 0
     * @param v
     * @return
     */
    private Double nullToZero(Double v) {
        return v == null ? 0 : v;
    }

    private Integer nullToZero(Integer v) {
        return v == null ? 0 : v;
    }

    public static void main(String[] args) throws Exception {
        RiskReportController controller = new RiskReportController();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dateType", "???");
//        map.put("policeImg", controller.HOST+"/static/police/1000966e8cb38-a554-48a9-9ef8-b638ce982132.png");
        map.put("policeImg", HttpIPUtil.HOST+"/static/police/1000966e8cb38-a554-48a9-9ef8-b638ce982132.png");
        map.put("totalScore", "???????????????12.23");
        map.put("policeName", "?????????");
        map.put("policeId", "052415");
        map.put("deptName", "?????????????????????");
        map.put("positionName", "?????????");
        map.put("thisMonthScore", "12.23");
        map.put("lastMonthScore", "12.33");

        map.put("belongingDate", "???????????????2020???7???~2021???7???");

        //????????????
        map.put("conductTotalScore", "8.90");
        //????????????
        map.put("bureauRuleTotalScore", "4.50");
        map.put("bureauRuleTotalCount", "3");
        map.put("maxBureauRuleDeductionScore", "2.25");
        map.put("minBureauRuleDeductionScore", "1.25");
        map.put("bureauRuleDeductionTypeName", "????????????");
        //????????????
        map.put("visitTotalScore", "4.40");
        map.put("visitTotalCount", "4");
        map.put("visitTypeName", "12345????????????????????????");
        map.put("maxVisitDeductionScore", "8.90");
        //????????????
        map.put("trafficViolationTotalScore", "8.80");
        map.put("trafficViolationTotalCount", "3");
        map.put("maxTrafficViolationScore", "8.00");
        map.put("trafficViolationContinueText", "??????3???");

        //????????????
        map.put("caseTotalScore", "12.54");
        //????????????
        map.put("caseAbilityTotalScore", "11.90");
        map.put("reconsiderationLitigationTotalScore", "3.95");
        map.put("letterVisitTotalScore", "6.21");
        map.put("lawEnforcementFaultTotalScore", "1.64");
        map.put("judicialSupervisionTotalScore", "3.60");
        //????????????
        map.put("lawEnforcementTotalScore", "8.90");
        map.put("lawEnforcementCount", "4");
        map.put("lawEnforcementMaxScore", "6.70");
        map.put("lawEnforcementMinScore", "2.22");
        map.put("lawEnforcementTypeName", "????????????");
        //????????????
        map.put("caseTestTotalScore", "18.93");
        map.put("caseTestCount", "5");
        map.put("caseTestQualifiedCount", "3");
        map.put("caseTestUnQualifiedCount", "2");

        //????????????
        map.put("dutyTotalScore", "11.55");
        map.put("dutyTotalCount", "4");
        map.put("dutyMaxScore", "5.5");
        map.put("dutyMinScore", "6.05");

        //????????????
        map.put("skillTotalScore", "2");
        //????????????
        map.put("physicalAchievementTotalScore", "8.90");
        map.put("physicalAchievementCount", "5");
        map.put("physicalAchievementQualifiedCount", "4");
        map.put("physicalAchievementUnTestCount", "2");
        map.put("physicalAchievementDrawCount", "3");
        //????????????
        map.put("firearmTotalScore", "0.8");
        map.put("firearmCount", "5");
        map.put("firearmQualifiedCount", "4");

        //????????????
        map.put("socialContactTotalScore", "6.78");
        map.put("socialContactCount", "2");
        map.put("socialContactContinueText", "3???/???");

        //????????????
        map.put("familyCommentTotalScore", "0");
        map.put("familyComment", "A");

        map.put("lawEnforcementTypeList", Arrays.asList(new RiskReportTypeStatisticsDO("??????1", 3), new RiskReportTypeStatisticsDO("??????2", 2)));
        map.put("conductVisitTypeList", Arrays.asList(new RiskReportTypeStatisticsDO("??????3", 3), new RiskReportTypeStatisticsDO("??????4", 2)));
        map.put("conductBureauRuleTypeList", Arrays.asList(new RiskReportTypeStatisticsDO(), null));
        map.put("dutyTypeList", Arrays.asList(new RiskReportTypeStatisticsDO("??????7", 3), new RiskReportTypeStatisticsDO("??????8", 2)));

        //????????????
        map.put("healthTotalScore", "1.25");
        //??????
        map.put("weightScore", "0.25");
        map.put("height", "169cm");
        map.put("weight", "49kg");
        map.put("bmiValue", "32.1");
        //?????????#C72928????????????#F59A23????????????#42B72A
        map.put("weightStyle", "background-color: #F59A23;");
        map.put("weightText", "??????");
        //?????????
        map.put("hyperlipidemiaScore", "0.25");
        map.put("highDensityLipoprotein", "44.19");
        map.put("hyperlipidemiaText", "????????????");
        //?????????
        map.put("hypertensionScore", "0.25");
        map.put("receiveCompression", "143");
        map.put("diastolicPressure", "84");
        map.put("hypertensionText", "??????2???");
        //?????????
        map.put("hyperglycemiaScore", "0");
        map.put("hyperglycemia", "");
        map.put("hyperglycemiaText", "");
        //????????????
        map.put("hyperuricemiaScore", "0.25");
        map.put("hyperuricemia", "371.9");
        map.put("hyperuricemiaText", "????????????");

        map.put("prostateDesc", controller.textParser("", 36));
        map.put("majorDiseasesDesc", controller.textParser("", 36));
        map.put("heartDesc", controller.textParser("1232????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????" +
                "????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????", 36));
        map.put("tumorAntigenDesc", controller.textParser("1232????????????????????????????????????????????????????????????????????????????????????????????????????????????" +
                "????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????", 36));
        map.put("orthopaedicsDesc", controller.textParser("1232????????????????????????????????????????????????????????????????????????????????????????????????????????????" +
                "????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????", 36));

        map.put("summary", controller.textParser("???????????????????????????????????????????????????????????????????????????3??????????????????????????????????????????????????????5????????????????????????????????????5??????" +
                "??????????????????10??????????????????????????????????????????40%???????????????????????????50%?????????????????????????????????????????????3????????????????????????" +
                "????????????????????????????????????????????????3??????????????????????????????????????????????????????????????????", 48));
        map.put("createDate", DateUtils.formatDate(new Date(), "yyyy???MM???dd???"));

        String htmlString = controller.getTemplateContent("D:\\codes\\work_web\\web_\\map_point\\", "risk_report_template_nth", map);

        DocumentBuilder builder =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(htmlString.getBytes("UTF-8")));

        ITextRenderer renderer = new ITextRenderer();

        ITextFontResolver fontResolver = renderer.getFontResolver();

        fontResolver.addFont("C:\\Windows\\Fonts\\simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        OutputStream out = new FileOutputStream(new File("C:\\Users\\lw\\Desktop\\sss.pdf"));

        renderer.setDocument(doc, null);
        renderer.layout();
        renderer.createPDF(out);
        out.close();
    }

}
