package com.bayee.political.mapper;

import com.bayee.political.domain.RiskDataOperationLog;

/**
 *
 * @author xxl
 */
public interface RiskDataOperationLogMapper {
    int deleteByPrimaryKey(String operationType);

    int insert(RiskDataOperationLog record);

    int insertSelective(RiskDataOperationLog record);

    RiskDataOperationLog selectByPrimaryKey(String operationType);

    int updateByPrimaryKeySelective(RiskDataOperationLog record);

    int updateByPrimaryKey(RiskDataOperationLog record);
}