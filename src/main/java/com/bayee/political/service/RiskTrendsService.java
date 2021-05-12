package com.bayee.political.service;

import java.util.List;
import java.util.Map;

import com.bayee.political.domain.RiskAlarm;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.RiskTrends;

public interface RiskTrendsService {

	int deleteByPrimaryKey(Integer id);

    int insert(RiskTrends record);

    int insertSelective(RiskTrends record);

    RiskTrends selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskTrends record);

    int updateByPrimaryKey(RiskTrends record);
    
    Integer selectRiskTotal(String startTime,String endTime);
    
    List<Map<String,Object>> selectRiskTrends();
    
    List<Map<String,Object>> selectDeptTopFive(String startTime,String endTime);
    
    List<RiskReportRecord> selectPoliceRiskTopTen(String sortName,String dateTime,String lastMonthTime,
		String orderName);
    
    List<Map<String,Object>> selectPolice(String content);
    
    
    // 综合指数风险
  	Integer comprehensiveIndex( String dateTime, String lastMonthTime);

  	// 行为规范风险
  	Integer drinkIndex( String dateTime, String lastMonthTime);

  	// 纪律作风风险
  	Integer conductIndex( String dateTime, String lastMonthTime);

  	// 执法办案风险
  	Integer caseIndex( String dateTime, String lastMonthTime);

  	// 接警执勤风险
  	Integer dutyIndex( String dateTime, String lastMonthTime);

  	// 警务技能风险
  	Integer trainIndex( String dateTime, String lastMonthTime);

  	// 学习培训风险
  	Integer studyIndex( String dateTime, String lastMonthTime);

  	// 健康风险
  	Integer healthIndex( String dateTime, String lastMonthTime);
  	
  	//各项风险指标平均数
  	List<Map<String,Object>> avgNum(String sortName);
  	
  	List<Map<String,Object>> riskPersonnelTrend(Integer alamType);
  	
  	Map<String,Object> theMonthAlamTotal();
  	
  	List<RiskAlarm> theMonthAlarm();
  	
  	String findAlarmType(Integer id);
  	
  	List<Map<String,Object>>findByPoliceIdRiskAlarm(String policeId);
  	
  	List<Map<String,Object>> continuityAlarm();
  	
  	List<Map<String,Object>> continuityAlarmDetails(String policeId);
}
