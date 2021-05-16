package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConductBureauRuleRecord;
import com.bayee.political.pojo.dto.ConductBureauRuleDetailsDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RiskConductBureauRuleRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductBureauRuleRecord record);

    int insertSelective(RiskConductBureauRuleRecord record);

    RiskConductBureauRuleRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductBureauRuleRecord record);

    int updateByPrimaryKey(RiskConductBureauRuleRecord record);

    /**
     * 查询扣分详情
     * @param policeId
     * @param dateTime
     * @return
     */
    List<RiskConductBureauRuleRecord> findByPoliceIdAndDate(@Param("policeId") String policeId, @Param("dateTime") String dateTime,
			 @Param("lastMonthTime") String lastMonthTime, @Param("timeType") Integer timeType);

    /**
     * 查询扣分详情
     * @param policeId
     * @param date
     * @return
     */
    List<RiskConductBureauRuleRecord> findRiskConductBureauRuleRecord(@Param("policeId") String policeId, @Param("date") String date);

    /**
     * 分页查询局规计分数据
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<RiskConductBureauRuleRecord> riskConductBureauRuleRecordPage(@Param("pageIndex") Integer pageIndex,
                                                                      @Param("pageSize") Integer pageSize,
                                                                      @Param("type") Integer type,
                                                                      @Param("key") String key);

    /**
     * 统计分页数据条数
     * @return
     */
    Integer getRiskConductBureauRuleRecordPageCount(@Param("type") Integer type,@Param("key") String key);

    /**
     * 根据局规类型统计数据数据条数
     * @param typeId
     * @return
     */
    Integer countByBureauRuleType(@Param("typeId") Integer typeId);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    ConductBureauRuleDetailsDO findById(@Param("id") Integer id);

}