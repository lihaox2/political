package com.bayee.political.data.injection.mapper;

import com.bayee.political.data.injection.domain.TrafficViolation;

public interface TrafficViolationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrafficViolation record);

    TrafficViolation selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(TrafficViolation record);
}