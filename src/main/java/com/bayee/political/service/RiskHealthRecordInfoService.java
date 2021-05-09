package com.bayee.political.service;

import com.bayee.political.domain.RiskHealthRecordInfo;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/8
 */
public interface RiskHealthRecordInfoService {

    int deleteByPrimaryKey(Integer id);

    int insert(RiskHealthRecordInfo record);

    RiskHealthRecordInfo selectByPrimaryKey(Integer id);

    List<RiskHealthRecordInfo> selectAll();

    int updateByPrimaryKey(RiskHealthRecordInfo record);

    /**
     *根据recordId进行查询
     * @param recordId
     * @return
     */
    RiskHealthRecordInfo findByRecordId(Integer recordId);

    /**
     * 根据recordId进行删除
     * @param recordId
     */
    void deleteByRecordId(Integer recordId);

}
