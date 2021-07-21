package com.bayee.political.service;

import com.bayee.political.domain.RiskRecordVerify;
import com.bayee.political.pojo.dto.RiskRecordVerifyDetailsDO;
import com.bayee.political.pojo.dto.RiskRecordVerifyPageResultDO;
import com.bayee.political.pojo.json.RiskRecordVerifyPageQueryParam;

import java.util.List;

/**
 * @author xxl
 * @date 2021/7/20
 */
public interface RiskRecordVerifyService {

    /**
     * 分页查询数据项审核
     * @param queryParam
     * @return
     */
    List<RiskRecordVerifyPageResultDO> riskRecordVerifyPage(RiskRecordVerifyPageQueryParam queryParam);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    RiskRecordVerify findById(Integer id);

    /**
     *
     * @param id
     * @return
     */
    RiskRecordVerifyDetailsDO findVerifyDOById(Integer id);

    /**
     * 添加警员申诉
     * @param verify
     */
    void addAppeal(RiskRecordVerify verify);

    /**
     * 撤销申诉
     * @param typeId
     * @param moduleId
     */
    void cancelAppeal(Integer typeId, Integer moduleId);

    /**
     * 申诉详情查询
     * @param typeId
     * @param moduleId
     * @return
     */
    RiskRecordVerifyDetailsDO appealDetails(Integer typeId, Integer moduleId);

    /**
     * 数据项审核
     * @param verify
     */
    void checkRecord(RiskRecordVerify verify);

}
