package com.bayee.political.service.impl;

import com.bayee.political.domain.PoliceKind;
import com.bayee.political.mapper.PoliceKindMapper;
import com.bayee.political.pojo.dto.PoliceKindDO;
import com.bayee.political.service.PoliceKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliceKindServiceImpl implements PoliceKindService {

    @Autowired
    private PoliceKindMapper policeKindMapper; //警种

    /**
     * 查询所有警种信息
     * @return
     */
    @Override
    public List<PoliceKind> findAll() {
        return policeKindMapper.findAll();
    }

    @Override
    public List<PoliceKindDO> findPoliceRanKind() {
        return policeKindMapper.findPoliceRanKind();
    }

}
