package com.bayee.political.mapper;

import com.bayee.political.domain.RiskCaseLawEnforcementType;
import com.bayee.political.json.ChartResult;
import com.bayee.political.pojo.RiskReportTypeStatisticsDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RiskCaseLawEnforcementTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseLawEnforcementType record);

    int insertSelective(RiskCaseLawEnforcementType record);

    RiskCaseLawEnforcementType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseLawEnforcementType record);

    int updateByPrimaryKey(RiskCaseLawEnforcementType record);

    /**
     * 获取执法管理类型
     * @return
     */
    List<RiskCaseLawEnforcementType> getLawEnforcementType();

    /**
     * 获取接处警类型
     * @return
     */
    List<RiskCaseLawEnforcementType> getDutyType();

    /**
     * 通过类型名称查询id
     * @param name
     * @param parentId
     * @return
     */
    Integer findByNameAndParentId(@Param("name") String name,@Param("parentId") Integer parentId);

    /**
     * 执法能力-扣分类型统计
     * @param policeId
     * @param dateTime
     * @param lastMonthTime
     * @param timeType
     */
    List<RiskReportTypeStatisticsDO> lawEnforcementReportTypeDOQuery(@Param("policeId") String policeId,
                                                                     @Param("dateTime") String dateTime,
                                                                     @Param("lastMonthTime") String lastMonthTime,
                                                                     @Param("timeType") Integer timeType);

}