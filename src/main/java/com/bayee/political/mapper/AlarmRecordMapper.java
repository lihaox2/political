package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.AlarmRecord;
import com.bayee.political.domain.AlarmRecordTimeName;

public interface AlarmRecordMapper {

	// 预警记录新增
	int alarmRecordCreat(AlarmRecord alarmRecord);

	// 预警记录修改
	int alarmRecordUpdate(AlarmRecord alarmRecord);

	/**
	 * 根据policeId查询本年月是否存在预警记录
	 * 
	 * @return
	 */
	AlarmRecord getAlarmRecord(@Param("policeId") String policeId, @Param("type") Integer type, 
			@Param("configTimeType") Integer configTimeType);

	// 局领导最新预警查询
	List<AlarmRecord> alarmLeaderNewestList(@Param("policeId") String policeId);

	// 局领导预警列表查询
	List<AlarmRecord> alarmBuckleList(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("dateTime") String dateTime, @Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 局领导预警列表数量统计
	int alarmBuckleListCount(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("dateTime") String dateTime);

	// 局领导预警列表时间查询
	List<AlarmRecordTimeName> alarmRecordDatePageList(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("dateTime") String dateTime, @Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 局领导加分预警列表查询
	List<AlarmRecord> alarmAddList(@Param("policeId") String policeId, @Param("dateTime") String dateTime);

	// 最近一年总预警数统计
	int personalAlarmTotalNum(@Param("scoredPoliceId") String scoredPoliceId);

	// 预警详情查询
	AlarmRecord alarmPoliceRecordItem(@Param("id") Integer id, @Param("policeId") String policeId,
			@Param("alarmConfigType") Integer alarmConfigType, @Param("type") Integer type);

}