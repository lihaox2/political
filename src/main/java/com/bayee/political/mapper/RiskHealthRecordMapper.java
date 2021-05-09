package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.domain.RiskReportRecord;
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

    /**
     * 分页查询健康数据
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<RiskHealthRecord> riskRiskHealthRecordPage(@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize);

    /**
     * 统计分页数据条数
     * @return
     */
    Integer getRiskReportRecordPageCount();

}