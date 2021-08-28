package com.bayee.political.service.impl;

import com.bayee.political.domain.ExcellentPoliceInfo;
import com.bayee.political.mapper.ExcellentPoliceInfoMapper;
import com.bayee.political.service.ExcellentPoliceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xxl
 * @date 2021/8/14
 */
@Service
public class ExcellentPoliceInfoServiceImpl implements ExcellentPoliceInfoService {

    @Autowired
    ExcellentPoliceInfoMapper excellentPoliceInfoMapper;

    @Override
    public void insertExcellentPoliceInfo(ExcellentPoliceInfo excellentPoliceInfo) {
        excellentPoliceInfoMapper.insert(excellentPoliceInfo);
    }

    @Override
    public void updateExcellentPoliceInfo(ExcellentPoliceInfo excellentPoliceInfo) {
        excellentPoliceInfoMapper.updateByPrimaryKey(excellentPoliceInfo);
    }

    @Override
    public ExcellentPoliceInfo excellentPoliceInfoDetails(Integer id) {
        return excellentPoliceInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void excellentPoliceInfoListDetails(List<ExcellentPoliceInfo> excellentPoliceInfoList) {
        if (excellentPoliceInfoList.size() <= 0) {
            return;
        }

        List<ExcellentPoliceInfo> insertList = new ArrayList<>();
        for (ExcellentPoliceInfo e : excellentPoliceInfoList) {
            Integer flag = excellentPoliceInfoMapper.checkExists(e.getPoliceId(), e.getLabel());
            if (flag == null || flag <= 0) {
                insertList.add(e);
            }
        }

        if (insertList.size() > 0) {
            excellentPoliceInfoMapper.insertExcellentPoliceInfoList(insertList);
        }
    }

    @Override
    public Integer countByPoliceIdAndCount(String policeId, Integer labelId) {
        return excellentPoliceInfoMapper.countByPoliceIdAndCount(policeId, labelId);
    }
}
