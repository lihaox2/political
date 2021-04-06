package com.bayee.political.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.Feedback;
import com.bayee.political.domain.FeedbackType;
import com.bayee.political.mapper.FeedbackMapper;
import com.bayee.political.mapper.FeedbackTypeMapper;
import com.bayee.political.service.FeedbackService;

/**
 * @author shentuqiwei
 * @version 2020年6月5日 下午1:11:45
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackMapper feedbackMapper;

	@Autowired
	FeedbackTypeMapper feedbackTypeMapper;

	// 反馈意见类型查询(api)
	@Override
	public List<FeedbackType> feedbackTypeApiList() {
		return feedbackTypeMapper.feedbackTypeApiList();
	}

	// 反馈意见新增(api)
	@Override
	public int feedbackApiCreat(Feedback feedback) {
		return feedbackMapper.feedbackApiCreat(feedback);
	}

	// 反馈意见修改(api)
	@Override
	public int feedbackApiUpdate(Feedback feedback) {
		return feedbackMapper.feedbackApiUpdate(feedback);
	}

	// 反馈意见删除(api)
	@Override
	public int feedbackApiDelete(Integer id) {
		return feedbackMapper.feedbackApiDelete(id);
	}

	// 反馈意见列表查询（api）
	@Override
	public List<Feedback> feedbackApiList(String policeId) {
		return feedbackMapper.feedbackApiList(policeId);
	}

	@Override
	public List<Feedback> getFeedback(@Param("pageSize") Integer pageSize, @Param("departmentId") Integer departmentId,
			@Param("searchValue") String searchValue) {
		return feedbackMapper.getFeedback(pageSize, departmentId, searchValue);
	}

	@Override
	public Integer getFeedbackSum(Integer departmentId, String searchValue) {
		return feedbackMapper.getFeedbackSum(departmentId, searchValue);
	}

	@Override
	public Feedback getFeedbackById(Integer id) {
		return feedbackMapper.getFeedbackById(id);
	}
}
