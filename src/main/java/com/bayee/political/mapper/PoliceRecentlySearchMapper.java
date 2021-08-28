package com.bayee.political.mapper;

import com.bayee.political.domain.PoliceRecentlySearch;

import java.util.List;

public interface PoliceRecentlySearchMapper {


    /**
     * 新增警员全息最近搜索
     * @param record
     * @return
     */
    Integer insert(PoliceRecentlySearch record);


    /**
     * 查询警员全息最近搜索
     * @return
     */
    List<PoliceRecentlySearch> findHolographicRecentlySearch();


    /**
     * 根据警号查询警员全息最近排行
     * @param policeId
     * @return
     */
    PoliceRecentlySearch findByHolographicRecentlyPoliceId(String policeId);


    /**
     * 修改警员全息最近搜索
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(PoliceRecentlySearch record);


}