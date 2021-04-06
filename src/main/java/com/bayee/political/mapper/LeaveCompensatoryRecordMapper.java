package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.LeaveCompensatoryRecord;

public interface LeaveCompensatoryRecordMapper {

	// 调休记录新增
	int leaveCompensatoryRecordCreat(LeaveCompensatoryRecord leaveCompensatoryRecord);

	// 调休记录修改
	int leaveCompensatoryRecordUpdate(LeaveCompensatoryRecord leaveCompensatoryRecord);

	// 个人最新调休记录查询
	List<LeaveCompensatoryRecord> leaveCompensatoryRecordNewestList(@Param("policeId") String policeId,
			@Param("readStatus") Integer readStatus);

	// 个人调休记录查询
	List<LeaveCompensatoryRecord> leaveCompensatoryRecordList(@Param("policeId") String policeId,
			@Param("type") Integer type, @Param("dateTime") String dateTime, @Param("readStatus") Integer readStatus,
			@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 个人调休记录总数统计
	int leaveCompensatoryRecordCount(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("dateTime") String dateTime, @Param("readStatus") Integer readStatus);

	// 个人调休记录详情查询
	LeaveCompensatoryRecord leaveCompensatoryRecordItem(@Param("id") Integer id);

	// 待办事项查询(2条)
	List<LeaveCompensatoryRecord> leaveLeaderNeedDealtNewestList(@Param("policeId") String policeId);

	// 完成事项总数统计
	int leaveLeaderNeedDealtNewestCount(@Param("policeId") String policeId);

	// 待办事项分页查询
	List<LeaveCompensatoryRecord> leaveLeaderNeedDealtList(@Param("policeId") String policeId,
			@Param("type") Integer type, @Param("dateTime") String dateTime, @Param("pageSize") Integer pageSize,
			@Param("pageNum") Integer pageNum);

	// 待办事项总数统计
	int leaveLeaderNeedDealtCount(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("dateTime") String dateTime);

	// 已办事项分页查询
	List<LeaveCompensatoryRecord> leaveLeaderOverDealtList(@Param("policeId") String policeId,
			@Param("type") Integer type, @Param("dateTime") String dateTime, @Param("pageSize") Integer pageSize,
			@Param("pageNum") Integer pageNum);

	// 已办事项总数统计
	int leaveLeaderOverDealtCount(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("dateTime") String dateTime);

	// 调休记录列表查询(定时任务修改调休记录状态进程)
	List<LeaveCompensatoryRecord> leaveCompensatoryRecordStatusList();

	// 调休人读取状态修改
	int leaveReadUpdate(LeaveCompensatoryRecord record);

	// 审批人读取状态修改
	int leaveApprovedReadUpdate(LeaveCompensatoryRecord record);
	
	/**
	 * 获得调休记录
	 * @return
	 */
	List<LeaveCompensatoryRecord> getLeaveCompensatoryRecord(@Param("departmentId") Integer departmentId,
			@Param("positionId") Integer positionId, @Param("keyword") String keyword,
			@Param("pageSize") Integer pageSize);
	
	/**
	 * 获得调休记录数量
	 * @return
	 */
	Integer getLeaveCompensatoryRecordCount(@Param("departmentId") Integer departmentId,
			@Param("positionId") Integer positionId, @Param("keyword") String keyword);
	
	/**
	 * 根据id查询调休详情
	 * @param id
	 * @return
	 */
	LeaveCompensatoryRecord getLeaveCompensatoryRecordOne(@Param("id") Integer id);
	
}