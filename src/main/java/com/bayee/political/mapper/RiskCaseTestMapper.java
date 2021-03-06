package com.bayee.political.mapper;

import com.bayee.political.domain.RiskCaseTest;
import com.bayee.political.pojo.GlobalIndexNumResultDO;
import org.apache.ibatis.annotations.Param;

public interface RiskCaseTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseTest record);

    int insertSelective(RiskCaseTest record);

    RiskCaseTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseTest record);

    int updateByPrimaryKey(RiskCaseTest record);

    // 警员执法考试风险查询
    RiskCaseTest riskCaseTestIndexItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime,
                                       @Param("lastMonthTime") String lastMonthTime, @Param("timeType") Integer timeType);

    /**
     * 查询警员执法考试风险信息
     *
     * @param policeId
     * @param date
     * @return
     */
    RiskCaseTest findPoliceRiskCaseTest(@Param("policeId") String policeId,@Param("date") String date);

    /**
     * 取得全局扣分 的最高分 - 最低分分值
     * @param date
     * @return
     */
    GlobalIndexNumResultDO findGlobalIndexNum(@Param("date") String date);

}