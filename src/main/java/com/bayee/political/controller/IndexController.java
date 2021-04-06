package com.bayee.political.controller;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bayee.political.service.AlarmService;
import com.bayee.political.service.EvaluationService;
import com.bayee.political.service.LeaveProcessService;
import com.bayee.political.service.TrainService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.StatusCode;

/**
 * @author shentuqiwei
 * @version 2020年5月22日 下午2:47:48
 */
@Controller
public class IndexController extends BaseController {

	@Autowired
	private EvaluationService evaluationService;
	
	@Autowired
	private AlarmService alarmService;
	
	@Autowired
	private LeaveProcessService leaveProcessService;
	
	@Autowired
	private TrainService trainService;
	
	/**
	 * 查询主页数据
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ResponseEntity<?> getNewThreeTalk() {
		
		/**
		 * 本周
		 */
		
		//评价任务数
		Integer taskNumWeek = evaluationService.taskNumWeek();
		
		//考核预警事件
		Integer alarmNumWeek = alarmService.alarmNumWeek();
		
		//出入境预警事件
		Integer alarmEntryExitNumWeek = alarmService.alarmEntryExitNumWeek();
		
		//约谈完成数
		Integer alarmTalkComplateNumWeek = alarmService.alarmTalkComplateNumWeek();
		
		//请假人数
		Integer leaveNum = leaveProcessService.leaveNum();
		
		//休年假人数
		Integer leaveYearNum = leaveProcessService.leaveYearNum();
		
		//需强制调休人数
		Integer leaveAdjustNum = leaveProcessService.leaveAdjustNum();
		
		//训练次数
		Integer allTrainNum = trainService.AllTrainNum();
		
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("taskNumWeek", taskNumWeek);
		map.put("alarmNumWeek", alarmNumWeek);
		map.put("alarmEntryExitNumWeek", alarmEntryExitNumWeek);
		map.put("alarmTalkComplateNumWeek", alarmTalkComplateNumWeek);
		map.put("leaveNum", leaveNum);
		map.put("leaveYearNum", leaveYearNum);
		map.put("leaveAdjustNum", leaveAdjustNum);
		map.put("allTrainNum", allTrainNum);
		
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);
		
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		
	}

}
