package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskStutyActivitiesPartyRecord;

public interface RiskStutyActivitiesPartyRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskStutyActivitiesPartyRecord record);

    int insertSelective(RiskStutyActivitiesPartyRecord record);

    RiskStutyActivitiesPartyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskStutyActivitiesPartyRecord record);

    int updateByPrimaryKey(RiskStutyActivitiesPartyRecord record);
    
    /**
     * 查询警员党组织培训数据
     * @param policeId
     * @param date
     * @return
     */
    List<RiskStutyActivitiesPartyRecord> findByPoliceIdAndDate(@Param("policeId") String policeId,@Param("date") String date);
    
    Integer getByIdAndYearMonth(@Param("yearMonth")String yearMonth,@Param("policeId")String policeId);
}