package com.bayee.political.mapper;

import com.bayee.political.domain.PolicePromotionRecordInfo;

import java.util.List;

public interface PolicePromotionRecordInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PolicePromotionRecordInfo record);

    int insertSelective(PolicePromotionRecordInfo record);

    PolicePromotionRecordInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PolicePromotionRecordInfo record);

    int updateByPrimaryKeyWithBLOBs(PolicePromotionRecordInfo record);

    int updateByPrimaryKey(PolicePromotionRecordInfo record);

//    List<PolicePromotionRecordInfo> selectPageList();
}