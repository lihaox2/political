package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.domain.FeedbackType;

public interface FeedbackTypeMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(FeedbackType record);

	int insertSelective(FeedbackType record);

	FeedbackType selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(FeedbackType record);

	int updateByPrimaryKey(FeedbackType record);

	// 反馈意见类型查询
	List<FeedbackType> feedbackTypeApiList();
}