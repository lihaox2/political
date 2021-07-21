package com.bayee.political.mapper;

import com.bayee.political.domain.RiskRecordVerify;
import com.bayee.political.pojo.dto.RiskRecordVerifyDetailsDO;
import com.bayee.political.pojo.dto.RiskRecordVerifyPageResultDO;
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
}