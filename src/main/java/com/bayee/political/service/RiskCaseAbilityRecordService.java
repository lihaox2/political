package com.bayee.political.service;


import com.bayee.political.domain.RiskCaseAbilityRecord;
import org.apache.ibatis.annotations.Param;

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
	 * @param date
	 * @return
	 */
	List<RiskCaseAbilityRecord> riskCaseAbilityRecordPage(Integer pageIndex, Integer pageSize,
														  List<String> columnList, Integer typeFlag,
														  String key, String date, Integer deptId);

	/**
	 * 统计分页数据条数
	 * @param columnList
	 * @param typeFlag
	 * @param key
	 * @param date
	 * @return
	 */
	Integer getRiskCaseAbilityRecordPageCount(List<String> columnList, Integer typeFlag, String key, String date, Integer deptId);

	/**
	 * 根据时间和警号查询id
	 * @param date
	 * @param policeId
	 * @return
	 */
	Integer getIdByDateAndPoliceId(String date, String policeId, Integer id);

}
