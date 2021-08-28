package com.bayee.political.mapper;

import com.bayee.political.domain.RiskHonour;
import com.bayee.political.json.RiskHonourPageQueryParam;
import com.bayee.political.pojo.RiskHonourPageResultDO;

import java.util.List;

public interface RiskHonourMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskHonour record);

    int insertSelective(RiskHonour record);

    RiskHonour selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskHonour record);

    int updateByPrimaryKey(RiskHonour record);

    /**
     * 分页查询表彰奖励
     * @return
     */
    List<RiskHonourPageResultDO> riskHonourPage(RiskHonourPageQueryParam queryParam);
}