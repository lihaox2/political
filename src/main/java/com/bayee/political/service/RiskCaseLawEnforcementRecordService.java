package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.RiskCaseLawEnforcementRecord;
import com.bayee.political.pojo.dto.CaseLawEnforcementDetailsDO;
import com.bayee.political.pojo.dto.CaseLawEnforcementPageDO;
import org.apache.ibatis.annotations.Param;

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
	List<CaseLawEnforcementPageDO> riskCaseLawEnforcementRecordPage(Integer pageIndex, Integer pageSize, String type,
																	String key, Integer deptId);

	/**
	 * 分页数据条数统计
	 * @param type
	 * @param key
	 * @return
	 */
	Integer riskCaseLawEnforcementRecordPageCount(String type, String key, Integer deptId);

	/**
	 * 通过id查询执法管理数据
	 * @param id
	 * @return
	 */
	CaseLawEnforcementDetailsDO findById(Integer id);

	/**
	 * 统计所有执法管理数据
	 * @return
	 */
	Integer countAll();

	/**
	 * 查询警员重复执法管理数
	 * @param policeId
	 * @param type
	 * @return
	 */
	Integer getPoliceReplaceErrorCount(String policeId, Integer type);

	/**
	 * 根据年份和月份进行查询
	 * @param caseLawEnforcementRecordYear
	 * @param caseLawEnforcementRecordMonth
	 * @param policeId
	 * @return
	 */
	List<RiskCaseLawEnforcementRecord> findCaseLawEnforcementRecordYearAndMonth(
			@Param("caseLawEnforcementRecordYear") String caseLawEnforcementRecordYear,
			@Param("caseLawEnforcementRecordMonth") String caseLawEnforcementRecordMonth,
			@Param("policeId") String policeId
	);

	/**
	 * 检查警员执法管理扣分情况
	 * @param policeId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	boolean checkPoliceDeductionScoreStatus(String policeId, String beginDate, String endDate);
}
