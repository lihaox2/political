package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.domain.DrinkRecord;

public interface DrinkRecordMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(DrinkRecord record);

	int insertSelective(DrinkRecord record);

	DrinkRecord selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(DrinkRecord record);

	int updateByPrimaryKey(DrinkRecord record);

	// 警员喝酒记录查询
	List<DrinkRecord> drinkList();
}