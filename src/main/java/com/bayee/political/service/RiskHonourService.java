package com.bayee.political.service;

import com.bayee.political.domain.RiskHonour;
import com.bayee.political.json.RiskHonourPageQueryParam;
import com.bayee.political.pojo.RiskHonourPageResultDO;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/28 15:24
 */
public interface RiskHonourService {

    /**
     * 分页查询表彰奖励
     * @return
     */
    List<RiskHonourPageResultDO> riskHonourPage(RiskHonourPageQueryParam queryParam);

    /**
     * 表彰奖励详情
     * @param id
     * @return
     */
    RiskHonour riskHonourDetails(Integer id);

    /**
     * 新增表彰奖励
     * @param riskHonour
     */
    void addRiskHonour(RiskHonour riskHonour);

    /**
     * 表彰奖励修改
     * @param riskHonour
     */
    void updateRiskHonour(RiskHonour riskHonour);

    /**
     * 删除表彰奖励
     * @param id
     */
    void deleteRiskHonour(Integer id);

    /**
     * 表彰奖励-职业生涯查询
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    List<RiskHonour> findHonorByPoliceIdAndYear(String policeId, String year, String month);

}
