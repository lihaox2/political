package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskSocialContact;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskSocialContactMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RiskSocialContact record);

	int insertSelective(RiskSocialContact record);

	RiskSocialContact selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskSocialContact record);

	int updateByPrimaryKey(RiskSocialContact record);

	// 警员社交风险查询
	RiskSocialContact riskSocialContactIndexItem(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime,
			@Param("lastMonthTime") String lastMonthTime, @Param("timeType") Integer timeType);

	// 社交风险指数图例
	List<ScreenDoubeChart> riskSocialContactIndexChart(@Param("policeId") String policeId,
			@Param("tableName") String tableName);

	/**
	 * 查询警员社交风险
	 * @param policeId
	 * @param date
	 * @return
	 */
	RiskSocialContact findPoliceRiskSocialContact(@Param("policeId") String policeId,@Param("date") String date);

	/**
	 * 批量添加风险指数数据
	 * @param riskSocialContactList
	 */
	void insertRiskSocialContactList(List<RiskSocialContact> riskSocialContactList);

}