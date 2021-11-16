package com.bayee.political.service.impl;

import com.bayee.political.domain.PolicePosition;
import com.bayee.political.mapper.PolicePositionMapper;
import com.bayee.political.service.PolicePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/8
 */
@Service
public class PolicePositionServiceImpl implements PolicePositionService {

    @Autowired
    private PolicePositionMapper positionMapper;

    @Override
    public List<PolicePosition> findAll() {
        return positionMapper.policePositionList();
    }
}
