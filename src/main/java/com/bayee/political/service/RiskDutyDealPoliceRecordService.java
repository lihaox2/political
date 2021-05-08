package com.bayee.political.service;

import com.bayee.political.domain.RiskDutyDealPoliceRecord;

import java.util.List;

public interface RiskDutyDealPoliceRecordService {

	int insertSelective(RiskDutyDealPoliceRecord record);

	int deleteByPrimaryKey(Integer id);

	int insert(RiskDutyDealPoliceRecord record);

	RiskDutyDealPoliceRecord selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskDutyDealPoliceRecord record);

	/**
	 * 分页查询接警执勤信息
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<RiskDutyDealPoliceRecord> riskDutyDealPoliceRecordPage(Integer pageIndex, Integer pageSize);

	/**
	 * 统计分页数据数据条数
	 * @return
	 */
	Integer riskDutyDealPoliceRecordPageCount();

}
