package com.bayee.political.mapper;

import com.bayee.political.domain.RiskRecordVerify;
import com.bayee.political.pojo.dto.RiskRecordVerifyDetailsDO;
import com.bayee.political.pojo.dto.RiskRecordVerifyPageResultDO;
import com.bayee.political.pojo.dto.RiskRecordVerifyStatisticsDO;
import com.bayee.political.pojo.json.RiskRecordVerifyPageQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RiskRecordVerifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskRecordVerify record);

    int insertSelective(RiskRecordVerify record);

    RiskRecordVerify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskRecordVerify record);

    int updateByPrimaryKey(RiskRecordVerify record);

    /**
     * 分页查询数据项审核
     * @param queryParam
     * @return
     */
    List<RiskRecordVerifyPageResultDO> riskRecordVerifyPage(@Param("param") RiskRecordVerifyPageQueryParam queryParam);

    /**
     * 统计分页数据条数
     * @param queryParam
     * @return
     */
    Integer countRiskRecordVerifyPage(@Param("param") RiskRecordVerifyPageQueryParam queryParam);

    /**
     *
     * @param id
     * @return
     */
    RiskRecordVerifyDetailsDO findVerifyDOById(Integer id);

    /**
     * 撤销申诉
     * @param typeId
     * @param moduleId
     */
    void cancelAppeal(@Param("typeId") Integer typeId,@Param("moduleId") Integer moduleId);

    /**
     * 申诉详情查询
     * @param typeId
     * @param moduleId
     * @return
     */
    RiskRecordVerifyDetailsDO appealDetails(@Param("typeId") Integer typeId,@Param("moduleId") Integer moduleId);

    /**
     * 数据项 申诉统计
     * @return
     */
    RiskRecordVerifyStatisticsDO riskRecordVerifyStatistics();

    /**
     * 校验数据是否已申诉过
     * @param typeId
     * @param moduleId
     * @return
     */
    Integer checkRecordFlag(@Param("typeId") Integer typeId, @Param("moduleId") Integer moduleId, @Param("state") Integer state);

}