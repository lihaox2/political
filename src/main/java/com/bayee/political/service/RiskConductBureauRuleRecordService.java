package com.bayee.political.service;

import com.bayee.political.domain.RiskConductBureauRuleRecord;
import com.bayee.political.domain.ScreenChart;
import com.bayee.political.pojo.dto.ConductBureauRuleDetailsDO;

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
    List<RiskConductBureauRuleRecord> riskConductBureauRuleRecordPage(Integer pageIndex, Integer pageSize,String type,
                                                                      String key, Integer deptId);

    /**
     * 统计分页数据条数
     * @return
     */
    Integer getRiskConductBureauRuleRecordPageCount(String type, String key, Integer deptId);

    /**
     * 根据局规类型统计数据数据条数
     * @param typeId
     * @return
     */
    Integer countByBureauRuleType(Integer typeId);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    ConductBureauRuleDetailsDO findById(Integer id);

    /**
     * 统计所有局规记分数据
     * @return
     */
    Integer countAll();

    /**
     * 首页图表查询
     * @return
     */
    List<ScreenChart> getConductBureauChart();

}
