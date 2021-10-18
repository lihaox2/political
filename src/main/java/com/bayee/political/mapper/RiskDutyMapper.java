package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.pojo.GlobalIndexNumResultDO;
import com.bayee.political.pojo.dto.RiskDutyReportDO;
import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskDuty;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskDutyMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RiskDuty record);

	int insertSelective(RiskDuty record);

	RiskDuty selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskDuty record);

	int updateByPrimaryKey(RiskDuty record);

	// 警员接警执勤指数查询
	RiskDuty riskDutyIndexItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime,
							   @Param("lastMonthTime") String lastMonthTime, @Param("timeType") Integer timeType);

	// 半年内接警执勤风险指数
	List<ScreenDoubeChart> riskDutyIndexChart(@Param("policeId") String policeId);

	/**
	 * 查询警员接警执勤指数数据
	 * @param policeId
	 * @param date
	 * @return
	 */
	RiskDuty findPoliceRiskDuty(@Param("policeId") String policeId, @Param("date") String date);

	/**
	 * 批量添加执勤报备数据
	 *
	 * @param riskDutyList
	 */
	void insertRiskDutys(List<RiskDuty> riskDutyList);

	/**
	 * 接警执勤-报表查询
	 * @param policeId
	 * @param dateTime
	 * @param lastMonthTime
	 * @param timeType
	 * @return
	 */
	RiskDutyReportDO dutyReportDOQuery(@Param("policeId") String policeId, @Param("dateTime") String dateTime,
									   @Param("lastMonthTime") String lastMonthTime, @Param("timeType") Integer timeType);

	/**
	 * 取得全局扣分 的最高分 - 最低分分值
	 * @param date
	 * @return
	 */
	GlobalIndexNumResultDO findGlobalIndexNum(@Param("date") String date);

	/**
	 * 取得全局扣分 的最高分 & 最低分
	 * @param lastDateTime
	 * @param date
	 * @param column
	 * @return
	 */
	GlobalIndexNumResultDO findGlobalIndexNumByYear(@Param("lastDateTime") String lastDateTime, @Param("date") String date,
													@Param("column") String column);

}