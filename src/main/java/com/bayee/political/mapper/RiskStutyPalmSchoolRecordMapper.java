package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskStutyPalmSchoolRecord;

public interface RiskStutyPalmSchoolRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskStutyPalmSchoolRecord record);

    int insertSelective(RiskStutyPalmSchoolRecord record);

    RiskStutyPalmSchoolRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskStutyPalmSchoolRecord record);

    int updateByPrimaryKey(RiskStutyPalmSchoolRecord record);
    
 // 警员掌上学堂风险查询
 	RiskStutyPalmSchoolRecord riskStutyPalmSchoolRecordIndexItem(@Param("policeId") String policeId,
 																 @Param("dateTime") String dateTime);

     /**
      * 查询警员掌上学习记录
      * @param policeId
      * @param date
      * @return
      */
     List<RiskStutyPalmSchoolRecord> findByPoliceIdAndDate(@Param("policeId") String policeId, @Param("date") String date);

 	
 	Integer getByIdAndYearMonth(@Param("yearMonth")String yearMonth,@Param("policeId")String policeId);
}