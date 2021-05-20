package com.bayee.political.service;

import com.bayee.political.domain.RiskDutyErrorType;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/19
 */
public interface RiskDutyErrorTypeService {

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
    Integer findIdByName(String name);

}
