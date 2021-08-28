package com.bayee.political.service;

import com.bayee.political.domain.ExcellentPoliceInfo;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/14
 */
public interface ExcellentPoliceInfoService {

    /**
     * 添加人才库信息
     * @param excellentPoliceInfo
     */
    void insertExcellentPoliceInfo(ExcellentPoliceInfo excellentPoliceInfo);

    /**
     * 修改人才库信息
     * @param excellentPoliceInfo
     */
    void updateExcellentPoliceInfo(ExcellentPoliceInfo excellentPoliceInfo);

    /**
     * 人才库详情
     * @param id
     * @return
     */
    ExcellentPoliceInfo excellentPoliceInfoDetails(Integer id);

    /**
     * 人才库信息批量处理
     * @param excellentPoliceInfoList
     */
    void excellentPoliceInfoListDetails(List<ExcellentPoliceInfo> excellentPoliceInfoList);

    /**
     *
     * @param policeId
     * @param labelId
     * @return
     */
    Integer countByPoliceIdAndCount(String policeId, Integer labelId);
}
