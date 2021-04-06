package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskReportRecordMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RiskReportRecord record);

	int insertSelective(RiskReportRecord record);

	RiskReportRecord selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskReportRecord record);

	int updateByPrimaryKey(RiskReportRecord record);

	// 警员风险分页查询
	List<RiskReportRecord> riskPageList(@Param("keyWords") String keyWords, @Param("sortName") String sortName,
			@Param("dateTime") String dateTime, @Param("lastDateTime") String lastDateTime,
			@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 警员风险列表总数
	int riskPageCount(@Param("keyWords") String keyWords, @Param("dateTime") String dateTime,
			@Param("lastDateTime") String lastDateTime);

	// 警员风险雷达图
	List<ScreenDoubeChart> riskChartList(@Param("policeId") String policeId, @Param("dateTime") String dateTime);

	// 警员风险详情查询
	RiskReportRecord riskReportRecordItem(@Param("id") Integer id, @Param("policeId") String policeId,
			@Param("dateTime") String dateTime, @Param("lastDateTime") String lastDateTime);
}