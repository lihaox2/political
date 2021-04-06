package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConductBureauRule;
import com.bayee.political.domain.ScreenDoubeChart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RiskConductBureauRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductBureauRule record);

    int insertSelective(RiskConductBureauRule record);

    RiskConductBureauRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductBureauRule record);

    int updateByPrimaryKey(RiskConductBureauRule record);

    /**
     * 查询局规计分
     * @param policeId
     * @param dateTime
     * @return
     */
    RiskConductBureauRule findRiskConductBureauRole(@Param("policeId") String policeId, @Param("dateTime") String dateTime);

    /**
     * 查询局规计分图形
     * @param policeId
     * @return
     */
    List<ScreenDoubeChart> findRiskConductBureauRoleChart(String policeId);

}