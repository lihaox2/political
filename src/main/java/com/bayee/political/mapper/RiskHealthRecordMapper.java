package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskHealthRecord;

public interface RiskHealthRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskHealthRecord record);

    int insertSelective(RiskHealthRecord record);

    RiskHealthRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskHealthRecord record);

    int updateByPrimaryKey(RiskHealthRecord record);

    Integer getByIdAndYear(@Param("policeId") String policeId,@Param("year")String year);
    
    List<RiskHealthRecord> selectYearAll(@Param("year")String year);
}