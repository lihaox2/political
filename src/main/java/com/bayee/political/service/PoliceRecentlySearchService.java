package com.bayee.political.service;

import com.bayee.political.domain.PoliceRecentlySearch;
import com.bayee.political.domain.User;

import java.util.List;

public interface PoliceRecentlySearchService {


    /**
     * 查询警员全息最近搜索
     * @return
     */
    List<PoliceRecentlySearch> findHolographicRecentlySearch();


    void findHolographicRecentlyPoliceId(User user);



}