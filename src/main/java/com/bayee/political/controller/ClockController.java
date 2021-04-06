package com.bayee.political.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.ClockRecord;
import com.bayee.political.domain.DrinkRecord;
import com.bayee.political.domain.User;
import com.bayee.political.service.ClockService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.StatusCode;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiAttendanceListRecordRequest;
import com.dingtalk.api.response.OapiAttendanceListRecordResponse;
import com.dingtalk.api.response.OapiAttendanceListRecordResponse.Recordresult;
import com.taobao.api.ApiException;

/**
 * @author shentuqiwei
 * @version 2020年7月9日 下午3:59:13
 */
@Controller
@Component
@EnableScheduling
public class ClockController extends BaseController {

	@Autowired
	private ClockService clockService;

	@Autowired
	private UserService userService;

	// 钉钉打卡记录保存(自动定时抓取钉钉请假数据，每天6点执行一次)
//	@Scheduled(cron = "0 0 6 * * ?") // 每天6点执行
//	@Scheduled(cron = "0 */1 * * * ?") // 间隔1分钟执行
//	@RequestMapping(value = "/clock/record/save", method = RequestMethod.GET)
//	public void clockRecordSave() throws ApiException {
//		DataListReturn dlr = new DataListReturn();
//		// 查询全部警员数据
//		List<User> userList = userService.userAllList();
//		List<String> srtUserList = new ArrayList<String>();
//		for (int i = 0; i < userList.size(); i++) {
//			srtUserList.add(userList.get(i).getDdUserId());
//		}
//		long count = 0L;
//		String accessToken = getAccessToken();
//		for (int i = 0; i < srtUserList.size(); i++) {
//			DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/attendance/listRecord");
//			OapiAttendanceListRecordRequest request = new OapiAttendanceListRecordRequest();
//			Date dNow = new Date(); // 当前时间
//			Date dBefore = new Date();
//			Calendar calendar = Calendar.getInstance(); // 得到日历
//			calendar.setTime(dNow);// 把当前时间赋给日历
//			calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
//			dBefore = calendar.getTime(); // 得到前一天的时间
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
//			String defaultStartDate = sdf.format(dBefore); // 格式化前一天
//			String defaultEndDate = sdf.format(dNow); // 格式化当前时间
//			request.setCheckDateFrom(defaultStartDate + " 00:00:00");
//			request.setCheckDateTo(defaultEndDate + " 05:00:00");
//			request.setUserIds(Arrays.asList(srtUserList.get(i)));
//			OapiAttendanceListRecordResponse response = client.execute(request, accessToken);
//			System.out.println("请求结果:" + response.getRecordresult());
//			List<Recordresult> recordresultList = response.getRecordresult();
//			if (recordresultList != null) {
//				System.out.println("成功进入");
//				for (Recordresult recordresult : recordresultList) {
//					ClockRecord clock = new ClockRecord();
//					clock.setUserId(recordresult.getUserId());
//					clock.setCheckType(recordresult.getCheckType());
//					clock.setGroupId(recordresult.getGroupId());
//					clock.setPlanId(recordresult.getPlanId());
////				clock.setRecordId(recordresult.getRecordId());
//					clock.setWorkDate(recordresult.getWorkDate());
//					clock.setTimeResult(recordresult.getTimeResult());
//					clock.setLocationResult(recordresult.getLocationResult());
//					clock.setApproveId(recordresult.getApproveId());
//					clock.setProcinstId(recordresult.getProcInstId());
//					clock.setBaseCheckTime(recordresult.getBaseCheckTime());
//					clock.setUserCheckTime(recordresult.getUserCheckTime());
//					clock.setCreationDate(new Date());
//					clockService.clockRecordCreate(clock);
//					count++;
//				}
//			}
//
//		}
//
////		for (int i = 0; i < srtUserList.size(); i++) {
////			DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/attendance/list");
////			OapiAttendanceListRequest request = new OapiAttendanceListRequest();
////			request.setWorkDateFrom("2020-07-05 00:00:00");
////			request.setWorkDateTo("2020-07-10 05:00:00");
////			request.setUserIdList(Arrays.asList(srtUserList.get(i)));
////			request.setOffset(count);
////			request.setLimit(50L);
////			OapiAttendanceListResponse response = client.execute(request, accessToken);
////			System.out.println("请求结果:"+response.getRecordresult());
////			List<Recordresult> recordresultList = response.getRecordresult();
////			if (recordresultList != null) {
////				System.out.println("成功进入");
////				for (Recordresult recordresult : recordresultList) {
////					ClockRecord clock = new ClockRecord();
////					clock.setUserId(recordresult.getUserId());
////					clock.setCheckType(recordresult.getCheckType());
////					clock.setGroupId(recordresult.getGroupId());
////					clock.setPlanId(recordresult.getPlanId());
////					clock.setRecordId(recordresult.getRecordId());
////					clock.setWorkDate(recordresult.getWorkDate());
////					clock.setTimeResult(recordresult.getTimeResult());
////					clock.setLocationResult(recordresult.getLocationResult());
////					clock.setApproveId(recordresult.getApproveId());
////					clock.setProcinstId(recordresult.getProcInstId());
////					clock.setBaseCheckTime(recordresult.getBaseCheckTime());
////					clock.setUserCheckTime(recordresult.getUserCheckTime());
////					clock.setCreationDate(new Date());
////					clockService.clockRecordCreate(clock);
////					count++;
////				}
////			}
////		}
//		System.out.println("成功保存：" + count);
//		dlr.setStatus(true);
//		dlr.setMessage("success");
//		dlr.setResult(1);
//		dlr.setCode(StatusCode.getSuccesscode());
//	}

	// 警员喝酒记录查询
	@RequestMapping(value = "/drink/list", method = RequestMethod.GET)
	public ResponseEntity<?> drinkList(@RequestParam(value = "policeType", required = false) Integer policeType) {
		DataListReturn dlr = new DataListReturn();
		List<DrinkRecord> list = clockService.drinkList();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

}
