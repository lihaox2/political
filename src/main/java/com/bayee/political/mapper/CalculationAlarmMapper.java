package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.CalculationAlarm;
import com.bayee.political.domain.CalculationChart;

public interface CalculationAlarmMapper {

	// 警力测算预警新增
	int calculationAlarmCreat(CalculationAlarm record);

	// 警力测算预警详情查询
	CalculationAlarm calculationAlarmItem(@Param("id") Integer id, @Param("policeType") Integer policeType,
			@Param("policeStationId") Integer policeStationId);

	// 警力测算预警修改
	int calculationAlarmUpdate(CalculationAlarm record);

	// 警力测算预警列表查询
	List<CalculationAlarm> calculationAlarmList();

	// 警力预警趋势查询
	List<CalculationChart> calculationAlarmTrendList(@Param("policeType") Integer policeType,
			@Param("policeStationId") Integer policeStationId, @Param("isLack") Integer isLack);
	
	/**
	 * 查询最新一条记录
	 * @param policeType
	 * @param policeStationId
	 * @return
	 */
	CalculationAlarm getCalculationAlarmBaseNew(@Param("policeType") Integer policeType,
			@Param("policeStationId") Integer policeStationId);
	
	/**
	 * 随机查询两条民警或辅警超出或缺少的派出所(该派出所的最新记录)
	 * @param policeType 1民警2辅警
	 * @return
	 */
	List<CalculationAlarm> calculationRandTwo(@Param("policeType") Integer policeType);
	
	/**
	 * 所有民警或所有辅警
	 * @param policeType 1民警2辅警
	 * @return
	 */
	Integer allPoliceCount(@Param("policeType") Integer policeType);
	
	/**
	 * 查询近N天的超出或紧缺人数
	 * @param policeStationId 派出所id
	 * @param isLack 0紧缺2超出
	 * @param policeType 1民警2辅警
	 * @param day 近多少天
	 * @return
	 */
	Integer nearDayDifferPoliceNum(
			@Param("policeStationId") Integer policeStationId,
			@Param("policeType")Integer policeType, @Param("day") String day);
	
	/**
	 * 近N天紧缺或超出最大的派出所
	 * @param isLack 0紧缺2超出
	 * @param policeType 1民警2辅警
	 * @return
	 */
	CalculationAlarm maxDifferPoliceNumThirty(@Param("isLack") Integer isLack, 
			@Param("policeType") Integer policeType, @Param("day") Integer day);
	
	/**
	 * 今天紧缺或超出最大的派出所
	 * @param isLack 0紧缺2超出
	 * @param policeType 1民警2辅警
	 * @return
	 */
	CalculationAlarm maxDifferPoliceNum(@Param("isLack") Integer isLack, 
			@Param("policeType") Integer policeType);
	
	/**
	 * 查询单位最新民警/辅警实际数
	 * @param policeType
	 * @return
	 */
	List<CalculationAlarm> newPoliceStationActual(@Param("policeType") Integer policeType);
	
	/**
	 * 查询某派出所最新数据
	 * @param policeType
	 * @param policeStationId
	 * @return
	 */
	CalculationAlarm policeStationNewData(@Param("policeType") Integer policeType, 
			@Param("policeStationId") Integer policeStationId);
	
	/**
	 * 民警辅警总数
	 * @param policeType
	 * @return
	 */
	Integer sumPoliceNum(@Param("policeType") Integer policeType);
	
}