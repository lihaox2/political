package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.AlarmScoringBreakdown;

public interface AlarmScoringBreakdownMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlarmScoringBreakdown record);

    int insertSelective(AlarmScoringBreakdown record);

    AlarmScoringBreakdown selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlarmScoringBreakdown record);

    int updateByPrimaryKey(AlarmScoringBreakdown record);
    
    /**
     * 查询全部的记分细目
     * @return
     */
    List<AlarmScoringBreakdown> getAllScoringBreakdown();
    
    /**
     * 根据记分项目获得对应的记分细目
     * @return scoreItems 记分项目id
     */
    List<AlarmScoringBreakdown> getBreakDownByProgram(@Param("scoreItems") Integer scoreItems);
    
    /**
     * 根据id获取记分项目对应的记分细目
     * @param id
     * @return
     */
    List<AlarmScoringBreakdown> getBreakDownById(@Param("id") String id);
    
    /**
     * 根据计分细目名获取计分细目id
     * @param name
     * @return
     */
    Integer getIdByBreakdownName(String name);
    
}