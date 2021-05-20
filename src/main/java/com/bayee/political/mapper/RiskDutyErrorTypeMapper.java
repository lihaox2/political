package com.bayee.political.mapper;

import com.bayee.political.domain.RiskDutyErrorType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/19
 */
public interface RiskDutyErrorTypeMapper {

    /**
     * 查询所有警情类别
     * @return
     */
    List<RiskDutyErrorType> getAll();

    /**
     * 通过名字查询id
     * @param name
     * @return
     */
    Integer findIdByName(@Param("name") String name);

}
