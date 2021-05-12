package com.bayee.political.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.RiskTrends;

public interface RiskTrendsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskTrends record);

    int insertSelective(RiskTrends record);

    RiskTrends selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskTrends record);

    int updateByPrimaryKey(RiskTrends record);
    
    Integer selectRiskTotal(@Param("startTime") String startTime,@Param("endTime") String endTime);
    
    List<Map<String,Object>> selectRiskTrends();
    
    List<Map<String,Object>> selectDeptTopFive(@Param("startTime") String startTime,@Param("endTime") String endTime);
    
    List<RiskReportRecord> selectPoliceRiskTopTen(
			@Param("sortName") String sortName, @Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime,
			@Param("orderName") String orderName);
    
    
    List<Map<String,Object>> selectPolice(@Param("content")String content);
    
    
    
    // 综合指数风险
 	Integer comprehensiveIndex(@Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime);

 	// 行为规范风险
 	Integer drinkIndex(@Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime);

 	// 纪律作风风险
 	Integer conductIndex(@Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime);

 	// 执法办案风险
 	Integer caseIndex(@Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime);

 	// 接警执勤风险
 	Integer dutyIndex(@Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime);

 	// 警务技能风险
 	Integer trainIndex(@Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime);

 	// 学习培训风险
 	Integer studyIndex(@Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime);

 	// 健康风险
 	Integer healthIndex(@Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime);
 	
 	//各项风险指标平均数
 	List<Map<String,Object>> avgNum(@Param(value="sortName")String sortName);
 	
 	//
 	List<Map<String,Object>> riskPersonnelTrend(@Param(value="alamType")Integer alamType);
 	
 	Map<String,Object> theMonthAlamTotal();
}