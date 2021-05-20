package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.RiskCaseLawEnforcementRecord;
import com.bayee.political.pojo.dto.CaseLawEnforcementDetailsDO;
import com.bayee.political.pojo.dto.CaseLawEnforcementPageDO;

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
	List<CaseLawEnforcementPageDO> riskCaseLawEnforcementRecordPage(Integer pageIndex, Integer pageSize, String type, String key);

	/**
	 * 分页数据条数统计
	 * @param type
	 * @param key
	 * @return
	 */
	Integer riskCaseLawEnforcementRecordPageCount(String type, String key);

	/**
	 * 通过id查询执法管理数据
	 * @param id
	 * @return
	 */
	CaseLawEnforcementDetailsDO findById(Integer id);

}
