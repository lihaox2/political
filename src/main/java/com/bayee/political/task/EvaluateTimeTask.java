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
import com.bayee.political.domain.EvaluateTask;
import com.bayee.political.service.EvaluationService;
import com.bayee.political.utils.DateUtils;

/**
 * @author shentuqiwei
 * @version 2020年4月10日 下午4:07:18
 */
@Component
@EnableScheduling
@Controller
public class EvaluateTimeTask extends BaseController {

	@Autowired
	EvaluationService evaluationService;

	@Autowired
	private TaskScheduler scheduler;

	public static final Map<Object, ScheduledFuture<?>> scheduledTasks = new IdentityHashMap<>();
	public static final String STARTJOB = "evaluateJob";

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
	Date date = new Date();
	String dateTime = sdf.format(date);

	String currentYearStr = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

	// 评价任务
	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟
	public void task() throws ParseException {
//		System.out.println("任务开始: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
		// 评价列表查询（定时任务修改任务进程）
		List<EvaluateTask> evaluateTaskTimingList = evaluationService.evaluateTaskTimingList();
		if (evaluateTaskTimingList.size() > 0) {
			for (int i = 0; i < evaluateTaskTimingList.size(); i++) {
//					String startTimeStr = sdf.format(evaluateTaskTimingList.get(i).getStartTime());
//					String endTimeStr = sdf.format(evaluateTaskTimingList.get(i).getEndTime());
//					Date startTimeDate = sdf.parse(startTimeStr);
//					Date endTimeDate = sdf.parse(endTimeStr);
				long startTime = evaluateTaskTimingList.get(i).getStartTime().getTime();
				long endTime = evaluateTaskTimingList.get(i).getEndTime().getTime();
				long currentTime = new Date().getTime();
				EvaluateTask evaluateTask = new EvaluateTask();
				evaluateTask.setId(evaluateTaskTimingList.get(i).getId());
				if (startTime < currentTime && currentTime < endTime) {
					evaluateTask.setProcess(1);
					evaluateTask.setUpdateDate(new Date());
				} else if (currentTime >= endTime) {
					EvaluateTask eItem = evaluationService.evaluateTaskItem(evaluateTaskTimingList.get(i).getId());
					if (eItem != null) {
						boolean isTrue = new Date().before(eItem.getEndTime());
						if (isTrue == false) {
							evaluateTask.setProcess(2);
							evaluateTask.setUpdateDate(new Date());
						}
					}
				}
				evaluationService.evaluateTaskUpdate(evaluateTask);
			}
		}
//		System.out.println("任务结束: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
	}

}
