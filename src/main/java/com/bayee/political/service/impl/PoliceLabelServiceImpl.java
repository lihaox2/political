package com.bayee.political.service.impl;

import com.bayee.political.domain.PoliceLabel;
import com.bayee.political.mapper.PoliceLabelMapper;
import com.bayee.political.service.PoliceLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliceLabelServiceImpl implements PoliceLabelService {

    @Autowired
    private PoliceLabelMapper policeLabelMapper;

    /**
     * 查询所有标签
     * @return
     */
    @Override
    public List<PoliceLabel> findLabelAll() {
        return policeLabelMapper.findLabelAll();
    }

    @Override
    public List<PoliceLabel> findLabelByName(String labelName) {
        return policeLabelMapper.findLabelByName(labelName);
    }

    @Override
    public List<PoliceLabel> findByPoliceIdAndDate(String policeId, String date) {
        return policeLabelMapper.findByPoliceIdAndDate(policeId, date);
    }

    @Override
    public List<String> findPoliceLabel(String policeId) {
        return policeLabelMapper.findPoliceLabel(policeId);
    }

    @Override
    public PoliceLabel findById(Integer id) {
        return policeLabelMapper.findById(id);
    }

    @Override
    public void insertPoliceLabel(PoliceLabel policeLabel) {
        policeLabelMapper.insertPoliceLabel(policeLabel);
    }

    @Override
    public Integer countByLabelName(String labelName) {
        return policeLabelMapper.countByLabelName(labelName);
    }
}
