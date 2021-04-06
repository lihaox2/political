package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.LeavePointsExchangeRecord;

public interface LeavePointsExchangeRecordMapper {

	// 积分兑换记录新增
	int leavePointsExchangeRecordCreat(LeavePointsExchangeRecord record);

	// 积分兑换记录修改
	int leavePointsExchangeRecordUpdate(LeavePointsExchangeRecord record);

	// 个人最新积分兑换记录查询
	List<LeavePointsExchangeRecord> leavePointsExchangeRecordNewestList(@Param("policeId") String policeId);

	// 个人积分兑换记录查询
	List<LeavePointsExchangeRecord> leavePointsExchangeRecordList(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime, @Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 个人积分兑换记录总数统计
	int leavePointsExchangeRecordCount(@Param("policeId") String policeId, @Param("dateTime") String dateTime);

	// 个人积分兑换记录详情查询
	LeavePointsExchangeRecord leavePointsExchangeRecordItem(@Param("id") Integer id);

}