package com.bayee.political.service;

import com.bayee.political.domain.RiskConductVisitType;

import java.util.List;

public interface RiskConductVisitTypeService {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductVisitType record);

    int insertSelective(RiskConductVisitType record);

    RiskConductVisitType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductVisitType record);

    int updateByPrimaryKey(RiskConductVisitType record);
    
    Integer selectByName(String name);

    /**
     * 分页查询..
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<RiskConductVisitType> riskConductVisitTypePage(Integer pageIndex, Integer pageSize);

    /**
     * 统计分页数据条数
     * @return
     */
    Integer getRiskConductVisitTypePageCount();

    /**
     * 查询所有信访类型
     * @return
     */
    List<RiskConductVisitType> getAllRiskConductVisitType();

    /**
     * 根绝名称和父级类型统计数据条数
     * @param name
     * @param parentId
     * @param id
     * @return
     */
    Integer countByNameAndParentId(String name, Integer parentId, Integer id);

    /**
     * 通过名称查询
     * @param name
     * @return
     */
    RiskConductVisitType findByName(String name);

}