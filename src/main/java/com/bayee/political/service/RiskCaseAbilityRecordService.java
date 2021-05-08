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
	 * @return
	 */
	List<RiskCaseAbilityRecord> riskCaseAbilityRecordPage(Integer pageIndex, Integer pageSize);

	/**
	 * 统计数据条数
	 * @return
	 */
	Integer getRiskCaseAbilityRecordPageCount();

}
