package com.bayee.political.mapper;

import java.util.List;
import java.util.Map;

import com.bayee.political.json.ChartResult;
import com.bayee.political.pojo.dto.RiskAlarmTypeDO;
import io.swagger.models.auth.In;
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

	/**
	 * 查询本年度存在风险的人数
	 * @param year
	 * @return
	 */
	Integer selectRiskTotals(@Param("year")String year);
    
    List<Map<String,Object>> selectRiskTrends();
    
//    List<Map<String,Object>> selectDeptTopFive(@Param("startTime") String startTime,@Param("endTime") String endTime);

	List<Map<String,Object>> selectDeptTopFive();
    List<RiskReportRecord> selectPoliceRiskTopTen(
			@Param("sortName") String sortName, @Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime,
			@Param("orderName") String orderName);
    
    
    List<Map<String,Object>> selectPolice(@Param("content")String content);

    //新综合指数风险
    Integer comprehensiveIndexs();
    
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

 	//各项风险人数
	Integer VariousRisks(@Param("type")Integer type);
 	//各项风险指标平均数
 	List<Map<String,Object>> avgNum(@Param(value="sortName")String sortName);
 	
 	//
 	List<Map<String,Object>> riskPersonnelTrend(@Param(value="alamType")Integer alamType);
 	
 	Map<String,Object> theMonthAlamTotal();

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

	/**
	 * 获取预警类型
	 * @return
	 */
	List<RiskAlarmTypeDO> getRiskAlarmType();
	
	Integer selectIndexRiskTotal();
	
	Integer selectTheMonthRiskTotal(@Param("month")String month);
	
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
	
	Integer inspectNum();
	
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

}