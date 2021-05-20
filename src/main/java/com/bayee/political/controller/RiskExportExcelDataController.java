package com.bayee.political.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bayee.political.domain.*;
import com.bayee.political.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.bayee.political.mapper.RiskDrinkRecordMapper;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import com.bayee.political.utils.GetExcel;
import com.bayee.political.utils.StatusCode;

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

	/**
	 * 	导入Excel警员健康数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/healthy/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importHealthyExcel(@Param("file") MultipartFile file) throws Exception {
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
		
		// 记录错误行
		int index = 0;
		try {
			for (List<String> excel : readExcel) {
				String policeId = excel.get(5);
				if (policeId != null && !"".equals(policeId)) {
					User user = userService.findByPoliceId(policeId);

					if (user == null || user.getName() == null) {
						throw new RuntimeException();
					}
				}else {
					throw new RuntimeException();
				}

				System.out.println(excel);
				Integer riskHealthRecordId=riskHealthRecordService.getByIdAndYear(excel.get(5), yearStr, null);
				// 累计错误行
				index++;
				// 警员体检
				RiskHealthRecord riskHealthRecord = new RiskHealthRecord();
				
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
				
				if(!excel.get(10).isEmpty()) {
					recordInfo.setHighDensityLipoprotein(Double.valueOf(excel.get(10)));
				}
				
				if(!excel.get(11).isEmpty()) {
					recordInfo.setLowDensityLipoprotein(Double.valueOf(excel.get(11)));
				}
				
				if(!excel.get(12).isEmpty()) {
			        recordInfo.setTriglyceride(Double.valueOf(excel.get(12)));
					if(Double.valueOf(excel.get(12))>1.7) {
						riskHealthRecord.setIsHyperlipidemia(1);
					}
				}
				
				if(!excel.get(13).isEmpty()) {

			        recordInfo.setCholesterol(Double.valueOf(excel.get(13)));
					if(Double.valueOf(excel.get(13))>5.72) {
						riskHealthRecord.setIsHyperlipidemia(1);
					}
				}
				
				if(!excel.get(14).isEmpty() &&  !excel.get(14).equals("弃检")) {
					

			        recordInfo.setReceiveCompression(Double.valueOf(excel.get(14)));
					if(Double.valueOf(excel.get(14))>140) {
						riskHealthRecord.setIsHypertension(1);
					}
				}
				
				if(!excel.get(15).isEmpty() &&  !excel.get(15).equals("弃检")) {

			        recordInfo.setDiastolicPressure(Double.valueOf(excel.get(15)));
					if(Double.valueOf(excel.get(15))>90) {
						riskHealthRecord.setIsHypertension(1);
					}
				}
				
				if(!excel.get(16).isEmpty()) {

			        recordInfo.setBloodSugar(Double.valueOf(excel.get(16)));
					if(Double.valueOf(excel.get(16))>6.1) {
						riskHealthRecord.setIsHyperglycemia(1);
					}
				}
				
				if(!excel.get(17).isEmpty()) {

			        recordInfo.setSerumUricAcid(Double.valueOf(excel.get(17)));
					if(Double.valueOf(excel.get(17))>420) {
						riskHealthRecord.setIsHyperuricemia(1);
					}
				}

				riskHealthRecord.setIsProstate(0);
				recordInfo.setIsProstate(0);
				if(!excel.get(18).isEmpty()) {
			        recordInfo.setProstateDesc(excel.get(18));
					if(excel.get(18).equals("异常")) {
						riskHealthRecord.setIsProstate(1);
				        recordInfo.setIsProstate(1);
					}
				}
				
				if(!excel.get(19).isEmpty()) {
					riskHealthRecord.setIsMajorDiseases(1);
			        recordInfo.setIsMajorDiseases(1);
				}else {
					riskHealthRecord.setIsMajorDiseases(0);
					recordInfo.setIsMajorDiseases(0);
				}
				riskHealthRecord.setMajorDiseasesDescribe(excel.get(19));
		        recordInfo.setMajorDiseasesDesc(excel.get(19));


				if(!excel.get(20).isEmpty()) {
					riskHealthRecord.setIsHeart(1);
			        recordInfo.setIsHeart(1);
				}else {
					riskHealthRecord.setIsHeart(0);
					recordInfo.setIsHeart(0);
				}
				riskHealthRecord.setHeartDescribe(excel.get(20));
				recordInfo.setHeartDesc(excel.get(20));

				
				if(!excel.get(21).isEmpty()) {
					riskHealthRecord.setIsTumorAntigen(1);
			        recordInfo.setIsTumorAntigen(1);
				}else {
					riskHealthRecord.setIsTumorAntigen(0);
					recordInfo.setIsTumorAntigen(0);
				}
				riskHealthRecord.setTumorAntigenDescribe(excel.get(21));
		        recordInfo.setTumorAntigenDesc(excel.get(21));
				
				if(!excel.get(22).isEmpty()) {
					riskHealthRecord.setIsOrthopaedics(1);
			        recordInfo.setIsOrthopaedics(1);
				}else {
					riskHealthRecord.setIsOrthopaedics(0);
					recordInfo.setIsOrthopaedics(0);
				}
				riskHealthRecord.setOrthopaedicsDescribe(excel.get(22));
		        recordInfo.setOrthopaedicsDesc(excel.get(22));
				
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

			

		} catch (Exception e) {

			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

		// 添加到数据库
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(readExcel.get(0).get(0).toString() + "-" + readExcel.get(0).get(1).toString());
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}
	
	
	
	/**
	 * 	导入Excel掌上学堂数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/school/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importSchoolExcel(@Param("file") MultipartFile file) throws Exception {
		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		readExcel.remove(0);
		String yearMonth=DateUtils.formatDate(new Date(),"yyyy-MM");
		// 记录错误行
		int index = 0;

		try {

			for (List<String> excel : readExcel) {
				index++;
				String policeId = excel.get(3);
				if (policeId != null && !"".equals(policeId)) {
					User user = userService.findByPoliceId(policeId);

					if (user == null || user.getName() == null) {
						throw new RuntimeException();
					}
				}else {
					throw new RuntimeException();
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
				
			}

		} catch (Exception e) {

			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

		// 添加到数据库
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(readExcel.get(0).get(0).toString() + "-" + readExcel.get(0).get(1).toString());
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}
	
	
	/**
	 * 	导入Excel下沙机关单位参加培训情况
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/unitTrain/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importUnitTrainExcel(@Param("file") MultipartFile file) throws Exception {
		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		readExcel.remove(0);
		String yearMonth=DateUtils.formatDate(new Date(),"yyyy-MM");
		// 记录错误行
		int index = 0;

		try {

			for (List<String> excel : readExcel) {
				index++;
				String policeId = excel.get(2);
				if (policeId != null && !"".equals(policeId)) {
					User user = userService.findByPoliceId(policeId);

					if (user == null || user.getName() == null) {
						throw new RuntimeException();
					}
				}else {
					throw new RuntimeException();
				}
				
				Integer id=riskStutyUnitTrainRecordService.getByIdAndYearMonth(yearMonth, excel.get(2));
				
				RiskStutyUnitTrainRecord riskStutyUnitTrainRecord=new RiskStutyUnitTrainRecord();
				
				riskStutyUnitTrainRecord.setPoliceId(excel.get(2));
				
				if(excel.get(6).equals("参加")) {
					riskStutyUnitTrainRecord.setIsCityTrain(1);
				}
				
				if(excel.get(7).equals("参加")) {
					riskStutyUnitTrainRecord.setIsPoliceSystem(1);
				}
				
				if(excel.get(8).equals("参加")) {
					riskStutyUnitTrainRecord.setIsEducationRectification(1);
				}
				
				if(excel.get(9).equals("参加")) {
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
				
			}

		} catch (Exception e) {

			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

		// 添加到数据库
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(readExcel.get(0).get(0).toString() + "-" + readExcel.get(0).get(1).toString());
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}
	
	
	/**
	 * 	导入Excel党组织培训
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/activityes/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importActivityesExcel(@Param("file") MultipartFile file) throws Exception {
		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		readExcel.remove(0);
		String yearMonth=DateUtils.formatDate(new Date(),"yyyy-MM");
		// 记录错误行
		int index = 0;

		try {

			for (List<String> excel : readExcel) {
				index++;
				String policeId = excel.get(2);
				if (policeId != null && !"".equals(policeId)) {
					User user = userService.findByPoliceId(policeId);

					if (user == null || user.getName() == null) {
						throw new RuntimeException();
					}
				}else {
					throw new RuntimeException();
				}
				
				Integer id=riskStutyActivitiesPartyRecordService.getByIdAndYearMonth(yearMonth, excel.get(2));
				
				RiskStutyActivitiesPartyRecord riskStutyActivitiesPartyRecord=new RiskStutyActivitiesPartyRecord();
				
				riskStutyActivitiesPartyRecord.setPoliceId(excel.get(2));
				
				if(excel.get(8).equals("代签")) {
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
				
			}

		} catch (Exception e) {

			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

		// 添加到数据库
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(readExcel.get(0).get(0).toString() + "-" + readExcel.get(0).get(1).toString());
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	
	/**
	 * 	导入Excel执法接处警数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/dutyCase/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importDutyCaseExcel(@Param("file") MultipartFile file) throws Exception {
		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		// 记录错误行
		int index = 0;

		try {

			for (List<String> excel : readExcel) {
				String policeId = excel.get(2);
				if (policeId != null && !"".equals(policeId)) {
					User user = userService.findByPoliceId(policeId);

					if (user == null || user.getName() == null) {
						throw new RuntimeException();
					}
				}else {
					throw new RuntimeException();
				}

				Integer informationId = riskDutyInformationTypeService.findIdByName(excel.get(6));
				Integer errorId = riskDutyErrorTypeService.findIdByName(excel.get(8));
				if (informationId == null || errorId == null) {
					throw new RuntimeException();
				}

				RiskDutyDealPoliceRecord policeRecord = new RiskDutyDealPoliceRecord();
				policeRecord.setInformationId(informationId);
				policeRecord.setErrorId(errorId);
				policeRecord.setPoliceListCode(excel.get(4));
				policeRecord.setJurisdiction(excel.get(5));
				policeRecord.setPoliceListInfo(excel.get(7));
				policeRecord.setIsVerified("已核实".equals(excel.get(10)) ? 1 : 0);
				policeRecord.setPoliceId(policeId);
				policeRecord.setContent(excel.get(9));
				policeRecord.setInputTime(new Date());
				policeRecord.setCreationDate(DateUtils.parseDate(excel.get(3), "yyyy-MM-dd HH:mm:ss"));
				policeRecord.setDeductionScore(Double.valueOf(excel.get(11)));

				riskDutyDealPoliceRecordService.insert(policeRecord);
				totalRiskDetailsService.dutyRiskDetails(policeId, LocalDate.parse(excel.get(3).substring(0, 10)));
			}

		} catch (Exception e) {
			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		}

		// 添加到数据库
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(readExcel.get(0).get(0).toString() + "-" + readExcel.get(0).get(1).toString());
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 导入执法管理数据
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/caseLaw/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importCaseLawExcel(@Param("file") MultipartFile file) throws Exception {
		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		// 记录错误行
		int index = 0;
		try {

			for (List<String> excel : readExcel) {
				index++;
				String policeId = excel.get(0);
				if (policeId != null && !"".equals(policeId)) {
					User user = userService.findByPoliceId(policeId);

					if (user == null || user.getName() == null) {
						throw new RuntimeException();
					}
				}else {
					throw new RuntimeException();
				}

				Integer typeId = null;
				Integer parentId = null;
				parentId = riskCaseLawEnforcementTypeService.findByNameAndParentId(excel.get(4), null);
				if (parentId == null) {
					throw new RuntimeException();
				}
				typeId = riskCaseLawEnforcementTypeService.findByNameAndParentId(excel.get(4), parentId);
				if (typeId == null) {
					throw new RuntimeException();
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

				riskCaseLawEnforcementRecordService.insert(record);
				totalRiskDetailsService.caseRiskDetails(policeId, LocalDate.parse(excel.get(7).substring(0, 10)));
			}

		} catch (Exception e) {
			e.printStackTrace();
			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		}

		// 添加到数据库
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}
	
	
	/**
	 * 	导入Excel饮酒报备
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/drink/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importDrinkExcel(@Param("file") MultipartFile file) throws Exception {
		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		//readExcel.remove(0);
		// 记录错误行
		int index = 0;

		try {

			for (List<String> excel : readExcel) {
				index++;
				String policeId = excel.get(0);
				if (policeId != null && !"".equals(policeId)) {
					User user = userService.findByPoliceId(policeId);

					if (user == null || user.getName() == null) {
						throw new RuntimeException();
					}
				}else {
					throw new RuntimeException();
				}

				RiskDrinkRecord riskDrinkRecord = new RiskDrinkRecord();
				riskDrinkRecord.setPoliceId(excel.get(0));
				riskDrinkRecord.setDrinkDate(DateUtils.toDate2(excel.get(2)));
				riskDrinkRecord.setCreationDate(new Date());
				
				if(excel.get(3).equals("家人")) {
					riskDrinkRecord.setType(1);
				}else if(excel.get(3).equals("朋友")) {
					riskDrinkRecord.setType(2);
				}else if(excel.get(3).equals("同事")) {
					riskDrinkRecord.setType(3);
				}else if(excel.get(3).equals("同学")) {
					riskDrinkRecord.setType(4);
				}else if(excel.get(3).equals("其他")) {
					riskDrinkRecord.setType(5);
				}
				riskService.insertInpromt(riskDrinkRecord);
			}
			LocalDate localDate = LocalDate.now();
			String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			riskDrinkRecordMapper.updateIsWorkDay(date);

		} catch (Exception e) {

			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

		// 添加到数据库
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(readExcel.get(0).get(0).toString() + "-" + readExcel.get(0).get(1).toString());
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	
	
	/**
	 * 	导入Excel执法考试管理
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/test/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importTestExcel(@Param("file") MultipartFile file) throws Exception {
		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		//readExcel.remove(0);
		// 记录错误行
		int index = 0;

		try {

			for (List<String> excel : readExcel) {
				index++;
				String policeId = excel.get(4);
				if (policeId != null && !"".equals(policeId)) {
					User user = userService.findByPoliceId(policeId);

					if (user == null || user.getName() == null) {
						throw new RuntimeException();
					}
				}else {
					throw new RuntimeException();
				}

				RiskCaseTestRecord riskCaseTestRecord = new RiskCaseTestRecord();

				riskCaseTestRecord.setYear(excel.get(0));
				//riskCaseTestRecord.setType(excel.get(1));
				riskCaseTestRecord.setSemester(Double.valueOf(excel.get(1)).intValue());
				riskCaseTestRecord.setIndexNum(0.0);
				riskCaseTestRecord.setName(excel.get(2));
				riskCaseTestRecord.setPoliceId(excel.get(4));
				riskCaseTestRecord.setScore(Double.valueOf(excel.get(5)));
				
				if(Double.valueOf(excel.get(5))<60) {
					riskCaseTestRecord.setDeductionScore(0.1);
				}else {
					riskCaseTestRecord.setDeductionScore(0.0);
				}
				Integer id=riskCaseTestRecordService.isExistence(excel.get(4), excel.get(0), Double.valueOf(excel.get(1)).intValue(), null);

				String lastTime = DateUtils.formatDate(new Date(), "-MM-dd HH:mm:ss");
				String lastDate = DateUtils.formatDate(new Date(), "-MM-dd");
				riskCaseTestRecord.setCreationDate(DateUtils.parseDate(excel.get(0)+lastTime, "yyyy-MM-dd"));
				if(id!=null) {
					riskCaseTestRecord.setId(id);
					riskCaseTestRecord.setUpdateDate(new Date());
					riskCaseTestRecord.setCreationDate(DateUtils.parseDate(excel.get(0)+lastTime, "yyyy-MM-dd"));
					riskCaseTestRecordService.updateByPrimaryKeySelective(riskCaseTestRecord);
				}else {
					riskCaseTestRecord.setCreationDate(DateUtils.parseDate(excel.get(0)+lastTime, "yyyy-MM-dd"));
					riskCaseTestRecordService.insertTest(riskCaseTestRecord);
				}

				totalRiskDetailsService.caseRiskDetails(policeId, LocalDate.parse(excel.get(0) + lastDate));
			}

		} catch (Exception e) {

			e.printStackTrace();
			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

		// 添加到数据库
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(readExcel.get(0).get(0).toString() + "-" + readExcel.get(0).get(1).toString());
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	
	/**
	 * 	导入Excel执法能力管理
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/ability/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importAbilityExcel(@Param("file") MultipartFile file) throws Exception {
		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		//readExcel.remove(0);
		// 记录错误行
		int index = 0;

		try {

			for (List<String> excel : readExcel) {
				index++;
				RiskCaseAbilityRecord riskCaseAbilityRecord = new RiskCaseAbilityRecord();
				String policeId = excel.get(2);
				if (policeId != null && !"".equals(policeId)) {
					User user = userService.findByPoliceId(policeId);

					if (user == null || user.getName() == null) {
						throw new RuntimeException();
					}
				}else {
					throw new RuntimeException();
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
					throw new RuntimeException();
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

				totalRiskDetailsService.caseRiskDetails(policeId, LocalDate.parse(columnDate));
			}

		} catch (Exception e) {

			e.printStackTrace();
			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

		// 添加到数据库
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(readExcel.get(0).get(0).toString() + "-" + readExcel.get(0).get(1).toString());
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}
	
	
	/**
	 * 	导入Excel局规积分
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/conduct/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importConductExcel(@Param("file") MultipartFile file) throws Exception {
		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		//readExcel.remove(0);
		// 记录错误行
		int index = 0;

		try {

			for (List<String> excel : readExcel) {
				index++;
				String policeId = excel.get(0);
				if (policeId != null && !"".equals(policeId)) {
					User user = userService.findByPoliceId(policeId);

					if (user == null || user.getName() == null) {
						throw new RuntimeException();
					}
				}else {
					throw new RuntimeException();
				}

				RiskConductBureauRuleRecord riskConductBureauRuleRecord = new RiskConductBureauRuleRecord();
				if(excel.get(0).equals("辅警")  ||  excel.get(0).equals("文员")) {
					continue;
				}
				riskConductBureauRuleRecord.setPoliceId(excel.get(0));
				
				if(excel.get(2).equals("谈话提醒")) {
					riskConductBureauRuleRecord.setType(10101);
					riskConductBureauRuleRecord.setDeductionScore(1.0);
				}else if(excel.get(2).equals("约谈函询")) {
					riskConductBureauRuleRecord.setType(10102);
					riskConductBureauRuleRecord.setDeductionScore(1.0);
				}else if(excel.get(2).equals("批评教育")) {
					riskConductBureauRuleRecord.setType(10103);
					riskConductBureauRuleRecord.setDeductionScore(2.0);
				}else if(excel.get(2).equals("诫勉谈话")) {
					riskConductBureauRuleRecord.setType(10104);
					riskConductBureauRuleRecord.setDeductionScore(2.0);
				}else if(excel.get(2).equals("通报批评")) {
					riskConductBureauRuleRecord.setType(10105);
					riskConductBureauRuleRecord.setDeductionScore(1.0);
				}
				
				riskConductBureauRuleRecord.setContent(excel.get(3));
				riskConductBureauRuleRecord.setInputTime(DateUtils.toDate2(excel.get(5)));
				riskConductBureauRuleRecord.setRemarks(excel.get(6));
				
				riskConductBureauRuleRecord.setCreationDate(DateUtils.toDate2(excel.get(5)));
				riskConductBureauRuleRecordService.insertSelective(riskConductBureauRuleRecord);

//				totalRiskDetailsService.conductRiskDetails(policeId, LocalDate.parse(columnDate));
			}

		} catch (Exception e) {

			e.printStackTrace();
			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

		// 添加到数据库
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(readExcel.get(0).get(0).toString() + "-" + readExcel.get(0).get(1).toString());
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}
	
	
	
	/**
	 * 	导入Excel局信访
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/risk/visit/import/excel/data", method = RequestMethod.POST)
	public ResponseEntity<?> importVisitExcel(@Param("file") MultipartFile file) throws Exception {
		List<List<String>> readExcel = GetExcel.ReadExcel(file);
		//readExcel.remove(0);
		// 记录错误行
		int index = 0;

		try {

			for (List<String> excel : readExcel) {
				index++;
				String policeId = excel.get(0);
				if (policeId != null && !"".equals(policeId)) {
					User user = userService.findByPoliceId(policeId);

					if (user == null || user.getName() == null) {
						throw new RuntimeException();
					}
				}else {
					throw new RuntimeException();
				}

				//
				RiskConductVisitRecord riskConductVisitRecord=new RiskConductVisitRecord();
				
				riskConductVisitRecord.setPoliceId(excel.get(0));
				
				if(excel.get(2).equals("党纪处分") && excel.get(3).equals("警告")) {
					riskConductVisitRecord.setType(11);
					riskConductVisitRecord.setDeductionScore(3.0);
				}else if(excel.get(2).equals("政纪处分") && excel.get(3).equals("警告")) {
					riskConductVisitRecord.setType(16);
					riskConductVisitRecord.setDeductionScore(1.0);
				}else {
					Integer typeId=riskConductVisitRecordService.selectByName(excel.get(3));
					
					if(typeId==null) {
						typeId=riskConductVisitRecordService.selectByName(excel.get(2));
					}
					riskConductVisitRecord.setType(typeId);
				}
				
				if(excel.get(2).equals("追究刑事责任") ||  excel.get(3).equals("撤销党内职务") ||  excel.get(3).equals("开除党籍") ||  excel.get(3).equals("开除")) {
					riskConductVisitRecord.setDeductionScore(10.0);
				}else if( excel.get(2).equals("追究民事责任") ){
					riskConductVisitRecord.setDeductionScore(9.0);
				}
				
				if(excel.get(3).equals("责令检查") || excel.get(3).equals("提醒谈话") || excel.get(3).equals("函询")) {
					riskConductVisitRecord.setDeductionScore(1.0);
				}else if(excel.get(3).equals("批评教育")|| excel.get(3).equals("诫勉") || excel.get(3).equals("记过")) {
					riskConductVisitRecord.setDeductionScore(2.0);
				}else if(excel.get(3).equals("记大过")) {
					riskConductVisitRecord.setDeductionScore(3.0);
				}else if(excel.get(3).equals("严重警告") || excel.get(3).equals("降级")) {
					riskConductVisitRecord.setDeductionScore(4.0);
				}else if(excel.get(3).equals("留党察看")) {
					riskConductVisitRecord.setDeductionScore(5.0);
				}else if(excel.get(3).equals("撤职")) {
					riskConductVisitRecord.setDeductionScore(7.0);
				}
				
				if(excel.get(2).equals("局规记分") ||  excel.get(3).equals("局规记分")) {
					riskConductVisitRecord.setDeductionScore(Double.valueOf(excel.get(5)));;
				}
				

				riskConductVisitRecord.setContent(excel.get(4));
				riskConductVisitRecord.setInputTime(DateUtils.toDate2(excel.get(6)));
				riskConductVisitRecord.setRemarks(excel.get(7));
				riskConductVisitRecord.setCreationDate(DateUtils.toDate2(excel.get(6)));
				riskConductVisitRecordService.insertSelective(riskConductVisitRecord);

				totalRiskDetailsService.conductRiskDetails(policeId, LocalDate.parse(excel.get(6)));
			}

		} catch (Exception e) {

			e.printStackTrace();
			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

		// 添加到数据库
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(readExcel.get(0).get(0).toString() + "-" + readExcel.get(0).get(1).toString());
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
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
		System.out.println("结束");
	}
	
//	@RequestMapping("/test")
//	public void test() {
//		riskReportRecordService.family();
//	}
}
