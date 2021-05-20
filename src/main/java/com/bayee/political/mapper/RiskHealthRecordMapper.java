package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.domain.RiskReportRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.bayee.political.domain.RiskHealthRecord;

public interface RiskHealthRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskHealthRecord record);

    int insertSelective(RiskHealthRecord record);

    RiskHealthRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskHealthRecord record);

    int updateByPrimaryKey(RiskHealthRecord record);

    Integer getByIdAndYear(@Param("policeId") String policeId,@Param("year")String year,@Param("id") Integer id);
    
    List<RiskHealthRecord> selectYearAll(@Param("year")String year);

    /**
     * 分页查询健康数据
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<RiskHealthRecord> riskRiskHealthRecordPage(@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize,
    		@Param("columnList") List<String> columnList, @Param("typeFlag") Integer typeFlag,
            @Param("key") String key);

    /**
     * 统计分页数据条数
     * @return
     */
    Integer getRiskReportRecordPageCount(
            @Param("columnList") List<String> columnList, @Param("typeFlag") Integer typeFlag,
            @Param("key") String key);

    /**
     * 统计所有数据条数
     * @return
     */
    Integer countAll();

}