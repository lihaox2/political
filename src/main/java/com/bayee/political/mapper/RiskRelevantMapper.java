package com.bayee.political.mapper;

import com.bayee.political.domain.RiskRelevant;
import com.bayee.political.pojo.GlobalIndexNumResultDO;
import org.apache.ibatis.annotations.Param;

public interface RiskRelevantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskRelevant record);

    int insertSelective(RiskRelevant record);

    RiskRelevant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskRelevant record);

    int updateByPrimaryKey(RiskRelevant record);

    /**
     * 查询警员动态排摸扣分情况
     * @param policeId
     * @param date
     * @return
     */
    RiskRelevant findByPoliceIdAndDate(@Param("policeId") String policeId, @Param("date") String date);

    /**
     * 取得全局扣分 的最高分 - 最低分分值
     * @param date
     * @return
     */
    GlobalIndexNumResultDO findGlobalIndexNum(String date);

}