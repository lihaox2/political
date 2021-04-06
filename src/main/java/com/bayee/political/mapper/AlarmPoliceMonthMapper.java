package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.AlarmPoliceMonth;

public interface AlarmPoliceMonthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlarmPoliceMonth record);

    int insertSelective(AlarmPoliceMonth record);

    AlarmPoliceMonth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlarmPoliceMonth record);

    int updateByPrimaryKey(AlarmPoliceMonth record);
    
    /**
     * 获得公安月
     */
    List<AlarmPoliceMonth> getAllMonth();
    
    /**
     * 月度考评预警趋势图
     * @param target 1代表加分数量 2代表扣分数量
     * @return
     */
    List<AlarmPoliceMonth> getMonthEvaluationTrendChart(@Param("target") Integer target, 
    		@Param("departmentId") Integer departmentId);
    
}