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
                                                                    @Param("key") String key);

    /**
     * 统计分页数据条数
     * @param type
     * @param key
     * @return
     */
    Integer riskCaseLawEnforcementRecordPageCount(@Param("list") List<Integer> type, @Param("key") String key);

    /**
     * 通过id查询执法管理数据
     * @param id
     * @return
     */
    CaseLawEnforcementDetailsDO findDetailsDOById(@Param("id") Integer id);

}