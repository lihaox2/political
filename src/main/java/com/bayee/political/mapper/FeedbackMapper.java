package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.Feedback;

public interface FeedbackMapper {

	// 反馈意见删除(api)
	int feedbackApiDelete(@Param("id") Integer id);

	// 反馈意见新增(api)
	int feedbackApiCreat(Feedback feedback);

	// 反馈意见修改(api)
	int feedbackApiUpdate(Feedback feedback);

	// 反馈意见列表查询（api）
	List<Feedback> feedbackApiList(@Param("policeId") String policeId);

	/**
	 * 获得反馈列表
	 */
	List<Feedback> getFeedback(@Param("pageSize") Integer pageSize, @Param("departmentId") Integer departmentId,
			@Param("searchValue") String searchValue);

	/**
	 * 获得反馈列表总条数
	 * 
	 * @param searchValue
	 * @param departmentId
	 */
	Integer getFeedbackSum(@Param("departmentId") Integer departmentId, @Param("searchValue") String searchValue);

	/**
	 * 根据id查询反馈详情
	 */
	Feedback getFeedbackById(Integer id);

}