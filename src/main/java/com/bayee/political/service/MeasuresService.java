package com.bayee.political.service;

import com.bayee.political.domain.Measures;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/15
 */
public interface MeasuresService {

    /**
     * 查询所有采取措施
     * @return
     */
    List<Measures> findAllMeasures();

}
