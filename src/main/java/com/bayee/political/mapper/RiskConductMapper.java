package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConduct;
import org.apache.ibatis.annotations.Param;

public interface RiskConductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConduct record);

    int insertSelective(RiskConduct record);

    RiskConduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConduct record);

    int updateByPrimaryKey(RiskConduct record);

    /**
     * 查询警员行为规范数据
     * @param policeId 警员
     * @param dateTime ${yyyy-mm}
     * @return
     */
    RiskConduct findRiskConductByPoliceIdAndDate(@Param("policeId") String policeId, @Param("dateTime") String dateTime);

}