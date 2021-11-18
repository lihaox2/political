package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConductBureauRuleRecord;
import com.bayee.political.domain.ScreenChart;
import com.bayee.political.pojo.RiskReportTypeStatisticsDO;
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
                                                                      @Param("list") List<Integer> type,
                                                                      @Param("key") String key, @Param("deptId") Integer deptId);

    /**
     * 统计分页数据条数
     * @return
     */
    Integer getRiskConductBureauRuleRecordPageCount(@Param("list") List<Integer> type,@Param("key") String key,
                                                    @Param("deptId") Integer deptId);

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


    /**
     * 根据年份和月份进行查询
     * @param conductBureauRuleRecordYear
     * @param conductBureauRuleRecordMonth
     * @param policeId
     * @return
     */
    List<RiskConductBureauRuleRecord> findConductBureauRuleRecordYearAndMonth(
            @Param("conductBureauRuleRecordYear") String conductBureauRuleRecordYear,
            @Param("conductBureauRuleRecordMonth") String conductBureauRuleRecordMonth,
            @Param("policeId") String policeId
    );

    /**
     * 局规记分-扣分类型统计
     * @param policeId
     * @param dateTime
     * @param lastMonthTime
     * @param timeType
     * @return
     */
    List<RiskReportTypeStatisticsDO> conductBureauRuleReportTypeDOQuery(@Param("policeId") String policeId,
                                                                        @Param("dateTime") String dateTime,
                                                                        @Param("lastMonthTime") String lastMonthTime,
                                                                        @Param("timeType") Integer timeType);


    /**
     * 判定数据是否存在
     * @param inspectorKey
     * @return
     */
    Integer findIdByInspectorKey(String inspectorKey);

}