package com.bayee.political.service;

import com.bayee.political.domain.PoliceKind;
import com.bayee.political.pojo.dto.PoliceKindDO;

import java.util.List;

public interface PoliceKindService {

    /**
     * 查询所有警种信息
     * @return
     */
    List<PoliceKind> findAll();

    /**
     * 宣传报道警种排名TOP5
     * @return
     */
    List<PoliceKindDO> findPoliceRanKind();
}
