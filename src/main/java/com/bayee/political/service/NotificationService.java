package com.bayee.political.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bayee.political.domain.HomePageStatistics;
import com.bayee.political.domain.Notification;

/**
 * @author shentuqiwei
 * @version 2020年8月20日 下午1:13:05
 */
@Service
public interface NotificationService {

	// 通知新增
	int notificationCreat(Notification notification);

	// 通知修改
	int notificationUpdate(Notification notification);

	// 通知详情查询
	Notification notificationItem(Integer id);

	// 通知列表查询
	List<Notification> notificationList(String policeId);

	// 首页各功能数量统计
	HomePageStatistics homePageStatistics(String policeId);

	// 民警首页预警数量统计
	int homePageAlarmPoliceCount(String policeId);

	// 预警查询
	List<Notification> notificationAlarmList(String policeId);
}
