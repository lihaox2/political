package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.domain.TrainFirearm;
import com.bayee.political.domain.TrainPhysical;
import com.bayee.political.pojo.GlobalIndexNumResultDO;
import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskTrain;
import com.bayee.political.domain.ScreenChart;

public interface RiskTrainMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RiskTrain record);

	int insertSelective(RiskTrain record);

	RiskTrain selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskTrain record);

	int updateByPrimaryKey(RiskTrain record);

	// 警员警务技能新增
	int riskTrainCreat(RiskTrain record);

	// 警员警务技能修改
	int riskTrainUpdate(RiskTrain record);

	// 警员警务技能指数查询
	RiskTrain riskTrainIndexItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime,
			@Param("lastMonthTime") String lastMonthTime, @Param("timeType") Integer timeType);

	// 警员综合训练不合格趋势图
	List<ScreenChart> riskTrainFailChart(@Param("policeId") String policeId, @Param("fieldName") String fieldName);

	// 警员警务技能统计查询
	RiskTrain riskTrainStatisticsItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime);

	/**
	 * 批量添加警务技能计算数据
	 * @param riskTrains
	 */
	void insertRiskTrainList(List<RiskTrain> riskTrains);
	
	/**
	 *
	 * @param policeId
	 * @param date
	 * @return
	 */
	RiskTrain findRiskTrainByPoliceIdAndDate(@Param("policeId") String policeId, @Param("date") String date);

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