package com.bayee.political.mapper;

import com.bayee.political.domain.RiskCaseIntegral;
import com.bayee.political.json.CaseIntegralPageQueryParam;
import com.bayee.political.pojo.CaseIntegralPageResultDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RiskCaseIntegralMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseIntegral record);

    int insertSelective(RiskCaseIntegral record);

    RiskCaseIntegral selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseIntegral record);

    int updateByPrimaryKey(RiskCaseIntegral record);

    /**
     * 分页查询办案积分
     * @param queryParam
     * @return
     */
    List<CaseIntegralPageResultDO> caseIntegralPage(CaseIntegralPageQueryParam queryParam);

    /**
     * 职业生涯-办案积分
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    List<RiskCaseIntegral> findCaseIntegralByPoliceIdAndYear(@Param("policeId") String policeId, @Param("year") String year,
                                                             @Param("month") String month);
}