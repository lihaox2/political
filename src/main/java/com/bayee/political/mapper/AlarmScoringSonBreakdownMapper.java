package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.AlarmScoringBreakdown;
import com.bayee.political.domain.AlarmScoringSonBreakdown;

public interface AlarmScoringSonBreakdownMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlarmScoringSonBreakdown record);

    int insertSelective(AlarmScoringSonBreakdown record);

    AlarmScoringSonBreakdown selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlarmScoringSonBreakdown record);

    int updateByPrimaryKey(AlarmScoringSonBreakdown record);
    
    /**
     * 查询所有记分子细目
     * @return
     */
    @Deprecated
    List<AlarmScoringSonBreakdown> getAllScoringSonBreakdowns();
    
    /**
     * 根据记分细目id查询记分子细目
     * @return
     */
    List<AlarmScoringSonBreakdown> getScoringSonBreakdownsByParentId(
    		@Param("scoringBreakdownId") Integer scoringBreakdownId
    		);
    
    /**
     * 根据计分细目名获取计分细目id
     * @param name
     * @return
     */
    Integer getIdBySonBreakdownName(
    		@Param("scoringBreakdownId") Integer scoringBreakdownId, 
    		@Param("name") String name);
    
}