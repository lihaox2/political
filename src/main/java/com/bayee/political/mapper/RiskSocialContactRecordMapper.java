package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskSocialContactRecord;

public interface RiskSocialContactRecordMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RiskSocialContactRecord record);

	int insertSelective(RiskSocialContactRecord record);

	RiskSocialContactRecord selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskSocialContactRecord record);

	int updateByPrimaryKey(RiskSocialContactRecord record);

	// 社交详情记录
	List<RiskSocialContactRecord> riskSocialContactRecordList(@Param("socialContactId") Integer socialContactId);

	/**
	 * 查询警员社交详情
	 * @param policeId
	 * @param date
	 * @return
	 */
	List<RiskSocialContactRecord> findPoliceRiskSocialContactRecordList(@Param("policeId") String policeId,@Param("date") String date);

}