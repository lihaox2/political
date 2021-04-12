package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskHealth;

public interface RiskHealthMapper {

	// 警员健康风险指数新增
	int riskHealthCreat(RiskHealth record);

	// 警员健康风险指数修改
	int riskHealthUpdate(RiskHealth record);

	// 警员健康风险指数查询
	RiskHealth riskHealthIndexItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime);
	
	Integer getByIdAndYear(@Param("policeId") String policeId,@Param("year")String year);
	
	// 警员健康风险指数新增
	int insertSelective(RiskHealth record);
	
	Double selectTotalNum(@Param("id") Integer id);
	
	Double fraction(@Param("id") Integer id);
	
    List<RiskHealth> getByYear(@Param("year")String year);
    
    List<String> getAllByYear(@Param("year")String year);

	// 警员最新一条健康风险指数查询
	RiskHealth riskHealthIndexNewestItem(@Param("policeId") String policeId);
}