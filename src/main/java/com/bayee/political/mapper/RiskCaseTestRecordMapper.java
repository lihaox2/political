package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.bayee.political.domain.RiskCaseTestRecord;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskCaseTestRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseTestRecord record);

    int insertSelective(RiskCaseTestRecord record);

    RiskCaseTestRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseTestRecord record);

    int updateByPrimaryKey(RiskCaseTestRecord record);
    
    // 警员执法考试风险指数查询
    RiskCaseTestRecord riskCaseTestItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime);
    
    // 执法考试风险指数图例
 	List<ScreenDoubeChart> riskCaseTestChart(@Param("policeId") String policeId);
}