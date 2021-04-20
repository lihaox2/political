package com.bayee.political.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bayee.political.enums.TrainProjectType;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.Department;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.TrainAchievementTemplate;
import com.bayee.political.domain.TrainActivityStyle;
import com.bayee.political.domain.TrainConstitution;
import com.bayee.political.domain.TrainFirearm;
import com.bayee.political.domain.TrainFirearmAchievement;
import com.bayee.political.domain.TrainGetMedal;
import com.bayee.political.domain.TrainGroup;
import com.bayee.political.domain.TrainGroupPolice;
import com.bayee.political.domain.TrainLeaderAchievement;
import com.bayee.political.domain.TrainLeaderMoreAchievement;
import com.bayee.political.domain.TrainLeader;
import com.bayee.political.domain.TrainLeaderSignUpSuccess;
import com.bayee.political.domain.TrainLeaderStatistics;
import com.bayee.political.domain.TrainMatch;
import com.bayee.political.domain.TrainMatchAchievement;
import com.bayee.political.domain.TrainMedalManage;
import com.bayee.political.domain.TrainPacesetter;
import com.bayee.political.domain.TrainPhysical;
import com.bayee.political.domain.TrainPhysicalAchievement;
import com.bayee.political.domain.TrainPhysicalAchievementDetails;
import com.bayee.political.domain.TrainPhysicalProjectRecord;
import com.bayee.political.domain.TrainPhysicalStatistics;
import com.bayee.political.domain.TrainProject;
import com.bayee.political.domain.TrainProjectNeedScore;
import com.bayee.political.domain.TrainProjectRule;
import com.bayee.political.domain.TrainProjectURule;
import com.bayee.political.domain.TrainRank;
import com.bayee.political.domain.TrainRankList;
import com.bayee.political.domain.TrainRecommendPolice;
import com.bayee.political.domain.TrainRecordPolice;
import com.bayee.political.domain.TrainRecordScore;
import com.bayee.political.domain.TrainRecordScoreType;
import com.bayee.political.domain.TrainSignIn;
import com.bayee.political.domain.TrainStatisticsAnalysis;
import com.bayee.political.domain.TrainUnit;
import com.bayee.political.domain.User;
import com.bayee.political.service.DepartmentService;
import com.bayee.political.service.TrainMatchService;
import com.bayee.political.service.TrainService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListPage;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import com.bayee.political.utils.ExportExcelUtils;
import com.bayee.political.utils.GetExcel;
import com.bayee.political.utils.QRCode;
import com.bayee.political.utils.StatusCode;
import com.taobao.api.ApiException;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;

/**
 * @author shentuqiwei
 * @version 2020年9月28日 上午9:41:36
 * 
 */
@Controller
@Component
public class TrainController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private TrainService trainService;

	@Autowired
	private TrainMatchService trainMatchService;

	@Autowired
	private DepartmentService departmentService;

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

	// 进行中的训练查询
	@RequestMapping(value = "/train/in/progress/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainInProgressList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 进行中的训练查询
		List<TrainPhysical> list = trainService.trainInProgressList(policeId, 2, "desc");// status=2
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 即将开始的训练查询
	@RequestMapping(value = "/train/start/right/now/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainStartRightNowList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 即将开始的训练查询
		List<TrainPhysical> list = trainService.trainInProgressList(policeId, 1, "asc");// status=1
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
					String dateTime = sdf.format(list.get(i).getTrainStartDate()).substring(11, 16);
					list.get(i).setCreationDateStr("明天" + dateTime + "开始");
				} else if (timeItem >= 2880 && timeItem < 4320) {
					String dateTime = sdf.format(list.get(i).getTrainStartDate()).substring(11, 16);
					list.get(i).setCreationDateStr("后天" + dateTime + "开始");
				} else if (timeItem >= 4320) {
					int yearInt = Integer.valueOf(currentYearStr);
					int createYear = Integer.valueOf(sdf.format(list.get(i).getTrainStartDate()).substring(0, 4));
					if (yearInt == createYear) {
						list.get(i).setCreationDateStr(
								sdf.format(list.get(i).getTrainStartDate()).substring(5, 10) + "开始");
					} else {
						String timeString = sdf.format(list.get(i).getTrainStartDate()).substring(0, 10);
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

	// 正在报名列表查询
	@RequestMapping(value = "/train/physical/page/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainPhysicalPageList(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainPhysicalStatistics item = new TrainPhysicalStatistics();
		// 综合体能列表查询
		List<TrainPhysical> list = trainService.trainPhysicalList(policeId, type);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getObjectId() == 1) {
					// 综合训练报名人头像查询
					List<CalculationChart> imageList = trainService.trainPhysicalHeadImageList(list.get(i).getId(),
							policeId, 5);
					list.get(i).setHeadImageList(imageList);
				} else if (list.get(i).getObjectId() == 2) {
					// 枪械报名人头像查询
					List<CalculationChart> imageList = trainService.trainFirearmHeadImageList(list.get(i).getId(),
							policeId, 5);
					list.get(i).setHeadImageList(imageList);
				}
			}
		}
		// 综合体能列表总数统计
//		int total = trainService.trainPhysicalCount(policeId, type);
		int total1 = trainService.trainPhysicalCount(policeId, 1);
		int total2 = trainService.trainPhysicalCount(policeId, 2);
		item.setDepNum(total1);
		item.setBranchOfficeNum(total2);
		item.setTrainList(list);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 综合体能详情查询
	@RequestMapping(value = "/train/physical/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainPhysicalItem(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		TrainPhysical item = new TrainPhysical();
		if (objectId == 1) {
			// 综合体能详情查询
			item = trainService.trainPhysicalItem(id);
			List<TrainPhysicalAchievementDetails> deList = trainService.isWholeSignList(id, policeId);
			if (deList.size() == 0) {
				item.setIsWholeSign(1);
			} else {
				item.setIsWholeSign(0);
			}
			if (item != null) {
				if (user != null) {
					item.setPoliceId(policeId);
					item.setPoliceName(user.getName());
				}
				// 查询组别民警
				List<TrainGroupPolice> groupList = trainService.trainGroupPoliceList(policeId);
				if (groupList.size() > 0) {
					item.setTrainGroupNames(groupList.get(0).getGroupName());
				}
				List<CalculationChart> totalList = new ArrayList<CalculationChart>();
				int num = 4;
				// 综合训练报名当前用户头像查询
				CalculationChart imageItem = trainService.trainPhysicalHeadImageItem(id, policeId);
				if (imageItem != null) {
					totalList.add(imageItem);
				} else {
					num = 5;
				}
				// 综合训练报名人头像查询(排除当前用户)
				List<CalculationChart> imageList = trainService.trainPhysicalHeadImageList(id, policeId, num);
				if (imageList.size() > 0) {
					totalList.addAll(imageList);
				}
				if (totalList.size() > 0) {
					item.setHeadImageList(totalList);
				}
				// 查询当前用户所在组包括的项目
				TrainPhysicalProjectRecord projectItem = trainService.projectNamesItem(id, policeId);
				if (projectItem != null) {
					List<String> list = Arrays.asList(projectItem.getTrainProject().split(","));
					List<String> nameList = new ArrayList<String>();
					for (int i = 0; i < list.size(); i++) {
						TrainProject tItem = trainService.getTrainProjectById(Integer.valueOf(list.get(i)));
						nameList.add(tItem.getName());
					}
					item.setTrainFirearmTypeName(String.join(",", nameList));
				}
				int count = trainService.singleTrainPhysicalAchievementCount(id);
				item.setPoliceNum(count);
				item.setIsU(1);
				// 单项综合体能项目报名详情
				TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, id, policeId);
				if (aItem != null) {
					item.setQrCode(aItem.getQrCode());
					item.setHeadImage(aItem.getHeadImage());
					item.setPoliceId(policeId);
					item.setPoliceName(aItem.getName());
				}
				if (item.getQrCode() == null) {
					if (item.getSignUpStatus() == 2) {
						item.setCreationDateStr(getReportSurplusTime(item.getTimeChange()));
					}
				} else {
					if (item.getStatus() == 1) {
						item.setCreationDateStr(getReportStartTime(item.getStartTimeChange()));
					} else if (item.getStatus() == 2) {
						String timeString = getDatePoor(item.getTrainEndDate(), new Date());
						item.setCreationDateStr("剩余" + timeString);
					}
				}
			}
		} else if (objectId == 2) {
			// 枪械详情查询
			item = trainService.trainPhFirearmItem(id);
			if (item != null) {
				if (user != null) {
					item.setPoliceId(policeId);
					item.setPoliceName(user.getName());
				}
				if (item.getType() == 1) {
					item.setTrainGroupNames("局部");
				} else {
					item.setTrainGroupNames("全局");
				}
				List<CalculationChart> totalList = new ArrayList<CalculationChart>();
				int num = 4;
				// 枪械报名当前用户头像查询
				CalculationChart imageItem = trainService.trainFirearmHeadImageItem(id, policeId);
				if (imageItem != null) {
					totalList.add(imageItem);
				} else {
					num = 5;
				}
				// 枪械报名人头像查询(排除当前用户)
				List<CalculationChart> imageList = trainService.trainFirearmHeadImageList(id, policeId, num);
				item.setHeadImageList(imageList);

				if (imageList.size() > 0) {
					totalList.addAll(imageList);
				}
				if (totalList.size() > 0) {
					item.setHeadImageList(totalList);
				}
				int count = trainService.singleTrainFirearmAchievementCount(id);
				TrainProject project = trainService.getTrainProjectById(item.getTrainFirearmType());
				if (project != null) {
					item.setIsU(project.getIsU());
				} else {
					item.setIsU(1);
				}
				item.setPoliceNum(count);
				// 枪械项目报名详情
				TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, id, policeId);
				if (fItem != null) {
					item.setQrCode(fItem.getQrCode());
					item.setHeadImage(fItem.getHeadImage());
					item.setPoliceId(policeId);
					item.setPoliceName(fItem.getName());
					if (fItem.getIsSign() == 2) {
						item.setIsWholeSign(1);
					} else {
						item.setIsWholeSign(0);
					}
				}
				if (item.getQrCode() == null) {
					if (item.getSignUpStatus() == 2) {
						item.setCreationDateStr(getReportSurplusTime(item.getTimeChange()));
					}
				} else {
					if (item.getStatus() == 1) {
						item.setCreationDateStr(getReportStartTime(item.getStartTimeChange()));
					} else if (item.getStatus() == 2) {
						Date enDate = item.getTrainEndDate();
						String timeString = getDatePoor(enDate, new Date());
						item.setCreationDateStr("剩余" + timeString);
					}
				}
			}
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
			surplusTime = "训练0分钟后开始";
		} else if (timeItem < 60 && timeItem > 0) {
			surplusTime = "训练" + timeItem + "分钟后开始";
		} else if (timeItem >= 60 && timeItem < 1440) {
			int timeInt = (int) Math.floor(timeItem / 60);
			surplusTime = "训练" + timeInt + "小时后开始";
		} else if (timeItem >= 1440) {
			int timeInt = (int) Math.floor(timeItem / 60 / 24);
			surplusTime = "训练" + timeInt + "天后开始";
		}
		return surplusTime;
	}

	// 综合体能报名(单位)
	@ResponseBody
	@RequestMapping(value = "/train/physical/sign/up/creat", method = RequestMethod.POST)
	public DataListReturn trainPhysicalSignUpCreat(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		TrainPhysicalAchievement item = new TrainPhysicalAchievement();
		// 单项综合体能项目报名详情
		TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, id, policeId);
		if (aItem == null) {
			// 综合体能详情查询
			TrainPhysical tItem = trainService.trainPhysicalItem(id);
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
			item.setTrainPhysicalId(id);
			item.setPoliceId(policeId);
			item.setRegistrationDate(new Date());
			// 查询组别民警
			List<TrainGroupPolice> groupList = trainService.trainGroupPoliceList(policeId);
			item.setTrainGroupId(groupList.get(0).getId());
			item.setIsSign(1);
			item.setAchievementGrade(1);
			item.setCreationDate(new Date());
			// 查询当前民警所属组别中的综合体能项目
			int count = 0;
			List<TrainProject> projectList = trainService.trainPoliceBelongToList(id, groupList.get(0).getId());
			if (projectList.size() > 0) {
				String qrName = "physical-" + UUID.randomUUID().toString() + ".jpg";
				item.setQrCode(trainQRCode + qrName);
				// 生成二维码
				new QRCode(root + trainQRCodeIcon).encodeQRCode("1," + id + "," + policeId, 500, 500,
						root + trainQRCode + qrName);
				count = trainService.trainPhysicalAchievementCreat(item);
				for (int i = 0; i < projectList.size(); i++) {
					// 项目成绩新增
					TrainPhysicalAchievementDetails deItem = new TrainPhysicalAchievementDetails();
					deItem.setTrainPhysicalId(id);
					deItem.setTrainPhysicalAchievementId(item.getId());
					deItem.setPoliceId(policeId);
					deItem.setProjectId(projectList.get(i).getId());
					deItem.setIsEntry(1);
					deItem.setIsSign(1);
					deItem.setCreationDate(new Date());
					trainService.trainPhysicalAchievementDetailsCreat(deItem);
				}
			} else {
				dlr.setStatus(false);
				dlr.setMessage("当前组别下没有综合体能项目，请在后台配置");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
			if (count > 0) {
				dlr.setStatus(true);
				dlr.setMessage("报名成功");
				dlr.setResult(item);
				dlr.setCode(StatusCode.getSuccesscode());
			} else {
				dlr.setStatus(false);
				dlr.setMessage("报名失败");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
			}
			return dlr;
		} else {
			dlr.setStatus(false);
			dlr.setMessage("您已报名该训练");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
	}

	// 枪械报名(单位)
	@ResponseBody
	@RequestMapping(value = "/train/firearm/sign/up/creat", method = RequestMethod.POST)
	public DataListReturn trainFirearmSignUpCreat(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		TrainFirearmAchievement item = new TrainFirearmAchievement();
		// 枪械项目报名详情
		TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, id, policeId);
		if (fItem == null) {
			// 枪械详情查询
			TrainFirearm tItem = trainService.trainFirearmItem(id);
//			List<String> list = Arrays.asList(tItem.getInvolvementPoliceIds().split(","));
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
			item.setTrainFirearmId(id);
			item.setPoliceId(policeId);
			item.setRegistrationDate(new Date());
			item.setIsSign(1);
			item.setIsSubmit(0);
			item.setTrainProjectType(tItem.getTrainFirearmType());
			item.setCreationDate(new Date());
			String qrName = "physical-" + UUID.randomUUID().toString() + ".jpg";
			item.setQrCode(trainQRCode + qrName);
			// 生成二维码
			new QRCode(root + trainQRCodeIcon).encodeQRCode("2," + id + "," + policeId, 500, 500,
					root + trainQRCode + qrName);
			int count = trainService.trainFirearmAchievementCreat(item);
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
			dlr.setMessage("您已报名该训练");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
	}

	// 近期训练查询
	@RequestMapping(value = "/train/recent/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainRecentList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 近期训练查询
		List<TrainPhysical> list = trainService.trainRecentList(policeId);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int timeItem = Math.abs(list.get(i).getTimeChange());
				if (list.get(i).getStatus() == 1) {
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
				} else {
					if (timeItem >= 0 && timeItem < 1440) {
						list.get(i).setCreationDateStr("第1天");
					} else if (timeItem >= 1440) {
						int timeInt = (int) Math.floor(timeItem / 60 / 24) + 1;
						list.get(i).setCreationDateStr("第" + timeInt + "天");
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

	// 我的训练查询
	@RequestMapping(value = "/train/my/page/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainMyList(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		TrainPhysicalStatistics item = new TrainPhysicalStatistics();
		if (status != null) {
			if (status != 1 && status != 2 && status != 3) {
				status = null;
			}
		}
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		// 我的训练列表查询
		List<TrainPhysical> list = trainService.trainMyList(policeId, type, status, pageSize, pageNum);
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
						String dateTime = sdf.format(list.get(i).getTrainStartDate()).substring(11, 16);
						list.get(i).setCreationDateStr("明天" + dateTime + "开始");
					} else if (timeItem >= 2880 && timeItem < 4320) {
						String dateTime = sdf.format(list.get(i).getTrainStartDate()).substring(11, 16);
						list.get(i).setCreationDateStr("后天" + dateTime + "开始");
					} else if (timeItem >= 4320) {
						int yearInt = Integer.valueOf(currentYearStr);
						int createYear = Integer.valueOf(sdf.format(list.get(i).getTrainStartDate()).substring(0, 4));
						if (yearInt == createYear) {
							list.get(i).setCreationDateStr(
									sdf.format(list.get(i).getTrainStartDate()).substring(5, 10) + "开始");
						} else {
							String timeString = sdf.format(list.get(i).getTrainStartDate()).substring(0, 10);
							list.get(i).setCreationDateStr(timeString + "开始");
						}
					}
				}
			}
		}
		// 我的训练列表总数统计
		int total = trainService.trainMyCount(policeId, type, status);
		int total1 = trainService.trainMyCount(policeId, 1, status);
		int total2 = trainService.trainMyCount(policeId, 2, status);
		item.setDepNum(total1);
		item.setBranchOfficeNum(total2);
		item.setTrainList(list);
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

	// 我的成绩查询
	@RequestMapping(value = "/train/my/entry/record/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainMyEntryRecordList(@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = true) Integer trainPhysicalId,
			@RequestParam(value = "policeId", required = true) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<TrainPhysicalAchievementDetails> list = new ArrayList<TrainPhysicalAchievementDetails>();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		if (objectId == 1) {// 1综合体能2枪械
			TrainPhysical item = trainService.trainPhysicalItem(trainPhysicalId);
			if (item.getStatus() != 1) {
				// 单项综合体能项目报名详情
				TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, trainPhysicalId,
						policeId);
				if (aItem != null) {
					// 我的参赛记录查询
					list = trainService.trainMyEntryRecordList(trainPhysicalId, aItem.getId(), policeId);
					for (int i = 0; i < list.size(); i++) {
						String sort = null;
						if (list.get(i).getSortNum() == 1) {// 升序
							sort = "asc";
						} else if (list.get(i).getSortNum() == 2) {// 降序
							sort = "desc";
						}
						TrainRank personalRankItem = trainService.trainPersonalRankItem(list.get(i).getProjectId(),
								trainPhysicalId, policeId, null, sort);
						if (list.get(i).getAchievementGrade() == null) {
							list.get(i).setRank(null);
						} else {
							if (personalRankItem != null && personalRankItem.getRankId() != null
									&& personalRankItem.getRankId() != 0) {
								list.get(i).setRank(personalRankItem.getRankId());
							} else {
								list.get(i).setRank(null);
							}
						}
					}
				}
			}
		} else if (objectId == 2) {// 2枪械
			TrainFirearm fitem = trainService.trainFirearmItem(trainPhysicalId);
			if (fitem.getStatus() != 1) {
				// 枪械报名详情
				TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, trainPhysicalId,
						policeId);
				if (fItem != null) {
					// 我的参赛记录查询
					TrainPhysicalAchievementDetails item = new TrainPhysicalAchievementDetails();
					item.setId(0);
					item.setTrainPhysicalId(0);
					item.setTrainPhysicalAchievementId(0);
					item.setPoliceId(fItem.getPoliceId());
					item.setProjectId(fItem.getTrainProjectType());
					item.setProjectName(fItem.getProjectName());
					if (fItem.getAchievement() == null) {
						item.setIsEntry(1);
					} else {
						item.setIsEntry(2);
						item.setAchievement(fItem.getAchievement());
						item.setAchievementGrade(fItem.getAchievementGrade());
						item.setAchievementStr(fItem.getAchievementStr());
					}
					item.setUnitName(fItem.getUnitName());
					// 参赛枪械项目名称查询
					List<CalculationChart> projectList = trainService.trainProjectFirearmRankList(trainPhysicalId,
							policeId);
					String sort = null;
					if (projectList.get(0).getNum() == 1) {// 升序
						sort = "asc";
					} else if (projectList.get(0).getNum() == 2) {// 降序
						sort = "desc";
					}
					TrainRank personalRankItem = trainService.trainPersonalFirearmRankItem(projectList.get(0).getId(),
							trainPhysicalId, policeId, null, sort);
					if (personalRankItem != null && personalRankItem.getRankId() != null
							&& personalRankItem.getRankId() != 0) {
						item.setRank(personalRankItem.getRankId());
					} else {
						item.setRank(null);
					}
					list.add(item);
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setResult(list);
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 实时排行榜
	@RequestMapping(value = "/train/my/entry/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainMyEntryRankList(@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<TrainRankList> list = new ArrayList<TrainRankList>();
		if (objectId == 1) {// 1综合体能
			TrainPhysical titem = trainService.trainPhysicalItem(trainPhysicalId);
			if (titem.getStatus() != 1) {
				// 单项综合体能项目报名详情
				TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, trainPhysicalId,
						policeId);
				if (aItem != null) {
					// 参赛项目名称查询
					List<CalculationChart> projectList = trainService.trainProjectRankList(trainPhysicalId,
							aItem.getId(), policeId, null);
					for (int i = 0; i < projectList.size(); i++) {
						String sort = null;
						if (projectList.get(i).getNum() == 1) {// 升序
							sort = "asc";
						} else if (projectList.get(i).getNum() == 2) {// 降序
							sort = "desc";
						}
						TrainRankList item = new TrainRankList();
						item.setProjectItem(projectList.get(i));
						// 根据年龄组和训练项目排名
						List<TrainRank> rankList = trainService.trainPersonalRankList(projectList.get(i).getId(),
								trainPhysicalId, policeId, null, sort);
						item.setRankList(rankList);
						TrainRank personalRankItem = trainService.trainPersonalRankItem(projectList.get(i).getId(),
								trainPhysicalId, policeId, null, sort);
						item.setRankItem(personalRankItem);
						list.add(item);
					}
				}
			}
		} else if (objectId == 2) {// 2枪械
			TrainFirearm fitem = trainService.trainFirearmItem(trainPhysicalId);
			if (fitem.getStatus() != 1) {
				// 枪械报名详情
				TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, trainPhysicalId,
						policeId);
				if (fItem != null) {
					// 参赛枪械项目名称查询
					List<CalculationChart> projectList = trainService.trainProjectFirearmRankList(trainPhysicalId,
							policeId);
					for (int i = 0; i < projectList.size(); i++) {
						String sort = null;
						if (projectList.get(i).getNum() == 1) {// 升序
							sort = "asc";
						} else if (projectList.get(i).getNum() == 2) {// 降序
							sort = "desc";
						}
						TrainRankList item = new TrainRankList();
						item.setProjectItem(projectList.get(i));
						// 根据年龄组和枪械项目排名
						List<TrainRank> rankList = trainService.trainPersonalFirearmRankList(projectList.get(i).getId(),
								trainPhysicalId, policeId, null, sort);
						if (rankList.size() > 0) {
							for (int k = 0; k < rankList.size(); k++) {
								if (rankList.get(k).getUnitName().equals("分秒")) {
									rankList.get(k).setUnitName("分");
								} else if (rankList.get(k).getUnitName().equals("时分秒")) {
									rankList.get(k).setUnitName("时");
								}
							}
						}
						item.setRankList(rankList);
						TrainRank personalRankItem = trainService.trainPersonalFirearmRankItem(
								projectList.get(i).getId(), trainPhysicalId, policeId, null, sort);
						if (personalRankItem != null) {
							if (personalRankItem.getUnitName().equals("分秒")) {
								personalRankItem.setUnitName("分");
							} else if (personalRankItem.getUnitName().equals("时分秒")) {
								personalRankItem.setUnitName("时");
							}
						}
						item.setRankItem(personalRankItem);
						list.add(item);
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

	// 更多榜单查询
	@RequestMapping(value = "/train/my/entry/more/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainMyEntryMoreRankList(
			@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "projectId", required = false) Integer projectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<TrainRankList> list = new ArrayList<TrainRankList>();
		if (objectId == 1) {// 1综合体能
			// 单项综合体能项目报名详情
			TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, trainPhysicalId, policeId);
			if (aItem != null) {
				// 参赛项目名称查询
				List<CalculationChart> projectList = trainService.trainProjectRankList(trainPhysicalId, aItem.getId(),
						policeId, projectId);
				for (int i = 0; i < projectList.size(); i++) {
					String sort = null;
					if (projectList.get(i).getNum() == 1) {// 升序
						sort = "asc";
					} else if (projectList.get(i).getNum() == 2) {// 降序
						sort = "desc";
					}
					TrainRankList item = new TrainRankList();
					item.setProjectItem(projectList.get(i));
					// 根据年龄组和训练项目排名(不限制人数)
					List<TrainRank> rankList = trainService.trainPersonalMoreRankList(projectList.get(i).getId(),
							trainPhysicalId, policeId, null, sort);
					item.setRankList(rankList);
					TrainRank personalRankItem = trainService.trainPersonalRankItem(projectList.get(i).getId(),
							trainPhysicalId, policeId, null, sort);
					item.setRankItem(personalRankItem);
					list.add(item);
				}
			}
		} else if (objectId == 2) {// 2枪械
			// 枪械报名详情
			TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, trainPhysicalId, policeId);
			if (fItem != null) {
				// 参赛枪械项目名称查询
				List<CalculationChart> projectList = trainService.trainProjectFirearmRankList(trainPhysicalId,
						policeId);
				for (int i = 0; i < projectList.size(); i++) {
					String sort = null;
					if (projectList.get(i).getNum() == 1) {// 升序
						sort = "asc";
					} else if (projectList.get(i).getNum() == 2) {// 降序
						sort = "desc";
					}
					TrainRankList item = new TrainRankList();
					item.setProjectItem(projectList.get(i));
					// 根据年龄组和枪械项目排名
					List<TrainRank> rankList = trainService.trainPersonalFirearmRankList(projectList.get(i).getId(),
							trainPhysicalId, policeId, null, sort);
					item.setRankList(rankList);
					TrainRank personalRankItem = trainService.trainPersonalFirearmRankItem(projectList.get(i).getId(),
							trainPhysicalId, policeId, null, sort);
					item.setRankItem(personalRankItem);
					list.add(item);
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 榜单项目查询
	@RequestMapping(value = "/train/my/entry/project/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainMyEntryProjectList(
			@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<CalculationChart> projectList = new ArrayList<CalculationChart>();
		if (objectId == 1) {// 1综合体能
			// 单项综合体能项目报名详情
			TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, trainPhysicalId, policeId);
			if (aItem != null) {
				// 参赛项目名称查询
				projectList = trainService.trainProjectRankList(trainPhysicalId, aItem.getId(), policeId, null);
			}
		} else if (objectId == 2) {// 2枪械
			// 枪械报名详情
			TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, trainPhysicalId, policeId);
			if (fItem != null) {
				// 参赛枪械项目名称查询
				projectList = trainService.trainProjectFirearmRankList(trainPhysicalId, policeId);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(projectList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 二维码签到页面查询
	@RequestMapping(value = "/train/sign/in/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainSignInItem(@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainSignIn sItem = new TrainSignIn();
		if (objectId == 1) {// 1综合体能
			// 单项综合体能项目报名详情
			TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, id, policeId);
			if (aItem != null) {
				sItem.setObjectId(1);
				sItem.setTrainPhysicalId(id);
				sItem.setTrainPhysicalName(aItem.getTrainPhysicalName());
				sItem.setPoliceId(policeId);
				sItem.setName(aItem.getName());
				sItem.setHeadImage(aItem.getHeadImage());
				// 查询当前训练下的项目
				List<TrainPhysicalAchievementDetails> projecList = trainService.trainSignInProjectList(id,
						aItem.getId(), policeId, null);
				if (projecList.size() > 0) {
					sItem.setProjecList(projecList);
				}
				// 查询组别民警
				List<TrainGroupPolice> groupList = trainService.trainGroupPoliceList(policeId);
				if (groupList.size() > 0) {
					sItem.setGroupName(groupList.get(0).getGroupName());
				}
				dlr.setResult(sItem);
			} else {
				dlr.setResult(null);
			}
		} else if (objectId == 2) {// 2枪械
			// 枪械报名详情
			TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, id, policeId);
			if (fItem != null) {
				TrainPhysical ttitem = trainService.trainPhFirearmItem(id);
				if (ttitem != null) {
					if (ttitem.getType() == 1) {
						sItem.setGroupName("局部");
					} else {
						sItem.setGroupName("全局");
					}
				}
				sItem.setObjectId(2);
				sItem.setTrainPhysicalId(id);
				sItem.setTrainPhysicalName(fItem.getTrainFirearmName());
				sItem.setPoliceId(policeId);
				sItem.setName(fItem.getName());
				sItem.setHeadImage(fItem.getHeadImage());
				// 查询当前训练下的项目
				List<TrainPhysicalAchievementDetails> projecList = new ArrayList<TrainPhysicalAchievementDetails>();
				TrainPhysicalAchievementDetails details = new TrainPhysicalAchievementDetails();
				details.setId(fItem.getId());
				details.setTrainPhysicalId(fItem.getTrainFirearmId());
				details.setPoliceId(fItem.getPoliceId());
				details.setProjectId(fItem.getTrainProjectType());
				details.setProjectName(fItem.getProjectName());
				details.setIsSign(fItem.getIsSign());
				details.setCreationDate(fItem.getCreationDate());
				projecList.add(details);
				sItem.setProjecList(projecList);
				dlr.setResult(sItem);
			} else {
				dlr.setResult(null);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 二维码签到
	@ResponseBody
	@RequestMapping(value = { "/train/sign/in/submit" }, method = RequestMethod.POST)
	public ResponseEntity<?> trainSignInSubmit(@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "scorerPoliceId", required = false) String scorerPoliceId,
			@RequestParam(value = "resultJson", required = false) String resultJson) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<TrainPhysicalAchievementDetails> projecList = new ArrayList<TrainPhysicalAchievementDetails>();
		if (objectId == 1) {// 1综合体能
			// 单项综合体能项目报名详情
			TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, id, policeId);
			if (aItem != null) {
				// 判断当前用户是否可扫码当前人员
				TrainPhysical scorerItem = trainService.physicalScorerPoliceItem(id, scorerPoliceId);
				if (scorerItem == null) {
					dlr.setStatus(false);
					dlr.setMessage("您此次无签到登记权限");
					dlr.setCode(StatusCode.getFailcode());
					return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
				} else {
					long endTimeLong = aItem.getTrainEndDate().getTime();
					long currentTime = new Date().getTime();
					if (currentTime < endTimeLong) {// 当前时间小于训练结束时间
						// 查询当前训练下的项目
						List<TrainPhysicalAchievementDetails> detailList = trainService.trainSignInProjectList(id, null,
								policeId, 1);
						if (detailList.size() > 0) {
//							String ss = "[{'projectId':'4'}," + "{'projectId':'5'}," + "{'projectId':'18'},"
//									+ "{'projectId':'20'}]";
							JSONArray json = (JSONArray) JSONArray.parse(resultJson);
							for (Object obj : json) {
								JSONObject jo = (JSONObject) obj;
								TrainPhysicalAchievementDetails detailsItem = new TrainPhysicalAchievementDetails();
								detailsItem.setPoliceId(policeId);
								detailsItem.setProjectId(jo.getInteger("projectId"));
								detailsItem.setTrainPhysicalId(id);
								detailsItem.setIsSign(2);
								detailsItem.setUpdateDate(new Date());
								detailsItem.setSignDate(new Date());
								trainService.trainDetailsPoliceUpdate(detailsItem);
								// 查询当前训练项详情
								TrainPhysicalAchievementDetails projecListItem = trainService.physicalDetailsItem(null,
										id, aItem.getId(), policeId, jo.getInteger("projectId"));
								if (projecListItem != null) {
									projecList.add(projecListItem);
								}
							}
							aItem.setIsSign(2);
							aItem.setSignDate(new Date());
							aItem.setUpdateDate(new Date());
							trainService.trainPhysicalAchievementUpdate(aItem);
							dlr.setStatus(true);
							dlr.setMessage("签到成功");
							dlr.setResult(projecList);
							dlr.setCode(StatusCode.getSuccesscode());
						} else {
							dlr.setStatus(false);
							dlr.setMessage("您已签到");
							dlr.setCode(StatusCode.getFailcode());
						}
					} else {
						dlr.setStatus(false);
						dlr.setMessage("训练已结束");
						dlr.setCode(StatusCode.getFailcode());
					}
				}
			} else {
				dlr.setStatus(false);
				dlr.setMessage("您未报名当前训练");
				dlr.setCode(StatusCode.getFailcode());
			}
		} else if (objectId == 2) {// 2枪械
			// 枪械报名详情
			TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, id, policeId);
			if (fItem != null) {
				// 枪械判断当前用户是否可扫码当前人员
				TrainFirearm scorerItem = trainService.firearmScorerPoliceItem(id, scorerPoliceId);
				if (scorerItem == null) {
					dlr.setStatus(false);
					dlr.setMessage("您此次无签到登记权限");
					dlr.setCode(StatusCode.getFailcode());
					return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
				} else {
					long endTimeLong = fItem.getTrainEndDate().getTime();
					long currentTime = new Date().getTime();
					if (currentTime < endTimeLong) {// 当前时间小于训练结束时间
						if (fItem.getIsSign() == null || fItem.getIsSign() == 1) {
							fItem.setIsSign(2);
							fItem.setUpdateDate(new Date());
							fItem.setSignDate(new Date());
							trainService.trainFirearmAchievementUpdate(fItem);
							TrainPhysicalAchievementDetails tailsItem = new TrainPhysicalAchievementDetails();
							tailsItem.setProjectId(fItem.getTrainProjectType());
							tailsItem.setProjectName(fItem.getProjectName());
							tailsItem.setIsSign(fItem.getIsSign());
							projecList.add(tailsItem);
							dlr.setStatus(true);
							dlr.setMessage("签到成功");
							dlr.setResult(projecList);
							dlr.setCode(StatusCode.getSuccesscode());
						} else if (fItem.getIsSign() == 2) {
							dlr.setStatus(false);
							dlr.setMessage("您已签到");
							dlr.setCode(StatusCode.getFailcode());
						}
					} else {
						dlr.setStatus(false);
						dlr.setMessage("训练已结束");
						dlr.setCode(StatusCode.getFailcode());
					}
				}
			} else {
				dlr.setStatus(false);
				dlr.setMessage("您未报名当前训练");
				dlr.setCode(StatusCode.getFailcode());
			}
		}
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 录分类型查询
	@RequestMapping(value = "/train/record/score/type/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainRecordScoreTypeItem(
			@RequestParam(value = "policeId", required = true) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainRecordScoreType item = new TrainRecordScoreType();
		// 综合体能未录分数量
		int trainNum = trainService.trainPhysicalNoScoreNum(policeId);
		item.setTrainNum(trainNum);
		// 赛事未录分数量
		int matchNum = trainMatchService.matchNoScoreNum(policeId);
		item.setMatchNum(matchNum);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 需要录分的训练查询
	@RequestMapping(value = "/train/record/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainRecordScoreList(@RequestParam(value = "policeId", required = true) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		// 需要录分的训练查询
		List<TrainRecordScore> list = trainService.trainPhysicalNoScoreList(policeId);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getProportion() > 0 && list.get(i).getProportion() < 100) {
					list.get(i).setStatusName("录入中");
				} else if (list.get(i).getProportion() == 0) {
					list.get(i).setStatusName("未录入");
				} else {
					list.get(i).setStatusName("已完成");
				}
				if (list.get(i).getObjectId() == 1) {
					TrainPhysical physical = trainService.trainPhysicalItem(list.get(i).getTrainPhysicalId());
					list.get(i).setTrainStartDate(physical.getTrainStartDate());
					list.get(i).setTrainEndDate(physical.getTrainEndDate());
				} else if (list.get(i).getObjectId() == 2) {
					TrainFirearm firearm = trainService.trainFirearmItem(list.get(i).getTrainPhysicalId());
					list.get(i).setTrainStartDate(firearm.getTrainStartDate());
					list.get(i).setTrainEndDate(firearm.getTrainEndDate());
				}
				if (user != null && list.get(i).getType() == 1) {
					list.get(i).setDepartmentId(user.getDepartmentId());
				}
				TrainPhysicalAchievement tItem = trainService.trainPhysicalAchievementItem(null,
						list.get(i).getTrainPhysicalId(), policeId);
				if (tItem == null) {
					list.get(i).setIsReport(0);
				} else {
					list.get(i).setIsReport(1);
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 已完成录分的训练查询
	@RequestMapping(value = "/train/record/over/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainRecordOverScoreList(
			@RequestParam(value = "policeId", required = true) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 已完成录分的训练查询
		List<TrainRecordScore> list = trainService.trainPhysicalOverScoreList(policeId);
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (user != null && list.get(i).getType() == 1) {
					list.get(i).setDepartmentId(user.getDepartmentId());
				}
				TrainPhysicalAchievement tItem = trainService.trainPhysicalAchievementItem(null,
						list.get(i).getTrainPhysicalId(), policeId);
				if (tItem == null) {
					list.get(i).setIsReport(0);
				} else {
					list.get(i).setIsReport(1);
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 录分训练计划中项目查询
	@RequestMapping(value = "/train/record/project/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainRecordProjectScoreList(
			@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = true) Integer trainPhysicalId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainProjectNeedScore item = new TrainProjectNeedScore();
		if (objectId == 1) {// 综合体能
			// 录分训练计划中综合体能项目查询
			List<TrainProject> list = trainService.trainRecordProjectPhysicalList(trainPhysicalId);
			item.setProjectList(list);
			item.setTotalEnterNum(list.size());
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getStatusName().equals("已录入")) {
					num = num + 1;
				}
				list.get(i).setIsU(1);
			}
			item.setEnterNum(num);
		} else if (objectId == 2) {
			// 录分训练计划中枪械查询
			List<TrainProject> list = trainService.trainRecordProjectFirearmList(trainPhysicalId);
			item.setProjectList(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 综合体能训练成绩提交
	@RequestMapping(value = { "/train/physical/score/submit" }, method = RequestMethod.POST)
	public ResponseEntity<?> trainPhysicalScoreSubmit(
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainPhysical item = new TrainPhysical();
		item.setId(trainPhysicalId);
		item.setIsSubmit(1);
		item.setSubmitDate(new Date());
		item.setUpdateDate(new Date());
		int count = trainService.trainPhysicalUpdate(item);
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

	// 训练人员身高体重录入查询
//	@RequestMapping(value = "/train/record/police/height/list", method = RequestMethod.GET)
//	public ResponseEntity<?> trainRecordPoliceHeightList(
//			@RequestParam(value = "projectId", required = true) Integer projectId,
//			@RequestParam(value = "trainPhysicalId", required = true) Integer trainPhysicalId) throws ApiException {
//		DataListReturn dlr = new DataListReturn();
//		// 已录人数统计
//		TrainRecordPolice item = trainService.trainRecordPoliceItem(projectId, trainPhysicalId);
//		if (item != null) {
//			// 录分训练计划中综合体能项目查询
//			List<User> list = userService.trainRecordPoliceHeightList(projectId, trainPhysicalId);
//			item.setUserList(list);
//		}
//		dlr.setStatus(true);
//		dlr.setMessage("success");
//		dlr.setResult(item);
//		dlr.setCode(StatusCode.getSuccesscode());
//		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
//	}

	// 训练人员身高体重录入修改
//	@RequestMapping(value = { "/train/record/police/height/update" }, method = RequestMethod.POST)
//	public ResponseEntity<?> trainRecordPoliceHeightUpdate(
//			@RequestParam(value = "resultJson", required = false) String resultJson) throws ApiException {
//		DataListReturn dlr = new DataListReturn();
//		String ss = "[{'policeId':'017107','policeHeight':170,'policeWeight':60},"
//				+ "{'policeId':'017090','policeHeight':171,'policeWeight':61},"
//				+ "{'policeId':'117095','policeHeight':172,'policeWeight':62},"
//				+ "{'policeId':'116823','policeHeight':173,'policeWeight':63},"
//				+ "{'policeId':'116442','policeHeight':174,'policeWeight':64}]";
//		JSONArray json = (JSONArray) JSONArray.parse(resultJson);
//		for (Object obj : json) {
//			JSONObject jo = (JSONObject) obj;
//			User item = new User();
//			item.setPoliceId(jo.getString("policeId"));
//			item.setPoliceHeight(jo.getDouble("policeHeight"));
//			item.setPoliceWeight(jo.getDouble("policeWeight"));
//			item.setUpdateDate(new Date());
//			userService.userPoliceUpdate(item);
//		}
//		dlr.setStatus(true);
//		dlr.setMessage("success");
//		dlr.setResult(1);
//		dlr.setCode(StatusCode.getSuccesscode());
//		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
//	}

	// 训练人员成绩查询
	@RequestMapping(value = "/train/record/police/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainRecordPoliceScoreList(
			@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "projectId", required = true) Integer projectId,
			@RequestParam(value = "trainPhysicalId", required = true) Integer trainPhysicalId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (objectId == 1) {// 综合体能
			// 已录人数统计
			TrainRecordPolice item = trainService.trainRecordPoliceItem(projectId, trainPhysicalId);
			if (item != null) {
				// 训练人员成绩查询
				List<TrainRank> list = trainService.trainRecordPoliceScoreList(projectId, trainPhysicalId);
				item.setScoreList(list);
				dlr.setResult(item);
			}
		} else if (objectId == 2) {// 枪械
			// 已录枪械人数统计
			TrainRecordPolice item = trainService.trainRecordFirearmPoliceItem(projectId, trainPhysicalId);
			if (item != null) {
				// 枪械训练人员成绩查询
				List<TrainRank> list = trainService.trainRecordFirearmPoliceScoreList(null, trainPhysicalId);
				item.setScoreList(list);
				dlr.setResult(item);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 综合体能训练成绩录入
	@RequestMapping(value = { "/train/physical/record/police/score/update" }, method = RequestMethod.POST)
	public ResponseEntity<?> trainPhysicalRecordPoliceScoreUpdate(
			@RequestParam(value = "resultJson", required = false) String resultJson) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		Date nowDate = new Date();
		String policeId = null;
		Integer trainPhysicalId = null;
		Integer projectId = null;
//		String ss = "[{'policeId':'117291','projectId':'19','trainPhysicalId':'19','achievement':'4.5','achievementFirst':'4','achievementSecond':'30','achievementStr':'4分30秒'},"
//				+ "{'policeId':'117565','projectId':'19','trainPhysicalId':'19','achievement':'5.0','achievementFirst':'5','achievementSecond':'0','achievementStr':'5分0秒'},"
//				+ "{'policeId':'117020','projectId':'19','trainPhysicalId':'19','achievement':'5.5','achievementFirst':'5','achievementSecond':'30','achievementStr':'5分30秒'},"
//				+ "{'policeId':'116629','projectId':'19','trainPhysicalId':'19','achievement':'6.5','achievementFirst':'6','achievementSecond':'30','achievementStr':'6分30秒'},"
//				+ "{'policeId':'117195','projectId':'19','trainPhysicalId':'19','achievement':'7.75','achievementFirst':'7','achievementSecond':'45','achievementStr':'7分45秒'}]";
		JSONArray json = (JSONArray) JSONArray.parse(resultJson);
		for (Object obj : json) {
			JSONObject jo = (JSONObject) obj;
			TrainPhysicalAchievementDetails item = new TrainPhysicalAchievementDetails();
			policeId = jo.getString("policeId");
			trainPhysicalId = jo.getInteger("trainPhysicalId");
			projectId = jo.getInteger("projectId");
			item.setPoliceId(jo.getString("policeId"));
			item.setProjectId(jo.getInteger("projectId"));
			item.setTrainPhysicalId(jo.getInteger("trainPhysicalId"));
			item.setAchievement(jo.getDouble("achievement"));
			item.setAchievementFirst(jo.getInteger("achievementFirst"));
			item.setAchievementSecond(jo.getDouble("achievementSecond"));
			item.setAchievementStr(jo.getString("achievementStr"));
			item.setIsEntry(2);
			item.setUpdateDate(new Date());
			TrainPhysicalAchievementDetails physicalDetailsItem = trainService.physicalDetailsItem(null,
					trainPhysicalId, null, jo.getString("policeId"), jo.getInteger("projectId"));
			Date signDate = null;
			Integer signInt = null;
			if (physicalDetailsItem.getIsSign() == 1 && jo.getDouble("achievement") > 0) {
				item.setIsSign(2);
				// 综合体能详情查询
				TrainPhysical trainPhysicalItem = trainService.trainPhysicalItem(trainPhysicalId);
				item.setSignDate(trainPhysicalItem.getTrainEndDate());
				// 查询当前训练下的项目
				List<TrainPhysicalAchievementDetails> projecList = trainService.trainSignInProjectList(trainPhysicalId,
						null, policeId, 1);
				if (projecList.size() == 0) {
					signDate = trainPhysicalItem.getTrainEndDate();
					signInt = 2;
				}
			}
			// 查询当前用户所在组包括的项目
			TrainPhysicalProjectRecord projectItem = trainService.projectNamesItem(jo.getInteger("trainPhysicalId"),
					jo.getString("policeId"));
			// 根据项目id/组别查询算分规则
			TrainProjectRule ruleItem = trainService.trainProjectPoliceRuleItem(jo.getInteger("projectId"),
					projectItem.getId());
			if (ruleItem != null) {
				if (ruleItem.getSymbol() == 1) {// 1>= 2> 3<= 4< 5=
					if (jo.getDouble("achievement") >= ruleItem.getQualifiedPhysical()) {
						item.setAchievementGrade(2);
					} else {
						item.setAchievementGrade(1);
					}

				} else if (ruleItem.getSymbol() == 2) {
					if (jo.getDouble("achievement") > ruleItem.getQualifiedPhysical()) {
						item.setAchievementGrade(2);
					} else {
						item.setAchievementGrade(1);
					}
				} else if (ruleItem.getSymbol() == 3) {
					if (jo.getDouble("achievement") <= ruleItem.getQualifiedPhysical()) {
						item.setAchievementGrade(2);
					} else {
						item.setAchievementGrade(1);
					}
				} else if (ruleItem.getSymbol() == 4) {
					if (jo.getDouble("achievement") < ruleItem.getQualifiedPhysical()) {
						item.setAchievementGrade(2);
					} else {
						item.setAchievementGrade(1);
					}
				} else if (ruleItem.getSymbol() == 5) {
					if (jo.getDouble("achievement") == ruleItem.getQualifiedPhysical()) {
						item.setAchievementGrade(2);
					} else {
						item.setAchievementGrade(1);
					}
				}
			}
			trainService.trainDetailsPoliceUpdate(item);
			// 单项综合体能项目报名详情
			TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null,
					jo.getInteger("trainPhysicalId"), jo.getString("policeId"));
			aItem.setAchievementDate(new Date());
			aItem.setUpdateDate(new Date());
			// 查询当前用户成绩是否合格
			List<TrainPhysicalAchievementDetails> dlist = trainService.detailsFailList(jo.getInteger("trainPhysicalId"),
					jo.getString("policeId"));
			if (dlist.size() > 0) {
				aItem.setAchievementGrade(1);
			} else {
				aItem.setAchievementGrade(2);
			}
			if (signDate != null && signInt != null) {
				aItem.setSignDate(signDate);
				aItem.setIsSign(signInt);
			}
			trainService.trainPhysicalAchievementUpdate(aItem);
			if (aItem.getAchievementGrade() == 2) {
				// 警员奖章详情记录查询(训练新秀)
				TrainGetMedal getItem2 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 2);
				if (getItem2 == null) {
					TrainGetMedal gItem = new TrainGetMedal();
					gItem.setMedalId(2);
					gItem.setPoliceId(jo.getString("policeId"));
					gItem.setGetDate(new Date());
					gItem.setCreationDate(new Date());
					trainMatchService.trainGetMedalCreat(gItem);
				}
				// 警员奖章详情记录查询(训练小咖)
				TrainGetMedal getItem3 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 3);
				if (getItem3 == null) {
					// 查询任意5次训练活动各项成绩均合格
					int achCount = trainService.trainArbitrarilyFivePassCount(jo.getString("policeId"));
					if (achCount >= 5) {
						TrainGetMedal gItem = new TrainGetMedal();
						gItem.setMedalId(3);
						gItem.setPoliceId(jo.getString("policeId"));
						gItem.setGetDate(new Date());
						gItem.setCreationDate(new Date());
						trainMatchService.trainGetMedalCreat(gItem);
					}
				}
				// 警员奖章详情记录查询(训练达人)
				TrainGetMedal getItem4 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 4);
				if (getItem4 == null) {
					// 查询连续5次训练活动各项成绩均合格
					int achCount = trainService.trainContinuityFivePassCount(jo.getString("policeId"));
					if (achCount == 5) {
						TrainGetMedal gItem = new TrainGetMedal();
						gItem.setMedalId(4);
						gItem.setPoliceId(jo.getString("policeId"));
						gItem.setGetDate(new Date());
						gItem.setCreationDate(new Date());
						trainMatchService.trainGetMedalCreat(gItem);
					}
				}
				// 警员奖章详情记录查询(累计训练月)
				TrainGetMedal getItem5 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 5);
				if (getItem5 == null) {
					// 连续3个月参加训练，可获得该奖章
					int achCount = trainService.trainContinuityThreeMonthPassCount(jo.getString("policeId"));
					if (achCount == 3) {
						TrainGetMedal gItem = new TrainGetMedal();
						gItem.setMedalId(5);
						gItem.setPoliceId(jo.getString("policeId"));
						gItem.setGetDate(new Date());
						gItem.setCreationDate(new Date());
						trainMatchService.trainGetMedalCreat(gItem);
					}
				}

				// 警员奖章详情记录查询(训练标兵)
				TrainGetMedal getItem6 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 6);
				if (getItem6 == null) {// 任意1次训练活动各项成绩Top3，可获得该奖章
					// 根据policeId查询部门
					User user = userService.policeItem(policeId, null, null);
					// 参赛项目名称查询
					List<CalculationChart> projectList = trainService.trainProjectRankList(trainPhysicalId,
							aItem.getId(), policeId, null);
					int totalRankNum = projectList.size();
					int rankInt = 0;
					for (int i = 0; i < projectList.size(); i++) {
						String sort = null;
						if (projectList.get(i).getNum() == 1) {// 升序
							sort = "asc";
						} else if (projectList.get(i).getNum() == 2) {// 降序
							sort = "desc";
						}
						TrainRank personalRankItem = trainService.trainPersonalRankItem(projectList.get(i).getId(),
								trainPhysicalId, policeId, null, sort);
						if (personalRankItem.getRankId() != null || personalRankItem.getRankId() < 4) {
							rankInt = rankInt + 1;
						}
					}
					if (totalRankNum == rankInt) {
						TrainGetMedal gItem = new TrainGetMedal();
						gItem.setMedalId(6);
						gItem.setPoliceId(jo.getString("policeId"));
						gItem.setGetDate(new Date());
						gItem.setCreationDate(new Date());
						trainMatchService.trainGetMedalCreat(gItem);
					}
				}

				// 警员奖章详情记录查询(全能健将)
				TrainGetMedal getItem7 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 7);
				if (getItem7 == null) {// 连续3次，训练活动各项成绩Top3，可获得该奖章
					// 根据policeId查询部门
					User user = userService.policeItem(policeId, null, null);
					// 我的训练列表查询
					List<TrainPhysical> list = trainService.trainMyList(policeId, null, null, 3, 0);
					boolean isTrue = true;
					if (list.size() == 3) {
						for (int i = 0; i < list.size(); i++) {
							if (list.get(i).getObjectId() == 1) {// 综合体能
								// 参赛项目名称查询
								List<CalculationChart> projectList = trainService
										.trainProjectRankList(list.get(i).getId(), aItem.getId(), policeId, null);
								for (int k = 0; k < projectList.size(); k++) {
									String sort = null;
									if (projectList.get(k).getNum() == 1) {// 升序
										sort = "asc";
									} else if (projectList.get(k).getNum() == 2) {// 降序
										sort = "desc";
									}
									TrainRank personalRankItem = trainService.trainPersonalRankItem(
											projectList.get(k).getId(), list.get(i).getId(), policeId, null, sort);
									if (personalRankItem == null || personalRankItem.getRankId() == null
											|| personalRankItem.getRankId() > 3) {
										isTrue = false;
									}
								}
							} else if (list.get(i).getObjectId() == 2) {// 枪械
								// 参赛枪械项目名称查询
								List<CalculationChart> projectList = trainService
										.trainProjectFirearmRankList(list.get(i).getId(), policeId);
								String sort = null;
								if (projectList.get(0).getNum() == 1) {// 升序
									sort = "asc";
								} else if (projectList.get(0).getNum() == 2) {// 降序
									sort = "desc";
								}
								TrainRank personalRankItem = trainService.trainPersonalFirearmRankItem(
										projectList.get(0).getId(), list.get(i).getId(), policeId, null, sort);
								if (personalRankItem == null || personalRankItem.getRankId() == null
										|| personalRankItem.getRankId() > 3) {
									isTrue = false;
								}
							}
						}
					} else {
						isTrue = false;
					}
					if (isTrue == true) {
						TrainGetMedal gItem = new TrainGetMedal();
						gItem.setMedalId(7);
						gItem.setPoliceId(jo.getString("policeId"));
						gItem.setGetDate(new Date());
						gItem.setCreationDate(new Date());
						trainMatchService.trainGetMedalCreat(gItem);
					}
				}
			}
		}
		TrainPhysicalAchievementDetails ddItem = new TrainPhysicalAchievementDetails();
		ddItem.setTrainPhysicalId(trainPhysicalId);
		ddItem.setProjectId(projectId);
		ddItem.setAchievementDate(nowDate);
		trainService.achievementDateUpdate(ddItem);
		dlr.setStatus(true);
		dlr.setMessage("提交成功");
		dlr.setResult(1);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 枪械训练人员成绩录入
	@RequestMapping(value = { "/train/record/police/score/update" }, method = RequestMethod.POST)
	public ResponseEntity<?> trainRecordPoliceScoreUpdate(
			@RequestParam(value = "resultJson", required = false) String resultJson) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		System.out.println(resultJson);
//		String ss = "[{'policeId':'112225','projectId':2,'trainPhysicalId':18,'achievement':36.0,'achievementStr':'36.0个','shootTime':0.0,'isU':1},"
//				+ "{'policeId':'117409','projectId':2,'trainPhysicalId':18,'achievement':9.0,'achievementStr':'9.0个','shootTime':0.0,'isU':1}]";
		JSONArray json = (JSONArray) JSONArray.parse(resultJson);
		for (Object obj : json) {
			JSONObject jo = (JSONObject) obj;
			TrainFirearmAchievement item = new TrainFirearmAchievement();
			String policeId = jo.getString("policeId");
			Integer trainFirearmId = jo.getInteger("trainPhysicalId");
			item.setPoliceId(jo.getString("policeId"));
			item.setTrainProjectType(jo.getInteger("projectId"));
			item.setTrainFirearmId(jo.getInteger("trainPhysicalId"));
			if (jo.getInteger("isU") == 2) {
				if (jo.getDouble("shootTime") != 0.0 && jo.getDouble("shootTime") != 0) {
					item.setAchievement(jo.getDouble("achievement") / jo.getDouble("shootTime"));
					item.setAchievementStr(String.format("%.2f", item.getAchievement()) + "比值");
				} else {
					item.setAchievement(0.0);
					item.setAchievementStr(0.0 + "比值");
				}
				item.setAchievementFirst(jo.getInteger("achievement"));
				item.setAchievementSecond(jo.getDouble("shootTime"));
				item.setShootTime(jo.getDouble("shootTime"));
			} else {
				item.setAchievement(jo.getDouble("achievement"));
				item.setAchievementStr(jo.getString("achievementStr"));
			}
			item.setIsSubmit(1);
			TrainFirearmAchievement achievementItem = trainService.trainFirearmAchievementItem(null, trainFirearmId,
					policeId);
			if (achievementItem.getIsSign() == 1) {
				if (item.getAchievement() != null && item.getAchievement() > 0) {
					item.setIsSign(2);
					TrainFirearm trainFirearmItem = trainService.trainFirearmItem(trainFirearmId);
					item.setSignDate(trainFirearmItem.getTrainEndDate());
				}
			}
			item.setUpdateDate(new Date());
			item.setAchievementDate(new Date());
			// 根据项目id/组别查询算分规则
			TrainProjectRule ruleItem = trainService.trainProjectPoliceRuleItem(jo.getInteger("projectId"), null);
			if (ruleItem != null) {
				if (jo.getInteger("isU") == 1) {
					if (ruleItem.getSymbol() == 1) {// 1>= 2> 3<= 4< 5=
						if (jo.getDouble("achievement") >= ruleItem.getQualifiedFirearmA()) {
							item.setAchievementGrade(2);
						} else {
							item.setAchievementGrade(1);
						}
					} else if (ruleItem.getSymbol() == 2) {
						if (jo.getDouble("achievement") > ruleItem.getQualifiedFirearmA()) {
							item.setAchievementGrade(2);
						} else {
							item.setAchievementGrade(1);
						}
					} else if (ruleItem.getSymbol() == 3) {
						if (jo.getDouble("achievement") <= ruleItem.getQualifiedFirearmA()) {
							item.setAchievementGrade(2);
						} else {
							item.setAchievementGrade(1);
						}
					} else if (ruleItem.getSymbol() == 4) {
						if (jo.getDouble("achievement") < ruleItem.getQualifiedFirearmA()) {
							item.setAchievementGrade(2);
						} else {
							item.setAchievementGrade(1);
						}
					} else if (ruleItem.getSymbol() == 5) {
						if (jo.getDouble("achievement") == ruleItem.getQualifiedFirearmA()) {
							item.setAchievementGrade(2);
						} else {
							item.setAchievementGrade(1);
						}
					}
				} else if (jo.getInteger("isU") == 2) {
					if (jo.getDouble("shootTime") > ruleItem.getLimitTime()) {
						item.setAchievementGrade(1);
					} else {
						// 根据项目规则id查询U型靶成绩规则
						List<TrainProjectURule> list = trainService.TrainProjectURuleList(ruleItem.getId());
						double num = 0.0;
						if (jo.getDouble("shootTime") != 0.0 && jo.getDouble("shootTime") != 0) {
							num = jo.getDouble("achievement") / jo.getDouble("shootTime");
						}
						if (list.get(0).getMinNum() <= num && num <= list.get(0).getMaxNum()) {// 1小于2小于等于
							item.setAchievementGrade(1);
						} else if (list.get(1).getMinNum() < num && num < list.get(1).getMaxNum()) {
							item.setAchievementGrade(2);
						} else if (list.get(2).getMinNum() <= num && num < list.get(2).getMaxNum()) {
							item.setAchievementGrade(3);
						} else if (list.get(3).getMinNum() < num) {
							item.setAchievementGrade(4);
						}
					}
				}
			}
			trainService.trainFirearmAchievementPoliceUpdate(item);
			TrainFirearm trainFirearm = new TrainFirearm();
			trainFirearm.setId(jo.getInteger("trainPhysicalId"));
			trainFirearm.setIsSubmit(1);
			trainFirearm.setSubmitDate(new Date());
			trainFirearm.setUpdateDate(new Date());
			trainService.trainFirearmUpdate(trainFirearm);
			if (item.getAchievementGrade() >= 2) {
				// 警员奖章详情记录查询(训练新秀)
				TrainGetMedal getItem1 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 1);
				if (getItem1 == null) {
					// 查询连续5次，枪械训练各项成绩Top3，可获得该奖章
					List<TrainPhysical> cList = trainService.trainFirearmContinuityFivePassList(policeId);
					boolean fTure = true;
					if (cList.size() == 5) {
						for (int i = 0; i < cList.size(); i++) {
							// 根据policeId查询部门
							User user = userService.policeItem(policeId, null, null);
							// 参赛枪械项目名称查询
							List<CalculationChart> projectList = trainService
									.trainProjectFirearmRankList(cList.get(i).getId(), policeId);
							String sort = null;
							if (projectList.get(0).getNum() == 1) {// 升序
								sort = "asc";
							} else if (projectList.get(0).getNum() == 2) {// 降序
								sort = "desc";
							}
							TrainRank personalRankItem = trainService.trainPersonalFirearmRankItem(
									projectList.get(0).getId(), cList.get(i).getId(), policeId, null, sort);
							if (personalRankItem == null || personalRankItem.getRankId() == null
									|| personalRankItem.getRankId() > 3) {
								fTure = false;
							}
						}
					} else {
						fTure = false;
					}
					if (fTure == true) {
						TrainGetMedal gItem = new TrainGetMedal();
						gItem.setMedalId(1);
						gItem.setPoliceId(jo.getString("policeId"));
						gItem.setGetDate(new Date());
						gItem.setCreationDate(new Date());
						trainMatchService.trainGetMedalCreat(gItem);
					}
				}
				// 警员奖章详情记录查询(训练新秀)
				TrainGetMedal getItem2 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 2);
				if (getItem2 == null) {
					TrainGetMedal gItem = new TrainGetMedal();
					gItem.setMedalId(2);
					gItem.setPoliceId(jo.getString("policeId"));
					gItem.setGetDate(new Date());
					gItem.setCreationDate(new Date());
					trainMatchService.trainGetMedalCreat(gItem);
				}
				// 警员奖章详情记录查询(训练小咖)
				TrainGetMedal getItem3 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 3);
				if (getItem3 == null) {
					// 查询任意5次训练活动各项成绩均合格
					int achCount = trainService.trainArbitrarilyFivePassCount(jo.getString("policeId"));
					if (achCount >= 5) {
						TrainGetMedal gItem = new TrainGetMedal();
						gItem.setMedalId(3);
						gItem.setPoliceId(jo.getString("policeId"));
						gItem.setGetDate(new Date());
						gItem.setCreationDate(new Date());
						trainMatchService.trainGetMedalCreat(gItem);
					}
				}
				// 警员奖章详情记录查询(训练达人)
				TrainGetMedal getItem4 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 4);
				if (getItem4 == null) {
					// 查询连续5次训练活动各项成绩均合格
					int achCount = trainService.trainContinuityFivePassCount(jo.getString("policeId"));
					if (achCount == 5) {
						TrainGetMedal gItem = new TrainGetMedal();
						gItem.setMedalId(4);
						gItem.setPoliceId(jo.getString("policeId"));
						gItem.setGetDate(new Date());
						gItem.setCreationDate(new Date());
						trainMatchService.trainGetMedalCreat(gItem);
					}
				}
				// 警员奖章详情记录查询(累计训练月)
				TrainGetMedal getItem5 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 5);
				if (getItem5 == null) {
					// 连续3个月参加训练，可获得该奖章
					int achCount = trainService.trainContinuityThreeMonthPassCount(jo.getString("policeId"));
					if (achCount == 3) {
						TrainGetMedal gItem = new TrainGetMedal();
						gItem.setMedalId(5);
						gItem.setPoliceId(jo.getString("policeId"));
						gItem.setGetDate(new Date());
						gItem.setCreationDate(new Date());
						trainMatchService.trainGetMedalCreat(gItem);
					}
				}
				// 警员奖章详情记录查询(训练标兵)
				TrainGetMedal getItem6 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 6);
				if (getItem6 == null) {// 任意1次训练活动各项成绩Top3，可获得该奖章
					// 根据policeId查询部门
					User user = userService.policeItem(policeId, null, null);
					// 参赛枪械项目名称查询
					List<CalculationChart> projectList = trainService.trainProjectFirearmRankList(trainFirearmId,
							policeId);
					String sort = null;
					if (projectList.get(0).getNum() == 1) {// 升序
						sort = "asc";
					} else if (projectList.get(0).getNum() == 2) {// 降序
						sort = "desc";
					}
					TrainRank personalRankItem = trainService.trainPersonalRankItem(projectList.get(0).getId(),
							trainFirearmId, policeId, null, sort);
					if (personalRankItem != null && personalRankItem.getRankId() != null
							&& personalRankItem.getRankId() < 4) {
						TrainGetMedal gItem = new TrainGetMedal();
						gItem.setMedalId(6);
						gItem.setPoliceId(jo.getString("policeId"));
						gItem.setGetDate(new Date());
						gItem.setCreationDate(new Date());
						trainMatchService.trainGetMedalCreat(gItem);
					}
				}

				// 警员奖章详情记录查询(全能健将)
				TrainGetMedal getItem7 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 7);
				if (getItem7 == null) {// 连续3次，训练活动各项成绩Top3，可获得该奖章
					// 根据policeId查询部门
					User user = userService.policeItem(policeId, null, null);
					// 我的训练列表查询
					List<TrainPhysical> list = trainService.trainMyList(policeId, null, null, 3, 0);
					boolean isTrue = true;
					if (list.size() == 3) {
						for (int i = 0; i < list.size(); i++) {
							if (list.get(i).getObjectId() == 1) {// 综合体能
								// 单项综合体能项目报名详情
								TrainPhysicalAchievement atItem = trainService.trainPhysicalAchievementItem(null,
										list.get(i).getId(), policeId);
								// 参赛项目名称查询
								List<CalculationChart> projectList = trainService
										.trainProjectRankList(list.get(i).getId(), atItem.getId(), policeId, null);
								for (int k = 0; k < projectList.size(); k++) {
									String sort = null;
									if (projectList.get(k).getNum() == 1) {// 升序
										sort = "asc";
									} else if (projectList.get(k).getNum() == 2) {// 降序
										sort = "desc";
									}
									TrainRank personalRankItem = trainService.trainPersonalRankItem(
											projectList.get(k).getId(), list.get(i).getId(), policeId, null, sort);
									if (personalRankItem == null || personalRankItem.getRankId() == null
											|| personalRankItem.getRankId() > 3) {
										isTrue = false;
									}
								}
							} else if (list.get(i).getObjectId() == 2) {// 枪械
								// 参赛枪械项目名称查询
								List<CalculationChart> projectList = trainService
										.trainProjectFirearmRankList(list.get(i).getId(), policeId);
								String sort = null;
								if (projectList.get(0).getNum() == 1) {// 升序
									sort = "asc";
								} else if (projectList.get(0).getNum() == 2) {// 降序
									sort = "desc";
								}
								TrainRank personalRankItem = trainService.trainPersonalFirearmRankItem(
										projectList.get(0).getId(), list.get(i).getId(), policeId, null, sort);
								if (personalRankItem == null || personalRankItem.getRankId() == null
										|| personalRankItem.getRankId() > 3) {
									isTrue = false;
								}
							}
						}
					} else {
						isTrue = false;
					}
					if (isTrue == true) {
						TrainGetMedal gItem = new TrainGetMedal();
						gItem.setMedalId(7);
						gItem.setPoliceId(jo.getString("policeId"));
						gItem.setGetDate(new Date());
						gItem.setCreationDate(new Date());
						trainMatchService.trainGetMedalCreat(gItem);
					}
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("提交成功");
		dlr.setResult(1);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 领队训练列表查询
	@RequestMapping(value = "/train/leader/page/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainLeaderList(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "signUpStatus", required = false) Integer signUpStatus,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		TrainLeaderStatistics item = new TrainLeaderStatistics();
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		int total = 0;
		List<TrainPhysical> list = new ArrayList<TrainPhysical>();
		if (signUpStatus != null && signUpStatus == 2) {
			// 领队训练报名中列表查询
			list = trainService.trainLeaderSignUpList(policeId, signUpStatus, departmentId, pageSize, pageNum);
			// 领队训练报名中列表数量统计
			total = trainService.trainLeaderSignUpCount(policeId, signUpStatus, departmentId);
		} else {
			// 领队训练列表查询
			list = trainService.trainLeaderList(policeId, status, departmentId, pageSize, pageNum);
			// 领队训练列表数量统计
			total = trainService.trainLeaderCount(policeId, status, departmentId);
		}
		int total5 = trainService.trainLeaderSignUpCount(policeId, 2, departmentId);
		int total2 = trainService.trainLeaderCount(policeId, 1, departmentId);
		int total3 = trainService.trainLeaderCount(policeId, 2, departmentId);
		int total4 = trainService.trainLeaderCount(policeId, 3, departmentId);
		item.setSignUpNum(total5);
		item.setTrainNotStartedNum(total2);
		item.setTrainOngoingNum(total3);
		item.setTrainOverNum(total4);
		item.setTrainList(list);
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

	// 推荐人员信息查询
	@RequestMapping(value = "/train/recommend/police/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainRecommendPoliceList(
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (objectId == 1) {
			// 综合训练推荐人员信息查询
			List<TrainRecommendPolice> list = trainService.trainPhysicalRecommendPoliceList(trainPhysicalId,
					departmentId, policeId);
			dlr.setResult(list);
		} else if (objectId == 2) {
			// 枪械推荐人员信息查询
			List<TrainRecommendPolice> list = trainService.trainFirearmRecommendPoliceList(departmentId, policeId);
			dlr.setResult(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 部门参加人员信息查询
	@RequestMapping(value = "/train/dep/police/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainDepPoliceList(@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "keywords", required = false) String keywords) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (objectId == 1) {
			// 综合训练部门参加人员信息查询
			List<TrainRecommendPolice> list = trainService.trainPhysicalDepPoliceList(trainPhysicalId, departmentId,
					keywords);
			dlr.setResult(list);
		} else if (objectId == 2) {
			// 枪械部门参加人员信息查询
			List<TrainRecommendPolice> list = trainService.trainFirearmDepPoliceList(departmentId, keywords);
			dlr.setResult(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 领队综合训练报名
	@ResponseBody
	@RequestMapping(value = { "/train/physical/leader/sign/up/creat" }, method = RequestMethod.POST)
	public DataListReturn trainPhysicalLeaderSignUpCreat(
			@RequestParam(value = "policeNum", required = false) Integer policeNum,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "resultJson", required = false) String resultJson)
			throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		int count = 0;
		// 综合体能详情查询
		TrainPhysical tItem = trainService.trainPhysicalItem(trainPhysicalId);
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
		if (tItem.getIsLimit() == 1) {// 人数限制
			// 综合训练部门参加人员信息查询
			List<TrainRecommendPolice> list = trainService.trainPhysicalDepPoliceList(trainPhysicalId, departmentId,
					null);
			double totalNum = list.size();
			int needNum = 0;
			if (tItem.getLimitType() == 1) {// 按比例限制
				needNum = Integer.valueOf(String.format("%.0f", totalNum * tItem.getLimitNum()));
			} else if (tItem.getLimitType() == 2) {// 按人数限制
				needNum = Integer.valueOf(String.format("%.0f", tItem.getLimitNum()));
			}
			if (tItem.getSymbol() == 1) {// 1>= 2> 3<= 4< 5=
				if (policeNum < needNum) {
					dlr.setStatus(false);
					dlr.setMessage("参加人员大于等于" + needNum + "人才能报名");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 2) {
				if (policeNum <= needNum) {
					dlr.setStatus(false);
					dlr.setMessage("参加人员大于" + needNum + "人才能报名");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 3) {
				if (policeNum > needNum) {
					dlr.setStatus(false);
					dlr.setMessage("参加人员小于等于" + needNum + "人才能报名");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 4) {
				if (policeNum >= needNum) {
					dlr.setStatus(false);
					dlr.setMessage("参加人员小于" + needNum + "人才能报名");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 5) {
				if (policeNum != needNum) {
					dlr.setStatus(false);
					dlr.setMessage("参加人员等于" + needNum + "人才能报名");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			}

		}
//		String ss = "[{'policeId':'016236','trainPhysicalId':'23'}," 
//				+ "{'policeId':'113997','trainPhysicalId':'23'}]";
		JSONArray json = (JSONArray) JSONArray.parse(resultJson);
		for (Object obj : json) {
			JSONObject jo = (JSONObject) obj;
			String policeId = jo.getString("policeId");
			Integer id = jo.getInteger("trainPhysicalId");
			TrainPhysicalAchievement item = new TrainPhysicalAchievement();
			// 单项综合体能项目报名详情
			TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, id, policeId);
			if (aItem == null) {
				item.setTrainPhysicalId(id);
				item.setPoliceId(policeId);
				item.setRegistrationDate(new Date());
				// 查询组别民警
				List<TrainGroupPolice> groupList = trainService.trainGroupPoliceList(policeId);
				item.setTrainGroupId(groupList.get(0).getId());
				item.setIsSign(1);
				item.setCreationDate(new Date());
				// 查询当前民警所属组别中的综合体能项目
				List<TrainProject> projectList = trainService.trainPoliceBelongToList(id, groupList.get(0).getId());
				if (projectList.size() > 0) {
					String qrName = "physical-" + UUID.randomUUID().toString() + ".jpg";
					item.setQrCode(trainQRCode + qrName);
					// 生成二维码
					new QRCode(root + trainQRCodeIcon).encodeQRCode("1," + id + "," + policeId, 500, 500,
							root + trainQRCode + qrName);
					count = trainService.trainPhysicalAchievementCreat(item);
					for (int i = 0; i < projectList.size(); i++) {
						// 项目成绩新增
						TrainPhysicalAchievementDetails deItem = new TrainPhysicalAchievementDetails();
						deItem.setTrainPhysicalId(id);
						deItem.setTrainPhysicalAchievementId(item.getId());
						deItem.setPoliceId(policeId);
						deItem.setProjectId(projectList.get(i).getId());
						deItem.setIsEntry(1);
						deItem.setCreationDate(new Date());
						trainService.trainPhysicalAchievementDetailsCreat(deItem);
					}
				} else {
					dlr.setStatus(false);
					dlr.setMessage("当前组别下没有综合体能项目，请在后台配置");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else {
				dlr.setStatus(false);
				dlr.setMessage("您已报名该训练");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
		}
		if (count > 0) {
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

	// 领队枪械训练报名
	@ResponseBody
	@RequestMapping(value = { "/train/firearm/leader/sign/up/creat" }, method = RequestMethod.POST)
	public DataListReturn trainFirearmLeaderSignUpCreat(
			@RequestParam(value = "policeNum", required = false) Integer policeNum,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "resultJson", required = false) String resultJson)
			throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		int count = 0;
		// 枪械详情查询
		TrainFirearm tItem = trainService.trainFirearmItem(trainPhysicalId);
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
		if (tItem.getIsLimit() == 1) {// 人数限制
			// 枪械部门参加人员信息查询
			List<TrainRecommendPolice> list = trainService.trainFirearmDepPoliceList(departmentId, null);
			double totalNum = list.size();
			int needNum = 0;
			if (tItem.getLimitType() == 1) {// 按比例限制
				needNum = Integer.valueOf(String.format("%.0f", totalNum * tItem.getLimitNum()));
			} else if (tItem.getLimitType() == 2) {// 按人数限制
				needNum = Integer.valueOf(String.format("%.0f", tItem.getLimitNum()));
			}
			if (tItem.getSymbol() == 1) {// 1>= 2> 3<= 4< 5=
				if (policeNum < needNum) {
					dlr.setStatus(false);
					dlr.setMessage("参加人员大于等于" + needNum + "人才能报名");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 2) {
				if (policeNum <= needNum) {
					dlr.setStatus(false);
					dlr.setMessage("参加人员大于" + needNum + "人才能报名");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 3) {
				if (policeNum > needNum) {
					dlr.setStatus(false);
					dlr.setMessage("参加人员小于等于" + needNum + "人才能报名");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 4) {
				if (policeNum >= needNum) {
					dlr.setStatus(false);
					dlr.setMessage("参加人员小于" + needNum + "人才能报名");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 5) {
				if (policeNum != needNum) {
					dlr.setStatus(false);
					dlr.setMessage("参加人员等于" + needNum + "人才能报名");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			}
		}
//		String ss = "[{'policeId':'017107','trainPhysicalId':'4'}," + "{'policeId':'017090','trainPhysicalId':'4'},"
//				+ "{'policeId':'117095','trainPhysicalId':'4'}," + "{'policeId':'116823','trainPhysicalId':'4'},"
//				+ "{'policeId':'116442','trainPhysicalId':'4'}]";
		JSONArray json = (JSONArray) JSONArray.parse(resultJson);
		for (Object obj : json) {
			JSONObject jo = (JSONObject) obj;
			String policeId = jo.getString("policeId");
			Integer id = jo.getInteger("trainPhysicalId");
			TrainFirearmAchievement item = new TrainFirearmAchievement();
			// 枪械项目报名详情
			TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, id, policeId);
			if (fItem == null) {
				item.setTrainFirearmId(id);
				item.setPoliceId(policeId);
				item.setRegistrationDate(new Date());
				item.setIsSign(1);
				item.setAchievementGrade(1);
				item.setIsSubmit(0);
				item.setTrainProjectType(tItem.getTrainFirearmType());
				item.setCreationDate(new Date());
				String qrName = "physical-" + UUID.randomUUID().toString() + ".jpg";
				item.setQrCode(trainQRCode + qrName);
				// 生成二维码
				new QRCode(root + trainQRCodeIcon).encodeQRCode("1," + id + "," + policeId, 500, 500,
						root + trainQRCode + qrName);
				count = trainService.trainFirearmAchievementCreat(item);
			} else {
				dlr.setStatus(false);
				dlr.setMessage("您已报名该训练");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
		}
		if (count > 0) {
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

	// 领队报名成功页面查询
	@RequestMapping(value = "/train/leader/sign/up/success/Item", method = RequestMethod.GET)
	public ResponseEntity<?> trainLeaderSignUpSuccessItem(
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainLeaderSignUpSuccess item = new TrainLeaderSignUpSuccess();
		if (objectId == 1) {
			TrainPhysical fPhysical = trainService.trainPhysicalItem(trainPhysicalId);
			// 领队综合训练人员成功查询
			List<TrainRecommendPolice> list = trainService.trainPhysicalLeaderSignUpSuccessList(trainPhysicalId,
					departmentId);
			item.setName(fPhysical.getName());
			item.setPlace(fPhysical.getPlace());
			item.setStatus(fPhysical.getStatus());
			item.setPoliceNum(list.size());
			String timeString = getDatePoor(fPhysical.getTrainEndDate(), new Date());
			item.setCreationDateStr(timeString);
			item.setPoliceList(list);
			dlr.setResult(item);
		} else if (objectId == 2) {
			TrainFirearm fPhysical = trainService.trainFirearmItem(trainPhysicalId);
			// 领队枪械报名成功查询
			List<TrainRecommendPolice> list = trainService.trainFirearmLeaderSignUpSuccessList(trainPhysicalId,
					departmentId);
			item.setName(fPhysical.getName());
			item.setPlace(fPhysical.getPlace());
			item.setStatus(fPhysical.getStatus());
			String timeString = getDatePoor(fPhysical.getTrainEndDate(), new Date());
			item.setCreationDateStr(timeString);
			item.setPoliceNum(list.size());
			item.setPoliceList(list);
			dlr.setResult(item);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 领队训练成绩查询
	@RequestMapping(value = "/train/leader/achievement/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainLeaderAchievementList(
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (objectId == 1) {
			// 领队综合训练成绩查询
			TrainLeaderAchievement aItem = trainService.trainPhysicalLeaderAchievemenItem(trainPhysicalId,
					departmentId);
			if (aItem.getSignUpRate() == 0) {
				aItem.setFailRate(0);
				aItem.setPassRate(0);
			}
			dlr.setResult(aItem);
		} else if (objectId == 2) {
			// 领队枪械成绩查询
			TrainLeaderAchievement aItem = trainService.trainFirearmLeaderAchievemenItem(trainPhysicalId, departmentId);
			if (aItem.getSignUpRate() == 0) {
				aItem.setFailRate(0);
				aItem.setPassRate(0);
			}
			dlr.setResult(aItem);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 领队训练成绩详情查询
	@RequestMapping(value = "/train/leader/more/achievement/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainLeaderMoreAchievementList(
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "projectId", required = false) Integer projectId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainLeaderMoreAchievement item = new TrainLeaderMoreAchievement();
		if (objectId == 1) {
			if (projectId == 1) {
				// 领队综合训练已签到人员查询
				List<TrainRecommendPolice> list1 = trainService.signMoreAchievementList(trainPhysicalId, departmentId);
				item.setYesNum(list1.size());
				item.setYesList(list1);
				// 领队综合训练未签到人员查询
				List<TrainRecommendPolice> list2 = trainService.noSignMoreAchievementList(trainPhysicalId,
						departmentId);
				item.setNoNum(list2.size());
				item.setNoList(list2);
				item.setTotalNum(list1.size() + list2.size());
			} else if (projectId == 4) {
				// 领队综合训练合格人员查询
				List<TrainRecommendPolice> passList = trainService.passMoreAchievementList(trainPhysicalId,
						departmentId);
				// 领队综合训练不合格人员查询
				List<TrainRecommendPolice> failList = trainService.failMoreAchievementList(trainPhysicalId,
						departmentId);
				item.setYesList(passList);
				item.setYesNum(passList.size());
				item.setTotalNum(passList.size() + failList.size());
			} else if (projectId == 5) {
				// 领队综合训练合格人员查询
				List<TrainRecommendPolice> passList = trainService.passMoreAchievementList(trainPhysicalId,
						departmentId);
				// 领队综合训练不合格人员查询
				List<TrainRecommendPolice> failList = trainService.failMoreAchievementList(trainPhysicalId,
						departmentId);
				item.setYesList(failList);
				item.setYesNum(failList.size());
				item.setTotalNum(passList.size() + failList.size());
			}
		} else if (objectId == 2) {
			// 领队枪械已签到人员查询
			List<TrainRecommendPolice> list1 = trainService.signFirearmMoreAchievementList(trainPhysicalId,
					departmentId);
			// 领队枪械未签到人员查询
			List<TrainRecommendPolice> list2 = trainService.noSignFirearmMoreAchievementList(trainPhysicalId,
					departmentId);
			item.setTotalNum(list1.size() + list2.size());
			if (projectId == 1) {// 1签到率
				item.setYesNum(list1.size());
				item.setYesList(list1);
				item.setNoNum(list2.size());
				item.setNoList(list2);
			} else if (projectId == 2) {// 2优秀率
				// 领队枪械优秀人员查询
				List<TrainRecommendPolice> goodList = trainService.passFirearmMoreAchievementList(trainPhysicalId,
						departmentId, 4);
				item.setYesNum(goodList.size());
				item.setYesList(goodList);
			} else if (projectId == 3) {// 3良好率
				// 领队枪械良好人员查询
				List<TrainRecommendPolice> justList = trainService.passFirearmMoreAchievementList(trainPhysicalId,
						departmentId, 3);
				item.setYesNum(justList.size());
				item.setYesList(justList);
			} else if (projectId == 4) {// 4合格率
				// 领队枪械合格人员查询
				List<TrainRecommendPolice> passList = trainService.passFirearmMoreAchievementList(trainPhysicalId,
						departmentId, 2);
				item.setYesNum(passList.size());
				item.setYesList(passList);
			} else if (projectId == 5) {// 5不合格率
				// 领队综合训练不合格人员查询
				List<TrainRecommendPolice> failList = trainService.failFirearmMoreAchievementList(trainPhysicalId,
						departmentId);
				item.setYesNum(failList.size());
				item.setYesList(failList);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 领队实时排行榜查询
	@RequestMapping(value = "/train/leader/time/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainLeaderTimeRankList(
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<TrainRankList> list = new ArrayList<TrainRankList>();
		Integer limitNum = 10;
		if (objectId == 1) {
			CalculationChart itemChart1 = new CalculationChart();
			TrainRankList rankItem1 = new TrainRankList();
			// 领队综合体能签到率排行榜查询
			List<TrainRank> sList1 = trainService.trainLeaderPhysicalSignUpRateRankList(trainPhysicalId, limitNum);
			itemChart1.setName("签到率");
			itemChart1.setId(1);
			rankItem1.setProjectItem(itemChart1);
			rankItem1.setRankList(sList1);
			list.add(rankItem1);
			TrainRankList rankItem2 = new TrainRankList();
			CalculationChart itemChart2 = new CalculationChart();
			// 领队综合体能合格率排行榜查询
			List<TrainRank> sList2 = trainService.trainLeaderPhysicalPassRateRankList(trainPhysicalId, limitNum);
			itemChart2.setName("合格率");
			itemChart2.setId(4);
			rankItem2.setProjectItem(itemChart2);
			rankItem2.setRankList(sList2);
			list.add(rankItem2);
			TrainRankList rankItem3 = new TrainRankList();
			CalculationChart itemChart3 = new CalculationChart();
			// 领队综合体能不合格率排行榜查询
			List<TrainRank> sList3 = trainService.trainLeaderPhysicalFailRateRankList(trainPhysicalId, limitNum);
			itemChart3.setName("不合格率");
			itemChart3.setId(5);
			rankItem3.setProjectItem(itemChart3);
			rankItem3.setRankList(sList3);
			list.add(rankItem3);
			dlr.setResult(list);
		} else if (objectId == 2) {
			TrainFirearm fPhysical = trainService.trainFirearmItem(trainPhysicalId);
			TrainProject project = trainService.getTrainProjectById(fPhysical.getTrainFirearmType());
			// 领队枪械签到率排行榜查询
			CalculationChart itemChart1 = new CalculationChart();
			TrainRankList rankItem1 = new TrainRankList();
			List<TrainRank> sList1 = trainService.trainLeaderFirearmSignUpRateRankList(trainPhysicalId, limitNum);
			itemChart1.setName("签到率");
			itemChart1.setId(1);
			rankItem1.setProjectItem(itemChart1);
			rankItem1.setRankList(sList1);
			list.add(rankItem1);
			if (project.getIsU() == 2) {
				// 领队枪械优秀率排行榜查询(2合格3良好4优秀)
				TrainRankList rankItem2 = new TrainRankList();
				CalculationChart itemChart2 = new CalculationChart();
				List<TrainRank> sList2 = trainService.trainLeaderFirearmGoodRateRankList(trainPhysicalId, 4, limitNum);
				itemChart2.setName("优秀率");
				itemChart2.setId(2);
				rankItem2.setProjectItem(itemChart2);
				rankItem2.setRankList(sList2);
				list.add(rankItem2);
				// 领队枪械良好率排行榜查询
				TrainRankList rankItem3 = new TrainRankList();
				CalculationChart itemChart3 = new CalculationChart();
				List<TrainRank> sList3 = trainService.trainLeaderFirearmGoodRateRankList(trainPhysicalId, 3, limitNum);
				itemChart3.setName("良好率");
				itemChart3.setId(3);
				rankItem3.setProjectItem(itemChart3);
				rankItem3.setRankList(sList3);
				list.add(rankItem3);
			}
			// 领队枪械合格率排行榜查询
			TrainRankList rankItem4 = new TrainRankList();
			CalculationChart itemChart4 = new CalculationChart();
			List<TrainRank> sList4 = trainService.trainLeaderFirearmGoodRateRankList(trainPhysicalId, 2, limitNum);
			itemChart4.setName("合格率");
			itemChart4.setId(4);
			rankItem4.setProjectItem(itemChart4);
			rankItem4.setRankList(sList4);
			list.add(rankItem4);
			// 领队枪械不合格率排行榜查询
			TrainRankList rankItem5 = new TrainRankList();
			CalculationChart itemChart5 = new CalculationChart();
			List<TrainRank> sList5 = trainService.trainLeaderFirearmFailRateRankList(trainPhysicalId, limitNum);
			itemChart5.setName("不合格率");
			itemChart5.setId(5);
			rankItem5.setProjectItem(itemChart5);
			rankItem5.setRankList(sList5);
			list.add(rankItem5);
			dlr.setResult(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 领队榜单项目查询
	@RequestMapping(value = "/train/leader/entry/project/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainLeaderEntryProjectList(
			@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<CalculationChart> projectList = new ArrayList<CalculationChart>();
		CalculationChart item1 = new CalculationChart();
		item1.setId(1);
		item1.setName("签到率");
		projectList.add(item1);
		CalculationChart item4 = new CalculationChart();
		item4.setId(4);
		item4.setName("合格率");
		CalculationChart item5 = new CalculationChart();
		item5.setId(5);
		item5.setName("不合格率");
		if (objectId == 2) {// 2枪械
			TrainFirearm fPhysical = trainService.trainFirearmItem(trainPhysicalId);
			TrainProject project = trainService.getTrainProjectById(fPhysical.getTrainFirearmType());
			if (project.getIsU() == 2) {
				CalculationChart item2 = new CalculationChart();
				item2.setId(2);
				item2.setName("优秀率");
				projectList.add(item2);
				CalculationChart item3 = new CalculationChart();
				item3.setId(3);
				item3.setName("良好率");
				projectList.add(item3);
			}
		}
		projectList.add(item4);
		projectList.add(item5);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(projectList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 领队更多榜单查询
	@RequestMapping(value = "/train/leader/more/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainLeaderMoreRankList(
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "projectId", required = false) Integer projectId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		Integer limitNum = 100000;
		List<TrainRank> list = new ArrayList<TrainRank>();
		if (objectId == 1) {
			if (projectId == 1) {// 签到率
				// 领队综合体能签到率排行榜查询
				list = trainService.trainLeaderPhysicalSignUpRateRankList(trainPhysicalId, limitNum);
			} else if (projectId == 4) {// 合格率
				// 领队综合体能合格率排行榜查询
				list = trainService.trainLeaderPhysicalPassRateRankList(trainPhysicalId, limitNum);
			} else if (projectId == 5) {// 不合格率
				// 领队综合体能不合格率排行榜查询
				list = trainService.trainLeaderPhysicalFailRateRankList(trainPhysicalId, limitNum);
			}
		} else if (objectId == 2) {
			if (projectId == 1) {
				// 领队枪械签到率排行榜查询
				list = trainService.trainLeaderFirearmSignUpRateRankList(trainPhysicalId, limitNum);
			} else if (projectId == 2) {
				// 领队枪械优秀率排行榜查询(2合格3良好4优秀)
				list = trainService.trainLeaderFirearmGoodRateRankList(trainPhysicalId, 4, limitNum);
			} else if (projectId == 3) {
				// 领队枪械良好率排行榜查询
				list = trainService.trainLeaderFirearmGoodRateRankList(trainPhysicalId, 3, limitNum);
			} else if (projectId == 4) {
				// 领队枪械合格率排行榜查询
				list = trainService.trainLeaderFirearmGoodRateRankList(trainPhysicalId, 2, limitNum);
			} else if (projectId == 5) {
				// 领队枪械不合格率排行榜查询
				list = trainService.trainLeaderFirearmFailRateRankList(trainPhysicalId, limitNum);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 是否是领队判断查询
	@RequestMapping(value = "/train/is/leader/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainIsLeaderItem(
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 是否是领队判断查询
		TrainLeader item = trainService.trainLeaderItem(null, policeId, departmentId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 是否是记分员判断查询
	@RequestMapping(value = "/train/is/scorer/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainIsScorerItem(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 是否是记分员判断查询
		List<TrainLeader> list = trainService.trainIsScorerList(policeId);
		TrainLeader item = new TrainLeader();
		if (list.size() > 0) {
			item.setLeaderId(policeId);
			dlr.setResult(item);
		} else {
			dlr.setResult(null);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 领队报名人员查询
	@RequestMapping(value = "/train/applicants/leader/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainApplicantsLeaderList(
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		String tableName = null;
		String tableId = null;
		if (objectId == 1) {
			tableName = "train_physical_achievement";
			tableId = "a.train_physical_id";
		} else if (objectId == 2) {
			tableName = "train_firearm_achievement";
			tableId = "a.train_firearm_id";
		}
		// 领队报名人员查询
		List<TrainPhysicalAchievement> list = trainService.trainApplicantsLeaderList(trainPhysicalId,
				user.getDepartmentId(), tableName, tableId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 统计分析总体概览查询
	@RequestMapping(value = "/train/statistics/analysis/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainStatisticsAnalysisItem(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		Integer limitNum = 10000;
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		// 训练总体数据统计分析
		TrainStatisticsAnalysis item = trainService.trainTotalStatistics(user.getDepartmentId());
		// 训练总体数据最近部分统计分析
		TrainStatisticsAnalysis titem = trainService.trainTotalLastStatistics(user.getDepartmentId());
		item.setDepRecentTrainPassRate(titem.getDepRecentTrainPassRate());
		List<Department> departmentList = departmentService.departmentList(user.getDepartmentId());
		item.setDepartmentName(departmentList.get(0).getName());
		// 查询最近一次分局训练
		TrainPhysical physicalItem = trainService.trainRecentPhysicalItem();
		if (physicalItem != null) {
			if (physicalItem.getObjectId() == 1) {// 综合体能
				// 查询最近一次分局综合训练合格率
				TrainStatisticsAnalysis aitem = trainService.trainPhysicalLastStatistics(physicalItem.getId(),
						user.getDepartmentId());
				item.setOfficeRecentTrainPassRate(aitem.getOfficeRecentTrainPassRate());// 最近一次单位训练总体合格率
				// 领队综合体能合格率排行榜查询
				List<TrainRank> sList2 = trainService.trainLeaderPhysicalPassRateRankList(physicalItem.getId(),
						limitNum);
				if (sList2.size() > 0) {
					for (int i = 0; i < sList2.size(); i++) {
						if (sList2.get(i).getDepartmentName().equals(departmentList.get(0).getName())) {
							item.setOfficeRecentTrainRank(sList2.get(i).getRankId());// 最近一次参加分局训练名次
						}
					}
				}
			} else if (physicalItem.getObjectId() == 2) {// 枪械
				// 查询最近一次分局枪械合格率
				TrainStatisticsAnalysis aitem = trainService.trainFirearmLastStatistics(physicalItem.getId(),
						user.getDepartmentId());
				item.setOfficeRecentTrainPassRate(aitem.getOfficeRecentTrainPassRate());// 最近一次单位训练总体合格率
				// 领队枪械合格率排行榜查询
				List<TrainRank> sList4 = trainService.trainLeaderFirearmGoodRateRankList(physicalItem.getId(), 2,
						limitNum);
				if (sList4.size() > 0) {
					for (int i = 0; i < sList4.size(); i++) {
						if (sList4.get(i).getDepartmentName().equals(departmentList.get(0).getName())) {
							item.setOfficeRecentTrainRank(sList4.get(i).getRankId());// 最近一次参加分局训练名次
						}
					}
				}
			}
		}
		// 比赛总体数据统计分析
		TrainStatisticsAnalysis mitem = trainService.matchTotalStatistics(user.getDepartmentId());
		item.setDepMatchNum(mitem.getDepMatchNum());// 单位赛事次数
		item.setDepMatchAveNum(mitem.getDepMatchAveNum());// 单位赛事每月平均次数
		item.setOfficeMatchNum(mitem.getOfficeMatchNum());// 分局赛事次数
		item.setOfficeMatchAveNum(mitem.getOfficeMatchAveNum());// 分局赛事每月平均次数
		item.setOfficeMatchSignRate(mitem.getOfficeMatchSignRate());// 分局赛事签到率
		item.setDepRecentMatchNum(mitem.getDepRecentMatchNum());// 近一月单位组织赛事次数
		item.setOfficeRecentMatchSignRate(mitem.getOfficeRecentMatchSignRate());// 近一月参加分局赛事平均签到率
		// 查询分局赛事
		List<TrainMatch> listMatchs = trainMatchService.trainOfficeMatchList();
		for (int i = 0; i < listMatchs.size(); i++) {
			// 比赛领队排行榜查询
			List<TrainRank> rankLists = trainMatchService.matchLeaderRankList(listMatchs.get(i).getId(), 10000);
			for (int j = 0; j < rankLists.size(); j++) {
				if (rankLists.get(j).getRankId() != null && rankLists.get(j).getRankId() == 1
						&& rankLists.get(j).getDepartmentName().equals(departmentList.get(0).getName())) {
					item.setRankId(1);
					item.setMatchName(listMatchs.get(i).getName());
					break;
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 单位组织训练次数趋势图(近12个月)
	@RequestMapping(value = "/train/dep/chart", method = RequestMethod.GET)
	public ResponseEntity<?> trainDepChart(@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "objectId", required = false) Integer objectId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (objectId == null || objectId == 1) {
			// 综合训练次数趋势图(近12个月)
			List<CalculationChart> list = trainService.trainDepPhysicalChart(departmentId);
			dlr.setResult(list);
		} else if (objectId == 2) {
			// 枪械次数趋势图(近12个月)
			List<CalculationChart> list = trainService.trainDepFirearmChart(departmentId);
			dlr.setResult(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 单位组织赛事次数趋势图(近12个月)
	@RequestMapping(value = "/match/dep/chart", method = RequestMethod.GET)
	public ResponseEntity<?> matchDepChart(@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "nature", required = false) Integer nature) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 单位组织赛事次数趋势图(近12个月)
		List<CalculationChart> list = trainService.matchDepChart(departmentId, nature);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 单位组织训练类型饼图
	@RequestMapping(value = "/train/dep/type/chart", method = RequestMethod.GET)
	public ResponseEntity<?> trainDepTypeChart(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 单位组织训练类型饼图
		List<CalculationChart> list = trainService.trainDepTypeChart(departmentId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 单位组织赛事类型饼图
	@RequestMapping(value = "/match/dep/type/chart", method = RequestMethod.GET)
	public ResponseEntity<?> matchDepTypeChart(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 单位组织赛事类型饼图
		List<CalculationChart> list = trainService.matchDepTypeChart(departmentId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员参加训练/赛事次数查询
	@RequestMapping(value = "/train/depart/police/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainDepartPoliceList(
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "type", required = false) Integer type) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (type == null || type == 1) {// 训练
			// 警员参加训练次数查询
			List<TrainRecommendPolice> list = trainService.trainDepPoliceList(departmentId);
			dlr.setResult(list);
		} else if (type == 2) {// 赛事
			// 警员参加赛事次数查询
			List<TrainRecommendPolice> list = trainService.matchDepPoliceList(departmentId);
			dlr.setResult(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 获得综合体能数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_list", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPhysicalList(@Param("departmentId") Integer departmentId,
			@Param("status") Integer status, @Param("reprotTime") String reprotTime, @Param("signTime") String signTime,
			@Param("keyWords") String keyWords, @Param("pageSize") Integer pageSize) {
		pageSize = null == pageSize ? 1 : pageSize;
		List<TrainPhysical> trainPhysicalList = trainService.getTrainPhysicalList(departmentId, status, reprotTime,
				signTime, keyWords, (pageSize - 1) * 10);
		for (TrainPhysical trainPhysical : trainPhysicalList) {
			// 根据group_ids查询组别
			trainPhysical.setTrainGroupNames(trainService.getTrainGroupByIds(trainPhysical.getTrainGroupIds()));
		}
		// 查询数量
		int count = trainService.getTrainPhysicalCount(departmentId, status, reprotTime, signTime, keyWords);
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainPhysicalList", trainPhysicalList);
		map.put("count", count);
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 获得枪械数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_firearm_list", method = RequestMethod.POST)
	public ResponseEntity<?> getFirearmList(@Param("status") Integer status,
			@Param("departmentId") Integer departmentId, @Param("reprotTime") String reprotTime,
			@Param("signTime") String signTime, @Param("keyWords") String keyWords,
			@Param("pageSize") Integer pageSize) {
		pageSize = null == pageSize ? 1 : pageSize;
		List<TrainFirearm> firearmList = trainService.getTrainFirearmList(departmentId, status, reprotTime, signTime,
				keyWords, (pageSize - 1) * 10);
		// 查询
		int count = trainService.getTrainFirearmCount(departmentId, status, reprotTime, signTime, keyWords);
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("firearmList", firearmList);
		map.put("count", count);
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 赛事管理查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_match_list", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainMatchList(@Param("departmentId") Integer departmentId, @Param("type") Integer type,
			@Param("status") Integer status, @Param("registrationStartDate") String registrationStartDate,
			@Param("registrationEndDate") String registrationEndDate, @Param("trainStartDate") String trainStartDate,
			@Param("trainEndDate") String trainEndDate, @Param("keyWords") String keyWords,
			@Param("pageSize") Integer pageSize) {

		pageSize = null == pageSize ? 1 : pageSize;

		List<TrainMatch> trainMatchList = trainService.getTrainMatchList(departmentId, type, status,
				registrationStartDate, registrationEndDate, trainStartDate, trainEndDate, keyWords,
				(pageSize - 1) * 10);

		int count = trainService.getTrainMatchCount(departmentId, type, status, registrationStartDate,
				registrationEndDate, trainStartDate, trainEndDate, keyWords);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainMatchList", trainMatchList);
		map.put("count", count);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 组别管理
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_group_list", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainGroupList(@Param("sex") Integer sex, @Param("minAge") Integer minAge,
			@Param("maxAge") Integer maxAge, @Param("keyWords") String keyWords, @Param("pageSize") Integer pageSize) {

		pageSize = null == pageSize ? 1 : pageSize;

		List<TrainGroup> trainGroupList = trainService.getTrainGroupList(sex, minAge, maxAge, keyWords,
				(pageSize - 1) * 10);

		int count = trainService.getTrainGroupCount(sex, minAge, maxAge, keyWords);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainGroupList", trainGroupList);
		map.put("count", count);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 训练项目管理
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_project_list", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPhysicalList(@Param("keyWords") String keyWords,
			@Param("pageSize") Integer pageSize) {

		pageSize = null == pageSize ? 1 : pageSize;

		List<TrainProject> trainProjectList = trainService.getTrainProjectList(keyWords, (pageSize - 1) * 10);

		int count = trainService.getTrainProjectCount(keyWords);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainProjectList", trainProjectList);
		map.put("count", count);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 记分员配置
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/train/train_scorer_list", method =
	 * RequestMethod.POST) public ResponseEntity<?>
	 * getTrainScorerList(@Param("keyWords") String keyWords,
	 * 
	 * @Param("pageSize") Integer pageSize) {
	 * 
	 * pageSize = null == pageSize ? 1 : pageSize;
	 * 
	 * List<TrainScorer> trainScorerList = trainService.getTrainScorerList(keyWords,
	 * (pageSize - 1) * 10);
	 * 
	 * int count = trainService.getTrainScorerCount(keyWords);
	 * 
	 * LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
	 * map.put("trainScorerList", trainScorerList); map.put("count", count);
	 * 
	 * DataListReturn dataListReturn = new DataListReturn();
	 * dataListReturn.setCode(StatusCode.getSuccesscode());
	 * dataListReturn.setMessage("success"); dataListReturn.setStatus(true);
	 * dataListReturn.setResult(map);
	 * 
	 * return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	 * 
	 * }
	 */

	/**
	 * 项目规则管理
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_project_rule_list", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainProjectRuleList(@Param("keyWords") String keyWords,
			@Param("pageSize") Integer pageSize) {

		pageSize = null == pageSize ? 1 : pageSize;

		List<TrainProjectRule> trainProjectRuleList = trainService.getTrainProjectRuleList(keyWords,
				(pageSize - 1) * 10);

		int count = trainService.getTrainProjectRuleCount(keyWords);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainProjectRuleList", trainProjectRuleList);
		map.put("count", count);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 奖章管理
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_medal_list", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainMedalList(@Param("type") Integer type, @Param("keyWords") String keyWords,
			@Param("sort") Integer sort, @Param("pageSize") Integer pageSize) {

		pageSize = null == pageSize ? 1 : pageSize;

		List<TrainMedalManage> trainMedalList = trainService.getTrainMedalList(type, keyWords, sort,
				(pageSize - 1) * 10);

		int count = trainService.getTrainMedalCount(type, keyWords);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainMedalList", trainMedalList);
		map.put("count", count);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 活动风采
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_activity_style_list", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainActivityStyle(@Param("status") Integer status,
			@Param("isRecommend") Integer isRecommend, @Param("keyWords") String keyWords, @Param("sort") Integer sort,
			@Param("pageSize") Integer pageSize) {

		pageSize = null == pageSize ? 1 : pageSize;

		List<TrainActivityStyle> trainActivityStyleList = trainService.getTrainActivityStyleList(status, isRecommend,
				keyWords, sort, (pageSize - 1) * 10);

		Integer count = trainService.getTrainActivityStyleCount(status, isRecommend, keyWords);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainActivityStyleList", trainActivityStyleList);
		map.put("count", count);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 训练标兵
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_pacesetter_list", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPacesetter(@Param("status") Integer status,
			@Param("isRecommend") Integer isRecommend, @Param("keyWords") String keyWords, @Param("sort") Integer sort,
			@Param("pageSize") Integer pageSize) {

		pageSize = null == pageSize ? 1 : pageSize;

		List<TrainPacesetter> trainPacesetterList = trainService.getTrainPacesetterList(status, isRecommend, keyWords,
				sort, (pageSize - 1) * 10);

		Integer count = trainService.getTrainPacesetterCount(status, isRecommend, keyWords);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainPacesetterList", trainPacesetterList);
		map.put("count", count);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 训练章程
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_constitution_list", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainConstitution(@Param("status") Integer status,
			@Param("isRecommend") Integer isRecommend, @Param("keyWords") String keyWords, @Param("sort") Integer sort,
			@Param("pageSize") Integer pageSize) {

		pageSize = null == pageSize ? 1 : pageSize;

		List<TrainConstitution> trainConstitutionList = trainService.getTrainConstitutionList(status, isRecommend,
				keyWords, sort, (pageSize - 1) * 10);

		Integer count = trainService.getTrainConstitutionCount(status, isRecommend, keyWords);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainConstitutionList", trainConstitutionList);
		map.put("count", count);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获取换算单位
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_unit_list", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainUnit() {

		List<TrainUnit> trainUnitList = trainService.getTrainUnitList();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainUnitList);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获取所有组别name和id
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_group_name", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainGroupName() {

		List<TrainGroup> trainGroupName = trainService.getTrainGroupNameId();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainGroupName);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 训练项目管理查询项目名 id 是否U型
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_project_name", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainProjectNameIdIsU() {

		List<TrainProject> trainProjectList = trainService.getTrainProjectNameIdIsU();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainProjectList);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获得所有组别对应的项目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_group_project", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainGroupProject() {

		List<TrainProjectRule> trainGroPro = trainService.getGroAndPro();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainGroPro);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获得所有射击类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_firearm_type", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainFirearmType() {

		List<TrainProjectRule> trainFirearmType = trainService.getTrainFirearmType();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainFirearmType);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 新增综合体能训练
	 * 
	 * @param name
	 * @param place
	 * @param registrationStartDate
	 * @param registrationEndDate
	 * @param trainStartDate
	 * @param trainEndDate
	 * @param type
	 * @param trainContent
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/train/train_add_physical", method = RequestMethod.POST)
	public ResponseEntity<?> addTrainPhysical(@Param("name") String name, @Param("place") String place,
			@Param("departmentId") Integer departmentId, @Param("registrationStartDate") String registrationStartDate,
			@Param("registrationEndDate") String registrationEndDate, @Param("trainStartDate") String trainStartDate,
			@Param("trainEndDate") String trainEndDate, @Param("isLimit") Integer isLimit,
			@Param("templateImg") String templateImg, @Param("limitType") Integer limitType,
			@Param("limitNum") Double limitNum, @Param("scorer") String scorer, @Param("type") Integer type,
			@Param("trainContent") String trainContent, @Param("json") String json, HttpServletRequest request) {

		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile coverImgFile = multipart.getFile("coverImg");
		MultipartFile trainImgFile = multipart.getFile("trainImg");

		String coverImgFileName = null;
		String trainImgFileName = null;

		try {

			if (null != coverImgFile.getOriginalFilename() && !"".equals(coverImgFile.getOriginalFilename())) {

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

			if (null != trainImgFile.getOriginalFilename() && !"".equals(trainImgFile.getOriginalFilename())) {

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

		TrainPhysical physical = new TrainPhysical();
		physical.setName(name);
		physical.setPlace(place);
//		physical.setRegistrationStartDate(DateUtil.parseDateTime(registrationStartDate + " 00:00:00"));
//		physical.setRegistrationEndDate(DateUtil.parseDateTime(registrationEndDate + " 59:59:59"));
//		physical.setTrainStartDate(DateUtil.parseDateTime(trainStartDate + " 00:00:00"));
//		physical.setTrainEndDate(DateUtil.parseDateTime(trainEndDate + " 59:59:59"));

		physical.setRegistrationStartDate(DateUtil.parseDateTime(registrationStartDate + ":00"));
		physical.setRegistrationEndDate(DateUtil.parseDateTime(registrationEndDate + ":00"));
		physical.setTrainStartDate(DateUtil.parseDateTime(trainStartDate + ":00"));
		physical.setTrainEndDate(DateUtil.parseDateTime(trainEndDate + ":00"));
		physical.setType(type);
		if (null != templateImg && !"".equals(templateImg)) {
			physical.setCoverImg(templateImg);
		} else {
			if (null != coverImgFileName) {
				physical.setCoverImg(coverImg + coverImgFileName);
			}
		}
		physical.setTrainContent(trainContent);
		if (null != trainImgFileName) {
			physical.setTrainImg(contentImg + trainImgFileName);
		}
		physical.setCreationDate(new Date());
		physical.setStatus(1);
		physical.setIsSubmit(0);
		physical.setSignUpStatus(1);
		if (type == 1) {
			physical.setDepartmentId(departmentId);
		}
		physical.setIsLimit(isLimit);
		if (null == isLimit || isLimit.equals(0)) {
			limitType = null;
			limitNum = null;
		} else {
			physical.setSymbol(1);
		}
		physical.setLimitType(limitType);
		physical.setLimitNum(limitNum);

		// String qrName = "physical-" + UUID.randomUUID().toString() + ".jpg";
		// physical.setQrCode(trainQRCode + qrName);
		physical.setScorerPoliceId(scorer);
		// String qrName = "physical-" + UUID.randomUUID().toString() + ".jpg";
		// physical.setQrCode(trainQRCode + qrName);
		String trainGroupIds = "";
		JSONObject jasonObject = JSONObject.parseObject(json);
		Map<String, String> map = (Map) jasonObject;

		// 获取键
		for (String i : map.keySet()) {
			trainGroupIds += i + ",";
		}
		trainGroupIds = trainGroupIds.substring(0, trainGroupIds.length() - 1);

		physical.setTrainGroupIds(trainGroupIds);
		// 存储到数据库
		trainService.trainPhysicalCreat(physical);

		for (String i : map.keySet()) {
			// 存储组别对应的项目
			TrainPhysicalProjectRecord trainPhysicalProjectRecord = new TrainPhysicalProjectRecord();
			trainPhysicalProjectRecord.setTrainPhysicalId(physical.getId());
			trainPhysicalProjectRecord.setTrainGroupId(Integer.parseInt(i));
			trainPhysicalProjectRecord.setTrainProject(map.get(i));
			trainPhysicalProjectRecord.setCreationDate(new Date());
			trainService.addTrainPhysicalProjectRecord(trainPhysicalProjectRecord);
		}

		// 生成二维码
		// new QRCode(root + trainQRCodeIcon).encodeQRCode("1," + physical.getId(), 500,
		// 500, root + trainQRCode + qrName);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 删除 综合体能训练
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_delete", method = RequestMethod.POST)
	public ResponseEntity<?> trainPhysicalDelete(@Param("id") Integer id) {

		int del = trainService.trainPhysicalDelete(id);
		// 删除记录表
		trainService.deleteByTrainPhysicalId(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(del);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 修改综合体能训练
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_update", method = RequestMethod.POST)
	public ResponseEntity<?> trainPhysicalDelete(@Param("id") Integer id, @Param("name") String name,
			@Param("place") String place, @Param("departmentId") Integer departmentId,
			@Param("registrationStartDate") String registrationStartDate, @Param("templateImg") String templateImg,
			@Param("isLimit") Integer isLimit, @Param("limitType") Integer limitType,
			@Param("limitNum") Double limitNum, @Param("registrationEndDate") String registrationEndDate,
			@Param("trainStartDate") String trainStartDate, @Param("trainEndDate") String trainEndDate,
			@Param("scorer") String scorer, @Param("trainContent") String trainContent, @Param("json") String json,
			HttpServletRequest request) {

		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile coverImgFile = multipart.getFile("coverImg");
		MultipartFile trainImgFile = multipart.getFile("trainImg");

		String coverImgFileName = null;
		String trainImgFileName = null;

		try {

			if (null != coverImgFile.getOriginalFilename() && !"".equals(coverImgFile.getOriginalFilename())) {

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

			if (null != trainImgFile.getOriginalFilename() && !"".equals(trainImgFile.getOriginalFilename())) {

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

		TrainPhysical physical = new TrainPhysical();
		physical.setId(id);
		physical.setName(name);
		physical.setPlace(place);
		physical.setRegistrationStartDate(DateUtil.parseDateTime(registrationStartDate + ":00"));
		physical.setRegistrationEndDate(DateUtil.parseDateTime(registrationEndDate + ":00"));
		physical.setTrainStartDate(DateUtil.parseDateTime(trainStartDate + ":00"));
		physical.setTrainEndDate(DateUtil.parseDateTime(trainEndDate + ":00"));
		if (null != templateImg && !"".equals(templateImg)) {
			physical.setCoverImg(templateImg);
		} else {
			if (null != coverImgFileName) {
				physical.setCoverImg(coverImg + coverImgFileName);
			}
		}
		physical.setTrainContent(trainContent);
		if (null != trainImgFileName) {
			physical.setTrainImg(contentImg + trainImgFileName);
		}
		physical.setScorerPoliceId(scorer);
		physical.setUpdateDate(new Date());
		physical.setIsLimit(null == isLimit ? 0 : isLimit);
		physical.setLimitType(null == isLimit ? null : limitType);
		physical.setLimitNum(null == isLimit ? null : limitNum);

		String trainGroupIds = "";

		JSONObject jasonObject = JSONObject.parseObject(json);
		Map<String, String> map = (Map) jasonObject;

		// 获取键
		for (String i : map.keySet()) {
			trainGroupIds += i + ",";
		}
		trainGroupIds = trainGroupIds.substring(0, trainGroupIds.length() - 1);

		physical.setTrainGroupIds(trainGroupIds);
		// 修改
		trainService.trainPhysicalUpdateSpecial(physical);

		// 删除项目记录
		trainService.deleteByTrainPhysicalId(id);
		for (String i : map.keySet()) {
			// 新增项目记录
			TrainPhysicalProjectRecord trainPhysicalProjectRecord = new TrainPhysicalProjectRecord();
			trainPhysicalProjectRecord.setTrainPhysicalId(id);
			trainPhysicalProjectRecord.setTrainGroupId(Integer.parseInt(i));
			trainPhysicalProjectRecord.setTrainProject(map.get(i));
			trainPhysicalProjectRecord.setCreationDate(new Date());
			trainService.addTrainPhysicalProjectRecord(trainPhysicalProjectRecord);
		}

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 立即开始综合体能训练
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_start", method = RequestMethod.POST)
	public ResponseEntity<?> trainPhysicalStart(@Param("id") Integer id) {

		int start = trainService.startTrainPhysical(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(start);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 立即结束综合体能训练
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_end", method = RequestMethod.POST)
	public ResponseEntity<?> trainPhysicalEnd(@Param("id") Integer id) {

		int end = trainService.endTrainPhysical(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(end);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 综合体能训练详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_details", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPhysicalDetails(@Param("id") Integer id) {

		// 查询体能训练
		TrainPhysical trainPhysical = trainService.getTrainPhysicalDetails(id);

		// 查询该训练的组别及项目id
		List<TrainPhysicalProjectRecord> trainPhysicalProjectRecordList = trainService
				.trainPhysicalProjectRecordByGroupIds(trainPhysical.getTrainGroupIds(), trainPhysical.getId());

		// 查询每个组别对应的项目名称
		for (TrainPhysicalProjectRecord t : trainPhysicalProjectRecordList) {
			t.setProjectName(trainService.getTrainProjectNamesByIds(t.getTrainProject()));
		}

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("trainPhysical", trainPhysical);
		map.put("trainPhysicalProjectRecord", trainPhysicalProjectRecordList);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 重启综合体能训练
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_restart", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPhysicalDelete(@Param("id") Integer id, @Param("endTime") String endTime) {

		int restart = trainService.restartTrainPhysical(id, endTime);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(restart);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 新增组别
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_add_group", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainAddGroup(@Param("name") String name, @Param("sex") Integer sex,
			@Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge) {

		TrainGroup trainGroup = new TrainGroup();
		trainGroup.setName(name);
		trainGroup.setSex(sex);
		trainGroup.setMinAge(minAge);
		trainGroup.setMaxAge(maxAge);
		trainGroup.setCreationDate(new Date());

		int add = trainService.addTrainGroup(trainGroup);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(add);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 删除组别
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_delete_group", method = RequestMethod.POST)
	public ResponseEntity<?> deleteTrainGroup(@Param("id") Integer id) {

		DataListReturn dataListReturn = new DataListReturn();

		// 查询该组别是否被引用
		int count = trainService.groupQuote(id);

		if (count > 0) {
			// 被引用
			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("fail");
			dataListReturn.setStatus(true);
			dataListReturn.setResult("该组别已被引用,无法删除");

		} else {
			// 删除组别
			int delete = trainService.deleteGroup(id);

			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("success");
			dataListReturn.setStatus(true);
			dataListReturn.setResult(delete);
		}

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 组别详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_details_group", method = RequestMethod.POST)
	public ResponseEntity<?> trainGroupDetails(@Param("id") Integer id) {

		// 组别详情查询
		TrainGroup trainGroup = trainService.trainGroupItem(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainGroup);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 组别更新
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_update_group", method = RequestMethod.POST)
	public ResponseEntity<?> trainGroupUpdate(@Param("name") Integer id, @Param("name") String name,
			@Param("sex") Integer sex, @Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge) {

		TrainGroup trainGroup = new TrainGroup();
		trainGroup.setId(id);
		trainGroup.setName(name);
		trainGroup.setSex(sex);
		trainGroup.setMinAge(minAge);
		trainGroup.setMaxAge(maxAge);
		trainGroup.setUpdateDate(new Date());

		// 组别更新
		int count = trainService.updateTrainGroup(trainGroup);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 检测新增训练项目名称是否重复
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_repeat_project", method = RequestMethod.POST)
	public ResponseEntity<?> repeatTrainProject(@Param("name") String name) {

		int count = trainService.getRepeatTrainProjectCount(name);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 新增训练项目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_add_project", method = RequestMethod.POST)
	public ResponseEntity<?> addTrainProject(@Param("name") String name, @Param("type") Integer type,
			@Param("isU") Integer isU, @Param("unitId") Integer unitId, @Param("sort") Integer sort) {

		TrainProject trainProject = new TrainProject();
		trainProject.setName(name);
		trainProject.setType(type);
		trainProject.setIsU(isU == null ? 1 : isU);
		trainProject.setUnitId(unitId);
		trainProject.setSort(sort);
		trainProject.setCreationDate(new Date());

		int add = trainService.addTrainProject(trainProject);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(add);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 删除训练项目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_delete_project", method = RequestMethod.POST)
	public ResponseEntity<?> deleteTrainProject(@Param("id") Integer id) {

		DataListReturn dataListReturn = new DataListReturn();

		List<TrainProjectRule> trainProjectRuleByProjectId = trainService.getTrainProjectRuleByProjectId(id);

		if (trainProjectRuleByProjectId.size() > 0) {

			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("fail");
			dataListReturn.setStatus(true);
			dataListReturn.setResult("该项目已被引用，无法删除！");

			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

		int delete = trainService.deleteTrainProject(id);

		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(delete);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 训练项目详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_details_project", method = RequestMethod.POST)
	public ResponseEntity<?> detailsTrainProject(@Param("id") Integer id) {

		TrainProject trainProject = trainService.getTrainProjectById(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainProject);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 修改训练项目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_update_project", method = RequestMethod.POST)
	public ResponseEntity<?> updateTrainProject(@Param("id") Integer id, @Param("name") String name,
			@Param("type") Integer type, @Param("isU") Integer isU, @Param("unitId") Integer unitId,
			@Param("sort") Integer sort) {

		TrainProject trainProject = new TrainProject();
		trainProject.setId(id);
		trainProject.setName(name);
		trainProject.setType(type);
		trainProject.setIsU(isU);
		trainProject.setUnitId(unitId);
		trainProject.setSort(sort);
		trainProject.setUpdateDate(new Date());

		int update = trainService.updateTrainProject(trainProject);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(update);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 添加记分员配置
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/train/train_add_scorer", method =
	 * RequestMethod.POST) public ResponseEntity<?> addTrainScorer(@Param("groupId")
	 * Integer groupId, @Param("scorerId") String scorerId) {
	 * 
	 * TrainScorer trainScorer = new TrainScorer(); trainScorer.setGroupId(groupId);
	 * trainScorer.setScorerId(scorerId); trainScorer.setCreationDate(new Date());
	 * 
	 * int add = trainService.addTrainScorer(trainScorer);
	 * 
	 * DataListReturn dataListReturn = new DataListReturn();
	 * dataListReturn.setCode(StatusCode.getSuccesscode());
	 * dataListReturn.setMessage("success"); dataListReturn.setStatus(true);
	 * dataListReturn.setResult(add);
	 * 
	 * return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	 * 
	 * }
	 */

	/**
	 * 删除记分员配置
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/train/train_delete_scorer", method =
	 * RequestMethod.POST) public ResponseEntity<?> deleteTrainScorer(@Param("id")
	 * Integer id) {
	 * 
	 * int delete = trainService.deleteTrainScorer(id);
	 * 
	 * DataListReturn dataListReturn = new DataListReturn();
	 * dataListReturn.setCode(StatusCode.getSuccesscode());
	 * dataListReturn.setMessage("success"); dataListReturn.setStatus(true);
	 * dataListReturn.setResult(delete);
	 * 
	 * return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	 * 
	 * }
	 */

	/**
	 * 查询记分员详情
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/train/train_get_scorer", method =
	 * RequestMethod.POST) public ResponseEntity<?> getTrainScorerById(@Param("id")
	 * Integer id) {
	 * 
	 * TrainScorer trainScorer = trainService.getTrainScorerById(id);
	 * 
	 * DataListReturn dataListReturn = new DataListReturn();
	 * dataListReturn.setCode(StatusCode.getSuccesscode());
	 * dataListReturn.setMessage("success"); dataListReturn.setStatus(true);
	 * dataListReturn.setResult(trainScorer);
	 * 
	 * return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	 * 
	 * }
	 */

	/**
	 * 修改记分员详情
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/train/train_update_scorer", method =
	 * RequestMethod.POST) public ResponseEntity<?> updateTrainScorer(@Param("id")
	 * Integer id, @Param("groupId") Integer groupId,
	 * 
	 * @Param("scorerId") String scorerId) {
	 * 
	 * TrainScorer trainScorer = new TrainScorer(); trainScorer.setId(id);
	 * trainScorer.setGroupId(groupId); trainScorer.setScorerId(scorerId);
	 * trainScorer.setUpdateDate(new Date());
	 * 
	 * int update = trainService.updateTrainScorer(trainScorer);
	 * 
	 * DataListReturn dataListReturn = new DataListReturn();
	 * dataListReturn.setCode(StatusCode.getSuccesscode());
	 * dataListReturn.setMessage("success"); dataListReturn.setStatus(true);
	 * dataListReturn.setResult(update);
	 * 
	 * return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	 * 
	 * }
	 */

	/**
	 * 新增项目规则
	 * 
	 * @param name              规则名称
	 * @param projectId         项目id
	 * @param limitTime         U型靶射击的时间限制
	 * @param groupId           综合体能项目的所属组别
	 * @param symbol            综合体能及非U型射击的运算符
	 * @param qualifiedPhysical 综合体能合格成绩
	 * @param qualifiedFirearmA 枪械非U型的合格成绩
	 * 
	 * @param jsonU             json格式的U型靶射击数据
	 * @return
	 */
	@RequestMapping(value = "/train/train_add_project_rule", method = RequestMethod.POST)
	public ResponseEntity<?> addTrainProjectRule(@Param("name") String name, @Param("projectId") Integer projectId,
			@Param("limitTime") Double limitTime, @Param("groupId") Integer groupId, @Param("symbol") Integer symbol,
			@Param("qualifiedPhysical") Double qualifiedPhysical, @Param("qualifiedFirearmA") Double qualifiedFirearmA,

			@Param("jsonU") String jsonU) {

		TrainProjectRule trainProjectRule = new TrainProjectRule();
		trainProjectRule.setName(name);
		trainProjectRule.setProjectId(projectId);
		trainProjectRule.setLimitTime(limitTime);
		trainProjectRule.setGroupId(groupId);
		trainProjectRule.setSymbol(symbol);
		trainProjectRule.setQualifiedPhysical(qualifiedPhysical);
		trainProjectRule.setQualifiedFirearmA(qualifiedFirearmA);
		trainProjectRule.setCreationDate(new Date());
		// 添加项目规则
		trainService.addTrainProjectRule(trainProjectRule);

		if (null != jsonU) {
			// 解析json
			// 将jsonU拆分成json数组
			String json[] = jsonU.split("-");

			for (String str : json) {
				JSONObject jasonObject = JSONObject.parseObject(str);
				Map<String, String> map = (Map) jasonObject;

				TrainProjectURule trainProjectURule = new TrainProjectURule();
				trainProjectURule.setRuleId(trainProjectRule.getId());
				trainProjectURule.setName(map.get("name"));
				trainProjectURule.setMinNum(Double.valueOf(map.get("minNum")));
				trainProjectURule
						.setMaxNum("null".equals(map.get("maxNum")) ? null : Double.valueOf(map.get("maxNum")));
				trainProjectURule.setSymbolA(Integer.valueOf(map.get("symbolA")));
				trainProjectURule
						.setSymbolB("null".equals(map.get("symbolB")) ? null : Integer.valueOf(map.get("symbolB")));
				trainProjectURule.setCreationDate(new Date());
				// 添加U型靶数据
				trainService.addTrainProjectURule(trainProjectURule);

			}

		}

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 项目规则详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_details_project_rule", method = RequestMethod.POST)
	public ResponseEntity<?> detailsTrainProjectRule(@Param("id") Integer id) {

		TrainProjectRule trainProjectRule = trainService.getTrainProjectRuleById(id);

		List<TrainProjectURule> trainProjectURule = null;

		// 如果是U型射击
		// 查询U型射击的详情
		if (null != trainProjectRule.getProjectType() && null != trainProjectRule.getProjectIsu()) {
			if (trainProjectRule.getProjectType() == 2 && trainProjectRule.getProjectIsu() == 2) {
				trainProjectURule = trainService.TrainProjectURuleList(id);
			}
		}

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainProjectRule", trainProjectRule);
		map.put("trainProjectURule", trainProjectURule);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 删除项目规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_delete_project_rule", method = RequestMethod.POST)
	public ResponseEntity<?> deleteTrainProjectRule(@Param("id") Integer id) {

		DataListReturn dataListReturn = new DataListReturn();

		List<TrainPhysicalProjectRecord> trainPhysicalProjectRecordByProjectRuleId = trainService
				.getTrainPhysicalProjectRecordByProjectRuleId(id);

		if (trainPhysicalProjectRecordByProjectRuleId.size() > 0) {

			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("fail");
			dataListReturn.setStatus(true);
			dataListReturn.setResult("该项目规则已被引用，无法删除！");

			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		} else if (trainService.getTrainFirearmByProjectRuleId(id).size() > 0) {

			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("fail");
			dataListReturn.setStatus(true);
			dataListReturn.setResult("该项目规则已被引用，无法删除！");

			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

		// 删除项目规则
		trainService.deleteTrainProjectRule(id);
		// 删除U型靶关联表
		trainService.deleteTrainProjectURule(id);

		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 修改项目规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_update_project_rule", method = RequestMethod.POST)
	public ResponseEntity<?> updateTrainProjectRule(@Param("id") Integer id, @Param("name") String name,
			@Param("projectId") Integer projectId, @Param("limitTime") Double limitTime,
			@Param("groupId") Integer groupId, @Param("symbol") Integer symbol,
			@Param("qualifiedPhysical") Double qualifiedPhysical, @Param("qualifiedFirearmA") Double qualifiedFirearmA,

			@Param("jsonU") String jsonU) {

		TrainProjectRule trainProjectRule = new TrainProjectRule();
		trainProjectRule.setId(id);
		trainProjectRule.setName(name);
		trainProjectRule.setProjectId(projectId);
		trainProjectRule.setLimitTime(limitTime);
		trainProjectRule.setGroupId(groupId);
		trainProjectRule.setSymbol(symbol);
		trainProjectRule.setQualifiedPhysical(qualifiedPhysical);
		trainProjectRule.setQualifiedFirearmA(qualifiedFirearmA);
		trainProjectRule.setUpdateDate(new Date());
		// 修改项目规则
		trainService.updateTrainProjectRule(trainProjectRule);

		if (null != jsonU) {
			// 解析json
			// 将jsonU拆分成json数组
			String json[] = jsonU.split("-");

			// 根据ruleId删除U型靶数据
			trainService.deleteTrainProjectURule(id);

			for (String str : json) {
				JSONObject jasonObject = JSONObject.parseObject(str);
				Map<String, String> map = (Map) jasonObject;

				TrainProjectURule trainProjectURule = new TrainProjectURule();
				trainProjectURule.setRuleId(id);
				trainProjectURule.setName(map.get("name"));
				trainProjectURule.setMinNum(Double.valueOf(map.get("minNum")));
				trainProjectURule
						.setMaxNum("null".equals(map.get("maxNum")) ? null : Double.valueOf(map.get("maxNum")));
				trainProjectURule.setSymbolA(Integer.valueOf(map.get("symbolA")));
				trainProjectURule
						.setSymbolB("null".equals(map.get("symbolB")) ? null : Integer.valueOf(map.get("symbolB")));
				trainProjectURule.setCreationDate(new Date());
				// 添加U型靶数据
				trainService.addTrainProjectURule(trainProjectURule);

			}

		}

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 综合体能训练成绩查询 训练成绩查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_achievement_score", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPhysicalAchievementScore(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("trainGroupId") String trainGroupId, @Param("departmentId") Integer departmentId,
			@Param("keyword") String keyword, @Param("pageSize") Integer pageSize) {

		List<TrainPhysicalAchievement> trainPhysicalAchievementList = trainService
				.getTrainPhysicalAchievement(trainPhysicalId, trainGroupId, departmentId, keyword, (pageSize - 1) * 10);
		int count = trainService.getTrainPhysicalAchievementCount(trainPhysicalId, trainGroupId, departmentId, keyword);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainPhysicalAchievementList", trainPhysicalAchievementList);
		map.put("count", count);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 综合体能训练成绩详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_achievement_details", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPhysicalAchievementDetails(@Param("id") Integer id) {
		// 查询详情
		TrainPhysicalAchievement trainPhysicalAchievement = trainService.getTrainPhysicalAchievementById(id);
		List<TrainPhysicalAchievementDetails> trainPhysicalAchievementDetailsList = trainService
				.getTrainPhysicalAchievementDetailsByCondition(trainPhysicalAchievement.getTrainPhysicalId(), id);
//		for (TrainPhysicalAchievementDetails t : trainPhysicalAchievementDetailsList) {
//			// 查询项目成绩所在组别中的排名
//			Integer rank = trainService.getRank(trainPhysicalAchievement.getTrainPhysicalId(),
//					trainPhysicalAchievement.getTrainGroupId(), t.getProjectId(), t.getPoliceId(),
//					trainService.getTrainProjectById(t.getProjectId()).getSort());
//			t.setRank(rank);
//		}
		if (trainPhysicalAchievementDetailsList.size() > 0) {
			for (int i = 0; i < trainPhysicalAchievementDetailsList.size(); i++) {

				String sort = null;
				if (trainPhysicalAchievementDetailsList.get(i).getSortNum() == 1) {// 升序
					sort = "asc";
				} else if (trainPhysicalAchievementDetailsList.get(i).getSortNum() == 2) {// 降序
					sort = "desc";
				}
				// 根据policeId查询部门
				User user = userService.policeItem(trainPhysicalAchievementDetailsList.get(i).getPoliceId(), null,
						null);
				TrainRank personalRankItem = trainService.trainPersonalRankItem(
						trainPhysicalAchievementDetailsList.get(i).getProjectId(),
						trainPhysicalAchievement.getTrainPhysicalId(), user.getPoliceId(), null, sort);
				if (personalRankItem != null && personalRankItem.getRankId() != null
						&& personalRankItem.getRankId() != 0) {
					trainPhysicalAchievementDetailsList.get(i).setRank(personalRankItem.getRankId());
				} else {
					trainPhysicalAchievementDetailsList.get(i).setRank(null);
				}
			}
		}
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainPhysicalAchievement", trainPhysicalAchievement);
		map.put("trainPhysicalAchievementDetails", trainPhysicalAchievementDetailsList);
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据训练id及训练详情id查询某警员训练项目及成绩
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_achievement_project", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPhysicalachievementProject(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("trainPhysicalAchievementId") Integer trainPhysicalAchievementId) {

		List<TrainPhysicalAchievementDetails> list = trainService
				.getAchievementDetailsProjectByCondition(trainPhysicalId, trainPhysicalAchievementId);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(list);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 综合体能训练成绩修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_achievement_update", method = RequestMethod.POST)

	public ResponseEntity<?> getTrainPhysicalAchievementUpdate(@Param("arrJson") String arrJson,
			@Param("trainPhysicalId") Integer trainPhysicalId, @Param("policeId") String policeId,
			@Param("trainGroupId") Integer trainGroupId) {

		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(arrJson);
		for (int i = 0; i < jsonArray.size(); i++) {

			net.sf.json.JSONObject jsonObj = net.sf.json.JSONObject.fromObject(jsonArray.get(i));

			TrainPhysicalAchievementDetails trainPhysicalAchievementDetails = new TrainPhysicalAchievementDetails();
			trainPhysicalAchievementDetails.setId(Integer.parseInt(jsonObj.get("id").toString()));

			trainPhysicalAchievementDetails.setAchievement(jsonObj.get("achievement").toString().equals("null") ? null
					: Double.parseDouble(jsonObj.get("achievement").toString()));

			Integer min = null;
			Double sec = null;

			if (!jsonObj.get("achievementFirst").toString().equals("null")) {
				// 分
				min = Integer.parseInt(jsonObj.get("achievementFirst").toString());
				trainPhysicalAchievementDetails.setAchievementFirst(min);
			}
			if (!jsonObj.get("achievementSecond").toString().equals("null")) {
				// 秒
				sec = Double.parseDouble(jsonObj.get("achievementSecond").toString());
				trainPhysicalAchievementDetails.setAchievementSecond(sec);
			}

			// 分秒
			if (null != min) {
				trainPhysicalAchievementDetails.setAchievementStr(min + "分");
				trainPhysicalAchievementDetails.setAchievement(min.doubleValue());
				if (null != sec) {
					trainPhysicalAchievementDetails.setAchievementStr(min + "分" + sec + "秒");
					// 秒转分+分
					String secMinStr = new DecimalFormat("#.00").format(sec / 60);
					trainPhysicalAchievementDetails.setAchievement(min + Double.parseDouble(secMinStr));
				}
			} else {
				// 非分秒
				trainPhysicalAchievementDetails.setAchievementStr(
						trainPhysicalAchievementDetails.getAchievement() + jsonObj.get("unit").toString().trim());
			}

			// 判断新修改的成绩是否合格
			TrainProjectRule qualifiedAchievement = trainService
					.getQualifiedAchievement(trainPhysicalAchievementDetails.getId(), trainGroupId);

			// 判断该项目的排序方式
			// 升序
			if (qualifiedAchievement.getSort() == 1) {
				int grade = (trainPhysicalAchievementDetails.getAchievement() <= qualifiedAchievement
						.getQualifiedPhysical()) ? 2 : 1;
				trainPhysicalAchievementDetails.setAchievementGrade(grade);
			} else {
				int grade = (trainPhysicalAchievementDetails.getAchievement() >= qualifiedAchievement
						.getQualifiedPhysical()) ? 2 : 1;
				trainPhysicalAchievementDetails.setAchievementGrade(grade);
			}

			trainPhysicalAchievementDetails.setTrainPhysicalId(trainPhysicalId);
			trainPhysicalAchievementDetails.setPoliceId(policeId);
			trainPhysicalAchievementDetails.setAchievementDate(new Date());

			// 更新数据
			trainService.trainPhysicalAchievementDetailsUpdate(trainPhysicalAchievementDetails);

		}

		// 单项综合体能项目报名详情
		TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, trainPhysicalId, policeId);
		aItem.setAchievementDate(new Date());
		aItem.setUpdateDate(new Date());
		// 查询当前用户成绩是否合格
		List<TrainPhysicalAchievementDetails> dlist = trainService.detailsFailList(trainPhysicalId, policeId);
		if (dlist.size() > 0) {
			aItem.setAchievementGrade(1);
		} else {
			aItem.setAchievementGrade(2);
		}

		trainService.trainPhysicalAchievementUpdate(aItem);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 新增BMI
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_achievement_bmi", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPhysicalachievementBMI(@Param("policeId") String policeId,
			@Param("height") Double height, @Param("weight") Double weight) {

		int count = userService.addHeightWeight(policeId, height, weight);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 综合体能训练成绩排行榜
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_achievement_rank", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPhysicalAchievementRank(@Param("id") Integer id,
			@Param("projectId") Integer projectId, @Param("keyword") String keyword,
			@Param("pageSize") Integer pageSize) {
		// 查询详情
		TrainPhysicalAchievement trainPhysicalAchievement = trainService.getTrainPhysicalAchievementById(id);
		int sort = trainService.getTrainProjectById(projectId).getSort();
		// 查询所在组别下项目的得分排行
		List<TrainPhysicalAchievementDetails> achievementRank = trainService.getAchievementRank(
				trainPhysicalAchievement.getTrainPhysicalId(), trainPhysicalAchievement.getTrainGroupId(), projectId,
				sort, keyword, (pageSize - 1) * 10);
		int count = trainService.getAchievementRankCount(trainPhysicalAchievement.getTrainPhysicalId(),
				trainPhysicalAchievement.getTrainGroupId(), projectId, keyword);
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("achievementRank", achievementRank);
		map.put("count", count);
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 成绩排行榜标题
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_project_title", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPhysicalProjectTitle(@Param("id") Integer id) {

		// 查询详情
		TrainPhysicalAchievement trainPhysicalAchievement = trainService.getTrainPhysicalAchievementById(id);

		// 查询参与的项目
		List<TrainPhysicalAchievementDetails> projectList = trainService
				.getProjectByCondition(trainPhysicalAchievement.getTrainPhysicalId(), id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(projectList);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 编辑活动风采
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_activity_style_edit", method = RequestMethod.POST)
	public ResponseEntity<?> trainActivityStyleEdit(@Param("id") Integer id, @Param("name") String name,
			@Param("isRecommend") Integer isRecommend, @Param("status") Integer status, HttpServletRequest request) {

		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile coverImgFile = multipart.getFile("coverImg");

		String coverImgFileName = null;

		try {
			if (null != coverImgFile.getOriginalFilename() && !"".equals(coverImgFile.getOriginalFilename())) {
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

		TrainActivityStyle trainActivityStyle = new TrainActivityStyle();
		trainActivityStyle.setName(name);
		trainActivityStyle.setIsRecommend(isRecommend);
		trainActivityStyle.setStatus(status);
		if (null != coverImgFileName) {
			trainActivityStyle.setCoverImg(coverImg + coverImgFileName);
		}
		trainActivityStyle.setUpdateDate(new Date());
		trainActivityStyle.setId(id);

		trainService.updateTrainActivityStyle(trainActivityStyle);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 编辑训练标兵
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_pacesetter_edit", method = RequestMethod.POST)
	public ResponseEntity<?> trainPacesetterEdit(@Param("id") Integer id, @Param("name") String name,
			@Param("policeId") String policeId, @Param("isRecommend") Integer isRecommend,
			@Param("status") Integer status, HttpServletRequest request) {

		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile headImgFile = multipart.getFile("headImg");

		String headImgFileName = null;

		try {
			if (null != headImgFile.getOriginalFilename() && !"".equals(headImgFile.getOriginalFilename())) {
				// 存储文件

				byte[] bytes = headImgFile.getBytes();

				// 生成唯一标识 UUID
				String uuid = UUID.randomUUID().toString();
				// 文件名
				String fileName = headImgFile.getOriginalFilename().substring(0,
						headImgFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// 文件存储路径
				String url = (root + coverImg);
				// 文件后缀名
				String suffix = headImgFile.getOriginalFilename()
						.substring(headImgFile.getOriginalFilename().lastIndexOf("."));
				// 加上后缀的文件名
				headImgFileName = fileName + suffix;
				Path path = Paths.get(url, headImgFileName);
				Files.write(path, bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		TrainPacesetter trainPacesetter = new TrainPacesetter();
		trainPacesetter.setName(name);
		trainPacesetter.setPoliceId(policeId);
		trainPacesetter.setIsRecommend(isRecommend);
		trainPacesetter.setStatus(status);
		if (null != headImgFileName) {
			trainPacesetter.setHeadImg(coverImg + headImgFileName);
		}
		trainPacesetter.setUpdateDate(new Date());
		trainPacesetter.setId(id);

		trainService.updateTrainPacesetter(trainPacesetter);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 编辑训练章程
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_constitution_edit", method = RequestMethod.POST)
	public ResponseEntity<?> trainconstitutionEdit(@Param("id") Integer id, @Param("name") String name,
			@Param("isRecommend") Integer isRecommend, @Param("status") Integer status, HttpServletRequest request) {

		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile coverImgFile = multipart.getFile("coverImg");
		MultipartFile filePathFile = multipart.getFile("filePath");

		String coverImgFileName = null;
		String filePathFileName = null;

		try {
			if (null != coverImgFile.getOriginalFilename() && !"".equals(coverImgFile.getOriginalFilename())) {

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
			if (null != filePathFile.getOriginalFilename() && !"".equals(filePathFile.getOriginalFilename())) {

				// 存储文件
				byte[] bytes2 = filePathFile.getBytes();

				// 生成唯一标识 UUID
				String uuid2 = UUID.randomUUID().toString();
				// 文件名
				String fileName2 = filePathFile.getOriginalFilename().substring(0,
						filePathFile.getOriginalFilename().lastIndexOf(".")) + uuid2;
				// 文件存储路径
				String url2 = (root + coverImg);
				// 文件后缀名
				String suffix2 = filePathFile.getOriginalFilename()
						.substring(filePathFile.getOriginalFilename().lastIndexOf("."));
				// 加上后缀的文件名
				filePathFileName = fileName2 + suffix2;
				Path path2 = Paths.get(url2, filePathFileName);
				Files.write(path2, bytes2);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		TrainConstitution trainConstitution = new TrainConstitution();
		trainConstitution.setName(name);
		trainConstitution.setIsRecommend(isRecommend);
		trainConstitution.setStatus(status);
		if (null != coverImgFileName) {
			trainConstitution.setCoverImg(coverImg + coverImgFileName);
		}
		if (null != filePathFileName) {
			trainConstitution.setFilePath(filePathFileName);
		}
		trainConstitution.setUpdateDate(new Date());
		trainConstitution.setId(id);

		trainService.updateTrainConstitution(trainConstitution);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 新增活动风采
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_activity_style_add", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainActivityStyleAdd(@Param("name") String name,
			@Param("isRecommend") Integer isRecommend, @Param("status") Integer status, HttpServletRequest request) {

		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile coverImgFile = multipart.getFile("coverImg");

		String coverImgFileName = null;

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

		TrainActivityStyle trainActivityStyle = new TrainActivityStyle();
		trainActivityStyle.setName(name);
		trainActivityStyle.setIsRecommend(isRecommend);
		trainActivityStyle.setStatus(status);
		if (null != coverImgFileName) {
			trainActivityStyle.setCoverImg(coverImg + coverImgFileName);
		}
		trainActivityStyle.setLikeNum(0);
		trainActivityStyle.setCreationDate(new Date());

		trainService.addTrainActivityStyle(trainActivityStyle);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 新增训练标兵
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_pacesetter_add", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPacesetterAdd(@Param("id") Integer id, @Param("name") String name,
			@Param("policeId") String policeId, @Param("isRecommend") Integer isRecommend,
			@Param("status") Integer status, HttpServletRequest request) {

		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile headImgFile = multipart.getFile("headImg");

		String headImgFileName = null;

		try {
			if (null != headImgFile && !"".equals(headImgFile.getOriginalFilename())) {
				// 存储文件

				byte[] bytes = headImgFile.getBytes();

				// 生成唯一标识 UUID
				String uuid = UUID.randomUUID().toString();
				// 文件名
				String fileName = headImgFile.getOriginalFilename().substring(0,
						headImgFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// 文件存储路径
				String url = (root + coverImg);
				// 文件后缀名
				String suffix = headImgFile.getOriginalFilename()
						.substring(headImgFile.getOriginalFilename().lastIndexOf("."));
				// 加上后缀的文件名
				headImgFileName = fileName + suffix;
				Path path = Paths.get(url, headImgFileName);
				Files.write(path, bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		TrainPacesetter trainPacesetter = new TrainPacesetter();
		trainPacesetter.setName(name);
		trainPacesetter.setPoliceId(policeId);
		trainPacesetter.setIsRecommend(isRecommend);
		trainPacesetter.setStatus(status);
		if (null != headImgFileName) {
			trainPacesetter.setHeadImg(coverImg + headImgFileName);
		}
		trainPacesetter.setLikeNum(0);
		trainPacesetter.setCreationDate(new Date());

		trainService.addTrainPacesetter(trainPacesetter);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 新增训练章程
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_constitution_add", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainConstitutionAdd(@Param("id") Integer id, @Param("name") String name,
			@Param("isRecommend") Integer isRecommend, @Param("status") Integer status, HttpServletRequest request) {

		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile coverImgFile = multipart.getFile("coverImg");
		MultipartFile filePathFile = multipart.getFile("filePath");

		String coverImgFileName = null;
		String filePathFileName = null;

		try {
			if (null != coverImgFile.getOriginalFilename() && !"".equals(coverImgFile.getOriginalFilename())) {

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
			if (null != filePathFile.getOriginalFilename() && !"".equals(filePathFile.getOriginalFilename())) {

				// 存储文件
				byte[] bytes2 = filePathFile.getBytes();

				// 生成唯一标识 UUID
				String uuid2 = UUID.randomUUID().toString();
				// 文件名
				String fileName2 = filePathFile.getOriginalFilename().substring(0,
						filePathFile.getOriginalFilename().lastIndexOf(".")) + uuid2;
				// 文件存储路径
				String url2 = (root + coverImg);
				// 文件后缀名
				String suffix2 = filePathFile.getOriginalFilename()
						.substring(filePathFile.getOriginalFilename().lastIndexOf("."));
				// 加上后缀的文件名
				filePathFileName = fileName2 + suffix2;
				Path path2 = Paths.get(url2, filePathFileName);
				Files.write(path2, bytes2);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		TrainConstitution trainConstitution = new TrainConstitution();
		trainConstitution.setName(name);
		trainConstitution.setIsRecommend(isRecommend);
		trainConstitution.setStatus(status);
		if (null != coverImgFileName) {
			trainConstitution.setCoverImg(coverImg + coverImgFileName);
		}
		if (null != filePathFileName) {
			trainConstitution.setFilePath(coverImg + filePathFileName);
		}
		trainConstitution.setView(0);
		trainConstitution.setCreationDate(new Date());

		trainService.addTrainConstitution(trainConstitution);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 删除活动风采
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_activity_style_delete", method = RequestMethod.POST)
	public ResponseEntity<?> TrainActivityStyleDelete(@Param("id") Integer id) {

		int count = trainService.deleteTrainActivityStyle(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 删除训练标兵
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_pacesetter_delete", method = RequestMethod.POST)
	public ResponseEntity<?> trainPacesetterDelete(@Param("id") Integer id) {

		int count = trainService.deleteTrainPacesetter(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 删除训练章程
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_constitution_delete", method = RequestMethod.POST)
	public ResponseEntity<?> trainConstitutionDelete(@Param("id") Integer id) {

		int count = trainService.deleteTrainConstitution(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 活动风采设为推荐或下架
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_activity_style_isrecommend", method = RequestMethod.POST)
	public ResponseEntity<?> trainActivityStyleIsRecommend(@Param("id") Integer id,
			@Param("isRecommend") Integer isRecommend, @Param("status") Integer status) {

		int count = trainService.isRecommendOrStatus(id, isRecommend, status);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 训练标兵设为推荐或下架
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_pacesetter_isrecommend", method = RequestMethod.POST)
	public ResponseEntity<?> trainActivityPacesetterIsrecommend(@Param("id") Integer id,
			@Param("isRecommend") Integer isRecommend, @Param("status") Integer status) {

		int count = trainService.pacesetterIsRecommendOrStatus(id, isRecommend, status);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 训练章程设为推荐或下架
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_constitution_isrecommend", method = RequestMethod.POST)
	public ResponseEntity<?> trainConstitutionIsrecommend(@Param("id") Integer id,
			@Param("isRecommend") Integer isRecommend, @Param("status") Integer status) {

		int count = trainService.constitutionIsRecommendOrStatus(id, isRecommend, status);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 活动风采详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_activity_style_details", method = RequestMethod.POST)
	public ResponseEntity<?> trainActivityStyleDetails(@Param("id") Integer id) {

		TrainActivityStyle trainActivityStyle = trainService.getTrainActivityStyleOnely(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainActivityStyle);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 训练标兵详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_pacesetter_details", method = RequestMethod.POST)
	public ResponseEntity<?> trainPacesetterDetails(@Param("id") Integer id) {

		TrainPacesetter trainPacesetter = trainService.getTrainPacesetterOnely(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainPacesetter);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 训练章程详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_constitution_details", method = RequestMethod.POST)
	public ResponseEntity<?> trainConstitutionDetails(@Param("id") Integer id) {

		TrainConstitution trainConstitution = trainService.getTrainConstitutionOnely(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainConstitution);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 查询奖章详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_medal_details", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainMedalDetails(@Param("id") Integer id) {

		TrainMedalManage trainMedalManage = trainService.getTrainMedalOnely(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainMedalManage);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 删除奖章详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_medal_delete", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainMedalDelete(@Param("id") Integer id) {

		int count = trainService.deleteTrainMedal(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 编辑奖章详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_medal_edit", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainMedalEdit(@Param("id") Integer id, @Param("name") String name,
			@Param("type") Integer type, @Param("remark") String remark, HttpServletRequest request) {

		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile imageFile = multipart.getFile("image");

		String imageFileName = null;

		try {
			if (null != imageFile && !"".equals(imageFile.getOriginalFilename())) {
				// 存储文件

				byte[] bytes = imageFile.getBytes();

				// 生成唯一标识 UUID
				String uuid = UUID.randomUUID().toString();
				// 文件名
				String fileName = imageFile.getOriginalFilename().substring(0,
						imageFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// 文件存储路径
				String url = (root + coverImg);
				// 文件后缀名
				String suffix = imageFile.getOriginalFilename()
						.substring(imageFile.getOriginalFilename().lastIndexOf("."));
				// 加上后缀的文件名
				imageFileName = fileName + suffix;
				Path path = Paths.get(url, imageFileName);
				Files.write(path, bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		TrainMedalManage trainMedalManage = new TrainMedalManage();
		trainMedalManage.setId(id);
		if (null != imageFile && !"".equals(imageFile.getOriginalFilename())) {
			trainMedalManage.setImage(coverImg + imageFileName);
		}
		trainMedalManage.setName(name);
		trainMedalManage.setRemark(remark);
		trainMedalManage.setUpdateDate(new Date());
		trainMedalManage.setType(type);

		trainService.updateTrainMedal(trainMedalManage);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 查询领队
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_leader_list", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainLeaderList(@Param("keyword") String keyword, @Param("pageSize") Integer pageSize) {
		pageSize = null == pageSize ? 1 : pageSize;
		List<TrainLeader> trainLeaderList = trainService.getTrainLeaderList(keyword, (pageSize - 1) * 10);
		int count = trainService.getTrainLeaderListCount(keyword);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainLeaderList", trainLeaderList);
		map.put("count", count);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 新增领队
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_leader_add", method = RequestMethod.POST)
	public ResponseEntity<?> trainLeaderAdd(@Param("departmentId") Integer departmentId,
			@Param("leaderId") String leaderId) {

		DataListReturn dataListReturn = new DataListReturn();

		// 判断该领队是否已存在
		TrainLeader trainLeaderItem = trainService.trainLeaderItem(null, leaderId, null);
		TrainLeader trainDepartmentItem = trainService.trainLeaderItem(null, null, departmentId);

		if (null != trainLeaderItem || null != trainDepartmentItem) {

			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("fail");
			dataListReturn.setStatus(true);
			dataListReturn.setResult("该领队或该单位已存在!");

			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

		TrainLeader trainLeader = new TrainLeader();
		trainLeader.setDepartmentId(departmentId);
		trainLeader.setLeaderId(leaderId);
		trainLeader.setCreationDate(new Date());

		int count = trainService.trainLeaderCreat(trainLeader);

		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 编辑领队
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_leader_edit", method = RequestMethod.POST)
	public ResponseEntity<?> trainLeaderEdit(@Param("id") Integer id, @Param("departmentId") Integer departmentId,
			@Param("leaderId") String leaderId) {

		TrainLeader trainLeader = new TrainLeader();
		trainLeader.setId(id);
		trainLeader.setDepartmentId(departmentId);
		trainLeader.setLeaderId(leaderId);
		trainLeader.setCreationDate(new Date());

		int count = trainService.trainLeaderUpdate(trainLeader);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 删除领队
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_leader_delete", method = RequestMethod.POST)
	public ResponseEntity<?> trainLeaderDelete(@Param("id") Integer id) {

		int count = trainService.trainLeaderDelete(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 领队详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_leader_onely", method = RequestMethod.POST)
	public ResponseEntity<?> trainLeaderOnely(@Param("id") Integer id) {

		TrainLeader trainLeader = trainService.getTrainLeaderById(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainLeader);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 新增枪械训练
	 * 
	 * @param name
	 * @param place
	 * @param trainFirearmType
	 * @param departmentId
	 * @param registrationStartDate
	 * @param registrationEndDate
	 * @param trainStartDate
	 * @param trainEndDate
	 * @param scorer
	 * @param type
	 * @param trainContent
	 * @param policeIds
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/train/train_add_firearm", method = RequestMethod.POST)
	public ResponseEntity<?> addTrainFirearm(@Param("name") String name, @Param("place") String place,
			@Param("trainFirearmType") Integer trainFirearmType, @Param("departmentId") Integer departmentId,
			@Param("registrationStartDate") String registrationStartDate, @Param("templateImg") String templateImg,
			@Param("registrationEndDate") String registrationEndDate, @Param("trainStartDate") String trainStartDate,
			@Param("trainEndDate") String trainEndDate, @Param("isAll") Integer isAll,
			@Param("isLimit") Integer isLimit, @Param("limitType") Integer limitType,
			@Param("limitNum") Double limitNum, @Param("scorer") String scorer, @Param("type") Integer type,
			@Param("trainContent") String trainContent, @Param("policeIds") String policeIds,
			HttpServletRequest request) {

		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile coverImgFile = multipart.getFile("coverImg");
		MultipartFile trainImgFile = multipart.getFile("trainImg");

		String coverImgFileName = null;
		String trainImgFileName = null;

		try {

			if (null != coverImgFile && !"".equals(coverImgFile.getOriginalFilename()) && null != trainImgFile) {

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

		// 存储到数据库
		TrainFirearm trainFirearm = new TrainFirearm();
		trainFirearm.setType(type);
		if (type == 1) {
			trainFirearm.setDepartmentId(departmentId);
			if (isAll != 2) {
				trainFirearm.setInvolvementPoliceIds(policeIds);
			} else {
				// 查询该单位下的所有人员
				trainFirearm.setInvolvementPoliceIds(userService.getPoliceIdByDepartmentId(departmentId));
			}
		}
		trainFirearm.setName(name);
		trainFirearm.setPlace(place);
		trainFirearm.setRegistrationStartDate(DateUtil.parseDateTime(registrationStartDate + ":00"));
		trainFirearm.setRegistrationEndDate(DateUtil.parseDateTime(registrationEndDate + ":00"));
		trainFirearm.setTrainStartDate(DateUtil.parseDateTime(trainStartDate + ":00"));
		trainFirearm.setTrainEndDate(DateUtil.parseDateTime(trainEndDate + ":00"));
		trainFirearm.setTrainFirearmType(trainFirearmType);
		trainFirearm.setScorerPoliceId(scorer);
		trainFirearm.setSignUpStatus(1);
		trainFirearm.setStatus(1);
		if (null != templateImg && !"".equals(templateImg)) {
			trainFirearm.setCoverImg(templateImg);
		} else {
			if (null != coverImgFileName) {
				trainFirearm.setCoverImg(coverImg + coverImgFileName);
			}
		}
		trainFirearm.setTrainContent(trainContent);
		if (null != trainImgFileName) {
			trainFirearm.setTrainImg(contentImg + trainImgFileName);
		}
		trainFirearm.setIsSubmit(0);
		trainFirearm.setIsAll(isAll);
		trainFirearm.setCreationDate(new Date());
		trainFirearm.setIsLimit(isLimit);
		trainFirearm.setLimitType(limitType);
		trainFirearm.setLimitNum(limitNum);

		trainService.trainFirearmAdd(trainFirearm);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 修改枪械训练
	 * 
	 * @param name
	 * @param place
	 * @param trainFirearmType
	 * @param departmentId
	 * @param registrationStartDate
	 * @param registrationEndDate
	 * @param trainStartDate
	 * @param trainEndDate
	 * @param scorer
	 * @param type
	 * @param trainContent
	 * @param policeIds
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/train/train_update_firearm", method = RequestMethod.POST)
	public ResponseEntity<?> updateTrainFirearm(@Param("name") String name, @Param("place") String place,
			@Param("id") Integer id, @Param("trainFirearmType") Integer trainFirearmType,
			@Param("templateImg") String templateImg, @Param("departmentId") Integer departmentId,
			@Param("registrationStartDate") String registrationStartDate, @Param("isAll") Integer isAll,
			@Param("registrationEndDate") String registrationEndDate, @Param("trainStartDate") String trainStartDate,
			@Param("trainEndDate") String trainEndDate, @Param("scorer") String scorer,
			@Param("isLimit") Integer isLimit, @Param("limitType") Integer limitType,
			@Param("limitNum") Double limitNum, @Param("trainContent") String trainContent,
			@Param("policeIds") String policeIds, HttpServletRequest request) {

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

		// 存储到数据库
		TrainFirearm trainFirearm = new TrainFirearm();
		trainFirearm.setId(id);
		if (0 != departmentId) {
			// trainFirearm.setDepartmentId(departmentId);
			if (isAll == 1) {
				// 部分人员
				trainFirearm.setInvolvementPoliceIds(policeIds);
			} else {
				// 全部人员
				// 查询该单位下的所有人员
				trainFirearm.setInvolvementPoliceIds(userService.getPoliceIdByDepartmentId(departmentId));
			}
		}
		trainFirearm.setName(name);
		trainFirearm.setPlace(place);
		trainFirearm.setRegistrationStartDate(DateUtil.parseDateTime(registrationStartDate + ":00"));
		trainFirearm.setRegistrationEndDate(DateUtil.parseDateTime(registrationEndDate + ":00"));
		trainFirearm.setTrainStartDate(DateUtil.parseDateTime(trainStartDate + ":00"));
		trainFirearm.setTrainEndDate(DateUtil.parseDateTime(trainEndDate + ":00"));
		trainFirearm.setTrainFirearmType(trainFirearmType);

		trainFirearm.setScorerPoliceId(scorer);
//		trainFirearm.setSignUpStatus(1);
//		trainFirearm.setStatus(1);
		if (null != templateImg && !"".equals(templateImg)) {
			trainFirearm.setCoverImg(templateImg);
		} else {
			if (null != coverImgFileName) {
				trainFirearm.setCoverImg(coverImg + coverImgFileName);
			}
		}
		trainFirearm.setTrainContent(trainContent);
		if (null != trainImgFileName) {
			trainFirearm.setTrainImg(contentImg + trainImgFileName);
		}
		trainFirearm.setIsSubmit(0);
		trainFirearm.setIsAll(isAll);
		trainFirearm.setIsLimit(null == isLimit ? 0 : isLimit);
		trainFirearm.setLimitType(null == isLimit ? null : limitType);
		trainFirearm.setLimitNum(null == isLimit ? null : limitNum);
		trainFirearm.setUpdateDate(new Date());

		trainService.trainFirearmUpdateSpecial(trainFirearm);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 删除枪械训练
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_delete_firearm", method = RequestMethod.POST)
	public ResponseEntity<?> trainFirearmDelete(@Param("id") Integer id) {
		int count = trainService.trainFirearmDelete(id);
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 立即开始枪械训练
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_start_firearm", method = RequestMethod.POST)
	public ResponseEntity<?> trainFirearmStart(@Param("id") Integer id) {

		int count = trainService.trainFirearmStart(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 立即结束枪械训练
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_end_firearm", method = RequestMethod.POST)
	public ResponseEntity<?> trainFirearmEnd(@Param("id") Integer id) {

		int count = trainService.trainFirearmEnd(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 立即重启枪械训练
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_restart_firearm", method = RequestMethod.POST)
	public ResponseEntity<?> trainFirearmReStart(@Param("id") Integer id, @Param("time") String time) {

		int count = trainService.trainFirearmReStart(id, time);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 枪械训练详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_details_firearm", method = RequestMethod.POST)
	public ResponseEntity<?> trainFirearmDetails(@Param("id") Integer id) {

		TrainFirearm trainFirearm = trainService.getTrainFirearmById(id);

		// 查询参与警员
		trainFirearm
				.setInvolvementPoliceNames(userService.getUserNameByPoliceIds(trainFirearm.getInvolvementPoliceIds()));

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainFirearm);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 枪械查看数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_data_firearm", method = RequestMethod.POST)
	public ResponseEntity<?> trainFirearmData(@Param("trainFirearmId") Integer trainFirearmId,
			@Param("departmentId") Integer departmentId, @Param("keyword") String keyword,
			@Param("pageSize") Integer pageSize) {

		List<TrainFirearmAchievement> trainFirearmAchievement = trainService.getFirearmAchievementData(trainFirearmId,
				departmentId, keyword, (pageSize - 1) * 10);

		// 查询枪械训练在该训练中的成绩排行
		for (TrainFirearmAchievement t : trainFirearmAchievement) {
			int rank = trainService.getFirearmRank(trainFirearmId, t.getPoliceId(),
					trainService.getTrainProjectById(t.getTrainProjectType()).getSort());
			t.setRank(rank);
		}

		int count = trainService.getFirearmAchievementDataCount(trainFirearmId, departmentId, keyword);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("trainFirearmAchievement", trainFirearmAchievement);
		map.put("count", count);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 添加枪械训练成绩
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_add_firearm_achievement", method = RequestMethod.POST)
	public ResponseEntity<?> trainFirearmAchievementAdd(@Param("trainFirearmId") Integer trainFirearmId,
			@Param("policeId") String policeId, @Param("trainProjectType") Integer trainProjectType,
			@Param("achievement") Double achievement) {

		TrainFirearmAchievement trainFirearmAchievement = new TrainFirearmAchievement();
		trainFirearmAchievement.setTrainFirearmId(trainFirearmId);
		trainFirearmAchievement.setPoliceId(policeId);
		trainFirearmAchievement.setTrainProjectType(trainProjectType);
		trainFirearmAchievement.setAchievement(achievement);
		trainFirearmAchievement.setUpdateDate(new Date());

		trainService.trainFirearmAchievementCreat(trainFirearmAchievement);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据id获得枪械成绩详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/train/train_onely_firearm_achievement", method = RequestMethod.POST)
	public ResponseEntity<?> trainFirearmAchievementOnely(@Param("id") Integer id) {

		TrainFirearmAchievement trainFirearmAchievement = trainService.getTrainFirearmAchievementById(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainFirearmAchievement);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 修改枪械训练成绩
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_update_firearm_achievement", method = RequestMethod.POST)
	public ResponseEntity<?> trainFirearmAchievementUpdate(@Param("id") Integer id,
			@Param("trainFirearmId") Integer trainFirearmId, @Param("firstValue") Integer firstValue,
			@Param("lastValue") Double lastValue, @Param("unit") String unit) {
		TrainFirearmAchievement trainFirearmAchievement = new TrainFirearmAchievement();
		trainFirearmAchievement.setId(id);
		trainFirearmAchievement.setTrainFirearmId(trainFirearmId);
		trainFirearmAchievement.setAchievementDate(new Date());
		if (null != lastValue) {
			// U型靶
			String format = new DecimalFormat("#.00").format(firstValue / lastValue);
			trainFirearmAchievement.setAchievement(Double.parseDouble(format));
			trainFirearmAchievement.setAchievementFirst(firstValue);
			trainFirearmAchievement.setAchievementSecond(lastValue);
			trainFirearmAchievement.setAchievementStr(Double.parseDouble(format) + "比值");
			trainFirearmAchievement.setShootTime(lastValue);
		} else {
			trainFirearmAchievement.setAchievement(firstValue.doubleValue());
			trainFirearmAchievement.setAchievementStr(firstValue.doubleValue() + unit);
		}
		TrainFirearmAchievement achievementItem = trainService.trainFirearmAchievementItem(id, null, null);
		// 根据项目id/组别查询算分规则
		TrainProjectRule ruleItem = trainService.trainProjectPoliceRuleItem(achievementItem.getTrainProjectType(),
				null);
		if (ruleItem != null) {
			if (lastValue == null) {
				if (ruleItem.getSymbol() == 1) {// 1>= 2> 3<= 4< 5=
					if (trainFirearmAchievement.getAchievement() >= ruleItem.getQualifiedFirearmA()) {
						trainFirearmAchievement.setAchievementGrade(2);
					} else {
						trainFirearmAchievement.setAchievementGrade(1);
					}
				} else if (ruleItem.getSymbol() == 2) {
					if (trainFirearmAchievement.getAchievement() > ruleItem.getQualifiedFirearmA()) {
						trainFirearmAchievement.setAchievementGrade(2);
					} else {
						trainFirearmAchievement.setAchievementGrade(1);
					}
				} else if (ruleItem.getSymbol() == 3) {
					if (trainFirearmAchievement.getAchievement() <= ruleItem.getQualifiedFirearmA()) {
						trainFirearmAchievement.setAchievementGrade(2);
					} else {
						trainFirearmAchievement.setAchievementGrade(1);
					}
				} else if (ruleItem.getSymbol() == 4) {
					if (trainFirearmAchievement.getAchievement() < ruleItem.getQualifiedFirearmA()) {
						trainFirearmAchievement.setAchievementGrade(2);
					} else {
						trainFirearmAchievement.setAchievementGrade(1);
					}
				} else if (ruleItem.getSymbol() == 5) {
					if (trainFirearmAchievement.getAchievement() == ruleItem.getQualifiedFirearmA()) {
						trainFirearmAchievement.setAchievementGrade(2);
					} else {
						trainFirearmAchievement.setAchievementGrade(1);
					}
				}
			} else {
				if (lastValue > ruleItem.getLimitTime()) {
					trainFirearmAchievement.setAchievementGrade(1);
				} else {
					// 根据项目规则id查询U型靶成绩规则
					List<TrainProjectURule> list = trainService.TrainProjectURuleList(ruleItem.getId());
					double num = 0.0;
					if (lastValue != 0.0 && lastValue != 0) {
						num = firstValue / lastValue;
					}
					if (list.get(0).getMinNum() <= num && num <= list.get(0).getMaxNum()) {// 1小于2小于等于
						trainFirearmAchievement.setAchievementGrade(1);
					} else if (list.get(1).getMinNum() < num && num < list.get(1).getMaxNum()) {
						trainFirearmAchievement.setAchievementGrade(2);
					} else if (list.get(2).getMinNum() <= num && num < list.get(2).getMaxNum()) {
						trainFirearmAchievement.setAchievementGrade(3);
					} else if (list.get(3).getMinNum() < num) {
						trainFirearmAchievement.setAchievementGrade(4);
					}
				}
			}
		}
		trainService.trainFirearmAchievementUpdate(trainFirearmAchievement);
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 综合体能成绩数据统计
	 * 
	 * @param trainPhysicalId 综合体能id
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_statistics", method = RequestMethod.POST)
	public ResponseEntity<?> trainPhysicalStatistics(@Param("trainPhysicalId") Integer trainPhysicalId) {

		Integer companyNum = trainService.companyNum(trainPhysicalId);
		Integer signUpNum = trainService.signUpNum(trainPhysicalId);
		Integer signInNum = trainService.signInNum(trainPhysicalId);
		Integer qualifiedNum = trainService.qualifiedNum(trainPhysicalId);
		Integer unqualifiedNum = signUpNum - qualifiedNum;

		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("companyNum", companyNum);
		map.put("signUpNum", signUpNum);
		map.put("signInNum", signInNum);
		map.put("qualifiedNum", qualifiedNum);
		map.put("unqualifiedNum", unqualifiedNum);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 枪械成绩数据统计
	 * 
	 * @param trainPhysicalId 综合体能id
	 * @return
	 */
	@RequestMapping(value = "/train/train_firearm_statistics", method = RequestMethod.POST)
	public ResponseEntity<?> trainFirearmStatistics(@Param("trainFirearmId") Integer trainFirearmId) {

		Integer companyNum = trainService.firearmCompanyNum(trainFirearmId);
		Integer firearmSignUpNum = trainService.firearmSignUpNum(trainFirearmId);
		Integer firearmSignInNum = trainService.firearmSignInNum(trainFirearmId);
		Integer firearmQualifiedNum = trainService.firearmQualifiedNum(trainFirearmId);
		Integer firearmUnqualifiedNum = firearmSignUpNum - firearmQualifiedNum;
		Integer firearmUFineNum = trainService.firearmUFineNum(trainFirearmId);
		Integer firearmUGoodNum = trainService.firearmUGoodNum(trainFirearmId);

		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("companyNum", companyNum);
		map.put("signUpNum", firearmSignUpNum);
		map.put("signInNum", firearmSignInNum);
		map.put("qualifiedNum", firearmQualifiedNum);
		map.put("unqualifiedNum", firearmUnqualifiedNum);
		map.put("uFineNum", firearmUFineNum);
		map.put("uGoodNum", firearmUGoodNum);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(map);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	// 分局签到率/合格率/不合格率/优秀率/良好率统计
	@RequestMapping(value = "/train/office/rate/statistics", method = RequestMethod.POST)
	public ResponseEntity<?> trainOfficeRateStatistics(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "type", required = true) Integer type) {
		DataListReturn dataListReturn = new DataListReturn();
		if (objectId == 1) {// 综合体能
			// 分局综合训练签到率/合格率/不合格率/优秀率/良好率统计
			List<LeaveChart> list = trainService.trainOfficeRateStatisticsList(id, type);
			dataListReturn.setResult(list);
		} else if (objectId == 2) {// 枪械
			// 分局枪械签到率/合格率/不合格率/优秀率/良好率统计
			List<LeaveChart> list = trainService.trainOfficeFirRateStatisticsList(id, type);
			dataListReturn.setResult(list);
		}
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获得综合体能成绩模板
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/train/physical_get_excel", method = RequestMethod.GET)
	public void physicalGetExcel(@Param("trainPhysicalId") Integer trainPhysicalId, HttpServletResponse response) {

		try {
			new ExportExcelUtils().exportExcel(response, getPhysicalExcel(trainPhysicalId));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private List<Map<String, Object>> getPhysicalExcel(Integer trainPhysicalId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		String fileName = "综合体能训练成绩数据导入";
		String excelHeader[] = { "体能训练名称", "姓名", "警号", "项目名称", "训练成绩", "换算单位" };

		String sheetName = "sheet";
		// 文件名
		map.put("fileName", fileName);
		// 表头
		map.put("excelHeader", excelHeader);
		// 数据
		map.put("keys", excelHeader);
		// sheet名
		map.put("sheetName", sheetName);

		list.add(map);

		List<TrainAchievementTemplate> trainAchievementTemplateList = trainService
				.getPhysicalTrainAchievementTemplateList(trainPhysicalId);

		for (int i = 0; i < trainAchievementTemplateList.size(); i++) {
			LinkedHashMap<String, Object> mapBody = new LinkedHashMap<String, Object>();
			TrainAchievementTemplate trainAchievementTemplate = trainAchievementTemplateList.get(i);
			mapBody.put("体能训练名称", trainAchievementTemplate.getName());
			mapBody.put("姓名", trainAchievementTemplate.getPoliceName());
			mapBody.put("警号", trainAchievementTemplate.getPoliceId());
			mapBody.put("项目名称", trainAchievementTemplate.getProjectName());
			mapBody.put("训练成绩", trainAchievementTemplate.getAchievementStr());
			mapBody.put("换算单位", trainAchievementTemplate.getUnit());
			list.add(mapBody);
		}

		return list;

	}

	/**
	 * 导入综合体能成绩
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/train/import_physical_excel", method = RequestMethod.POST)
	public ResponseEntity<?> importPhysicalExcel(@Param("file") MultipartFile file,
			@Param("trainPhysicalId") Integer trainPhysicalId) throws Exception {

		List<List<String>> readExcel = GetExcel.ReadExcel(file);

		List<TrainPhysicalAchievementDetails> list = new ArrayList<TrainPhysicalAchievementDetails>();
		List<TrainPhysicalAchievement> list2 = new ArrayList<TrainPhysicalAchievement>();

		// 记录错误行
		int index = 0;

		try {
			for (List<String> excel : readExcel) {
				// 累计错误行
				index++;
				// 实例化对象接收
				TrainPhysicalAchievementDetails trainPhysicalAchievementDetails = new TrainPhysicalAchievementDetails();
				trainPhysicalAchievementDetails.setTrainPhysicalId(trainPhysicalId);
				String policeId = excel.get(2).toString().substring(0, 6);
				trainPhysicalAchievementDetails.setPoliceId(policeId);

				String achievementStr = excel.get(4).toString();
				// 成绩字符串
				trainPhysicalAchievementDetails.setAchievementStr(achievementStr);
				String unit = excel.get(5).toString();
				Double achievement = null;
				// 判断是否为分秒单位
				if (unit.equals("分秒")) {
					// 获得分
					Double min = Double.parseDouble(achievementStr.substring(0, achievementStr.indexOf("分")));
					// 获得秒
					Double sec = Double.parseDouble(
							achievementStr.substring(achievementStr.indexOf("分") + 1, achievementStr.indexOf("秒")));

					// 最后换算时间
					achievement = NumberUtil.round(min + sec / 60, 2).doubleValue();

				} else {
					// 成绩
					achievement = Double.parseDouble(achievementStr.substring(0, achievementStr.indexOf(unit)));
				}
				trainPhysicalAchievementDetails.setAchievement(achievement);

				// 根据项目名称查询项目id
				Integer projectId = trainService.getTrainProjectByName(excel.get(3).toString());
				trainPhysicalAchievementDetails.setProjectId(projectId);

				// 查询成绩是否合格
				// 查询当前用户所在组包括的项目
				TrainPhysicalProjectRecord projectItem = trainService.projectNamesItem(trainPhysicalId, policeId);
				// 根据项目id/组别查询算分规则
				TrainProjectRule ruleItem = trainService.trainProjectPoliceRuleItem(projectId, projectItem.getId());
				if (ruleItem != null) {
					if (ruleItem.getSymbol() == 1) {// 1>= 2> 3<= 4< 5=
						if (achievement >= ruleItem.getQualifiedPhysical()) {
							trainPhysicalAchievementDetails.setAchievementGrade(2);
						} else {
							trainPhysicalAchievementDetails.setAchievementGrade(1);
						}

					} else if (ruleItem.getSymbol() == 2) {
						if (achievement > ruleItem.getQualifiedPhysical()) {
							trainPhysicalAchievementDetails.setAchievementGrade(2);
						} else {
							trainPhysicalAchievementDetails.setAchievementGrade(1);
						}
					} else if (ruleItem.getSymbol() == 3) {
						if (achievement <= ruleItem.getQualifiedPhysical()) {
							trainPhysicalAchievementDetails.setAchievementGrade(2);
						} else {
							trainPhysicalAchievementDetails.setAchievementGrade(1);
						}
					} else if (ruleItem.getSymbol() == 4) {
						if (achievement < ruleItem.getQualifiedPhysical()) {
							trainPhysicalAchievementDetails.setAchievementGrade(2);
						} else {
							trainPhysicalAchievementDetails.setAchievementGrade(1);
						}
					} else if (ruleItem.getSymbol() == 5) {
						if (achievement == ruleItem.getQualifiedPhysical()) {
							trainPhysicalAchievementDetails.setAchievementGrade(2);
						} else {
							trainPhysicalAchievementDetails.setAchievementGrade(1);
						}
					}
				}

				// 已签到
				trainPhysicalAchievementDetails.setIsSign(2);
				trainPhysicalAchievementDetails.setUpdateDate(new Date());

				TrainPhysicalAchievement trainPhysicalAchievement = new TrainPhysicalAchievement();
				// 成绩更新时间
				trainPhysicalAchievement.setAchievementDate(new Date());
				// 成绩等级
				trainPhysicalAchievement.setAchievementGrade(trainPhysicalAchievementDetails.getAchievementGrade());
				// 签到
				trainPhysicalAchievement.setIsSign(2);
				// trainPhysicalId
				trainPhysicalAchievement.setTrainPhysicalId(trainPhysicalId);
				// 警员
				trainPhysicalAchievement.setPoliceId(policeId);

				list2.add(trainPhysicalAchievement);

				// 读取成功的添加到集合
				list.add(trainPhysicalAchievementDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		}

		// 修改成绩表
		for (TrainPhysicalAchievementDetails t : list) {
			trainService.trainPhysicalAchievementDetailsUpdateCondition(t);
		}
		// 更新报名表
		for (TrainPhysicalAchievement t : list2) {
			trainService.trainPhysicalAchievementUpdateByCondition(t);
		}

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(null);
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获得枪械成绩模板
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/train/firearm_get_excel", method = RequestMethod.GET)
	public void firearmGetExcel(@Param("trainFirearmId") Integer trainFirearmId, HttpServletResponse response) {

		try {
			new ExportExcelUtils().exportExcel(response, getFirearmExcel(trainFirearmId));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private List<Map<String, Object>> getFirearmExcel(Integer trainFirearmId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		String fileName = "枪械训练成绩数据导入";

		String excelHeader[] = { "训练名称", "姓名", "警号", "项目名称", "U型靶射击时间", "训练成绩", "换算单位" };

		String sheetName = "sheet";
		// 文件名
		map.put("fileName", fileName);
		// 表头
		map.put("excelHeader", excelHeader);
		// 数据
		map.put("keys", excelHeader);
		// sheet名
		map.put("sheetName", sheetName);

		list.add(map);

		// 获得枪械成绩模板
		List<TrainAchievementTemplate> trainAchievementTemplateList = trainService
				.getFirearmTrainAchievementTemplate(trainFirearmId);

		for (int i = 0; i < trainAchievementTemplateList.size(); i++) {
			LinkedHashMap<String, Object> mapBody = new LinkedHashMap<String, Object>();
			TrainAchievementTemplate trainAchievementTemplate = trainAchievementTemplateList.get(i);
			mapBody.put("训练名称", trainAchievementTemplate.getName());
			mapBody.put("姓名", trainAchievementTemplate.getPoliceName());
			mapBody.put("警号", trainAchievementTemplate.getPoliceId());
			mapBody.put("项目名称", trainAchievementTemplate.getProjectName());
			mapBody.put("U型靶射击时间",
					trainAchievementTemplate.getIsU() == 1 ? "非U型靶射击" : trainAchievementTemplate.getShootTime());
			mapBody.put("训练成绩", trainAchievementTemplate.getAchievementStr());
			mapBody.put("换算单位", trainAchievementTemplate.getUnit());
			list.add(mapBody);
		}

		return list;

	}

	/**
	 * 导入枪械成绩
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/train/import_firearm_excel", method = RequestMethod.POST)
	public ResponseEntity<?> importFirearmExcel(@Param("file") MultipartFile file,
			@Param("trainFirearmId") Integer trainFirearmId) throws Exception {

		List<List<String>> readExcel = GetExcel.ReadExcel(file);

		List<TrainFirearmAchievement> list = new ArrayList<TrainFirearmAchievement>();

		// 记录错误行
		int index = 0;

		try {
			for (List<String> excel : readExcel) {
				// 累计错误行
				index++;
				// 实例化对象接收
				TrainFirearmAchievement trainFirearmAchievement = new TrainFirearmAchievement();
				trainFirearmAchievement.setTrainFirearmId(trainFirearmId);
				String policeId = excel.get(2).toString().substring(0, 6);
				trainFirearmAchievement.setPoliceId(policeId);
				// U型靶射击时间
				Double shootTime = Double.parseDouble(excel.get(4).toString());

				String achievementStr = excel.get(5).toString();
				// 成绩字符串
				trainFirearmAchievement.setAchievementStr(achievementStr);
				String unit = excel.get(6).toString();
				Double achievement = null;
				// 判断是否为分秒单位
				if (unit.equals("分秒")) {
					// 获得分
					Double min = Double.parseDouble(achievementStr.substring(0, achievementStr.indexOf("分")));
					// 获得秒
					Double sec = Double.parseDouble(
							achievementStr.substring(achievementStr.indexOf("分") + 1, achievementStr.indexOf("秒")));

					// 最后换算时间
					achievement = NumberUtil.round(min + sec / 60, 2).doubleValue();

				} else {
					// 成绩
					achievement = Double.parseDouble(achievementStr.substring(0, achievementStr.indexOf(unit)));
				}
				trainFirearmAchievement.setAchievement(achievement);

				// 成绩更新时间
				trainFirearmAchievement.setAchievementDate(new Date());
				// 计算成绩等级
				TrainProject trainProject = trainService.getTrainProjectByConditon(trainFirearmId, policeId);
				TrainProjectRule ruleItem = trainService.trainProjectPoliceRuleItem(trainProject.getId(), null);
				if (ruleItem != null) {
					if (trainProject.getIsU() == 1) {
						if (ruleItem.getSymbol() == 1) {// 1>= 2> 3<= 4< 5=
							if (achievement >= ruleItem.getQualifiedFirearmA()) {
								trainFirearmAchievement.setAchievementGrade(2);
							} else {
								trainFirearmAchievement.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 2) {
							if (achievement > ruleItem.getQualifiedFirearmA()) {
								trainFirearmAchievement.setAchievementGrade(2);
							} else {
								trainFirearmAchievement.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 3) {
							if (achievement <= ruleItem.getQualifiedFirearmA()) {
								trainFirearmAchievement.setAchievementGrade(2);
							} else {
								trainFirearmAchievement.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 4) {
							if (achievement < ruleItem.getQualifiedFirearmA()) {
								trainFirearmAchievement.setAchievementGrade(2);
							} else {
								trainFirearmAchievement.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 5) {
							if (achievement == ruleItem.getQualifiedFirearmA()) {
								trainFirearmAchievement.setAchievementGrade(2);
							} else {
								trainFirearmAchievement.setAchievementGrade(1);
							}
						}
					} else if (trainProject.getIsU() == 2) {
						if (shootTime > ruleItem.getLimitTime()) {
							trainFirearmAchievement.setAchievementGrade(1);
						} else {
							// 根据项目规则id查询U型靶成绩规则
							List<TrainProjectURule> list2 = trainService.TrainProjectURuleList(ruleItem.getId());
							double num = achievement / shootTime;
							if (list2.get(0).getMinNum() < num && num <= list2.get(0).getMaxNum()) {// 1小于2小于等于
								trainFirearmAchievement.setAchievementGrade(1);
							} else if (list2.get(1).getMinNum() < num && num < list2.get(1).getMaxNum()) {
								trainFirearmAchievement.setAchievementGrade(2);
							} else if (list2.get(2).getMinNum() <= num && num < list2.get(2).getMaxNum()) {
								trainFirearmAchievement.setAchievementGrade(3);
							} else if (list2.get(3).getMinNum() < num) {
								trainFirearmAchievement.setAchievementGrade(4);
							}
						}
					}
				}

				// 已签到
				trainFirearmAchievement.setIsSign(2);
				trainFirearmAchievement.setUpdateDate(new Date());

				// 读取成功的添加到集合
				list.add(trainFirearmAchievement);
			}
		} catch (Exception e) {
			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		}

		// 修改
		for (TrainFirearmAchievement t : list) {
			trainService.trainFirearmAchievementUpdateExport(t);
		}

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(null);
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获得赛事成绩模板
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/train/match_get_excel", method = RequestMethod.GET)
	public void matchGetExcel(@Param("trainFirearmId") Integer trainFirearmId, HttpServletResponse response) {

		try {
			new ExportExcelUtils().exportExcel(response, getMatchExcel(trainFirearmId));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private List<Map<String, Object>> getMatchExcel(Integer trainFirearmId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		String fileName = "赛事成绩数据导入";
		String excelHeader[] = { "赛事名称", "姓名", "警号", "项目类型", "项目名称", "赛事成绩", "换算单位" };

		String sheetName = "sheet";
		// 文件名
		map.put("fileName", fileName);
		// 表头
		map.put("excelHeader", excelHeader);
		// 数据
		map.put("keys", excelHeader);
		// sheet名
		map.put("sheetName", sheetName);

		list.add(map);

		// 获得枪械成绩模板
		List<TrainAchievementTemplate> trainAchievementTemplateList = trainService
				.getMatchTrainAchievementTemplate(trainFirearmId);

		for (int i = 0; i < trainAchievementTemplateList.size(); i++) {
			LinkedHashMap<String, Object> mapBody = new LinkedHashMap<String, Object>();
			TrainAchievementTemplate trainAchievementTemplate = trainAchievementTemplateList.get(i);
			mapBody.put("赛事名称", trainAchievementTemplate.getName());
			mapBody.put("姓名", trainAchievementTemplate.getPoliceName());
			mapBody.put("警号", trainAchievementTemplate.getPoliceId());
			mapBody.put("项目类型", trainAchievementTemplate.getProjectTypeName());
			mapBody.put("项目名称", trainAchievementTemplate.getProjectName());
			mapBody.put("赛事成绩", trainAchievementTemplate.getAchievementStr());
			mapBody.put("换算单位", trainAchievementTemplate.getUnit());
			list.add(mapBody);
		}

		return list;

	}

	/**
	 * 导入赛事成绩
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/train/import_match_excel", method = RequestMethod.POST)
	public ResponseEntity<?> importMatchExcel(@Param("file") MultipartFile file,
			@Param("trainMatchId") Integer trainMatchId) throws Exception {

		List<List<String>> readExcel = GetExcel.ReadExcel(file);

		List<TrainMatchAchievement> list = new ArrayList<TrainMatchAchievement>();

		// 记录错误行
		int index = 0;

		try {
			for (List<String> excel : readExcel) {
				// 累计错误行
				index++;
				// 实例化对象接收
				TrainMatchAchievement trainMatchAchievement = new TrainMatchAchievement();
				trainMatchAchievement.setTrainMatchId(trainMatchId);
				// 警号
				trainMatchAchievement.setPoliceId(excel.get(2).toString().substring(0, 6));

				String achievementStr = excel.get(5).toString();
				// 成绩字符串
				trainMatchAchievement.setAchievementStr(achievementStr);
				String unit = excel.get(6).toString();
				Double achievement = null;
				// 判断是否为分秒单位
				if (unit.equals("分秒")) {
					// 获得分
					Double min = Double.parseDouble(achievementStr.substring(0, achievementStr.indexOf("分")));
					// 获得秒
					Double sec = Double.parseDouble(
							achievementStr.substring(achievementStr.indexOf("分") + 1, achievementStr.indexOf("秒")));

					// 最后换算时间
					achievement = NumberUtil.round(min + sec / 60, 2).doubleValue();

				} else {
					// 成绩
					achievement = Double.parseDouble(achievementStr.substring(0, achievementStr.indexOf(unit)));
				}
				trainMatchAchievement.setAchievement(achievement);

				// 成绩更新时间
				trainMatchAchievement.setAchievementDate(new Date());
				// 已签到
				trainMatchAchievement.setIsSign(2);
				trainMatchAchievement.setUpdateDate(new Date());
				// 读取成功的添加到集合
				list.add(trainMatchAchievement);
			}
		} catch (Exception e) {
			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		}

		// 修改
		for (TrainMatchAchievement t : list) {
			trainService.matchAchievementUpdateExport(t);
		}

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(null);
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

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

	// 导入综合训练成绩并新增综合训练
	@RequestMapping(value = "/import/train/physical", method = RequestMethod.POST)
	public ResponseEntity<?> importAddTrainPhysical(@Param("name") String name,
			@Param("registrationStartDate") String registrationStartDate,
			@Param("json") String json, @Param("file") MultipartFile file, HttpServletRequest request)
			throws Exception {
		System.out.println("开始: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
		TrainPhysical physical = new TrainPhysical();
		physical.setName(name);
		physical.setPlace("分局");
		physical.setRegistrationStartDate(DateUtil.parseDateTime(registrationStartDate));
		physical.setRegistrationEndDate(DateUtil.parseDateTime(registrationStartDate));
		physical.setTrainStartDate(DateUtil.parseDateTime(registrationStartDate));
		physical.setTrainEndDate(DateUtil.parseDateTime(registrationStartDate));
		physical.setType(2);
		physical.setCoverImg("/template-image/physical-template-2.jpg");
		physical.setTrainContent(name);
		physical.setTrainImg("/template-image/physical-template-2.jpg");
		physical.setCreationDate(new Date());
		physical.setStatus(3);
		physical.setIsSubmit(1);
		physical.setSubmitDate(new Date());
		physical.setSignUpStatus(3);
		physical.setIsLimit(0);
		physical.setScorerPoliceId("052805");
		String trainGroupIds = "";
		JSONObject jasonObject = JSONObject.parseObject(json);
		Map<String, String> map = (Map) jasonObject;
		// 获取键
		for (String i : map.keySet()) {
			trainGroupIds += i + ",";
		}
		trainGroupIds = trainGroupIds.substring(0, trainGroupIds.length() - 1);
		physical.setTrainGroupIds(trainGroupIds);
		// 存储到数据库
		trainService.trainPhysicalCreat(physical);
		for (String i : map.keySet()) {
			// 存储组别对应的项目
			TrainPhysicalProjectRecord trainPhysicalProjectRecord = new TrainPhysicalProjectRecord();
			trainPhysicalProjectRecord.setTrainPhysicalId(physical.getId());
			trainPhysicalProjectRecord.setTrainGroupId(Integer.parseInt(i));
			trainPhysicalProjectRecord.setTrainProject(map.get(i));
			trainPhysicalProjectRecord.setCreationDate(new Date());
			trainService.addTrainPhysicalProjectRecord(trainPhysicalProjectRecord);
		}
		// 训练人员列表查询
		List<User> userList = userService.userTrainList();
		List<TrainPhysicalAchievement> addSignList = new ArrayList<TrainPhysicalAchievement>();
		List<TrainPhysicalAchievementDetails> deItemList = new ArrayList<TrainPhysicalAchievementDetails>();
		for (int i = 0; i < userList.size(); i++) {
			TrainPhysicalAchievement item = new TrainPhysicalAchievement();
			item.setTrainPhysicalId(physical.getId());
			item.setPoliceId(userList.get(i).getPoliceId());
			item.setRegistrationDate(new Date());
			// 查询组别民警
			List<TrainGroupPolice> groupList = trainService.trainGroupPoliceList(userList.get(i).getPoliceId());
			item.setTrainGroupId(groupList.get(0).getId());
			item.setSignDate(new Date());
			item.setQrCode("/train-qrcode/physical-870c515b-6d06-445f-bf11-c143bdc6a878.jpg");
			item.setIsSign(2);
			item.setAchievementGrade(1);
			item.setCreationDate(new Date());
			trainService.trainPhysicalAchievementCreat(item);
			List<TrainProject> projectList = trainService.trainPoliceBelongToList(physical.getId(),
					groupList.get(0).getId());
			for (int j = 0; j < projectList.size(); j++) {
				// 项目成绩新增
				TrainPhysicalAchievementDetails deItem = new TrainPhysicalAchievementDetails();
				deItem.setTrainPhysicalId(physical.getId());
				deItem.setTrainPhysicalAchievementId(item.getId());
				deItem.setPoliceId(userList.get(i).getPoliceId());
				deItem.setProjectId(projectList.get(j).getId());
				deItem.setIsEntry(2);
				deItem.setIsSign(2);
				deItem.setSignDate(new Date());
				deItem.setCreationDate(new Date());
				deItemList.add(deItem);
//				trainService.trainPhysicalAchievementDetailsCreat(deItem);
			}
		}
		trainService.trainPhysicalAchievementDetailsCreatBatch(deItemList);
		List<List<String>> readExcel = null;
		try {
			readExcel = GetExcel.ReadExcel(file);
		} catch (Exception e) {

			e.printStackTrace();
		}
		// 最终获取的数据
		List<TrainPhysicalAchievementDetails> finalList = new ArrayList<TrainPhysicalAchievementDetails>();
		// 最终修改的民警训练成绩是否全部合格
//		List<TrainPhysicalAchievement> updateAchievementList = new ArrayList<TrainPhysicalAchievement>();
		// 记录错误行
		try {
			for (List<String> excel : readExcel) {
				// 累计错误行
				String policeId = excel.get(1).toString().replaceAll("\\s*", "").substring(0, 6);
				String projectName1 = excel.get(2).toString().replaceAll("\\s*", "");
				double score1 = 0.0;
				if (StringUtils.isEmpty(projectName1) == false
						&& excel.get(3).toString().replaceAll("\\s*", "") != null) {
					score1 = Double.valueOf(excel.get(3).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem1 = detailsItem(physical.getId(), policeId,
							projectName1);
					if (detailsItem1 != null) {
						detailsItem1.setAchievement(score1);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem1.getProjectId(),
								score1);
						TrainPhysicalAchievementDetails train1 = updateItem(detailsItem1.getId(), score1,
								achievementGrade, projectName1, detailsItem1.getProjectId());
						finalList.add(train1);
					}
				}
				String projectName2 = excel.get(4).toString().replaceAll("\\s*", "");
				double score2 = 0.0;
				if (StringUtils.isEmpty(projectName2) == false
						&& excel.get(5).toString().replaceAll("\\s*", "") != null) {
					score2 = Double.valueOf(excel.get(5).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem2 = detailsItem(physical.getId(), policeId,
							projectName2);
					if (detailsItem2 != null) {
						detailsItem2.setAchievement(score2);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem2.getProjectId(),
								score2);
						TrainPhysicalAchievementDetails train2 = updateItem(detailsItem2.getId(), score2,
								achievementGrade, projectName2, detailsItem2.getProjectId());
						finalList.add(train2);
					}
				}
				String projectName3 = excel.get(6).toString().replaceAll("\\s*", "");
				double score3 = 0.0;
				if (StringUtils.isEmpty(projectName3) == false
						&& excel.get(7).toString().replaceAll("\\s*", "") != null) {
					score3 = Double.valueOf(excel.get(7).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem3 = detailsItem(physical.getId(), policeId,
							projectName3);
					if (detailsItem3 != null) {
						detailsItem3.setAchievement(score3);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem3.getProjectId(),
								score3);
						TrainPhysicalAchievementDetails train3 = updateItem(detailsItem3.getId(), score3,
								achievementGrade, projectName3, detailsItem3.getProjectId());
						finalList.add(train3);
					}
				}
				String projectName4 = excel.get(8).toString().replaceAll("\\s*", "");
				double score4 = 0.0;
				if (StringUtils.isEmpty(projectName4) == false
						&& excel.get(9).toString().replaceAll("\\s*", "") != null) {
					score4 = Double.valueOf(excel.get(9).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem4 = detailsItem(physical.getId(), policeId,
							projectName4);
					if (detailsItem4 != null) {
						detailsItem4.setAchievement(score4);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem4.getProjectId(),
								score4);
						TrainPhysicalAchievementDetails train4 = updateItem(detailsItem4.getId(), score4,
								achievementGrade, projectName4, detailsItem4.getProjectId());
						finalList.add(train4);
					}
				}
				String projectName5 = excel.get(10).toString().replaceAll("\\s*", "");
				double score5 = 0.0;
				if (StringUtils.isEmpty(projectName5) == false
						&& excel.get(11).toString().replaceAll("\\s*", "") != null) {
					score5 = Double.valueOf(excel.get(11).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem5 = detailsItem(physical.getId(), policeId,
							projectName5);
					if (detailsItem5 != null) {
						detailsItem5.setAchievement(score5);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem5.getProjectId(),
								score5);
						TrainPhysicalAchievementDetails train5 = updateItem(detailsItem5.getId(), score5,
								achievementGrade, projectName5, detailsItem5.getProjectId());
						finalList.add(train5);
					}
				}
				String projectName6 = excel.get(12).toString().replaceAll("\\s*", "");
				double score6 = 0.0;
				if (StringUtils.isEmpty(projectName6) == false
						&& excel.get(13).toString().replaceAll("\\s*", "") != null) {
					score6 = Double.valueOf(excel.get(13).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem6 = detailsItem(physical.getId(), policeId,
							projectName6);
					if (detailsItem6 != null) {
						detailsItem6.setAchievement(score6);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem6.getProjectId(),
								score6);
						TrainPhysicalAchievementDetails train6 = updateItem(detailsItem6.getId(), score6,
								achievementGrade, projectName6, detailsItem6.getProjectId());
						finalList.add(train6);
					}
				}
				String projectName7 = excel.get(14).toString().replaceAll("\\s*", "");
				double score7 = 0.0;
				if (StringUtils.isEmpty(projectName7) == false
						&& excel.get(15).toString().replaceAll("\\s*", "") != null) {
					score7 = Double.valueOf(excel.get(15).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem7 = detailsItem(physical.getId(), policeId,
							projectName7);
					if (detailsItem7 != null) {
						detailsItem7.setAchievement(score7);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem7.getProjectId(),
								score7);
						TrainPhysicalAchievementDetails train7 = updateItem(detailsItem7.getId(), score7,
								achievementGrade, projectName7, detailsItem7.getProjectId());
						finalList.add(train7);
					}
				}
				String projectName8 = excel.get(16).toString().replaceAll("\\s*", "");
				double score8 = 0.0;
				if (StringUtils.isEmpty(projectName8) == false
						&& excel.get(17).toString().replaceAll("\\s*", "") != null) {
					score8 = Double.valueOf(excel.get(17).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem8 = detailsItem(physical.getId(), policeId,
							projectName8);
					if (detailsItem8 != null) {
						detailsItem8.setAchievement(score8);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem8.getProjectId(),
								score8);
						TrainPhysicalAchievementDetails train8 = updateItem(detailsItem8.getId(), score8,
								achievementGrade, projectName8, detailsItem8.getProjectId());
						finalList.add(train8);
					}
				}
			}
			// 批量修改警员成绩
			trainService.trainPhysicalAchievementDetailsUpdateBatch(finalList);
			// 根据训练id查询报名人员list
			List<TrainPhysicalAchievement> updateGradeList = trainService.updateGradeList(physical.getId());
			if (updateGradeList.size() > 0) {
				for (int i = 0; i < updateGradeList.size(); i++) {
					achievementItem(physical.getId(), updateGradeList.get(i).getPoliceId());
//					if (achievementItem != null) {
//						updateAchievementList.add(achievementItem);
//					}
				}
			}
			// 训练报名成绩批量修改
//			trainService.trainPhysicalAchievementUpdateBatch(updateGradeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("结束: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
//		System.out.println("第" + index + "行报错");
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(1);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	// 修改民警成绩
	private TrainPhysicalAchievementDetails updateItem(Integer id, Double score, Integer achievementGrade,
			String projectName, Integer projectId) {
		TrainPhysicalAchievementDetails train = new TrainPhysicalAchievementDetails();
		TrainProject project = trainService.getTrainProjectById(projectId);
		train.setId(id);
		train.setAchievement(score);
		train.setAchievementGrade(achievementGrade);
		train.setAchievementDate(new Date());
		train.setUpdateDate(new Date());
		if (projectName.equals("2000米跑")) {
			DecimalFormat df = new DecimalFormat("#.00");
			double x = score;
			int m = (int) x;
			double y = Double.valueOf(df.format(x - m));
			train.setAchievementStr(m + "分" + Double.valueOf(df.format(y * 60.0)) + "秒");
			train.setAchievementFirst(m);
			train.setAchievementSecond(Double.valueOf(df.format(y * 60.0)));
		} else {
			train.setAchievementStr(score + project.getUnitName());
		}
		return train;
	}

	// 查询项目id
	private TrainPhysicalAchievementDetails detailsItem(Integer trainPhysicalId, String policeId, String projectName) {
		TrainPhysicalAchievementDetails item = null;
		// 根据项目名查询项目id
		TrainProject pItem = trainService.trainProjectIdItem(projectName);
		Integer projectId = null;
		if (pItem != null) {
			projectId = pItem.getId();
			// 查询当前训练项详情
			item = trainService.physicalDetailsItem(null, trainPhysicalId, null, policeId, projectId);
		}
		return item;

	}

	// 查询成绩是否合格
	private Integer detailsGrade(Integer trainPhysicalId, String policeId, Integer projectId, Double achievement) {
		Integer detailsGrade = null;
		// 查询当前用户所在组包括的项目
		TrainPhysicalProjectRecord projectItem = trainService.projectNamesItem(trainPhysicalId, policeId);
		// 根据项目id/组别查询算分规则
		TrainProjectRule ruleItem = trainService.trainProjectPoliceRuleItem(projectId, projectItem.getId());
		if (ruleItem != null) {
			if (ruleItem.getSymbol() == 1) {// 1>= 2> 3<= 4< 5=
				if (achievement >= ruleItem.getQualifiedPhysical()) {
					detailsGrade = 2;
				} else {
					detailsGrade = 1;
				}
			} else if (ruleItem.getSymbol() == 2) {
				if (achievement > ruleItem.getQualifiedPhysical()) {
					detailsGrade = 2;
				} else {
					detailsGrade = 1;
				}
			} else if (ruleItem.getSymbol() == 3) {
				if (achievement <= ruleItem.getQualifiedPhysical()) {
					detailsGrade = 2;
				} else {
					detailsGrade = 1;
				}
			} else if (ruleItem.getSymbol() == 4) {
				if (achievement < ruleItem.getQualifiedPhysical()) {
					detailsGrade = 2;
				} else {
					detailsGrade = 1;
				}
			} else if (ruleItem.getSymbol() == 5) {
				if (achievement == ruleItem.getQualifiedPhysical()) {
					detailsGrade = 2;
				} else {
					detailsGrade = 1;
				}
			}
		}
		return detailsGrade;
	}

	// 查询成绩是否全部合格
	private TrainPhysicalAchievement achievementItem(Integer trainPhysicalId, String policeId) {
		// 单项综合体能项目报名详情
		TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, trainPhysicalId, policeId);
		aItem.setAchievementDate(new Date());
		aItem.setUpdateDate(new Date());
		// 查询当前用户成绩是否合格
		List<TrainPhysicalAchievementDetails> dlist = trainService.detailsFailList(trainPhysicalId, policeId);
		if (dlist.size() > 0) {
			aItem.setAchievementGrade(1);
		} else {
			aItem.setAchievementGrade(2);
		}
		trainService.trainPhysicalAchievementUpdate(aItem);
		return aItem;
	}

	// 枪械新增并导入成绩
	@RequestMapping(value = "/import/train/firearm", method = RequestMethod.POST)
	public ResponseEntity<?> importAddTrainFirearm(@Param("name") String name,
			@Param("registrationStartDate") String registrationStartDate, @Param("type") Integer type,
			@Param("file") MultipartFile file, HttpServletRequest request) {
		TrainFirearm trainFirearm = new TrainFirearm();
		trainFirearm.setType(type);
		trainFirearm.setName(name);
		trainFirearm.setPlace("分局");
		trainFirearm.setRegistrationStartDate(DateUtil.parseDateTime(registrationStartDate));
		trainFirearm.setRegistrationEndDate(DateUtil.parseDateTime(registrationStartDate));
		trainFirearm.setTrainStartDate(DateUtil.parseDateTime(registrationStartDate));
		trainFirearm.setTrainEndDate(DateUtil.parseDateTime(registrationStartDate));
		trainFirearm.setTrainFirearmType(2);
		trainFirearm.setScorerPoliceId("117409");
		trainFirearm.setSignUpStatus(3);
		trainFirearm.setStatus(3);
		trainFirearm.setCoverImg("train-content-img/nth-sj.jpg");
		trainFirearm.setTrainContent(name);
		trainFirearm.setTrainImg("train-content-img/nth-sj.jpg");
		trainFirearm.setIsSubmit(1);
		trainFirearm.setSubmitDate(new Date());
		trainFirearm.setIsAll(2);
		trainFirearm.setCreationDate(new Date());
		trainFirearm.setIsLimit(0);
		trainService.trainFirearmAdd(trainFirearm);
		// 训练人员列表查询
		List<User> userList = userService.userTrainList();
		List<TrainFirearmAchievement> addSignList = new ArrayList<TrainFirearmAchievement>();
		for (int i = 0; i < userList.size(); i++) {
			TrainFirearmAchievement item = new TrainFirearmAchievement();
			item.setTrainFirearmId(trainFirearm.getId());
			item.setPoliceId(userList.get(i).getPoliceId());
			item.setRegistrationDate(new Date());
			item.setSignDate(new Date());
			item.setIsSign(2);
			item.setIsSubmit(1);
			item.setQrCode("/train-qrcode/physical-870c515b-6d06-445f-bf11-c143bdc6a878.jpg\"");
			item.setTrainProjectType(2);
			item.setCreationDate(new Date());
			addSignList.add(item);
		}
		// 枪械报名批量新增
		trainService.trainFirearmAchievementCreatBatch(addSignList);
		List<List<String>> readExcel = null;
		try {
			readExcel = GetExcel.ReadExcel(file);
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {
			List<TrainFirearmAchievement> finalList = new ArrayList<TrainFirearmAchievement>();
			for (List<String> excel : readExcel) {
				String policeId = null;
				if (!StringUtils.isEmpty(excel.get(1))) {
					policeId = excel.get(1).toString().replaceAll("\\s*", "").substring(0, 6);
				}
				double score1 = 0.0;
				if (!StringUtils.isEmpty(excel.get(2))) {
					score1 = Double.valueOf(excel.get(2).toString().replaceAll("\\s*", ""));
				}
				if (StringUtils.isEmpty(excel.get(1)) == false
						&& StringUtils.isEmpty(excel.get(2).toString().replaceAll("\\s*", "")) == false) {
					score1 = Double.valueOf(excel.get(2).toString().replaceAll("\\s*", ""));
					// 枪械项目报名详情
					TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, trainFirearm.getId(),
							policeId);
					if (fItem != null) {
						fItem.setAchievement(score1);
						fItem.setAchievementStr(score1 + "环");
						fItem.setUpdateDate(new Date());
						fItem.setAchievementDate(new Date());
						// 根据项目id/组别查询算分规则
						TrainProjectRule ruleItem = trainService.trainProjectPoliceRuleItem(2, null);
						if (ruleItem.getSymbol() == 1) {// 1>= 2> 3<= 4< 5=
							if (score1 >= ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 2) {
							if (score1 > ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 3) {
							if (score1 <= ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 4) {
							if (score1 < ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 5) {
							if (score1 == ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						}
						finalList.add(fItem);
					}
				}
			}
			// 批量修改警员枪械成绩
			trainService.trainFirearmAchievementUpdateBatch(finalList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("结束: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
//			System.out.println("第" + index + "行报错");
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	@PostMapping(value = "/import/exists/train/physical")
	public ResponseEntity<?> importExistsPhysical(@Param("id") Integer id, @Param("file") MultipartFile file) {
		TrainPhysical physical = trainService.getTrainPhysicalDetails(id);
		if (physical == null) {
			throw new RuntimeException("导入异常!");
		}
		physical.setUpdateDate(new Date());

		List<List<String>> readExcel = null;
		try {
			readExcel = GetExcel.ReadExcel(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 最终获取的数据
		List<TrainPhysicalAchievementDetails> finalList = new ArrayList<TrainPhysicalAchievementDetails>();
		List<TrainPhysicalAchievementDetails> insertList = new ArrayList<>();

		// 记录错误行
		try {
			for (List<String> excel : readExcel) {
				// 累计错误行
				String policeId = excel.get(1).toString().replaceAll("\\s*", "");

				//添加报名记录
				TrainPhysicalAchievement trainPhysicalAchievement = trainService.findTrainPhysicalAchievementByPolice(physical.getId(), policeId);
				if (trainPhysicalAchievement == null || trainPhysicalAchievement.getId() == null) {
					trainPhysicalAchievement = new TrainPhysicalAchievement();
					trainPhysicalAchievement.setTrainPhysicalId(physical.getId());
					trainPhysicalAchievement.setPoliceId(policeId);
					trainPhysicalAchievement.setRegistrationDate(new Date());
					trainPhysicalAchievement.setAchievementDate(new Date());
					trainPhysicalAchievement.setSignDate(new Date());
					// 查询组别民警
					List<TrainGroupPolice> groupList = trainService.trainGroupPoliceList(policeId);
					trainPhysicalAchievement.setTrainGroupId(groupList.get(0).getId());
					trainPhysicalAchievement.setQrCode("/train-qrcode/physical-870c515b-6d06-445f-bf11-c143bdc6a878.jpg");
					trainPhysicalAchievement.setAchievementGrade(1);
					trainPhysicalAchievement.setIsSign(2);
					trainPhysicalAchievement.setCreationDate(new Date());

					trainService.addTrainPhysicalAchievement(trainPhysicalAchievement);
				}

				String projectName1 = excel.get(2).toString().replaceAll("\\s*", "");
				double score1 = 0.0;
				if (StringUtils.isEmpty(projectName1) == false
						&& excel.get(3).toString().replaceAll("\\s*", "") != null) {
					score1 = Double.valueOf(excel.get(3).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem1 = detailsItem(physical.getId(), policeId,
							projectName1);
					if (detailsItem1 != null) {
						detailsItem1.setAchievement(score1);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem1.getProjectId(),
								score1);
						TrainPhysicalAchievementDetails train1 = updateItem(detailsItem1.getId(), score1,
								achievementGrade, projectName1, detailsItem1.getProjectId());
						finalList.add(train1);
					}else {
						//查询项目信息
						TrainProject pItem = trainService.trainProjectIdItem(projectName1);
						//判断是否合格
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, pItem.getId(), score1);
						//生成比赛信息
						detailsItem1 = updateItem(null, score1, achievementGrade, projectName1, pItem.getId());
						detailsItem1.setPoliceId(policeId);
						detailsItem1.setTrainPhysicalId(physical.getId());
						detailsItem1.setTrainPhysicalAchievementId(trainPhysicalAchievement.getId());
						detailsItem1.setProjectId(pItem.getId());
						detailsItem1.setCreationDate(new Date());
						detailsItem1.setUpdateDate(null);
						detailsItem1.setIsEntry(2);
						detailsItem1.setIsSign(2);
						detailsItem1.setSignDate(new Date());
						insertList.add(detailsItem1);
					}
				}
				String projectName2 = excel.get(4).toString().replaceAll("\\s*", "");
				double score2 = 0.0;
				if (StringUtils.isEmpty(projectName2) == false
						&& excel.get(5).toString().replaceAll("\\s*", "") != null) {
					score2 = Double.valueOf(excel.get(5).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem2 = detailsItem(physical.getId(), policeId,
							projectName2);
					if (detailsItem2 != null) {
						detailsItem2.setAchievement(score2);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem2.getProjectId(),
								score2);
						TrainPhysicalAchievementDetails train2 = updateItem(detailsItem2.getId(), score2,
								achievementGrade, projectName2, detailsItem2.getProjectId());
						finalList.add(train2);
					}
				}
				String projectName3 = excel.get(6).toString().replaceAll("\\s*", "");
				double score3 = 0.0;
				if (StringUtils.isEmpty(projectName3) == false
						&& excel.get(7).toString().replaceAll("\\s*", "") != null) {
					score3 = Double.valueOf(excel.get(7).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem3 = detailsItem(physical.getId(), policeId,
							projectName3);
					if (detailsItem3 != null) {
						detailsItem3.setAchievement(score3);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem3.getProjectId(),
								score3);
						TrainPhysicalAchievementDetails train3 = updateItem(detailsItem3.getId(), score3,
								achievementGrade, projectName3, detailsItem3.getProjectId());
						finalList.add(train3);
					}
				}
				String projectName4 = excel.get(8).toString().replaceAll("\\s*", "");
				double score4 = 0.0;
				if (StringUtils.isEmpty(projectName4) == false
						&& excel.get(9).toString().replaceAll("\\s*", "") != null) {
					score4 = Double.valueOf(excel.get(9).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem4 = detailsItem(physical.getId(), policeId,
							projectName4);
					if (detailsItem4 != null) {
						detailsItem4.setAchievement(score4);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem4.getProjectId(),
								score4);
						TrainPhysicalAchievementDetails train4 = updateItem(detailsItem4.getId(), score4,
								achievementGrade, projectName4, detailsItem4.getProjectId());
						finalList.add(train4);
					}
				}
				String projectName5 = excel.get(10).toString().replaceAll("\\s*", "");
				double score5 = 0.0;
				if (StringUtils.isEmpty(projectName5) == false
						&& excel.get(11).toString().replaceAll("\\s*", "") != null) {
					score5 = Double.valueOf(excel.get(11).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem5 = detailsItem(physical.getId(), policeId,
							projectName5);
					if (detailsItem5 != null) {
						detailsItem5.setAchievement(score5);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem5.getProjectId(),
								score5);
						TrainPhysicalAchievementDetails train5 = updateItem(detailsItem5.getId(), score5,
								achievementGrade, projectName5, detailsItem5.getProjectId());
						finalList.add(train5);
					}
				}
				String projectName6 = excel.get(12).toString().replaceAll("\\s*", "");
				double score6 = 0.0;
				if (StringUtils.isEmpty(projectName6) == false
						&& excel.get(13).toString().replaceAll("\\s*", "") != null) {
					score6 = Double.valueOf(excel.get(13).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem6 = detailsItem(physical.getId(), policeId,
							projectName6);
					if (detailsItem6 != null) {
						detailsItem6.setAchievement(score6);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem6.getProjectId(),
								score6);
						TrainPhysicalAchievementDetails train6 = updateItem(detailsItem6.getId(), score6,
								achievementGrade, projectName6, detailsItem6.getProjectId());
						finalList.add(train6);
					}
				}
				String projectName7 = excel.get(14).toString().replaceAll("\\s*", "");
				double score7 = 0.0;
				if (StringUtils.isEmpty(projectName7) == false
						&& excel.get(15).toString().replaceAll("\\s*", "") != null) {
					score7 = Double.valueOf(excel.get(15).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem7 = detailsItem(physical.getId(), policeId,
							projectName7);
					if (detailsItem7 != null) {
						detailsItem7.setAchievement(score7);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem7.getProjectId(),
								score7);
						TrainPhysicalAchievementDetails train7 = updateItem(detailsItem7.getId(), score7,
								achievementGrade, projectName7, detailsItem7.getProjectId());
						finalList.add(train7);
					}
				}
				String projectName8 = excel.get(16).toString().replaceAll("\\s*", "");
				double score8 = 0.0;
				if (StringUtils.isEmpty(projectName8) == false
						&& excel.get(17).toString().replaceAll("\\s*", "") != null) {
					score8 = Double.valueOf(excel.get(17).toString().replaceAll("\\s*", ""));
					TrainPhysicalAchievementDetails detailsItem8 = detailsItem(physical.getId(), policeId,
							projectName8);
					if (detailsItem8 != null) {
						detailsItem8.setAchievement(score8);
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, detailsItem8.getProjectId(),
								score8);
						TrainPhysicalAchievementDetails train8 = updateItem(detailsItem8.getId(), score8,
								achievementGrade, projectName8, detailsItem8.getProjectId());
						finalList.add(train8);
					}
				}
			}
			// 批量修改警员成绩
			if (finalList.size() > 0) {
				trainService.trainPhysicalAchievementDetailsUpdateBatch(finalList);
			}
			//批量添加警员成绩
			if (insertList.size() > 0) {
				trainService.trainPhysicalAchievementDetailsCreatBatch(insertList);
			}
			// 根据训练id查询报名人员list
			List<TrainPhysicalAchievement> updateGradeList = trainService.updateGradeList(physical.getId());
			if (updateGradeList.size() > 0) {
				for (int i = 0; i < updateGradeList.size(); i++) {
					achievementItem(physical.getId(), updateGradeList.get(i).getPoliceId());
				}
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(1);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	@PostMapping(value = "/import/exists/train/firearm")
	public ResponseEntity<?> importExistsFirearm(@Param("id") Integer id, @Param("file") MultipartFile file) {
		TrainFirearm trainFirearm = trainService.getTrainFirearmById(id);
		if (trainFirearm == null) {
			throw new RuntimeException("导入异常");
		}
		trainFirearm.setUpdateDate(new Date());

		List<List<String>> readExcel = null;
		try {
			readExcel = GetExcel.ReadExcel(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			List<TrainFirearmAchievement> finalList = new ArrayList<TrainFirearmAchievement>();
			List<TrainFirearmAchievement> insertList = new ArrayList<>();
			for (List<String> excel : readExcel) {
				String policeId = null;
				if (!StringUtils.isEmpty(excel.get(1))) {
					policeId = excel.get(1).toString().replaceAll("\\s*", "").substring(0, 6);
				}
				double score1 = 0.0;
				if (!StringUtils.isEmpty(excel.get(2))) {
					score1 = Double.valueOf(excel.get(2).toString().replaceAll("\\s*", ""));
				}
				if (StringUtils.isEmpty(excel.get(1)) == false
						&& StringUtils.isEmpty(excel.get(2).toString().replaceAll("\\s*", "")) == false) {
					score1 = Double.valueOf(excel.get(2).toString().replaceAll("\\s*", ""));
					// 枪械项目报名详情
					TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, trainFirearm.getId(),
							policeId);
					if (fItem != null) {
						fItem.setAchievement(score1);
						fItem.setAchievementStr(score1 + "环");
						fItem.setUpdateDate(new Date());
						fItem.setAchievementDate(new Date());
						// 根据项目id/组别查询算分规则
						TrainProjectRule ruleItem = trainService.trainProjectPoliceRuleItem(2, null);
						if (ruleItem.getSymbol() == 1) {// 1>= 2> 3<= 4< 5=
							if (score1 >= ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 2) {
							if (score1 > ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 3) {
							if (score1 <= ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 4) {
							if (score1 < ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 5) {
							if (score1 == ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						}
						finalList.add(fItem);
					}else {
						fItem = new TrainFirearmAchievement();
						fItem.setPoliceId(policeId);
						fItem.setTrainFirearmId(id);
						fItem.setRegistrationDate(new Date());
						fItem.setAchievementDate(new Date());
						fItem.setIsSign(2);
						fItem.setSignDate(new Date());
						fItem.setTrainProjectType(id);
						fItem.setAchievement(score1);
						fItem.setAchievementStr(score1 + "环");
						fItem.setIsSubmit(1);
						fItem.setQrCode("/train-qrcode/physical-870c515b-6d06-445f-bf11-c143bdc6a878.jpg");
						// 根据项目id/组别查询算分规则
						TrainProjectRule ruleItem = trainService.trainProjectPoliceRuleItem(2, null);
						if (ruleItem.getSymbol() == 1) {// 1>= 2> 3<= 4< 5=
							if (score1 >= ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 2) {
							if (score1 > ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 3) {
							if (score1 <= ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 4) {
							if (score1 < ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						} else if (ruleItem.getSymbol() == 5) {
							if (score1 == ruleItem.getQualifiedFirearmA()) {
								fItem.setAchievementGrade(2);
							} else {
								fItem.setAchievementGrade(1);
							}
						}
						insertList.add(fItem);
					}
				}
			}
			// 批量修改警员枪械成绩
			if(finalList.size() > 0) {
				trainService.trainFirearmAchievementUpdateBatch(finalList);
			}
			//批量添加警员枪械成绩
			if (insertList.size() > 0) {
				trainService.trainFirearmAchievementCreatBatch(insertList);
			}
			trainService.trainFirearmUpdate(trainFirearm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

}
