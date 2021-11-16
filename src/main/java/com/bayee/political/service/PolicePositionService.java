package com.bayee.political.service;

import com.bayee.political.domain.PolicePosition;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/8
 */
public interface PolicePositionService {

    /**
     * 查询所有职务
     * @return
     */
    List<PolicePosition> findAll();
}
