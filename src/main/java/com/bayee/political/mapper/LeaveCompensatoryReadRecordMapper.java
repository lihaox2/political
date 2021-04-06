package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.LeaveCompensatoryReadRecord;

public interface LeaveCompensatoryReadRecordMapper {

	// 局领导调休提醒记录新增
	int leaveCompensatoryReadRecordCreat(LeaveCompensatoryReadRecord record);

	// 局领导调休提醒记录修改
	int leaveCompensatoryReadRecordUpdate(LeaveCompensatoryReadRecord record);

	// 局领导最新调休提醒查询
	List<LeaveCompensatoryReadRecord> leaveLeaderCompensatoryRecordNewestList(@Param("policeId") String policeId,
			@Param("approvedReadStatus") Integer approvedReadStatus);

	// 局领导调休提醒分页查询
	List<LeaveCompensatoryReadRecord> leaveLeaderCompensatoryRecordList(@Param("policeId") String policeId,
			@Param("type") Integer type, @Param("dateTime") String dateTime,
			@Param("approvedReadStatus") Integer approvedReadStatus, @Param("pageSize") Integer pageSize,
			@Param("pageNum") Integer pageNum);

	// 局领导调休提醒记录总数统计
	int leaveLeaderCompensatoryRecordCount(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("dateTime") String dateTime, @Param("approvedReadStatus") Integer approvedReadStatus);

}