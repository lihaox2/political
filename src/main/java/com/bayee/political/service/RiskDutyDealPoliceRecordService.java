package com.bayee.political.service;

import com.bayee.political.domain.RiskDutyDealPoliceRecord;
import com.bayee.political.pojo.dto.DutyDetailsDO;
import com.bayee.political.pojo.dto.DutyPageDO;

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
	 * @param informationId
	 * @param errorId
	 * @param key
	 * @return
	 */
	List<DutyPageDO> riskDutyDealPoliceRecordPage(Integer pageIndex, Integer pageSize,
												  Integer informationId, Integer errorId, String key);

	/**
	 * 统计分页数据数据条数
	 * @param informationId
	 * @param errorId
	 * @param key
	 * @return
	 */
	Integer riskDutyDealPoliceRecordPageCount(Integer informationId, Integer errorId, String key);

	/**
	 * 通过id查询接警执勤详情
	 * @param id
	 * @return
	 */
	DutyDetailsDO findById(Integer id);

}
