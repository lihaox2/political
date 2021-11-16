package com.bayee.political.mapper;

import com.bayee.political.domain.DisciplinaryActionOfficeInfo;

import java.util.List;

public interface DisciplinaryActionOfficeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DisciplinaryActionOfficeInfo record);

    int insertSelective(DisciplinaryActionOfficeInfo record);

    DisciplinaryActionOfficeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DisciplinaryActionOfficeInfo record);

    int updateByPrimaryKeyWithBLOBs(DisciplinaryActionOfficeInfo record);

    int updateByPrimaryKey(DisciplinaryActionOfficeInfo record);

    /**
     * 查询全部纪律处分机关
      * @return
     */
    List<DisciplinaryActionOfficeInfo> selectList();
}