package com.bayee.political.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bayee.political.domain.RiskAlarm;
import com.bayee.political.domain.RiskHealth;
import com.bayee.political.domain.RiskIndexMonitorChild;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.json.RiskProportionResult;
import com.bayee.political.json.RiskTrendResult;
import com.bayee.political.service.RiskService;
import com.bayee.political.service.RiskTrendsService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import com.bayee.political.utils.StatusCode;
import com.taobao.api.ApiException;

@Controller
public class CockpitController {
	
	@Autowired
	RiskController RiskController;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RiskTrendsService riskTrendsService;
	
	@Autowired
	private RiskService riskService;
	
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
	
	DecimalFormat df = new DecimalFormat(".00");
	
	
	@GetMapping("/cockpit/risk/trend")
	public ResponseEntity<?> riskTrends() throws ParseException{
		
		Date currdate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		String endTime = sd.format(calendar.getTime());
		
		String startTime= DateUtils.lastMonthTime();
		//String startTime= sd.format(new Date().getTime());
		//警员总数
		Integer policeTotal=userService.policeForceOnlineCount();
		
		Integer risktotal=riskTrendsService.selectRiskTotal(startTime+"-01", endTime+"-30");
		
		List<Map<String,Object>>  riskTrends=riskTrendsService.selectRiskTrends();
		
		RiskTrendResult riskTrendResult=new RiskTrendResult();
		
		riskTrendResult.setPoliceTotal(policeTotal);
		riskTrendResult.setRisktotal(risktotal);
		riskTrendResult.setProportion(Double.valueOf(risktotal)/Double.valueOf(policeTotal)*100);
		riskTrendResult.setRiskTrends(riskTrends);
		
		
		return new ResponseEntity<>(riskTrendResult, HttpStatus.OK);
	}
	
	@GetMapping("/cockpit/dept/top/five")
	public ResponseEntity<?> deptTopFive() throws ParseException{
		
		Date currdate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		String endTime = sd.format(calendar.getTime());
		
		String startTime= DateUtils.lastMonthTime();
		//String startTime= sd.format(new Date().getTime());
		
		List<Map<String,Object>>  deptTopFive=riskTrendsService.selectDeptTopFive(startTime+"-01", endTime+"-30");
		
		return new ResponseEntity<>(deptTopFive, HttpStatus.OK);
	}
	
	
	@GetMapping("/cockpit/police/risk/top/ten")
	public ResponseEntity<?> policeRiskTopTen(
			@RequestParam(value = "sortType", required = false) Integer sortType) throws ParseException{
		
		String sortName = null;
		String orderName = null;
		if (sortType == null || sortType == 11001) {
			sortName = "a.total_num";
			orderName = "total_num";
		} else if (sortType == 11002) {
			sortName = "a.conduct_num";
			orderName = "conduct_num";
		} else if (sortType == 11003) {
			sortName = "a.handling_case_num";
			orderName = "handling_case_num";
		} else if (sortType == 11004) {
			sortName = "a.duty_num";
			orderName = "duty_num";
		} else if (sortType == 11005) {
			sortName = "a.train_num";
			orderName = "train_num";
		} else if (sortType == 11006) {
			sortName = "a.social_contact_num";
			orderName = "social_contact_num";
		} else if (sortType == 11007) {
			sortName = "a.amily_evaluation_num";
			orderName = "amily_evaluation_num";
		} else if (sortType == 11008) {
			sortName = "a.health_num";
			orderName = "health_num";
		}
		Date currdate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		String dateTime = sd.format(calendar.getTime());
		String lastMonthTime = DateUtils.lastMonthTime();
		
		List<RiskReportRecord> list=riskTrendsService.selectPoliceRiskTopTen(sortName, dateTime, lastMonthTime, orderName);
		
		for (int i = 0; i < list.size(); i++) {
			// 警员健康风险指数查询
			RiskHealth item = riskService.riskHealthIndexNewestItem(list.get(i).getPoliceId());
			if (item != null && item.getIndexNum() != null) {
				list.get(i).setHealthNum(item.getIndexNum());
			} else {
				list.get(i).setHealthNum(0.0);
			}
			Double totalNum = list.get(i).getConductNum() + list.get(i).getHandlingCaseNum() + list.get(i).getDutyNum()
					+ list.get(i).getTrainNum() + list.get(i).getSocialContactNum()
					+ list.get(i).getAmilyEvaluationNum() + list.get(i).getDrinkNum() + list.get(i).getStudyNum()
					+ list.get(i).getWorkNum() + list.get(i).getHealthNum();
			list.get(i).setTotalNum(Double.valueOf(df.format(totalNum)));
		}
		if(sortType==null || sortType == 11001) {
			list=list.stream().sorted(Comparator.comparing(RiskReportRecord::getTotalNum).reversed()).collect(Collectors.toList());
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("/cockpit/police")
	public ResponseEntity<?> police(String content){
		
		List<Map<String,Object>> police=riskTrendsService.selectPolice(content);
		
		return new ResponseEntity<>(police, HttpStatus.OK);
	}
	
	
	@GetMapping("/cockpit/risk/proportion")
	public ResponseEntity<?> riskProportion() throws ParseException{
		Date currdate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		String dateTime = sd.format(calendar.getTime());
		String lastMonthTime = DateUtils.lastMonthTime();
		
		List<RiskProportionResult> list=new ArrayList<RiskProportionResult>();
		
		Integer comprehensiveIndex=riskTrendsService.comprehensiveIndex(dateTime+"-30", lastMonthTime);
		RiskProportionResult item1=new RiskProportionResult();
		item1.setName("综合");
		item1.setValue(comprehensiveIndex);
		
		
		Integer drinkIndex=riskTrendsService.drinkIndex(dateTime+"-30", lastMonthTime);
		
		RiskProportionResult item2=new RiskProportionResult();
		item2.setName("行为规范");
		item2.setValue(drinkIndex);
		
		Integer conductIndex=riskTrendsService.conductIndex(dateTime+"-30", lastMonthTime);
		
		RiskProportionResult item3=new RiskProportionResult();
		item3.setName("执法办案");
		item3.setValue(conductIndex);
		
		Integer caseIndex=riskTrendsService.caseIndex(dateTime+"-30", lastMonthTime);
		
		RiskProportionResult item4=new RiskProportionResult();
		item4.setName("接警执勤风险");
		item4.setValue(caseIndex);
		
		Integer dutyIndex=riskTrendsService.dutyIndex(dateTime+"-30", lastMonthTime);
		
		RiskProportionResult item5=new RiskProportionResult();
		item5.setName("警务技能风险");
		item5.setValue(dutyIndex);
		
		Integer trainIndex=riskTrendsService.trainIndex(dateTime, lastMonthTime);
		
		RiskProportionResult item6=new RiskProportionResult();
		item6.setName("社交风险");
		item6.setValue(trainIndex);
		
		Integer studyIndex=riskTrendsService.studyIndex(dateTime+"-30", lastMonthTime);
		RiskProportionResult item7=new RiskProportionResult();
		item7.setName("家属评价风险 ");
		item7.setValue(studyIndex);
		
		Integer healthIndex=riskTrendsService.healthIndex(dateTime+"-30", lastMonthTime);
		
		RiskProportionResult item8=new RiskProportionResult();
		item8.setName("健康");
		item8.setValue(healthIndex);
		
		list.add(item1);
		list.add(item2);
		list.add(item3);
		list.add(item4);
		list.add(item5);
		list.add(item6);
		list.add(item7);
		list.add(item8);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	
	// 警员风险指标监测
	@RequestMapping(value = "/cockpit/monitor/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskIndexMonitorItem(@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "num1", required = false) Double num1,
			@RequestParam(value = "num2", required = false) Double num2,
			@RequestParam(value = "num3", required = false) Double num3,
			@RequestParam(value = "num4", required = false) Double num4,
			@RequestParam(value = "num5", required = false) Double num5,
			@RequestParam(value = "num6", required = false) Double num6,
			@RequestParam(value = "num7", required = false) Double num7,
			@RequestParam(value = "num8", required = false) Double num8)
			throws ApiException, ParseException {

//		int userNum=userService.countTotal();
//		
//		DataListReturn dlr = new DataListReturn();
//		if (dateTime == null) {
//			dateTime = sd.format(new Date());
//		}
//		String year = dateTime.substring(0, 4);
//		List<RiskIndexMonitorChild> list = new ArrayList<RiskIndexMonitorChild>();
//		RiskIndexMonitorChild item0 = new RiskIndexMonitorChild();
//		item0.setIndexPoliceNum(0);
//		item0.setAlarmPoliceRate(0);
//		item0.setAlarmPoliceNum(0);
//		item0.setTalkPoliceNum(0);
//		item0.setIsDisplay(0);
//		// 综合指数风险
//		RiskIndexMonitorChild item1 = riskService.comprehensiveIndex(dateTime,num1==null?null:(int)(userNum*num1));
//		if (item1 != null) {
//			item1.setId(11001);
//			item1.setName("综合指数风险");
//			if(num1!=null) {
//				item1.setAlarmPoliceNum((int) (userNum*num1));
//			}
//			list.add(item1);
//		} else {
//			item0.setId(11001);
//			item0.setName("综合指数风险");
//			list.add(item0);
//		}
//		// 行为规范风险
//		RiskIndexMonitorChild item2 = riskService.drinkIndex(dateTime,num2==null?null:(int) (userNum*num2));
//		if (item2 != null) {
//			item2.setId(11002);
//			item2.setName("行为规范风险");
//			if(num2!=null) {
//				item2.setAlarmPoliceNum((int) (userNum*num2));
//			}
//			list.add(item2);
//		} else {
//			item0.setId(11002);
//			item0.setName("行为规范风险");
//			list.add(item0);
//		}
//		// 纪律作风风险
//		RiskIndexMonitorChild item3 = riskService.conductIndex(dateTime,num3==null?null:(int) (userNum*num3));
//		if (item3 != null) {
//			item3.setId(11003);
//			item3.setName("纪律作风风险");
//			if(num3!=null) {
//				item3.setAlarmPoliceNum((int) (userNum*num3));
//			}
//			list.add(item3);
//		} else {
//			item0.setId(11003);
//			item0.setName("纪律作风风险");
//			list.add(item0);
//		}
//		// 执法办案风险
//		RiskIndexMonitorChild item4 = riskService.caseIndex(dateTime,num4==null?null:(int) (userNum*num4));
//		if (item4 != null) {
//			item4.setId(11004);
//			item4.setName("执法办案风险");
//			if(num4!=null) {
//				item4.setAlarmPoliceNum((int) (userNum*num4));
//			}
//			list.add(item4);
//		} else {
//			item0.setId(11004);
//			item0.setName("执法办案风险");
//			list.add(item0);
//		}
//		// 接警执勤风险
//		RiskIndexMonitorChild item5 = riskService.dutyIndex(dateTime,num5==null?null:(int) (userNum*num5));
//		if (item5 != null) {
//			item5.setId(11005);
//			item5.setName("接警执勤风险");
//			if(num5!=null) {
//				item5.setAlarmPoliceNum((int) (userNum*num5));
//			}
//			list.add(item5);
//		} else {
//			item0.setId(11005);
//			item0.setName("接警执勤风险");
//			list.add(item0);
//		}
//		// 警务技能风险
//		RiskIndexMonitorChild item6 = riskService.trainIndex(dateTime,num6==null?null:(int) (userNum*num6));
//		if (item6 != null) {
//			item6.setId(11006);
//			item6.setName("警务技能风险");
//			if(num6!=null) {
//				item6.setAlarmPoliceNum((int) (userNum*num6));
//			}
//			list.add(item6);
//		} else {
//			item0.setId(11006);
//			item0.setName("警务技能风险");
//			list.add(item0);
//		}
//		// 学习培训风险
//		RiskIndexMonitorChild item7 = riskService.studyIndex(dateTime,num7==null?null:(int) (userNum*num7));
//		if (item7 != null) {
//			item7.setId(11007);
//			item7.setName("学习培训风险");
//			if(num7!=null) {
//				item7.setAlarmPoliceNum((int) (userNum*num7));
//			}
//			list.add(item7);
//		} else {
//			item0.setId(11007);
//			item0.setName("学习培训风险");
//			list.add(item0);
//		}
//		// 健康风险
//		RiskIndexMonitorChild item8 = riskService.healthIndex(year, dateTime,num8==null?null:(int) (userNum*num8));
//		if (item8 != null) {
//			item8.setId(11008);
//			item8.setName("健康风险");
//			if(num8!=null) {
//				item8.setAlarmPoliceNum((int) (userNum*num8));
//			}
//			list.add(item8);
//		} else {
//			item0.setId(11008);
//			item0.setName("健康风险");
//			list.add(item0);
//		}
//		dlr.setStatus(true);
//		dlr.setMessage("success");
//		dlr.setResult(list);
//		dlr.setCode(StatusCode.getSuccesscode());
		
		Date currdate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		String lastMonthTime = DateUtils.lastMonthTime();
		
		List<RiskProportionResult> list=new ArrayList<RiskProportionResult>();
		
		Integer comprehensiveIndex=riskTrendsService.comprehensiveIndex(dateTime+"-30", lastMonthTime);
		RiskProportionResult item1=new RiskProportionResult();
		item1.setName("综合");
		item1.setValue(comprehensiveIndex);
		
		
		Integer drinkIndex=riskTrendsService.drinkIndex(dateTime+"-30", lastMonthTime);
		
		RiskProportionResult item2=new RiskProportionResult();
		item2.setName("行为规范");
		item2.setValue(drinkIndex);
		
		Integer conductIndex=riskTrendsService.conductIndex(dateTime+"-30", lastMonthTime);
		
		RiskProportionResult item3=new RiskProportionResult();
		item3.setName("执法办案");
		item3.setValue(conductIndex);
		
		Integer caseIndex=riskTrendsService.caseIndex(dateTime+"-30", lastMonthTime);
		
		RiskProportionResult item4=new RiskProportionResult();
		item4.setName("接警执勤风险");
		item4.setValue(caseIndex);
		
		Integer dutyIndex=riskTrendsService.dutyIndex(dateTime+"-30", lastMonthTime);
		
		RiskProportionResult item5=new RiskProportionResult();
		item5.setName("警务技能风险");
		item5.setValue(dutyIndex);
		
		Integer trainIndex=riskTrendsService.trainIndex(dateTime, lastMonthTime);
		
		RiskProportionResult item6=new RiskProportionResult();
		item6.setName("社交风险");
		item6.setValue(trainIndex);
		
		Integer studyIndex=riskTrendsService.studyIndex(dateTime+"-30", lastMonthTime);
		RiskProportionResult item7=new RiskProportionResult();
		item7.setName("家属评价风险 ");
		item7.setValue(studyIndex);
		
		Integer healthIndex=riskTrendsService.healthIndex(dateTime+"-30", lastMonthTime);
		
		RiskProportionResult item8=new RiskProportionResult();
		item8.setName("健康");
		item8.setValue(healthIndex);
		
		Integer total=comprehensiveIndex+drinkIndex+conductIndex+caseIndex+dutyIndex+trainIndex+studyIndex+healthIndex;
		int userNum=userService.countTotal();
		System.out.println(total);
		item1.setAlarmPoliceRate(Double.valueOf(df.format(((double)comprehensiveIndex/userNum)*100)));
		item2.setAlarmPoliceRate(Double.valueOf(df.format(((double)drinkIndex/userNum)*100)));
		item3.setAlarmPoliceRate(Double.valueOf(df.format(((double)conductIndex/userNum)*100)));
		item4.setAlarmPoliceRate(Double.valueOf(df.format(((double)caseIndex/userNum)*100)));
		item5.setAlarmPoliceRate(Double.valueOf(df.format(((double)dutyIndex/userNum)*100)));
		item6.setAlarmPoliceRate(Double.valueOf(df.format(((double)trainIndex/userNum)*100)));
		item7.setAlarmPoliceRate(Double.valueOf(df.format(((double)studyIndex/userNum)*100)));
		item8.setAlarmPoliceRate(Double.valueOf(df.format(((double)healthIndex/userNum)*100)));
		
		list.add(item1);
		list.add(item2);
		list.add(item3);
		list.add(item4);
		list.add(item5);
		list.add(item6);
		list.add(item7);
		list.add(item8);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	} 	
	
	/**
	 * 各项风险指标平均分数和人数趋势
	 * @param alamType
	 * @return
	 */
	@GetMapping("/cockpit/risk/avg")
	public ResponseEntity<?> riskAvg(Integer alamType){
		
		String sortName = null;
		if (alamType == null || alamType == 11001) {
			sortName = "a.total_num";
		} else if (alamType == 11002) {
			sortName = "a.conduct_num";
		} else if (alamType == 11003) {
			sortName = "a.handling_case_num";
		} else if (alamType == 11004) {
			sortName = "a.duty_num";
		} else if (alamType == 11005) {
			sortName = "a.train_num";
		} else if (alamType == 11006) {
			sortName = "a.social_contact_num";
		} else if (alamType == 11007) {
			sortName = "a.amily_evaluation_num";
		} else if (alamType == 11008) {
			sortName = "a.health_num";
		}
		
		List<Map<String,Object>> avgList=riskTrendsService.avgNum(sortName);
		
		List<Map<String,Object>> riskPersonnelTrend=riskTrendsService.riskPersonnelTrend(alamType);
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("avgList", avgList);
		map.put("riskPersonnelTrend", riskPersonnelTrend);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	
	@GetMapping("/cockpit/risk/alam")
	public ResponseEntity<?> riskAlam(Integer alamType){
		
		Map<String,Object> map=riskTrendsService.theMonthAlamTotal();
		
		List<RiskAlarm> list=riskTrendsService.theMonthAlarm();
		
		Map<String,Object> lastMap=new HashMap<String, Object>();
		
		for(RiskAlarm r:list) {
			if(r.getNum()>1) {
				
				List<Map<String,Object>> policeIdAlarm= riskTrendsService.findByPoliceIdRiskAlarm(r.getPoliceId());
				
				for(Map<String,Object> m: policeIdAlarm) {
					System.out.println(r.getPoliceId());
					String typeName=riskTrendsService.findAlarmType((Integer)m.get("alarmType"));
					Map<String,Object> typeMap=new HashMap<String, Object>();
					typeMap.put("typeName", typeName);
					typeMap.put("alarmScore", m.get("alarmScore"));
					if(r.getTypeList()==null) {
						r.setTypeList(new ArrayList<Map<String,Object>>());
					}
					r.getTypeList().add(typeMap);
				}
				
			}else {
				String typeName=riskTrendsService.findAlarmType(r.getAlarmType());
				Map<String,Object> typeMap=new HashMap<String, Object>();
				typeMap.put("typeName", typeName);
				typeMap.put("alarmScore", r.getAlarmScore());
				if(r.getTypeList()==null) {
					r.setTypeList(new ArrayList<Map<String,Object>>());
				}
				r.getTypeList().add(typeMap);
			}
		}
		lastMap.put("theMonthAlarm", list);
		lastMap.put("theMonthAlamTotal", map);
		
		
		return new ResponseEntity<>(lastMap, HttpStatus.OK);
	}
	
	
	@GetMapping("/cockpit/risk/continuity/alarm")
	public ResponseEntity<?> continuityAlarm(){
		
		List<Map<String,Object>> continuityAlarm=riskTrendsService.continuityAlarm();
		
		
		return new ResponseEntity<>(continuityAlarm, HttpStatus.OK);
	}
	
	@GetMapping("/cockpit/risk/continuity/alarm/details")
	public ResponseEntity<?> continuityAlarmDetails(String policeId){
		
		List<Map<String,Object>> continuityAlarmDetails=riskTrendsService.continuityAlarmDetails(policeId);
		
		
		return new ResponseEntity<>(continuityAlarmDetails, HttpStatus.OK);
	}
}