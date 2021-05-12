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
    List<RiskConductBureauRuleType> riskConductBureauRuleTypePage(@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize,@Param("type") Integer type,@Param("key") String key);

    /**
     * 统计数据条数
     * @return
     */
    Integer getRiskConductBureauRuleTypePageCount(@Param("type") Integer type,@Param("key") String key);

    /**
     * 查询所有大于1级的类型
     * @return
     */
    List<RiskConductBureauRuleType> getAllRiskConductBureauRuleType();

    /**
     * 根据名称和上级编号统计数据条数
     * @param name
     * @param parentId
     * @param id
     * @return
     */
    Integer countRuleTypeByNameAndRuleType(@Param("name") String name,@Param("parentId") Integer parentId, @Param("id") Integer id);

}
