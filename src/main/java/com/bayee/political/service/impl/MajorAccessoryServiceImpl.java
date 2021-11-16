package com.bayee.political.service.impl;

import com.bayee.political.domain.MajorAccessory;
import com.bayee.political.mapper.MajorAccessoryMapper;
import com.bayee.political.service.MajorAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/4
 */
@Service
public class MajorAccessoryServiceImpl implements MajorAccessoryService {

    @Autowired
    private MajorAccessoryMapper accessoryMapper;

    @Override
    public void addAccessory(MajorAccessory accessory) {
        accessoryMapper.insert(accessory);
    }

    @Override
    public void updateAccessory(MajorAccessory accessory) {
        accessoryMapper.updateByPrimaryKey(accessory);
    }

    @Override
    public MajorAccessory findById(Integer id) {
        return accessoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MajorAccessory> findByReportId(Integer id) {
        return accessoryMapper.selectByReportId(id);
    }

    @Override
    public void deleteByReportId(Integer id) {
        List<MajorAccessory> accessoryList = accessoryMapper.selectByReportId(id);
        for (MajorAccessory accessory : accessoryList) {
            accessoryMapper.deleteByPrimaryKey(accessory.getId());
        }
    }

    @Override
    public void deleteAccessory(Integer id) {
        accessoryMapper.deleteByPrimaryKey(id);
    }
}
