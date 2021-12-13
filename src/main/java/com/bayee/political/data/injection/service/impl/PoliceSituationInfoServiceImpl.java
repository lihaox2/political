package com.bayee.political.data.injection.service.impl;

import com.bayee.political.data.injection.domain.PoliceSituationInfo;
import com.bayee.political.data.injection.mapper.PoliceSituationInfoMapper;
import com.bayee.political.data.injection.service.PoliceSituationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/12/8
 */
@Service
public class PoliceSituationInfoServiceImpl implements PoliceSituationInfoService {

    @Autowired
    PoliceSituationInfoMapper policeSituationInfoMapper;

    @Override
    public boolean checkIdPGExists(String idPG) {
        return policeSituationInfoMapper.checkIdPGExists(idPG);
    }

    @Override
    public void insertPoliceSituationInfoList(List<PoliceSituationInfo> infoList) {
        policeSituationInfoMapper.insertPoliceSituationInfoList(infoList);
    }
}
