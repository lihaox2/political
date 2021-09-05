package com.bayee.political.service;

import com.bayee.political.domain.PoliceRelevantType;

import java.util.List;

/**
 * @author xxl
 * @date 2021/9/4 19:17
 */
public interface PoliceRelevantTypeService {

    /**
     * 查询所有
     * @return
     */
    List<PoliceRelevantType> queryAll();

    /**
     * 根据code查询
     * @param code
     * @return
     */
    PoliceRelevantType findByCode(String code);

}
