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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bayee.political.controller.BaseController;
import com.bayee.political.domain.RiskConduct;
import com.bayee.political.domain.RiskConductBureauRule;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.RiskTrain;
import com.bayee.political.domain.User;
import com.bayee.political.service.RiskService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DateUtils;

/**
 * @author shentuqiwei
 * @version 2021年4月1日 上午10:04:40 风险定时任务
 */
@Component
@EnableScheduling
@Controller
public class RiskTimeTask extends BaseController {

	@Autowired
	private RiskService riskService;

	@Autowired
	private UserService userService;

	@Autowired
	private TaskScheduler scheduler;

	public static final Map<Object, ScheduledFuture<?>> scheduledTasks = new IdentityHashMap<>();
	public static final String STARTJOB = "riskJob";

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 设置日期格式

	// 警员警务技能数据录入
	@Scheduled(cron = "0 0/10 * * * ?") // 每10分钟
//	@RequestMapping(value = "/risk/task", method = RequestMethod.GET)
	public void riskTrainTask() throws ParseException {
//		System.out.println("任务开始: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
		String dateTime = sdf.format(new Date());
		List<User> userList = userService.userInfoAllList();
		for (int i = 0; i < userList.size(); i++) {
			// 警员警务技能指数查询
			RiskTrain ritem = riskService.riskTrainIndexItem(userList.get(i).getPoliceId(), dateTime);
			// 警员警务技能统计查询
			RiskTrain item = riskService.riskTrainStatisticsItem(userList.get(i).getPoliceId(), dateTime);
			if (ritem == null) {
				item.setPoliceId(userList.get(i).getPoliceId());
				item.setIndexNum(0.0);
				item.setPhysicalStandardStatus(0);
				item.setFirearmStandardStatus(0);
				item.setCreationDate(new Date());
				riskService.riskTrainCreat(item);
			} else {
				ritem.setPhysicalNum(item.getPhysicalNum());
				ritem.setPhysicalPassNum(item.getPhysicalPassNum());
				ritem.setPhysicalFailNum(item.getPhysicalFailNum());
				ritem.setFirearmNum(item.getFirearmNum());
				ritem.setFirearmPassNum(item.getFirearmPassNum());
				ritem.setFirearmFailNum(item.getFirearmFailNum());
				ritem.setUpdateDate(new Date());
				riskService.riskTrainUpdate(ritem);
			}
		}
//		System.out.println("任务结束: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
	}

	// 总表人员新增
	@Scheduled(cron = "0 0/5 * * * ?") // 每5分钟
//	@RequestMapping(value = "/risk/task", method = RequestMethod.GET)
	public void riskPoliceTask() throws ParseException {
//		System.out.println("任务开始: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
		String dateTime = sdf.format(new Date());
		Date currdate = sdf.parse(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		String lastDateTime = sdf.format(calendar.getTime());
		List<User> userList = userService.userInfoAllList();
		int num = 0;
		for (int i = 0; i < userList.size(); i++) {
			RiskReportRecord ritem = riskService.riskReportRecordItem(null, userList.get(i).getPoliceId(), dateTime,
					lastDateTime);
			if (ritem == null) {
				RiskReportRecord record = new RiskReportRecord();
				record.setPoliceId(userList.get(i).getPoliceId());
				record.setYear(dateTime.substring(0, 4));
				record.setMonth(dateTime.substring(5, 7));
				record.setTotalNum(0.0);
				record.setConductNum(0.0);
				record.setHandlingCaseNum(0.0);
				record.setDutyNum(0.0);
				record.setTrainNum(0.0);
				record.setHealthNum(0.0);
				record.setDrinkNum(0.0);
				record.setStudyNum(0.0);
				record.setSocialContactNum(0.0);
				record.setAmilyEvaluationNum(0.0);
				record.setCreationDate(new Date());
				riskService.riskReportRecordCreat(record);
				num++;
			}
//			RiskConduct conductItem = riskService.riskConductIndexItem(userList.get(i).getPoliceId(), dateTime);
//			if (conductItem == null) {
//				RiskConduct iConduct = new RiskConduct();
//				iConduct.setBureauRuleScore(0.0);
//				iConduct.setVisitScore(0.0);
//				iConduct.setIndexNum(0.0);
//				iConduct.setPoliceId(userList.get(i).getPoliceId());
//				iConduct.setCreationDate(new Date());
//				riskService.riskConductCreat(iConduct);
//			}
//
//			RiskConductBureauRule bureauRuleItem = riskService
//					.riskConductBureauRuleIndexItem(userList.get(i).getPoliceId(), dateTime);
//			if (bureauRuleItem == null) {
//				RiskConductBureauRule ruleItem = new RiskConductBureauRule();
//				ruleItem.setPoliceId(userList.get(i).getPoliceId());
//				ruleItem.setIndexNum(0.0);
//				ruleItem.setTotalDeductionScore(0.0);
//				ruleItem.setDeductionScoreCount(0);
//				ruleItem.setCreationDate(new Date());
//				riskService.riskConductBureauRuleCreat(ruleItem);
//			}
		}
//		System.out.println("任务结束: " + num + "次数" + DateUtils.formatDate(new Date(), "HH:mm:ss"));
	}
}
