package com.bayee.political.service;

import com.bayee.political.domain.RiskHealthRecord;
import com.bayee.political.domain.RiskReportRecord;

import java.util.List;

public interface RiskHealthRecordService {

	int deleteByPrimaryKey(Integer id);

    int insert(RiskHealthRecord record);

    int insertSelective(RiskHealthRecord record);

    RiskHealthRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskHealthRecord record);

    int updateByPrimaryKey(RiskHealthRecord record);
    
    Integer getByIdAndYear(String policeId, String year, Integer id);
    
    RiskReportRecord getByPoliceIdMonth(String year,String month,String policeId);

    /**
     * 分页查询健康数据
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<RiskHealthRecord> riskRiskHealthRecordPage(Integer pageIndex, Integer pageSize, List<String> columnList,
                                                    Integer typeFlag, String key);

    /**
     * 统计分页数据条数
     * @return
     */
    Integer getRiskReportRecordPageCount(List<String> columnList,Integer typeFlag,
            String key);

    /**
     * 统计所有数据条数
     * @return
     */
    Integer countAll();

}
