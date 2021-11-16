package com.bayee.political.mapper;

import com.bayee.political.domain.DisciplinaryActionLevelInfo;

import java.util.List;

public interface DisciplinaryActionLevelInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DisciplinaryActionLevelInfo record);

    int insertSelective(DisciplinaryActionLevelInfo record);

    DisciplinaryActionLevelInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DisciplinaryActionLevelInfo record);

    int updateByPrimaryKeyWithBLOBs(DisciplinaryActionLevelInfo record);

    int updateByPrimaryKey(DisciplinaryActionLevelInfo record);

    /**
     * 查询全部的纪律处分机关级别
     */
    List<DisciplinaryActionLevelInfo> selectList();
}