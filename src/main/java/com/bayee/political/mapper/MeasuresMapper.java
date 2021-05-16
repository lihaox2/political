package com.bayee.political.mapper;

import com.bayee.political.domain.Measures;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/15
 */
public interface MeasuresMapper {

    /**
     * 查询所有采取措施
     * @return
     */
    List<Measures> findAllMeasures();

}
