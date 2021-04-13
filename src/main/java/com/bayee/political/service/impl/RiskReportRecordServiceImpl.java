package com.bayee.political.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.bayee.political.service.RiskAlarmService;
import com.bayee.political.service.RiskHealthRecordService;
import com.bayee.political.service.RiskReportRecordService;
import com.bayee.political.service.RiskService;
import com.bayee.political.service.UserService;

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
	RiskFamilyEvaluationRecordMapper riskFamilyEvaluationRecordMapper;
	
	@Autowired
	RiskFamilyEvaluationMapper riskFamilyEvaluationMapper;


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
    public void health() {
		Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		Integer month=cal.get(Calendar.MONTH)+1;
		String yearStr=year.toString();
		String monthStr=month.toString();

		if(month<10) {
			monthStr="0"+monthStr;
		}
		
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

			if(r.getIsOverweight()==1) {
				riskHealth.setOverweightNum(0.5);
				indexNum+=0.5;
			}
			
			if(r.getIsHyperlipidemia()==1) {
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
				riskHealth.setCreationDate(new Date());
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
				riskReportRecord.setCreationDate(new Date());
				riskService.insertRiskReportRecord(riskReportRecord);
			}
			

			
			if(healthNum!=null  &&  healthNum>=3) {
				RiskAlarm riskAlarm=new RiskAlarm();
				riskAlarm.setPoliceId(r.getPoliceId());
				riskAlarm.setAlarmType(11008);
				riskAlarm.setAlarmScore(healthNum);
				riskAlarm.setCreationDate(new Date());
				riskAlarm.setIsTalk(0);
				riskAlarmService.insert(riskAlarm);
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
				riskHealth.setCreationDate(new Date());
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
				riskReportRecord.setCreationDate(new Date());
				riskService.insertRiskReportRecord(riskReportRecord);
			}
		
		}
	}
    
    
    public void family() {
    	Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		Integer month=cal.get(Calendar.MONTH)+1;
		String yearStr=year.toString();
		String monthStr=month.toString();

		if(month<10) {
			monthStr="0"+monthStr;
		}
		
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
				riskFamilyEvaluation.setCreationDate(new Date());
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
				riskReportRecord.setCreationDate(new Date());
				riskService.insertRiskReportRecord(riskReportRecord);
			}
			
			Double yeartotal=riskFamilyEvaluationMapper.findByPoliceIdOrYearTotalNum(r.getPoliceId(), yearStr);
			
			if(yeartotal>3 || indexNum==3) {
				RiskAlarm riskAlarm=new RiskAlarm();
				riskAlarm.setPoliceId(r.getPoliceId());
				riskAlarm.setAlarmType(11007);
				riskAlarm.setAlarmScore(yeartotal);
				riskAlarm.setCreationDate(new Date());
				riskAlarm.setIsTalk(0);
				riskAlarmService.insert(riskAlarm);
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
				riskFamilyEvaluation.setCreationDate(new Date());
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
				riskReportRecord.setCreationDate(new Date());
				riskService.insertRiskReportRecord(riskReportRecord);
			}
		
		}
    }
}
