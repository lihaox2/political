package com.bayee.political.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author xxl
 * @date 2021/6/14
 */
public interface RiskHolographicService {

    /**
     * 查询警员职业生涯信息
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    List<Object> getPoliceCareer(String policeId, String year, String month, Integer type);

    /**
     * 职业生涯类型
     * @return
     */
    List<Map<String, Serializable>> getPoliceCareerType();

}
