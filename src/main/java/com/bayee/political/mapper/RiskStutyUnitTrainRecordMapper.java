package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskStutyUnitTrainRecord;

public interface RiskStutyUnitTrainRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskStutyUnitTrainRecord record);

    int insertSelective(RiskStutyUnitTrainRecord record);

    RiskStutyUnitTrainRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskStutyUnitTrainRecord record);

    int updateByPrimaryKey(RiskStutyUnitTrainRecord record);
    
 // 警员机关单位培训风险查询
 	RiskStutyUnitTrainRecord riskStutyUnitTrainIndexItem(@Param("policeId") String policeId,
 			@Param("dateTime") String dateTime);

 	/**
 	 * 查询警员的机关单位培训记录
 	 * @param policeId
 	 * @param date
 	 * @return
 	 */
 	List<RiskStutyUnitTrainRecord> findRiskStutyUnitTrainRecordByPoliceIdAndDate(@Param("policeId") String policeId,
 																				 @Param("date") String date);
 	
 	Integer getByIdAndYearMonth(@Param("yearMonth")String yearMonth,@Param("policeId")String policeId);
}