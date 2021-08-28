package com.bayee.political.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bayee.political.domain.*;
import com.bayee.political.enums.AlarmTypeEnum;
import com.bayee.political.json.ChartResult;
import com.bayee.political.pojo.dto.RiskReportRecordDO;
import com.bayee.political.service.*;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskAlarm;
import com.bayee.political.domain.RiskFamilyEvaluation;
import com.bayee.political.domain.RiskFamilyEvaluationRecord;
import com.bayee.political.domain.RiskHealth;
import com.bayee.political.domain.RiskHealthRecord;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.User;
import com.bayee.political.mapper.RiskFamilyEvaluationMapper;
import com.bayee.political.mapper.RiskFamilyEvaluationRecordMapper;
import com.bayee.political.mapper.RiskHealthRecordMapper;
import com.bayee.political.mapper.RiskReportRecordMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xxl
 * @date 2021/4/6
 */
@Service
public class RiskReportRecordServiceImpl implements RiskReportRecordService {

    @Autowired
    RiskReportRecordMapper riskReportRecordMapper;

	@Autowired
	RiskHealthRecordMapper riskHealthRecordMapper;	
	
	@Autowired
	RiskService riskService;
	
	@Autowired
	RiskHealthRecordService riskHealthRecordService;
	
	@Autowired
	RiskAlarmService riskAlarmService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RiskSkillService riskSkillService;

	@Autowired
	RiskConductBureauRuleService riskConductBureauRuleService;

	@Autowired
	RiskFamilyEvaluationRecordMapper riskFamilyEvaluationRecordMapper;
	
	@Autowired
	RiskReportRecordService riskReportRecordService;

	@Autowired
	DutyRiskService dutyRiskService;

	@Autowired
	HandlingCasesRiskService handlingCasesRiskService;

	@Autowired
	RiskConductService riskConductService;

	@Autowired
	RiskFamilyEvaluationMapper riskFamilyEvaluationMapper;

	@Autowired
	RiskSocialContactService riskSocialContactService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void policeRiskDetails(List<User> userList, LocalDate localDate) {
		String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String year = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
		String month = localDate.format(DateTimeFormatter.ofPattern("MM"));

		List<RiskReportRecord> riskReportRecordList = new ArrayList<>();
		List<RiskCase> riskCaseList = new ArrayList<>();
		List<RiskDuty> riskDutyList = new ArrayList<>();
		List<RiskConduct> riskConductList = new ArrayList<>();
		List<RiskSocialContact> riskSocialContactList = new ArrayList<>();

		for (User user : userList) {
			RiskReportRecord record = new RiskReportRecord();
			record.setPoliceId(user.getPoliceId());
			record.setYear(year);
			record.setMonth(month);
			record.setHandlingCaseNum(0d);
			record.setDutyNum(0d);
			record.setSocialContactNum(0d);
			record.setAmilyEvaluationNum(0d);
			record.setHealthNum(0d);
			record.setDrinkNum(0d);
			record.setStudyNum(0d);
			record.setConductNum(0d);
			record.setTrainNum(0d);

			//执法办案
			RiskCase riskCase = handlingCasesRiskService.handlingCasesRiskDetails(user, date);
			if (riskCase != null) {
				if (riskCase.getId() == null) {
					riskCaseList.add(riskCase);
				}
				record.setHandlingCaseNum(riskCase.getIndexNum());
			}

			//接警执勤
			RiskDuty riskDuty = dutyRiskService.dutyRiskDetails(user, date);
			if (riskDuty != null) {
				if (riskDuty.getId() == null) {
					riskDutyList.add(riskDuty);
				}
				record.setDutyNum(riskDuty.getIndexNum());
			}

			//警务技能
			RiskTrain riskTrain = riskSkillService.riskSkillDetails(user, date);
			if (riskTrain != null && riskTrain.getIndexNum() != null) {
				record.setTrainNum(riskTrain.getIndexNum());
			}

			//行为规范
			RiskConduct riskConduct = riskConductBureauRuleService.riskConductBureauRuleDetails(user, date);
			if (riskConduct != null) {
				if (riskConduct.getId() == null) {
					riskConductList.add(riskConduct);
				}
				record.setConductNum(riskConduct.getIndexNum());
			}

			//社交风险
			RiskSocialContact riskSocialContact = riskSocialContactService.riskSocialContactDetails(user, date);
			if (riskSocialContact != null) {
				if (riskSocialContact.getId() == null) {
					riskSocialContactList.add(riskSocialContact);
				}
				record.setSocialContactNum(riskSocialContact.getIndexNum());
			}

			Double healthScore = riskReportRecordMapper.findPoliceHealthScoreByYear(user.getPoliceId(), year);
			record.setHealthNum(healthScore != null ? healthScore : 0d);

			record.setTotalNum(Math.min(record.getConductNum() + record.getHandlingCaseNum() + record.getDutyNum()
					+ record.getTrainNum() + record.getSocialContactNum(), 100));
			record.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));

			//处理产生过的风险报备数据
			RiskReportRecord oldRecord = riskReportRecordService.findRiskReportRecord(user.getPoliceId(), year, month);
			if (oldRecord != null && oldRecord.getId() != null) {
				oldRecord.setTrainNum(record.getTrainNum());
				oldRecord.setConductNum(record.getConductNum());
				oldRecord.setHandlingCaseNum(record.getHandlingCaseNum());
				oldRecord.setDutyNum(record.getDutyNum());
				oldRecord.setSocialContactNum(record.getSocialContactNum());
				oldRecord.setHealthNum(oldRecord.getHealthNum() == 0 ? record.getHealthNum() : oldRecord.getHealthNum());

				oldRecord.setTotalNum(Math.min(record.getTotalNum() + oldRecord.getHealthNum() +
						oldRecord.getAmilyEvaluationNum(), 100));
				oldRecord.setUpdateDate(new Date());

				riskReportRecordService.updateByPrimaryKey(oldRecord);
			} else {
				record.setTotalNum(Math.min(record.getTotalNum() + record.getHealthNum(), 100));

				riskReportRecordList.add(record);
			}

			// 产生预警数据
			if (record.getTotalNum() >= 60) {
				RiskAlarm riskAlarm = riskAlarmService.generateRiskAlarm(user.getPoliceId(), AlarmTypeEnum.COMPREHENSIVE_RISK, date,
						record.getTotalNum());

				if (riskAlarm != null) {
					riskAlarmService.insert(riskAlarm);
				}
			}
		}

		//批量添加数据
		if (riskCaseList.size() > 0) {
			handlingCasesRiskService.addRiskCaseList(riskCaseList);
		}
		if (riskDutyList.size() > 0) {
			dutyRiskService.addRiskDutyList(riskDutyList);
		}
		if (riskConductList.size() > 0) {
			riskConductService.insertRiskConductList(riskConductList);
		}
		if (riskSocialContactList.size() > 0) {
			riskSocialContactService.addRiskSocialContactList(riskSocialContactList);
		}
		if (riskReportRecordList.size() > 0) {
			riskReportRecordService.insertRiskReportRecordList(riskReportRecordList);
		}

		health(localDate);

		family(localDate);
	}

    @Override
    public int updateByPrimaryKey(RiskReportRecord record) {
        return riskReportRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public int insert(RiskReportRecord record) {
        return riskReportRecordMapper.insert(record);
    }

    @Override
    public void insertRiskReportRecordList(List<RiskReportRecord> reportRecords) {
        riskReportRecordMapper.insertRiskReportRecords(reportRecords);
    }

    @Override
    public RiskReportRecord findRiskReportRecord(String policeId, String year, String month) {
        return riskReportRecordMapper.findRiskReportRecord(policeId, year, month);
    }

    @Override
    public RiskReportRecord getByPoliceIdMonth(String year, String month, String policeId) {
        return riskReportRecordMapper.getByPoliceIdMonth(year, month, policeId);
    }
    
    @Override
	@Transactional(rollbackFor = Exception.class)
    public void health(LocalDate localDate) {
		String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String yearStr = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
		String monthStr = localDate.format(DateTimeFormatter.ofPattern("MM"));
		
		List<RiskHealthRecord> riskHealthRecordList=riskHealthRecordMapper.selectYearAll(yearStr);
		
		for(RiskHealthRecord r:riskHealthRecordList) {
			
			Integer id=riskService.getByIdAndYear(r.getPoliceId(), yearStr);
			

			RiskReportRecord riskReportRecord= riskHealthRecordService.getByPoliceIdMonth(yearStr, monthStr, r.getPoliceId());

			if(riskReportRecord==null) {
				riskReportRecord=new RiskReportRecord();
				riskReportRecord.setYear(yearStr);
				riskReportRecord.setMonth(monthStr);
			}
			
			//警员健康风险
			RiskHealth riskHealth = new RiskHealth();

			riskHealth.setPoliceId(r.getPoliceId());

			riskHealth.setYear(yearStr);

			riskHealth.setHeight(r.getHeight());
			
			riskHealth.setWeight(r.getWeight());

			riskHealth.setBmi(r.getBmi());

			riskHealth.setBmiId(r.getBmiId());

			riskHealth.setBlood(r.getBlood());
			
			double indexNum=0;

			if(r.getBmiId()==3 ||  r.getBmiId()==4) {
				riskHealth.setOverweightNum(0.5);
				indexNum+=0.5;
			}
			
			if(r.getIsHyperlipidemia() != null && r.getIsHyperlipidemia()==1) {
				riskHealth.setHyperlipidemiaNum(0.75);
				indexNum+=0.75;
			}
			
			if(r.getIsHypertension()==1) {
				riskHealth.setHypertensionNum(0.75);
				indexNum+=0.75;
			}
			
			if(r.getIsHyperglycemia()==1) {
				riskHealth.setHyperglycemiaNum(0.75);
				indexNum+=0.75;
			}
			
			if(r.getIsHyperuricemia()==1) {
				riskHealth.setHyperuricemiaNum(0.75);
				indexNum+=0.75;
			}
			
			if(r.getIsProstate()==1) {
				riskHealth.setProstateNum(0.5);
				indexNum+=0.5;
			}
			
			if(r.getIsMajorDiseases()==1) {
				riskHealth.setMajorDiseasesNum(0.25);
				indexNum+=0.25;
			}
			
			if(r.getIsHeart()==1) {
				riskHealth.setHeartNum(0.25);
				indexNum+=0.25;
			}
			
			if(r.getIsTumorAntigen()==1) {
				riskHealth.setTumorAntigenNum(0.25);
				indexNum+=0.25;
			}
			
			if(r.getIsOrthopaedics()==1) {
				riskHealth.setOrthopaedicsNum(0.25);
				indexNum+=0.25;

			}
			riskHealth.setIndexNum(indexNum);
			
			if(id!=null) {
				riskHealth.setId(id);
				riskHealth.setUpdateDate(new Date());
				riskService.riskHealthUpdate(riskHealth);
			}else {
				riskHealth.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
				riskService.insertSelective(riskHealth);
			}

			Double healthNum=riskService.selectTotalNum(riskHealth.getId());
			riskReportRecord.setHealthNum(healthNum);
			if(riskReportRecord != null  &&  riskReportRecord.getId()!=null) {
				Double fraction= riskService.fraction(riskReportRecord.getId());
				riskReportRecord.setTotalNum(healthNum+fraction);
				riskReportRecord.setId(riskReportRecord.getId());
				riskReportRecord.setUpdateDate(new Date());
				riskService.updateRiskReportRecord(riskReportRecord);
			}else {
				riskReportRecord.setTotalNum(healthNum);
				riskReportRecord.setPoliceId(r.getPoliceId());
				riskReportRecord.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
				riskService.insertRiskReportRecord(riskReportRecord);

				//产生预警数据
				if (healthNum != null && healthNum >= 3) {
					RiskAlarm riskAlarm = riskAlarmService.generateRiskAlarm(r.getPoliceId(), AlarmTypeEnum.HEALTHY_RISK, date,
							healthNum);

					if (riskAlarm != null) {
						riskAlarmService.insert(riskAlarm);
					}
				}
			}

		}

		List<User> usersList=userService.userInfoAllList();	
		List<String> policeIds=riskService.getAllByYear(yearStr);
		for(int i=0;i<usersList.size();i++) {
			
			for(String p : policeIds) {
				
				if(usersList.get(i).getPoliceId().equals(p)) {
					usersList.remove(i);
					i--;
					break;
				}
				
			}
			
		}
		
		for(User u:usersList) {

			Integer id=riskService.getByIdAndYear(u.getPoliceId(), yearStr);
			
			RiskReportRecord riskReportRecord= riskHealthRecordService.getByPoliceIdMonth(yearStr, monthStr,u.getPoliceId());
			
			RiskHealth riskHealth = new RiskHealth();
			if(riskReportRecord==null) {
				riskReportRecord=new RiskReportRecord();
				riskReportRecord.setYear(yearStr);
				riskReportRecord.setMonth(monthStr);
			}
			
			if(id!=null) {
				riskHealth.setId(id);
				riskHealth.setYear(yearStr);
				riskHealth.setPoliceId(u.getPoliceId());
				riskHealth.setUpdateDate(new Date());
				riskHealth.setBmiId(1);
				riskService.riskHealthUpdate(riskHealth);
			}else {
				riskHealth.setYear(yearStr);
				riskHealth.setPoliceId(u.getPoliceId());
				riskHealth.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
				riskHealth.setBmiId(1);
				riskService.insertSelective(riskHealth);
			}
			
			Double healthNum=riskService.selectTotalNum(riskHealth.getId());
			riskReportRecord.setHealthNum(healthNum);
			if(riskReportRecord != null  &&  riskReportRecord.getId()!=null) {
				Double fraction= riskService.fraction(riskReportRecord.getId());
				riskReportRecord.setTotalNum(healthNum+fraction);
				riskReportRecord.setId(riskReportRecord.getId());
				riskReportRecord.setPoliceId(u.getPoliceId());
				riskReportRecord.setUpdateDate(new Date());
				riskService.updateRiskReportRecord(riskReportRecord);
			}else {
				riskReportRecord.setTotalNum(healthNum);
				riskReportRecord.setPoliceId(u.getPoliceId());
				riskReportRecord.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
				riskService.insertRiskReportRecord(riskReportRecord);
			}
		
		}
	}
    
    @Override
	@Transactional(rollbackFor = Exception.class)
    public void family(LocalDate localDate) {
		String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String yearStr = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
		String monthStr = localDate.format(DateTimeFormatter.ofPattern("MM"));
		
		List<RiskFamilyEvaluationRecord> riskFamilyEvaluationRecordList= riskFamilyEvaluationRecordMapper.findByYearAndMonth(yearStr,monthStr);
    	
		for(RiskFamilyEvaluationRecord r:riskFamilyEvaluationRecordList) {
			
			Integer id=riskFamilyEvaluationMapper.findByPoliceIdOrYearMonth(r.getPoliceId(),yearStr,monthStr);
			
			RiskReportRecord riskReportRecord= riskHealthRecordService.getByPoliceIdMonth(yearStr, monthStr, r.getPoliceId());

			if(riskReportRecord==null) {
				riskReportRecord=new RiskReportRecord();
				riskReportRecord.setYear(yearStr);
				riskReportRecord.setMonth(monthStr);
			}
			
			RiskFamilyEvaluation riskFamilyEvaluation=new RiskFamilyEvaluation();
			
			riskFamilyEvaluation.setPoliceId(r.getPoliceId());
			riskFamilyEvaluation.setYear(yearStr);
			riskFamilyEvaluation.setMonth(monthStr);
			riskFamilyEvaluation.setRankId(r.getRankId());
			Double indexNum=0.0;
			if(r.getRankId()==2) {
				indexNum=1.0;
			}else if(r.getRankId()==3) {
				indexNum=2.0;
			}else if(r.getRankId()==4) {
				indexNum=3.0;
			}
			riskFamilyEvaluation.setIndexNum(indexNum);
			
			riskFamilyEvaluation.setComment(r.getComment());
			
			if(id!=null) {
				riskFamilyEvaluation.setId(id);
				riskFamilyEvaluation.setUpdateDate(new Date());
				riskFamilyEvaluationMapper.updateByPrimaryKeySelective(riskFamilyEvaluation);
			}else {
				riskFamilyEvaluation.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
				riskFamilyEvaluationMapper.insertSelective(riskFamilyEvaluation);
			}
			
			riskReportRecord.setAmilyEvaluationNum(indexNum);;
			if(riskReportRecord != null  &&  riskReportRecord.getId()!=null) {
				Double fraction= riskReportRecordMapper.findNotFamilyTotalNum(riskReportRecord.getId());
				riskReportRecord.setTotalNum(indexNum+fraction);
				riskReportRecord.setId(riskReportRecord.getId());
				riskReportRecord.setUpdateDate(new Date());
				riskService.updateRiskReportRecord(riskReportRecord);
			}else {
				riskReportRecord.setTotalNum(indexNum);
				riskReportRecord.setPoliceId(r.getPoliceId());
				riskReportRecord.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
				riskService.insertRiskReportRecord(riskReportRecord);
			}
			
			Double yeartotal=riskFamilyEvaluationMapper.findByPoliceIdOrYearTotalNum(r.getPoliceId(), yearStr);

			//产生预警数据
			if (yeartotal>3 || indexNum==3) {
				RiskAlarm riskAlarm = riskAlarmService.generateRiskAlarm(r.getPoliceId(), AlarmTypeEnum.FAMILY_RISK, date,
						yeartotal);

				if (riskAlarm != null) {
					riskAlarmService.insert(riskAlarm);
				}
			}
			
		}
		
		List<User> usersList=userService.userInfoAllList();	
		List<String> policeIds=riskFamilyEvaluationMapper.findPoliceIdALlByYearOrMonth(yearStr, monthStr);
		for(int i=0;i<usersList.size();i++) {
			
			for(String p : policeIds) {
				
				if(usersList.get(i).getPoliceId().equals(p)) {
					usersList.remove(i);
					i--;
					break;
				}
				
			}
			
		}
		
		for(User u:usersList) {

			Integer id=riskFamilyEvaluationMapper.findByPoliceIdOrYearMonth(u.getPoliceId(),yearStr,monthStr);
			
			RiskReportRecord riskReportRecord= riskHealthRecordService.getByPoliceIdMonth(yearStr, monthStr,u.getPoliceId());
			
			RiskFamilyEvaluation riskFamilyEvaluation=new RiskFamilyEvaluation();
			if(riskReportRecord==null) {
				riskReportRecord=new RiskReportRecord();
				riskReportRecord.setYear(yearStr);
				riskReportRecord.setMonth(monthStr);
			}
			
			riskFamilyEvaluation.setYear(yearStr);
			riskFamilyEvaluation.setMonth(monthStr);
			riskFamilyEvaluation.setRankId(1);
			if(id!=null) {
				riskFamilyEvaluation.setId(id);
				riskFamilyEvaluation.setPoliceId(u.getPoliceId());
				riskFamilyEvaluation.setUpdateDate(new Date());
				riskFamilyEvaluationMapper.updateByPrimaryKeySelective(riskFamilyEvaluation);
			}else {
				riskFamilyEvaluation.setPoliceId(u.getPoliceId());
				riskFamilyEvaluation.setIndexNum(0.0);
				riskFamilyEvaluation.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
				riskFamilyEvaluationMapper.insertSelective(riskFamilyEvaluation);
			}
			
			riskReportRecord.setAmilyEvaluationNum(0.0);;
			if(riskReportRecord != null  &&  riskReportRecord.getId()!=null) {
				Double fraction= riskReportRecordMapper.findNotFamilyTotalNum(riskReportRecord.getId());
				riskReportRecord.setTotalNum(0.0+fraction);
				riskReportRecord.setId(riskReportRecord.getId());
				riskReportRecord.setUpdateDate(new Date());
				riskService.updateRiskReportRecord(riskReportRecord);
			}else {
				riskReportRecord.setTotalNum(0.0);
				riskReportRecord.setPoliceId(u.getPoliceId());
				riskReportRecord.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
				riskService.insertRiskReportRecord(riskReportRecord);
			}
		
		}
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateRiskReportRecord(RiskReportRecord riskReportRecord) {
		riskReportRecord.setUpdateDate(new Date());
		riskReportRecord.setTotalNum(Math.min(riskReportRecord.getConductNum() + riskReportRecord.getHandlingCaseNum() +
				riskReportRecord.getDutyNum() + riskReportRecord.getTrainNum() + riskReportRecord.getStudyNum() +
				riskReportRecord.getSocialContactNum() + riskReportRecord.getAmilyEvaluationNum() +
				riskReportRecord.getHealthNum() + riskReportRecord.getDrinkNum(), 100));
		riskReportRecordMapper.updateByPrimaryKey(riskReportRecord);

		// 产生预警数据
		if (riskReportRecord.getTotalNum() >= 60) {
			RiskAlarm riskAlarm = riskAlarmService.generateRiskAlarm(riskReportRecord.getPoliceId(), AlarmTypeEnum.COMPREHENSIVE_RISK,
					DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"), riskReportRecord.getTotalNum());

			if (riskAlarm != null) {
				riskAlarmService.insert(riskAlarm);
			}
		}
	}

	@Override
	public List<ChartResult> findPoliceAllRiskMonth(String police, String year) {
		return riskReportRecordMapper.findPoliceAllRiskMonth(police, year);
	}

	@Override
	public RiskReportRecordDO findRiskReportByPoliceIdToYear(String policeId, String date, String lastDate) {
		//查询近12月数据
		RiskReportRecordDO reportRecordDO1 = riskReportRecordMapper.findRiskReportByPoliceIdToYear(policeId, date, lastDate);
		//查询当前月部分数据
		RiskReportRecordDO reportRecordDO2 = riskReportRecordMapper.findRiskReportByPoliceIdToMonth(policeId, date);

		if (reportRecordDO1 == null || reportRecordDO2 == null) {
			reportRecordDO1 = new RiskReportRecordDO();
			reportRecordDO1.setTotalNum(0d);
			reportRecordDO1.setConductNum(0d);
			reportRecordDO1.setHandlingCaseNum(0d);
			reportRecordDO1.setTrainNum(0d);
			reportRecordDO1.setSocialContactNum(0d);
			reportRecordDO1.setAmilyEvaluationNum(0d);
			reportRecordDO1.setDutyNum(0d);
			reportRecordDO1.setHealthNum(0d);
			return reportRecordDO1;
		}

		reportRecordDO1.setTotalNum(reportRecordDO1.getConductNum() + reportRecordDO1.getHealthNum() +
				reportRecordDO1.getAmilyEvaluationNum() + reportRecordDO1.getHandlingCaseNum() +
				reportRecordDO1.getDutyNum() + reportRecordDO1.getSocialContactNum() + reportRecordDO1.getTrainNum());
		//数据融合
		reportRecordDO1.setCreationDate(reportRecordDO2.getCreationDate());
		reportRecordDO1.setLastMonthScore(reportRecordDO2.getLastMonthScore());
		reportRecordDO1.setThisMonthScore(reportRecordDO2.getThisMonthScore());
		reportRecordDO1.setQoq(reportRecordDO2.getQoq());
		reportRecordDO1.setHealthDesc(reportRecordDO2.getHealthDesc());

		return reportRecordDO1;
	}
}
