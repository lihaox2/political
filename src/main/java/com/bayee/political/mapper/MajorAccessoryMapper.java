package com.bayee.political.mapper;

import com.bayee.political.domain.MajorAccessory;

import java.util.List;

public interface MajorAccessoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MajorAccessory record);

    int insertSelective(MajorAccessory record);

    MajorAccessory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MajorAccessory record);

    int updateByPrimaryKey(MajorAccessory record);

    List<MajorAccessory> selectByReportId(Integer id);
}