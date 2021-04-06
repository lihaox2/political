package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.Notification;
import com.bayee.political.mapper.NotificationMapper;
import com.bayee.political.service.NotificationService;
import com.bayee.political.domain.HomePageStatistics;

/**
 * @author shentuqiwei
 * @version 2020年8月20日 下午1:13:22
 */
@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationMapper notificationMapper;

	// 通知新增
	@Override
	public int notificationCreat(Notification notification) {
		return notificationMapper.notificationCreat(notification);
	}

	// 通知修改
	@Override
	public int notificationUpdate(Notification notification) {
		return notificationMapper.notificationUpdate(notification);
	}

	// 通知详情查询
	@Override
	public Notification notificationItem(Integer id) {
		return notificationMapper.notificationItem(id);
	}

	// 通知列表查询
	@Override
	public List<Notification> notificationList(String policeId) {
		return notificationMapper.notificationList(policeId);
	}

	// 首页各功能数量统计
	@Override
	public HomePageStatistics homePageStatistics(String policeId) {
		return notificationMapper.homePageStatistics(policeId);
	}
	
	// 民警首页预警数量统计
	@Override
	public int homePageAlarmPoliceCount(String policeId) {
		return notificationMapper.homePageAlarmPoliceCount(policeId);
	}
	
	// 预警查询
	@Override
	public List<Notification> notificationAlarmList(String policeId) {
		return notificationMapper.notificationAlarmList(policeId);
	}
}
