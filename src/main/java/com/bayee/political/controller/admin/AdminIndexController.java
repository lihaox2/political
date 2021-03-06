package com.bayee.political.controller.admin;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.hutool.core.util.StrUtil;
import com.bayee.political.json.*;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bayee.political.domain.ScreenChart;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.service.RiskCaseLawEnforcementRecordService;
import com.bayee.political.service.RiskConductBureauRuleRecordService;
import com.bayee.political.service.RiskConductVisitRecordService;
import com.bayee.political.service.RiskDutyDealPoliceRecordService;
import com.bayee.political.service.RiskHealthRecordService;
import com.bayee.political.service.RiskTrendsService;
import com.bayee.political.service.TrainFirearmService;
import com.bayee.political.service.TrainPhysicalService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;

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
    
    @Autowired
	private RiskTrendsService riskTrendsService;
    
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
	
	DecimalFormat df = new DecimalFormat(".00");

    /**
     * ??????
     * @return
     */
    @GetMapping("/head/data")
    public ResponseEntity<?> headData() {
        IndexHeadDataResult result = new IndexHeadDataResult();
        result.setPoliceNum(userService.countAllPolice());
        result.setCaseManageNum(riskCaseLawEnforcementRecordService.countAll());
        result.setDutyNum(riskDutyDealPoliceRecordService.countAll());
        result.setHealthNum(riskHealthRecordService.countAll());
//        result.setConductNum(riskConductVisitRecordService.countAll() + riskConductBureauRuleRecordService.countAll());
        result.setConductNum(riskConductBureauRuleRecordService.countAll());
        result.setTrainNum(trainFirearmService.countAll() + trainPhysicalService.countAll());

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * ????????????
     * @return
     */
    @GetMapping("/risk/alarm/chart")
    public ResponseEntity<?> riskAlarmChart() {
    	
    	Date currdate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		String month = sd.format(calendar.getTime());
        IndexRiskAlarmChartResult result = new IndexRiskAlarmChartResult();
        
        List<Map<String,Object>>  riskTrends=riskTrendsService.selectRiskTrends();
        
        Integer selectIndexRiskTotal=riskTrendsService.selectIndexRiskTotal();
        
        List<ScreenChart> chartList=new ArrayList<ScreenChart>();
        for(Map<String,Object> m:riskTrends) {
        	ScreenChart screenChart=new ScreenChart();
        	screenChart.setName(m.get("month").toString());
        	screenChart.setValue(Integer.valueOf(m.get("value").toString()));
        	chartList.add(screenChart);
        }
        
        //????????????
  		Integer policeTotal=userService.policeForceOnlineCount();
  		
  		Integer thMonthTotal=riskTrendsService.selectTheMonthRiskTotal(month);
        
        result.setAlarmNum(thMonthTotal);
        result.setGlobalRatio(Double.valueOf(thMonthTotal)/Double.valueOf(policeTotal)*100);
        result.setThisMonthNewRatio(Double.valueOf(thMonthTotal)/Double.valueOf(policeTotal)*100);
        result.setChartList(chartList);

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * ??????????????????
     * @param date ?????? yyyy-MM
     * @return
     */
    @GetMapping("/risk/dept/alarm/chart")
    public ResponseEntity<?> riskDeptAlarmChart(@RequestParam("date") String date) {
        if (StrUtil.isBlank(date)) {
            return new ResponseEntity<>(DataListReturn.error("???????????????"), HttpStatus.OK);
        }

        return new ResponseEntity<>(DataListReturn.ok(riskTrendsService.riskDeptAlarmChart(date)), HttpStatus.OK);
    }

    /**
     * ????????????
     * @return
     */
    @GetMapping("/case/chart")
    public ResponseEntity<?> caseChart() {
        IndexCaseChartResult result = new IndexCaseChartResult();
        
        List<Map<String,Object>>  riskTrends=riskTrendsService.caseLawTrends();
        
        List<ScreenChart> chartList=new ArrayList<ScreenChart>();
        for(Map<String,Object> m:riskTrends) {
        	ScreenChart screenChart=new ScreenChart();
        	screenChart.setName(m.get("name").toString());
        	screenChart.setValue(Integer.valueOf(m.get("value").toString()));
        	chartList.add(screenChart);
        }
        //????????????
  		Integer policeTotal=userService.policeForceOnlineCount();
        //????????????
        Integer caseLawPepolNum= riskTrendsService.caseLawPepolNum();
        
        Integer caseLawThisMonthNum=riskTrendsService.caseLawThisMonthNum();
        
        result.setExistsErrorPeopleNum(caseLawThisMonthNum);
        result.setThisMonthNewRatio(Double.valueOf(caseLawThisMonthNum)/Double.valueOf(policeTotal)*100);
        result.setReplaceErrorNum(riskTrendsService.caseLawRepeatNum());
        result.setChartList(chartList);

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * ??????????????????
     * @param date yyyy-MM-dd
     * @return
     */
    @GetMapping("/case/dept/chart")
    public ResponseEntity<?> caseDeptChart(@RequestParam("date") String date) {
        if (StrUtil.isBlank(date)) {
            return new ResponseEntity<>(DataListReturn.error("???????????????"), HttpStatus.OK);
        }

        return new ResponseEntity<>(DataListReturn.ok(riskTrendsService.caseDeptChart(date)), HttpStatus.OK);
    }

    /**
     * ????????????
     * @return
     */
    @GetMapping("/duty/chart")
    public ResponseEntity<?> dutyChart() {
    	
    	IndexCaseChartResult result = new IndexCaseChartResult();
        
        List<Map<String,Object>>  riskTrends=riskTrendsService.dutyDealTrends();
        
        List<ScreenChart> chartList=new ArrayList<ScreenChart>();
        for(Map<String,Object> m:riskTrends) {
        	ScreenChart screenChart=new ScreenChart();
        	screenChart.setName(m.get("name").toString());
        	screenChart.setValue(Integer.valueOf(m.get("value").toString()));
        	chartList.add(screenChart);
        }
        //????????????
  		Integer policeTotal=userService.policeForceOnlineCount();
        //????????????
        Integer dutyDealPepolNum= riskTrendsService.dutyDealPepolNum();
        
        Integer dutyDealThisMonthNum=riskTrendsService.dutyDealThisMonthNum();
        
        result.setExistsErrorPeopleNum(dutyDealThisMonthNum);
        result.setThisMonthNewRatio(Double.valueOf(dutyDealThisMonthNum)/Double.valueOf(policeTotal)*100);
        result.setReplaceErrorNum(riskTrendsService.dutyDealRepeatNum());
        result.setChartList(chartList);

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * ??????????????????
     * @param date - yyyy-MM-dd
     * @return
     */
    @GetMapping("/duty/dept/chart")
    public ResponseEntity<?> dutyDeptChart(@RequestParam("date") String date) {

        return new ResponseEntity<>(DataListReturn.ok(riskTrendsService.dutyDeptChart(date)), HttpStatus.OK);
    }

    /**
     * ????????????
     * @return
     */
    @GetMapping("/train/chart")
    public ResponseEntity<?> trainChart()  {
        IndexTrainChartResult result = new IndexTrainChartResult();
        String date = DateUtils.formatDate(new Date(), "yyyy-MM-dd");

        result.setTrainCount(trainFirearmService.countByDate(date) + trainPhysicalService.countByDate(date));
        result.setEligibleCount(riskTrendsService.qualifiedNum());
        result.setEligibleRatio(riskTrendsService.qualifiedRate());
        
        List<Map<String,Object>>  riskTrends=riskTrendsService.qualifiedRateEcharts();
        
        List<TrainChartResult> chartList=new ArrayList<TrainChartResult>();
        for(Map<String,Object> m:riskTrends) {
            TrainChartResult chartResult=new TrainChartResult();
            chartResult.setId(Integer.parseInt(m.get("id").toString()));
            chartResult.setType(Integer.parseInt(m.get("type").toString()));
            chartResult.setName(m.get("name").toString());
            chartResult.setValue(Double.valueOf(m.get("rate").toString()));
        	chartList.add(chartResult);
        }

        result.setChartList(chartList);

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * ??????????????????
     * @param type ?????? 1.???????????????2.????????????
     * @param id
     * @return
     */
    @GetMapping("/train/dept/chart")
    public ResponseEntity<?> trainDeptChart(@RequestParam("type") Integer type, @RequestParam("id") Integer id) {

        if (type == null || type == 1) {
            return new ResponseEntity<>(DataListReturn.ok(riskTrendsService.physicalTrainDeptChart(id)), HttpStatus.OK);
        }

        if (type == 2) {
            return new ResponseEntity<>(DataListReturn.ok(riskTrendsService.firearmTrainDeptChart(id)), HttpStatus.OK);
        }
        return new ResponseEntity<>(DataListReturn.error("???????????????"), HttpStatus.OK);
    }

    /**
     * ????????????
     * @return
     */
    @GetMapping("/conduct/chart")
    public ResponseEntity<?> conductChart() {
        List<ScreenChart> chart = riskConductBureauRuleRecordService.getConductBureauChart();
        Integer totalSize = 0;
        if (chart != null && chart.size() > 0) {
            for (ScreenChart c : chart) {
                totalSize += c.getValue();
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("totalSize", totalSize);
        result.put("chart", chart);

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * ????????????
     * @return
     */
    @GetMapping("/health/alarm/chart")
    public ResponseEntity<?> healthAlarmChart() {
        IndexHealthAlarmChartResult result = new IndexHealthAlarmChartResult();
        
        List<IndexHealthChart> chartList =new ArrayList<IndexHealthChart>();
        
        IndexHealthChart indexHealthChart=new IndexHealthChart();
        result.setHealthTotalCount(riskTrendsService.inspectNums());
        result.setHealthyCount(riskTrendsService.healthNum());
//        result.setAlarmCount(riskTrendsService.healthRiskNum());
        result.setAlarmCount(result.getHealthTotalCount()-result.getHealthyCount());
        Map<String,Object> ishealth=riskTrendsService.ishealth();
        
        Map<String,Object> nohealth=riskTrendsService.nohealth();
        
        indexHealthChart.setHealthyCount(Integer.valueOf(ishealth.get("noOverweight").toString()));
        indexHealthChart.setAlarmCount(Integer.valueOf(nohealth.get("isOverweight").toString()));
        indexHealthChart.setName("??????");
        indexHealthChart.setKey("is_overweight");
        chartList.add(indexHealthChart);
        
        IndexHealthChart indexHealthChart2=new IndexHealthChart();
        indexHealthChart2.setHealthyCount(Integer.valueOf(ishealth.get("noHyperlipidemia").toString()));
        indexHealthChart2.setAlarmCount(Integer.valueOf(nohealth.get("isHyperlipidemia").toString()));
        indexHealthChart2.setName("?????????");
        indexHealthChart2.setKey("is_hyperlipidemia");
        chartList.add(indexHealthChart2);
        
        IndexHealthChart indexHealthChart3=new IndexHealthChart();
        indexHealthChart3.setHealthyCount(Integer.valueOf(ishealth.get("noHypertension").toString()));
        indexHealthChart3.setAlarmCount(Integer.valueOf(nohealth.get("isHypertension").toString()));
        indexHealthChart3.setName("?????????");
        indexHealthChart3.setKey("is_hypertension");
        chartList.add(indexHealthChart3);
        
        IndexHealthChart indexHealthChart4=new IndexHealthChart();
        indexHealthChart4.setHealthyCount(Integer.valueOf(ishealth.get("noHyperglycemia").toString()));
        indexHealthChart4.setAlarmCount(Integer.valueOf(nohealth.get("isHyperglycemia").toString()));
        indexHealthChart4.setName("?????????");
        indexHealthChart4.setKey("is_hyperglycemia");
        chartList.add(indexHealthChart4);
        
        IndexHealthChart indexHealthChart5=new IndexHealthChart();
        indexHealthChart5.setHealthyCount(Integer.valueOf(ishealth.get("noHyperuricemia").toString()));
        indexHealthChart5.setAlarmCount(Integer.valueOf(nohealth.get("isHyperuricemia").toString()));
        indexHealthChart5.setName("????????????");
        indexHealthChart5.setKey("is_hyperuricemia");
        chartList.add(indexHealthChart5);
        
        IndexHealthChart indexHealthChart6=new IndexHealthChart();
        indexHealthChart6.setHealthyCount(Integer.valueOf(ishealth.get("noProstate").toString()));
        indexHealthChart6.setAlarmCount(Integer.valueOf(nohealth.get("isProstate").toString()));
        indexHealthChart6.setName("?????????????????????");
        indexHealthChart6.setKey("is_prostate");
        chartList.add(indexHealthChart6);
        
        IndexHealthChart indexHealthChart7=new IndexHealthChart();
        indexHealthChart7.setHealthyCount(Integer.valueOf(ishealth.get("noMajorDiseases").toString()));
        indexHealthChart7.setAlarmCount(Integer.valueOf(nohealth.get("isMajorDiseases").toString()));
        indexHealthChart7.setName("????????????");
        indexHealthChart7.setKey("is_major_diseases");
        chartList.add(indexHealthChart7);
        
        IndexHealthChart indexHealthChart8=new IndexHealthChart();
        indexHealthChart8.setHealthyCount(Integer.valueOf(ishealth.get("onHeart").toString()));
        indexHealthChart8.setAlarmCount(Integer.valueOf(nohealth.get("isHeart").toString()));
        indexHealthChart8.setName("??????????????????");
        indexHealthChart8.setKey("is_heart");
        chartList.add(indexHealthChart8);
        
        IndexHealthChart indexHealthChart9=new IndexHealthChart();
        indexHealthChart9.setHealthyCount(Integer.valueOf(ishealth.get("onTumorAntigen").toString()));
        indexHealthChart9.setAlarmCount(Integer.valueOf(nohealth.get("isTumorAntigen").toString()));
        indexHealthChart9.setName("????????????????????????");
        indexHealthChart9.setKey("is_tumor_antigen");
        chartList.add(indexHealthChart9);
        
        IndexHealthChart indexHealthChart10=new IndexHealthChart();
        indexHealthChart10.setHealthyCount(Integer.valueOf(ishealth.get("onOrthopaedics").toString()));
        indexHealthChart10.setAlarmCount(Integer.valueOf(nohealth.get("isOrthopaedics").toString()));
        indexHealthChart10.setName("??????????????????");
        indexHealthChart10.setKey("is_orthopaedics");
        chartList.add(indexHealthChart10);
        
        result.setChartList(chartList);

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * ??????????????????
     * @return
     */
    @GetMapping("/health/alarm/dept/chart")
    public ResponseEntity<?> healthAlarmDeptChart(@RequestParam("key") String key) {
        if (StrUtil.isBlank(key)) {
            return new ResponseEntity<>(DataListReturn.error("key ?????????"), HttpStatus.OK);
        }
        return new ResponseEntity<>(DataListReturn.ok(riskTrendsService.healthAlarmDeptChart(key)), HttpStatus.OK);
    }

}
