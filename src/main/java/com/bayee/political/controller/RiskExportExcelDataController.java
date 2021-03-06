package com.bayee.political.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.bayee.political.domain.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bayee.political.mapper.RiskDrinkRecordMapper;

@Component
@EnableScheduling
@Controller
public class RiskExportExcelDataController {
	
	@Autowired
	RiskHealthRecordService riskHealthRecordService;
	
	@Autowired
	RiskService riskService;
	
	@Autowired
	RiskAlarmService riskAlarmService;
	
	@Autowired
	RiskStutyPalmSchoolRecordService riskStutyPalmSchoolRecordService;
	
	@Autowired
	RiskStutyUnitTrainRecordService riskStutyUnitTrainRecordService;
	
	@Autowired
	RiskStutyActivitiesPartyRecordService riskStutyActivitiesPartyRecordService;
	
	@Autowired
	RiskDutyDealPoliceRecordService riskDutyDealPoliceRecordService;
	
	@Autowired
	RiskCaseLawEnforcementRecordService riskCaseLawEnforcementRecordService;
	
	@Autowired
	RiskCaseTestRecordService riskCaseTestRecordService;
	
	@Autowired
	RiskDrinkRecordMapper riskDrinkRecordMapper;
	
	@Autowired
	RiskCaseAbilityRecordService riskCaseAbilityRecordService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RiskConductBureauRuleRecordService riskConductBureauRuleRecordService;
	
	@Autowired
	RiskConductVisitRecordService riskConductVisitRecordService;
	
	@Autowired
	RiskReportRecordService riskReportRecordService;
	
	@Autowired
    RiskHealthRecordInfoService riskHealthRecordInfoService;

	@Autowired
	TotalRiskDetailsService totalRiskDetailsService;

	@Autowired
	RiskCaseLawEnforcementTypeService riskCaseLawEnforcementTypeService;

	@Autowired
	RiskDutyInformationTypeService riskDutyInformationTypeService;

	@Autowired
	RiskDutyErrorTypeService riskDutyErrorTypeService;

	@Autowired
	RiskConductVisitOriginService riskConductVisitOriginService;

	@Autowired
	RiskConductVisitTypeService riskConductVisitTypeService;

	@Autowired
	RiskHonourService riskHonourService;

	@Autowired
	RiskHonourTypeService riskHonourTypeService;

	@Autowired
	RiskCaseIntegralService riskCaseIntegralService;

	@Autowired
	DepartmentService departmentService;

	/**
	 * ??????????????????
	 * @param file
	 * @return
	 */
	@PostMapping("/risk/caseIntegral/import/data")
	public ResponseEntity<?> importCaseIntegralDate(@RequestParam("file") MultipartFile file) throws Exception {
		List<JSONObject> jsonObjectList = new ArrayList<>();

		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		// ???????????????
		int i = 0;
		int passCount = 0;
		for (List<String> excel : readExcel) {
			i++;
			String policeId = excel.get(1);
			if (policeId != null && !"".equals(policeId)) {
				User user = userService.findByPoliceId(policeId);

				if (user == null || user.getName() == null) {
					jsonObjectList.add(errorJsonObjCreate(i, policeId, "??????????????????"));
					continue;
				}
			} else {
				jsonObjectList.add(errorJsonObjCreate(i, policeId, "???????????????"));
				continue;
			}
			String year = excel.get(3);
			String month = excel.get(4);
			if (StrUtil.isBlank(year)) {
				jsonObjectList.add(errorJsonObjCreate(i, policeId, "????????????????????????"));
				continue;
			}
			if (StrUtil.isBlank(month)) {
				jsonObjectList.add(errorJsonObjCreate(i, policeId, "????????????????????????"));
				continue;
			}

			Department department = departmentService.getDepartmentByName(excel.get(2));
			if (department == null || department.getName() == null) {
				jsonObjectList.add(errorJsonObjCreate(i, policeId, "????????????????????????"));
				continue;
			}
			if (month.length() == 1) {
				month = "0"+month;
			}

			RiskCaseIntegral caseIntegral = new RiskCaseIntegral();
			caseIntegral.setPoliceId(policeId);
			caseIntegral.setDeptId(department.getId());
			caseIntegral.setBusinessTime(DateUtils.parseDate(year +"-"+ month+"-01", "yyyy-MM-dd"));
			caseIntegral.setScore(Double.valueOf(excel.get(5)));
			caseIntegral.setCreationDate(new Date());

			passCount++;
			riskCaseIntegralService.addRiskCaseIntegral(caseIntegral);
		}

		Map<String, Object> result = new HashMap<>();
		result.put("data", jsonObjectList);
		result.put("passCount", passCount);
		return new ResponseEntity<DataListReturn>(DataListReturn.ok(result), HttpStatus.OK);
	}

	/**
	 * ??????????????????
	 * @param file
	 * @return
	 */
	@PostMapping("/risk/honour/import/data")
	public ResponseEntity<?> importHonourDate(@RequestParam("file") MultipartFile file) throws Exception {
		List<JSONObject> jsonObjectList = new ArrayList<>();

		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		// ???????????????
		int i = 0;
		int passCount = 0;
		for (List<String> excel : readExcel) {
			i++;
			String policeId = excel.get(1);
			if (policeId != null && !"".equals(policeId)) {
				User user = userService.findByPoliceId(policeId);

				if (user == null || user.getName() == null) {
					jsonObjectList.add(errorJsonObjCreate(i, policeId, "??????????????????"));
					continue;
				}
			} else {
				jsonObjectList.add(errorJsonObjCreate(i, policeId, "???????????????"));
				continue;
			}

			RiskHonourType honourType = riskHonourTypeService.findByTypeName(excel.get(4));
			if (honourType == null || honourType.getId() == null) {
				jsonObjectList.add(errorJsonObjCreate(i, policeId, "????????????????????????"));
				continue;
			}

			RiskHonour honour = new RiskHonour();
			honour.setPoliceId(policeId);
			honour.setHonourName(excel.get(2));
			honour.setHonourReason(excel.get(3));
			honour.setHonourTypeCode(honourType.getCode());
			honour.setHonourUnit(excel.get(5));
			honour.setHonourUnitLevel(excel.get(6));
			honour.setRemark(excel.get(8));
			honour.setBusinessTime(DateUtils.parseDate(excel.get(7), "yyyy-MM-dd"));
			honour.setCreationDate(new Date());

			passCount++;
			riskHonourService.addRiskHonour(honour);
		}

		Map<String, Object> result = new HashMap<>();
		result.put("data", jsonObjectList);
		result.put("passCount", passCount);
		return new ResponseEntity<DataListReturn>(DataListReturn.ok(result), HttpStatus.OK);
	}

//	@PostMapping("/risk/healthy/import/excel/data")
	public ResponseEntity<?> importHealthData(@RequestParam("file") MultipartFile file) {
		List<JSONObject> importData = ExcelUtil.dataImport(file, 0, 1);
		if (importData == null) {
			return new ResponseEntity<>(DataListReturn.error("????????????"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String yearStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));

		for (JSONObject jsonObject : importData) {
			String policeId = jsonObject.getString("5");
			User user = userService.findByPoliceId(policeId);

			if (user == null || user.getName() == null) {
				continue;
			}

			Integer riskHealthRecordId = riskHealthRecordService.getByIdAndYear(policeId, yearStr, null);

			// ????????????
			RiskHealthRecord riskHealthRecord = new RiskHealthRecord();
			riskHealthRecord.setHeight(0d);
			riskHealthRecord.setWeight(0d);
			riskHealthRecord.setBmi(0d);
			riskHealthRecord.setIsOverweight(0);
			riskHealthRecord.setIsHyperlipidemia(0);
			riskHealthRecord.setIsHypertension(0);
			riskHealthRecord.setIsHyperglycemia(0);
			riskHealthRecord.setIsHyperuricemia(0);
			riskHealthRecord.setIsProstate(0);
			riskHealthRecord.setIsMajorDiseases(0);
			riskHealthRecord.setIsHeart(0);
			riskHealthRecord.setIsTumorAntigen(0);
			riskHealthRecord.setIsOrthopaedics(0);

			RiskHealthRecordInfo recordInfo = new RiskHealthRecordInfo();

			riskHealthRecord.setPoliceId(policeId);
			recordInfo.setPoliceId(policeId);
			riskHealthRecord.setYear(yearStr);

			if(jsonObject.getString("6").indexOf("\\")==-1) {
				riskHealthRecord.setHeight(Double.valueOf(jsonObject.getString("6")));
				recordInfo.setHeight(Double.valueOf(jsonObject.getString("6")));
			}
			if(jsonObject.getString("7").indexOf("\\")==-1) {
				riskHealthRecord.setWeight(Double.valueOf(jsonObject.getString("7")));
				recordInfo.setWeight(Double.valueOf(jsonObject.getString("7")));
			}

			if(jsonObject.getString("6").indexOf("\\")==-1 &&  jsonObject.getString("7").indexOf("\\")==-1) {

				Double bmi=Double.valueOf(jsonObject.getString("7"))/Math.pow(Double.valueOf(jsonObject.getString("6"))/100,2);
				riskHealthRecord.setBmi(bmi);
				riskHealthRecord.setIsOverweight(0);
				if(bmi<=18.40) {
					riskHealthRecord.setBmiId(1);
				}else if(bmi>=18.41 && bmi<=23.99) {
					riskHealthRecord.setBmiId(2);
				}else if(bmi>=24 && bmi<=27.99) {
					riskHealthRecord.setBmiId(3);
					riskHealthRecord.setIsOverweight(1);
				}else if(bmi>=28 && bmi<=100.00) {
					riskHealthRecord.setBmiId(4);
					riskHealthRecord.setIsOverweight(1);
				}
			}

			riskHealthRecord.setBlood(jsonObject.getString("8"));
			if (!jsonObject.getString("10").isEmpty()) {
				recordInfo.setHighDensityLipoprotein(Double.valueOf(jsonObject.getString("10")));
			}

			if (!jsonObject.getString("11").isEmpty()) {
				recordInfo.setLowDensityLipoprotein(Double.valueOf(jsonObject.getString("11")));
			}

			if (!jsonObject.getString("12").isEmpty()) {
				recordInfo.setTriglyceride(Double.valueOf(jsonObject.getString("12")));
				if (Double.valueOf(jsonObject.getString("12")) > 1.7) {
					riskHealthRecord.setIsHyperlipidemia(1);
				}
			}

			if (!jsonObject.getString("13").isEmpty()) {
				recordInfo.setCholesterol(Double.valueOf(jsonObject.getString("13")));
				if (Double.valueOf(jsonObject.getString("13")) > 5.72) {
					riskHealthRecord.setIsHyperlipidemia(1);
				}
			}

			if (!jsonObject.getString("14").isEmpty() && !jsonObject.getString("14").equals("??????")) {
				recordInfo.setReceiveCompression(Double.valueOf(jsonObject.getString("14")));
				if (Double.valueOf(jsonObject.getString("14")) > 140) {
					riskHealthRecord.setIsHypertension(1);
				}
			}

			if (!jsonObject.getString("15").isEmpty() && !jsonObject.getString("15").equals("??????")) {

				recordInfo.setDiastolicPressure(Double.valueOf(jsonObject.getString("15")));
				if (Double.valueOf(jsonObject.getString("15")) > 90) {
					riskHealthRecord.setIsHypertension(1);
				}
			}

			if (!jsonObject.getString("16").isEmpty()) {
				recordInfo.setBloodSugar(Double.valueOf(jsonObject.getString("16")));
				if (Double.valueOf(jsonObject.getString("16")) > 6.1) {
					riskHealthRecord.setIsHyperglycemia(1);
				}
			}

			if (!jsonObject.getString("17").isEmpty()) {
				recordInfo.setSerumUricAcid(Double.valueOf(jsonObject.getString("17")));
				if (Double.valueOf(jsonObject.getString("17")) > 420) {
					riskHealthRecord.setIsHyperuricemia(1);
				}
			}

			riskHealthRecord.setIsProstate(0);
			recordInfo.setIsProstate(0);

			if (!jsonObject.getString("18").isEmpty()) {
				recordInfo.setProstateDesc(jsonObject.getString("18"));
				if (jsonObject.getString("18").equals("??????")) {
					riskHealthRecord.setIsProstate(1);
					recordInfo.setIsProstate(1);
				}
			}

			if (!jsonObject.getString("19").isEmpty()) {
				riskHealthRecord.setIsMajorDiseases(1);
				recordInfo.setIsMajorDiseases(1);
				riskHealthRecord.setMajorDiseasesDescribe(jsonObject.getString("19"));
				recordInfo.setMajorDiseasesDesc(jsonObject.getString("19"));
			} else {
				riskHealthRecord.setIsMajorDiseases(0);
				recordInfo.setIsMajorDiseases(0);
			}

			if (!jsonObject.getString("20").isEmpty()) {
				riskHealthRecord.setIsHeart(1);
				recordInfo.setIsHeart(1);
				riskHealthRecord.setHeartDescribe(jsonObject.getString("20"));
				recordInfo.setHeartDesc(jsonObject.getString("20"));
			} else {
				riskHealthRecord.setIsHeart(0);
				recordInfo.setIsHeart(0);
			}

			if (!jsonObject.getString("21").isEmpty()) {
				riskHealthRecord.setIsTumorAntigen(1);
				recordInfo.setIsTumorAntigen(1);
				riskHealthRecord.setTumorAntigenDescribe(jsonObject.getString("21"));
				recordInfo.setTumorAntigenDesc(jsonObject.getString("21"));
			} else {
				riskHealthRecord.setIsTumorAntigen(0);
				recordInfo.setIsTumorAntigen(0);
			}

			if (!jsonObject.getString("22").isEmpty()) {
				riskHealthRecord.setIsOrthopaedics(1);
				recordInfo.setIsOrthopaedics(1);
				riskHealthRecord.setOrthopaedicsDescribe(jsonObject.getString("22"));
				recordInfo.setOrthopaedicsDesc(jsonObject.getString("22"));
			} else {
				riskHealthRecord.setIsOrthopaedics(0);
				recordInfo.setIsOrthopaedics(0);
			}

			if(riskHealthRecordId!=null) {
				riskHealthRecord.setId(riskHealthRecordId);
				riskHealthRecord.setUpdateDate(new Date());
				riskHealthRecordService.updateByPrimaryKeySelective(riskHealthRecord);

				riskHealthRecordInfoService.deleteByRecordId(riskHealthRecordId);

				recordInfo.setRecordId(riskHealthRecord.getId());
				recordInfo.setCreationDate(new Date());

				riskHealthRecordInfoService.insert(recordInfo);
			}else {
				riskHealthRecord.setCreationDate(new Date());
				riskHealthRecordService.insert(riskHealthRecord);

				recordInfo.setRecordId(riskHealthRecord.getId());
				recordInfo.setCreationDate(new Date());

				riskHealthRecordInfoService.insert(recordInfo);
			}
		}

		return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
	}

	/**
	 * 	??????Excel??????????????????
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/healthy/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importHealthyExcel(@Param("file") MultipartFile file) throws Exception {
		List<JSONObject> jsonObjectList = new ArrayList<>();

		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		readExcel.remove(0);
		Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		Integer month=cal.get(Calendar.MONTH)+1;
		String yearStr=year.toString();
		String monthStr=month.toString();
		if(month<10) {
			monthStr="0"+monthStr;
		}
		
		// ???????????????
		int i = 0;
		int passCount = 0;
		for (List<String> excel : readExcel) {
			String policeId = excel.get(5);
			if (policeId != null && !"".equals(policeId)) {
				User user = userService.findByPoliceId(policeId);

				if (user == null || user.getName() == null) {
					jsonObjectList.add(errorJsonObjCreate(i, excel.get(5), "??????????????????"));
					continue;
				}
			}else {
				jsonObjectList.add(errorJsonObjCreate(i, excel.get(5), "???????????????"));
				continue;
			}

			System.out.println(excel);
			Integer riskHealthRecordId=riskHealthRecordService.getByIdAndYear(excel.get(5), yearStr, null);
			// ???????????????
			i++;
			// ????????????
			RiskHealthRecord riskHealthRecord = new RiskHealthRecord();
			riskHealthRecord.setHeight(0d);
			riskHealthRecord.setWeight(0d);
			riskHealthRecord.setBmi(0d);
			riskHealthRecord.setIsOverweight(0);
			riskHealthRecord.setIsHyperlipidemia(0);
			riskHealthRecord.setIsHypertension(0);
			riskHealthRecord.setIsHyperglycemia(0);
			riskHealthRecord.setIsHyperuricemia(0);
			riskHealthRecord.setIsProstate(0);
			riskHealthRecord.setIsMajorDiseases(0);
			riskHealthRecord.setIsHeart(0);
			riskHealthRecord.setIsTumorAntigen(0);
			riskHealthRecord.setIsOrthopaedics(0);

			RiskHealthRecordInfo recordInfo = new RiskHealthRecordInfo();

			riskHealthRecord.setPoliceId(excel.get(5));
			recordInfo.setPoliceId(excel.get(5));
			riskHealthRecord.setYear(yearStr);

			if(excel.get(6).indexOf("\\")==-1) {
				riskHealthRecord.setHeight(Double.valueOf(excel.get(6)));
				recordInfo.setHeight(Double.valueOf(excel.get(6)));
			}
			if(excel.get(7).indexOf("\\")==-1) {
				riskHealthRecord.setWeight(Double.valueOf(excel.get(7)));
				recordInfo.setWeight(Double.valueOf(excel.get(7)));
			}

			if(excel.get(6).indexOf("\\")==-1 &&  excel.get(7).indexOf("\\")==-1) {

				Double bmi=Double.valueOf(excel.get(7))/Math.pow(Double.valueOf(excel.get(6))/100,2);
				riskHealthRecord.setBmi(bmi);
				riskHealthRecord.setIsOverweight(0);
				riskHealthRecord.setBmiId(0);
				if(bmi<=18.40) {
					riskHealthRecord.setBmiId(1);
				}else if(bmi>=18.41 && bmi<=23.99) {
					riskHealthRecord.setBmiId(2);
				}else if(bmi>=24 && bmi<=27.99) {
					riskHealthRecord.setBmiId(3);
					riskHealthRecord.setIsOverweight(1);
				}else if(bmi>=28 && bmi<=100.00) {
					riskHealthRecord.setBmiId(4);
					riskHealthRecord.setIsOverweight(1);
				}
			}

			riskHealthRecord.setBlood(excel.get(8));

			if (excel.size() > 10) {

				if (!excel.get(10).isEmpty()) {
					recordInfo.setHighDensityLipoprotein(Double.valueOf(excel.get(10)));
				}

				if (!excel.get(11).isEmpty()) {
					recordInfo.setLowDensityLipoprotein(Double.valueOf(excel.get(11)));
				}

				if (!excel.get(12).isEmpty()) {
					recordInfo.setTriglyceride(Double.valueOf(excel.get(12)));
					if (Double.valueOf(excel.get(12)) > 1.7) {
						riskHealthRecord.setIsHyperlipidemia(1);
					}
				}

				if (!excel.get(13).isEmpty()) {

					recordInfo.setCholesterol(Double.valueOf(excel.get(13)));
					if (Double.valueOf(excel.get(13)) > 5.72) {
						riskHealthRecord.setIsHyperlipidemia(1);
					}
				}

				if (!excel.get(14).isEmpty() && !excel.get(14).equals("??????")) {
					recordInfo.setReceiveCompression(Double.valueOf(excel.get(14)));
					if (Double.valueOf(excel.get(14)) > 140) {
						riskHealthRecord.setIsHypertension(1);
					}
				}

				if (!excel.get(15).isEmpty() && !excel.get(15).equals("??????")) {

					recordInfo.setDiastolicPressure(Double.valueOf(excel.get(15)));
					if (Double.valueOf(excel.get(15)) > 90) {
						riskHealthRecord.setIsHypertension(1);
					}
				}

				if (!excel.get(16).isEmpty()) {

					recordInfo.setBloodSugar(Double.valueOf(excel.get(16)));
					if (Double.valueOf(excel.get(16)) > 6.1) {
						riskHealthRecord.setIsHyperglycemia(1);
					}
				}

				if (!excel.get(17).isEmpty()) {

					recordInfo.setSerumUricAcid(Double.valueOf(excel.get(17)));
					if (Double.valueOf(excel.get(17)) > 420) {
						riskHealthRecord.setIsHyperuricemia(1);
					}
				}

				riskHealthRecord.setIsProstate(0);
				recordInfo.setIsProstate(0);
				if (!excel.get(18).isEmpty()) {
					recordInfo.setProstateDesc(excel.get(18));
					if (excel.get(18).equals("??????")) {
						riskHealthRecord.setIsProstate(1);
						recordInfo.setIsProstate(1);
					}
				}

				if (!excel.get(19).isEmpty()) {
					riskHealthRecord.setIsMajorDiseases(1);
					recordInfo.setIsMajorDiseases(1);
					riskHealthRecord.setMajorDiseasesDescribe(excel.get(19));
					recordInfo.setMajorDiseasesDesc(excel.get(19));
				} else {
					riskHealthRecord.setIsMajorDiseases(0);
					recordInfo.setIsMajorDiseases(0);
				}

				if (!excel.get(20).isEmpty()) {
					riskHealthRecord.setIsHeart(1);
					recordInfo.setIsHeart(1);
					riskHealthRecord.setHeartDescribe(excel.get(20));
					recordInfo.setHeartDesc(excel.get(20));
				} else {
					riskHealthRecord.setIsHeart(0);
					recordInfo.setIsHeart(0);
				}

				if (!excel.get(21).isEmpty()) {
					riskHealthRecord.setIsTumorAntigen(1);
					recordInfo.setIsTumorAntigen(1);
					riskHealthRecord.setTumorAntigenDescribe(excel.get(21));
					recordInfo.setTumorAntigenDesc(excel.get(21));
				} else {
					riskHealthRecord.setIsTumorAntigen(0);
					recordInfo.setIsTumorAntigen(0);
				}

				if (!excel.get(22).isEmpty()) {
					riskHealthRecord.setIsOrthopaedics(1);
					recordInfo.setIsOrthopaedics(1);
					riskHealthRecord.setOrthopaedicsDescribe(excel.get(22));
					recordInfo.setOrthopaedicsDesc(excel.get(22));
				} else {
					riskHealthRecord.setIsOrthopaedics(0);
					recordInfo.setIsOrthopaedics(0);
				}
			}
			recordInfo.setOtherHealthDesc(excel.get(23));

			if(riskHealthRecordId!=null) {
				riskHealthRecord.setId(riskHealthRecordId);
				riskHealthRecord.setUpdateDate(new Date());
				riskHealthRecordService.updateByPrimaryKeySelective(riskHealthRecord);

				riskHealthRecordInfoService.deleteByRecordId(riskHealthRecordId);

				recordInfo.setRecordId(riskHealthRecord.getId());
				recordInfo.setCreationDate(new Date());

				riskHealthRecordInfoService.insert(recordInfo);
			}else {
				riskHealthRecord.setCreationDate(new Date());
				riskHealthRecordService.insert(riskHealthRecord);

				recordInfo.setRecordId(riskHealthRecord.getId());
				recordInfo.setCreationDate(new Date());

				riskHealthRecordInfoService.insert(recordInfo);
			}
			passCount++;
		}

		totalRiskDetailsService.healthRiskDetails(LocalDate.now());

		Map<String, Object> result = new HashMap<>();
		result.put("data", jsonObjectList);
		result.put("passCount", passCount);
		return new ResponseEntity<DataListReturn>(DataListReturn.ok(result), HttpStatus.OK);
	}
	
	
	
	/**
	 * 	??????Excel??????????????????
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/school/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importSchoolExcel(@Param("file") MultipartFile file) throws Exception {
		List<JSONObject> jsonObjectList = new ArrayList<>();

		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		readExcel.remove(0);
		String yearMonth=DateUtils.formatDate(new Date(),"yyyy-MM");
		// ???????????????
		int i = 0;
		int passCount = 0;
		for (List<String> excel : readExcel) {
			i++;
			String policeId = excel.get(3);
			if (policeId != null && !"".equals(policeId)) {
				User user = userService.findByPoliceId(policeId);

				if (user == null || user.getName() == null) {
					jsonObjectList.add(errorJsonObjCreate(i, excel.get(3), "??????????????????"));
					continue;
				}
			}else {
				jsonObjectList.add(errorJsonObjCreate(i, excel.get(3), "???????????????"));
				continue;
			}

			Integer id=riskStutyPalmSchoolRecordService.getByIdAndYearMonth(yearMonth, excel.get(3));

			RiskStutyPalmSchoolRecord riskStutyPalmSchoolRecord =new RiskStutyPalmSchoolRecord();

			riskStutyPalmSchoolRecord.setPoliceId(excel.get(3));
			riskStutyPalmSchoolRecord.setTextbookLawEnforcementRate(Double.valueOf(excel.get(6).substring(0, excel.get(6).length()-1)));

			riskStutyPalmSchoolRecord.setPoliceInvolvedRate(Double.valueOf(excel.get(7).substring(0, excel.get(7).length()-1)));

			riskStutyPalmSchoolRecord.setGun1HandoverRate(Double.valueOf(excel.get(8).substring(0, excel.get(8).length()-1)));

			riskStutyPalmSchoolRecord.setForceRate(Double.valueOf(excel.get(9).substring(0, excel.get(9).length()-1)));

			riskStutyPalmSchoolRecord.setGun2HandoverRate(Double.valueOf(excel.get(10).substring(0, excel.get(10).length()-1)));

			riskStutyPalmSchoolRecord.setPhysicalRate(Double.valueOf(excel.get(11).substring(0, excel.get(11).length()-1)));

			riskStutyPalmSchoolRecord.setDrinkDealRate(Double.valueOf(excel.get(12).substring(0, excel.get(12).length()-1)));

			if(id!=null) {
				riskStutyPalmSchoolRecord.setId(id);
				riskStutyPalmSchoolRecord.setUpdateDate(new Date());
				riskStutyPalmSchoolRecordService.updateByPrimaryKeySelective(riskStutyPalmSchoolRecord);
			}else {
				riskStutyPalmSchoolRecord.setCreationDate(new Date());
				riskStutyPalmSchoolRecordService.insertSelective(riskStutyPalmSchoolRecord);
			}
			passCount++;
		}

		Map<String, Object> result = new HashMap<>();
		result.put("data", jsonObjectList);
		result.put("passCount", passCount);
		return new ResponseEntity<DataListReturn>(DataListReturn.ok(result), HttpStatus.OK);
	}
	
	
	/**
	 * 	??????Excel????????????????????????????????????
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/unitTrain/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importUnitTrainExcel(@Param("file") MultipartFile file) throws Exception {
		List<JSONObject> jsonObjectList = new ArrayList<>();

		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		readExcel.remove(0);
		String yearMonth=DateUtils.formatDate(new Date(),"yyyy-MM");
		// ???????????????
		int i = 0;
		int passCount = 0;
		for (List<String> excel : readExcel) {
			i++;
			String policeId = excel.get(2);
			if (policeId != null && !"".equals(policeId)) {
				User user = userService.findByPoliceId(policeId);

				if (user == null || user.getName() == null) {
					jsonObjectList.add(errorJsonObjCreate(i, excel.get(2), "??????????????????"));
					continue;
				}
			}else {
				jsonObjectList.add(errorJsonObjCreate(i, excel.get(2), "???????????????"));
				continue;
			}

			Integer id=riskStutyUnitTrainRecordService.getByIdAndYearMonth(yearMonth, excel.get(2));

			RiskStutyUnitTrainRecord riskStutyUnitTrainRecord=new RiskStutyUnitTrainRecord();

			riskStutyUnitTrainRecord.setPoliceId(excel.get(2));

			if(excel.get(6).equals("??????")) {
				riskStutyUnitTrainRecord.setIsCityTrain(1);
			}

			if(excel.get(7).equals("??????")) {
				riskStutyUnitTrainRecord.setIsPoliceSystem(1);
			}

			if(excel.get(8).equals("??????")) {
				riskStutyUnitTrainRecord.setIsEducationRectification(1);
			}

			if(excel.get(9).equals("??????")) {
				riskStutyUnitTrainRecord.setIsPartyStyleConstruction(1);
			}
			riskStutyUnitTrainRecord.setTotalCount(Double.valueOf(excel.get(10)).intValue());

			if(id!=null) {
				riskStutyUnitTrainRecord.setId(id);
				riskStutyUnitTrainRecord.setUpdateDate(new Date());
				riskStutyUnitTrainRecordService.updateByPrimaryKeySelective(riskStutyUnitTrainRecord);
			}else {
				riskStutyUnitTrainRecord.setCreationDate(new Date());
				riskStutyUnitTrainRecordService.insertSelective(riskStutyUnitTrainRecord);
			}
			passCount++;
		}

		Map<String, Object> result = new HashMap<>();
		result.put("data", jsonObjectList);
		result.put("passCount", passCount);
		return new ResponseEntity<DataListReturn>(DataListReturn.ok(passCount), HttpStatus.OK);
	}
	
	
	/**
	 * 	??????Excel???????????????
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/activityes/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importActivityesExcel(@Param("file") MultipartFile file) throws Exception {
		List<JSONObject> jsonObjectList = new ArrayList<>();

		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		readExcel.remove(0);
		String yearMonth=DateUtils.formatDate(new Date(),"yyyy-MM");
		// ???????????????
		int i = 0;
		int passCount = 0;
		for (List<String> excel : readExcel) {
			i++;
			String policeId = excel.get(2);
			if (policeId != null && !"".equals(policeId)) {
				User user = userService.findByPoliceId(policeId);

				if (user == null || user.getName() == null) {
					jsonObjectList.add(errorJsonObjCreate(i, excel.get(2), "??????????????????"));
					continue;
				}
			}else {
				jsonObjectList.add(errorJsonObjCreate(i, excel.get(2), "???????????????"));
				continue;
			}

			Integer id=riskStutyActivitiesPartyRecordService.getByIdAndYearMonth(yearMonth, excel.get(2));

			RiskStutyActivitiesPartyRecord riskStutyActivitiesPartyRecord=new RiskStutyActivitiesPartyRecord();

			riskStutyActivitiesPartyRecord.setPoliceId(excel.get(2));

			if(excel.get(8).equals("??????")) {
				riskStutyActivitiesPartyRecord.setIsSign(1);
			}else if(excel.get(8).equals("") || excel.get(2)==null) {
				riskStutyActivitiesPartyRecord.setIsSign(2);
			}else {
				riskStutyActivitiesPartyRecord.setIsSign(0);
			}

			if(id!=null) {
				riskStutyActivitiesPartyRecord.setId(id);
				riskStutyActivitiesPartyRecord.setUpdateDate(new Date());
				riskStutyActivitiesPartyRecordService.updateByPrimaryKeySelective(riskStutyActivitiesPartyRecord);
			}else {
				riskStutyActivitiesPartyRecord.setCreationDate(new Date());
				riskStutyActivitiesPartyRecordService.insertSelective(riskStutyActivitiesPartyRecord);
			}
			passCount++;
		}

		Map<String, Object> result = new HashMap<>();
		result.put("data", jsonObjectList);
		result.put("passCount", passCount);
		return new ResponseEntity<DataListReturn>(DataListReturn.ok(result), HttpStatus.OK);
	}

	
	/**
	 * 	??????Excel?????????????????????
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/dutyCase/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importDutyCaseExcel(@Param("file") MultipartFile file) throws Exception {
		List<JSONObject> jsonObjectList = new ArrayList<>();

		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		// ???????????????
		int i = 0;
		int passCount = 0;
		for (List<String> excel : readExcel) {
			i++;
			String policeId = excel.get(2);
			if (policeId != null && !"".equals(policeId)) {
				User user = userService.findByPoliceId(policeId);

				if (user == null || user.getName() == null) {
					jsonObjectList.add(errorJsonObjCreate(i, excel.get(2), "??????????????????"));
					continue;
				}
			} else {
				jsonObjectList.add(errorJsonObjCreate(i, excel.get(2), "???????????????"));
				continue;
			}

			Integer informationId = riskDutyInformationTypeService.findIdByName(excel.get(6));
			Integer errorId = riskDutyErrorTypeService.findIdByName(excel.get(8));
			if (informationId == null) {
				jsonObjectList.add(errorJsonObjCreate(i, policeId, "???????????????????????????????????????????????????????????????"));
				continue;
			}
			if (errorId == null) {
				jsonObjectList.add(errorJsonObjCreate(i, policeId, "???????????????????????????????????????????????????????????????"));
				continue;
			}

			RiskDutyDealPoliceRecord policeRecord = new RiskDutyDealPoliceRecord();
			policeRecord.setInformationId(informationId);
			policeRecord.setErrorId(errorId);
			policeRecord.setPoliceListCode(excel.get(4));
			policeRecord.setJurisdiction(excel.get(5));
			policeRecord.setPoliceListInfo(excel.get(7));
			policeRecord.setIsVerified("?????????".equals(excel.get(10)) ? 1 : 0);
			policeRecord.setPoliceId(policeId);
			policeRecord.setContent(excel.get(9)==null?"":excel.get(9));
			policeRecord.setInputTime(new Date());
			policeRecord.setCreationDate(DateUtils.parseDate(excel.get(3), "yyyy-MM-dd HH:mm"));
			policeRecord.setDeductionScore(Double.valueOf(excel.get(11)));
			policeRecord.setIsEffective(1);

			passCount++;
			riskDutyDealPoliceRecordService.insert(policeRecord);
			totalRiskDetailsService.dutyRiskDetails(LocalDate.parse(DateUtils.formatDate(policeRecord.getCreationDate(), "yyyy-MM-dd")));
		}

		Map<String, Object> result = new HashMap<>();
		result.put("data", jsonObjectList);
		result.put("passCount", passCount);
		return new ResponseEntity<DataListReturn>(DataListReturn.ok(result), HttpStatus.OK);
	}

	/**
	 * ????????????????????????
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/caseLaw/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importCaseLawExcel(@Param("file") MultipartFile file) throws Exception {
		List<JSONObject> jsonObjectList = new ArrayList<>();

		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		int i=0;
		int passCount = 0;
		for (List<String> excel : readExcel) {
			i++;
			String policeId = excel.get(0);
			if (policeId != null && !"".equals(policeId)) {
				User user = userService.findByPoliceId(policeId);

				if (user == null || user.getName() == null) {
					jsonObjectList.add(errorJsonObjCreate(i, excel.get(0), "??????????????????"));
					continue;
				}
			}else {
				jsonObjectList.add(errorJsonObjCreate(i, excel.get(0), "???????????????"));
				continue;
			}

			Integer typeId = null;
			Integer parentId = null;
			parentId = riskCaseLawEnforcementTypeService.findByNameAndParentId(excel.get(4), null);
			if (parentId == null) {
				jsonObjectList.add(errorJsonObjCreate(i, policeId, "??????????????????????????????????????????????????????????????????"));
				continue;
			}
			typeId = riskCaseLawEnforcementTypeService.findByNameAndParentId(excel.get(5), parentId);
			if (typeId == null) {
				jsonObjectList.add(errorJsonObjCreate(i, policeId, "??????????????????????????????????????????????????????????????????"));
				continue;
			}

			RiskCaseLawEnforcementRecord record = new RiskCaseLawEnforcementRecord();
			record.setDeptName(excel.get(2));
			record.setCaseCode(excel.get(3));
			record.setPoliceId(policeId);
			record.setType(typeId);
			record.setContent(excel.get(6));
			record.setInputTime(new Date());
			record.setDeductionScore(Double.valueOf(excel.get(8)));
			record.setCreationDate(DateUtils.parseDate(excel.get(7), "yyyy-MM-dd HH:mm:ss"));
			record.setIsEffective(1);

			passCount++;
			riskCaseLawEnforcementRecordService.insert(record);
			totalRiskDetailsService.caseRiskDetails(LocalDate.parse(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd")));
		}

		Map<String, Object> result = new HashMap<>();
		result.put("data", jsonObjectList);
		result.put("passCount", passCount);
		return new ResponseEntity<DataListReturn>(DataListReturn.ok(result), HttpStatus.OK);
	}
	
	
	/**
	 * 	??????Excel????????????
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/drink/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importDrinkExcel(@Param("file") MultipartFile file) throws Exception {
		List<JSONObject> jsonObjectList = new ArrayList<>();

		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		int i = 0;
		int passCount = 0;
		for (List<String> excel : readExcel) {
			i++;
			String policeId = excel.get(0);
			if (policeId != null && !"".equals(policeId)) {
				User user = userService.findByPoliceId(policeId);

				if (user == null || user.getName() == null) {
					jsonObjectList.add(errorJsonObjCreate(i, excel.get(0), "??????????????????"));
					continue;
				}
			}else {
				jsonObjectList.add(errorJsonObjCreate(i, excel.get(0), "???????????????"));
				continue;
			}

			RiskDrinkRecord riskDrinkRecord = new RiskDrinkRecord();
			riskDrinkRecord.setPoliceId(excel.get(0));
			riskDrinkRecord.setDrinkDate(DateUtils.toDate2(excel.get(2)));
			riskDrinkRecord.setCreationDate(new Date());

			if(excel.get(3).equals("??????")) {
				riskDrinkRecord.setType(1);
			}else if(excel.get(3).equals("??????")) {
				riskDrinkRecord.setType(2);
			}else if(excel.get(3).equals("??????")) {
				riskDrinkRecord.setType(3);
			}else if(excel.get(3).equals("??????")) {
				riskDrinkRecord.setType(4);
			}else if(excel.get(3).equals("??????")) {
				riskDrinkRecord.setType(5);
			}
			passCount++;
			riskService.insertInpromt(riskDrinkRecord);
		}
		LocalDate localDate = LocalDate.now();
		String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		riskDrinkRecordMapper.updateIsWorkDay(date);

		Map<String, Object> result = new HashMap<>();
		result.put("data", jsonObjectList);
		result.put("passCount", passCount);
		return new ResponseEntity<DataListReturn>(DataListReturn.ok(result), HttpStatus.OK);
	}

	
	
	/**
	 * 	??????Excel??????????????????
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/test/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importTestExcel(@Param("file") MultipartFile file) throws Exception {
		List<JSONObject> jsonObjectList = new ArrayList<>();

		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		int i = 0;
		int passCount = 0;
		for (List<String> excel : readExcel) {
			i++;
			String policeId = excel.get(3);
			if (policeId != null && !"".equals(policeId)) {
				User user = userService.findByPoliceId(policeId);

				if (user == null || user.getName() == null) {
					jsonObjectList.add(errorJsonObjCreate(i, policeId, "??????????????????"));
					continue;
				}
			}else {
				jsonObjectList.add(errorJsonObjCreate(i, excel.get(3), "???????????????"));
				continue;
			}
			String lastTime = DateUtils.formatDate(new Date(), "HH:mm:ss");
			String date = excel.get(0) +" "+lastTime;
			RiskCaseTestRecord riskCaseTestRecord = new RiskCaseTestRecord();

			riskCaseTestRecord.setYear(date.substring(0, 4));
			//riskCaseTestRecord.setType(excel.get(1));
			riskCaseTestRecord.setSemester(Integer.valueOf(date.substring(5, 7)));
			riskCaseTestRecord.setIndexNum(0.0);
			riskCaseTestRecord.setName(excel.get(1));
			riskCaseTestRecord.setPoliceId(excel.get(3));
			riskCaseTestRecord.setScore(Double.valueOf(excel.get(4)));

			if(Double.valueOf(riskCaseTestRecord.getScore())<60) {
				riskCaseTestRecord.setDeductionScore(0.1);
			}else {
				riskCaseTestRecord.setDeductionScore(0.0);
			}
			/*Integer id=riskCaseTestRecordService.isExistence(policeId, riskCaseTestRecord.getYear(), riskCaseTestRecord.getSemester(), null);

			riskCaseTestRecord.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
			if(id!=null) {
				riskCaseTestRecord.setId(id);
				riskCaseTestRecord.setUpdateDate(new Date());
				riskCaseTestRecord.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
				riskCaseTestRecordService.updateByPrimaryKeySelective(riskCaseTestRecord);
			}else {

			}*/
			riskCaseTestRecord.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
			riskCaseTestRecordService.insertTest(riskCaseTestRecord);

			passCount++;
			totalRiskDetailsService.caseRiskDetails(LocalDate.parse(DateUtils.formatDate(riskCaseTestRecord.getCreationDate(), "yyyy-MM-dd")));
		}

		Map<String, Object> result = new HashMap<>();
		result.put("data", jsonObjectList);
		result.put("passCount", passCount);
		return new ResponseEntity<DataListReturn>(DataListReturn.ok(result), HttpStatus.OK);
	}

	
	/**
	 * 	??????Excel??????????????????
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/ability/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importAbilityExcel(@Param("file") MultipartFile file) throws Exception {
		List<JSONObject> jsonObjectList = new ArrayList<>();

		List<List<String>> readExcel = GetExcel.ReadExcel(file);

		// ???????????????
		int i = 0;
		int passCount = 0;
		for (List<String> excel : readExcel) {
			i++;
			RiskCaseAbilityRecord riskCaseAbilityRecord = new RiskCaseAbilityRecord();
			String policeId = excel.get(2);
			if (policeId != null && !"".equals(policeId)) {
				User user = userService.findByPoliceId(policeId);

				if (user == null || user.getName() == null) {
					jsonObjectList.add(errorJsonObjCreate(i, excel.get(2), "??????????????????"));
					continue;
				}
			}else {
				jsonObjectList.add(errorJsonObjCreate(i, excel.get(2), "???????????????"));
				continue;
			}

			riskCaseAbilityRecord.setPoliceId(policeId);
			riskCaseAbilityRecord.setReconsiderationLitigationStatus(Double.valueOf(excel.get(3)).intValue());
			riskCaseAbilityRecord.setLetterVisitStatus(Double.valueOf(excel.get(4)).intValue());
			riskCaseAbilityRecord.setLawEnforcementFaultStatus(Double.valueOf(excel.get(5)).intValue());
			riskCaseAbilityRecord.setJudicialSupervisionStatus(Double.valueOf(excel.get(6)).intValue());
			riskCaseAbilityRecord.setCaseExpertStatus(Double.valueOf(excel.get(7)).intValue());
			riskCaseAbilityRecord.setExcellentLegalOfficerStatus(Double.valueOf(excel.get(8)).intValue());
			riskCaseAbilityRecord.setBasicTestStatus(Double.valueOf(excel.get(9)).intValue());
			riskCaseAbilityRecord.setHighTestStatus(Double.valueOf(excel.get(10)).intValue());
			riskCaseAbilityRecord.setJudicialTestStatus(Double.valueOf(excel.get(11)).intValue());

			String columnDate = excel.get(0);
			Integer id = null;
			if (columnDate != null && !"".equals(columnDate)) {
				riskCaseAbilityRecord.setYear(columnDate.substring(0, 4));
				id = riskCaseAbilityRecordService.getByYearAndPoliceId(columnDate, policeId);
			}else {
				jsonObjectList.add(errorJsonObjCreate(i, policeId, "?????????????????????"));
				continue;
			}

			if(id!=null) {
				riskCaseAbilityRecord.setId(id);
				riskCaseAbilityRecord.setUpdateDate(new Date());
				riskCaseAbilityRecord.setCreationDate(DateUtils.parseDate(columnDate, "yyyy-MM-dd"));
				riskCaseAbilityRecordService.updateByPrimaryKeySelective(riskCaseAbilityRecord);
			}else {
				riskCaseAbilityRecord.setCreationDate(DateUtils.parseDate(columnDate, "yyyy-MM-dd"));
				riskCaseAbilityRecordService.insertSelective(riskCaseAbilityRecord);
			}

			passCount++;
			totalRiskDetailsService.caseRiskDetails(LocalDate.parse(DateUtils.formatDate(riskCaseAbilityRecord.getCreationDate(), "yyyy-MM-dd")));
		}

		Map<String, Object> result = new HashMap<>();
		result.put("data", jsonObjectList);
		result.put("passCount", passCount);
		return new ResponseEntity<DataListReturn>(DataListReturn.ok(result), HttpStatus.OK);
	}
	
	
	/**
	 * 	??????Excel????????????
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/conduct/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importConductExcel(@Param("file") MultipartFile file) throws Exception {
		List<JSONObject> jsonObjectList = new ArrayList<>();

		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		//readExcel.remove(0);
		// ???????????????
		int i = 0;
		int passCount = 0;
		for (List<String> excel : readExcel) {
			i++;
			String policeId = excel.get(0);
			if (policeId != null && !"".equals(policeId)) {
				User user = userService.findByPoliceId(policeId);

				if (user == null || user.getName() == null) {
					jsonObjectList.add(errorJsonObjCreate(i, excel.get(0), "??????????????????"));
					continue;
				}
			}else {
				jsonObjectList.add(errorJsonObjCreate(i, excel.get(0), "???????????????"));
				continue;
			}

			RiskConductBureauRuleRecord riskConductBureauRuleRecord = new RiskConductBureauRuleRecord();
			if(excel.get(0).equals("??????")  ||  excel.get(0).equals("??????")) {
//				jsonObjectList.add(errorJsonObjCreate(i, excel.get(0), ""));
				continue;
			}
			riskConductBureauRuleRecord.setPoliceId(excel.get(0));

			if(excel.get(2).equals("????????????")) {
				riskConductBureauRuleRecord.setType(10101);
				riskConductBureauRuleRecord.setDeductionScore(1.0);
			}else if(excel.get(2).equals("????????????")) {
				riskConductBureauRuleRecord.setType(10102);
				riskConductBureauRuleRecord.setDeductionScore(1.0);
			}else if(excel.get(2).equals("????????????")) {
				riskConductBureauRuleRecord.setType(10103);
				riskConductBureauRuleRecord.setDeductionScore(2.0);
			}else if(excel.get(2).equals("????????????")) {
				riskConductBureauRuleRecord.setType(10104);
				riskConductBureauRuleRecord.setDeductionScore(2.0);
			}else if(excel.get(2).equals("????????????")) {
				riskConductBureauRuleRecord.setType(10105);
				riskConductBureauRuleRecord.setDeductionScore(1.0);
			}

			riskConductBureauRuleRecord.setContent(excel.get(3));
			riskConductBureauRuleRecord.setInputTime(DateUtils.toDate2(excel.get(5)));
			riskConductBureauRuleRecord.setRemarks(excel.get(6));
			riskConductBureauRuleRecord.setIsEffective(1);

			riskConductBureauRuleRecord.setCreationDate(DateUtils.toDate2(excel.get(5)));

			passCount++;
			riskConductBureauRuleRecordService.insertSelective(riskConductBureauRuleRecord);
		}

		Map<String, Object> result = new HashMap<>();
		result.put("data", jsonObjectList);
		result.put("passCount", passCount);
		return new ResponseEntity<DataListReturn>(DataListReturn.ok(result), HttpStatus.OK);
	}
	
	
	
	/**
	 * 	??????Excel?????????
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/visit/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importVisitExcel(@Param("file") MultipartFile file) throws Exception {
		List<JSONObject> jsonObjectList = new ArrayList<>();

		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		// ???????????????
		int i = 0;
		int passCount = 0;
		for (List<String> excel : readExcel) {
			i++;
			String policeId = excel.get(0);
			if (policeId != null && !"".equals(policeId)) {
				User user = userService.findByPoliceId(policeId);

				if (user == null || user.getName() == null) {
					jsonObjectList.add(errorJsonObjCreate(i, excel.get(0), "??????????????????"));
					continue;
				}
			}else {
				jsonObjectList.add(errorJsonObjCreate(i, excel.get(0), "???????????????"));
				continue;
			}


			RiskConductVisitRecord riskConductVisitRecord=new RiskConductVisitRecord();
			String really = excel.get(6);
			riskConductVisitRecord.setIsEffective(1);
			riskConductVisitRecord.setIsReally(really == null ? 1 : "???".equals(really) ? 2 : 1);
			riskConductVisitRecord.setPoliceId(excel.get(0));
			riskConductVisitRecord.setInputTime(DateUtils.toDate2(excel.get(9)));
			riskConductVisitRecord.setContent(excel.get(7));
			riskConductVisitRecord.setRemarks(excel.get(10));
			riskConductVisitRecord.setCreationDate(DateUtils.toDate2(excel.get(9)));
			riskConductVisitRecord.setDeductionScore(Double.valueOf(excel.get(8)));

			RiskConductVisitType visitType = riskConductVisitTypeService.findByName(excel.get(5));
			if (visitType != null) {
				riskConductVisitRecord.setType(visitType.getId());
			}
			RiskConductVisitOrigin visitOrigin = riskConductVisitOriginService.findByName(excel.get(3));
			if (visitOrigin != null) {
				riskConductVisitRecord.setOriginId(visitOrigin.getId());
			}

			passCount++;
			riskConductVisitRecordService.insertSelective(riskConductVisitRecord);
			totalRiskDetailsService.conductRiskDetails(LocalDate.parse(DateUtils.formatDate(riskConductVisitRecord.getCreationDate(), "yyyy-MM-dd")));
		}

		Map<String, Object> result = new HashMap<>();
		result.put("data", jsonObjectList);
		result.put("passCount", passCount);
		return new ResponseEntity<DataListReturn>(DataListReturn.ok(result), HttpStatus.OK);
	}
	
	//@Scheduled( cron="0 0 1 * * ?")
	//@Scheduled( cron="30 * * * * ?")
	public void healthDataTiming() {
		Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		Integer month=cal.get(Calendar.MONTH)+1;
		String yearStr=year.toString();
		String monthStr=month.toString();
		if(month<10) {
			monthStr="0"+monthStr;
		}
		List<RiskHealth> riskHealth=riskService.getByYear(yearStr);
		
		if(riskHealth!=null && riskHealth.size()>0) {
			
			for(RiskHealth r : riskHealth) {
				
				RiskReportRecord riskReportRecord= riskHealthRecordService.getByPoliceIdMonth(yearStr, monthStr, r.getPoliceId());
				
				if(riskReportRecord!=null) {
					Double healthNum=riskService.fraction(riskReportRecord.getId());
					riskReportRecord.setHealthNum(r.getHealthTotal());
					riskReportRecord.setTotalNum(healthNum+r.getHealthTotal());
					riskReportRecord.setUpdateDate(new Date());
					riskService.updateRiskReportRecord(riskReportRecord);
				}
			}
		}
		System.out.println("??????");
	}

	private JSONObject errorJsonObjCreate(int count, String policeId, String desc) {
		JSONObject object = new JSONObject();
		object.put("count", count);
		object.put("policeId", policeId);
		object.put("desc", desc);
		return object;
	}
	
//	@RequestMapping("/test")
//	public void test() {
//		riskReportRecordService.family();
//	}
}
