package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.domain.TrainUnit;

public interface TrainUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrainUnit record);

    int insertSelective(TrainUnit record);

    TrainUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainUnit record);

    int updateByPrimaryKey(TrainUnit record);
    
    /**
     * 查询所有换算单位
     * @return
     */
    List<TrainUnit> getTrainUnitList();
    
}