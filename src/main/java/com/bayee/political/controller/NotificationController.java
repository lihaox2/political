package com.bayee.political.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bayee.political.domain.AlarmLeaderStatistics;
import com.bayee.political.domain.HomePageStatistics;
import com.bayee.political.domain.Notification;
import com.bayee.political.domain.User;
import com.bayee.political.service.AlarmService;
import com.bayee.political.service.NotificationService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.StatusCode;
import com.taobao.api.ApiException;

/**
 * @author shentuqiwei
 * @version 2020年8月20日 下午1:12:37
 */
@Controller
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private UserService userService;

	// 首页各功能数量统计
	@RequestMapping(value = "/home/page/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> homePageStatistics(@RequestParam(value = "policeId", required = true) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String hostId = policeId;
		User userItem = userService.policeItem(policeId, null, null);
		HomePageStatistics homeItem = new HomePageStatistics();
		// 局领导当前月预警提醒人数统计
		AlarmLeaderStatistics alarmLeader = alarmService.alarmLeaderRemindStatistics(policeId);
		int alarmLeaderNum = alarmLeader.getTotalNum();
		// 局领导统计需填写约谈数量
		int leaderNum = alarmService.alarmTalkFillCount(hostId);
		// 民警首页预警数量统计
		int policeNum = notificationService.homePageAlarmPoliceCount(policeId);
		if (userItem.getPositionId() == 1) {// 局领导
			homeItem.setAlarmNum(alarmLeaderNum + leaderNum);
		} else if (userItem.getPositionId() == 2 || userItem.getPositionId() == 3) {// 中层干部
			homeItem.setAlarmNum(alarmLeaderNum + leaderNum + policeNum);
		} else if (userItem.getPositionId() == 4) {// 民警
			homeItem.setAlarmNum(policeNum);
		}
		HomePageStatistics item = notificationService.homePageStatistics(policeId);
		homeItem.setEvaluateNum(item.getEvaluateNum());
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(homeItem);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 预警,通知列表查询
	@RequestMapping(value = "/notification/list", method = RequestMethod.GET)
	public ResponseEntity<?> notificationList(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<Notification> list = new ArrayList<Notification>();
		if (type == null || type == 1) {
			// 通知查询
			list = notificationService.notificationList(policeId);
		} else if (type == 2) {
			// 预警查询
			list = notificationService.notificationAlarmList(policeId);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

}
