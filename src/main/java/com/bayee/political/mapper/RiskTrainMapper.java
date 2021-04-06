package com.bayee.political.mapper;

import java.util.List;

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
	RiskTrain riskTrainIndexItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime);

	// 警员综合训练不合格趋势图
	List<ScreenChart> riskTrainFailChart(@Param("policeId") String policeId, @Param("fieldName") String fieldName);

	// 警员警务技能统计查询
	RiskTrain riskTrainStatisticsItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime);

}