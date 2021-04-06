package com.bayee.political.mapper;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.CalculationPolicePost;

public interface CalculationPolicePostMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CalculationPolicePost record);

	int insertSelective(CalculationPolicePost record);

	CalculationPolicePost selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CalculationPolicePost record);

	int updateByPrimaryKey(CalculationPolicePost record);

	/**
	 * 根据id修改总数
	 */
	int calculationPolicePostUpdateByid(@Param("id") Integer id, @Param("sum") Integer sum);

	// 查询当前岗位数量
	CalculationPolicePost calculationPolicePostNumItem(@Param("policeType") Integer policeType,
			@Param("stationPostId") Integer stationPostId);

	// 根据岗位id和民警类型type修改
	int calculationPolicePostUpdate(CalculationPolicePost record);

}