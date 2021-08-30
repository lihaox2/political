package com.bayee.political.mapper;

import com.bayee.political.domain.RiskHonour;
import com.bayee.political.json.RiskHonourPageQueryParam;
import com.bayee.political.pojo.RiskHonourPageResultDO;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 表彰奖励-职业生涯查询
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    List<RiskHonour> findHonorByPoliceIdAndYear(@Param("policeId") String policeId,@Param("year") String year,
                                                @Param("month") String month);

    /**
     * 分页统计
     * @param queryParam
     * @return
     */
    Integer riskHonourPageCount(RiskHonourPageQueryParam queryParam);
}