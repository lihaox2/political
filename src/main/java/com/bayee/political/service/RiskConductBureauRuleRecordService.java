package com.bayee.political.service;

import com.bayee.political.domain.RiskConductBureauRuleRecord;

import java.util.List;

public interface RiskConductBureauRuleRecordService {
	
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductBureauRuleRecord record);

    int insertSelective(RiskConductBureauRuleRecord record);

    RiskConductBureauRuleRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductBureauRuleRecord record);

    int updateByPrimaryKey(RiskConductBureauRuleRecord record);

    /**
     * 分页查询局规计分数据
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<RiskConductBureauRuleRecord> riskConductBureauRuleRecordPage(Integer pageIndex, Integer pageSize);

    /**
     * 统计分页数据条数
     * @return
     */
    Integer getRiskConductBureauRuleRecordPageCount();

    /**
     * 根据局规类型统计数据数据条数
     * @param typeId
     * @return
     */
    Integer countByBureauRuleType(Integer typeId);

}
