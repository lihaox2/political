package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.pojo.dto.DutyDetailsDO;
import com.bayee.political.pojo.dto.DutyPageDO;
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
	 * @param informationId
	 * @param errorId
	 * @param key
	 * @return
	 */
	List<DutyPageDO> riskDutyDealPoliceRecordPage(@Param("pageIndex") Integer pageIndex,
												  @Param("pageSize") Integer pageSize,
												  @Param("informationId") Integer informationId,
												  @Param("errorId") Integer errorId,
												  @Param("key") String key,
												  @Param("deptId") Integer deptId);

	/**
	 * 统计分页数据数据条数
	 * @param informationId
	 * @param errorId
	 * @param key
	 * @return
	 */
	Integer riskDutyDealPoliceRecordPageCount(@Param("informationId") Integer informationId,
											  @Param("errorId") Integer errorId, @Param("key") String key,
											  @Param("deptId") Integer deptId);

	/**
	 * 通过id查询接警执勤详情
	 * @param id
	 * @return
	 */
	DutyDetailsDO findById(@Param("id") Integer id);

	/**
	 * 统计所有数据条数
	 * @return
	 */
	Integer countAll();

	/**
	 * 获取重复次数
	 * @param policeId
	 * @param type
	 * @return
	 */
	Integer getReplaceErrorCount(@Param("policeId") String policeId,@Param("type") Integer type);

}