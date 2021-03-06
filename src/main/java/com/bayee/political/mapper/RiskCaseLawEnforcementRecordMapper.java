package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.pojo.dto.CaseLawEnforcementDetailsDO;
import com.bayee.political.pojo.dto.CaseLawEnforcementPageDO;
import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskCaseLawEnforcementRecord;

public interface RiskCaseLawEnforcementRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseLawEnforcementRecord record);

    int insertSelective(RiskCaseLawEnforcementRecord record);

    RiskCaseLawEnforcementRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseLawEnforcementRecord record);

    int updateByPrimaryKey(RiskCaseLawEnforcementRecord record);

    // 警员执法管理数据列表查询
    List<RiskCaseLawEnforcementRecord> riskCaseLawEnforcementRecordList(@Param("policeId") String policeId,
                                                                        @Param("dateTime") String dateTime,
                                                                        @Param("lastMonthTime") String lastMonthTime,
                                                                        @Param("timeType") Integer timeType);

    /**
     * 统计警员办案量
     *
     * @param policeId
     * @param date
     * @return
     */
    Integer countPoliceCaseData(@Param("policeId") String policeId, @Param("date") String date);

    /**
     * 查询某个月警员的平均扣分
     *
     * @param date
     * @return
     */
    Double findPoliceAvgDeductionScoreByDate(@Param("date") String date);

    /**
     * 查询警员执法数据
     *
     * @param policeId 警号
     * @param date     时间
     * @return
     */
    List<RiskCaseLawEnforcementRecord> findPoliceCaseData(@Param("policeId") String policeId,
                                                          @Param("date") String date);

    /**
     * 分页查询执法管理数据
     * @param pageIndex
     * @param pageSize
     * @param type
     * @param key
     * @return
     */
    List<CaseLawEnforcementPageDO> riskCaseLawEnforcementRecordPage(@Param("pageIndex") Integer pageIndex,
                                                                    @Param("pageSize") Integer pageSize,
                                                                    @Param("list") List<Integer> type,
                                                                    @Param("key") String key,
                                                                    @Param("deptId") Integer deptId);

    /**
     * 统计分页数据条数
     * @param type
     * @param key
     * @return
     */
    Integer riskCaseLawEnforcementRecordPageCount(@Param("list") List<Integer> type, @Param("key") String key,
                                                  @Param("deptId") Integer deptId);

    /**
     * 通过id查询执法管理数据
     * @param id
     * @return
     */
    CaseLawEnforcementDetailsDO findDetailsDOById(@Param("id") Integer id);

    /**
     * 统计所有执法管理数据
     * @return
     */
    Integer countAll();

    /**
     * 查询警员重复执法管理数
     * @param policeId
     * @param type
     * @return
     */
    Integer getPoliceReplaceErrorCount(@Param("policeId") String policeId, @Param("type") Integer type);

    /**
     * 根据年份和月份进行查询
     * @param caseLawEnforcementRecordYear
     * @param caseLawEnforcementRecordMonth
     * @param policeId
     * @return
     */
    List<RiskCaseLawEnforcementRecord> findCaseLawEnforcementRecordYearAndMonth(
            @Param("caseLawEnforcementRecordYear") String caseLawEnforcementRecordYear,
            @Param("caseLawEnforcementRecordMonth") String caseLawEnforcementRecordMonth,
            @Param("policeId") String policeId
    );

    /**
     * 检查警员执法管理扣分情况
     * @param policeId
     * @param beginDate
     * @param endDate
     * @return
     */
    Integer checkPoliceDeductionScoreStatus(@Param("policeId") String policeId, @Param("beginDate") String beginDate,
                                            @Param("endDate") String endDate);
}