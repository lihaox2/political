package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.domain.LeaveIntegralExchangeRule;

public interface LeaveIntegralExchangeRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeaveIntegralExchangeRule record);

    int insertSelective(LeaveIntegralExchangeRule record);

    LeaveIntegralExchangeRule selectByPrimaryKey(Integer id);

    /**
     * 修改积分兑换规则数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(LeaveIntegralExchangeRule leaveIntegralExchangeRule);

    int updateByPrimaryKey(LeaveIntegralExchangeRule record);
    
    /**
     * 获得积分兑换规则数据
     * @return
     */
    List<LeaveIntegralExchangeRule> getLeaveIntegralExchangeRuleList();
}