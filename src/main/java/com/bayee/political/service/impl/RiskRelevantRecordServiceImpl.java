package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskRelevantRecord;
import com.bayee.political.json.RiskRelevantPageQueryParam;
import com.bayee.political.mapper.RiskRelevantRecordMapper;
import com.bayee.political.pojo.RiskRelevantPageResultDO;
import com.bayee.political.service.RiskRelevantRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/9/4 19:09
 */
@Service
public class RiskRelevantRecordServiceImpl implements RiskRelevantRecordService {

    @Autowired
    RiskRelevantRecordMapper policeRelevantMapper;

    @Override
    public List<RiskRelevantPageResultDO> policeRelevantPage(RiskRelevantPageQueryParam queryParam) {
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
    public Integer policeRelevantPageCount(RiskRelevantPageQueryParam queryParam) {
        return policeRelevantMapper.policeRelevantPageCount(queryParam);
    }

    @Override
    public void insertPoliceRelevant(RiskRelevantRecord policeRelevant) {
        policeRelevantMapper.insert(policeRelevant);
    }

    @Override
    public void updatePoliceRelevant(RiskRelevantRecord policeRelevant) {
        policeRelevantMapper.updateByPrimaryKey(policeRelevant);
    }

    @Override
    public void deleteById(Integer id) {
        policeRelevantMapper.deleteByPrimaryKey(id);
    }

    @Override
    public RiskRelevantRecord findById(Integer id) {
        return policeRelevantMapper.selectByPrimaryKey(id);
    }
}
