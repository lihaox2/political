package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConductVisitType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RiskConductVisitTypeMapper {
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
    List<RiskConductVisitType> riskConductVisitTypePage(@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize);

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
    Integer countByNameAndParentId(@Param("name") String name,@Param("parentId") Integer parentId, @Param("id") Integer id);

    /**
     * 通过名称查询
     * @param name
     * @return
     */
    RiskConductVisitType findByName(@Param("name") String name);

}