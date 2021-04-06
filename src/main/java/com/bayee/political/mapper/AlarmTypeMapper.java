package com.bayee.political.mapper;

import com.bayee.political.domain.AlarmType;

public interface AlarmTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlarmType record);

    int insertSelective(AlarmType record);

    AlarmType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlarmType record);

    int updateByPrimaryKey(AlarmType record);
}