package com.bayee.political.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.bayee.political.domain.RiskCaseAbilityRecord;
import com.bayee.political.domain.RiskCaseLawEnforcementRecord;
import com.bayee.political.domain.RiskCaseTestRecord;
import com.bayee.political.domain.RiskDrinkRecord;
import com.bayee.political.domain.RiskDutyDealPoliceRecord;
import com.bayee.political.domain.RiskHealth;
import com.bayee.political.domain.RiskHealthRecord;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.RiskStutyActivitiesPartyRecord;
import com.bayee.political.domain.RiskStutyPalmSchoolRecord;
import com.bayee.political.domain.RiskStutyUnitTrainRecord;
import com.bayee.political.mapper.RiskDrinkRecordMapper;
import com.bayee.political.service.RiskAlarmService;
import com.bayee.political.service.RiskCaseAbilityRecordService;
import com.bayee.political.service.RiskCaseLawEnforcementRecordService;
import com.bayee.political.service.RiskCaseTestRecordService;
import com.bayee.political.service.RiskDutyDealPoliceRecordService;
import com.bayee.political.service.RiskHealthRecordService;
import com.bayee.political.service.RiskService;
import com.bayee.political.service.RiskStutyActivitiesPartyRecordService;
import com.bayee.political.service.RiskStutyPalmSchoolRecordService;
import com.bayee.political.service.RiskStutyUnitTrainRecordService;
import com.bayee.political.service.UserService;
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
				
				Integer riskHealthRecordId=riskHealthRecordService.getByIdAndYear(excel.get(5), yearStr);
				// 累计错误行
				index++;
				// 警员体检
				RiskHealthRecord riskHealthRecord = new RiskHealthRecord();
				
				
				riskHealthRecord.setPoliceId(excel.get(5));
				
				
				riskHealthRecord.setYear(yearStr);
				
				
				if(excel.get(6).indexOf("\\")==-1) {
					riskHealthRecord.setHeight(Double.valueOf(excel.get(6)));
				}
				if(excel.get(7).indexOf("\\")==-1) {
					riskHealthRecord.setWeight(Double.valueOf(excel.get(7)));
				}
				
				if(excel.get(6).indexOf("\\")==-1 &&  excel.get(7).indexOf("\\")==-1) {

					Double bmi=Double.valueOf(excel.get(7))/Math.pow(Double.valueOf(excel.get(6))/100,2);
					riskHealthRecord.setBmi(bmi);
					if(bmi<=18.40) {
						riskHealthRecord.setBmiId(1);
					}else if(bmi>=18.50 && bmi<=23.9) {
						riskHealthRecord.setBmiId(2);
					}else if(bmi>=24.00 && bmi<=27.9) {
						riskHealthRecord.setBmiId(3);
					}else if(bmi>=28 && bmi<=100.00) {
						riskHealthRecord.setBmiId(4);
					}
				}
				
				riskHealthRecord.setBlood(excel.get(8));
				
				
				if(!excel.get(9).isEmpty()) {
					riskHealthRecord.setIsOverweight(1);
				}
				
				if(!excel.get(10).isEmpty()) {
					riskHealthRecord.setIsHyperlipidemia(1);
				}
				
				if(!excel.get(11).isEmpty()) {
					riskHealthRecord.setIsHypertension(1);
				}
				
				if(!excel.get(12).isEmpty()) {
					riskHealthRecord.setIsHyperglycemia(1);
				}
				
				if(!excel.get(13).isEmpty()) {
					riskHealthRecord.setIsHyperuricemia(1);
				}
				
				if(!excel.get(14).isEmpty()) {
					riskHealthRecord.setIsProstate(1);
				}
				
				if(!excel.get(15).isEmpty()) {
					riskHealthRecord.setIsMajorDiseases(1);
				}
				riskHealthRecord.setMajorDiseasesDescribe(excel.get(15));
				if(!excel.get(16).isEmpty()) {
					riskHealthRecord.setIsHeart(1);
				}
				riskHealthRecord.setHeartDescribe(excel.get(16));
				if(!excel.get(17).isEmpty()) {
					riskHealthRecord.setIsTumorAntigen(1);
				}
				riskHealthRecord.setTumorAntigenDescribe(excel.get(17));
				if(!excel.get(18).isEmpty()) {
					riskHealthRecord.setIsOrthopaedics(1);
				}
				riskHealthRecord.setOrthopaedicsDescribe(excel.get(18));
				
				
				
				if(riskHealthRecordId!=null) {
					riskHealthRecord.setId(riskHealthRecordId);
					riskHealthRecord.setUpdateDate(new Date());
					riskHealthRecordService.updateByPrimaryKeySelective(riskHealthRecord);
				}else {
					riskHealthRecord.setCreationDate(new Date());
					riskHealthRecordService.insertSelective(riskHealthRecord);
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
		//readExcel.remove(0);
		// 记录错误行
		int index = 0;

		try {

			for (List<String> excel : readExcel) {
				
				if(excel.get(2).equals("jcj")) {
					RiskDutyDealPoliceRecord riskDutyDealPoliceRecord=new RiskDutyDealPoliceRecord();
					
					riskDutyDealPoliceRecord.setPoliceId(excel.get(0));
					riskDutyDealPoliceRecord.setType(12001);
					riskDutyDealPoliceRecord.setContent(excel.get(3));
					
					riskDutyDealPoliceRecord.setInputTime(DateUtils.toDate2(excel.get(4)));
					riskDutyDealPoliceRecord.setDeductionScore(Double.valueOf(excel.get(5)));
					riskDutyDealPoliceRecord.setCreationDate(DateUtils.toDate2(excel.get(4)));
					
					riskDutyDealPoliceRecordService.insertSelective(riskDutyDealPoliceRecord);
					
				}else {
					RiskCaseLawEnforcementRecord riskCaseLawEnforcementRecord=new RiskCaseLawEnforcementRecord();
					
					riskCaseLawEnforcementRecord.setPoliceId(excel.get(0));
					riskCaseLawEnforcementRecord.setContent(excel.get(3));
					
					riskCaseLawEnforcementRecord.setInputTime(DateUtils.toDate2(excel.get(4)));
					riskCaseLawEnforcementRecord.setDeductionScore(Double.valueOf(excel.get(5)));
					riskCaseLawEnforcementRecord.setCreationDate(DateUtils.toDate2(excel.get(4)));
					
					if(excel.get(2).equals("xtsy")) {
						riskCaseLawEnforcementRecord.setType(12002);
					}else if(excel.get(2).equals("sawp")) {
						riskCaseLawEnforcementRecord.setType(12003);
					}else if(excel.get(2).equals("baq")) {
						riskCaseLawEnforcementRecord.setType(12004);
					}else if(excel.get(2).equals("wsba")) {
						riskCaseLawEnforcementRecord.setType(12005);
					}
					
					riskCaseLawEnforcementRecordService.insertSelective(riskCaseLawEnforcementRecord);
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
				RiskCaseTestRecord riskCaseTestRecord = new RiskCaseTestRecord();
				
				
				riskCaseTestRecord.setYear(excel.get(0));
				//riskCaseTestRecord.setType(excel.get(1));
				riskCaseTestRecord.setSemester(Double.valueOf(excel.get(1)).intValue());
				riskCaseTestRecord.setIndexNum(0.0);
				riskCaseTestRecord.setName(excel.get(2));
				riskCaseTestRecord.setPoliceId(excel.get(4));
				riskCaseTestRecord.setScore(Double.valueOf(excel.get(5)));
				

				Integer id=riskCaseTestRecordService.isExistence(excel.get(4), excel.get(0), Double.valueOf(excel.get(1)).intValue());
				
				if(id!=null) {
					riskCaseTestRecord.setId(id);
					riskCaseTestRecord.setUpdateDate(new Date());
					riskCaseTestRecordService.updateByPrimaryKeySelective(riskCaseTestRecord);
				}else {
					riskCaseTestRecord.setCreationDate(new Date());
					riskCaseTestRecordService.insertTest(riskCaseTestRecord);
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
				riskCaseAbilityRecord.setYear(excel.get(0));
				riskCaseAbilityRecord.setPoliceId(excel.get(2));
				riskCaseAbilityRecord.setReconsiderationLitigationStatus(Double.valueOf(excel.get(3)).intValue());
				riskCaseAbilityRecord.setLetterVisitStatus(Double.valueOf(excel.get(4)).intValue());
				riskCaseAbilityRecord.setLawEnforcementFaultStatus(Double.valueOf(excel.get(5)).intValue());
				riskCaseAbilityRecord.setJudicialSupervisionStatus(Double.valueOf(excel.get(6)).intValue());
				riskCaseAbilityRecord.setCaseExpertStatus(Double.valueOf(excel.get(7)).intValue());
				riskCaseAbilityRecord.setExcellentLegalOfficerStatus(Double.valueOf(excel.get(8)).intValue());
				riskCaseAbilityRecord.setBasicTestStatus(Double.valueOf(excel.get(9)).intValue());
				riskCaseAbilityRecord.setHighTestStatus(Double.valueOf(excel.get(10)).intValue());
				riskCaseAbilityRecord.setJudicialTestStatus(Double.valueOf(excel.get(11)).intValue());
				Integer id=riskCaseAbilityRecordService.getByYearAndPoliceId(excel.get(0),excel.get(2));
				
				if(id!=null) {
					riskCaseAbilityRecord.setId(id);
					riskCaseAbilityRecord.setUpdateDate(new Date());
					riskCaseAbilityRecordService.updateByPrimaryKeySelective(riskCaseAbilityRecord);
				}else {
					riskCaseAbilityRecord.setCreationDate(DateUtils.toDate2(excel.get(0)));
					riskCaseAbilityRecordService.insertSelective(riskCaseAbilityRecord);
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
}
