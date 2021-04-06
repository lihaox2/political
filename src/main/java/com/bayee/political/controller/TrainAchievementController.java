package com.bayee.political.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bayee.political.domain.MatchAchievementStatistics;
import com.bayee.political.domain.MatchAchievementTotalList;
import com.bayee.political.domain.MatchDepAchievementStatistics;
import com.bayee.political.domain.MatchTypeAchievement;
import com.bayee.political.domain.TrainAchievementStatistics;
import com.bayee.political.domain.TrainAchievementTotalList;
import com.bayee.political.domain.TrainDepAchievementStatistics;
import com.bayee.political.domain.TrainMatch;
import com.bayee.political.domain.TrainMatchAchievement;
import com.bayee.political.domain.TrainRank;
import com.bayee.political.domain.TrainRankList;
import com.bayee.political.domain.TrainTypeAchievement;
import com.bayee.political.domain.User;
import com.bayee.political.service.DepartmentService;
import com.bayee.political.service.TrainAchievementService;
import com.bayee.political.service.TrainMatchService;
import com.bayee.political.service.TrainService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.StatusCode;
import com.taobao.api.ApiException;

/**
 * @author shentuqiwei
 * @version 2021年1月11日 下午2:39:40
 */
@Controller
@Component
public class TrainAchievementController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private TrainAchievementService trainAchievementService;

	@Autowired
	private TrainService trainService;

	@Autowired
	private TrainMatchService trainMatchService;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	String currentYearStr = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

	// 训练成绩统计查询
	@RequestMapping(value = "/train/achievement/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> trainAchievementStatistics(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		Integer dateType = null;
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = currentYearStr;
			dateType = 1;
		} else {
			if (dateTime.length() == 4) {
				dateType = 1;
			} else {
				dateType = 2;
			}
		}
		// 训练成绩统计查询
		TrainAchievementStatistics item = trainAchievementService.trainAchievementStatisticsItem(policeId, dateType,
				dateTime);
		item.setDateTime(dateTime);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 单位/分局训练成绩查询
	@RequestMapping(value = "/train/dep/achievement/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> trainDepAchievementStatistics(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		Integer dateType = null;
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = currentYearStr;
			dateType = 1;
		} else {
			if (dateTime.length() == 4) {
				dateType = 1;
			} else {
				dateType = 2;
			}
		}
		TrainTypeAchievement item = new TrainTypeAchievement();
		// 单位/分局训练成绩查询
		TrainDepAchievementStatistics item1 = trainAchievementService.trainDepAchievementStatisticsItem(policeId, 1,
				dateType, dateTime);
		item.setDepItem(item1);
		// 单位/分局训练成绩查询
		TrainDepAchievementStatistics item2 = trainAchievementService.trainDepAchievementStatisticsItem(policeId, 2,
				dateType, dateTime);
		item.setBranchOfficeItem(item2);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 训练成绩记录查询
	@RequestMapping(value = "/train/achievement/total/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainAchievementTotalList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		Integer dateType = null;
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = currentYearStr;
			dateType = 1;
		} else {
			if (dateTime.length() == 4) {
				dateType = 1;
			} else {
				dateType = 2;
			}
		}
		// 训练成绩记录查询
		List<TrainAchievementTotalList> list = trainAchievementService.trainAchievementTotalList(policeId, type,
				dateType, dateTime);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setPoliceId(policeId);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 赛事成绩统计查询
	@RequestMapping(value = "/match/achievement/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> matchAchievementStatistics(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		Integer dateType = null;
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = currentYearStr;
			dateType = 1;
		} else {
			if (dateTime.length() == 4) {
				dateType = 1;
			} else {
				dateType = 2;
			}
		}
		// 赛事成绩统计查询
		MatchAchievementStatistics item = trainAchievementService.matchAchievementStatisticsItem(policeId, dateType,
				dateTime);
		item.setDateTime(dateTime);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 单位/分局赛事成绩查询
	@RequestMapping(value = "/match/dep/achievement/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> matchDepAchievementStatistics(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		Integer dateType = null;
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = currentYearStr;
			dateType = 1;
		} else {
			if (dateTime.length() == 4) {
				dateType = 1;
			} else {
				dateType = 2;
			}
		}
		MatchTypeAchievement item = new MatchTypeAchievement();
		// 单位赛事成绩查询
		MatchDepAchievementStatistics item1 = trainAchievementService.matchDepAchievementStatisticsItem(policeId, 1,
				dateType, dateTime);
		item.setDepItem(item1);
		// 分局赛事成绩查询
		MatchDepAchievementStatistics item2 = trainAchievementService.matchDepAchievementStatisticsItem(policeId, 2,
				dateType, dateTime);
		item.setBranchOfficeItem(item2);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 赛事成绩记录查询
	@RequestMapping(value = "/match/achievement/total/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchAchievementTotalList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		Integer dateType = null;
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = currentYearStr;
			dateType = 1;
		} else {
			if (dateTime.length() == 4) {
				dateType = 1;
			} else {
				dateType = 2;
			}
		}
		// 赛事成绩记录查询
		List<MatchAchievementTotalList> list = trainAchievementService.matchAchievementTotalList(policeId, type,
				dateType, dateTime);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setPoliceId(policeId);
				if (type == 1) {
					// 赛事详情查询
					TrainMatch fitem = trainMatchService.matchItem(list.get(i).getId());
					if (fitem.getStatus() != 1) {
						// 个人赛事详情查询
						TrainMatchAchievement fItem = trainMatchService.matchAchievementItem(null, list.get(i).getId(),
								policeId);
						if (fItem != null) {
							String sortStr = null;
							if (fitem.getSort() == 1) {// 升序
								sortStr = "asc";
							} else if (fitem.getSort() == 2) {// 降序
								sortStr = "desc";
							}
							// 赛事个人排名
							TrainRank personalRankItem = trainMatchService.matchMyEntryRankItem(list.get(i).getId(),
									policeId, sortStr);
							if (personalRankItem != null) {
								list.get(i).setRankId(personalRankItem.getRankId());
								list.get(i).setRankStr("第" + personalRankItem.getRankId() + "名");
							} else {
								list.get(i).setRankId(0);
								list.get(i).setRankStr("暂无排名");
							}
						}
					}
				} else if (type == 2) {
					// 个人分局赛事排名查询
					TrainRank rankItem = trainMatchService.matchDepRankItem(list.get(i).getId(),
							list.get(i).getDepartmentId());
					if (rankItem != null) {
						list.get(i).setRankId(rankItem.getRankId());
						list.get(i).setRankStr("第" + rankItem.getRankId() + "名");
					} else {
						list.get(i).setRankId(0);
						list.get(i).setRankStr("暂无排名");
					}
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

}
