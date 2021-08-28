package com.bayee.political.mapper;

import com.bayee.political.domain.ConductBureauRecordTypeViews;
import com.bayee.political.domain.ConductBureauRecordViews;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/19
 */
public interface ConductBureauRecordViewsMapper {

    List<ConductBureauRecordViews> findAll();

}
