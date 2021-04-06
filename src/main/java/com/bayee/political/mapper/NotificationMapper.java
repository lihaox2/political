package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.HomePageStatistics;
import com.bayee.political.domain.Notification;

public interface NotificationMapper {

	int deleteByPrimaryKey(Integer id);

	// 通知新增
	int notificationCreat(Notification notification);

	// 通知修改
	int notificationUpdate(Notification notification);

	// 通知详情查询
	Notification notificationItem(@Param("id") Integer id);

	// 通知列表查询
	List<Notification> notificationList(@Param("policeId") String policeId);

	// 首页各功能数量统计
	HomePageStatistics homePageStatistics(@Param("policeId") String policeId);

	// 民警首页预警数量统计
	int homePageAlarmPoliceCount(@Param("policeId") String policeId);

	// 预警查询
	List<Notification> notificationAlarmList(@Param("policeId") String policeId);

}