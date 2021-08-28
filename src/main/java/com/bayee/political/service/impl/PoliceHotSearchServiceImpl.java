package com.bayee.political.service.impl;

import com.bayee.political.domain.PoliceHotSearch;
import com.bayee.political.domain.User;
import com.bayee.political.mapper.PoliceHotSearchMapper;
import com.bayee.political.service.PoliceHotSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PoliceHotSearchServiceImpl implements PoliceHotSearchService {

    /**
     * 警员全息热搜排名
     */
    @Autowired
    private PoliceHotSearchMapper policeHotSearchMapper;

    /**
     * 查询警员全息热搜排名
     * @return
     */
    @Override
    public List<PoliceHotSearch> findHolographicHostSearch() {
        return policeHotSearchMapper.findHolographicHostSearch();
    }

    @Override
    public void HolographicSearchHotDetails(User user) {
        //用警号查询热搜排名
        PoliceHotSearch search = new PoliceHotSearch();
        PoliceHotSearch holographicSortNum = policeHotSearchMapper.findHolographicSortNum(user.getPoliceId());
        search.setPoliceHeadPhoto(user.getHeadImage());
        search.setPoliceName(user.getName());
        search.setPoliceId(user.getPoliceId());
        search.setPoliceDeptName(user.getDepartmentName());
        if(holographicSortNum != null){
            if(holographicSortNum.getPoliceId().equals(user.getPoliceId())){
                search.setId(holographicSortNum.getId());
                search.setSortNum(holographicSortNum.getSortNum() + 1);
                search.setCreateTime(holographicSortNum.getCreateTime());
                search.setUpdateTime(new Date());
                //修改
                policeHotSearchMapper.updateByPrimaryKey(search);
            }
        }else{
            search.setSortNum(1);
            search.setCreateTime(new Date());
            search.setUpdateTime(new Date());
            //新增
            policeHotSearchMapper.insert(search);
        }
    }

}
