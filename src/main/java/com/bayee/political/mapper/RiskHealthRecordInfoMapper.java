package com.bayee.political.mapper;

import com.bayee.political.domain.RiskHealthRecordInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RiskHealthRecordInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskHealthRecordInfo record);

    RiskHealthRecordInfo selectByPrimaryKey(Integer id);

    List<RiskHealthRecordInfo> selectAll();

    int updateByPrimaryKey(RiskHealthRecordInfo record);

    /**
     *
     * @param recordId
     * @return
     */
    RiskHealthRecordInfo findByRecordId(@Param("recordId") Integer recordId);

}