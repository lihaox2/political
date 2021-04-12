package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskCaseLawEnforcementRecord;

public interface RiskCaseLawEnforcementRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseLawEnforcementRecord record);

    int insertSelective(RiskCaseLawEnforcementRecord record);

    RiskCaseLawEnforcementRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseLawEnforcementRecord record);

    int updateByPrimaryKey(RiskCaseLawEnforcementRecord record);

    // 警员执法管理数据列表查询
    List<RiskCaseLawEnforcementRecord> riskCaseLawEnforcementRecordList(@Param("policeId") String policeId,
                                                                        @Param("dateTime") String dateTime,
                                                                        @Param("lastMonthTime") String lastMonthTime,
                                                                        @Param("timeType") Integer timeType);
}