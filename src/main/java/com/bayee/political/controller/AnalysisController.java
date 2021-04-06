/**
 * 
 */
package com.bayee.political.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bayee.political.domain.Chart;
import com.bayee.political.domain.DepartmentAnnualLeaveRatio;
import com.bayee.political.domain.LeaveProcess;
import com.bayee.political.domain.LeaveTypeCount;
import com.bayee.political.domain.MonthlyLeaveTotal;
import com.bayee.political.domain.Series;
import com.bayee.political.domain.UserLeaveStatisticsProfile;
import com.bayee.political.service.LeaveProcessService;
import com.bayee.political.service.UserService;

/**
 * @author seanguo
 *
 */
@RestController
public class AnalysisController extends BaseController {
	
	@Autowired
	LeaveProcessService leaveProcessService;
	
	@Autowired
	UserService userService;

	/**
	 * 全年使用过年休假人数占比
	 * */
	@RequestMapping(value="/analysis/chart/pie/annual-leave/all", method = RequestMethod.GET)
	public ResponseEntity<?> annualLeaveAll(
			@RequestParam(value = "year", required=false) String year
			)  {
		if(StringUtils.isEmpty(year)) {
			// TODO default year should be current year
			year = "2019";
		}
		
		int annualLeveUserCount = leaveProcessService.countAnnualLeveByUser(year);
		int totalUserCount = userService.countTotal();
		
		Chart chart = new Chart();
		chart.setTitle("全年使用过年休假人数占比");
		String [] categories = new String[]{"使用过年休假人数比","未使用过年休假人数比"};
		chart.setCategories(categories);
		
		Series s = new Series();
		s.setType("pie");
		float [] data = new float [] {
				new BigDecimal(((float)annualLeveUserCount/(float)totalUserCount)*100).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue(),
				new BigDecimal(((float)(totalUserCount-annualLeveUserCount)/(float)totalUserCount)*100).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue()
				};
		s.setData(data);
		
		Series [] ss = new Series [1];
		ss[0] = s;
		
		chart.setSeries(ss);
		
		return new ResponseEntity<Chart>(chart, HttpStatus.OK);
	}
	
	/**
	 * 各部门年假使用情况
	 * */
	@RequestMapping(value="/analysis/chart/multiple/annual-leave/department", method = RequestMethod.GET)
	public ResponseEntity<?> annualLeaveByDepartment(
			@RequestParam(value = "year", required=false) String year
			)  {
		if(StringUtils.isEmpty(year)) {
			// TODO default year should be current year
			year = "2019";
		}
		
		List<DepartmentAnnualLeaveRatio> deptAnnualLeaveRatioList = leaveProcessService.countAnnualLeaveRatioByDepartment(year);
		
		int annualLeveUserCount = leaveProcessService.countAnnualLeveByUser(year);
		int totalUserCount = userService.countTotal();
		
		Chart chart = new Chart();
		chart.setTitle("部门年假使用情况");
		chart.setyAxisTitle("年假使用率");
		
		Series [] series = new Series [2];
		
		// 全局平均年假使用率 折线
		Series seriesLine = new Series();
		seriesLine.setType("line");
		float [] seriesLineData = new float [deptAnnualLeaveRatioList.size()];
		seriesLine.setData(seriesLineData);
		series[0] = seriesLine;
		
		// 各部门年假使用率 柱
		Series seriesBar = new Series();
		seriesBar.setType("bar");
		float [] seriesBarData = new float [deptAnnualLeaveRatioList.size()];
		seriesBar.setData(seriesBarData);
		series[1] = seriesBar;
		
		String [] categories = new String[deptAnnualLeaveRatioList.size()];
		int i = 0;
		for(DepartmentAnnualLeaveRatio ratio : deptAnnualLeaveRatioList) {
			categories[i] = ratio.getName();
			seriesLineData[i] = new BigDecimal(((float)annualLeveUserCount/(float)totalUserCount)*100).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
			seriesBarData[i] = new BigDecimal(ratio.getDeptAnnualLeaveRatio()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
			i++;
		}
		
		chart.setCategories(categories);
		chart.setSeries(series);
		
		return new ResponseEntity<Chart>(chart, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/analysis/chart/line/leave/monthly"})
	public ResponseEntity<Chart> annualLeaveUser(
			@RequestParam(value = "year", required=false) String year
			) {
		if(StringUtils.isEmpty(year)) {
			// TODO default year should be current year
			year = "2019";
		}
		
		List<MonthlyLeaveTotal> monthlyLeaveCounts = leaveProcessService.countMonthlyLeaveByYear(year);
		
		Chart chart = new Chart();
		chart.setTitle("每月请假人数趋势");
		chart.setyAxisTitle("请假人数");
		
		//初始化数据
		String [] categories = new String[] {"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
		chart.setCategories(categories);
		
		Series series = new Series();
		series.setType("line");
		float [] data = new float [] {0,0,0,0,0,0,0,0,0,0,0,0};
		series.setData(data);
		
		Series [] s = new Series[1];
		s[0] = series;
		chart.setSeries(s);
		
		for(MonthlyLeaveTotal monthCount : monthlyLeaveCounts) {
			if(monthCount.getMonth().equals("01")) {
				data[0] = monthCount.getCount();
			} else if(monthCount.getMonth().equals("02")) {
				data[1] = monthCount.getCount();
			} else if(monthCount.getMonth().equals("03")) {
				data[2] = monthCount.getCount();
			} else if(monthCount.getMonth().equals("04")) {
				data[3] = monthCount.getCount();
			} else if(monthCount.getMonth().equals("05")) {
				data[4] = monthCount.getCount();
			} else if(monthCount.getMonth().equals("06")) {
				data[5] = monthCount.getCount();
			} else if(monthCount.getMonth().equals("07")) {
				data[6] = monthCount.getCount();
			} else if(monthCount.getMonth().equals("08")) {
				data[7] = monthCount.getCount();
			} else if(monthCount.getMonth().equals("09")) {
				data[8] = monthCount.getCount();
			} else if(monthCount.getMonth().equals("10")) {
				data[9] = monthCount.getCount();
			} else if(monthCount.getMonth().equals("11")) {
				data[10] = monthCount.getCount();
			} else if(monthCount.getMonth().equals("12")) {
				data[11] = monthCount.getCount();
			} 
		}
		
		return new ResponseEntity<Chart>(chart, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/analysis/chart/pie/leave/types"})
	public ResponseEntity<Chart> leaveTypesAnslysis(
			@RequestParam(value = "year", required=false) String year
			)  {
		if(StringUtils.isEmpty(year)) {
			// TODO default year should be current year
			year = "2019";
		}
		
		int totalLeaveCount = leaveProcessService.countTotalLeave(year);
		List<LeaveTypeCount> leaveReasonCounts = leaveProcessService.countLeaveByTypes(year);
		
		Chart chart = new Chart();
		chart.setTitle("请假原因占比情况");
		String [] categories = new String[leaveReasonCounts.size()];
		chart.setCategories(categories);
		
		Series s = new Series();
		s.setType("pie");
		float [] data = new float [leaveReasonCounts.size()];
		s.setData(data);
		
		Series [] ss = new Series [1];
		ss[0] = s;
		
		chart.setSeries(ss);
		
		int i = 0;
		for(LeaveTypeCount typeCount : leaveReasonCounts) {
			categories[i] =  typeCount.getLeaveType();
			data[i] = new BigDecimal(((float)typeCount.getCount()/(float)totalLeaveCount)*100).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
			i++;
		}
		
		return new ResponseEntity<Chart>(chart, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/analysis/leave/user"})
	public ResponseEntity<UserLeaveStatisticsProfile> userLeaveStatistics(
			@RequestParam(value="userId", required=true) String userId,
			@RequestParam(value = "year", required=false) String year
			) {
		if(StringUtils.isEmpty(year)) {
			// TODO default year should be current year
			year = "2019";
		}
		
		UserLeaveStatisticsProfile statisticsProfile = new UserLeaveStatisticsProfile(); 
		
		// 计算用户总请假数
		int totalLeaveCount = leaveProcessService.countUserTotalLeaveCount(userId, year);
		statisticsProfile.setTotalLeave(totalLeaveCount);
		
		// 计算用户剩余年假数
		int annualLeaveLeftCount = leaveProcessService.countAnnualLeaveLeftCount(userId, year);
		statisticsProfile.setAnnualLeaveRemain(annualLeaveLeftCount);
		
		// 计算用户使用年假次数
		int annualLeaveUsedTimes = leaveProcessService.countAnnualLeaveUsedTimes(userId, year);
		statisticsProfile.setAnnualLeaveUsed(annualLeaveUsedTimes);
		
		List<LeaveProcess> allLeaveProcessList = leaveProcessService.findByUserId(userId);
		statisticsProfile.setLeaves(allLeaveProcessList);
		if(allLeaveProcessList!= null && allLeaveProcessList.size()>0) {
			statisticsProfile.setLatestLeave(allLeaveProcessList.get(0));
		}
		
		List<LeaveTypeCount> leaveTypeCountList = leaveProcessService.countUserLeaveByTypes(userId, year);
		Chart chart = new Chart();
		chart.setTitle("请假原因占比情况");
		String [] categories = new String[leaveTypeCountList.size()];
		chart.setCategories(categories);
		
		Series s = new Series();
		s.setType("pie");
		float [] data = new float [leaveTypeCountList.size()];
		s.setData(data);
		
		Series [] ss = new Series [1];
		ss[0] = s;
		
		chart.setSeries(ss);
		
		int i = 0;
		for(LeaveTypeCount typeCount : leaveTypeCountList) {
			categories[i] =  typeCount.getLeaveType();
			data[i] = new BigDecimal(((float)typeCount.getCount()/(float)totalLeaveCount)*100).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
			i++;
		}
		
		statisticsProfile.setLeaveTypePie(chart);
		
		return new ResponseEntity<UserLeaveStatisticsProfile>(statisticsProfile, HttpStatus.OK);
	}
}
