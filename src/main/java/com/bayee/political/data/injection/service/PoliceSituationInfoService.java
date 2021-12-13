package com.bayee.political.data.injection.service;

import com.bayee.political.data.injection.domain.PoliceSituationInfo;

import java.util.List;

/**
 * @author xxl
 * @date 2021/12/8
 */
public interface PoliceSituationInfoService {

    /**
     * 检查执法办案id是否存在
     * @param idPG
     * @return
     */
    boolean checkIdPGExists(String idPG);

    /**
     * 批量新增
     * @param infoList
     */
    void insertPoliceSituationInfoList(List<PoliceSituationInfo> infoList);

}
