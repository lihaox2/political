package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConduct;
import com.bayee.political.domain.ScreenDoubeChart;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RiskConductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConduct record);

    int insertSelective(RiskConduct record);

    RiskConduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConduct record);

    int updateByPrimaryKey(RiskConduct record);

    /**
     * 查询警员行为规范风险指数
     * @param policeId
     * @param date
     * @return
     */
    RiskConduct findByPoliceIdAndDate(@Param("policeId") String policeId,@Param("date") String date);

    /**
     * 查询警员行为规范风险指数图例
     * @param policeId
     * @return
     */
    List<ScreenDoubeChart> findRiskConductChart(String policeId);

    /**
     * 分类统计
     * @param policeId
     * @param date
     * @return
     */
    List<Map<String, Object>> countByConductType(@Param("policeId") String policeId,@Param("date") String date);

    /**
     * 查询最严重的状态 & 总共风险条数
     * @param policeId
     * @param date
     * @return
     */
    Map<String, Object> findMostSeriousStatusAndTotalCount(@Param("policeId") String policeId,@Param("date") String date);

}