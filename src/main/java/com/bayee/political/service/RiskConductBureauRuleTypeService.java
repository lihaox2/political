package com.bayee.political.service;

import com.bayee.political.domain.RiskConductBureauRuleType;
import com.bayee.political.domain.RiskConductVisitType;
import com.bayee.political.pojo.dto.ConductBureauRuleTypeDetailsDO;

import java.util.List;
import java.util.Map;

/**
 * @author xxl
 * @date 2021/5/8
 */
public interface RiskConductBureauRuleTypeService {

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
    List<RiskConductBureauRuleType> riskConductBureauRuleTypePage(Integer pageIndex, Integer pageSize,Integer type,String key);

    /**
     * 统计数据条数
     * @return
     */
    Integer getRiskConductBureauRuleTypePageCount(Integer type,String key);

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
    Integer countRuleTypeByNameAndRuleType(String name, Integer parentId, Integer id);

    /**
     * 查询一级分类
     * @return
     */
    List<RiskConductBureauRuleType> getMeasuresType();

    /**
     * 通过id查询详情
     * @param id
     * @return
     */
    ConductBureauRuleTypeDetailsDO findById(Integer id);

    /**
     * 通过计分项查询所有类型
     * @param name
     * @return
     */
    List<Map<String, Object>> getTotalTypeByScoringOptionName(String name);

}
