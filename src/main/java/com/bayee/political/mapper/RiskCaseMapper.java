package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.pojo.GlobalIndexNumResultDO;
import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskCase;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskCaseMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RiskCase record);

	int insertSelective(RiskCase record);

	RiskCase selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskCase record);

	int updateByPrimaryKey(RiskCase record);

	// 警员执法办案风险指数查询
	RiskCase riskCaseIndexItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime,
							   @Param("lastMonthTime") String lastMonthTime, @Param("timeType") Integer timeType);

	// 执法办案风险指数图例
	List<ScreenDoubeChart> riskCaseIndexChart(@Param("policeId") String policeId);

	/**
	 * 查询警员办案风险指数数据
	 * @param policeId
	 * @param date
	 * @return
	 */
	RiskCase findPoliceRiskCase(@Param("policeId") String policeId, @Param("date") String date);

	/**
	 * 批量添加执法报备数据
	 *
	 * @param riskCaseList
	 */
	void insertRiskCases(List<RiskCase> riskCaseList);

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