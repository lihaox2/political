package com.bayee.political.service;

import com.bayee.political.domain.RiskCaseIntegral;
import com.bayee.political.json.CaseIntegralPageQueryParam;
import com.bayee.political.pojo.CaseIntegralPageResultDO;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/28 19:09
 */
public interface RiskCaseIntegralService {

    /**
     * 分页查询办案积分
     * @param queryParam
     * @return
     */
    List<CaseIntegralPageResultDO> caseIntegralPage(CaseIntegralPageQueryParam queryParam);

    /**
     * 添加办案积分
     * @param caseIntegral
     */
    void addRiskCaseIntegral(RiskCaseIntegral caseIntegral);

    /**
     * 修改办案积分
     * @param caseIntegral
     */
    void updateRiskCaseIntegral(RiskCaseIntegral caseIntegral);

    /**
     * 详情查询
     * @param id
     * @return
     */
    RiskCaseIntegral findById(Integer id);

    /**
     * 删除办案积分
     * @param id
     */
    void deleteRiskCaseIntegral(Integer id);

    /**
     * 职业生涯-办案积分
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    List<RiskCaseIntegral> findCaseIntegralByPoliceIdAndYear(String policeId, String year, String month);

}
