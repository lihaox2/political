package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskConductTrafficViolation;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskConductTrafficViolationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductTrafficViolation record);

    int insertSelective(RiskConductTrafficViolation record);

    RiskConductTrafficViolation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductTrafficViolation record);

    int updateByPrimaryKey(RiskConductTrafficViolation record);
    
    //警员交通违章风险指数查询
    RiskConductTrafficViolation riskConductTrafficViolationItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime);
    
    // 交通违章风险指数图例
 	List<ScreenDoubeChart> riskConductTrafficViolationChart(@Param("policeId") String policeId);
}