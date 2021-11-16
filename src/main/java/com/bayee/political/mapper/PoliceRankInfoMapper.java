package com.bayee.political.mapper;

import com.bayee.political.domain.PoliceRankInfo;

public interface PoliceRankInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceRankInfo record);

    int insertSelective(PoliceRankInfo record);

    PoliceRankInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoliceRankInfo record);

    int updateByPrimaryKeyWithBLOBs(PoliceRankInfo record);

    int updateByPrimaryKey(PoliceRankInfo record);
}