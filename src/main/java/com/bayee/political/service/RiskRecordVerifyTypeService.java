package com.bayee.political.service;

import com.bayee.political.domain.RiskRecordVerifyType;

import java.util.List;

/**
 * @author xxl
 * @date 2021/7/20
 */
public interface RiskRecordVerifyTypeService {

    /**
     * 查询所有申诉类型
     * @return
     */
    List<RiskRecordVerifyType> findAllVerifyType(Integer scorer);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    RiskRecordVerifyType findById(Integer id);

}
