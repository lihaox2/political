package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskFamilyEvaluation;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskFamilyEvaluationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskFamilyEvaluation record);

    int insertSelective(RiskFamilyEvaluation record);

    RiskFamilyEvaluation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskFamilyEvaluation record);

    int updateByPrimaryKey(RiskFamilyEvaluation record);
    
    // 家属评价风险指数查询
    RiskFamilyEvaluation riskFamilyEvaluationItem(@Param("policeId")String policeId,@Param("dateTime")String dateTime);
    
    // 家属评价风险指数图例
 	List<ScreenDoubeChart> riskFamilyEvaluationChart(@Param("policeId")String policeId);
 	
 	Integer findByPoliceIdOrYearMonth(@Param("policeId")String policeId,@Param("year")String year,@Param("month")String month);
 	
 	Double findByPoliceIdOrYearTotalNum(@Param("policeId")String policeId,@Param("year")String year);
 	
 	List<String> findPoliceIdALlByYearOrMonth(@Param("year")String year,@Param("month")String month);
}