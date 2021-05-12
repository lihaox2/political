package com.bayee.political.service;


import com.bayee.political.domain.RiskCaseAbilityRecord;

import java.util.List;

public interface RiskCaseAbilityRecordService {
	
	Integer getByYearAndPoliceId(String year,String policeId);
	
	int updateByPrimaryKeySelective(RiskCaseAbilityRecord record);
	
	int insertSelective(RiskCaseAbilityRecord record);

	int deleteByPrimaryKey(Integer id);

	RiskCaseAbilityRecord selectByPrimaryKey(Integer id);

	/**
	 * 分页查询执法能力数据
	 * @param pageIndex
	 * @param pageSize
	 * @param columnList
	 * @param typeFlag
	 * @param key
	 * @return
	 */
	List<RiskCaseAbilityRecord> riskCaseAbilityRecordPage(Integer pageIndex, Integer pageSize,
														  List<String> columnList, Integer typeFlag, String key);

	/**
	 * 统计分页数据条数
	 * @param columnList
	 * @param typeFlag
	 * @param key
	 * @return
	 */
	Integer getRiskCaseAbilityRecordPageCount(List<String> columnList, Integer typeFlag, String key);

}
