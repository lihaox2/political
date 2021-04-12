package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskDrinkRecord;

public interface RiskDrinkRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskDrinkRecord record);

    int insertSelective(RiskDrinkRecord record);

    RiskDrinkRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskDrinkRecord record);

    int updateByPrimaryKey(RiskDrinkRecord record);
    /**
     * 更新是否工作日数据
     * @param year 年份
     * @return 更新的数据条数
     */
    int updateIsWorkDay(String year);

    /**
     * 查询本月连续的饮酒数据
     * @param policeId 警员id
     * @param date 当前时间
     * @return
     */
    List<RiskDrinkRecord> findRORThreeDaysDrinkData(@Param("policeId") String policeId, @Param("date") String date);

    /**
     * 统计警员饮酒次数
     * @param policeId 警员id
     * @param date 当前时间
     * @return
     */
    List<RiskDrinkRecord> findPoliceDrinkData(@Param("policeId") String policeId,@Param("date") String date);
    
    int insertInpromt(RiskDrinkRecord riskDrinkRecord);
}