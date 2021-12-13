package com.bayee.political.data.injection.mapper;

import com.bayee.political.data.injection.domain.PoliceSituationInfo;

import java.util.List;

public interface PoliceSituationInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceSituationInfo record);

    PoliceSituationInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeyWithBLOBs(PoliceSituationInfo record);

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