package com.bayee.political.mapper;

import com.bayee.political.domain.PoliceHotSearch;

import java.util.List;

public interface PoliceHotSearchMapper {

    /**
     * 新增警员全息热搜排名
     * @param policeHotSearch
     * @return
     */
    Integer insert(PoliceHotSearch policeHotSearch);

    /**
     *  查询警员全息热搜排名
     * @return
     */
    List<PoliceHotSearch> findHolographicHostSearch();

    /**
     *  根据警号查询热搜信息
     * @param policeId
     * @return
     */
    PoliceHotSearch findHolographicSortNum(String policeId);

    /**
     * 修改警员全息热搜排名
     * @param policeHotSearch
     * @return
     */
    Integer updateByPrimaryKey(PoliceHotSearch policeHotSearch);

}