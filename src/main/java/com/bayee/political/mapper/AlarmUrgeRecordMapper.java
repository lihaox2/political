package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.AlarmTimeName;
import com.bayee.political.domain.AlarmUrgeRecord;

public interface AlarmUrgeRecordMapper {
	// 局领导催还护照记录新增
	int alarmUrgeRecordCreat(AlarmUrgeRecord record);

	// 局领导催还护照记录修改
	int alarmUrgeRecordUpdate(AlarmUrgeRecord record);

	// 局领导催还护照记录详情查询
	AlarmUrgeRecord alarmUrgeRecordItem(@Param("id") Integer id, @Param("entryId") Integer entryId,
			@Param("policeId") String policeId, @Param("urgePoliceId") String urgePoliceId);

	// 个人护照催还通知最新查询
	List<AlarmUrgeRecord> alarmPersonalPassportReturnNewestList(@Param("policeId") String policeId,
			@Param("readStatus") Integer readStatus);

	// 个人护照催还通知总数统计
	int alarmPersonalPassportReturnCount(@Param("policeId") String policeId, @Param("readStatus") Integer readStatus);

	// 个人护照催还通知时间查询
	List<AlarmTimeName> alarmPassportDateList(@Param("policeId") String policeId,
			@Param("readStatus") Integer readStatus, @Param("pageSize") Integer pageSize,
			@Param("pageNum") Integer pageNum);

	// 个人护照催还通知分页查询
	List<AlarmUrgeRecord> alarmPersonalPassportReturnList(@Param("policeId") String policeId,
			@Param("readStatus") Integer readStatus, @Param("pageSize") Integer pageSize,
			@Param("pageNum") Integer pageNum);
}