package com.bayee.political.mapper;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.CalculationPoliceStation;

public interface CalculationPoliceStationMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CalculationPoliceStation record);

	int insertSelective(CalculationPoliceStation record);

	CalculationPoliceStation selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CalculationPoliceStation record);

	int updateByPrimaryKey(CalculationPoliceStation record);

	/**
	 * 根据id修改总数
	 */
	int CalculationPoliceStationUpdateByid(@Param("id") Integer id, @Param("sum") Integer sum);

	/**
	 * 根据派出所id及警员类型查询总数
	 */
	CalculationPoliceStation getAllByPoliceIdType(@Param("policeId") Integer policeId,
			@Param("policeType") Integer policeType);

	// 根据派出所id和policeType修改
	int pStationUpdate(CalculationPoliceStation pStation);

}