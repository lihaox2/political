package com.bayee.political.mapper;

import com.bayee.political.domain.RiskRecordVerifyType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RiskRecordVerifyTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskRecordVerifyType record);

    int insertSelective(RiskRecordVerifyType record);

    RiskRecordVerifyType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskRecordVerifyType record);

    int updateByPrimaryKey(RiskRecordVerifyType record);

    /**
     * 查询所有申诉类型
     * @return
     */
    List<RiskRecordVerifyType> findAllVerifyType(@Param("scorer") Integer scorer);
}