package com.bayee.political.controller.admin;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bayee.political.domain.ScreenChart;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.json.IndexCaseChartResult;
import com.bayee.political.json.IndexHeadDataResult;
import com.bayee.political.json.IndexHealthAlarmChartResult;
import com.bayee.political.json.IndexHealthChart;
import com.bayee.political.json.IndexRiskAlarmChartResult;
import com.bayee.political.json.IndexTrainChartResult;
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
        
        //警员总数
  		Integer policeTotal=userService.policeForceOnlineCount();
  		
  		Integer thMonthTotal=riskTrendsService.selectTheMonthRiskTotal(month);
        
        result.setAlarmNum(selectIndexRiskTotal);
        result.setGlobalRatio(Double.valueOf(selectIndexRiskTotal)/Double.valueOf(policeTotal)*100);
        result.setThisMonthNewRatio(Double.valueOf(thMonthTotal)/Double.valueOf(policeTotal)*100);
        result.setChartList(chartList);

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 执法办案
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
        //警员总数
  		Integer policeTotal=userService.policeForceOnlineCount();
        //问题人数
        Integer caseLawPepolNum= riskTrendsService.caseLawPepolNum();
        
        Integer caseLawThisMonthNum=riskTrendsService.caseLawThisMonthNum();
        
        result.setExistsErrorPeopleNum(caseLawPepolNum);
        result.setThisMonthNewRatio(Double.valueOf(caseLawThisMonthNum)/Double.valueOf(policeTotal)*100);
        result.setReplaceErrorNum(riskTrendsService.caseLawRepeatNum());
        result.setChartList(chartList);

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 接警执勤
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
        //警员总数
  		Integer policeTotal=userService.policeForceOnlineCount();
        //问题人数
        Integer dutyDealPepolNum= riskTrendsService.caseLawPepolNum();
        
        Integer dutyDealThisMonthNum=riskTrendsService.caseLawThisMonthNum();
        
        result.setExistsErrorPeopleNum(dutyDealPepolNum);
        result.setThisMonthNewRatio(Double.valueOf(dutyDealThisMonthNum)/Double.valueOf(policeTotal)*100);
        result.setReplaceErrorNum(riskTrendsService.caseLawRepeatNum());
        result.setChartList(chartList);

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 警务技能
     * @return
     */
    @GetMapping("/train/chart")
    public ResponseEntity<?> trainChart() {
        IndexTrainChartResult result = new IndexTrainChartResult();
        result.setTrainCount(trainFirearmService.countAll() + trainPhysicalService.countAll());
        result.setEligibleCount(riskTrendsService.qualifiedNum());
        result.setEligibleRatio(riskTrendsService.qualifiedRate());
        
        List<Map<String,Object>>  riskTrends=riskTrendsService.qualifiedRateEcharts();
        
        List<ScreenDoubeChart> chartList=new ArrayList<ScreenDoubeChart>();
        for(Map<String,Object> m:riskTrends) {
        	ScreenDoubeChart screenDoubeChart=new ScreenDoubeChart();
        	screenDoubeChart.setName(m.get("name").toString());
        	screenDoubeChart.setValue(Double.valueOf(m.get("rate").toString()));
        	chartList.add(screenDoubeChart);
        }
        
        
        
        result.setChartList(chartList);

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 行为规范
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
     * 健康风险
     * @return
     */
    @GetMapping("/health/alarm/chart")
    public ResponseEntity<?> healthAlarmChart() {
        IndexHealthAlarmChartResult result = new IndexHealthAlarmChartResult();
        
        List<IndexHealthChart> chartList =new ArrayList<IndexHealthChart>();
        
        IndexHealthChart indexHealthChart=new IndexHealthChart();
        result.setHealthTotalCount(riskTrendsService.inspectNums());
        result.setHealthyCount(riskTrendsService.healthNum());
        result.setAlarmCount(riskTrendsService.healthRiskNum());
        
        Map<String,Object> ishealth=riskTrendsService.ishealth();
        
        Map<String,Object> nohealth=riskTrendsService.nohealth();
        
        indexHealthChart.setHealthyCount(Integer.valueOf(ishealth.get("noOverweight").toString()));
        indexHealthChart.setAlarmCount(Integer.valueOf(nohealth.get("isOverweight").toString()));
        indexHealthChart.setName("体重");
        chartList.add(indexHealthChart);
        
        IndexHealthChart indexHealthChart2=new IndexHealthChart();
        indexHealthChart2.setHealthyCount(Integer.valueOf(ishealth.get("noHyperlipidemia").toString()));
        indexHealthChart2.setAlarmCount(Integer.valueOf(nohealth.get("isHyperlipidemia").toString()));
        indexHealthChart2.setName("高血脂");
        chartList.add(indexHealthChart2);
        
        IndexHealthChart indexHealthChart3=new IndexHealthChart();
        indexHealthChart3.setHealthyCount(Integer.valueOf(ishealth.get("noHypertension").toString()));
        indexHealthChart3.setAlarmCount(Integer.valueOf(nohealth.get("isHypertension").toString()));
        indexHealthChart3.setName("高血压");
        chartList.add(indexHealthChart3);
        
        IndexHealthChart indexHealthChart4=new IndexHealthChart();
        indexHealthChart4.setHealthyCount(Integer.valueOf(ishealth.get("noHyperglycemia").toString()));
        indexHealthChart4.setAlarmCount(Integer.valueOf(nohealth.get("isHyperglycemia").toString()));
        indexHealthChart4.setName("高血糖");
        chartList.add(indexHealthChart4);
        
        IndexHealthChart indexHealthChart5=new IndexHealthChart();
        indexHealthChart5.setHealthyCount(Integer.valueOf(ishealth.get("noHyperuricemia").toString()));
        indexHealthChart5.setAlarmCount(Integer.valueOf(nohealth.get("isHyperuricemia").toString()));
        indexHealthChart5.setName("高血尿酸");
        chartList.add(indexHealthChart5);
        
        IndexHealthChart indexHealthChart6=new IndexHealthChart();
        indexHealthChart6.setHealthyCount(Integer.valueOf(ishealth.get("noProstate").toString()));
        indexHealthChart6.setAlarmCount(Integer.valueOf(nohealth.get("isProstate").toString()));
        indexHealthChart6.setName("前列腺指标异常");
        chartList.add(indexHealthChart6);
        
        IndexHealthChart indexHealthChart7=new IndexHealthChart();
        indexHealthChart7.setHealthyCount(Integer.valueOf(ishealth.get("noMajorDiseases").toString()));
        indexHealthChart7.setAlarmCount(Integer.valueOf(nohealth.get("isMajorDiseases").toString()));
        indexHealthChart7.setName("重大疾病");
        chartList.add(indexHealthChart7);
        
        IndexHealthChart indexHealthChart8=new IndexHealthChart();
        indexHealthChart8.setHealthyCount(Integer.valueOf(ishealth.get("onHeart").toString()));
        indexHealthChart8.setAlarmCount(Integer.valueOf(nohealth.get("isHeart").toString()));
        indexHealthChart8.setName("心脏指标异常");
        chartList.add(indexHealthChart8);
        
        IndexHealthChart indexHealthChart9=new IndexHealthChart();
        indexHealthChart9.setHealthyCount(Integer.valueOf(ishealth.get("onTumorAntigen").toString()));
        indexHealthChart9.setAlarmCount(Integer.valueOf(nohealth.get("isTumorAntigen").toString()));
        indexHealthChart9.setName("肿瘤抗原指标异常");
        chartList.add(indexHealthChart9);
        
        IndexHealthChart indexHealthChart10=new IndexHealthChart();
        indexHealthChart10.setHealthyCount(Integer.valueOf(ishealth.get("onOrthopaedics").toString()));
        indexHealthChart10.setAlarmCount(Integer.valueOf(nohealth.get("isOrthopaedics").toString()));
        indexHealthChart10.setName("骨科指标异常");
        chartList.add(indexHealthChart10);
        
        result.setChartList(chartList);

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

}
