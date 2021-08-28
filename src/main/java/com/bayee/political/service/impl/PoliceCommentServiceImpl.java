package com.bayee.political.service.impl;

import com.bayee.political.domain.PoliceComment;
import com.bayee.political.mapper.PoliceCommentMapper;
import com.bayee.political.service.PoliceCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/6/5
 */
@Service
public class PoliceCommentServiceImpl implements PoliceCommentService {

    @Autowired
    PoliceCommentMapper policeCommentMapper;

    @Override
    public List<PoliceComment> findCommentByPoliceId(String policeId) {
        return policeCommentMapper.findCommentByPoliceId(policeId);
    }

    @Override
    public PoliceComment findNewCommentByPoliceId(String policeId) {
        return policeCommentMapper.findNewCommentByPoliceId(policeId);
    }

    @Override
    public List<PoliceComment> findCommentByPoliceIdAndYearAndMonth(String policeId, String year, String month) {
        return policeCommentMapper.findCommentByPoliceIdAndYearAndMonth(policeId, year, month);
    }

    @Override
    public List<PoliceComment> findPoliceCommentByPoliceIdAndDate(String policeId, String date) {
        return policeCommentMapper.findPoliceCommentByPoliceIdAndDate(policeId, date);
    }
}
