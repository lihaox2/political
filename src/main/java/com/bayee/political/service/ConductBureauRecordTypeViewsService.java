package com.bayee.political.service;

import com.bayee.political.domain.ConductBureauRecordTypeViews;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/20
 */
public interface ConductBureauRecordTypeViewsService {

    /**
     * 查询所有局规记分类型
     * @return
     */
    List<ConductBureauRecordTypeViews> findAll();

}
