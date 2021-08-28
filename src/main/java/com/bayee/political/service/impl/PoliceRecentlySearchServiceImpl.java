package com.bayee.political.service.impl;

import com.bayee.political.domain.PoliceRecentlySearch;
import com.bayee.political.domain.User;
import com.bayee.political.mapper.PoliceRecentlySearchMapper;
import com.bayee.political.service.PoliceRecentlySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PoliceRecentlySearchServiceImpl implements PoliceRecentlySearchService {

    /**
     * 警员全息最近搜素
     */
    @Autowired
    private PoliceRecentlySearchMapper policeRecentlySearchMapper;


    /**
     *  查询警员全息最近搜索
     * @return
     */
    @Override
    public List<PoliceRecentlySearch> findHolographicRecentlySearch() {
        return policeRecentlySearchMapper.findHolographicRecentlySearch();
    }


    @Override
    public void findHolographicRecentlyPoliceId(User user) {
        //根据警号查询全息最近排行信息
        PoliceRecentlySearch search = new PoliceRecentlySearch();
        PoliceRecentlySearch recentlyPoliceId = policeRecentlySearchMapper.findByHolographicRecentlyPoliceId(user.getPoliceId());

        search.setPoliceHeadPhoto(user.getHeadImage());
        search.setPoliceName(user.getName());
        search.setPoliceId(user.getPoliceId());
        search.setPoliceDeptName(user.getDepartmentName());

        if(recentlyPoliceId != null){
            if(recentlyPoliceId.getPoliceId().equals(user.getPoliceId())) {
                search.setId(recentlyPoliceId.getId());
                search.setCreateTime(recentlyPoliceId.getCreateTime());
                search.setUpdateTime(new Date());
                policeRecentlySearchMapper.updateByPrimaryKey(search);
            }
        } else{
            search.setCreateTime(new Date());
            search.setUpdateTime(new Date());
            policeRecentlySearchMapper.insert(search);
        }
    }
}
