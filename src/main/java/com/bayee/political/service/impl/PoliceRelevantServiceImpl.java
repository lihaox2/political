package com.bayee.political.service.impl;

import com.bayee.political.domain.PoliceRelevant;
import com.bayee.political.json.PoliceRelevantPageQueryParam;
import com.bayee.political.mapper.PoliceRelevantMapper;
import com.bayee.political.pojo.PoliceRelevantPageResultDO;
import com.bayee.political.service.PoliceRelevantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/9/4 19:09
 */
@Service
public class PoliceRelevantServiceImpl implements PoliceRelevantService {

    @Autowired
    PoliceRelevantMapper policeRelevantMapper;

    @Override
    public List<PoliceRelevantPageResultDO> policeRelevantPage(PoliceRelevantPageQueryParam queryParam) {
        if (queryParam.getPageIndex() == null || queryParam.getPageIndex() < 1) {
            queryParam.setPageIndex(1);
        }
        if (queryParam.getPageSize() < 10) {
            queryParam.setPageSize(10);
        }
        queryParam.setPageIndex((queryParam.getPageIndex() - 1) * queryParam.getPageSize());

        return policeRelevantMapper.policeRelevantPage(queryParam);
    }

    @Override
    public Integer policeRelevantPageCount(PoliceRelevantPageQueryParam queryParam) {
        return policeRelevantMapper.policeRelevantPageCount(queryParam);
    }

    @Override
    public void insertPoliceRelevant(PoliceRelevant policeRelevant) {
        policeRelevantMapper.insert(policeRelevant);
    }

    @Override
    public void updatePoliceRelevant(PoliceRelevant policeRelevant) {
        policeRelevantMapper.updateByPrimaryKey(policeRelevant);
    }

    @Override
    public void deleteById(Integer id) {
        policeRelevantMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PoliceRelevant findById(Integer id) {
        return policeRelevantMapper.selectByPrimaryKey(id);
    }
}
