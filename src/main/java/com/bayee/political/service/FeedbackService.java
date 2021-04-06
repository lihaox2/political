package com.bayee.political.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.Feedback;
import com.bayee.political.domain.FeedbackType;

/**
 * @author shentuqiwei
 * @version 2020年6月5日 下午1:10:59
 */
@Service
public interface FeedbackService {

	// 反馈意见类型查询(api)
	List<FeedbackType> feedbackTypeApiList();

	// 反馈意见新增(api)
	int feedbackApiCreat(Feedback feedback);

	// 反馈意见修改(api)
	int feedbackApiUpdate(Feedback feedback);

	// 反馈意见删除(api)
	int feedbackApiDelete(Integer id);

	// 反馈意见列表查询（api）
	List<Feedback> feedbackApiList(String policeId);

	/**
	 * 获得反馈列表
	 */
	List<Feedback> getFeedback(@Param("pageSize") Integer pageSize, 
			@Param("departmentId") Integer departmentId, 
			@Param("searchValue") String searchValue);
	
	/**
	 * 获得反馈列表总条数
	 * @param searchValue 
	 * @param departmentId 
	 */
	Integer getFeedbackSum(Integer departmentId, String searchValue);
	
	/**
	 * 根据id查询反馈详情
	 */
	Feedback getFeedbackById(Integer id);
	
}
