package com.bayee.political.controller.admin;

import com.bayee.political.domain.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xxl
 * @date 2021/5/18
 */
@Controller
public class ReportController {

    @Autowired
    UserService userService;

    @Autowired
    DepartmentService departmentService;

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

    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");

    final static String HOST = "http://8.136.146.186:8099";
//    final static String HOST = "http://41.190.128.250:8080";

//    @GetMapping("/create/policeRisk/report")
    public ResponseEntity<?> createPoliceRiskReport(@RequestParam("policeId") String policeId,
                                                    @RequestParam("timeType") Integer timeType,
                                                    @RequestParam("dateTime") String dateTime) throws ParseException {
        String lastMonthTime = DateUtils.lastMonthTime();
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
        if (dateTime == null) {
            dateTime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        }else {
            date = dateTime+"-01";
            year = dateTime.substring(0, 4);
        }
        Date currDate = sd.parse(dateTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currDate);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        String lastDateTime = sd.format(calendar.getTime());

        User user = userService.findByPoliceId(policeId);

        // 警员局规计分风险查询
        RiskConduct conduct = riskConductService.riskConductItem(policeId, dateTime, lastMonthTime, timeType);
        if (conduct == null) {
            conduct = new RiskConduct();
            conduct.setIndexNum(0d);
        }

        RiskDuty duty = riskService.riskDutyIndexItem(policeId, dateTime, lastMonthTime, timeType);
        if (duty == null) {
            duty = new RiskDuty();
            duty.setIndexNum(0d);
        }

        RiskSocialContact socialContact = riskService.riskSocialContactIndexItem(policeId, dateTime, lastMonthTime, timeType);
        if (socialContact == null) {
            socialContact = new RiskSocialContact();
            socialContact.setIndexNum(0d);
        }

        RiskFamilyEvaluation familyEvaluation = riskFamilyEvaluationService.riskFamilyEvaluationItem(policeId, dateTime);
        if (familyEvaluation == null) {
            familyEvaluation = new RiskFamilyEvaluation();
            familyEvaluation.setIndexNum(0d);
        }

        RiskCase riskCase = riskService.riskCaseIndexItem(policeId, dateTime, lastMonthTime, timeType);
        if (riskCase == null) {
            riskCase = new RiskCase();
            riskCase.setIndexNum(0d);
        }

        RiskHealth health = riskService.riskHealthIndexItem(policeId, year);
        if (health == null) {
            health = new RiskHealth();
            health.setIndexNum(0d);
        }

        RiskTrain train = riskService.riskTrainIndexItem(policeId, dateTime, lastMonthTime, timeType);
        if (train == null) {
            train = new RiskTrain();
            train.setIndexNum(0d);
        }

        RiskReportRecord reportRecord = riskService.riskReportRecordItem(null, policeId, dateTime, lastDateTime, lastMonthTime, timeType);
        if (reportRecord == null) {
            reportRecord = new RiskReportRecord();
        }
        /*Double physicalScore = riskSkillService.getPolicePhysicalDeductionScore(user.getPoliceId(), date);
        Double firearmScore = riskSkillService.getPoliceFirearmDeductionScore(user.getPoliceId(), date);*/

        try {
            Double totalNum = conduct.getIndexNum() + riskCase.getIndexNum() + duty.getIndexNum() +
                    train.getIndexNum() + socialContact.getIndexNum() + familyEvaluation.getIndexNum() + health.getIndexNum();

            Map<String,String> map = new HashMap<>();
            String dateUnit = "月";
            String inputDate = DateUtils.formatDate(reportRecord.getCreationDate(), "yyyy年MM月");
            if (timeType == 1) {
                dateUnit = "年";
                inputDate = DateUtils.formatDate(DateUtils.parseDate(lastMonthTime, "yyyy-MM"), "yyyy年MM月")
                        + "~" + DateUtils.formatDate(DateUtils.parseDate(dateTime, "yyyy-MM"), "yyyy年MM月");
            }
            map.put("dateUnit", dateUnit);
            map.put("inputDate", inputDate);

            map.put("policeName", user.getName());
            map.put("deptName", user.getDepartmentName());
            map.put("position", user.getPositionName());
            map.put("policeId", user.getPoliceId());
            map.put("totalScore", totalNum.toString());
            map.put("upperMonthScore", reportRecord.getLastTotalNum()+"");
            map.put("thisMonthScore", reportRecord.getCurrentTotalNum()+"");

            map.put("conductScore", conduct.getIndexNum() == null ? "0" : conduct.getIndexNum().toString());
            map.put("caseScore", riskCase.getIndexNum() == null ? "0" : riskCase.getIndexNum().toString());
            map.put("dutyScore", duty.getIndexNum() == null ? "0" : duty.getIndexNum().toString());
            map.put("skillScore", train.getIndexNum() == null ? "0" : train.getIndexNum().toString());
            map.put("socialContactScore", socialContact.getIndexNum() == null ? "0" : socialContact.getIndexNum().toString());
            map.put("familyAssessScore", familyEvaluation.getIndexNum() == null ? "0" : familyEvaluation.getIndexNum().toString());
            map.put("healthScore", health.getIndexNum() == null ? "0" : health.getIndexNum().toString());

            map.put("reportCreateDate", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
            map.put("bureauRuleScore",  conduct.getBureauRuleScore() == null ? "0" : conduct.getBureauRuleScore().toString());
            map.put("visitScore", conduct.getVisitScore() == null ? "0" : conduct.getVisitScore().toString());
            map.put("trafficViolationScore", conduct.getTrafficViolationScore() == null ? "0" : conduct.getTrafficViolationScore().toString());
            map.put("caseAbilityScore", riskCase.getAbilityNum() == null ? "0" : riskCase.getAbilityNum().toString());
            map.put("caseManageScore", riskCase.getLawEnforcementNum() == null ? "0" : riskCase.getLawEnforcementNum().toString());
            map.put("caseTestScore", riskCase.getTestNum() == null ? "0" : riskCase.getTestNum().toString());
            map.put("comprehensiveTrainScore", train.getPhysicalScore() == null ? "0" : train.getPhysicalScore().toString());
            map.put("firingTrainScore", train.getFirearmScore() == null ? "0" : train.getFirearmScore().toString());
            map.put("overweightFlag", health.getBmiName());
            map.put("hyperlipidemiaValue", health.getHyperlipidemiaNum() == null ? "0" : health.getHyperlipidemiaNum().toString());
            map.put("hypertensionValue", health.getHypertensionNum() == null ? "0" : health.getHypertensionNum().toString());
            map.put("hyperglycemiaValue", health.getHyperglycemiaNum() == null ? "0" : health.getHyperglycemiaNum().toString());
            map.put("hyperuricemiaValue", health.getHyperuricemiaNum() == null ? "0" : health.getHyperuricemiaNum().toString());
            map.put("prostateFlag", health.getProstateNum() == null ? "0" : health.getProstateNum().toString());
            map.put("majorDiseasesFlag", health.getMajorDiseasesNum() == null ? "0" : health.getMajorDiseasesNum().toString());
            map.put("heartFlag", health.getHeartNum() == null ? "0" : health.getHeartNum().toString());
            map.put("tumorAntigenFlag", health.getTumorAntigenNum() == null ? "0" : health.getTumorAntigenNum().toString());
            map.put("orthopaedicsFlag", health.getOrthopaedicsNum() == null ? "0" : health.getOrthopaedicsNum().toString());
            map.put("content","");

            Map<String,String> map2 = new HashMap();
            map2.put("headImg",HOST + "/static"+user.getHeadImage());

            Map<String, Object> data = new HashMap<>();
            data.put("datemap",map);
            data.put("imgmap",map2);

            String fileName = policeId+"_"+dateTime+"_riskReport_"+System.currentTimeMillis();
            return new ResponseEntity<>(DataListReturn.ok(createPoliceRiskReportDetails(data, fileName)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(DataListReturn.error("下载失败！"), HttpStatus.OK);
        }
//        return File(new FileStream(path, FileMode.Open), "text/plain", HttpUtility.UrlEncode("Site.txt"));
    }

    private String createPoliceRiskReportDetails(Map<String, Object> o, String fileName) {
        // 模板路径
        String templatePath = "/mnt/qiantang/police_risk_template_V1.pdf";
//        String templatePath = "C:\\Users\\颜世旺\\Desktop\\police_risk_template_V1.pdf";
        // 生成的新文件路径
        String newPDFPath = "/mnt/qiantang/policeRiskReport/"+fileName+".pdf";
//        String newPDFPath = "C:\\Users\\颜世旺\\Desktop\\"+fileName+".pdf";

//        String fontStylePath = "C:\\Windows\\Fonts\\simfang.ttf";
        String fontStylePath = "/usr/share/font/simfang.ttf";

        String returnPath = HOST + "/static/policeRiskReport/"+fileName+".pdf";
//        String returnPath = newPDFPath;

        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            BaseFont bf = BaseFont.createFont(fontStylePath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            // 输出流
            out = new FileOutputStream(newPDFPath);

            // 读取pdf模板
            reader = new PdfReader(templatePath);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            //文字类的内容处理
            Map<String,String> datemap = (Map<String,String>)o.get("datemap");
            form.addSubstitutionFont(bf);
            for(String key : datemap.keySet()){
                String value = datemap.get(key);
                form.setField(key, value);
            }
            //图片类的内容处理
            Map<String,String> imgmap = (Map<String,String>)o.get("imgmap");
            for(String key : imgmap.keySet()) {
                String value = imgmap.get(key);
                String imgpath = value;
                int pageNo = form.getFieldPositions(key).get(0).page;
                Rectangle signRect = form.getFieldPositions(key).get(0).position;
                float x = signRect.getLeft();
                float y = signRect.getBottom();
                //根据路径读取图片
                Image image = Image.getInstance(imgpath);
                //获取图片页面
                PdfContentByte under = stamper.getOverContent(pageNo);
                //图片大小自适应
                image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                //添加图片
                image.setAbsolutePosition(x, y);
                under.addImage(image);
            }
            // 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
            stamper.setFormFlattening(true);
            stamper.close();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();

        } catch (DocumentException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        return returnPath;
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap();
        map.put("dateUnit", "年");
        map.put("policeName", "吴静静");
        map.put("deptName", "南太湖新区分局");
        map.put("position", "局领导");
        map.put("totalScore", "46.75");
        map.put("policeId", "052415");
        map.put("upperMonthScore", "0");
        map.put("thisMonthScore", "46.75");
        map.put("conductScore", "14.5");
        map.put("caseScore", "20");
        map.put("dutyScore", "10");
        map.put("skillScore", "0");
        map.put("socialContactScore","0");
        map.put("familyAssessScore","0");
        map.put("healthScore","2.25");
        map.put("inputDate","2020年4月 ~ 2021年05月");
        map.put("reportCreateDate","2021年05月18日");
        map.put("bureauRuleScore","2.5");
        map.put("visitScore","12");
        map.put("trafficViolationScore","0");
        map.put("caseAbilityScore","55");
        map.put("caseManageScore","16.5");
        map.put("caseTestScore","2");
        map.put("comprehensiveTrainScore","0");
        map.put("firingTrainScore","0");
        map.put("overweightFlag", "0");
        map.put("hyperlipidemiaValue","0.75");
        map.put("hypertensionValue","0.75");
        map.put("hyperglycemiaValue","0.75");
        map.put("hyperuricemiaValue","0");
        map.put("prostateFlag","0");
        map.put("majorDiseasesFlag","0");
        map.put("heartFlag","0");
        map.put("tumorAntigenFlag","0");
        map.put("orthopaedicsFlag","0");
        map.put("content","1) 描述1\n2) 描述2");

        Map<String,String> map2 = new HashMap();
        map2.put("headImg",HOST+"/static/police/1000966e8cb38-a554-48a9-9ef8-b638ce982132.png");

        Map<String,Object> o=new HashMap();
        o.put("datemap",map);
        o.put("imgmap",map2);


        // 模板路径
        String templatePath = "C:\\Users\\Lenovo\\Desktop\\police_risk_template_V2.pdf";
        // 生成的新文件路径
        String newPDFPath = "C:\\Users\\Lenovo\\Desktop\\警员风险月度报告_test.pdf";

        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            BaseFont bf = BaseFont.createFont("c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//            Font FontChinese = new Font(bf, 5, Font.NORMAL);
            // 输出流
            out = new FileOutputStream(newPDFPath);

            // 读取pdf模板
            reader = new PdfReader(templatePath);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            //文字类的内容处理
            Map<String,String> datemap = (Map<String,String>)o.get("datemap");
            form.addSubstitutionFont(bf);
            for(String key : datemap.keySet()){
                String value = datemap.get(key);
                form.setField(key,value);

            }
            //图片类的内容处理
            Map<String,String> imgmap = (Map<String,String>)o.get("imgmap");
            for(String key : imgmap.keySet()) {
                String value = imgmap.get(key);
                String imgpath = value;
                int pageNo = form.getFieldPositions(key).get(0).page;
                Rectangle signRect = form.getFieldPositions(key).get(0).position;
                float x = signRect.getLeft();
                float y = signRect.getBottom();
                //根据路径读取图片
                Image image = Image.getInstance(imgpath);
                //获取图片页面
                PdfContentByte under = stamper.getOverContent(pageNo);
                //图片大小自适应
                image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                //添加图片
                image.setAbsolutePosition(x, y);
                under.addImage(image);
            }
            // 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
            stamper.setFormFlattening(true);
            stamper.close();
            Document doc = new Document();
            Font font = new Font(bf, 32);
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();

        } catch (IOException e) {
            System.out.println(e);
        } catch (DocumentException e) {
            System.out.println(e);
        }
    }

}
