package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskDutyDealPoliceRecord;

public interface RiskDutyDealPoliceRecordMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RiskDutyDealPoliceRecord record);

	int insertSelective(RiskDutyDealPoliceRecord record);

	RiskDutyDealPoliceRecord selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskDutyDealPoliceRecord record);

	int updateByPrimaryKey(RiskDutyDealPoliceRecord record);

	// 警员接警执勤数据列表查询
	List<RiskDutyDealPoliceRecord> riskDutyRecordList(@Param("policeId") String policeId,
													  @Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime,
													  @Param("timeType") Integer timeType);

	/**
	 * 查询
	 * @param policeId
	 * @param date
	 * @return
	 */
	List<RiskDutyDealPoliceRecord> findRiskDutyDealPoliceRecordList(@Param("policeId") String policeId, @Param("date") String date);

	/**
	 * 统计警员办案量
	 *
	 * @param policeId
	 * @param date
	 * @return
	 */
	Integer countPoliceCaseData(@Param("policeId") String policeId, @Param("date") String date);


	/**
	 * 查询某个月警员的平均扣分
	 *
	 * @param date
	 * @return
	 */
	Double findPoliceAvgDeductionScoreByDate(@Param("date") String date);

	/**
	 * 分页查询接警执勤信息
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<RiskDutyDealPoliceRecord> riskDutyDealPoliceRecordPage(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize
			,@Param("type") Integer type,@Param("key") String key);

	/**
	 * 统计分页数据数据条数
	 * @return
	 */
	Integer riskDutyDealPoliceRecordPageCount(@Param("type") Integer type,@Param("key") String key);

}