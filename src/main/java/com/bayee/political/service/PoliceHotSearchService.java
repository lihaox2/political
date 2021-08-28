package com.bayee.political.service;

import com.bayee.political.domain.PoliceHotSearch;
import com.bayee.political.domain.User;

import java.util.List;

public interface PoliceHotSearchService {

    /**
     * 查询警员全息如搜榜
     * @return
     */
    List<PoliceHotSearch> findHolographicHostSearch();


    void HolographicSearchHotDetails(User user);

}