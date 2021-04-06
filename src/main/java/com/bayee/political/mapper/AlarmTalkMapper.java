package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.AlarmEvaluation;
import com.bayee.political.domain.AlarmLeaderStatistics;
import com.bayee.political.domain.AlarmTalk;
import com.bayee.political.domain.AlarmTalkPoliceNum;
import com.bayee.political.domain.AlarmTalkStatistics;
import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.TimeName;

public interface AlarmTalkMapper {

	// 约谈记录新增
	int alarmTalkCreat(AlarmTalk alarmTalk);

	// 约谈记录修改
	int alarmTalkUpdate(AlarmTalk alarmTalk);

	// 约谈记录详情查询(后台)
	AlarmTalk alarmTalkItem(@Param("id") Integer id);

	// 约谈近期列表查询(api)
	List<AlarmTalk> alarmTalkLastList(@Param("hostId") String hostId, @Param("dateTime") String dateTime);

	// 约谈消息列表查询(api)
	List<AlarmTalk> alarmTalkNewsList(@Param("hostId") String hostId);

	// 约谈完成率，未完成率查询
	AlarmTalkStatistics alarmTalkRateItem(@Param("hostId") String hostId, @Param("dateTime") String dateTime);

	// 约谈完成人数查询
	AlarmTalkPoliceNum talkPoliceNumItem(@Param("hostId") String hostId, @Param("dateTime") String dateTime);

	// 约谈未完成人数查询
	AlarmTalkPoliceNum noTalkPoliceNumItem(@Param("hostId") String hostId, @Param("dateTime") String dateTime);

	// 个人谈话预约列表查询
	List<AlarmTalk> alarmTalkPersonalList(@Param("policeId") String policeId, @Param("isReceive") Integer isReceive);

	/**
	 * 获得约谈列表/根据条件查询
	 */
	List<AlarmTalk> getAlarmTalk(@Param("alrmTalk") AlarmTalk alrmTalk, @Param("keywords") String keywords,
			@Param("departmentIds") String departmentIds, @Param("pageSize") Integer pageSize);

	/**
	 * 获得约谈列表数量
	 */
	Integer getAlarmTalkCount(@Param("alrmTalk") AlarmTalk alrmTalk, @Param("departmentIds") String departmentIds,
			@Param("keywords") String keywords);

	// 局领导约谈人数统计
	AlarmLeaderStatistics alarmLeaderTalkStatistics(@Param("policeId") String policeId);

	// 局领导约谈事项查询
	List<AlarmTalk> alarmLeaderTalkList(@Param("policeId") String policeId);

	// 局领导约谈数量累计统计
	AlarmLeaderStatistics alarmLeaderTalkItem(@Param("policeId") String policeId);

	// 各月已约谈人数查询
	List<CalculationChart> alarmTalkMonthNumChart(@Param("hostId") String hostId);

	// 约谈列表查询(定时任务修改约谈状态进程)
	List<AlarmTalk> alarmTalkStatusList();

	// 个人谈话完成数量统计
	int alarmTalkOverNum(@Param("policeId") String policeId);

	// 统计需填写约谈数量
	int alarmTalkFillCount(@Param("hostId") String hostId);

	// 局领导约谈列表查询
	List<AlarmTalk> alarmLeaderTalkPageList(@Param("hostId") String hostId, @Param("dateTime") String dateTime,
			@Param("viewType") Integer viewType, @Param("pageSize") Integer pageSize,
			@Param("pageNum") Integer pageNum);

	// 局领导约谈列表时间查询
	List<TimeName> alarmDatePageList(@Param("hostId") String hostId, @Param("viewType") Integer viewType,
			@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 局领导约谈列表总数查询
	int alarmLeaderTalkPageCount(@Param("hostId") String hostId, @Param("dateTime") String dateTime,
			@Param("viewType") Integer viewType);

	// 查询当前人最新一条约谈记录
	List<AlarmTalk> newAlarmTalkList(@Param("hostId") String hostId);

	// 统计被约谈人需填写反馈数量
	int alarmObjectTalkFillCount(@Param("policeId") String policeId);

	// 被约谈人约谈列表时间查询
	List<TimeName> alarmObjectDatePageList(@Param("policeId") String policeId, @Param("viewType") Integer viewType,
			@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 被约谈人约谈列表查询
	List<AlarmTalk> alarmObjectTalkPageList(@Param("policeId") String policeId, @Param("viewType") Integer viewType,
			@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 被约谈人约谈列表总数查询
	int alarmObjectTalkPageCount(@Param("policeId") String policeId, @Param("viewType") Integer viewType);

	// 局领导约谈人数趋势图
	List<CalculationChart> alarmLeaderTalkLineChart(@Param("policeId") String policeId);

	// 查询最新约谈详情
	AlarmTalk talkNewItem(@Param("alarmRecordId") Integer alarmRecordId, @Param("policeId") String policeId,
			@Param("alarmType") Integer alarmType, @Param("alarmConfigType") Integer alarmConfigType,
			@Param("dateTime") String dateTime);

	// 查询进行中的约谈
	List<AlarmTalk> talkExistList(@Param("policeId") String policeId, @Param("alarmRecordId") Integer alarmRecordId,
			@Param("alarmType") Integer alarmType);
	
	/**
	 * 最新3条已完成或已发起的约谈
	 * @return
	 */
	List<AlarmEvaluation> newThreeTalk();

}