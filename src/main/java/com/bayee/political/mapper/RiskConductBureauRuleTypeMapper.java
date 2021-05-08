package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConductBureauRuleType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/8
 */
public interface RiskConductBureauRuleTypeMapper {

    RiskConductBureauRuleType selectByPrimaryKey(Integer id);

    void deleteByPrimaryKey(Integer id);

    void insert(RiskConductBureauRuleType ruleType);

    void insertSelective(RiskConductBureauRuleType ruleType);

    void updateByPrimaryKeySelective(RiskConductBureauRuleType ruleType);

    void updateByPrimaryKey(RiskConductBureauRuleType ruleType);

    /**
     * 分页查询局规计分类型
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<RiskConductBureauRuleType> riskConductBureauRuleTypePage(@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize);

    /**
     * 统计数据条数
     * @return
     */
    Integer getRiskConductBureauRuleTypePageCount();

}
