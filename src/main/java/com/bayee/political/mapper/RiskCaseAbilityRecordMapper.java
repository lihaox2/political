package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskCaseAbilityRecord;

public interface RiskCaseAbilityRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseAbilityRecord record);

    int insertSelective(RiskCaseAbilityRecord record);

    RiskCaseAbilityRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseAbilityRecord record);

    int updateByPrimaryKey(RiskCaseAbilityRecord record);
    
    /**
     * 查询警员执法数据
     * @param policeId 警号
     * @param date 时间
     * @return
     */
    List<RiskCaseAbilityRecord> findPoliceCaseData(@Param("policeId") String policeId,@Param("date") String date);
    
    
    Integer getByYearAndPoliceId(@Param("year")String year,@Param("policeId") String policeId);

    /**
     * 分页查询执法能力数据
     * @param pageIndex
     * @param pageSize
     * @param columnList
     * @param typeFlag
     * @param key
     * @param date
     * @return
     */
    List<RiskCaseAbilityRecord> riskCaseAbilityRecordPage(@Param("pageIndex") Integer pageIndex,
                                                          @Param("pageSize") Integer pageSize,
                                                          @Param("columnList") List<String> columnList,
                                                          @Param("typeFlag") Integer typeFlag, @Param("key") String key,
                                                          @Param("date") String date,@Param("deptId") Integer deptId);

    /**
     * 统计数据条数
     * @param columnList
     * @param typeFlag
     * @param key
     * @param date
     * @return
     */
    Integer getRiskCaseAbilityRecordPageCount(@Param("columnList") List<String> columnList,
                                              @Param("typeFlag") Integer typeFlag, @Param("key") String key,
                                              @Param("date") String date, @Param("deptId") Integer deptId);

    /**
     * 根据时间和警号查询id
     * @param date
     * @param policeId
     * @return
     */
    Integer getIdByDateAndPoliceId(@Param("date") String date,@Param("policeId") String policeId, @Param("id") Integer id);

    /**
     * 根据年份和月份进行查询
     * @param caseAbilityRecordYear
     * @param caseAbilityRecordMonth
     * @param policeId
     * @return
     */
    List<RiskCaseAbilityRecord> findCaseAbilityRecordYearAndMonth(
            @Param("caseAbilityRecordYear") String caseAbilityRecordYear,
            @Param("caseAbilityRecordMonth") String caseAbilityRecordMonth,
            @Param("policeId") String policeId
    );
}