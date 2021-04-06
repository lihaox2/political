package com.bayee.political.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.bayee.political.controller.BaseController;
import com.bayee.political.domain.TrainFirearm;
import com.bayee.political.domain.TrainMatch;
import com.bayee.political.domain.TrainMedalManage;
import com.bayee.political.domain.TrainPhysical;
import com.bayee.political.service.TrainMatchService;
import com.bayee.political.service.TrainService;
import com.bayee.political.utils.DateUtils;

/**
 * @author shentuqiwei
 * @version 2020年9月29日 下午3:16:40 训练定时任务
 */
@Component
@EnableScheduling
@Controller
public class TrainTimeTask extends BaseController {

	@Autowired
	private TrainService trainService;

	@Autowired
	private TrainMatchService trainMatchService;

	@Autowired
	private TaskScheduler scheduler;

	public static final Map<Object, ScheduledFuture<?>> scheduledTasks = new IdentityHashMap<>();
	public static final String STARTJOB = "trainJob";

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
	Date date = new Date();
	String dateTime = sdf.format(date);

	String currentYearStr = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

	// AI预警任务执行
	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟
	public void trainTotalRunTask() throws ParseException {
		trainStatusTask();
		matchStatusTask();
		getMedalNumTask();
	}

	// 训练报名状态,训练状态修改任务
	public void trainStatusTask() throws ParseException {
		// 综合训练列表查询（定时任务修改约谈状态进程）
		List<TrainPhysical> physicalList = trainService.physicaStatuslList();
		for (int i = 0; i < physicalList.size(); i++) {
			// 上报开始时间
			long registrationStartTime = physicalList.get(i).getRegistrationStartDate().getTime();
			// 上报结束时间
			long registrationEndTime = physicalList.get(i).getRegistrationEndDate().getTime();
			// 训练开始时间
			long trainStartTime = physicalList.get(i).getTrainStartDate().getTime();
			// 训练结束时间
			long trainEndTime = physicalList.get(i).getTrainEndDate().getTime();
			// 当前时间
			long currentTime = new Date().getTime();
			TrainPhysical item = new TrainPhysical();
			item.setId(physicalList.get(i).getId());
			if (currentTime >= registrationStartTime && currentTime < registrationEndTime) {
				item.setSignUpStatus(2);
			} else if (currentTime >= registrationEndTime) {
				item.setSignUpStatus(3);
			}
			if (currentTime >= trainStartTime && currentTime < trainEndTime) {
				item.setStatus(2);
			} else if (currentTime >= trainEndTime) {
				item.setStatus(3);
			}
			item.setUpdateDate(new Date());
			trainService.trainPhysicalUpdate(item);
		}

		// 枪械列表查询（定时任务修改约谈状态进程）
		List<TrainFirearm> firearmList = trainService.firearmStatuslList();
		for (int i = 0; i < firearmList.size(); i++) {
			// 上报开始时间
			long registrationStartTime = firearmList.get(i).getRegistrationStartDate().getTime();
			// 上报结束时间
			long registrationEndTime = firearmList.get(i).getRegistrationEndDate().getTime();
			// 训练开始时间
			long trainStartTime = firearmList.get(i).getTrainStartDate().getTime();
			// 训练结束时间
			long trainEndTime = firearmList.get(i).getTrainEndDate().getTime();
			// 当前时间
			long currentTime = new Date().getTime();
			TrainFirearm item = new TrainFirearm();
			item.setId(firearmList.get(i).getId());
			if (currentTime >= registrationStartTime && currentTime < registrationEndTime) {
				item.setSignUpStatus(2);
			} else if (currentTime >= registrationEndTime) {
				item.setSignUpStatus(3);
			}
			if (currentTime >= trainStartTime && currentTime < trainEndTime) {
				item.setStatus(2);
			} else if (currentTime >= trainEndTime) {
				item.setStatus(3);
			}
			item.setUpdateDate(new Date());
			trainService.trainFirearmUpdate(item);
		}
//		System.out.println("训练结束: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
	}

	// 比赛报名状态,比赛状态修改任务
//	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟
	public void matchStatusTask() throws ParseException {
		// 比赛列表查询（定时任务修改约谈状态进程）
		List<TrainMatch> physicalList = trainMatchService.matchStatuslList();
		for (int i = 0; i < physicalList.size(); i++) {
			// 上报开始时间
			long registrationStartTime = physicalList.get(i).getRegistrationStartDate().getTime();
			// 上报结束时间
			long registrationEndTime = physicalList.get(i).getRegistrationEndDate().getTime();
			// 比赛开始时间
			long matchStartTime = physicalList.get(i).getMatchStartDate().getTime();
			// 比赛结束时间
			long matchEndTime = physicalList.get(i).getMatchEndDate().getTime();
			// 当前时间
			long currentTime = new Date().getTime();
			TrainMatch item = new TrainMatch();
			item.setId(physicalList.get(i).getId());
			if (currentTime >= registrationStartTime && currentTime < registrationEndTime) {
				item.setSignUpStatus(2);
			} else if (currentTime >= registrationEndTime) {
				item.setSignUpStatus(3);
			}
			if (currentTime >= matchStartTime && currentTime < matchEndTime) {
				item.setStatus(2);
			} else if (currentTime >= matchEndTime) {
				item.setStatus(3);
			}
			item.setUpdateDate(new Date());
			trainMatchService.trainMatchUpdate(item);
		}
//		System.out.println("比赛结束: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
	}

	// 获得奖章数量修改任务
//	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟
	public void getMedalNumTask() throws ParseException {
		// 查询奖章数量
		List<TrainMedalManage> physicalList = trainMatchService.getMedalNumList();
		if (physicalList.size() > 0) {
			for (int i = 0; i < physicalList.size(); i++) {
				TrainMedalManage item = new TrainMedalManage();
				item.setId(physicalList.get(i).getId());
				item.setIssueNum(physicalList.get(i).getIssueNum());
				item.setUpdateDate(new Date());
				trainMatchService.updateTrainMedal(item);
			}
		}
//		System.out.println("奖章数量修改: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
	}
}
