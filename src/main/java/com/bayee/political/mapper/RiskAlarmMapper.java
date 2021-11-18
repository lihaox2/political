package com.bayee.political.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskAlarm;

public interface RiskAlarmMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RiskAlarm record);

	int insertSelective(RiskAlarm record);

	RiskAlarm selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskAlarm record);

	int updateByPrimaryKey(RiskAlarm record);

	// 警员预警分页查询
	List<RiskAlarm> riskAlarmPageList(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum,@Param("dateTime") String dateTime);

	// 警员预警列表总数
	int riskAlarmPageCount(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("dateTime") String dateTime);

	/**
	 * 查询本月已产生的预警数据
	 * 
	 * @param policeId 禁用id
	 * @param type     预警类型
	 * @param date     预警时间
	 * @return
	 */
	RiskAlarm findByPoliceIdAndTypeAndDate(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("date") String date);

	/**
	 * 批量添加预警数据
	 * 
	 * @param riskAlarmList
	 */
	void insertRiskAlarms(List<RiskAlarm> riskAlarmList);

	// 警员预警新增
	int riskAlarmCreat(RiskAlarm item);
	
	List<RiskAlarm> theMonthAlarm();

	String findAlarmType(Integer id);
	
	List<Map<String,Object>> findByPoliceIdRiskAlarm(String policeId);
	
	List<Map<String,Object>> continuityAlarm();

	List<Map<String,Object>> continuityAlarmDataList(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	List<Map<String,Object>> continuityAlarmDetails(String policeId);

	/**
	 * 查询近12个月的风险人数总和，按警号去重
	 * @return
	 */
	Integer countRiskAlarmByPolice();

	/**
	 * 根据风险类型查询 该类型下的所有人
	 * @param alarmType
	 * @return
	 */
	List<RiskAlarm> theMonthAlarmByAlarmType(@Param("alarmType") Integer alarmType);

}