package com.bayee.political.mapper;

import com.bayee.political.domain.DisciplinaryActionTypeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DisciplinaryActionTypeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DisciplinaryActionTypeInfo record);

    int insertSelective(DisciplinaryActionTypeInfo record);

    DisciplinaryActionTypeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DisciplinaryActionTypeInfo record);

    int updateByPrimaryKeyWithBLOBs(DisciplinaryActionTypeInfo record);

    int updateByPrimaryKey(DisciplinaryActionTypeInfo record);

    /**
     * 查询全部的纪律处分类型信息
     * @return
     */
    List<DisciplinaryActionTypeInfo> selectList();

    /**
     * 根据父级id查询纪律处分类型信息
     */
    List<DisciplinaryActionTypeInfo> selectParentList(@Param("parentId") Integer parentId);

}