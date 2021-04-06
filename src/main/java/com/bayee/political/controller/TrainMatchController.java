package com.bayee.political.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.MatchLeaderApplicant;
import com.bayee.political.domain.MatchLeaderStatistics;
import com.bayee.political.domain.MatchRecordScore;
import com.bayee.political.domain.MatchStatistics;
import com.bayee.political.domain.MatchSubmit;
import com.bayee.political.domain.ReportDataFillTime;
import com.bayee.political.domain.TrainActivityStyle;
import com.bayee.political.domain.TrainChartStatistics;
import com.bayee.political.domain.TrainConstitution;
import com.bayee.political.domain.TrainEvaluateRecord;
import com.bayee.political.domain.TrainFirearmAchievement;
import com.bayee.political.domain.TrainGetMedal;
import com.bayee.political.domain.TrainLeaderMoreAchievement;
import com.bayee.political.domain.TrainLeaderSignUpSuccess;
import com.bayee.political.domain.TrainLeaderStatistics;
import com.bayee.political.domain.TrainLikeRecord;
import com.bayee.political.domain.TrainMatch;
import com.bayee.political.domain.TrainMatchAchievement;
import com.bayee.political.domain.TrainMatchBestAchievement;
import com.bayee.political.domain.TrainMatchDepartmentAchievement;
import com.bayee.political.domain.TrainMatchLimitReport;
import com.bayee.political.domain.TrainMatchNumber;
import com.bayee.political.domain.TrainMatchPersonalResult;
import com.bayee.political.domain.TrainMatchProject;
import com.bayee.political.domain.TrainMatchType;
import com.bayee.political.domain.TrainMedalManage;
import com.bayee.political.domain.TrainMedalPerson;
import com.bayee.political.domain.TrainMtachChartStatistics;
import com.bayee.political.domain.TrainPacesetter;
import com.bayee.political.domain.TrainPersonalAchievement;
import com.bayee.political.domain.TrainPersonalAchievementStatistics;
import com.bayee.political.domain.TrainPersonalResult;
import com.bayee.political.domain.TrainPhysical;
import com.bayee.political.domain.TrainPhysicalAchievement;
import com.bayee.political.domain.TrainPhysicalAchievementDetails;
import com.bayee.political.domain.TrainRank;
import com.bayee.political.domain.TrainRankList;
import com.bayee.political.domain.TrainRecommendPolice;
import com.bayee.political.domain.TrainRecordPolice;
import com.bayee.political.domain.TrainTimeName;
import com.bayee.political.domain.User;
import com.bayee.political.service.TrainMatchService;
import com.bayee.political.service.TrainService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListPage;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import com.bayee.political.utils.QRCode;
import com.bayee.political.utils.StatusCode;
import com.taobao.api.ApiException;

import cn.hutool.core.date.DateUtil;

/**
 * @author shentuqiwei
 * @version 2020年10月25日 下午4:43:38
 */
@Controller
@Component
public class TrainMatchController extends BaseController {

	@Autowired
	private TrainMatchService trainMatchService;

	@Autowired
	private UserService userService;

	@Autowired
	private TrainService trainService;

	@Value("${train_images}")
	private String imageDirectory; // = "uploads";

	@Value("${file_container}")
	private String root;// 根路径

	@Value("${train_coverImg}")
	private String coverImg;// 封面图路径

	@Value("${train_contentImg}")
	private String contentImg;// 内容图路径

	@Value("${train_QR_code}")
	private String trainQRCode;// 二维码路径

	@Value("${train_QR_code_icon}")
	private String trainQRCodeIcon;// 二维码图标

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	String currentYearStr = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	String monthStr = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);

	// 进行中的赛事查询
	@RequestMapping(value = "/match/in/progress/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchInProgressList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 进行中的赛事查询
		List<TrainMatch> list = trainMatchService.matchInProgressList(policeId, 2, "desc");// status=2
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 正在报名的赛事查询
	@RequestMapping(value = "/match/page/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchList(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (type == null || type == 0) {
			type = null;
		}
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		// 赛事列表查询
		List<TrainMatch> list = trainMatchService.matchList(policeId, type, user.getDepartmentId());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 赛事报名人头像查询
				List<CalculationChart> imageList = trainMatchService.matchHeadImageList(list.get(i).getId(), policeId,
						5);
				list.get(i).setHeadImageList(imageList);
			}
		}
		// 赛事总数统计
//		int total = trainMatchService.matchListCount(policeId, type, departmentId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 即将开始的赛事查询
	@RequestMapping(value = "/match/start/right/now/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchStartRightNowList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 即将开始的赛事查询
		List<TrainMatch> list = trainMatchService.matchInProgressList(policeId, 1, "desc");// status=2
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int timeItem = list.get(i).getTimeChange();
				if (timeItem == 0) {
					list.get(i).setCreationDateStr("1分钟后开始");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "分钟后开始");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "小时后开始");
				} else if (timeItem >= 1440 && timeItem < 2880) {
					String dateTime = sdf.format(list.get(i).getMatchStartDate()).substring(11, 16);
					list.get(i).setCreationDateStr("明天" + dateTime + "开始");
				} else if (timeItem >= 2880 && timeItem < 4320) {
					String dateTime = sdf.format(list.get(i).getMatchStartDate()).substring(11, 16);
					list.get(i).setCreationDateStr("后天" + dateTime + "开始");
				} else if (timeItem >= 4320) {
					int yearInt = Integer.valueOf(currentYearStr);
					int createYear = Integer.valueOf(sdf.format(list.get(i).getMatchStartDate()).substring(0, 4));
					if (yearInt == createYear) {
						list.get(i).setCreationDateStr(
								sdf.format(list.get(i).getMatchStartDate()).substring(5, 10) + "开始");
					} else {
						String timeString = sdf.format(list.get(i).getMatchStartDate()).substring(0, 10);
						list.get(i).setCreationDateStr(timeString + "开始");
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

	// 我的赛事列表查询
	@RequestMapping(value = "/my/match/page/list", method = RequestMethod.GET)
	public ResponseEntity<?> myMatchList(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "policeType", required = false) Integer policeType,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		if (status == null || status == 0) {
			status = null;
		}
		MatchStatistics item = new MatchStatistics();
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;

		List<TrainMatch> list = new ArrayList<TrainMatch>();
		int total = 0;
		if (policeType == 1) {
			// 我的赛事列表查询
			list = trainMatchService.myMatchList(policeId, type, status, pageSize, pageNum);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getStatus() == 1) {
						int timeItem = list.get(i).getTimeChange();
						if (timeItem == 0) {
							list.get(i).setCreationDateStr("1分钟后开始");
						} else if (timeItem < 60 && timeItem > 0) {
							list.get(i).setCreationDateStr(timeItem + "分钟后开始");
						} else if (timeItem >= 60 && timeItem < 1440) {
							int timeInt = (int) Math.floor(timeItem / 60);
							list.get(i).setCreationDateStr(timeInt + "小时后开始");
						} else if (timeItem >= 1440 && timeItem < 2880) {
							String dateTime = sdf.format(list.get(i).getMatchStartDate()).substring(11, 16);
							list.get(i).setCreationDateStr("明天" + dateTime + "开始");
						} else if (timeItem >= 2880 && timeItem < 4320) {
							String dateTime = sdf.format(list.get(i).getMatchStartDate()).substring(11, 16);
							list.get(i).setCreationDateStr("后天" + dateTime + "开始");
						} else if (timeItem >= 4320) {
							int yearInt = Integer.valueOf(currentYearStr);
							int createYear = Integer
									.valueOf(sdf.format(list.get(i).getMatchStartDate()).substring(0, 4));
							if (yearInt == createYear) {
								list.get(i).setCreationDateStr(
										sdf.format(list.get(i).getMatchStartDate()).substring(5, 10) + "开始");
							} else {
								String timeString = sdf.format(list.get(i).getMatchStartDate()).substring(0, 10);
								list.get(i).setCreationDateStr(timeString + "开始");
							}
						}
					}
				}
			}
			// 我的赛事总数统计
			total = trainMatchService.myMatchListCount(policeId, type, status);
			int total1 = trainMatchService.myMatchListCount(policeId, 1, status);
			int total2 = trainMatchService.myMatchListCount(policeId, 2, status);
			item.setDepNum(total1);
			item.setOfficeNum(total2);
		} else if (policeType == 2) {
			// 领队比赛列表查询
			list = trainMatchService.matchLeaderPageList(policeId, null, status, user.getDepartmentId(), pageSize,
					pageNum);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getPoliceNum() == null) {
						list.get(i).setPoliceNum(0);
					}
				}
			}
			// 领队比赛列表数量统计
			total = trainMatchService.matchLeaderPageCount(policeId, null, status, user.getDepartmentId());
		}
		item.setMatchList(list);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 赛事详情查询
	@RequestMapping(value = "/match/item", method = RequestMethod.GET)
	public ResponseEntity<?> matchItem(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (objectId == null) {
			objectId = 1;
		}
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		// 赛事详情查询
		TrainMatch item = trainMatchService.matchItem(id);
		item.setPoliceId(policeId);
		item.setPoliceName(user.getName());
		List<String> listStr = new ArrayList<String>();
		// 赛事领队报名人员查询
		List<TrainMatchAchievement> list = trainMatchService.matchApplicantsLeaderList(id, null);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i <= 4) {
					listStr.add(list.get(i).getHeadImage());
				}
			}
			item.setHeadImageStr(StringUtils.join(listStr.toArray(), ","));
		}
		// 部门赛事详情查询
		TrainMatchDepartmentAchievement depItem = trainMatchService.trainMatchDepartmentAchievementItem(null, id,
				user.getDepartmentId());
		if (depItem != null) {
			if (depItem.getAchievement() != null) {
				item.setDepAchievementStr(depItem.getAchievement() + "分");
			}
		}
		// 个人赛事详情查询
		TrainMatchAchievement aItem = trainMatchService.matchAchievementItem(null, id, policeId);
		if (aItem != null) {
			if (aItem.getAchievement() != null) {
				item.setAchievement(aItem.getAchievement());
				item.setAchievementStr(aItem.getAchievementStr());
			}
			if (aItem.getQrCode() != null) {
				item.setQrCode(aItem.getQrCode());
			}
		}
		if (objectId == 1) {// 参赛者
			if (item.getQrCode() == null) {
				item.setIsSign(1);
				if (item.getSignUpStatus() == 2) {
					item.setReportDateStr(getReportSurplusTime(item.getTimeChange()));
				}
			} else {
				if (aItem != null) {
					item.setIsSign(aItem.getIsSign());
				} else {
					item.setIsSign(1);
				}
				if (item.getStatus() == 1) {
					item.setCreationDateStr(getReportStartTime(item.getStartTimeChange()));
				} else if (item.getStatus() == 2) {
					String timeString = getSurplusStrDate(item.getMatchEndDate(), new Date());
					item.setCreationDateStr("剩余" + timeString + "后结束");
				}
			}
		} else if (objectId == 2) {// 领队
			item.setIsSign(1);
			if (item.getSignUpStatus() == 2) {
				item.setReportDateStr(getReportSurplusTime(item.getTimeChange()));
			}
			if (item.getStatus() == 1) {
				item.setCreationDateStr(getReportStartTime(item.getStartTimeChange()));
			} else if (item.getStatus() == 2) {
				String timeString = getSurplusStrDate(item.getMatchEndDate(), new Date());
				item.setCreationDateStr("剩余" + timeString + "后结束");
			}
		}
		if (item.getManMaxNum() > 0 && item.getWomanMaxNum() == 0) {
			item.setLimitPeopleStr("男性限报" + item.getManMaxNum() + "人");
		} else if (item.getManMaxNum() > 0 && item.getWomanMaxNum() > 0) {
			item.setLimitPeopleStr("男性限报" + item.getManMaxNum() + "人;" + "女性限报" + item.getWomanMaxNum() + "人;");
		} else if (item.getManMaxNum() == 0 && item.getWomanMaxNum() > 0) {
			item.setLimitPeopleStr("女性限报" + item.getWomanMaxNum() + "人");
		}
		List<CalculationChart> totalList = new ArrayList<CalculationChart>();
		int num = 4;
		// 赛事报名当前用户头像查询
		CalculationChart imageItem = trainMatchService.matchHeadImageItem(id, policeId);
		if (imageItem != null) {
			totalList.add(imageItem);
		} else {
			num = 5;
		}
		// 赛事报名人头像查询
		List<CalculationChart> imageList = trainMatchService.matchHeadImageList(id, policeId, num);
		if (imageList.size() > 0) {
			totalList.addAll(imageList);
		}
		if (totalList.size() > 0) {
			item.setHeadImageList(totalList);
		}

		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 计算训练报名剩余时间
	private String getReportSurplusTime(Integer timeItem) {
		String surplusTime = null;
		if (timeItem == 0) {
			surplusTime = "剩余0分钟";
		} else if (timeItem < 60 && timeItem > 0) {
			surplusTime = "剩余" + timeItem + "分钟";
		} else if (timeItem >= 60 && timeItem < 1440) {
			int timeInt = (int) Math.floor(timeItem / 60);
			surplusTime = "剩余" + timeInt + "小时";
		} else if (timeItem >= 1440) {
			int timeInt = (int) Math.floor(timeItem / 60 / 24);
			surplusTime = "剩余" + timeInt + "天";
		}
		return surplusTime;
	}

	// 计算当前时间和训练开始时间的差值
	private String getReportStartTime(Integer timeItem) {
		String surplusTime = null;
		if (timeItem == 0) {
			surplusTime = "赛事马上开始";
		} else if (timeItem < 60 && timeItem > 0) {
			surplusTime = "赛事" + timeItem + "分钟后开始";
		} else if (timeItem >= 60 && timeItem < 1440) {
			int timeInt = (int) Math.floor(timeItem / 60);
			surplusTime = "赛事" + timeInt + "小时后开始";
		} else if (timeItem >= 1440) {
			int timeInt = (int) Math.floor(timeItem / 60 / 24);
			surplusTime = "赛事" + timeInt + "天后开始";
		}
		return surplusTime;
	}

	// 赛事实时排名榜查询
	@RequestMapping(value = "/match/my/entry/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchMyEntryRankList(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<TrainRankList> list = new ArrayList<TrainRankList>();
		// 赛事详情查询
		TrainMatch fitem = trainMatchService.matchItem(trainMatchId);
		if (fitem.getStatus() != 1) {
			// 个人赛事详情查询
			TrainMatchAchievement fItem = trainMatchService.matchAchievementItem(null, trainMatchId, policeId);
			if (fItem != null) {
				String sortStr = null;
				if (fitem.getSort() == 1) {// 升序
					sortStr = "asc";
				} else if (fitem.getSort() == 2) {// 降序
					sortStr = "desc";
				}
				TrainRankList item = new TrainRankList();
				// 赛事排名
				List<TrainRank> rankList = trainMatchService.matchMyEntryRankList(trainMatchId, sortStr);
				if (rankList.size() > 0) {
					for (int i = 0; i < rankList.size(); i++) {
						// 根据policeId查询部门
						User user = userService.policeItem(rankList.get(i).getPoliceId(), null, null);
						rankList.get(i).setDepartmentName(user.getDepartmentName());
					}
					item.setRankList(rankList);
				}
				// 赛事个人排名
				TrainRank personalRankItem = trainMatchService.matchMyEntryRankItem(trainMatchId, policeId, sortStr);
				if (personalRankItem != null) {
					// 根据policeId查询部门
					User user = userService.policeItem(policeId, null, null);
					personalRankItem.setDepartmentName(user.getDepartmentName());
					item.setRankItem(personalRankItem);
				} else {
					// 根据policeId查询部门
					User user = userService.policeItem(policeId, null, null);
					TrainRank RItem = new TrainRank();
					RItem.setHeadImage(user.getHeadImage());
					item.setRankItem(RItem);
				}
				list.add(item);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 赛事更多榜单排名榜查询
	@RequestMapping(value = "/match/my/entry/more/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchMyEntryMoreRankList(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<TrainRankList> list = new ArrayList<TrainRankList>();
		// 赛事详情查询
		TrainMatch fitem = trainMatchService.matchItem(trainMatchId);
		if (fitem.getStatus() != 1) {
			// 个人赛事详情查询
			TrainMatchAchievement fItem = trainMatchService.matchAchievementItem(null, trainMatchId, policeId);
			if (fItem != null) {
				String sortStr = null;
				if (fitem.getSort() == 1) {// 升序
					sortStr = "asc";
				} else if (fitem.getSort() == 2) {// 降序
					sortStr = "desc";
				}
				TrainRankList item = new TrainRankList();
				// 赛事更多榜单排名榜查询
				List<TrainRank> rankList = trainMatchService.matchMyEntryMoreRankList(trainMatchId, sortStr);
				item.setRankList(rankList);
				// 赛事个人排名
				TrainRank personalRankItem = trainMatchService.matchMyEntryRankItem(trainMatchId, policeId, sortStr);
				if (personalRankItem != null) {
					item.setRankItem(personalRankItem);
				} else {
					// 根据policeId查询部门
					User user = userService.policeItem(policeId, null, null);
					TrainRank RItem = new TrainRank();
					RItem.setHeadImage(user.getHeadImage());
					item.setRankItem(RItem);
				}
				list.add(item);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);

	}

	// 个人赛事报名(单位)
	@ResponseBody
	@RequestMapping(value = "/match/personal/sign/up/creat", method = RequestMethod.POST)
	public DataListReturn matchPersonalSignUpCreat(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		TrainMatchAchievement item = new TrainMatchAchievement();
		// 个人赛事详情查询
		TrainMatchAchievement aItem = trainMatchService.matchAchievementItem(null, id, policeId);
		if (aItem == null) {
			// 赛事详情查询
			TrainMatch tItem = trainMatchService.matchItem(id);
			long startTimeLong = tItem.getRegistrationStartDate().getTime();
			long endTimeLong = tItem.getRegistrationEndDate().getTime();
			long currentTime = new Date().getTime();
			if (currentTime < startTimeLong) {
				dlr.setStatus(false);
				dlr.setMessage("报名未开始");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			} else if (currentTime > endTimeLong) {
				dlr.setStatus(false);
				dlr.setMessage("报名已截止");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
			// 根据policeId查询部门
			User user = userService.policeItem(policeId, null, null);
			Integer sex = user.getGender();
			// 查询当前项目男性报名人数
			int manNum = trainMatchService.alreadySignUpPoliceNum(id, 1);
			// 查询当前项目女性报名人数
			int womanNum = trainMatchService.alreadySignUpPoliceNum(id, 2);
			if (tItem.getManMaxNum() > 0 && tItem.getWomanMaxNum() == 0) {
				if (sex == 1) {// 男
					if (tItem.getManMaxNum() <= manNum) {
						dlr.setStatus(false);
						dlr.setMessage("您来晚了，报名人数已满");
						dlr.setResult(0);
						dlr.setCode(StatusCode.getFailcode());
						return dlr;
					}
				} else if (sex == 2) {// 女
					dlr.setStatus(false);
					dlr.setMessage("女性不可参加当前比赛");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getManMaxNum() > 0 && tItem.getWomanMaxNum() > 0) {
				if (sex == 1) {// 男
					if (tItem.getManMaxNum() <= manNum) {
						dlr.setStatus(false);
						dlr.setMessage("您来晚了，报名人数已满");
						dlr.setResult(0);
						dlr.setCode(StatusCode.getFailcode());
						return dlr;
					}
				} else if (sex == 2) {// 女
					if (tItem.getWomanMaxNum() <= womanNum) {
						dlr.setStatus(false);
						dlr.setMessage("您来晚了，报名人数已满");
						dlr.setResult(0);
						dlr.setCode(StatusCode.getFailcode());
						return dlr;
					}
				}
			} else if (tItem.getManMaxNum() == 0 && tItem.getWomanMaxNum() > 0) {
				if (sex == 1) {// 男
					dlr.setStatus(false);
					dlr.setMessage("男性不可参加当前比赛");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				} else if (sex == 2) {// 女
					if (tItem.getWomanMaxNum() <= womanNum) {
						dlr.setStatus(false);
						dlr.setMessage("您来晚了，报名人数已满");
						dlr.setResult(0);
						dlr.setCode(StatusCode.getFailcode());
						return dlr;
					}
				}
			}
			item.setDepartmentId(user.getDepartmentId());
			item.setTrainMatchId(id);
			item.setPoliceId(policeId);
			item.setRegistrationDate(new Date());
			item.setIsSign(1);
			item.setCreationDate(new Date());
			String qrName = "physical-" + UUID.randomUUID().toString() + ".jpg";
			item.setQrCode(trainQRCode + qrName);
			// 生成二维码
			new QRCode(root + trainQRCodeIcon).encodeQRCode(id + "," + policeId, 500, 500, root + trainQRCode + qrName);
			int count = trainMatchService.matchAchievementCreat(item);
			if (count > 0) {
				dlr.setStatus(true);
				dlr.setMessage("报名成功");
				dlr.setResult(item);
				dlr.setCode(StatusCode.getSuccesscode());
				return dlr;
			} else {
				dlr.setStatus(false);
				dlr.setMessage("报名失败");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
		} else {
			dlr.setStatus(false);
			dlr.setMessage("您已报名该赛事");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
	}

	// 个人赛事报名成功查询
	@RequestMapping(value = "/match/personal/sign/up/success/item", method = RequestMethod.GET)
	public ResponseEntity<?> matchPersonalSignUpSuccessItem(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人赛事报名成功查询
		TrainMatchAchievement item = trainMatchService.matchPersonalSignUpSuccessItem(trainMatchId, policeId);
		if (item.getStatus() == 2) {
			item.setCreationDateStr(getDatePoor(item.getMatchEndDate(), new Date()));
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 计算时间
	private String getDatePoor(Date endDate, Date nowDate) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		long ns = 1000;
		String string = null;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		long sec = diff % nd % nh % nm / ns;
		if (day == 0 && hour == 0 && min == 0 && sec == 0) {
			string = "已结束";
		} else if (day == 0 && hour == 0 && min == 0) {
			string = sec + "秒";
		} else if (day == 0 && hour == 0) {
			string = min + "分钟" + sec + "秒";
		} else if (day == 0) {
			string = hour + "小时" + min + "分钟" + sec + "秒";
		} else {
			string = day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
		}
		return string;
	}

	// 计算时间
	private String getSurplusStrDate(Date endDate, Date nowDate) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		long ns = 1000;
		String string = null;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		long sec = diff % nd % nh % nm / ns;
		if (day == 0 && hour == 0 && min == 0 && sec == 0) {
			string = "已结束";
		} else if (day == 0 && hour == 0 && min == 0) {
			string = sec + "秒";
		} else if (day == 0 && hour == 0) {
			string = min + "分钟";
		} else if (day == 0) {
			string = hour + "小时";
		} else {
			string = day + "天";
		}
		return string;
	}

	// 赛事签到确认查询
	@RequestMapping(value = "/match/sign/in/confirm/item", method = RequestMethod.GET)
	public ResponseEntity<?> matchSignInConfirmItem(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "scorerPoliceId", required = false) String scorerPoliceId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人赛事详情查询
		TrainMatchAchievement aItem = trainMatchService.matchAchievementItem(null, id, policeId);
		if (aItem != null) {
			// 判断当前用户是否可扫码当前人员
			TrainMatch scorerItem = trainMatchService.matchScorerPoliceItem(id, scorerPoliceId);
			if (scorerItem == null) {
				dlr.setStatus(false);
				dlr.setMessage("您此次无签到登记权限");
				dlr.setCode(StatusCode.getFailcode());
				return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
			} else {
				// 赛事详情查询
				TrainMatch tItem = trainMatchService.matchItem(id);
				aItem.setMatchTypeName(tItem.getMatchTypeName());
				aItem.setMatchProjectName(tItem.getProjectName());
				aItem.setNature(tItem.getNature());
				dlr.setStatus(true);
				dlr.setMessage("success");
				dlr.setResult(aItem);
				dlr.setCode(StatusCode.getSuccesscode());
			}
		}
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 赛事二维码签到
	@RequestMapping(value = "/match/sign/in/item", method = RequestMethod.GET)
	public ResponseEntity<?> matchSignInItem(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "scorerPoliceId", required = false) String scorerPoliceId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人赛事详情查询
		TrainMatchAchievement aItem = trainMatchService.matchAchievementItem(null, id, policeId);
		if (aItem != null) {
			// 判断当前用户是否可扫码当前人员
			TrainMatch scorerItem = trainMatchService.matchScorerPoliceItem(id, scorerPoliceId);
			if (scorerItem == null) {
				dlr.setStatus(false);
				dlr.setMessage("您此次无签到登记权限");
				dlr.setCode(StatusCode.getFailcode());
				return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
			} else {
				// 赛事详情查询
				TrainMatch tItem = trainMatchService.matchItem(id);
				long endTimeLong = tItem.getMatchEndDate().getTime();
				long currentTime = new Date().getTime();
				if (currentTime < endTimeLong) {// 当前时间小于训练结束时间
					if (aItem.getIsSign() == null || aItem.getIsSign() == 1) {
						aItem.setIsSign(2);
						aItem.setUpdateDate(new Date());
						aItem.setSignDate(new Date());
						trainMatchService.matchAchievementUpdate(aItem);
						if (tItem.getType() == 2) {
							// 根据policeId查询部门
							User user = userService.policeItem(policeId, null, null);
							TrainMatchDepartmentAchievement dItem = trainMatchService
									.trainMatchDepartmentAchievementItem(null, id, user.getDepartmentId());
							if (dItem != null) {
								dItem.setIsSign(2);
								dItem.setUpdateDate(new Date());
								trainMatchService.matchDepartmentAchievementUpdate(dItem);
							}
						}
						dlr.setStatus(true);
						dlr.setMessage("签到成功");
						dlr.setResult(aItem);
						dlr.setCode(StatusCode.getSuccesscode());
					} else if (aItem.getIsSign() == 2) {
						dlr.setStatus(true);
						dlr.setMessage("您已签到");
						dlr.setResult(aItem);
						dlr.setCode(StatusCode.getSuccesscode());
					}
				} else {
					dlr.setStatus(true);
					dlr.setMessage("比赛已结束");
					dlr.setResult(aItem);
					dlr.setCode(StatusCode.getSuccesscode());
				}
			}
		} else {
			dlr.setStatus(false);
			dlr.setMessage("您未报名当前比赛");
			dlr.setCode(StatusCode.getFailcode());
		}
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 赛事活动录分查询
	@RequestMapping(value = "/match/record/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchRecordScoreList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 需要录分的赛事活动查询
		List<MatchRecordScore> list = trainMatchService.matchRecordScoreList(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 赛事活动已完成录分查询
	@RequestMapping(value = "/match/record/over/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchRecordOverScoreList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 赛事活动已完成录分查询
		List<MatchRecordScore> list = trainMatchService.matchRecordOverScoreList(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 分局赛事录分提交页面查询
	@RequestMapping(value = "/match/office/submit/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchOfficeSubmitScoreList(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainMatch matchItem = trainMatchService.matchItem(trainMatchId);
		// 分局赛事录分提交页面查询
		List<MatchSubmit> list = trainMatchService.matchOfficeSubmitScoreList(trainMatchId);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setIsMoreUnit(matchItem.getIsMoreUnit());
				list.get(i).setSort(matchItem.getSort());
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 赛事需要录分的人员查询
	@RequestMapping(value = "/match/record/police/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchRecordPoliceScoreList(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 已签到人数统计
		TrainRecordPolice item = trainMatchService.matchRecordPoliceItem(trainMatchId);
		if (item != null) {
			// 赛事人员成绩查询
			List<TrainRank> list = trainMatchService.matchRecordPoliceScoreList(trainMatchId);
			item.setScoreList(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 赛事需要录分的部门查询
	@RequestMapping(value = "/match/record/department/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchRecordDepartmentScoreList(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 已签到部门统计
		TrainRecordPolice item = trainMatchService.matchRecordDepartmentItem(trainMatchId);
		if (item != null) {
			// 赛事部门成绩查询
			List<TrainRank> list = trainMatchService.matchRecordDepartmentScoreList(trainMatchId);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setUnitName("分");
			}
			item.setScoreList(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人赛事成绩录入
	@RequestMapping(value = { "/match/record/personal/score/update" }, method = RequestMethod.POST)
	public ResponseEntity<?> matchRecordPersonalScoreUpdate(
			@RequestParam(value = "resultJson", required = false) String resultJson) throws ApiException {
		DataListReturn dlr = new DataListReturn();
//		String ss = "[{'policeId':'115561','trainMatchId':'13','achievement':'3.5','achievementFirst':'3','achievementSecond':'30','achievementStr':'3分30秒'},"
//				+ "{'policeId':'115424','trainMatchId':'13','achievement':'4.75','achievementFirst':'4','achievementSecond':'45','achievementStr':'4分45秒'},"
//				+ "{'policeId':'114718','trainMatchId':'13','achievement':'5.42','achievementFirst':'5','achievementSecond':'25','achievementStr':'5分25秒'},"
//				+ "{'policeId':'114634','trainMatchId':'13','achievement':'6.5','achievementFirst':'6','achievementSecond':'30','achievementStr':'6分30秒'},"
//				+ "{'policeId':'113169','trainMatchId':'13','achievement':'7.5','achievementFirst':'7','achievementSecond':'30','achievementStr':'7分30秒'}]";
		JSONArray json = (JSONArray) JSONArray.parse(resultJson);
		Integer trainMatchId = null;
		for (Object obj : json) {
			JSONObject jo = (JSONObject) obj;
			TrainMatchAchievement item = new TrainMatchAchievement();
			item.setPoliceId(jo.getString("policeId"));
			item.setTrainMatchId(jo.getInteger("trainMatchId"));
			item.setAchievement(Double.parseDouble(jo.getString("achievement")));
			item.setAchievementFirst(jo.getInteger("achievementFirst"));
			item.setAchievementSecond(jo.getDouble("achievementSecond"));
			item.setAchievementStr(jo.getString("achievementStr"));
			item.setAchievementDate(new Date());
			item.setUpdateDate(new Date());
			// 个人赛事详情查询
			TrainMatchAchievement aItem = trainMatchService.matchAchievementItem(null, jo.getInteger("trainMatchId"),
					jo.getString("policeId"));
			if (aItem != null && aItem.getIsSign() == 1) {
				item.setIsSign(2);
				item.setSignDate(new Date());
			}
			item.setId(aItem.getId());
			trainMatchService.matchAchievementUpdate(item);
			trainMatchId = jo.getInteger("trainMatchId");
		}
		TrainMatch matchItem = trainMatchService.matchItem(trainMatchId);
		String sortStr = null;
		if (matchItem.getType() == 1) {
			// 查询当前赛事已经有成绩的人
			List<TrainMatchAchievement> list = trainMatchService.trainMatchAlreadyAchievementList(trainMatchId);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					TrainMatchBestAchievement bIte = trainMatchService.matchBestAchievementItem(null, null,
							matchItem.getType(), list.get(i).getPoliceId(), null);
					TrainMatchBestAchievement bestItem = new TrainMatchBestAchievement();
					if (matchItem.getSort() == 1) {// 1升序
						sortStr = "asc";
					} else if (matchItem.getSort() == 2) {// 2降序
						sortStr = "desc";
					}
					// 个人赛事排名查询
					TrainRank rankItem = trainMatchService.matchPersonalRankItem(trainMatchId,
							list.get(i).getPoliceId(), sortStr);
					if (bIte == null) {
						bestItem.setTrainMatchId(trainMatchId);
						bestItem.setType(matchItem.getType());
						bestItem.setDepartmentId(list.get(i).getDepartmentId());
						bestItem.setPoliceId(list.get(i).getPoliceId());
						bestItem.setTrainStartDate(matchItem.getMatchStartDate());
						bestItem.setTrainEndDate(matchItem.getMatchEndDate());
						bestItem.setAchievementStr(list.get(i).getAchievementStr());
						bestItem.setCreationDate(new Date());
						if (rankItem != null && rankItem.getRankId() != null && rankItem.getRankId() != 0) {
							bestItem.setRankId(rankItem.getRankId());
							trainMatchService.matchBestAchievementCreat(bestItem);
						}
					} else {
						if (rankItem != null && rankItem.getRankId() != null && rankItem.getRankId() != 0
								&& bIte.getRankId() >= rankItem.getRankId()) {
							bIte.setTrainMatchId(trainMatchId);
							bIte.setTrainStartDate(matchItem.getMatchStartDate());
							bIte.setTrainEndDate(matchItem.getMatchEndDate());
							bIte.setAchievementStr(list.get(i).getAchievementStr());
							bIte.setRankId(rankItem.getRankId());
							bIte.setUpdateDate(new Date());
							trainMatchService.matchBestAchievementUpdate(bIte);
						}
					}
				}
			}
			matchItem.setSubmitDate(new Date());
			matchItem.setIsSubmit(1);
			trainMatchService.trainMatchUpdate(matchItem);
		}
		dlr.setStatus(true);
		dlr.setMessage("录入成功");
		dlr.setResult(1);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 部门赛事成绩录入
	@RequestMapping(value = { "/match/record/dep/score/update" }, method = RequestMethod.POST)
	public ResponseEntity<?> matchRecordDepScoreUpdate(
			@RequestParam(value = "resultJson", required = false) String resultJson) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		Integer trainMatchId = null;
//		String ss = "[{'departmentId':'25','trainMatchId':'8','achievement':'90'},"
//				+ "{'departmentId':'26','trainMatchId':'8','achievement':'96'},"
//		        + "{'departmentId':'27','trainMatchId':'8','achievement':'95'},"
//				+ "{'departmentId':'28','trainMatchId':'8','achievement':'97'},"
//				+ "{'departmentId':'29','trainMatchId':'8','achievement':'98'}]";
		JSONArray json = (JSONArray) JSONArray.parse(resultJson);
		for (Object obj : json) {
			JSONObject jo = (JSONObject) obj;
			TrainMatchDepartmentAchievement item = new TrainMatchDepartmentAchievement();
			item.setDepartmentId(jo.getInteger("departmentId"));
			item.setTrainMatchId(jo.getInteger("trainMatchId"));
			item.setAchievementGrade(jo.getDouble("achievement") + "分");
			item.setAchievement(jo.getDouble("achievement"));
			item.setAchievementDate(new Date());
			item.setUpdateDate(new Date());
			// 部门详情查询
			TrainMatchDepartmentAchievement aItem = trainMatchService.trainMatchDepartmentAchievementItem(null,
					jo.getInteger("trainMatchId"), jo.getInteger("departmentId"));
			if (aItem != null && aItem.getIsSign() == 1) {
				item.setIsSign(2);
			}
			item.setId(aItem.getId());
			trainMatchService.matchDepartmentAchievementUpdate(item);
			trainMatchId = jo.getInteger("trainMatchId");
		}
		TrainMatch matchItem = trainMatchService.matchItem(trainMatchId);
		if (matchItem.getType() == 2) {
			// 查询当前赛事已经有成绩的人
			List<TrainMatchAchievement> list = trainMatchService.trainMatchAlreadyAchievementList(trainMatchId);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					TrainMatchBestAchievement bIte = trainMatchService.matchBestAchievementItem(null, null,
							matchItem.getType(), list.get(i).getPoliceId(), null);
					TrainMatchBestAchievement bestItem = new TrainMatchBestAchievement();
					// 个人分局赛事排名查询
					TrainRank rankItem = trainMatchService.matchDepRankItem(trainMatchId,
							list.get(i).getDepartmentId());
					if (bIte == null) {
						bestItem.setTrainMatchId(trainMatchId);
						bestItem.setType(matchItem.getType());
						bestItem.setDepartmentId(list.get(i).getDepartmentId());
						bestItem.setPoliceId(list.get(i).getPoliceId());
						bestItem.setTrainStartDate(matchItem.getMatchStartDate());
						bestItem.setTrainEndDate(matchItem.getMatchEndDate());
						bestItem.setAchievementStr(list.get(i).getAchievementStr());
						bestItem.setCreationDate(new Date());
						if (rankItem != null && rankItem.getRankId() != null && rankItem.getRankId() != 0) {
							bestItem.setRankId(rankItem.getRankId());
							trainMatchService.matchBestAchievementCreat(bestItem);
						}
					} else {
						if (rankItem != null && rankItem.getRankId() != null && rankItem.getRankId() != 0
								&& bIte.getRankId() >= rankItem.getRankId()) {
							bIte.setTrainMatchId(trainMatchId);
							bIte.setTrainStartDate(matchItem.getMatchStartDate());
							bIte.setTrainEndDate(matchItem.getMatchEndDate());
							bIte.setAchievementStr(list.get(i).getAchievementStr());
							bIte.setRankId(rankItem.getRankId());
							bIte.setUpdateDate(new Date());
							trainMatchService.matchBestAchievementUpdate(bIte);
						}
					}
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("录入成功");
		dlr.setResult(1);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 比赛成绩提交
	@RequestMapping(value = { "/match/score/submit" }, method = RequestMethod.POST)
	public ResponseEntity<?> matchScoreSubmit(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainMatch item = trainMatchService.matchItem(trainMatchId);
		item.setIsSubmit(1);
		item.setSubmitDate(new Date());
		item.setUpdateDate(new Date());
		int count = trainMatchService.trainMatchUpdate(item);
		if (item.getType() == 2) {
			// 比赛领队排行榜查询
			List<TrainRank> list = trainMatchService.matchLeaderRankList(trainMatchId, 3);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					// 领队比赛人员成功查询
					List<TrainRecommendPolice> policeList = trainMatchService.matchLeaderSignUpSuccessList(trainMatchId,
							list.get(i).getProjectId());
					if (policeList.size() > 0) {
						for (int j = 0; j < policeList.size(); j++) {
							if (list.get(i).getRankId() != null) {
								TrainGetMedal gItem = new TrainGetMedal();
								if (list.get(i).getRankId() == 1) {
									gItem.setMedalId(8);
								} else if (list.get(i).getRankId() == 2) {
									gItem.setMedalId(9);
								} else if (list.get(i).getRankId() == 3) {
									gItem.setMedalId(10);
								}
								gItem.setObjectId(3);
								gItem.setTrainId(trainMatchId);
								gItem.setPoliceId(policeList.get(j).getPoliceId());
								gItem.setGetDate(new Date());
								gItem.setCreationDate(new Date());
								trainMatchService.trainGetMedalCreat(gItem);
							}
						}
					}
				}
			}
		}
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("提交成功");
			dlr.setResult(1);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("提交失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 领队比赛列表查询
	@RequestMapping(value = "/match/leader/page/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchLeaderPageList(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "signUpStatus", required = false) Integer signUpStatus,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		MatchLeaderStatistics item = new MatchLeaderStatistics();
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		// 领队比赛列表查询
		List<TrainMatch> list = trainMatchService.matchLeaderPageList(policeId, signUpStatus, status, departmentId,
				pageSize, pageNum);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getPoliceNum() == null) {
					list.get(i).setPoliceNum(0);
				}
			}
		}
		// 领队比赛列表数量统计
		int total = trainMatchService.matchLeaderPageCount(policeId, signUpStatus, status, departmentId);
		int total1 = trainMatchService.matchLeaderPageCount(policeId, 2, null, departmentId);
		int total2 = trainMatchService.matchLeaderPageCount(policeId, null, 1, departmentId);
		int total3 = trainMatchService.matchLeaderPageCount(policeId, null, 2, departmentId);
		int total4 = trainMatchService.matchLeaderPageCount(policeId, null, 3, departmentId);
		item.setSignUpNum(total1);
		item.setMatchNotStartedNum(total2);
		item.setMatchOngoingNum(total3);
		item.setMatchOverNum(total4);
		item.setMatchList(list);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 领队推荐人员查询
	@RequestMapping(value = "/match/recommend/police/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchRecommendPoliceList(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		Integer type = 0;
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		// 赛事详情查询
		TrainMatch item = trainMatchService.matchItem(id);
		if (item.getSex() == null || item.getSex() == 3) {// 男女不限
			type = 3;
		} else if (item.getSex() == 2) {// 女性
			type = 2;
		} else if (item.getSex() == 1) {// 男性
			type = 1;
		}
		// 根据性别查询部门人员
		List<TrainRecommendPolice> list = trainMatchService.matchRecommendPoliceList(user.getDepartmentId(), type);
		dlr.setResult(list);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 领队限报人数查询
	@RequestMapping(value = "/match/limit/report/police/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> matchLimitReportPoliceStatistics(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainMatchLimitReport report = new TrainMatchLimitReport();
		// 赛事详情查询
		TrainMatch item = trainMatchService.matchItem(id);
		if (item.getManMaxNum() > 0 && item.getWomanMaxNum() == 0) {
			report.setManStr("男性人数限报" + item.getManMaxNum() + "人");
			report.setManNum(item.getManMaxNum());
			report.setWomanStr("女性不可报名");
			report.setWomanNum(0);
		} else if (item.getManMaxNum() > 0 && item.getWomanMaxNum() > 0) {
			report.setManStr("男性人数限报" + item.getManMaxNum() + "人");
			report.setManNum(item.getManMaxNum());
			report.setWomanStr("女性人数限报" + item.getWomanMaxNum() + "人");
			report.setWomanNum(item.getWomanMaxNum());
		} else if (item.getManMaxNum() == 0 && item.getWomanMaxNum() > 0) {
			report.setManStr("男性不可报名");
			report.setManNum(0);
			report.setWomanStr("女性人数限报" + item.getWomanMaxNum() + "人");
			report.setWomanNum(item.getWomanMaxNum());
		}
		report.setIsLimit(2);
		dlr.setResult(report);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 部门参加人员信息查询
	@RequestMapping(value = "/match/dep/police/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchDepPoliceList(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "keywords", required = false) String keywords) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		Integer type = 0;
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		// 赛事详情查询
		TrainMatch item = trainMatchService.matchItem(id);
		if (item.getSex() == null || item.getSex() == 3) {// 男女不限
			type = 3;
		} else if (item.getSex() == 2) {// 女性
			type = 2;
		} else if (item.getSex() == 1) {// 男性
			type = 1;
		}
		// 部门参加人员信息查询
		List<TrainRecommendPolice> list = trainMatchService.matchDepPoliceList(user.getDepartmentId(), type, keywords);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getIsRecommend() == null) {
				list.get(i).setIsRecommend(0);
			}
		}
		// 根据性别查询部门人员
		List<TrainRecommendPolice> list2 = trainMatchService.matchRecommendPoliceList(user.getDepartmentId(), type);
		if (list2.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list2.size(); j++) {
					if (list2.get(j).getPoliceId().equals(list.get(i).getPoliceId())) {
						list.get(i).setIsRecommend(1);
					}
				}
			}
		}
		dlr.setResult(list);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 领队比赛报名
	@ResponseBody
	@RequestMapping(value = { "/match/leader/sign/up/creat" }, method = RequestMethod.POST)
	public DataListReturn matchLeaderSignUpCreat(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId,
			@RequestParam(value = "resultJson", required = false) String resultJson)
			throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		int count = 0;
		// 赛事详情查询
		TrainMatch tItem = trainMatchService.matchItem(trainMatchId);
		long startTimeLong = tItem.getRegistrationStartDate().getTime();
		long endTimeLong = tItem.getRegistrationEndDate().getTime();
		long currentTime = new Date().getTime();
		if (currentTime < startTimeLong) {
			dlr.setStatus(false);
			dlr.setMessage("报名未开始");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		} else if (currentTime > endTimeLong) {
			dlr.setStatus(false);
			dlr.setMessage("报名已截止");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
//		String ss = "[{'policeId':'117343','trainMatchId':'17'}," + "{'policeId':'117284','trainMatchId':'17'},"
//				+ "{'policeId':'117565','trainMatchId':'17'}," + "{'policeId':'117020','trainMatchId':'17'},"
//				+ "{'policeId':'116629','trainMatchId':'17'}]";
		JSONArray json1 = (JSONArray) JSONArray.parse(resultJson);
		Integer departmentId = null;
		// 查询当前项目男性报名人数
		int manNum = 0;
		// 查询当前项目女性报名人数
		int womanNum = 0;
		Integer obj1DepartmentId = null;
		for (Object obj1 : json1) {
			JSONObject jo = (JSONObject) obj1;
			String policeId = jo.getString("policeId");
			// 根据policeId查询部门
			User user = userService.policeItem(policeId, null, null);
			obj1DepartmentId = user.getDepartmentId();
			int sex = user.getGender();
			if (sex == 1) {
				manNum = manNum + 1;
			} else {
				womanNum = womanNum + 1;
			}
		}
		// 查询本单位是否已经完成报名
		List<TrainMatchAchievement> reList = trainMatchService.registrationLompletedList(trainMatchId,
				obj1DepartmentId);
		if (reList.size() > 0) {
			dlr.setStatus(false);
			dlr.setMessage("当前单位不可重复报名");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		} else {
			if (tItem.getManMaxNum() > 0 && tItem.getWomanMaxNum() == 0) {
				if (tItem.getManMaxNum() < manNum) {
					dlr.setStatus(false);
					dlr.setMessage("报名人数不能超过" + tItem.getManMaxNum() + "人");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getManMaxNum() > 0 && tItem.getWomanMaxNum() > 0) {
				if (tItem.getManMaxNum() < manNum) {
					dlr.setStatus(false);
					dlr.setMessage("男性报名人数不能超过" + tItem.getManMaxNum() + "人");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				} else if (tItem.getWomanMaxNum() < womanNum) {// 女
					dlr.setStatus(false);
					dlr.setMessage("女性报名人数不能超过" + tItem.getWomanMaxNum() + "人");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getManMaxNum() == 0 && tItem.getWomanMaxNum() > 0) {
				if (tItem.getWomanMaxNum() < womanNum) {// 女
					dlr.setStatus(false);
					dlr.setMessage("报名人数不能超过" + tItem.getWomanMaxNum() + "人");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			}
			JSONArray json = (JSONArray) JSONArray.parse(resultJson);
			for (Object obj : json) {
				JSONObject jo = (JSONObject) obj;
				String policeId = jo.getString("policeId");
				TrainMatchAchievement item = new TrainMatchAchievement();
				// 根据policeId查询部门
				User user = userService.policeItem(policeId, null, null);
				departmentId = user.getDepartmentId();
				item.setTrainMatchId(trainMatchId);
				item.setPoliceId(policeId);
				item.setDepartmentId(departmentId);
				item.setRegistrationDate(new Date());
				item.setIsSign(1);
				item.setCreationDate(new Date());
				String qrName = "physical-" + UUID.randomUUID().toString() + ".jpg";
				item.setQrCode(trainQRCode + qrName);
				// 生成二维码
				new QRCode(root + trainQRCodeIcon).encodeQRCode(trainMatchId + "," + policeId, 500, 500,
						root + trainQRCode + qrName);
				count = trainMatchService.matchAchievementCreat(item);
			}
			TrainMatchDepartmentAchievement dItem = new TrainMatchDepartmentAchievement();
			dItem.setTrainMatchId(trainMatchId);
			dItem.setDepartmentId(departmentId);
			dItem.setIsSign(1);
			dItem.setCreationDate(new Date());
			int count2 = trainMatchService.matchDepartmentAchievementCreat(dItem);
			if (count > 0 && count2 > 0) {
				dlr.setStatus(true);
				dlr.setMessage("报名成功");
				dlr.setResult(count);
				dlr.setCode(StatusCode.getSuccesscode());
				return dlr;
			} else {
				dlr.setStatus(false);
				dlr.setMessage("报名失败");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
		}
	}

	// 领队比赛报名成功页面查询
	@RequestMapping(value = "/match/leader/sign/up/success/item", method = RequestMethod.GET)
	public ResponseEntity<?> matchLeaderSignUpSuccessItem(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		TrainLeaderSignUpSuccess item = new TrainLeaderSignUpSuccess();
		TrainMatch fPhysical = trainMatchService.matchItem(trainMatchId);
		// 领队比赛人员成功查询
		List<TrainRecommendPolice> list = trainMatchService.matchLeaderSignUpSuccessList(trainMatchId,
				user.getDepartmentId());
		item.setName(fPhysical.getName());
		item.setPlace(fPhysical.getPlace());
		item.setStatus(fPhysical.getStatus());
		item.setPoliceNum(list.size());
		if (fPhysical.getStatus() == 2) {
			String timeString = getDatePoor(fPhysical.getMatchEndDate(), new Date());
			item.setCreationDateStr(timeString);
		}
		item.setPoliceList(list);
		dlr.setResult(item);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 全部人员分局赛事实时排名榜查询
	@RequestMapping(value = "/match/branch/office/police/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchBranchEntryRankList(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 赛事详情查询
		TrainMatch fitem = trainMatchService.matchItem(trainMatchId);
		if (fitem.getStatus() != 1) {
			String sortStr = null;
			if (fitem.getSort() == 1) {// 升序
				sortStr = "asc";
			} else if (fitem.getSort() == 2) {// 降序
				sortStr = "desc";
			}
			// 赛事排名
			List<TrainRank> rankList = trainMatchService.matchMyEntryRankList(trainMatchId, sortStr);
			if (rankList.size() > 0) {
				for (int i = 0; i < rankList.size(); i++) {
					// 根据policeId查询部门
					User user = userService.policeItem(rankList.get(i).getPoliceId(), null, null);
					rankList.get(i).setDepartmentName(user.getDepartmentName());
				}
				dlr.setResult(rankList);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 领队自己部门比赛成绩排行榜
	@RequestMapping(value = "/match/leader/dep/police/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchLeaderDepPoliceRankList(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 赛事详情查询
		TrainMatch fitem = trainMatchService.matchItem(trainMatchId);
		String sortStr = null;
		if (fitem.getSort() == 1) {// 升序
			sortStr = "asc";
		} else if (fitem.getSort() == 2) {// 降序
			sortStr = "desc";
		}
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		// 领队自己部门比赛成绩排行榜
		List<TrainRank> list = trainMatchService.matchLeaderDepPoliceRankList(trainMatchId, user.getDepartmentId(),
				sortStr);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 领队比赛成绩排行榜
	@RequestMapping(value = "/match/leader/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchLeaderRankList(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		Integer limitNum = 10000;
		// 比赛领队排行榜查询
		List<TrainRank> list = trainMatchService.matchLeaderRankList(trainMatchId, limitNum);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 比赛领队更多排行榜查询
	@RequestMapping(value = "/match/leader/more/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchLeaderMoreRankList(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		Integer limitNum = 10000;
		// 比赛领队排行榜查询
		List<TrainRank> list = trainMatchService.matchLeaderRankList(trainMatchId, limitNum);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 领队部门人员比赛成绩查询
	@RequestMapping(value = "/match/leader/dep/achievement/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchLeaderDepAchievementList(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainLeaderMoreAchievement item = new TrainLeaderMoreAchievement();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		// 领队部门人员比赛成绩查询(已签到)
		List<TrainRecommendPolice> yesList = trainMatchService.matchLeaderDepAchievementList(trainMatchId,
				user.getDepartmentId(), 2);
		if (yesList.size() > 0) {
			item.setYesList(yesList);
		}
		// 领队部门人员比赛成绩查询(未签到)
		List<TrainRecommendPolice> yesList2 = trainMatchService.matchLeaderDepAchievementList(trainMatchId,
				user.getDepartmentId(), 1);
		item.setTotalNum(yesList.size() + yesList2.size());
		item.setYesNum(yesList.size());
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 赛事领队报名人员查询
	@RequestMapping(value = "/match/applicants/leader/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchApplicantsLeaderList(
			@RequestParam(value = "trainMatchId", required = false) Integer trainMatchId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		MatchLeaderApplicant item = new MatchLeaderApplicant();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		item.setDepartmentName(user.getDepartmentName());
		// 赛事领队报名人员查询
		List<TrainMatchAchievement> list = trainMatchService.matchApplicantsLeaderList(trainMatchId,
				user.getDepartmentId());
		if (list.size() > 0) {
			list.removeAll(Collections.singleton(null));
			item.setList(list);
			item.setTotalNum(list.size());
		} else {
			item.setTotalNum(0);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 训练/赛事通知查询
	@RequestMapping(value = "/train/match/notification/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainMatchNotificationList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		// 训练/赛事通知查询
		List<TrainPhysical> list = trainService.trainMatchNotificationList(policeId, user.getDepartmentId());
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 本周训练/赛事数量统计
	@RequestMapping(value = "/train/match/number/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> trainMatchNumberStatistics(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		ReportDataFillTime fillTime = fillDate(null, 1);// 获取本周开始时间和结束时间
		String startTime = fillTime.getStartTime();
		String endTime = fillTime.getEndTime();
		TrainMatchNumber item = new TrainMatchNumber();
		// 本周训练查询
		List<TrainPhysical> trainList = trainService.trainWeekList(policeId, user.getDepartmentId(), startTime,
				endTime);
		item.setTrainNum(trainList.size());
		// 本周赛事查询
		List<TrainMatch> list = trainMatchService.matchWeekList(policeId, user.getDepartmentId(), startTime, endTime);
		item.setMatchNum(list.size());
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 本周训练查询
	@RequestMapping(value = "/train/week/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainWeekList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		ReportDataFillTime fillTime = fillDate(null, 1);// 获取本周开始时间和结束时间
		String startTime = fillTime.getStartTime();
		String endTime = fillTime.getEndTime();
		// 本周训练查询
		List<TrainPhysical> list = trainService.trainWeekList(policeId, user.getDepartmentId(), startTime, endTime);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getStatus() == 1) {
					int timeItem = Math.abs(list.get(i).getTimeChange());
					if (timeItem == 0) {
						list.get(i).setCreationDateStr("1分钟后开始");
					} else if (timeItem < 60 && timeItem > 0) {
						list.get(i).setCreationDateStr(timeItem + "分钟后开始");
					} else if (timeItem >= 60 && timeItem < 1440) {
						int timeInt = (int) Math.floor(timeItem / 60);
						list.get(i).setCreationDateStr(timeInt + "小时后开始");
					} else if (timeItem >= 1440) {
						int timeInt = (int) Math.floor(timeItem / 60 / 24);
						list.get(i).setCreationDateStr(timeInt + "天后开始");
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

	// 本周赛事查询
	@RequestMapping(value = "/match/week/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchWeekList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		ReportDataFillTime fillTime = fillDate(null, 1);// 获取本周开始时间和结束时间
		String startTime = fillTime.getStartTime();
		String endTime = fillTime.getEndTime();
		// 本周赛事查询
		List<TrainMatch> list = trainMatchService.matchWeekList(policeId, user.getDepartmentId(), startTime, endTime);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getStatus() == 1) {
					int timeItem = Math.abs(list.get(i).getTimeChange());
					if (timeItem == 0) {
						list.get(i).setCreationDateStr("1分钟后开始");
					} else if (timeItem < 60 && timeItem > 0) {
						list.get(i).setCreationDateStr(timeItem + "分钟后开始");
					} else if (timeItem >= 60 && timeItem < 1440) {
						int timeInt = (int) Math.floor(timeItem / 60);
						list.get(i).setCreationDateStr(timeInt + "小时后开始");
					} else if (timeItem >= 1440) {
						int timeInt = (int) Math.floor(timeItem / 60 / 24);
						list.get(i).setCreationDateStr(timeInt + "天后开始");
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

	// 即将训练查询(2条)
	@RequestMapping(value = "/train/soon/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainSoonList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		// 即将训练查询
		List<TrainPhysical> list = trainService.trainSoonList(policeId, user.getDepartmentId());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getStatus() == 1) {
					int timeItem = Math.abs(list.get(i).getTimeChange());
					if (timeItem == 0) {
						list.get(i).setCreationDateStr("1分钟后开始");
					} else if (timeItem < 60 && timeItem > 0) {
						list.get(i).setCreationDateStr(timeItem + "分钟后开始");
					} else if (timeItem >= 60 && timeItem < 1440) {
						int timeInt = (int) Math.floor(timeItem / 60);
						list.get(i).setCreationDateStr(timeInt + "小时后开始");
					} else if (timeItem >= 1440) {
						int timeInt = (int) Math.floor(timeItem / 60 / 24);
						list.get(i).setCreationDateStr(timeInt + "天后开始");
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

	// 即将比赛赛事查询(2条)
	@RequestMapping(value = "/match/soon/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchSoonList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		// 即将比赛赛事查询
		List<TrainMatch> list = trainMatchService.matchSoonList(policeId, user.getDepartmentId());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getStatus() == 1) {
					int timeItem = Math.abs(list.get(i).getTimeChange());
					if (timeItem == 0) {
						list.get(i).setCreationDateStr("1分钟后开始");
					} else if (timeItem < 60 && timeItem > 0) {
						list.get(i).setCreationDateStr(timeItem + "分钟后开始");
					} else if (timeItem >= 60 && timeItem < 1440) {
						int timeInt = (int) Math.floor(timeItem / 60);
						list.get(i).setCreationDateStr(timeInt + "小时后开始");
					} else if (timeItem >= 1440) {
						int timeInt = (int) Math.floor(timeItem / 60 / 24);
						list.get(i).setCreationDateStr(timeInt + "天后开始");
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

	// 进行中的训练/赛事查询
	@RequestMapping(value = "/train/match/ongoing/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainMatchOngoingList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		TrainLeaderStatistics item = new TrainLeaderStatistics();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		// 进行中的训练/赛事查询
		List<TrainPhysical> list = trainService.trainMatchOngoingList(policeId, user.getDepartmentId());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getPoliceNum() == null) {
					list.get(i).setPoliceNum(0);
				}
			}
			item.setTrainOngoingNum(list.size());
			item.setTrainList(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 正在报名的训练/赛事查询
	@RequestMapping(value = "/train/match/sign/up/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainMatchSignUpList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		TrainLeaderStatistics item = new TrainLeaderStatistics();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		// 正在报名的训练/赛事查询
		List<TrainPhysical> list = trainService.trainMatchSignUpList(policeId, user.getDepartmentId());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getPoliceNum() == null) {
					list.get(i).setPoliceNum(0);
				}
			}
			item.setSignUpNum(list.size());
			item.setTrainList(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 推荐/活动风采/训练标兵/训练章程查询
	@RequestMapping(value = "/recommend/list", method = RequestMethod.GET)
	public ResponseEntity<?> recommendList(@RequestParam(value = "type", required = false) Integer type)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		Integer limitNum = 30;
		if (type == 1) {// 推荐
			// 推荐查询
			List<TrainActivityStyle> list = trainMatchService.recommendList(limitNum);
			dlr.setResult(list);
		} else if (type == 2) {// 活动风采
			// 活动风采查询
			List<TrainActivityStyle> list = trainMatchService.activityStyleList();
			dlr.setResult(list);
		} else if (type == 3) {// 训练标兵
			// 训练标兵查询
			List<TrainActivityStyle> list = trainMatchService.pacesetterList();
			dlr.setResult(list);
		} else if (type == 4) {// 训练章程
			// 训练章程查询
			List<TrainActivityStyle> list = trainMatchService.constitutionList();
			dlr.setResult(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 推荐/活动风采/训练标兵/训练章程首页查询
	@RequestMapping(value = "/train/home/page/activity/list", method = RequestMethod.GET)
	public ResponseEntity<?> activityList(@RequestParam(value = "type", required = false) Integer type)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		Integer limitNum = 30;
		List<TrainActivityStyle> list = new ArrayList<TrainActivityStyle>();
		if (type == 1) {// 推荐
			// 推荐查询
			list = trainMatchService.recommendList(limitNum);
		} else if (type == 2) {// 活动风采
			// 活动风采查询
			list = trainMatchService.activityStyleList();
		} else if (type == 3) {// 训练标兵
			// 训练标兵查询
			list = trainMatchService.pacesetterList();
		} else if (type == 4) {// 训练章程
			// 训练章程查询
			list = trainMatchService.constitutionList();
		}
		if (list.size() <= 4) {
			dlr.setResult(list);
		} else {
			dlr.setResult(list.subList(0, 4));// 取前四条数据
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 活动风采/训练标兵/训练章程详情查询
	@RequestMapping(value = "/recommend/item", method = RequestMethod.GET)
	public ResponseEntity<?> recommendItem(@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "id", required = false) Integer id) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		if (objectId == 1) {// 活动风采
			// 活动风采详情查询
			TrainActivityStyle item = trainMatchService.activityStyleItem(id);
			// 点赞人员查询
			List<TrainLikeRecord> likeList = trainMatchService.trainLikeRecordList(objectId, id, null);
			item.setLikeList(likeList);
			// 评论查询
			List<TrainEvaluateRecord> evaluateList = trainMatchService.trainEvaluateRecordList(objectId, id, null);
			item.setEvaluateList(evaluateList);
			item.setEvaluateNum(evaluateList.size());
			dlr.setResult(item);
		} else if (objectId == 2) {// 训练标兵
			// 训练标兵详情查询
			TrainActivityStyle item = trainMatchService.pacesetterItem(id);
			// 点赞人员查询
			List<TrainLikeRecord> likeList = trainMatchService.trainLikeRecordList(objectId, id, null);
			item.setLikeList(likeList);
			// 评论查询
			List<TrainEvaluateRecord> evaluateList = trainMatchService.trainEvaluateRecordList(objectId, id, null);
			item.setEvaluateList(evaluateList);
			item.setEvaluateNum(evaluateList.size());
			dlr.setResult(item);
		} else if (objectId == 3) {// 训练章程
			// 训练章程详情查询
			TrainActivityStyle item = trainMatchService.constitutionItem(id);
			dlr.setResult(item);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 活动浏览量新增
	@RequestMapping(value = { "/train/activity/creat" }, method = RequestMethod.POST)
	public ResponseEntity<?> trainActivityCreat(@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "id", required = false) Integer id) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		int count = 0;
		if (objectId == 1) {// 活动风采
			// 活动风采详情查询
			TrainActivityStyle item = trainMatchService.activityStyleItem(id);
			item.setId(id);
			item.setView(item.getView() + 1);
			item.setUpdateDate(new Date());
			count = trainMatchService.updateTrainActivityStyle(item);
		} else if (objectId == 2) {// 训练标兵
			// 训练标兵详情查询
			TrainActivityStyle item = trainMatchService.pacesetterItem(id);
			TrainPacesetter pItem = new TrainPacesetter();
			pItem.setId(id);
			pItem.setView(item.getView() + 1);
			pItem.setUpdateDate(new Date());
			count = trainMatchService.updateTrainPacesetter(pItem);
		} else if (objectId == 3) {// 训练章程
			// 训练章程详情查询
			TrainActivityStyle item = trainMatchService.constitutionItem(id);
			TrainConstitution cItem = new TrainConstitution();
			cItem.setId(id);
			cItem.setView(item.getView() + 1);
			cItem.setUpdateDate(new Date());
			count = trainMatchService.updateTrainConstitution(cItem);
		}
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("浏览新增成功");
			dlr.setResult(1);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("浏览新增失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 点赞新增
	@RequestMapping(value = { "/train/like/creat" }, method = RequestMethod.POST)
	public ResponseEntity<?> trainLikeCreat(@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainLikeRecord recordItem = trainMatchService.trainLikeRecordItem(null, objectId, id, policeId);
		if (recordItem == null) {
			int count = 0;
			TrainLikeRecord record = new TrainLikeRecord();
			if (objectId == 1) {// 活动风采
				// 活动风采详情查询
				TrainActivityStyle item = trainMatchService.activityStyleItem(id);
				item.setId(id);
				item.setLikeNum(item.getLikeNum() + 1);
				item.setUpdateDate(new Date());
				count = trainMatchService.updateTrainActivityStyle(item);
			} else if (objectId == 2) {// 训练标兵
				// 训练标兵详情查询
				TrainActivityStyle item = trainMatchService.pacesetterItem(id);
				TrainPacesetter pItem = new TrainPacesetter();
				pItem.setId(id);
				pItem.setLikeNum(item.getLikeNum() + 1);
				pItem.setUpdateDate(new Date());
				count = trainMatchService.updateTrainPacesetter(pItem);
			}
			record.setObjectId(objectId);
			record.setActivityId(id);
			record.setPoliceId(policeId);
			record.setCreationDate(new Date());
			int count2 = trainMatchService.trainLikeRecordCreat(record);
			if (count > 0 && count2 > 0) {
				dlr.setStatus(true);
				dlr.setMessage("点赞成功");
				dlr.setResult(1);
				dlr.setCode(StatusCode.getSuccesscode());
			} else {
				dlr.setStatus(false);
				dlr.setMessage("点赞失败");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
			}
		} else {
			dlr.setStatus(false);
			dlr.setMessage("点赞重复");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 点赞取消
	@RequestMapping(value = { "/train/like/delete" }, method = RequestMethod.POST)
	public ResponseEntity<?> trainLikeDelete(@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		int count = 0;
		int count2 = trainMatchService.trainLikeRecordDelete(objectId, id, policeId);
		if (objectId == 1) {// 活动风采
			// 活动风采详情查询
			TrainActivityStyle item = trainMatchService.activityStyleItem(id);
			item.setId(id);
			item.setLikeNum(item.getLikeNum() - 1);
			item.setUpdateDate(new Date());
			count = trainMatchService.updateTrainActivityStyle(item);
		} else if (objectId == 2) {// 训练标兵
			// 训练标兵详情查询
			TrainActivityStyle item = trainMatchService.pacesetterItem(id);
			TrainPacesetter pItem = new TrainPacesetter();
			pItem.setId(id);
			pItem.setLikeNum(item.getLikeNum() - 1);
			pItem.setUpdateDate(new Date());
			count = trainMatchService.updateTrainPacesetter(pItem);
		}
		if (count > 0 && count2 > 0) {
			dlr.setStatus(true);
			dlr.setMessage("点赞取消成功");
			dlr.setResult(1);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("点赞取消失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 评论新增
	@RequestMapping(value = { "/train/evaluate/creat" }, method = RequestMethod.POST)
	public ResponseEntity<?> trainEvaluateCreat(@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "content", required = false) String content) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainEvaluateRecord record = new TrainEvaluateRecord();
		record.setObjectId(objectId);
		record.setActivityId(id);
		record.setPoliceId(policeId);
		record.setContent(content);
		record.setCreationDate(new Date());
		int count = trainMatchService.trainEvaluateRecordCreat(record);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("评论成功");
			dlr.setResult(1);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("评论失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人奖章详情查询
	@RequestMapping(value = "/train/medal/personal/item", method = RequestMethod.GET)
	public ResponseEntity<?> medalPersonalItem(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 个人奖章详情查询
		TrainMedalPerson item = trainMatchService.medalPersonalItem(policeId);
		if (item.getName() == null) {
			// 根据policeId查询部门
			User user = userService.policeItem(policeId, null, null);
			item.setHeadImage(user.getHeadImage());
			item.setPoliceId(policeId);
			item.setName(user.getName());
			item.setMedalNum(0);
			item.setExceedRate(0);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人获得训练奖章/赛事奖章查询
	@RequestMapping(value = "/train/get/medal/personal/List", method = RequestMethod.GET)
	public ResponseEntity<?> trainGetMedalPersonalList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 个人获得训练奖章/赛事奖章查询
		List<TrainMedalManage> list = trainMatchService.trainGetMedalPersonalList(policeId, type);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getIsBright() == null || list.get(i).getIsBright() == 0) {
					list.get(i).setImage(list.get(i).getGreyImage());
				}
			}
		}
		dlr.setResult(list);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人训练奖章/赛事奖章详情查询
	@RequestMapping(value = "/train/get/medal/personal/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainGetMedalPersonalItem(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "medalManageId", required = false) Integer medalManageId)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		List<TrainMedalManage> list = new ArrayList<TrainMedalManage>();
		// 个人训练奖章/赛事奖章详情查询
		TrainMedalManage item = trainMatchService.trainGetMedalPersonalItem(policeId, type, medalManageId);
		if (item != null) {
			if (item.getIsBright() == null || item.getIsBright() == 0) {
				item.setImage(item.getGreyImage());
			}
		}
		list.add(item);
		// 查询剩余奖牌id
		List<TrainMedalManage> surplusList = trainMatchService.surplusMedalList(type, medalManageId);
		for (int i = 0; i < surplusList.size(); i++) {
			TrainMedalManage sitem = trainMatchService.trainGetMedalPersonalItem(policeId, type,
					surplusList.get(i).getId());
			list.add(sitem);
		}
		dlr.setResult(list);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人奖章墙查询
	@RequestMapping(value = "/train/medal/personal/wall/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainMedalPersonalWallList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 个人奖章墙查询
		List<TrainMedalManage> list = trainMatchService.trainMedalPersonalWallList(policeId, type);
		dlr.setResult(list);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人成绩统计查询
	@RequestMapping(value = "/train/personal/achievement/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> trainPersonalAchievementStatistics(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = null;
		} else {
			dateTime = dateTime.substring(0, 7);
		}
		// 个人成绩统计查询
		TrainPersonalAchievementStatistics item = trainMatchService.trainPersonalAchievementItem(policeId, type,
				dateTime);
		// 查询训练最近一次成绩
		TrainPhysical pitem = trainService.newsetScoreEnterItem(policeId);
		if (pitem != null) {
			if (pitem.getObjectId() == 1) {// 综合训练
				// 最近一次综合训练成绩查询
				TrainPersonalAchievementStatistics sitem = trainMatchService.trainPhysicallAchievementItem(policeId);
				item.setTrainName(sitem.getTrainName());
				item.setTrainAchievement(sitem.getTrainAchievement());
			} else if (pitem.getObjectId() == 2) {// 枪械
				// 最近一次枪械成绩查询
				TrainPersonalAchievementStatistics sitem = trainMatchService.trainFirearmAchievementItem(policeId);
				item.setTrainName(sitem.getTrainName());
				item.setTrainAchievement(sitem.getTrainAchievement());
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人最近成绩查询
	@RequestMapping(value = "/train/personal/lately/achievement/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainPersonalLatelyAchievementList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		List<TrainPhysical> list = trainService.trainPersonalLatelyAchievementList(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人最好成绩查询
	@RequestMapping(value = "/train/personal/best/achievement/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainPersonalBestAchievementList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		List<TrainPhysical> list = trainService.trainPersonalBestAchievementList(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人训练成绩列表查询
	@RequestMapping(value = "/train/personal/achievement/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainPersonalAchievementList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		if (!StringUtils.isEmpty(dateTime)) {
			dateTime = dateTime.substring(0, 7);
		}
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		// 个人训练成绩时间查询
		List<TrainTimeName> dateList = trainService.trainTimeNameList(policeId, type, dateTime, pageSize, pageNum);
		// 个人训练成绩列表查询
		List<TrainPersonalAchievement> list = trainService.trainPersonalAchievementList(policeId, type, dateTime,
				pageSize, pageNum);
		// 个人训练成绩列表总数查询
		int total = trainService.trainPersonalAchievementCount(policeId, type, dateTime);
		if (dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				List<TrainPersonalAchievement> aList = new ArrayList<TrainPersonalAchievement>();
				for (int j = 0; j < list.size(); j++) {
					if (dateList.get(i).getName().equals(list.get(j).getStrTime())) {
						aList.add(list.get(j));
					}
				}
				dateList.get(i).setAchievementList(aList);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(dateList);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 个人训练成绩详情查询
	@RequestMapping(value = "/train/personal/achievement/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainPersonalAchievementItem(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		List<TrainPhysicalAchievementDetails> list = new ArrayList<TrainPhysicalAchievementDetails>();
		// 个人训练成绩详情查询
		TrainPersonalAchievement item = trainService.trainPersonalAchievementItem(policeId, id, objectId);
		if (item != null) {
			if (objectId == 1) {// 1综合体能2枪械
				// 单项综合体能项目报名详情
				TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, item.getId(),
						policeId);
				if (aItem != null) {
					// 我的参赛记录查询
					list = trainService.trainMyEntryRecordList(item.getId(), aItem.getId(), policeId);
					if (list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							if (list.get(i).getUnitName().equals("分秒")) {
								list.get(i).setUnitName("分");
							} else if (list.get(i).getUnitName().equals("时分秒")) {
								list.get(i).setUnitName("时");
							}
						}
					}
				}
			} else if (objectId == 2) {// 2枪械
				// 枪械报名详情
				TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, item.getId(), policeId);
				if (fItem != null) {
					// 我的参赛记录查询
					TrainPhysicalAchievementDetails sitem = new TrainPhysicalAchievementDetails();
					sitem.setId(0);
					sitem.setTrainPhysicalId(0);
					sitem.setTrainPhysicalAchievementId(0);
					sitem.setPoliceId(fItem.getPoliceId());
					sitem.setProjectId(fItem.getTrainProjectType());
					item.setProjectName(fItem.getProjectName());
					if (fItem.getAchievement() == null) {
						sitem.setIsEntry(1);
					} else {
						sitem.setIsEntry(2);
						sitem.setAchievement(fItem.getAchievement());
						sitem.setAchievementStr(fItem.getAchievementStr());
						sitem.setAchievementGrade(fItem.getAchievementGrade());
					}
					if (fItem.getUnitName().equals("分秒")) {
						fItem.setUnitName("分");
					} else if (fItem.getUnitName().equals("时分秒")) {
						fItem.setUnitName("时");
					}
					sitem.setUnitName(fItem.getUnitName());
					list.add(sitem);
				}
			} else if (objectId == 3) {// 赛事
				String sortStr = null;
				if (item.getSort() == 1) {// 1升序
					sortStr = "asc";
				} else if (item.getSort() == 2) {// 2降序
					sortStr = "desc";
				}
				// 个人赛事排名查询
				TrainRank rankItem = trainMatchService.matchPersonalRankItem(item.getId(), policeId, sortStr);
				if (rankItem != null && rankItem.getRankId() != null) {
					item.setRankId(rankItem.getRankId());
				}
				if (item.getType() == 2) {// 分局赛事
					// 根据policeId查询部门
					User user = userService.policeItem(policeId, null, null);
					// 个人分局赛事排名查询
					TrainRank depRankItem = trainMatchService.matchDepRankItem(item.getId(), user.getDepartmentId());
					if (depRankItem != null && depRankItem.getRankId() != null) {
						item.setDepRankId(depRankItem.getRankId());
					}
				}
			}
		}
		list.removeAll(Collections.singleton(null));
		if (list.size() > 0) {
			item.setProjectList(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人综合体能/枪械训练统计
	@RequestMapping(value = "/train/personal/physical/firearm/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> trainPersonalPhysicalFirearmStatistics(
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "objectType", required = false) Integer objectType,
			@RequestParam(value = "dateType", required = false) Integer dateType,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		if (objectType == null || objectType == 0) {
			objectType = null;
		}
		String startTime = null;
		String endTime = null;
		String tableName = null;
		String fromTableName = null;
		String yearString = null;
		String monthString = null;
		List<CalculationChart> list = new ArrayList<CalculationChart>();
		if (objectId == 1) {
			tableName = "train_physical_achievement";
			fromTableName = "train_physical b on b.id=a.train_physical_id";
		} else if (objectId == 2) {
			tableName = "train_firearm_achievement";
			fromTableName = "train_firearm b on b.id=a.train_firearm_id";
		}
		if (dateType == 1) {// 周
			if (StringUtils.isEmpty(dateTime)) {
				yearString = currentYearStr;
				monthString = monthStr;
			} else {
				yearString = dateTime.substring(0, 4);
				monthString = String.valueOf(Integer.valueOf(dateTime.substring(5, 7)));
			}
			List<Map<String, String>> queryWeekList = DateUtils.queryWeek(yearString, monthString);
			List<String> startList = new ArrayList<String>();
			List<String> endList = new ArrayList<String>();
			for (Map<String, String> m : queryWeekList) {
				for (String k : m.keySet()) {
					Object ob = m.get(k);
					if (k.equals("start")) {
						startList.add(ob.toString());
					}
					if (k.equals("end")) {
						endList.add(ob.toString());
					}
				}
			}
			String srtStartTime1 = startList.get(0);
			String srtEndTime1 = endList.get(0);
			String srtStartTime2 = startList.get(1);
			String srtEndTime2 = endList.get(1);
			String srtStartTime3 = startList.get(2);
			String srtEndTime3 = endList.get(2);
			String srtStartTime4 = startList.get(3);
			String srtEndTime4 = endList.get(3);
			// 个人训练柱状图统计(周)
			int list1 = trainService.trainPersonalWeekChartList(policeId, objectType, srtStartTime1, srtEndTime1,
					tableName, fromTableName);
			CalculationChart chart1 = new CalculationChart();
			chart1.setId(1);
			chart1.setName("第一周");
			chart1.setNum(list1);
			list.add(chart1);
			int list2 = trainService.trainPersonalWeekChartList(policeId, objectType, srtStartTime2, srtEndTime2,
					tableName, fromTableName);
			CalculationChart chart2 = new CalculationChart();
			chart2.setId(2);
			chart2.setName("第二周");
			chart2.setNum(list2);
			list.add(chart2);
			int list3 = trainService.trainPersonalWeekChartList(policeId, objectType, srtStartTime3, srtEndTime3,
					tableName, fromTableName);
			CalculationChart chart3 = new CalculationChart();
			chart3.setId(3);
			chart3.setName("第三周");
			chart3.setNum(list3);
			list.add(chart3);
			int list4 = trainService.trainPersonalWeekChartList(policeId, objectType, srtStartTime4, srtEndTime4,
					tableName, fromTableName);
			CalculationChart chart4 = new CalculationChart();
			chart4.setId(4);
			chart4.setName("第四周");
			chart4.setNum(list4);
			list.add(chart4);
			startTime = srtStartTime1;
			endTime = srtEndTime4;
		} else if (dateType == 2) {// 月
			if (StringUtils.isEmpty(dateTime)) {
				yearString = currentYearStr;
			} else {
				yearString = dateTime.substring(0, 4);
			}
			startTime = yearString + "-01-01";
			endTime = yearString + "-12-31";
			// 个人训练柱状图统计(月)
			list = trainService.trainPersonalMonthChartList(policeId, objectType, startTime, endTime, tableName,
					fromTableName);
		} else if (dateType == 3) {// 年
			// 个人训练柱状图统计(总)
			list = trainService.trainPersonalTotalChartList(policeId, objectType, startTime, endTime, tableName,
					fromTableName);
		}
		TrainChartStatistics item = new TrainChartStatistics();
		if (objectId == 1) {// 1综合体能
			// 个人综合体能训练统计
			item = trainService.trainPersonalPhysicalStatisticsItem(policeId, objectType, startTime, endTime);
		} else if (objectId == 2) {// 2枪械
			// 个人枪械训练统计
			item = trainService.trainPersonalFirearmStatisticsItem(policeId, objectType, startTime, endTime);
		}
		item.setChartList(list);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人赛事成绩列表查询
	@RequestMapping(value = "/match/personal/achievement/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchPersonalAchievementList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		String sortStr = null;
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		if (!StringUtils.isEmpty(dateTime)) {
			dateTime = dateTime.substring(0, 7);
		}
		// 个人赛事成绩时间查询
		List<TrainTimeName> dateList = trainService.matchTimeNameList(policeId, type, dateTime, pageSize, pageNum);
		// 个人赛事成绩列表查询
		List<TrainPersonalAchievement> list = trainService.matchPersonalAchievementList(policeId, type, dateTime,
				pageSize, pageNum);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getType() == 1) {
					if (list.get(i).getSort() == 1) {// 1升序
						sortStr = "asc";
					} else if (list.get(i).getSort() == 2) {// 2降序
						sortStr = "desc";
					}
					// 个人赛事排名查询
					TrainRank rankItem = trainMatchService.matchPersonalRankItem(list.get(i).getId(), policeId,
							sortStr);
					if (rankItem != null && rankItem.getRankId() != null) {
						list.get(i).setRankId(rankItem.getRankId());
					}
				} else if (list.get(i).getType() == 2) {
					// 根据policeId查询部门
					User user = userService.policeItem(policeId, null, null);
					// 个人分局赛事排名查询
					TrainRank depRankItem = trainMatchService.matchDepRankItem(list.get(i).getId(),
							user.getDepartmentId());
					if (depRankItem != null && depRankItem.getRankId() != null) {
						list.get(i).setRankId(depRankItem.getRankId());
					}
				}
			}
		}
		// 个人赛事成绩列表总数查询
		int total = trainService.matchPersonalAchievementCount(policeId, type, dateTime);
		if (dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				List<TrainPersonalAchievement> aList = new ArrayList<TrainPersonalAchievement>();
				for (int j = 0; j < list.size(); j++) {
					if (dateList.get(i).getName().equals(list.get(j).getStrTime())) {
						aList.add(list.get(j));
					}
				}
				dateList.get(i).setAchievementList(aList);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(dateList);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 个人赛事类型查询
	@RequestMapping(value = "/match/personal/type/list", method = RequestMethod.GET)
	public ResponseEntity<?> matchPersonalAchievementList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<TrainMatchType> totalList = new ArrayList<TrainMatchType>();
		TrainMatchType item = new TrainMatchType();
		item.setId(0);
		item.setName("全部");
		totalList.add(item);
		List<TrainMatchType> list = trainMatchService.matchPersonalTypeList(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		if (list.size() > 0) {
			totalList.addAll(list);
			dlr.setResult(totalList);
		} else {
			dlr.setResult(list);
		}
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人赛事统计
	@RequestMapping(value = "/match/personal/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> matchPersonalStatistics(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "matchTypeId", required = false) Integer matchTypeId,
			@RequestParam(value = "objectType", required = false) Integer objectType,
			@RequestParam(value = "dateType", required = false) Integer dateType,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		String startTime = null;
		String endTime = null;
		String yearString = null;
		String monthString = null;
		List<CalculationChart> list = new ArrayList<CalculationChart>();
		if (matchTypeId == null || matchTypeId == 0) {
			matchTypeId = null;
		}
		if (dateType == 1) {// 周
			if (StringUtils.isEmpty(dateTime)) {
				yearString = currentYearStr;
				monthString = monthStr;
			} else {
				yearString = dateTime.substring(0, 4);
				monthString = String.valueOf(Integer.valueOf(dateTime.substring(5, 7)));
			}
			List<Map<String, String>> queryWeekList = DateUtils.queryWeek(yearString, monthString);
			List<String> startList = new ArrayList<String>();
			List<String> endList = new ArrayList<String>();
			for (Map<String, String> m : queryWeekList) {
				for (String k : m.keySet()) {
					Object ob = m.get(k);
					if (k.equals("start")) {
						startList.add(ob.toString());
					}
					if (k.equals("end")) {
						endList.add(ob.toString());
					}
				}
			}
			String srtStartTime1 = startList.get(0);
			String srtEndTime1 = endList.get(0);
			String srtStartTime2 = startList.get(1);
			String srtEndTime2 = endList.get(1);
			String srtStartTime3 = startList.get(2);
			String srtEndTime3 = endList.get(2);
			String srtStartTime4 = startList.get(3);
			String srtEndTime4 = endList.get(3);
			// 个人赛事柱状图统计(周)
			int list1 = trainService.matchPersonalWeekChartList(policeId, objectType, matchTypeId, srtStartTime1,
					srtEndTime1);
			CalculationChart chart1 = new CalculationChart();
			chart1.setId(1);
			chart1.setName("第一周");
			chart1.setNum(list1);
			list.add(chart1);
			int list2 = trainService.matchPersonalWeekChartList(policeId, objectType, matchTypeId, srtStartTime2,
					srtEndTime2);
			CalculationChart chart2 = new CalculationChart();
			chart2.setId(2);
			chart2.setName("第二周");
			chart2.setNum(list2);
			list.add(chart2);
			int list3 = trainService.matchPersonalWeekChartList(policeId, objectType, matchTypeId, srtStartTime3,
					srtEndTime3);
			CalculationChart chart3 = new CalculationChart();
			chart3.setId(3);
			chart3.setName("第三周");
			chart3.setNum(list3);
			list.add(chart3);
			int list4 = trainService.matchPersonalWeekChartList(policeId, objectType, matchTypeId, srtStartTime4,
					srtEndTime4);
			CalculationChart chart4 = new CalculationChart();
			chart4.setId(4);
			chart4.setName("第四周");
			chart4.setNum(list4);
			list.add(chart4);
			startTime = srtStartTime1;
			endTime = srtEndTime4;
		} else if (dateType == 2) {// 月
			if (StringUtils.isEmpty(dateTime)) {
				yearString = currentYearStr;
			} else {
				yearString = dateTime.substring(0, 4);
			}
			startTime = yearString + "-01-01";
			endTime = yearString + "-12-31";
			// 个人赛事柱状图统计(月)
			list = trainService.matchPersonalMonthChartList(policeId, objectType, matchTypeId, startTime, endTime);
		} else if (dateType == 3) {// 年
			// 个人赛事柱状图统计(总)
			list = trainService.matchPersonalTotalChartList(policeId, objectType, matchTypeId, null, null);
		}
		// 个人赛事统计
		TrainMtachChartStatistics item = new TrainMtachChartStatistics();
		// 个人已经参加单位赛事查询
		List<TrainMatch> list1 = trainMatchService.matchPersonalAlreadyEnterList(policeId, 1, matchTypeId, startTime,
				endTime);
		String sortStr = null;
		int depFirstNum = 0;
		int depSecondNum = 0;
		int depThirdNum = 0;
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				if (list1.get(i).getSort() == 1) {// 1升序
					sortStr = "asc";
				} else if (list1.get(i).getSort() == 2) {// 2降序
					sortStr = "desc";
				}
				// 个人赛事排名查询
				TrainRank rankItem = trainMatchService.matchPersonalRankItem(list1.get(i).getId(), policeId, sortStr);
				if (rankItem != null && rankItem.getRankId() != null) {
					if (rankItem.getRankId() == 1) {
						depFirstNum = depFirstNum + 1;
					} else if (rankItem.getRankId() == 2) {
						depSecondNum = depSecondNum + 1;
					} else if (rankItem.getRankId() == 3) {
						depThirdNum = depThirdNum + 1;
					}
				}
			}
		}
		int officeFirstNum = 0;
		int officeSecondNum = 0;
		int officeThirdNum = 0;
		// 个人已经参加分局赛事查询
		List<TrainMatch> list2 = trainMatchService.matchPersonalAlreadyEnterList(policeId, 2, matchTypeId, null, null);
		if (list2.size() > 0) {
			// 根据policeId查询部门
			User user = userService.policeItem(policeId, null, null);
			for (int i = 0; i < list2.size(); i++) {
				// 个人分局赛事排名查询
				TrainRank depRankItem = trainMatchService.matchDepRankItem(list2.get(i).getId(),
						user.getDepartmentId());
				if (depRankItem != null && depRankItem.getRankId() != null) {
					if (depRankItem.getRankId() == 1) {
						officeFirstNum = officeFirstNum + 1;
					} else if (depRankItem.getRankId() == 2) {
						officeSecondNum = officeSecondNum + 1;
					} else if (depRankItem.getRankId() == 3) {
						officeThirdNum = officeThirdNum + 1;
					}
				}
			}
		}
		if (objectType == null || objectType == 0) {
			item.setFirstNum(depFirstNum + officeFirstNum);
			item.setSecondNum(depSecondNum + officeSecondNum);
			item.setThirdNum(depThirdNum + officeThirdNum);
		} else if (objectType == 1) {
			item.setFirstNum(depFirstNum);
			item.setSecondNum(depSecondNum);
			item.setThirdNum(depThirdNum);
		} else if (objectType == 2) {
			item.setFirstNum(officeFirstNum);
			item.setSecondNum(officeSecondNum);
			item.setThirdNum(officeThirdNum);
		}
		int totalNum = 0;
		for (int i = 0; i < list.size(); i++) {
			totalNum = totalNum + list.get(i).getNum();
		}
		item.setTotalNum(totalNum);
		item.setChartList(list);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人训练成绩综合查询
	@RequestMapping(value = "/train/personal/result/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainPersonalResultItem(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人训练成绩综合查询查询
		TrainPersonalResult item = trainService.trainPersonalResultItem(policeId);
		// 个人分局各训练类型所占比
		List<CalculationChart> officeList = trainService.trainPersonalResultOfficeList(policeId);
		if (officeList.size() > 0) {
			int office = 0;
			item.setOfficeList(officeList);
			for (int i = 0; i < officeList.size(); i++) {
				office = officeList.get(i).getNum() + office;
			}
			item.setOfficeNum(office);
		}
		// 个人单位各训练类型所占比
		List<CalculationChart> depList = trainService.trainPersonalResultDepList(policeId);
		item.setDepList(depList);
		if (depList.size() > 0) {
			int dep = 0;
			item.setDepList(depList);
			for (int i = 0; i < depList.size(); i++) {
				dep = depList.get(i).getNum() + dep;
			}
			item.setDepNum(dep);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人赛事成绩综合查询
	@RequestMapping(value = "/match/personal/result/item", method = RequestMethod.GET)
	public ResponseEntity<?> matchPersonalResultItem(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人赛事成绩综合查询
		TrainMatchPersonalResult item = trainMatchService.matchPersonalResultItem(policeId);
		// 个人已经参加单位赛事查询
		List<TrainMatch> list1 = trainMatchService.matchPersonalAlreadyEnterList(policeId, 1, null, null, null);
		String sortStr = null;
		int depFirstNum = 0;
		int depSecondNum = 0;
		int depThirdNum = 0;
		int depOtherNum = 0;
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				if (list1.get(i).getSort() == 1) {// 1升序
					sortStr = "asc";
				} else if (list1.get(i).getSort() == 2) {// 2降序
					sortStr = "desc";
				}
				// 个人赛事排名查询
				TrainRank rankItem = trainMatchService.matchPersonalRankItem(list1.get(i).getId(), policeId, sortStr);
				if (rankItem != null && rankItem.getRankId() != null) {
					if (rankItem.getRankId() == 1) {
						depFirstNum = depFirstNum + 1;
					} else if (rankItem.getRankId() == 2) {
						depSecondNum = depSecondNum + 1;
					} else if (rankItem.getRankId() == 3) {
						depThirdNum = depThirdNum + 1;
					}
				}
			}
		}
		item.setDepFirstNum(depFirstNum);
		item.setDepSecondNum(depSecondNum);
		item.setDepThirdNum(depThirdNum);
		if (item.getDepTotalNum() != 0) {
			depOtherNum = item.getDepTotalNum() - depFirstNum - depSecondNum - depThirdNum;
			item.setDepOtherNum(depOtherNum);
			double depTotal = item.getDepTotalNum();
			double firstNum = depFirstNum * 100;
			double secondNum = depSecondNum * 100;
			double thirdNum = depThirdNum * 100;
			double otherNum = depOtherNum * 100;
			item.setDepFirstRate(Double.valueOf(String.format("%.2f", firstNum / depTotal)));
			item.setDepSecondRate(Double.valueOf(String.format("%.2f", secondNum / depTotal)));
			item.setDepThirdRate(Double.valueOf(String.format("%.2f", thirdNum / depTotal)));
			item.setDepOtherRate(Double.valueOf(String.format("%.2f", otherNum / depTotal)));
		}
		int officeFirstNum = 0;
		int officeSecondNum = 0;
		int officeThirdNum = 0;
		int officeOtherNum = 0;
		// 个人已经参加分局赛事查询
		List<TrainMatch> list2 = trainMatchService.matchPersonalAlreadyEnterList(policeId, 2, null, null, null);
		if (list2.size() > 0) {
			// 根据policeId查询部门
			User user = userService.policeItem(policeId, null, null);
			for (int i = 0; i < list2.size(); i++) {
				// 个人分局赛事排名查询
				TrainRank depRankItem = trainMatchService.matchDepRankItem(list2.get(i).getId(),
						user.getDepartmentId());
				if (depRankItem != null && depRankItem.getRankId() != null) {
					if (depRankItem.getRankId() == 1) {
						officeFirstNum = officeFirstNum + 1;
					} else if (depRankItem.getRankId() == 2) {
						officeSecondNum = officeSecondNum + 1;
					} else if (depRankItem.getRankId() == 3) {
						officeThirdNum = officeThirdNum + 1;
					}
				}
			}
		}
		item.setOfficeFirstNum(officeFirstNum);
		item.setOfficeSecondNum(officeSecondNum);
		item.setOfficeThirdNum(officeThirdNum);
		if (item.getOfficeTotalNum() != 0) {
			officeOtherNum = item.getOfficeTotalNum() - officeFirstNum - officeSecondNum - officeThirdNum;
			item.setOfficeOtherNum(officeOtherNum);
			double officeTotal = item.getOfficeTotalNum();
			double oFirstNum = officeFirstNum * 100;
			double oSecondNum = officeSecondNum * 100;
			double oThirdNum = officeThirdNum * 100;
			double oOtherNum = officeOtherNum * 100;
			item.setOfficeFirstRate(Double.valueOf(String.format("%.2f", oFirstNum / officeTotal)));
			item.setOfficeSecondRate(Double.valueOf(String.format("%.2f", oSecondNum / officeTotal)));
			item.setOfficeThirdRate(Double.valueOf(String.format("%.2f", oThirdNum / officeTotal)));
			item.setOfficeOtherRate(Double.valueOf(String.format("%.2f", oOtherNum / officeTotal)));
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 成绩起始时间查询
	@RequestMapping(value = "/train/match/time/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainMatchTimeItem(@RequestParam(value = "objectId", required = false) Integer objectId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String tableName = null;
		if (objectId == 1) {
			tableName = "train_physical_achievement";
		} else if (objectId == 2) {
			tableName = "train_firearm_achievement";
		} else if (objectId == 3) {
			tableName = "train_match_achievement";
		}
		// 成绩起始时间查询
		ReportDataFillTime item = trainService.trainMatchTimeItem(tableName);
		if (item != null) {
			item.setCurrentYear(Integer.valueOf(currentYearStr));
			item.setCurrentMonth(Integer.valueOf(monthStr));
			item.setStartYear(Integer.valueOf(item.getStartTime().substring(0, 4)));
			item.setStartMonth(Integer.valueOf(item.getStartTime().substring(5, 7)));
			item.setEndYear(Integer.valueOf(item.getEndTime().substring(0, 4)));
			item.setEndMonth(Integer.valueOf(item.getEndTime().substring(5, 7)));
		} else {
			item = new ReportDataFillTime();
			item.setCurrentYear(Integer.valueOf(currentYearStr));
			item.setCurrentMonth(Integer.valueOf(monthStr));
			item.setStartYear(Integer.valueOf(currentYearStr));
			item.setStartMonth(Integer.valueOf(monthStr));
			item.setEndYear(Integer.valueOf(currentYearStr));
			item.setEndMonth(Integer.valueOf(monthStr));
		}

		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 查询赛事类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/get_match_type", method = RequestMethod.POST)
	public ResponseEntity<?> matchTypeList(@Param("keyword") String keyword, @Param("pageSize") Integer pageSize) {

		pageSize = pageSize == null ? 1 : pageSize;

		List<TrainMatchType> trainMatchTypeList = trainMatchService.getTrainMatchTypeList(keyword, (pageSize - 1) * 10);

		int count = trainMatchService.getTrainMatchTypeCount(keyword);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainMatchTypeList", trainMatchTypeList);
		map.put("count", count);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(map);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 新增赛事类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/add_match_type", method = RequestMethod.POST)
	public ResponseEntity<?> matchTypeAdd(@Param("name") String name) {

		TrainMatchType trainMatchType = new TrainMatchType();
		trainMatchType.setName(name);

		// trainMatchType.setSort(sort); @Param("sort") Integer sort

		trainMatchType.setCreationDate(new Date());

		int count = trainMatchService.addTrainMatchType(trainMatchType);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(count);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 编辑赛事类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/update_match_type", method = RequestMethod.POST)
	public ResponseEntity<?> matchTypeUpdate(@Param("id") Integer id, @Param("name") String name,
			@Param("sort") Integer sort) {

		TrainMatchType trainMatchType = new TrainMatchType();
		trainMatchType.setId(id);
		trainMatchType.setName(name);
		trainMatchType.setSort(sort);
		trainMatchType.setUpdateDate(new Date());

		int count = trainMatchService.updateTrainMatchType(trainMatchType);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(count);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 删除赛事类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/delete_match_type", method = RequestMethod.POST)
	public ResponseEntity<?> matchTypeDelete(@Param("id") Integer id) {

		List<TrainMatchProject> trainMatchProjectByType = trainMatchService.getTrainMatchProjectByType(id);

		DataListReturn dlr = new DataListReturn();

		if (trainMatchProjectByType.size() > 0) {

			dlr.setStatus(true);
			dlr.setMessage("fail");
			dlr.setResult("该赛事类型已被引用，无法删除！");
			dlr.setCode(StatusCode.getSuccesscode());
			return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);

		}

		int count = trainMatchService.deleteTrainMatchType(id);

		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(count);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 赛事类型详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/details_match_type", method = RequestMethod.POST)
	public ResponseEntity<?> matchTypeDetails(@Param("id") Integer id) {

		TrainMatchType trainMatchType = trainMatchService.selectTrainMatchTypeById(id);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(trainMatchType);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 查询比赛项目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/get_match_project", method = RequestMethod.POST)
	public ResponseEntity<?> matchProjectList(@Param("keyword") String keyword, @Param("pageSize") Integer pageSize) {

		pageSize = pageSize == null ? 1 : pageSize;

		List<TrainMatchProject> trainMatchProjectList = trainMatchService.getTrainMatchProjectList(keyword,
				(pageSize - 1) * 10);

		int count = trainMatchService.getTrainMatchProjectCount(keyword);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainMatchProjectList", trainMatchProjectList);
		map.put("count", count);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(map);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 新增比赛项目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/add_match_project", method = RequestMethod.POST)
	public ResponseEntity<?> matchProjectAdd(@Param("name") String name, @Param("type") Integer type,
			@Param("nature") Integer nature, @Param("unitId") Integer unitId, @Param("sort") Integer sort,
			@Param("gender") Integer gender) {
		TrainMatchProject trainMatchProject = new TrainMatchProject();
		trainMatchProject.setName(name);
		trainMatchProject.setType(type);
		trainMatchProject.setNature(nature);
		trainMatchProject.setSort(sort);
		trainMatchProject.setUnitId(unitId);
		trainMatchProject.setGender(gender);
		trainMatchProject.setIsMoreUnit(unitId == 8 || unitId == 9 ? 1 : 0);
		trainMatchProject.setCreationDate(new Date());

		int count = trainMatchService.addTrainMatchProject(trainMatchProject);

		// 修改项目类型表中的排序方式
//		TrainMatchType trainMatchType = new TrainMatchType();
//		trainMatchType.setId(type);
//		trainMatchType.setSort(sort);
//		trainMatchService.updateTrainMatchType(trainMatchType);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(count);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 修改比赛项目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/update_match_project", method = RequestMethod.POST)

	public ResponseEntity<?> matchProjectUpdate(@Param("id") Integer id, @Param("name") String name,
			@Param("type") Integer type, @Param("nature") Integer nature, @Param("unitId") Integer unitId,
			@Param("gender") Integer gender) {
		TrainMatchProject trainMatchProject = new TrainMatchProject();
		trainMatchProject.setId(id);
		trainMatchProject.setName(name);
		trainMatchProject.setNature(nature);
		trainMatchProject.setUnitId(unitId);
		trainMatchProject.setType(type);
		trainMatchProject.setGender(gender);
		trainMatchProject.setIsMoreUnit(unitId == 8 || unitId == 9 ? 1 : 0);
		trainMatchProject.setCreationDate(new Date());
		
		int count = trainMatchService.updateTrainMatchProject(trainMatchProject);
		
		//修改赛事性质
		//查询引用了该比赛项目的赛事
		List<TrainMatch> trainProjectList = trainMatchService.getTrainMatchByTrainProjectId(id);
		for (TrainMatch t : trainProjectList) {
			
			TrainMatch trainMatch = new TrainMatch();
			trainMatch.setNature(nature);
			trainMatch.setId(t.getId());
			trainMatchService.trainMatchUpdate(trainMatch);
			
		}
		
		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(count);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 删除比赛项目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/delete_match_project", method = RequestMethod.POST)
	public ResponseEntity<?> matchProjectDelete(@Param("id") Integer id) {

		List<TrainMatch> trainMatchByMatchProjectId = trainMatchService.getTrainMatchByMatchProjectId(id);

		DataListReturn dlr = new DataListReturn();

		if (trainMatchByMatchProjectId.size() > 0) {

			dlr.setStatus(true);
			dlr.setMessage("fail");
			dlr.setResult("该项目已被引用，无法删除！");
			dlr.setCode(StatusCode.getSuccesscode());
			return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);

		}

		int count = trainMatchService.deleteTrainMatchProject(id);

		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(count);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 比赛项目详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/details_match_project", method = RequestMethod.POST)
	public ResponseEntity<?> matchProjectDetails(@Param("id") Integer id) {

		TrainMatchProject trainMatchProject = trainMatchService.selectTrainMatchProjectById(id);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(trainMatchProject);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 比赛项目立即开始
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/start_match", method = RequestMethod.POST)
	public ResponseEntity<?> matchStart(@Param("id") Integer id) {

		int count = trainMatchService.startTrainMatch(id);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(count);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 比赛项目立即结束
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/end_match", method = RequestMethod.POST)
	public ResponseEntity<?> matchEnd(@Param("id") Integer id) {

		int count = trainMatchService.endTrainMatch(id);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(count);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 比赛项目立即重启
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/restart_match", method = RequestMethod.POST)
	public ResponseEntity<?> matchRestart(@Param("id") Integer id, @Param("id") String time) {

		int count = trainMatchService.reStartTrainMatch(id, time);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(count);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 比赛项目删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/delete_match", method = RequestMethod.POST)
	public ResponseEntity<?> matchDelete(@Param("id") Integer id) {

		int count = trainMatchService.deleteTrainMatch(id);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(count);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 根据赛事类型type查询该类型下的项目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/type_match_project", method = RequestMethod.POST)
	public ResponseEntity<?> matchProjectType(@Param("type") Integer type) {

		List<TrainMatchProject> projectByType = trainMatchService.getTrainMatchProjectByType(type);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(projectByType);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 新增赛事
	 * 
	 * @param name
	 * @param place
	 * @param type
	 * @param matchTypeId
	 * @param departmentId
	 * @param registrationStartDate
	 * @param registrationEndDate
	 * @param trainStartDate
	 * @param trainEndDate
	 * @param scorerPoliceId
	 * @param limitPeopleNum
	 * @param maxPeopleNum
	 * @param matchContent
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/match/add_match", method = RequestMethod.POST)
	public ResponseEntity<?> addTrainPhysical(@Param("name") String name, @Param("place") String place,
			@Param("type") Integer type, @Param("matchTypeId") Integer matchTypeId,
			@Param("matchProjectId") Integer matchProjectId, @Param("departmentId") Integer departmentId,
			@Param("registrationStartDate") String registrationStartDate, @Param("templateImg") String templateImg,
			@Param("registrationEndDate") String registrationEndDate, @Param("matchStartDate") String matchStartDate,
			@Param("matchEndDate") String matchEndDate, @Param("scorer") String scorer,
			@Param("limitPeopleNum") Integer limitPeopleNum, @Param("maxPeopleNum") Integer maxPeopleNum,
			@Param("matchContent") String matchContent, @Param("totalMaxNum") Integer totalMaxNum,
			@Param("manMaxNum") Integer manMaxNum, @Param("womanMaxNum") Integer womanMaxNum,
			HttpServletRequest request) {

		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile coverImgFile = multipart.getFile("coverImg");
		MultipartFile trainImgFile = multipart.getFile("trainImg");

		String coverImgFileName = null;
		String trainImgFileName = null;

		try {

			if (null != coverImgFile && !"".equals(coverImgFile.getOriginalFilename())) {

				// 存储文件
				byte[] bytes = coverImgFile.getBytes();
				// 生成唯一标识 UUID
				String uuid = UUID.randomUUID().toString();
				// 文件名
				String fileName = coverImgFile.getOriginalFilename().substring(0,
						coverImgFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// 文件存储路径
				String url = (root + coverImg);
				// 文件后缀名
				String suffix = coverImgFile.getOriginalFilename()
						.substring(coverImgFile.getOriginalFilename().lastIndexOf("."));
				// 加上后缀的文件名
				coverImgFileName = fileName + suffix;
				Path path = Paths.get(url, coverImgFileName);
				Files.write(path, bytes);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			if (null != trainImgFile && !"".equals(trainImgFile.getOriginalFilename())) {

				// 存储文件
				byte[] bytes2 = trainImgFile.getBytes();
				// 生成唯一标识 UUID
				String uuid2 = UUID.randomUUID().toString();
				// 文件名
				String fileName2 = trainImgFile.getOriginalFilename().substring(0,
						trainImgFile.getOriginalFilename().lastIndexOf(".")) + uuid2;
				// 文件存储路径
				String url2 = (root + contentImg);
				// 文件后缀名
				String suffix2 = trainImgFile.getOriginalFilename()
						.substring(trainImgFile.getOriginalFilename().lastIndexOf("."));
				// 加上后缀的文件名
				trainImgFileName = fileName2 + suffix2;
				Path path2 = Paths.get(url2, trainImgFileName);
				Files.write(path2, bytes2);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		TrainMatch trainMatch = new TrainMatch();
		trainMatch.setName(name);
		trainMatch.setType(type);
		if (type == 1) {
			trainMatch.setDepartmentId(departmentId);
		}
		trainMatch.setMatchTypeId(matchTypeId);
		trainMatch.setMatchProjectId(matchProjectId);

		// 根据项目查询赛事性质
		TrainMatchProject trainMatchProjectById = trainMatchService.selectTrainMatchProjectById(matchProjectId);
		trainMatch.setNature(trainMatchProjectById.getNature());

		trainMatch.setPlace(place);
		trainMatch.setRegistrationStartDate(DateUtil.parseDateTime(registrationStartDate + ":00"));
		trainMatch.setRegistrationEndDate(DateUtil.parseDateTime(registrationEndDate + ":00"));
		trainMatch.setMatchStartDate(DateUtil.parseDateTime(matchStartDate + ":00"));
		trainMatch.setMatchEndDate(DateUtil.parseDateTime(matchEndDate + ":00"));
		trainMatch.setSignUpStatus(1);
		trainMatch.setStatus(1);
		trainMatch.setScorerPoliceId(scorer);
		trainMatch.setLimitPeopleNum(limitPeopleNum);
		trainMatch.setIsSubmit(0);
		trainMatch.setMaxPeopleNum(maxPeopleNum);
		if (null != templateImg && !"".equals(templateImg)) {
			trainMatch.setCoverImg(templateImg);
		} else {
			if (null != coverImgFileName) {
				trainMatch.setCoverImg(coverImg + coverImgFileName);
			}
		}
		trainMatch.setMatchContent(matchContent);
		if (null != trainImgFileName) {
			trainMatch.setTrainImg(contentImg + trainImgFileName);
		}
		trainMatch.setTotalMaxNum(totalMaxNum);
		trainMatch.setManMaxNum(null == manMaxNum ? 0 : manMaxNum);
		trainMatch.setWomanMaxNum(null == womanMaxNum ? 0 : womanMaxNum);
		trainMatch.setCreationDate(new Date());

		// 存储到数据库
		int count = trainMatchService.trainMatchCreat(trainMatch);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 修改赛事
	 * 
	 * @param id
	 * @param name
	 * @param place
	 * @param type
	 * @param matchTypeId
	 * @param matchProjectId
	 * @param departmentId
	 * @param registrationStartDate
	 * @param registrationEndDate
	 * @param matchStartDate
	 * @param matchEndDate
	 * @param scorer
	 * @param limitPeopleNum
	 * @param maxPeopleNum
	 * @param matchContent
	 * @param totalMaxNum
	 * @param manMaxNum
	 * @param womanMaxNum
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/match/update_match", method = RequestMethod.POST)
	public ResponseEntity<?> updateTrainMatch(@Param("id") Integer id, @Param("name") String name,
			@Param("place") String place, @Param("type") Integer type, @Param("matchTypeId") Integer matchTypeId,
			@Param("matchProjectId") Integer matchProjectId, @Param("nature") Integer nature,
			@Param("departmentId") Integer departmentId, @Param("registrationStartDate") String registrationStartDate,
			@Param("registrationEndDate") String registrationEndDate, @Param("matchStartDate") String matchStartDate,
			@Param("matchEndDate") String matchEndDate, @Param("scorer") String scorer,
			@Param("limitPeopleNum") Integer limitPeopleNum, @Param("maxPeopleNum") Integer maxPeopleNum,
			@Param("matchContent") String matchContent, @Param("totalMaxNum") Integer totalMaxNum,
			@Param("manMaxNum") Integer manMaxNum, @Param("womanMaxNum") Integer womanMaxNum,
			@Param("templateImg") String templateImg, HttpServletRequest request) {
		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile coverImgFile = multipart.getFile("coverImg");
		MultipartFile trainImgFile = multipart.getFile("trainImg");
		String coverImgFileName = null;
		String trainImgFileName = null;

		try {

			if (null != coverImgFile && !"".equals(coverImgFile.getOriginalFilename())) {

				// 存储文件
				byte[] bytes = coverImgFile.getBytes();
				// 生成唯一标识 UUID
				String uuid = UUID.randomUUID().toString();
				// 文件名
				String fileName = coverImgFile.getOriginalFilename().substring(0,
						coverImgFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// 文件存储路径
				String url = (root + coverImg);
				// 文件后缀名
				String suffix = coverImgFile.getOriginalFilename()
						.substring(coverImgFile.getOriginalFilename().lastIndexOf("."));
				// 加上后缀的文件名
				coverImgFileName = fileName + suffix;
				Path path = Paths.get(url, coverImgFileName);
				Files.write(path, bytes);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			if (null != trainImgFile && !"".equals(trainImgFile.getOriginalFilename())) {

				// 存储文件
				byte[] bytes2 = trainImgFile.getBytes();
				// 生成唯一标识 UUID
				String uuid2 = UUID.randomUUID().toString();
				// 文件名
				String fileName2 = trainImgFile.getOriginalFilename().substring(0,
						trainImgFile.getOriginalFilename().lastIndexOf(".")) + uuid2;
				// 文件存储路径
				String url2 = (root + contentImg);
				// 文件后缀名
				String suffix2 = trainImgFile.getOriginalFilename()
						.substring(trainImgFile.getOriginalFilename().lastIndexOf("."));
				// 加上后缀的文件名
				trainImgFileName = fileName2 + suffix2;
				Path path2 = Paths.get(url2, trainImgFileName);
				Files.write(path2, bytes2);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		TrainMatch trainMatch = new TrainMatch();
		trainMatch.setId(id);
		trainMatch.setName(name);
		trainMatch.setType(type);
		trainMatch.setMatchTypeId(matchTypeId);
		trainMatch.setMatchProjectId(matchProjectId);
		trainMatch.setNature(nature);
		trainMatch.setPlace(place);
		trainMatch.setRegistrationStartDate(DateUtil.parseDateTime(registrationStartDate + ":00"));
		trainMatch.setRegistrationEndDate(DateUtil.parseDateTime(registrationEndDate + ":00"));
		trainMatch.setMatchStartDate(DateUtil.parseDateTime(matchStartDate + ":00"));
		trainMatch.setMatchEndDate(DateUtil.parseDateTime(matchEndDate + ":00"));
		trainMatch.setScorerPoliceId(scorer);
		trainMatch.setLimitPeopleNum(limitPeopleNum);
		trainMatch.setMaxPeopleNum(limitPeopleNum.equals(1) ? null : maxPeopleNum);

		if (null != templateImg && !"".equals(templateImg)) {
			trainMatch.setCoverImg(templateImg);
		} else {
			if (null != coverImgFileName) {
				trainMatch.setCoverImg(coverImg + coverImgFileName);
			}
		}
		trainMatch.setMatchContent(matchContent);
		if (null != trainImgFileName) {
			trainMatch.setTrainImg(contentImg + trainImgFileName);
		}
		trainMatch.setTotalMaxNum(totalMaxNum);
		trainMatch.setManMaxNum(manMaxNum);
		trainMatch.setWomanMaxNum(womanMaxNum);
		trainMatch.setUpdateDate(new Date());

		// 修改
		int count = trainMatchService.trainMatchUpdateSpecial(trainMatch);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获得赛事成绩列表
	 * 
	 * @param trainMatchId
	 * @param keyword
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/match/get_match_achievement", method = RequestMethod.POST)
	public ResponseEntity<?> matchTrainMatchAchievement(@Param("trainMatchId") Integer trainMatchId,
			@Param("departmentId") Integer departmentId, @Param("keyword") String keyword,
			@Param("pageSize") Integer pageSize) {

		List<TrainMatchAchievement> trainMatchAchievementList = trainMatchService
				.getTrainMatchAchievementList(trainMatchId, departmentId, keyword, (pageSize - 1) * 10);

		int count = trainMatchService.getTrainMatchAchievementCount(trainMatchId, departmentId, keyword);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainMatchAchievementList", trainMatchAchievementList);
		map.put("count", count);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(map);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 获得赛事详情
	 * 
	 * @param trainMatchId
	 * @param keyword
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/match/get_match_achievement_only", method = RequestMethod.POST)
	public ResponseEntity<?> matchTrainMatchAchievement(@Param("id") Integer id) {

		TrainMatchAchievement matchAchievementItem = trainMatchService.getTrainMatchAchievementById(id);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(matchAchievementItem);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 修改赛事成绩列表
	 * 
	 * @param id
	 * @param achievement
	 * @return
	 */
	@RequestMapping(value = "/match/update_match_achievement", method = RequestMethod.POST)
	public ResponseEntity<?> updateMatchTrainMatchAchievement(@Param("id") Integer id,
			@Param("achievement") Double achievement, @Param("achievementFirst") Integer achievementFirst,
			@Param("achievementSecond") Double achievementSecond) {

		TrainMatchAchievement trainMatchAchievement = new TrainMatchAchievement();
		trainMatchAchievement.setId(id);
		trainMatchAchievement.setAchievement(achievement);

		Integer min = null;
		Double sec = null;

		if (null != achievementFirst) {
			// 分
			min = achievementFirst;
			trainMatchAchievement.setAchievementFirst(min);
		}
		if (null != achievementSecond) {
			// 秒
			sec = achievementSecond;
			trainMatchAchievement.setAchievementSecond(sec);
		}

		// 分秒
		if (null != min) {
			trainMatchAchievement.setAchievementStr(min + "分");
			trainMatchAchievement.setAchievement(min.doubleValue());
			if (null != sec) {
				trainMatchAchievement.setAchievementStr(min + "分" + sec + "秒");
				// 秒转分+分
				String secMinStr = new DecimalFormat("#.00").format(sec / 60);
				trainMatchAchievement.setAchievement(min + Double.parseDouble(secMinStr));
			}
		} else {
			// 非分秒
			// 查询该条成绩的项目的单位
			String unitName = trainMatchService.getUnitNameByAchievementId(id);
			trainMatchAchievement.setAchievementStr(achievement + unitName);
			trainMatchAchievement.setAchievement(achievement);
		}

		trainMatchAchievement.setUpdateDate(new Date());
		trainMatchAchievement.setAchievementDate(new Date());

		int count = trainMatchService.matchAchievementUpdate(trainMatchAchievement);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(count);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 赛事详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/details_match", method = RequestMethod.POST)
	public ResponseEntity<?> matchDetails(@Param("id") Integer id) {

		TrainMatch trainMatch = trainMatchService.getTrainMatchById(id);

		DataListReturn dlr = new DataListReturn();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(trainMatch);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 获取当前日期当前周,当前月,当前季度,当前半年,当前年的时间段
	private ReportDataFillTime fillDate(String dataTime, Integer fillId) throws ParseException {
		ReportDataFillTime report = new ReportDataFillTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		int year = 0;
		int month = 0;
		String startTime = null;
		String endTime = null;
		Date time = null;
		if (dataTime == null || dataTime == "") {
			time = new Date();
		} else {
			if (dataTime.length() == 10) {// 2020-02-02
				time = sdf.parse(dataTime);
			} else if (dataTime.length() == 7) {// 2020-02
				time = sdf.parse(dataTime + "-01");
				year = Integer.valueOf(dataTime.substring(0, 4));
				month = Integer.valueOf(dataTime.substring(5, 7));
			} else if (dataTime.length() == 4) {// 2020
				time = sdf.parse(dataTime + "-01" + "-01");
				year = Integer.valueOf(dataTime);
				month = 1;
			}
		}
		Calendar cal = Calendar.getInstance();
		if (dataTime == null || dataTime == "") {
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
		}
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String imptimeBegin = sdf.format(cal.getTime());
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		cal.add(Calendar.DATE, 13);
		String dateTime = null;
		if (fillId == 1) {// 周报
			dateTime = imptimeBegin + "至" + imptimeEnd;
			startTime = imptimeBegin;
			endTime = imptimeEnd;
		} else if (fillId == 2) {// 月报
			dateTime = year + "年" + month + "月";
			startTime = year + "-" + month + "-01";
			if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				endTime = year + "-" + month + "-31";
			} else if (month == 4 || month == 6 || month == 9 || month == 11) {
				endTime = year + "-" + month + "-30";
			} else if (month == 2) {
				if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
					endTime = year + "-" + month + "-29";
				} else {
					endTime = year + "-" + month + "-28";
				}
			}
		} else if (fillId == 3) {// 季报
			if (month < 4) {
				dateTime = year + "年1月至" + year + "年3月";
				startTime = year + "-01-01";
				endTime = year + "-03-31";
			} else if (month > 3 && month < 7) {
				dateTime = year + "年4月至" + year + "年6月";
				startTime = year + "-04-01";
				endTime = year + "-06-30";
			} else if (month > 6 && month < 10) {
				dateTime = year + "年7月至" + year + "年9月";
				startTime = year + "-07-01";
				endTime = year + "-09-30";
			} else {
				dateTime = year + "年10月至" + year + "年12月";
				startTime = year + "-10-01";
				endTime = year + "-12-31";
			}
		} else if (fillId == 4) {// 半年报
			if (month < 7) {
				dateTime = year + "年1月至" + year + "年6月";
				startTime = year + "-01-01";
				endTime = year + "-06-30";
			} else {
				dateTime = year + "年7月至" + year + "年12月";
				startTime = year + "-07-01";
				endTime = year + "-12-31";
			}
		} else if (fillId == 5) {// 年报
			dateTime = year + "年";
			startTime = year + "-01-01";// 2020-01-01
			endTime = year + "-12-31";
		}
		report.setFillTime(dateTime);
		report.setStartTime(startTime);
		report.setEndTime(endTime);
		return report;
	}
}
