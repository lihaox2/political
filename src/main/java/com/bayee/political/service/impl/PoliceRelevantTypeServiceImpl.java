package com.bayee.political.service.impl;

import com.bayee.political.domain.PoliceRelevantType;
import com.bayee.political.mapper.PoliceRelevantTypeMapper;
import com.bayee.political.service.PoliceRelevantTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/9/4 19:17
 */
@Service
public class PoliceRelevantTypeServiceImpl implements PoliceRelevantTypeService {

    @Autowired
    PoliceRelevantTypeMapper policeRelevantTypeMapper;

    @Override
    public List<PoliceRelevantType> queryAll() {
        return policeRelevantTypeMapper.queryAll();
    }

    @Override
    public PoliceRelevantType findByCode(String code) {
        return policeRelevantTypeMapper.selectByPrimaryKey(code);
    }
}
