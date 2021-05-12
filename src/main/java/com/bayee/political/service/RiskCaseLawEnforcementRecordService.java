package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.RiskCaseLawEnforcementRecord;

public interface RiskCaseLawEnforcementRecordService {

	List<RiskCaseLawEnforcementRecord> riskCaseLawEnforcementRecordList(String policeId,String dateTime);
	
	int insertSelective(RiskCaseLawEnforcementRecord record);

	int deleteByPrimaryKey(Integer id);

	int insert(RiskCaseLawEnforcementRecord record);

	RiskCaseLawEnforcementRecord selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskCaseLawEnforcementRecord record);

	/**
	 * 分页查询执法管理数据
	 * @param pageIndex
	 * @param pageSize
	 * @param type
	 * @param key
	 * @return
	 */
	List<RiskCaseLawEnforcementRecord> riskCaseLawEnforcementRecordPage(Integer pageIndex, Integer pageSize, Integer type, String key);

	/**
	 * 分页数据条数统计
	 * @param type
	 * @param key
	 * @return
	 */
	Integer riskCaseLawEnforcementRecordPageCount(Integer type, String key);

}
