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

import com.bayee.political.json.RiskTopAlamResult;
import io.swagger.models.auth.In;
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
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.json.RiskProportionResult;
import com.bayee.political.json.RiskTrendResult;
import com.bayee.political.service.RiskService;
import com.bayee.political.service.RiskTrendsService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
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
	public ResponseEntity<?> riskTrends() throws ParseException {
		
		Date currdate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		String endTime = sd.format(calendar.getTime());
		
		String startTime= DateUtils.lastMonthTime();
		//String startTime= sd.format(new Date().getTime());
		//????????????
		Integer policeTotal=userService.policeForceOnlineCount();
		//???????????????
		//?????????
//		Integer risktotal=riskTrendsService.selectRiskTotal(startTime, endTime);
		//????????????
		String year=sd.format(currdate.getTime());
		Integer risktotal=riskTrendsService.selectRiskTotals(year);
		
		List<Map<String,Object>>  riskTrends=riskTrendsService.selectRiskTrends();
		
		RiskTrendResult riskTrendResult=new RiskTrendResult();

		riskTrendResult.setAlarmPoliceCount(0);
		riskTrendResult.setPoliceTotal(policeTotal);
		riskTrendResult.setRisktotal(risktotal);
		riskTrendResult.setProportion(Double.valueOf(df.format(Double.valueOf(risktotal)/Double.valueOf(policeTotal)*100)));
		riskTrendResult.setRiskTrends(riskTrends);
		
		
		return new ResponseEntity<>(riskTrendResult, HttpStatus.OK);
	}
	
	@GetMapping("/cockpit/dept/top/five")
	public ResponseEntity<?> deptTopFive() throws ParseException{
		//?????????
//		Date currdate = new Date();
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(currdate);
//		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
//		String endTime = sd.format(calendar.getTime());
		
//		String startTime= DateUtils.lastMonthTime();
		//String startTime= sd.format(new Date().getTime());

		//????????????
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = sdf.format(cal.getTime());
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		String endTime = sdf.format(cal.getTime());

		List<Map<String,Object>>  deptTopFive=riskTrendsService.selectDeptTopFive(beginTime, endTime);
		
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
		} else if (sortType == 11010) {
			sortName = "a.relevant_num";
			orderName = "rr.index_num";
		}
		Date currdate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		String dateTime = sd.format(calendar.getTime());
		String lastMonthTime = DateUtils.lastMonthTime();
		
		List<RiskReportRecord> list=riskTrendsService.selectPoliceRiskTopTen(sortName, dateTime, lastMonthTime, orderName);
		
		for (int i = 0; i < list.size(); i++) {
			// ??????????????????????????????
			RiskHealth item = riskService.riskHealthIndexNewestItem(list.get(i).getPoliceId());
			if (item != null && item.getIndexNum() != null) {
				list.get(i).setHealthNum(item.getIndexNum());
			} else {
				list.get(i).setHealthNum(0.0);
			}
			/*Double totalNum = list.get(i).getConductNum() + list.get(i).getHandlingCaseNum() + list.get(i).getDutyNum()
					+ list.get(i).getTrainNum() + list.get(i).getSocialContactNum()
					+ list.get(i).getAmilyEvaluationNum() + list.get(i).getDrinkNum() + list.get(i).getStudyNum()
					+ list.get(i).getWorkNum() + list.get(i).getHealthNum();
			list.get(i).setTotalNum(Double.valueOf(df.format(totalNum)));*/
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

	public static void main(String[] str) throws ParseException {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = sdf.format(cal.getTime());
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		String endTime = sdf.format(cal.getTime());
		System.out.println(beginTime);
		System.out.println(endTime);
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
//
//		Integer comprehensiveIndex=riskTrendsService.comprehensiveIndex(dateTime+"-30", lastMonthTime);
//		RiskProportionResult item1=new RiskProportionResult();
//		item1.setName("??????");
//		item1.setValue(comprehensiveIndex);
//
//
//		Integer drinkIndex=riskTrendsService.drinkIndex(dateTime+"-30", lastMonthTime);
//
//		RiskProportionResult item2=new RiskProportionResult();
//		item2.setName("????????????");
//		item2.setValue(drinkIndex);
//
//		Integer conductIndex=riskTrendsService.conductIndex(dateTime+"-30", lastMonthTime);
//
//		RiskProportionResult item3=new RiskProportionResult();
//		item3.setName("????????????");
//		item3.setValue(conductIndex);
//
//		Integer caseIndex=riskTrendsService.caseIndex(dateTime+"-30", lastMonthTime);
//
//		RiskProportionResult item4=new RiskProportionResult();
//		item4.setName("???????????????");
//		item4.setValue(caseIndex);
//
//		Integer dutyIndex=riskTrendsService.dutyIndex(dateTime+"-30", lastMonthTime);
//
//		RiskProportionResult item5=new RiskProportionResult();
//		item5.setName("????????????");
//		item5.setValue(dutyIndex);
//
//		Integer trainIndex=riskTrendsService.trainIndex(dateTime, lastMonthTime);
//
//		RiskProportionResult item6=new RiskProportionResult();
//		item6.setName("????????????");
//		item6.setValue(trainIndex);
//
//		Integer studyIndex=riskTrendsService.studyIndex(dateTime+"-30", lastMonthTime);
//		RiskProportionResult item7=new RiskProportionResult();
//		item7.setName("???????????? ");
//		item7.setValue(studyIndex);
//
//		Integer healthIndex=riskTrendsService.healthIndex(dateTime+"-30", lastMonthTime);
//
//		RiskProportionResult item8=new RiskProportionResult();
//		item8.setName("??????");
//		item8.setValue(healthIndex);
		Integer comprehensiveIndex=riskTrendsService.VariousRisks(11001);
		RiskProportionResult item1=new RiskProportionResult();
		item1.setName("??????");
		item1.setValue(comprehensiveIndex);


		Integer drinkIndex=riskTrendsService.VariousRisks(11002);

		RiskProportionResult item2=new RiskProportionResult();
		item2.setName("????????????");
		item2.setValue(drinkIndex);

		Integer conductIndex=riskTrendsService.VariousRisks(11003);

		RiskProportionResult item3=new RiskProportionResult();
		item3.setName("????????????");
		item3.setValue(conductIndex);

		Integer caseIndex=riskTrendsService.VariousRisks(11004);

		RiskProportionResult item4=new RiskProportionResult();
		item4.setName("???????????????");
		item4.setValue(caseIndex);

		Integer dutyIndex=riskTrendsService.VariousRisks(11005);

		RiskProportionResult item5=new RiskProportionResult();
		item5.setName("????????????");
		item5.setValue(dutyIndex);

		Integer trainIndex=riskTrendsService.VariousRisks(11006);

		RiskProportionResult item6=new RiskProportionResult();
		item6.setName("????????????");
		item6.setValue(trainIndex);

		Integer studyIndex=riskTrendsService.VariousRisks(11007);
		RiskProportionResult item7=new RiskProportionResult();
		item7.setName("???????????? ");
		item7.setValue(studyIndex);

		Integer healthIndex=riskTrendsService.VariousRisks(11008);

		RiskProportionResult item8=new RiskProportionResult();
		item8.setName("??????");
		item8.setValue(healthIndex);

		Integer total=comprehensiveIndex+drinkIndex+conductIndex+caseIndex+dutyIndex+trainIndex+studyIndex+healthIndex;
		int userNum=userService.countTotal();
		System.out.println(total);
		item1.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)comprehensiveIndex/total)*100)));
		item2.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)drinkIndex/total)*100)));
		item3.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)conductIndex/total)*100)));
		item4.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)caseIndex/total)*100)));
		item5.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)dutyIndex/total)*100)));
		item6.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)trainIndex/total)*100)));
		item7.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)studyIndex/total)*100)));
		item8.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)healthIndex/total)*100)));

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
	
	
	
	// ????????????????????????
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

		Date currdate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		String lastMonthTime = DateUtils.lastMonthTime();
		
		List<RiskProportionResult> list=new ArrayList<RiskProportionResult>();
//
//		Integer comprehensiveIndex=riskTrendsService.comprehensiveIndex(dateTime+"-30", lastMonthTime);
//		RiskProportionResult item1=new RiskProportionResult();
//		item1.setName("??????");
//		item1.setValue(comprehensiveIndex);
//
//
//		Integer drinkIndex=riskTrendsService.drinkIndex(dateTime+"-30", lastMonthTime);
//
//		RiskProportionResult item2=new RiskProportionResult();
//		item2.setName("????????????");
//		item2.setValue(drinkIndex);
//
//		Integer conductIndex=riskTrendsService.conductIndex(dateTime+"-30", lastMonthTime);
//
//		RiskProportionResult item3=new RiskProportionResult();
//		item3.setName("????????????");
//		item3.setValue(conductIndex);
//
//		Integer caseIndex=riskTrendsService.caseIndex(dateTime+"-30", lastMonthTime);
//
//		RiskProportionResult item4=new RiskProportionResult();
//		item4.setName("???????????????");
//		item4.setValue(caseIndex);
//
//		Integer dutyIndex=riskTrendsService.dutyIndex(dateTime+"-30", lastMonthTime);
//
//		RiskProportionResult item5=new RiskProportionResult();
//		item5.setName("????????????");
//		item5.setValue(dutyIndex);
//
//		Integer trainIndex=riskTrendsService.trainIndex(dateTime, lastMonthTime);
//
//		RiskProportionResult item6=new RiskProportionResult();
//		item6.setName("????????????");
//		item6.setValue(trainIndex);
//
//		Integer studyIndex=riskTrendsService.studyIndex(dateTime+"-30", lastMonthTime);
//		RiskProportionResult item7=new RiskProportionResult();
//		item7.setName("???????????? ");
//		item7.setValue(studyIndex);
//
//		Integer healthIndex=riskTrendsService.healthIndex(dateTime+"-30", lastMonthTime);
//
//		RiskProportionResult item8=new RiskProportionResult();
//		item8.setName("??????");
//		item8.setValue(healthIndex);
//
//		Integer total=comprehensiveIndex+drinkIndex+conductIndex+caseIndex+dutyIndex+trainIndex+studyIndex+healthIndex;
//		int userNum=userService.countTotal();



//		Integer total=comprehensiveIndex+drinkIndex+conductIndex+caseIndex+dutyIndex+trainIndex+studyIndex+healthIndex;
//		int userNum=userService.countTotal();
//		System.out.println(total);
//		item1.setAlarmPoliceRate(Double.valueOf(df.format(((double)comprehensiveIndex/userNum)*100)));
//		item2.setAlarmPoliceRate(Double.valueOf(df.format(((double)drinkIndex/userNum)*100)));
//		item3.setAlarmPoliceRate(Double.valueOf(df.format(((double)conductIndex/userNum)*100)));
//		item4.setAlarmPoliceRate(Double.valueOf(df.format(((double)caseIndex/userNum)*100)));
//		item5.setAlarmPoliceRate(Double.valueOf(df.format(((double)dutyIndex/userNum)*100)));
//		item6.setAlarmPoliceRate(Double.valueOf(df.format(((double)trainIndex/userNum)*100)));
//		item7.setAlarmPoliceRate(Double.valueOf(df.format(((double)studyIndex/userNum)*100)));
//		item8.setAlarmPoliceRate(Double.valueOf(df.format(((double)healthIndex/userNum)*100)));

		Integer comprehensiveIndex=riskTrendsService.VariousRisks(11001);
		RiskProportionResult item1=new RiskProportionResult();
		item1.setName("??????");
		item1.setValue(comprehensiveIndex);


		Integer drinkIndex=riskTrendsService.VariousRisks(11002);

		RiskProportionResult item2=new RiskProportionResult();
		item2.setName("????????????");
		item2.setValue(drinkIndex);

		Integer conductIndex=riskTrendsService.VariousRisks(11003);

		RiskProportionResult item3=new RiskProportionResult();
		item3.setName("????????????");
		item3.setValue(conductIndex);

		Integer caseIndex=riskTrendsService.VariousRisks(11004);

		RiskProportionResult item4=new RiskProportionResult();
		item4.setName("???????????????");
		item4.setValue(caseIndex);

		Integer dutyIndex=riskTrendsService.VariousRisks(11005);

		RiskProportionResult item5=new RiskProportionResult();
		item5.setName("????????????");
		item5.setValue(dutyIndex);

		Integer trainIndex=riskTrendsService.VariousRisks(11006);

		RiskProportionResult item6=new RiskProportionResult();
		item6.setName("????????????");
		item6.setValue(trainIndex);

		Integer studyIndex=riskTrendsService.VariousRisks(11007);
		RiskProportionResult item7=new RiskProportionResult();
		item7.setName("???????????? ");
		item7.setValue(studyIndex);

		Integer healthIndex=riskTrendsService.VariousRisks(11008);

		RiskProportionResult item8=new RiskProportionResult();
		item8.setName("??????");
		item8.setValue(healthIndex);

		Integer total=comprehensiveIndex+drinkIndex+conductIndex+caseIndex+dutyIndex+trainIndex+studyIndex+healthIndex;
		int userNum=userService.countTotal();
		System.out.println(total);
		item1.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)comprehensiveIndex/total)*100)));
		item2.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)drinkIndex/total)*100)));
		item3.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)conductIndex/total)*100)));
		item4.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)caseIndex/total)*100)));
		item5.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)dutyIndex/total)*100)));
		item6.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)trainIndex/total)*100)));
		item7.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)studyIndex/total)*100)));
		item8.setAlarmPoliceRate(total <= 0 ? 0 : Double.valueOf(df.format(((double)healthIndex/total)*100)));
		
		list.add(item1);
		list.add(item2);
		list.add(item3);
		list.add(item4);
		list.add(item5);
		list.add(item6);
		list.add(item7);
		list.add(item8);

		// ????????????-???????????????0?????????????????????3%
		double radio = 0.03;
		List<RiskProportionResult> listResult = list.stream().map(e -> {
			if (e.getValue() <= 0) {
//				int num = (int)Math.ceil(userNum * radio);
				int num = (int)Math.ceil(total * radio);
				e.setValue(num);
//				e.setAlarmPoliceRate(Double.valueOf(df.format(((double)num/userNum)*100)));
				e.setAlarmPoliceRate(Double.valueOf(df.format(0.03*100)));

			}
			return e;
		}).collect(Collectors.toList());
		
		return new ResponseEntity<>(listResult, HttpStatus.OK);
	} 	
	
	/**
	 * ?????????????????????????????????????????????
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

	@GetMapping("/cockpit/risk/top/alam")
	public ResponseEntity <RiskTopAlamResult> riskTopAlam(){
		//???????????????????????????
		Integer toMonth=riskTrendsService.newTheMonthAlamTotal();
		//???????????????????????????
		Integer upMonth=riskTrendsService.comparedWithLastMonthAlamTotal();
		RiskTopAlamResult result = new RiskTopAlamResult();
		result.setToMonth(toMonth);
		result.setUpMonth(upMonth);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/cockpit/risk/alam")
	public ResponseEntity<?> riskAlam(@RequestParam("alamType") Integer alamType){
		
		Map<String,Object> map=riskTrendsService.theMonthAlamTotal();

		if (alamType == null) {
			alamType = 11001;
		}
		List<RiskAlarm> list = riskTrendsService.theMonthAlarm(alamType);
		
		/*for(RiskAlarm r:list) {
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
		}*/
		/*lastMap.put("theMonthAlarm", list);
		lastMap.put("theMonthAlamTotal", map);*/
		
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("/cockpit/risk/continuity/alarm")
	public ResponseEntity<?> continuityAlarm(){
//		List<Map<String,Object>> continuityAlarm=riskTrendsService.continuityAlarm();
		List<Map<String,Object>> continuityAlarm=riskTrendsService.newContinuityAlarmDateList();
		if(continuityAlarm!=null && continuityAlarm.size()>0){
			continuityAlarm.stream().forEach(e->{
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				cal.set(Calendar.DAY_OF_MONTH, 1);
				for(int i=2;i<12;i++){
					Integer count = riskTrendsService.selectPoliceIdCount(e.get("policeId").toString(), i);
					if(count==0){
						break;
					}else {
						e.put("value",Integer.valueOf(e.get("value").toString())+count);
					}
				}
			});
		}
		return new ResponseEntity<>(continuityAlarm, HttpStatus.OK);
	}
	
	@GetMapping("/cockpit/risk/continuity/alarm/details")
	public ResponseEntity<?> continuityAlarmDetails(String policeId){
		List<Map<String,Object>> continuityAlarmDetails=riskTrendsService.newContinuityAlarmDetails(policeId);
		List<Map<String,Object>> list=new ArrayList<>();
		if(continuityAlarmDetails!=null && continuityAlarmDetails.size()>0){
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH, 1);
			SimpleDateFormat sdf = new SimpleDateFormat("MM");
			String format = sdf.format(cal.getTime());
			Integer num=Integer.valueOf(format);
			Integer sum=num;
			for(int i=0;i<num;i++){
				Integer count = riskTrendsService.selectPoliceIdCount(policeId, i);
				if(count==0){
					break;
				}else {
					for( Map<String,Object> e:continuityAlarmDetails){
						Integer month = Integer.valueOf(e.get("month").toString());
						if(sum==month){
							list.add(e);
						}
					}
					sum-=1;
				}
			}

		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/cockpit/risk/type")
	public ResponseEntity<?> riskAlarmType() {
		return new ResponseEntity<>(DataListReturn.ok(riskTrendsService.getRiskAlarmType()),
				HttpStatus.OK);
	}

}
