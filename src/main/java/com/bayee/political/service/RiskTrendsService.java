package com.bayee.political.service;

import java.util.List;
import java.util.Map;

import com.bayee.political.domain.RiskAlarm;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.RiskTrends;
import com.bayee.political.json.ChartResult;
import com.bayee.political.pojo.dto.RiskAlarmTypeDO;
import org.apache.ibatis.annotations.Param;

public interface RiskTrendsService {

	int deleteByPrimaryKey(Integer id);

    int insert(RiskTrends record);

    int insertSelective(RiskTrends record);

    RiskTrends selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskTrends record);

    int updateByPrimaryKey(RiskTrends record);
    
    Integer selectRiskTotal(String startTime,String endTime);

	/**
	 * 查询本年度存在风险的人数
	 * @param year 年份
	 * @return
	 */
	Integer selectRiskTotals(String year);
    
    List<Map<String,Object>> selectRiskTrends();
    
    List<Map<String,Object>> selectDeptTopFive(String startTime,String endTime);
    
    List<RiskReportRecord> selectPoliceRiskTopTen(String sortName,String dateTime,String lastMonthTime,
		String orderName);
    
    List<Map<String,Object>> selectPolice(String content);
    
    
    // 综合指数风险
  	Integer comprehensiveIndex( String dateTime, String lastMonthTime);

	//新综合指数风险
	public Integer comprehensiveIndexs();

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

  	//各项风险指标风险数
	Integer VariousRisks(Integer type);
  	//各项风险指标平均数
  	List<Map<String,Object>> avgNum(String sortName);
  	
  	List<Map<String,Object>> riskPersonnelTrend(Integer alamType);
  	
  	Map<String,Object> theMonthAlamTotal();
  	
  	List<RiskAlarm> theMonthAlarm();
  	
  	String findAlarmType(Integer id);
  	
  	List<Map<String,Object>>findByPoliceIdRiskAlarm(String policeId);
  	
  	List<Map<String,Object>> continuityAlarm();
  	
  	List<Map<String,Object>> continuityAlarmDetails(String policeId);

	/**
	 * 查询近12个月的风险人数总和，按警号去重
	 * @return
	 */
	Integer countRiskAlarmByPolice();

	/**
	 * 获取预警类型
	 * @return
	 */
	List<RiskAlarmTypeDO> getRiskAlarmType();

	/**
	 * 根据风险类型查询 该类型下的所有人
	 * @param alarmType
	 * @return
	 */
	List<RiskAlarm> theMonthAlarm(@Param("alarmType") Integer alarmType);
	
	Integer selectIndexRiskTotal();
	
	Integer selectTheMonthRiskTotal(String month);
	
	List<Map<String,Object>> caseLawTrends();
	
	Integer caseLawPepolNum();
	
	Integer caseLawThisMonthNum();

	Integer caseLawRepeatNum();
	
	
	List<Map<String,Object>> dutyDealTrends();
	
	Integer dutyDealPepolNum();
	
	Integer dutyDealThisMonthNum();
	
	Integer dutyDealRepeatNum();
	
	Map<String,Object> ishealth();
	
	Map<String,Object> nohealth();
	
	Integer inspectNums();
	
	Integer healthNum();
	
	Integer healthRiskNum();
	
	Integer qualifiedNum();
	
	Double qualifiedRate();
	
	List<Map<String,Object>> qualifiedRateEcharts();

	/**
	 * 警员风险下钻
	 * @param date
	 * @return
	 */
	List<ChartResult> riskDeptAlarmChart(String date);

	/**
	 * 执法办案下钻
	 * @param date
	 * @return
	 */
	List<ChartResult> caseDeptChart(String date);

	/**
	 * 接警执勤下钻
	 * @param date
	 * @return
	 */
	List<ChartResult> dutyDeptChart(String date);

	/**
	 * 综合训练下钻
	 * @param id
	 * @return
	 */
	List<ChartResult> physicalTrainDeptChart(Integer id);

	/**
	 * 枪械训练下钻
	 * @param id
	 * @return
	 */
	List<ChartResult> firearmTrainDeptChart(Integer id);

	/**
	 * 健康风险下钻
	 * @param key
	 * @return
	 */
	List<ChartResult> healthAlarmDeptChart(String key);

	/**
	 * 新本月新增预警人数
	 * @return
	 */
	Integer newTheMonthAlamTotal();

	/**
	 * 较上月新增预警人数
	 * @return
	 */
	Integer comparedWithLastMonthAlamTotal();
}
