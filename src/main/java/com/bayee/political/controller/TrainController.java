package com.bayee.political.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.bayee.political.service.*;
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
 * @version 2020???9???28??? ??????9:41:36
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

	@Autowired
	TotalRiskDetailsService totalRiskDetailsService;

	@Value("${train_images}")
	private String imageDirectory; // = "uploads";

	@Value("${file_container}")
	private String root;// ?????????

	@Value("${train_coverImg}")
	private String coverImg;// ???????????????

	@Value("${train_contentImg}")
	private String contentImg;// ???????????????

	@Value("${train_QR_code}")
	private String trainQRCode;// ???????????????

	@Value("${train_QR_code_icon}")
	private String trainQRCodeIcon;// ???????????????

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	String currentYearStr = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

	// ????????????????????????
	@RequestMapping(value = "/train/in/progress/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainInProgressList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ????????????????????????
		List<TrainPhysical> list = trainService.trainInProgressList(policeId, 2, "desc");// status=2
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ???????????????????????????
	@RequestMapping(value = "/train/start/right/now/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainStartRightNowList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ???????????????????????????
		List<TrainPhysical> list = trainService.trainInProgressList(policeId, 1, "asc");// status=1
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int timeItem = list.get(i).getTimeChange();
				if (timeItem == 0) {
					list.get(i).setCreationDateStr("1???????????????");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "???????????????");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "???????????????");
				} else if (timeItem >= 1440 && timeItem < 2880) {
					String dateTime = sdf.format(list.get(i).getTrainStartDate()).substring(11, 16);
					list.get(i).setCreationDateStr("??????" + dateTime + "??????");
				} else if (timeItem >= 2880 && timeItem < 4320) {
					String dateTime = sdf.format(list.get(i).getTrainStartDate()).substring(11, 16);
					list.get(i).setCreationDateStr("??????" + dateTime + "??????");
				} else if (timeItem >= 4320) {
					int yearInt = Integer.valueOf(currentYearStr);
					int createYear = Integer.valueOf(sdf.format(list.get(i).getTrainStartDate()).substring(0, 4));
					if (yearInt == createYear) {
						list.get(i).setCreationDateStr(
								sdf.format(list.get(i).getTrainStartDate()).substring(5, 10) + "??????");
					} else {
						String timeString = sdf.format(list.get(i).getTrainStartDate()).substring(0, 10);
						list.get(i).setCreationDateStr(timeString + "??????");
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

	// ????????????????????????
	@RequestMapping(value = "/train/physical/page/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainPhysicalPageList(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainPhysicalStatistics item = new TrainPhysicalStatistics();
		// ????????????????????????
		List<TrainPhysical> list = trainService.trainPhysicalList(policeId, type);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getObjectId() == 1) {
					// ?????????????????????????????????
					List<CalculationChart> imageList = trainService.trainPhysicalHeadImageList(list.get(i).getId(),
							policeId, 5);
					list.get(i).setHeadImageList(imageList);
				} else if (list.get(i).getObjectId() == 2) {
					// ???????????????????????????
					List<CalculationChart> imageList = trainService.trainFirearmHeadImageList(list.get(i).getId(),
							policeId, 5);
					list.get(i).setHeadImageList(imageList);
				}
			}
		}
		// ??????????????????????????????
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

	// ????????????????????????
	@RequestMapping(value = "/train/physical/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainPhysicalItem(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ??????policeId????????????
		User user = userService.policeItem(policeId, null, null);
		TrainPhysical item = new TrainPhysical();
		if (objectId == 1) {
			// ????????????????????????
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
				// ??????????????????
				List<TrainGroupPolice> groupList = trainService.trainGroupPoliceList(policeId);
				if (groupList.size() > 0) {
					item.setTrainGroupNames(groupList.get(0).getGroupName());
				}
				List<CalculationChart> totalList = new ArrayList<CalculationChart>();
				int num = 4;
				// ??????????????????????????????????????????
				CalculationChart imageItem = trainService.trainPhysicalHeadImageItem(id, policeId);
				if (imageItem != null) {
					totalList.add(imageItem);
				} else {
					num = 5;
				}
				// ?????????????????????????????????(??????????????????)
				List<CalculationChart> imageList = trainService.trainPhysicalHeadImageList(id, policeId, num);
				if (imageList.size() > 0) {
					totalList.addAll(imageList);
				}
				if (totalList.size() > 0) {
					item.setHeadImageList(totalList);
				}
				// ??????????????????????????????????????????
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
				// ????????????????????????????????????
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
						item.setCreationDateStr("??????" + timeString);
					}
				}
			}
		} else if (objectId == 2) {
			// ??????????????????
			item = trainService.trainPhFirearmItem(id);
			if (item != null) {
				if (user != null) {
					item.setPoliceId(policeId);
					item.setPoliceName(user.getName());
				}
				if (item.getType() == 1) {
					item.setTrainGroupNames("??????");
				} else {
					item.setTrainGroupNames("??????");
				}
				List<CalculationChart> totalList = new ArrayList<CalculationChart>();
				int num = 4;
				// ????????????????????????????????????
				CalculationChart imageItem = trainService.trainFirearmHeadImageItem(id, policeId);
				if (imageItem != null) {
					totalList.add(imageItem);
				} else {
					num = 5;
				}
				// ???????????????????????????(??????????????????)
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
				// ????????????????????????
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
						item.setCreationDateStr("??????" + timeString);
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

	// ??????????????????????????????
	private String getReportSurplusTime(Integer timeItem) {
		String surplusTime = null;
		if (timeItem == 0) {
			surplusTime = "??????0??????";
		} else if (timeItem < 60 && timeItem > 0) {
			surplusTime = "??????" + timeItem + "??????";
		} else if (timeItem >= 60 && timeItem < 1440) {
			int timeInt = (int) Math.floor(timeItem / 60);
			surplusTime = "??????" + timeInt + "??????";
		} else if (timeItem >= 1440) {
			int timeInt = (int) Math.floor(timeItem / 60 / 24);
			surplusTime = "??????" + timeInt + "???";
		}
		return surplusTime;
	}

	// ????????????????????????????????????????????????
	private String getReportStartTime(Integer timeItem) {
		String surplusTime = null;
		if (timeItem == 0) {
			surplusTime = "??????0???????????????";
		} else if (timeItem < 60 && timeItem > 0) {
			surplusTime = "??????" + timeItem + "???????????????";
		} else if (timeItem >= 60 && timeItem < 1440) {
			int timeInt = (int) Math.floor(timeItem / 60);
			surplusTime = "??????" + timeInt + "???????????????";
		} else if (timeItem >= 1440) {
			int timeInt = (int) Math.floor(timeItem / 60 / 24);
			surplusTime = "??????" + timeInt + "????????????";
		}
		return surplusTime;
	}

	// ??????????????????(??????)
	@ResponseBody
	@RequestMapping(value = "/train/physical/sign/up/creat", method = RequestMethod.POST)
	public DataListReturn trainPhysicalSignUpCreat(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		TrainPhysicalAchievement item = new TrainPhysicalAchievement();
		// ????????????????????????????????????
		TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, id, policeId);
		if (aItem == null) {
			// ????????????????????????
			TrainPhysical tItem = trainService.trainPhysicalItem(id);
			long startTimeLong = tItem.getRegistrationStartDate().getTime();
			long endTimeLong = tItem.getRegistrationEndDate().getTime();
			long currentTime = new Date().getTime();
			if (currentTime < startTimeLong) {
				dlr.setStatus(false);
				dlr.setMessage("???????????????");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			} else if (currentTime > endTimeLong) {
				dlr.setStatus(false);
				dlr.setMessage("???????????????");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
			item.setTrainPhysicalId(id);
			item.setPoliceId(policeId);
			item.setRegistrationDate(new Date());
			// ??????????????????
			List<TrainGroupPolice> groupList = trainService.trainGroupPoliceList(policeId);
			item.setTrainGroupId(groupList.get(0).getId());
			item.setIsSign(1);
			item.setAchievementGrade(1);
			item.setCreationDate(new Date());
			// ??????????????????????????????????????????????????????
			int count = 0;
			List<TrainProject> projectList = trainService.trainPoliceBelongToList(id, groupList.get(0).getId());
			if (projectList.size() > 0) {
				String qrName = "physical-" + UUID.randomUUID().toString() + ".jpg";
				item.setQrCode(trainQRCode + qrName);
				// ???????????????
				new QRCode(root + trainQRCodeIcon).encodeQRCode("1," + id + "," + policeId, 500, 500,
						root + trainQRCode + qrName);
				count = trainService.trainPhysicalAchievementCreat(item);
				for (int i = 0; i < projectList.size(); i++) {
					// ??????????????????
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
				dlr.setMessage("????????????????????????????????????????????????????????????");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
			if (count > 0) {
				dlr.setStatus(true);
				dlr.setMessage("????????????");
				dlr.setResult(item);
				dlr.setCode(StatusCode.getSuccesscode());
			} else {
				dlr.setStatus(false);
				dlr.setMessage("????????????");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
			}
			return dlr;
		} else {
			dlr.setStatus(false);
			dlr.setMessage("?????????????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
	}

	// ????????????(??????)
	@ResponseBody
	@RequestMapping(value = "/train/firearm/sign/up/creat", method = RequestMethod.POST)
	public DataListReturn trainFirearmSignUpCreat(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		TrainFirearmAchievement item = new TrainFirearmAchievement();
		// ????????????????????????
		TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, id, policeId);
		if (fItem == null) {
			// ??????????????????
			TrainFirearm tItem = trainService.trainFirearmItem(id);
//			List<String> list = Arrays.asList(tItem.getInvolvementPoliceIds().split(","));
			long startTimeLong = tItem.getRegistrationStartDate().getTime();
			long endTimeLong = tItem.getRegistrationEndDate().getTime();
			long currentTime = new Date().getTime();
			if (currentTime < startTimeLong) {
				dlr.setStatus(false);
				dlr.setMessage("???????????????");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			} else if (currentTime > endTimeLong) {
				dlr.setStatus(false);
				dlr.setMessage("???????????????");
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
			// ???????????????
			new QRCode(root + trainQRCodeIcon).encodeQRCode("2," + id + "," + policeId, 500, 500,
					root + trainQRCode + qrName);
			int count = trainService.trainFirearmAchievementCreat(item);
			if (count > 0) {
				dlr.setStatus(true);
				dlr.setMessage("????????????");
				dlr.setResult(item);
				dlr.setCode(StatusCode.getSuccesscode());
				return dlr;
			} else {
				dlr.setStatus(false);
				dlr.setMessage("????????????");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
		} else {
			dlr.setStatus(false);
			dlr.setMessage("?????????????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
	}

	// ??????????????????
	@RequestMapping(value = "/train/recent/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainRecentList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ??????????????????
		List<TrainPhysical> list = trainService.trainRecentList(policeId);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int timeItem = Math.abs(list.get(i).getTimeChange());
				if (list.get(i).getStatus() == 1) {
					if (timeItem == 0) {
						list.get(i).setCreationDateStr("1???????????????");
					} else if (timeItem < 60 && timeItem > 0) {
						list.get(i).setCreationDateStr(timeItem + "???????????????");
					} else if (timeItem >= 60 && timeItem < 1440) {
						int timeInt = (int) Math.floor(timeItem / 60);
						list.get(i).setCreationDateStr(timeInt + "???????????????");
					} else if (timeItem >= 1440) {
						int timeInt = (int) Math.floor(timeItem / 60 / 24);
						list.get(i).setCreationDateStr(timeInt + "????????????");
					}
				} else {
					if (timeItem >= 0 && timeItem < 1440) {
						list.get(i).setCreationDateStr("???1???");
					} else if (timeItem >= 1440) {
						int timeInt = (int) Math.floor(timeItem / 60 / 24) + 1;
						list.get(i).setCreationDateStr("???" + timeInt + "???");
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

	// ??????????????????
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
		// ????????????????????????
		List<TrainPhysical> list = trainService.trainMyList(policeId, type, status, pageSize, pageNum);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getStatus() == 1) {
					int timeItem = list.get(i).getTimeChange();
					if (timeItem == 0) {
						list.get(i).setCreationDateStr("1???????????????");
					} else if (timeItem < 60 && timeItem > 0) {
						list.get(i).setCreationDateStr(timeItem + "???????????????");
					} else if (timeItem >= 60 && timeItem < 1440) {
						int timeInt = (int) Math.floor(timeItem / 60);
						list.get(i).setCreationDateStr(timeInt + "???????????????");
					} else if (timeItem >= 1440 && timeItem < 2880) {
						String dateTime = sdf.format(list.get(i).getTrainStartDate()).substring(11, 16);
						list.get(i).setCreationDateStr("??????" + dateTime + "??????");
					} else if (timeItem >= 2880 && timeItem < 4320) {
						String dateTime = sdf.format(list.get(i).getTrainStartDate()).substring(11, 16);
						list.get(i).setCreationDateStr("??????" + dateTime + "??????");
					} else if (timeItem >= 4320) {
						int yearInt = Integer.valueOf(currentYearStr);
						int createYear = Integer.valueOf(sdf.format(list.get(i).getTrainStartDate()).substring(0, 4));
						if (yearInt == createYear) {
							list.get(i).setCreationDateStr(
									sdf.format(list.get(i).getTrainStartDate()).substring(5, 10) + "??????");
						} else {
							String timeString = sdf.format(list.get(i).getTrainStartDate()).substring(0, 10);
							list.get(i).setCreationDateStr(timeString + "??????");
						}
					}
				}
			}
		}
		// ??????????????????????????????
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

	// ??????????????????
	@RequestMapping(value = "/train/my/entry/record/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainMyEntryRecordList(@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = true) Integer trainPhysicalId,
			@RequestParam(value = "policeId", required = true) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<TrainPhysicalAchievementDetails> list = new ArrayList<TrainPhysicalAchievementDetails>();
		// ??????policeId????????????
		User user = userService.policeItem(policeId, null, null);
		if (objectId == 1) {// 1????????????2??????
			TrainPhysical item = trainService.trainPhysicalItem(trainPhysicalId);
			if (item.getStatus() != 1) {
				// ????????????????????????????????????
				TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, trainPhysicalId,
						policeId);
				if (aItem != null) {
					// ????????????????????????
					list = trainService.trainMyEntryRecordList(trainPhysicalId, aItem.getId(), policeId);
					for (int i = 0; i < list.size(); i++) {
						String sort = null;
						if (list.get(i).getSortNum() == 1) {// ??????
							sort = "asc";
						} else if (list.get(i).getSortNum() == 2) {// ??????
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
		} else if (objectId == 2) {// 2??????
			TrainFirearm fitem = trainService.trainFirearmItem(trainPhysicalId);
			if (fitem.getStatus() != 1) {
				// ??????????????????
				TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, trainPhysicalId,
						policeId);
				if (fItem != null) {
					// ????????????????????????
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
					// ??????????????????????????????
					List<CalculationChart> projectList = trainService.trainProjectFirearmRankList(trainPhysicalId,
							policeId);
					String sort = null;
					if (projectList.get(0).getNum() == 1) {// ??????
						sort = "asc";
					} else if (projectList.get(0).getNum() == 2) {// ??????
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

	// ???????????????
	@RequestMapping(value = "/train/my/entry/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainMyEntryRankList(@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<TrainRankList> list = new ArrayList<TrainRankList>();
		if (objectId == 1) {// 1????????????
			TrainPhysical titem = trainService.trainPhysicalItem(trainPhysicalId);
			if (titem.getStatus() != 1) {
				// ????????????????????????????????????
				TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, trainPhysicalId,
						policeId);
				if (aItem != null) {
					// ????????????????????????
					List<CalculationChart> projectList = trainService.trainProjectRankList(trainPhysicalId,
							aItem.getId(), policeId, null);
					for (int i = 0; i < projectList.size(); i++) {
						String sort = null;
						if (projectList.get(i).getNum() == 1) {// ??????
							sort = "asc";
						} else if (projectList.get(i).getNum() == 2) {// ??????
							sort = "desc";
						}
						TrainRankList item = new TrainRankList();
						item.setProjectItem(projectList.get(i));
						// ????????????????????????????????????
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
		} else if (objectId == 2) {// 2??????
			TrainFirearm fitem = trainService.trainFirearmItem(trainPhysicalId);
			if (fitem.getStatus() != 1) {
				// ??????????????????
				TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, trainPhysicalId,
						policeId);
				if (fItem != null) {
					// ??????????????????????????????
					List<CalculationChart> projectList = trainService.trainProjectFirearmRankList(trainPhysicalId,
							policeId);
					for (int i = 0; i < projectList.size(); i++) {
						String sort = null;
						if (projectList.get(i).getNum() == 1) {// ??????
							sort = "asc";
						} else if (projectList.get(i).getNum() == 2) {// ??????
							sort = "desc";
						}
						TrainRankList item = new TrainRankList();
						item.setProjectItem(projectList.get(i));
						// ????????????????????????????????????
						List<TrainRank> rankList = trainService.trainPersonalFirearmRankList(projectList.get(i).getId(),
								trainPhysicalId, policeId, null, sort);
						if (rankList.size() > 0) {
							for (int k = 0; k < rankList.size(); k++) {
								if (rankList.get(k).getUnitName().equals("??????")) {
									rankList.get(k).setUnitName("???");
								} else if (rankList.get(k).getUnitName().equals("?????????")) {
									rankList.get(k).setUnitName("???");
								}
							}
						}
						item.setRankList(rankList);
						TrainRank personalRankItem = trainService.trainPersonalFirearmRankItem(
								projectList.get(i).getId(), trainPhysicalId, policeId, null, sort);
						if (personalRankItem != null) {
							if (personalRankItem.getUnitName().equals("??????")) {
								personalRankItem.setUnitName("???");
							} else if (personalRankItem.getUnitName().equals("?????????")) {
								personalRankItem.setUnitName("???");
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

	// ??????????????????
	@RequestMapping(value = "/train/my/entry/more/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainMyEntryMoreRankList(
			@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "projectId", required = false) Integer projectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<TrainRankList> list = new ArrayList<TrainRankList>();
		if (objectId == 1) {// 1????????????
			// ????????????????????????????????????
			TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, trainPhysicalId, policeId);
			if (aItem != null) {
				// ????????????????????????
				List<CalculationChart> projectList = trainService.trainProjectRankList(trainPhysicalId, aItem.getId(),
						policeId, projectId);
				for (int i = 0; i < projectList.size(); i++) {
					String sort = null;
					if (projectList.get(i).getNum() == 1) {// ??????
						sort = "asc";
					} else if (projectList.get(i).getNum() == 2) {// ??????
						sort = "desc";
					}
					TrainRankList item = new TrainRankList();
					item.setProjectItem(projectList.get(i));
					// ????????????????????????????????????(???????????????)
					List<TrainRank> rankList = trainService.trainPersonalMoreRankList(projectList.get(i).getId(),
							trainPhysicalId, policeId, null, sort);
					item.setRankList(rankList);
					TrainRank personalRankItem = trainService.trainPersonalRankItem(projectList.get(i).getId(),
							trainPhysicalId, policeId, null, sort);
					item.setRankItem(personalRankItem);
					list.add(item);
				}
			}
		} else if (objectId == 2) {// 2??????
			// ??????????????????
			TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, trainPhysicalId, policeId);
			if (fItem != null) {
				// ??????????????????????????????
				List<CalculationChart> projectList = trainService.trainProjectFirearmRankList(trainPhysicalId,
						policeId);
				for (int i = 0; i < projectList.size(); i++) {
					String sort = null;
					if (projectList.get(i).getNum() == 1) {// ??????
						sort = "asc";
					} else if (projectList.get(i).getNum() == 2) {// ??????
						sort = "desc";
					}
					TrainRankList item = new TrainRankList();
					item.setProjectItem(projectList.get(i));
					// ????????????????????????????????????
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

	// ??????????????????
	@RequestMapping(value = "/train/my/entry/project/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainMyEntryProjectList(
			@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<CalculationChart> projectList = new ArrayList<CalculationChart>();
		if (objectId == 1) {// 1????????????
			// ????????????????????????????????????
			TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, trainPhysicalId, policeId);
			if (aItem != null) {
				// ????????????????????????
				projectList = trainService.trainProjectRankList(trainPhysicalId, aItem.getId(), policeId, null);
			}
		} else if (objectId == 2) {// 2??????
			// ??????????????????
			TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, trainPhysicalId, policeId);
			if (fItem != null) {
				// ??????????????????????????????
				projectList = trainService.trainProjectFirearmRankList(trainPhysicalId, policeId);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(projectList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ???????????????????????????
	@RequestMapping(value = "/train/sign/in/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainSignInItem(@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainSignIn sItem = new TrainSignIn();
		if (objectId == 1) {// 1????????????
			// ????????????????????????????????????
			TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, id, policeId);
			if (aItem != null) {
				sItem.setObjectId(1);
				sItem.setTrainPhysicalId(id);
				sItem.setTrainPhysicalName(aItem.getTrainPhysicalName());
				sItem.setPoliceId(policeId);
				sItem.setName(aItem.getName());
				sItem.setHeadImage(aItem.getHeadImage());
				// ??????????????????????????????
				List<TrainPhysicalAchievementDetails> projecList = trainService.trainSignInProjectList(id,
						aItem.getId(), policeId, null);
				if (projecList.size() > 0) {
					sItem.setProjecList(projecList);
				}
				// ??????????????????
				List<TrainGroupPolice> groupList = trainService.trainGroupPoliceList(policeId);
				if (groupList.size() > 0) {
					sItem.setGroupName(groupList.get(0).getGroupName());
				}
				dlr.setResult(sItem);
			} else {
				dlr.setResult(null);
			}
		} else if (objectId == 2) {// 2??????
			// ??????????????????
			TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, id, policeId);
			if (fItem != null) {
				TrainPhysical ttitem = trainService.trainPhFirearmItem(id);
				if (ttitem != null) {
					if (ttitem.getType() == 1) {
						sItem.setGroupName("??????");
					} else {
						sItem.setGroupName("??????");
					}
				}
				sItem.setObjectId(2);
				sItem.setTrainPhysicalId(id);
				sItem.setTrainPhysicalName(fItem.getTrainFirearmName());
				sItem.setPoliceId(policeId);
				sItem.setName(fItem.getName());
				sItem.setHeadImage(fItem.getHeadImage());
				// ??????????????????????????????
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

	// ???????????????
	@ResponseBody
	@RequestMapping(value = { "/train/sign/in/submit" }, method = RequestMethod.POST)
	public ResponseEntity<?> trainSignInSubmit(@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "scorerPoliceId", required = false) String scorerPoliceId,
			@RequestParam(value = "resultJson", required = false) String resultJson) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<TrainPhysicalAchievementDetails> projecList = new ArrayList<TrainPhysicalAchievementDetails>();
		if (objectId == 1) {// 1????????????
			// ????????????????????????????????????
			TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, id, policeId);
			if (aItem != null) {
				// ?????????????????????????????????????????????
				TrainPhysical scorerItem = trainService.physicalScorerPoliceItem(id, scorerPoliceId);
				if (scorerItem == null) {
					dlr.setStatus(false);
					dlr.setMessage("??????????????????????????????");
					dlr.setCode(StatusCode.getFailcode());
					return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
				} else {
					long endTimeLong = aItem.getTrainEndDate().getTime();
					long currentTime = new Date().getTime();
					if (currentTime < endTimeLong) {// ????????????????????????????????????
						// ??????????????????????????????
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
								// ???????????????????????????
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
							dlr.setMessage("????????????");
							dlr.setResult(projecList);
							dlr.setCode(StatusCode.getSuccesscode());
						} else {
							dlr.setStatus(false);
							dlr.setMessage("????????????");
							dlr.setCode(StatusCode.getFailcode());
						}
					} else {
						dlr.setStatus(false);
						dlr.setMessage("???????????????");
						dlr.setCode(StatusCode.getFailcode());
					}
				}
			} else {
				dlr.setStatus(false);
				dlr.setMessage("????????????????????????");
				dlr.setCode(StatusCode.getFailcode());
			}
		} else if (objectId == 2) {// 2??????
			// ??????????????????
			TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, id, policeId);
			if (fItem != null) {
				// ???????????????????????????????????????????????????
				TrainFirearm scorerItem = trainService.firearmScorerPoliceItem(id, scorerPoliceId);
				if (scorerItem == null) {
					dlr.setStatus(false);
					dlr.setMessage("??????????????????????????????");
					dlr.setCode(StatusCode.getFailcode());
					return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
				} else {
					long endTimeLong = fItem.getTrainEndDate().getTime();
					long currentTime = new Date().getTime();
					if (currentTime < endTimeLong) {// ????????????????????????????????????
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
							dlr.setMessage("????????????");
							dlr.setResult(projecList);
							dlr.setCode(StatusCode.getSuccesscode());
						} else if (fItem.getIsSign() == 2) {
							dlr.setStatus(false);
							dlr.setMessage("????????????");
							dlr.setCode(StatusCode.getFailcode());
						}
					} else {
						dlr.setStatus(false);
						dlr.setMessage("???????????????");
						dlr.setCode(StatusCode.getFailcode());
					}
				}
			} else {
				dlr.setStatus(false);
				dlr.setMessage("????????????????????????");
				dlr.setCode(StatusCode.getFailcode());
			}
		}
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????
	@RequestMapping(value = "/train/record/score/type/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainRecordScoreTypeItem(
			@RequestParam(value = "policeId", required = true) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainRecordScoreType item = new TrainRecordScoreType();
		// ???????????????????????????
		int trainNum = trainService.trainPhysicalNoScoreNum(policeId);
		item.setTrainNum(trainNum);
		// ?????????????????????
		int matchNum = trainMatchService.matchNoScoreNum(policeId);
		item.setMatchNum(matchNum);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ???????????????????????????
	@RequestMapping(value = "/train/record/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainRecordScoreList(@RequestParam(value = "policeId", required = true) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ??????policeId????????????
		User user = userService.policeItem(policeId, null, null);
		// ???????????????????????????
		List<TrainRecordScore> list = trainService.trainPhysicalNoScoreList(policeId);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getProportion() > 0 && list.get(i).getProportion() < 100) {
					list.get(i).setStatusName("?????????");
				} else if (list.get(i).getProportion() == 0) {
					list.get(i).setStatusName("?????????");
				} else {
					list.get(i).setStatusName("?????????");
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

	// ??????????????????????????????
	@RequestMapping(value = "/train/record/over/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainRecordOverScoreList(
			@RequestParam(value = "policeId", required = true) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ??????????????????????????????
		List<TrainRecordScore> list = trainService.trainPhysicalOverScoreList(policeId);
		// ??????policeId????????????
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

	// ?????????????????????????????????
	@RequestMapping(value = "/train/record/project/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainRecordProjectScoreList(
			@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = true) Integer trainPhysicalId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainProjectNeedScore item = new TrainProjectNeedScore();
		if (objectId == 1) {// ????????????
			// ?????????????????????????????????????????????
			List<TrainProject> list = trainService.trainRecordProjectPhysicalList(trainPhysicalId);
			item.setProjectList(list);
			item.setTotalEnterNum(list.size());
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getStatusName().equals("?????????")) {
					num = num + 1;
				}
				list.get(i).setIsU(1);
			}
			item.setEnterNum(num);
		} else if (objectId == 2) {
			// ?????????????????????????????????
			List<TrainProject> list = trainService.trainRecordProjectFirearmList(trainPhysicalId);
			item.setProjectList(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????????????????
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
			dlr.setMessage("????????????");
			dlr.setResult(1);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ????????????????????????????????????
//	@RequestMapping(value = "/train/record/police/height/list", method = RequestMethod.GET)
//	public ResponseEntity<?> trainRecordPoliceHeightList(
//			@RequestParam(value = "projectId", required = true) Integer projectId,
//			@RequestParam(value = "trainPhysicalId", required = true) Integer trainPhysicalId) throws ApiException {
//		DataListReturn dlr = new DataListReturn();
//		// ??????????????????
//		TrainRecordPolice item = trainService.trainRecordPoliceItem(projectId, trainPhysicalId);
//		if (item != null) {
//			// ?????????????????????????????????????????????
//			List<User> list = userService.trainRecordPoliceHeightList(projectId, trainPhysicalId);
//			item.setUserList(list);
//		}
//		dlr.setStatus(true);
//		dlr.setMessage("success");
//		dlr.setResult(item);
//		dlr.setCode(StatusCode.getSuccesscode());
//		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
//	}

	// ????????????????????????????????????
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

	// ????????????????????????
	@RequestMapping(value = "/train/record/police/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainRecordPoliceScoreList(
			@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "projectId", required = true) Integer projectId,
			@RequestParam(value = "trainPhysicalId", required = true) Integer trainPhysicalId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (objectId == 1) {// ????????????
			// ??????????????????
			TrainRecordPolice item = trainService.trainRecordPoliceItem(projectId, trainPhysicalId);
			if (item != null) {
				// ????????????????????????
				List<TrainRank> list = trainService.trainRecordPoliceScoreList(projectId, trainPhysicalId);
				item.setScoreList(list);
				dlr.setResult(item);
			}
		} else if (objectId == 2) {// ??????
			// ????????????????????????
			TrainRecordPolice item = trainService.trainRecordFirearmPoliceItem(projectId, trainPhysicalId);
			if (item != null) {
				// ??????????????????????????????
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

	// ??????????????????????????????
	@RequestMapping(value = { "/train/physical/record/police/score/update" }, method = RequestMethod.POST)
	public ResponseEntity<?> trainPhysicalRecordPoliceScoreUpdate(
			@RequestParam(value = "resultJson", required = false) String resultJson) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		Date nowDate = new Date();
		String policeId = null;
		Integer trainPhysicalId = null;
		Integer projectId = null;
//		String ss = "[{'policeId':'117291','projectId':'19','trainPhysicalId':'19','achievement':'4.5','achievementFirst':'4','achievementSecond':'30','achievementStr':'4???30???'},"
//				+ "{'policeId':'117565','projectId':'19','trainPhysicalId':'19','achievement':'5.0','achievementFirst':'5','achievementSecond':'0','achievementStr':'5???0???'},"
//				+ "{'policeId':'117020','projectId':'19','trainPhysicalId':'19','achievement':'5.5','achievementFirst':'5','achievementSecond':'30','achievementStr':'5???30???'},"
//				+ "{'policeId':'116629','projectId':'19','trainPhysicalId':'19','achievement':'6.5','achievementFirst':'6','achievementSecond':'30','achievementStr':'6???30???'},"
//				+ "{'policeId':'117195','projectId':'19','trainPhysicalId':'19','achievement':'7.75','achievementFirst':'7','achievementSecond':'45','achievementStr':'7???45???'}]";
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
				// ????????????????????????
				TrainPhysical trainPhysicalItem = trainService.trainPhysicalItem(trainPhysicalId);
				item.setSignDate(trainPhysicalItem.getTrainEndDate());
				// ??????????????????????????????
				List<TrainPhysicalAchievementDetails> projecList = trainService.trainSignInProjectList(trainPhysicalId,
						null, policeId, 1);
				if (projecList.size() == 0) {
					signDate = trainPhysicalItem.getTrainEndDate();
					signInt = 2;
				}
			}
			// ??????????????????????????????????????????
			TrainPhysicalProjectRecord projectItem = trainService.projectNamesItem(jo.getInteger("trainPhysicalId"),
					jo.getString("policeId"));
			// ????????????id/????????????????????????
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
			// ????????????????????????????????????
			TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null,
					jo.getInteger("trainPhysicalId"), jo.getString("policeId"));
			aItem.setAchievementDate(new Date());
			aItem.setUpdateDate(new Date());
			// ????????????????????????????????????
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
				// ??????????????????????????????(????????????)
				TrainGetMedal getItem2 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 2);
				if (getItem2 == null) {
					TrainGetMedal gItem = new TrainGetMedal();
					gItem.setMedalId(2);
					gItem.setPoliceId(jo.getString("policeId"));
					gItem.setGetDate(new Date());
					gItem.setCreationDate(new Date());
					trainMatchService.trainGetMedalCreat(gItem);
				}
				// ??????????????????????????????(????????????)
				TrainGetMedal getItem3 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 3);
				if (getItem3 == null) {
					// ????????????5????????????????????????????????????
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
				// ??????????????????????????????(????????????)
				TrainGetMedal getItem4 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 4);
				if (getItem4 == null) {
					// ????????????5????????????????????????????????????
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
				// ??????????????????????????????(???????????????)
				TrainGetMedal getItem5 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 5);
				if (getItem5 == null) {
					// ??????3???????????????????????????????????????
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

				// ??????????????????????????????(????????????)
				TrainGetMedal getItem6 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 6);
				if (getItem6 == null) {// ??????1???????????????????????????Top3?????????????????????
					// ??????policeId????????????
					User user = userService.policeItem(policeId, null, null);
					// ????????????????????????
					List<CalculationChart> projectList = trainService.trainProjectRankList(trainPhysicalId,
							aItem.getId(), policeId, null);
					int totalRankNum = projectList.size();
					int rankInt = 0;
					for (int i = 0; i < projectList.size(); i++) {
						String sort = null;
						if (projectList.get(i).getNum() == 1) {// ??????
							sort = "asc";
						} else if (projectList.get(i).getNum() == 2) {// ??????
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

				// ??????????????????????????????(????????????)
				TrainGetMedal getItem7 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 7);
				if (getItem7 == null) {// ??????3??????????????????????????????Top3?????????????????????
					// ??????policeId????????????
					User user = userService.policeItem(policeId, null, null);
					// ????????????????????????
					List<TrainPhysical> list = trainService.trainMyList(policeId, null, null, 3, 0);
					boolean isTrue = true;
					if (list.size() == 3) {
						for (int i = 0; i < list.size(); i++) {
							if (list.get(i).getObjectId() == 1) {// ????????????
								// ????????????????????????
								List<CalculationChart> projectList = trainService
										.trainProjectRankList(list.get(i).getId(), aItem.getId(), policeId, null);
								for (int k = 0; k < projectList.size(); k++) {
									String sort = null;
									if (projectList.get(k).getNum() == 1) {// ??????
										sort = "asc";
									} else if (projectList.get(k).getNum() == 2) {// ??????
										sort = "desc";
									}
									TrainRank personalRankItem = trainService.trainPersonalRankItem(
											projectList.get(k).getId(), list.get(i).getId(), policeId, null, sort);
									if (personalRankItem == null || personalRankItem.getRankId() == null
											|| personalRankItem.getRankId() > 3) {
										isTrue = false;
									}
								}
							} else if (list.get(i).getObjectId() == 2) {// ??????
								// ??????????????????????????????
								List<CalculationChart> projectList = trainService
										.trainProjectFirearmRankList(list.get(i).getId(), policeId);
								String sort = null;
								if (projectList.get(0).getNum() == 1) {// ??????
									sort = "asc";
								} else if (projectList.get(0).getNum() == 2) {// ??????
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
		dlr.setMessage("????????????");
		dlr.setResult(1);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????????????????
	@RequestMapping(value = { "/train/record/police/score/update" }, method = RequestMethod.POST)
	public ResponseEntity<?> trainRecordPoliceScoreUpdate(
			@RequestParam(value = "resultJson", required = false) String resultJson) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		System.out.println(resultJson);
//		String ss = "[{'policeId':'112225','projectId':2,'trainPhysicalId':18,'achievement':36.0,'achievementStr':'36.0???','shootTime':0.0,'isU':1},"
//				+ "{'policeId':'117409','projectId':2,'trainPhysicalId':18,'achievement':9.0,'achievementStr':'9.0???','shootTime':0.0,'isU':1}]";
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
					item.setAchievementStr(String.format("%.2f", item.getAchievement()) + "??????");
				} else {
					item.setAchievement(0.0);
					item.setAchievementStr(0.0 + "??????");
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
			// ????????????id/????????????????????????
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
						// ??????????????????id??????U??????????????????
						List<TrainProjectURule> list = trainService.TrainProjectURuleList(ruleItem.getId());
						double num = 0.0;
						if (jo.getDouble("shootTime") != 0.0 && jo.getDouble("shootTime") != 0) {
							num = jo.getDouble("achievement") / jo.getDouble("shootTime");
						}
						if (list.get(0).getMinNum() <= num && num <= list.get(0).getMaxNum()) {// 1??????2????????????
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
				// ??????????????????????????????(????????????)
				TrainGetMedal getItem1 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 1);
				if (getItem1 == null) {
					// ????????????5??????????????????????????????Top3?????????????????????
					List<TrainPhysical> cList = trainService.trainFirearmContinuityFivePassList(policeId);
					boolean fTure = true;
					if (cList.size() == 5) {
						for (int i = 0; i < cList.size(); i++) {
							// ??????policeId????????????
							User user = userService.policeItem(policeId, null, null);
							// ??????????????????????????????
							List<CalculationChart> projectList = trainService
									.trainProjectFirearmRankList(cList.get(i).getId(), policeId);
							String sort = null;
							if (projectList.get(0).getNum() == 1) {// ??????
								sort = "asc";
							} else if (projectList.get(0).getNum() == 2) {// ??????
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
				// ??????????????????????????????(????????????)
				TrainGetMedal getItem2 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 2);
				if (getItem2 == null) {
					TrainGetMedal gItem = new TrainGetMedal();
					gItem.setMedalId(2);
					gItem.setPoliceId(jo.getString("policeId"));
					gItem.setGetDate(new Date());
					gItem.setCreationDate(new Date());
					trainMatchService.trainGetMedalCreat(gItem);
				}
				// ??????????????????????????????(????????????)
				TrainGetMedal getItem3 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 3);
				if (getItem3 == null) {
					// ????????????5????????????????????????????????????
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
				// ??????????????????????????????(????????????)
				TrainGetMedal getItem4 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 4);
				if (getItem4 == null) {
					// ????????????5????????????????????????????????????
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
				// ??????????????????????????????(???????????????)
				TrainGetMedal getItem5 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 5);
				if (getItem5 == null) {
					// ??????3???????????????????????????????????????
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
				// ??????????????????????????????(????????????)
				TrainGetMedal getItem6 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 6);
				if (getItem6 == null) {// ??????1???????????????????????????Top3?????????????????????
					// ??????policeId????????????
					User user = userService.policeItem(policeId, null, null);
					// ??????????????????????????????
					List<CalculationChart> projectList = trainService.trainProjectFirearmRankList(trainFirearmId,
							policeId);
					String sort = null;
					if (projectList.get(0).getNum() == 1) {// ??????
						sort = "asc";
					} else if (projectList.get(0).getNum() == 2) {// ??????
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

				// ??????????????????????????????(????????????)
				TrainGetMedal getItem7 = trainMatchService.trainGetMedalPoliceItem(null, jo.getString("policeId"), 7);
				if (getItem7 == null) {// ??????3??????????????????????????????Top3?????????????????????
					// ??????policeId????????????
					User user = userService.policeItem(policeId, null, null);
					// ????????????????????????
					List<TrainPhysical> list = trainService.trainMyList(policeId, null, null, 3, 0);
					boolean isTrue = true;
					if (list.size() == 3) {
						for (int i = 0; i < list.size(); i++) {
							if (list.get(i).getObjectId() == 1) {// ????????????
								// ????????????????????????????????????
								TrainPhysicalAchievement atItem = trainService.trainPhysicalAchievementItem(null,
										list.get(i).getId(), policeId);
								// ????????????????????????
								List<CalculationChart> projectList = trainService
										.trainProjectRankList(list.get(i).getId(), atItem.getId(), policeId, null);
								for (int k = 0; k < projectList.size(); k++) {
									String sort = null;
									if (projectList.get(k).getNum() == 1) {// ??????
										sort = "asc";
									} else if (projectList.get(k).getNum() == 2) {// ??????
										sort = "desc";
									}
									TrainRank personalRankItem = trainService.trainPersonalRankItem(
											projectList.get(k).getId(), list.get(i).getId(), policeId, null, sort);
									if (personalRankItem == null || personalRankItem.getRankId() == null
											|| personalRankItem.getRankId() > 3) {
										isTrue = false;
									}
								}
							} else if (list.get(i).getObjectId() == 2) {// ??????
								// ??????????????????????????????
								List<CalculationChart> projectList = trainService
										.trainProjectFirearmRankList(list.get(i).getId(), policeId);
								String sort = null;
								if (projectList.get(0).getNum() == 1) {// ??????
									sort = "asc";
								} else if (projectList.get(0).getNum() == 2) {// ??????
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
		dlr.setMessage("????????????");
		dlr.setResult(1);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ????????????????????????
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
			// ?????????????????????????????????
			list = trainService.trainLeaderSignUpList(policeId, signUpStatus, departmentId, pageSize, pageNum);
			// ???????????????????????????????????????
			total = trainService.trainLeaderSignUpCount(policeId, signUpStatus, departmentId);
		} else {
			// ????????????????????????
			list = trainService.trainLeaderList(policeId, status, departmentId, pageSize, pageNum);
			// ??????????????????????????????
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

	// ????????????????????????
	@RequestMapping(value = "/train/recommend/police/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainRecommendPoliceList(
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (objectId == 1) {
			// ????????????????????????????????????
			List<TrainRecommendPolice> list = trainService.trainPhysicalRecommendPoliceList(trainPhysicalId,
					departmentId, policeId);
			dlr.setResult(list);
		} else if (objectId == 2) {
			// ??????????????????????????????
			List<TrainRecommendPolice> list = trainService.trainFirearmRecommendPoliceList(departmentId, policeId);
			dlr.setResult(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????????????????
	@RequestMapping(value = "/train/dep/police/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainDepPoliceList(@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "keywords", required = false) String keywords) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (objectId == 1) {
			// ??????????????????????????????????????????
			List<TrainRecommendPolice> list = trainService.trainPhysicalDepPoliceList(trainPhysicalId, departmentId,
					keywords);
			dlr.setResult(list);
		} else if (objectId == 2) {
			// ????????????????????????????????????
			List<TrainRecommendPolice> list = trainService.trainFirearmDepPoliceList(departmentId, keywords);
			dlr.setResult(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ????????????????????????
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
		// ????????????????????????
		TrainPhysical tItem = trainService.trainPhysicalItem(trainPhysicalId);
		long startTimeLong = tItem.getRegistrationStartDate().getTime();
		long endTimeLong = tItem.getRegistrationEndDate().getTime();
		long currentTime = new Date().getTime();
		if (currentTime < startTimeLong) {
			dlr.setStatus(false);
			dlr.setMessage("???????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		} else if (currentTime > endTimeLong) {
			dlr.setStatus(false);
			dlr.setMessage("???????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		if (tItem.getIsLimit() == 1) {// ????????????
			// ??????????????????????????????????????????
			List<TrainRecommendPolice> list = trainService.trainPhysicalDepPoliceList(trainPhysicalId, departmentId,
					null);
			double totalNum = list.size();
			int needNum = 0;
			if (tItem.getLimitType() == 1) {// ???????????????
				needNum = Integer.valueOf(String.format("%.0f", totalNum * tItem.getLimitNum()));
			} else if (tItem.getLimitType() == 2) {// ???????????????
				needNum = Integer.valueOf(String.format("%.0f", tItem.getLimitNum()));
			}
			if (tItem.getSymbol() == 1) {// 1>= 2> 3<= 4< 5=
				if (policeNum < needNum) {
					dlr.setStatus(false);
					dlr.setMessage("????????????????????????" + needNum + "???????????????");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 2) {
				if (policeNum <= needNum) {
					dlr.setStatus(false);
					dlr.setMessage("??????????????????" + needNum + "???????????????");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 3) {
				if (policeNum > needNum) {
					dlr.setStatus(false);
					dlr.setMessage("????????????????????????" + needNum + "???????????????");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 4) {
				if (policeNum >= needNum) {
					dlr.setStatus(false);
					dlr.setMessage("??????????????????" + needNum + "???????????????");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 5) {
				if (policeNum != needNum) {
					dlr.setStatus(false);
					dlr.setMessage("??????????????????" + needNum + "???????????????");
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
			// ????????????????????????????????????
			TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, id, policeId);
			if (aItem == null) {
				item.setTrainPhysicalId(id);
				item.setPoliceId(policeId);
				item.setRegistrationDate(new Date());
				// ??????????????????
				List<TrainGroupPolice> groupList = trainService.trainGroupPoliceList(policeId);
				item.setTrainGroupId(groupList.get(0).getId());
				item.setIsSign(1);
				item.setCreationDate(new Date());
				// ??????????????????????????????????????????????????????
				List<TrainProject> projectList = trainService.trainPoliceBelongToList(id, groupList.get(0).getId());
				if (projectList.size() > 0) {
					String qrName = "physical-" + UUID.randomUUID().toString() + ".jpg";
					item.setQrCode(trainQRCode + qrName);
					// ???????????????
					new QRCode(root + trainQRCodeIcon).encodeQRCode("1," + id + "," + policeId, 500, 500,
							root + trainQRCode + qrName);
					count = trainService.trainPhysicalAchievementCreat(item);
					for (int i = 0; i < projectList.size(); i++) {
						// ??????????????????
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
					dlr.setMessage("????????????????????????????????????????????????????????????");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else {
				dlr.setStatus(false);
				dlr.setMessage("?????????????????????");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
		}
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("????????????");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
			return dlr;
		} else {
			dlr.setStatus(false);
			dlr.setMessage("????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
	}

	// ????????????????????????
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
		// ??????????????????
		TrainFirearm tItem = trainService.trainFirearmItem(trainPhysicalId);
		long startTimeLong = tItem.getRegistrationStartDate().getTime();
		long endTimeLong = tItem.getRegistrationEndDate().getTime();
		long currentTime = new Date().getTime();
		if (currentTime < startTimeLong) {
			dlr.setStatus(false);
			dlr.setMessage("???????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		} else if (currentTime > endTimeLong) {
			dlr.setStatus(false);
			dlr.setMessage("???????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		if (tItem.getIsLimit() == 1) {// ????????????
			// ????????????????????????????????????
			List<TrainRecommendPolice> list = trainService.trainFirearmDepPoliceList(departmentId, null);
			double totalNum = list.size();
			int needNum = 0;
			if (tItem.getLimitType() == 1) {// ???????????????
				needNum = Integer.valueOf(String.format("%.0f", totalNum * tItem.getLimitNum()));
			} else if (tItem.getLimitType() == 2) {// ???????????????
				needNum = Integer.valueOf(String.format("%.0f", tItem.getLimitNum()));
			}
			if (tItem.getSymbol() == 1) {// 1>= 2> 3<= 4< 5=
				if (policeNum < needNum) {
					dlr.setStatus(false);
					dlr.setMessage("????????????????????????" + needNum + "???????????????");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 2) {
				if (policeNum <= needNum) {
					dlr.setStatus(false);
					dlr.setMessage("??????????????????" + needNum + "???????????????");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 3) {
				if (policeNum > needNum) {
					dlr.setStatus(false);
					dlr.setMessage("????????????????????????" + needNum + "???????????????");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 4) {
				if (policeNum >= needNum) {
					dlr.setStatus(false);
					dlr.setMessage("??????????????????" + needNum + "???????????????");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else if (tItem.getSymbol() == 5) {
				if (policeNum != needNum) {
					dlr.setStatus(false);
					dlr.setMessage("??????????????????" + needNum + "???????????????");
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
			// ????????????????????????
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
				// ???????????????
				new QRCode(root + trainQRCodeIcon).encodeQRCode("1," + id + "," + policeId, 500, 500,
						root + trainQRCode + qrName);
				count = trainService.trainFirearmAchievementCreat(item);
			} else {
				dlr.setStatus(false);
				dlr.setMessage("?????????????????????");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
		}
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("????????????");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
			return dlr;
		} else {
			dlr.setStatus(false);
			dlr.setMessage("????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
	}

	// ??????????????????????????????
	@RequestMapping(value = "/train/leader/sign/up/success/Item", method = RequestMethod.GET)
	public ResponseEntity<?> trainLeaderSignUpSuccessItem(
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		TrainLeaderSignUpSuccess item = new TrainLeaderSignUpSuccess();
		if (objectId == 1) {
			TrainPhysical fPhysical = trainService.trainPhysicalItem(trainPhysicalId);
			// ????????????????????????????????????
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
			// ??????????????????????????????
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

	// ????????????????????????
	@RequestMapping(value = "/train/leader/achievement/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainLeaderAchievementList(
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (objectId == 1) {
			// ??????????????????????????????
			TrainLeaderAchievement aItem = trainService.trainPhysicalLeaderAchievemenItem(trainPhysicalId,
					departmentId);
			if (aItem.getSignUpRate() == 0) {
				aItem.setFailRate(0);
				aItem.setPassRate(0);
			}
			dlr.setResult(aItem);
		} else if (objectId == 2) {
			// ????????????????????????
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

	// ??????????????????????????????
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
				// ???????????????????????????????????????
				List<TrainRecommendPolice> list1 = trainService.signMoreAchievementList(trainPhysicalId, departmentId);
				item.setYesNum(list1.size());
				item.setYesList(list1);
				// ???????????????????????????????????????
				List<TrainRecommendPolice> list2 = trainService.noSignMoreAchievementList(trainPhysicalId,
						departmentId);
				item.setNoNum(list2.size());
				item.setNoList(list2);
				item.setTotalNum(list1.size() + list2.size());
			} else if (projectId == 4) {
				// ????????????????????????????????????
				List<TrainRecommendPolice> passList = trainService.passMoreAchievementList(trainPhysicalId,
						departmentId);
				// ???????????????????????????????????????
				List<TrainRecommendPolice> failList = trainService.failMoreAchievementList(trainPhysicalId,
						departmentId);
				item.setYesList(passList);
				item.setYesNum(passList.size());
				item.setTotalNum(passList.size() + failList.size());
			} else if (projectId == 5) {
				// ????????????????????????????????????
				List<TrainRecommendPolice> passList = trainService.passMoreAchievementList(trainPhysicalId,
						departmentId);
				// ???????????????????????????????????????
				List<TrainRecommendPolice> failList = trainService.failMoreAchievementList(trainPhysicalId,
						departmentId);
				item.setYesList(failList);
				item.setYesNum(failList.size());
				item.setTotalNum(passList.size() + failList.size());
			}
		} else if (objectId == 2) {
			// ?????????????????????????????????
			List<TrainRecommendPolice> list1 = trainService.signFirearmMoreAchievementList(trainPhysicalId,
					departmentId);
			// ?????????????????????????????????
			List<TrainRecommendPolice> list2 = trainService.noSignFirearmMoreAchievementList(trainPhysicalId,
					departmentId);
			item.setTotalNum(list1.size() + list2.size());
			if (projectId == 1) {// 1?????????
				item.setYesNum(list1.size());
				item.setYesList(list1);
				item.setNoNum(list2.size());
				item.setNoList(list2);
			} else if (projectId == 2) {// 2?????????
				// ??????????????????????????????
				List<TrainRecommendPolice> goodList = trainService.passFirearmMoreAchievementList(trainPhysicalId,
						departmentId, 4);
				item.setYesNum(goodList.size());
				item.setYesList(goodList);
			} else if (projectId == 3) {// 3?????????
				// ??????????????????????????????
				List<TrainRecommendPolice> justList = trainService.passFirearmMoreAchievementList(trainPhysicalId,
						departmentId, 3);
				item.setYesNum(justList.size());
				item.setYesList(justList);
			} else if (projectId == 4) {// 4?????????
				// ??????????????????????????????
				List<TrainRecommendPolice> passList = trainService.passFirearmMoreAchievementList(trainPhysicalId,
						departmentId, 2);
				item.setYesNum(passList.size());
				item.setYesList(passList);
			} else if (projectId == 5) {// 5????????????
				// ???????????????????????????????????????
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

	// ???????????????????????????
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
			// ??????????????????????????????????????????
			List<TrainRank> sList1 = trainService.trainLeaderPhysicalSignUpRateRankList(trainPhysicalId, limitNum);
			itemChart1.setName("?????????");
			itemChart1.setId(1);
			rankItem1.setProjectItem(itemChart1);
			rankItem1.setRankList(sList1);
			list.add(rankItem1);
			TrainRankList rankItem2 = new TrainRankList();
			CalculationChart itemChart2 = new CalculationChart();
			// ??????????????????????????????????????????
			List<TrainRank> sList2 = trainService.trainLeaderPhysicalPassRateRankList(trainPhysicalId, limitNum);
			itemChart2.setName("?????????");
			itemChart2.setId(4);
			rankItem2.setProjectItem(itemChart2);
			rankItem2.setRankList(sList2);
			list.add(rankItem2);
			TrainRankList rankItem3 = new TrainRankList();
			CalculationChart itemChart3 = new CalculationChart();
			// ?????????????????????????????????????????????
			List<TrainRank> sList3 = trainService.trainLeaderPhysicalFailRateRankList(trainPhysicalId, limitNum);
			itemChart3.setName("????????????");
			itemChart3.setId(5);
			rankItem3.setProjectItem(itemChart3);
			rankItem3.setRankList(sList3);
			list.add(rankItem3);
			dlr.setResult(list);
		} else if (objectId == 2) {
			TrainFirearm fPhysical = trainService.trainFirearmItem(trainPhysicalId);
			TrainProject project = trainService.getTrainProjectById(fPhysical.getTrainFirearmType());
			// ????????????????????????????????????
			CalculationChart itemChart1 = new CalculationChart();
			TrainRankList rankItem1 = new TrainRankList();
			List<TrainRank> sList1 = trainService.trainLeaderFirearmSignUpRateRankList(trainPhysicalId, limitNum);
			itemChart1.setName("?????????");
			itemChart1.setId(1);
			rankItem1.setProjectItem(itemChart1);
			rankItem1.setRankList(sList1);
			list.add(rankItem1);
			if (project.getIsU() == 2) {
				// ????????????????????????????????????(2??????3??????4??????)
				TrainRankList rankItem2 = new TrainRankList();
				CalculationChart itemChart2 = new CalculationChart();
				List<TrainRank> sList2 = trainService.trainLeaderFirearmGoodRateRankList(trainPhysicalId, 4, limitNum);
				itemChart2.setName("?????????");
				itemChart2.setId(2);
				rankItem2.setProjectItem(itemChart2);
				rankItem2.setRankList(sList2);
				list.add(rankItem2);
				// ????????????????????????????????????
				TrainRankList rankItem3 = new TrainRankList();
				CalculationChart itemChart3 = new CalculationChart();
				List<TrainRank> sList3 = trainService.trainLeaderFirearmGoodRateRankList(trainPhysicalId, 3, limitNum);
				itemChart3.setName("?????????");
				itemChart3.setId(3);
				rankItem3.setProjectItem(itemChart3);
				rankItem3.setRankList(sList3);
				list.add(rankItem3);
			}
			// ????????????????????????????????????
			TrainRankList rankItem4 = new TrainRankList();
			CalculationChart itemChart4 = new CalculationChart();
			List<TrainRank> sList4 = trainService.trainLeaderFirearmGoodRateRankList(trainPhysicalId, 2, limitNum);
			itemChart4.setName("?????????");
			itemChart4.setId(4);
			rankItem4.setProjectItem(itemChart4);
			rankItem4.setRankList(sList4);
			list.add(rankItem4);
			// ???????????????????????????????????????
			TrainRankList rankItem5 = new TrainRankList();
			CalculationChart itemChart5 = new CalculationChart();
			List<TrainRank> sList5 = trainService.trainLeaderFirearmFailRateRankList(trainPhysicalId, limitNum);
			itemChart5.setName("????????????");
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

	// ????????????????????????
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
		item1.setName("?????????");
		projectList.add(item1);
		CalculationChart item4 = new CalculationChart();
		item4.setId(4);
		item4.setName("?????????");
		CalculationChart item5 = new CalculationChart();
		item5.setId(5);
		item5.setName("????????????");
		if (objectId == 2) {// 2??????
			TrainFirearm fPhysical = trainService.trainFirearmItem(trainPhysicalId);
			TrainProject project = trainService.getTrainProjectById(fPhysical.getTrainFirearmType());
			if (project.getIsU() == 2) {
				CalculationChart item2 = new CalculationChart();
				item2.setId(2);
				item2.setName("?????????");
				projectList.add(item2);
				CalculationChart item3 = new CalculationChart();
				item3.setId(3);
				item3.setName("?????????");
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

	// ????????????????????????
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
			if (projectId == 1) {// ?????????
				// ??????????????????????????????????????????
				list = trainService.trainLeaderPhysicalSignUpRateRankList(trainPhysicalId, limitNum);
			} else if (projectId == 4) {// ?????????
				// ??????????????????????????????????????????
				list = trainService.trainLeaderPhysicalPassRateRankList(trainPhysicalId, limitNum);
			} else if (projectId == 5) {// ????????????
				// ?????????????????????????????????????????????
				list = trainService.trainLeaderPhysicalFailRateRankList(trainPhysicalId, limitNum);
			}
		} else if (objectId == 2) {
			if (projectId == 1) {
				// ????????????????????????????????????
				list = trainService.trainLeaderFirearmSignUpRateRankList(trainPhysicalId, limitNum);
			} else if (projectId == 2) {
				// ????????????????????????????????????(2??????3??????4??????)
				list = trainService.trainLeaderFirearmGoodRateRankList(trainPhysicalId, 4, limitNum);
			} else if (projectId == 3) {
				// ????????????????????????????????????
				list = trainService.trainLeaderFirearmGoodRateRankList(trainPhysicalId, 3, limitNum);
			} else if (projectId == 4) {
				// ????????????????????????????????????
				list = trainService.trainLeaderFirearmGoodRateRankList(trainPhysicalId, 2, limitNum);
			} else if (projectId == 5) {
				// ???????????????????????????????????????
				list = trainService.trainLeaderFirearmFailRateRankList(trainPhysicalId, limitNum);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ???????????????????????????
	@RequestMapping(value = "/train/is/leader/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainIsLeaderItem(
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ???????????????????????????
		TrainLeader item = trainService.trainLeaderItem(null, policeId, departmentId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????????????????
	@RequestMapping(value = "/train/is/scorer/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainIsScorerItem(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ??????????????????????????????
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

	// ????????????????????????
	@RequestMapping(value = "/train/applicants/leader/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainApplicantsLeaderList(
			@RequestParam(value = "objectId", required = false) Integer objectId,
			@RequestParam(value = "trainPhysicalId", required = false) Integer trainPhysicalId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ??????policeId????????????
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
		// ????????????????????????
		List<TrainPhysicalAchievement> list = trainService.trainApplicantsLeaderList(trainPhysicalId,
				user.getDepartmentId(), tableName, tableId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????????????????
	@RequestMapping(value = "/train/statistics/analysis/item", method = RequestMethod.GET)
	public ResponseEntity<?> trainStatisticsAnalysisItem(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		Integer limitNum = 10000;
		// ??????policeId????????????
		User user = userService.policeItem(policeId, null, null);
		// ??????????????????????????????
		TrainStatisticsAnalysis item = trainService.trainTotalStatistics(user.getDepartmentId());
		// ??????????????????????????????????????????
		TrainStatisticsAnalysis titem = trainService.trainTotalLastStatistics(user.getDepartmentId());
		item.setDepRecentTrainPassRate(titem.getDepRecentTrainPassRate());
		List<Department> departmentList = departmentService.departmentList(user.getDepartmentId());
		item.setDepartmentName(departmentList.get(0).getName());
		// ??????????????????????????????
		TrainPhysical physicalItem = trainService.trainRecentPhysicalItem();
		if (physicalItem != null) {
			if (physicalItem.getObjectId() == 1) {// ????????????
				// ?????????????????????????????????????????????
				TrainStatisticsAnalysis aitem = trainService.trainPhysicalLastStatistics(physicalItem.getId(),
						user.getDepartmentId());
				item.setOfficeRecentTrainPassRate(aitem.getOfficeRecentTrainPassRate());// ???????????????????????????????????????
				// ??????????????????????????????????????????
				List<TrainRank> sList2 = trainService.trainLeaderPhysicalPassRateRankList(physicalItem.getId(),
						limitNum);
				if (sList2.size() > 0) {
					for (int i = 0; i < sList2.size(); i++) {
						if (sList2.get(i).getDepartmentName().equals(departmentList.get(0).getName())) {
							item.setOfficeRecentTrainRank(sList2.get(i).getRankId());// ????????????????????????????????????
						}
					}
				}
			} else if (physicalItem.getObjectId() == 2) {// ??????
				// ???????????????????????????????????????
				TrainStatisticsAnalysis aitem = trainService.trainFirearmLastStatistics(physicalItem.getId(),
						user.getDepartmentId());
				item.setOfficeRecentTrainPassRate(aitem.getOfficeRecentTrainPassRate());// ???????????????????????????????????????
				// ????????????????????????????????????
				List<TrainRank> sList4 = trainService.trainLeaderFirearmGoodRateRankList(physicalItem.getId(), 2,
						limitNum);
				if (sList4.size() > 0) {
					for (int i = 0; i < sList4.size(); i++) {
						if (sList4.get(i).getDepartmentName().equals(departmentList.get(0).getName())) {
							item.setOfficeRecentTrainRank(sList4.get(i).getRankId());// ????????????????????????????????????
						}
					}
				}
			}
		}
		// ??????????????????????????????
		TrainStatisticsAnalysis mitem = trainService.matchTotalStatistics(user.getDepartmentId());
		item.setDepMatchNum(mitem.getDepMatchNum());// ??????????????????
		item.setDepMatchAveNum(mitem.getDepMatchAveNum());// ??????????????????????????????
		item.setOfficeMatchNum(mitem.getOfficeMatchNum());// ??????????????????
		item.setOfficeMatchAveNum(mitem.getOfficeMatchAveNum());// ??????????????????????????????
		item.setOfficeMatchSignRate(mitem.getOfficeMatchSignRate());// ?????????????????????
		item.setDepRecentMatchNum(mitem.getDepRecentMatchNum());// ?????????????????????????????????
		item.setOfficeRecentMatchSignRate(mitem.getOfficeRecentMatchSignRate());// ??????????????????????????????????????????
		// ??????????????????
		List<TrainMatch> listMatchs = trainMatchService.trainOfficeMatchList();
		for (int i = 0; i < listMatchs.size(); i++) {
			// ???????????????????????????
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

	// ?????????????????????????????????(???12??????)
	@RequestMapping(value = "/train/dep/chart", method = RequestMethod.GET)
	public ResponseEntity<?> trainDepChart(@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "objectId", required = false) Integer objectId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (objectId == null || objectId == 1) {
			// ???????????????????????????(???12??????)
			List<CalculationChart> list = trainService.trainDepPhysicalChart(departmentId);
			dlr.setResult(list);
		} else if (objectId == 2) {
			// ?????????????????????(???12??????)
			List<CalculationChart> list = trainService.trainDepFirearmChart(departmentId);
			dlr.setResult(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ?????????????????????????????????(???12??????)
	@RequestMapping(value = "/match/dep/chart", method = RequestMethod.GET)
	public ResponseEntity<?> matchDepChart(@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "nature", required = false) Integer nature) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ?????????????????????????????????(???12??????)
		List<CalculationChart> list = trainService.matchDepChart(departmentId, nature);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????????????????
	@RequestMapping(value = "/train/dep/type/chart", method = RequestMethod.GET)
	public ResponseEntity<?> trainDepTypeChart(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ??????????????????????????????
		List<CalculationChart> list = trainService.trainDepTypeChart(departmentId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????????????????
	@RequestMapping(value = "/match/dep/type/chart", method = RequestMethod.GET)
	public ResponseEntity<?> matchDepTypeChart(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ??????????????????????????????
		List<CalculationChart> list = trainService.matchDepTypeChart(departmentId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????/??????????????????
	@RequestMapping(value = "/train/depart/police/list", method = RequestMethod.GET)
	public ResponseEntity<?> trainDepartPoliceList(
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "type", required = false) Integer type) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (type == null || type == 1) {// ??????
			// ??????????????????????????????
			List<TrainRecommendPolice> list = trainService.trainDepPoliceList(departmentId);
			dlr.setResult(list);
		} else if (type == 2) {// ??????
			// ??????????????????????????????
			List<TrainRecommendPolice> list = trainService.matchDepPoliceList(departmentId);
			dlr.setResult(list);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * ????????????????????????
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
			// ??????group_ids????????????
			trainPhysical.setTrainGroupNames(trainService.getTrainGroupByIds(trainPhysical.getTrainGroupIds()));
		}
		// ????????????
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
	 * ??????????????????
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
		// ??????
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
	 * ??????????????????
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
	 * ????????????
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
	 * ??????????????????
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
	 * ???????????????
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
	 * ??????????????????
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
	 * ????????????
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
	 * ????????????
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
	 * ????????????
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
	 * ????????????
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
	 * ??????????????????
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
	 * ??????????????????name???id
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
	 * ????????????????????????????????? id ??????U???
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
	 * ?????????????????????????????????
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
	 * ????????????????????????
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
	 * ????????????????????????
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

				// ????????????
				byte[] bytes = coverImgFile.getBytes();
				// ?????????????????? UUID
				String uuid = UUID.randomUUID().toString();
				// ?????????
				String fileName = coverImgFile.getOriginalFilename().substring(0,
						coverImgFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// ??????????????????
				String url = (root + coverImg);
				// ???????????????
				String suffix = coverImgFile.getOriginalFilename()
						.substring(coverImgFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
				coverImgFileName = fileName + suffix;
				Path path = Paths.get(url, coverImgFileName);
				Files.write(path, bytes);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			if (null != trainImgFile.getOriginalFilename() && !"".equals(trainImgFile.getOriginalFilename())) {

				// ????????????
				byte[] bytes2 = trainImgFile.getBytes();
				// ?????????????????? UUID
				String uuid2 = UUID.randomUUID().toString();
				// ?????????
				String fileName2 = trainImgFile.getOriginalFilename().substring(0,
						trainImgFile.getOriginalFilename().lastIndexOf(".")) + uuid2;
				// ??????????????????
				String url2 = (root + contentImg);
				// ???????????????
				String suffix2 = trainImgFile.getOriginalFilename()
						.substring(trainImgFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
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

		// ?????????
		for (String i : map.keySet()) {
			trainGroupIds += i + ",";
		}
		trainGroupIds = trainGroupIds.substring(0, trainGroupIds.length() - 1);

		physical.setTrainGroupIds(trainGroupIds);
		// ??????????????????
		trainService.trainPhysicalCreat(physical);

		for (String i : map.keySet()) {
			// ???????????????????????????
			TrainPhysicalProjectRecord trainPhysicalProjectRecord = new TrainPhysicalProjectRecord();
			trainPhysicalProjectRecord.setTrainPhysicalId(physical.getId());
			trainPhysicalProjectRecord.setTrainGroupId(Integer.parseInt(i));
			trainPhysicalProjectRecord.setTrainProject(map.get(i));
			trainPhysicalProjectRecord.setCreationDate(new Date());
			trainService.addTrainPhysicalProjectRecord(trainPhysicalProjectRecord);
		}

		// ???????????????
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
	 * ?????? ??????????????????
	 * 
	 * @return
	 */
	@DeleteMapping("/train/train_physical_delete")
	public ResponseEntity<?> trainPhysicalDelete(Integer id) {

		TrainPhysical trainPhysical = trainService.trainPhysicalItem(id);
		int del = trainService.trainPhysicalDelete(id);
		// ???????????????
		trainService.deleteByTrainPhysicalId(id);
		trainService.deleteTrainPhysicalAchievementDetailsByPhysicalId(id);

		totalRiskDetailsService.skillRiskDetails(LocalDate.parse(DateUtils.formatDate(trainPhysical.getTrainStartDate(), "yyyy-MM-dd")));

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(del);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * ????????????????????????
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

				// ????????????
				byte[] bytes = coverImgFile.getBytes();
				// ?????????????????? UUID
				String uuid = UUID.randomUUID().toString();
				// ?????????
				String fileName = coverImgFile.getOriginalFilename().substring(0,
						coverImgFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// ??????????????????
				String url = (root + coverImg);
				// ???????????????
				String suffix = coverImgFile.getOriginalFilename()
						.substring(coverImgFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
				coverImgFileName = fileName + suffix;
				Path path = Paths.get(url, coverImgFileName);
				Files.write(path, bytes);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			if (null != trainImgFile.getOriginalFilename() && !"".equals(trainImgFile.getOriginalFilename())) {

				// ????????????
				byte[] bytes2 = trainImgFile.getBytes();
				// ?????????????????? UUID
				String uuid2 = UUID.randomUUID().toString();
				// ?????????
				String fileName2 = trainImgFile.getOriginalFilename().substring(0,
						trainImgFile.getOriginalFilename().lastIndexOf(".")) + uuid2;
				// ??????????????????
				String url2 = (root + contentImg);
				// ???????????????
				String suffix2 = trainImgFile.getOriginalFilename()
						.substring(trainImgFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
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

		// ?????????
		for (String i : map.keySet()) {
			trainGroupIds += i + ",";
		}
		trainGroupIds = trainGroupIds.substring(0, trainGroupIds.length() - 1);

		physical.setTrainGroupIds(trainGroupIds);
		// ??????
		trainService.trainPhysicalUpdateSpecial(physical);

		// ??????????????????
		trainService.deleteByTrainPhysicalId(id);
		for (String i : map.keySet()) {
			// ??????????????????
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
	 * ??????????????????????????????
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
	 * ??????????????????????????????
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
	 * ????????????????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_details", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPhysicalDetails(@Param("id") Integer id) {

		// ??????????????????
		TrainPhysical trainPhysical = trainService.getTrainPhysicalDetails(id);

		// ?????????????????????????????????id
		List<TrainPhysicalProjectRecord> trainPhysicalProjectRecordList = trainService
				.trainPhysicalProjectRecordByGroupIds(trainPhysical.getTrainGroupIds(), trainPhysical.getId());

		// ???????????????????????????????????????
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
	 * ????????????????????????
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
	 * ????????????
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
	 * ????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_delete_group", method = RequestMethod.POST)
	public ResponseEntity<?> deleteTrainGroup(@Param("id") Integer id) {

		DataListReturn dataListReturn = new DataListReturn();

		// ??????????????????????????????
		int count = trainService.groupQuote(id);

		if (count > 0) {
			// ?????????
			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("fail");
			dataListReturn.setStatus(true);
			dataListReturn.setResult("?????????????????????,????????????");

		} else {
			// ????????????
			int delete = trainService.deleteGroup(id);

			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("success");
			dataListReturn.setStatus(true);
			dataListReturn.setResult(delete);
		}

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * ????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_details_group", method = RequestMethod.POST)
	public ResponseEntity<?> trainGroupDetails(@Param("id") Integer id) {

		// ??????????????????
		TrainGroup trainGroup = trainService.trainGroupItem(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(trainGroup);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * ????????????
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

		// ????????????
		int count = trainService.updateTrainGroup(trainGroup);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * ??????????????????????????????????????????
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
	 * ??????????????????
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
	 * ??????????????????
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
			dataListReturn.setResult("???????????????????????????????????????");

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
	 * ??????????????????
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
	 * ??????????????????
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
	 * ?????????????????????
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
	 * ?????????????????????
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
	 * ?????????????????????
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
	 * ?????????????????????
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
	 * ??????????????????
	 * 
	 * @param name              ????????????
	 * @param projectId         ??????id
	 * @param limitTime         U???????????????????????????
	 * @param groupId           ?????????????????????????????????
	 * @param symbol            ??????????????????U?????????????????????
	 * @param qualifiedPhysical ????????????????????????
	 * @param qualifiedFirearmA ?????????U??????????????????
	 * 
	 * @param jsonU             json?????????U??????????????????
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
		// ??????????????????
		trainService.addTrainProjectRule(trainProjectRule);

		if (null != jsonU) {
			// ??????json
			// ???jsonU?????????json??????
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
				// ??????U????????????
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
	 * ??????????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_details_project_rule", method = RequestMethod.POST)
	public ResponseEntity<?> detailsTrainProjectRule(@Param("id") Integer id) {

		TrainProjectRule trainProjectRule = trainService.getTrainProjectRuleById(id);

		List<TrainProjectURule> trainProjectURule = null;

		// ?????????U?????????
		// ??????U??????????????????
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
	 * ??????????????????
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
			dataListReturn.setResult("?????????????????????????????????????????????");

			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		} else if (trainService.getTrainFirearmByProjectRuleId(id).size() > 0) {

			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("fail");
			dataListReturn.setStatus(true);
			dataListReturn.setResult("?????????????????????????????????????????????");

			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

		// ??????????????????
		trainService.deleteTrainProjectRule(id);
		// ??????U???????????????
		trainService.deleteTrainProjectURule(id);

		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(null);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * ??????????????????
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
		// ??????????????????
		trainService.updateTrainProjectRule(trainProjectRule);

		if (null != jsonU) {
			// ??????json
			// ???jsonU?????????json??????
			String json[] = jsonU.split("-");

			// ??????ruleId??????U????????????
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
				// ??????U????????????
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
	 * ?????????????????????????????? ??????????????????
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
	 * ??????????????????????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_achievement_details", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPhysicalAchievementDetails(@Param("id") Integer id) {
		// ????????????
		TrainPhysicalAchievement trainPhysicalAchievement = trainService.getTrainPhysicalAchievementById(id);
		List<TrainPhysicalAchievementDetails> trainPhysicalAchievementDetailsList = trainService
				.getTrainPhysicalAchievementDetailsByCondition(trainPhysicalAchievement.getTrainPhysicalId(), id);
//		for (TrainPhysicalAchievementDetails t : trainPhysicalAchievementDetailsList) {
//			// ??????????????????????????????????????????
//			Integer rank = trainService.getRank(trainPhysicalAchievement.getTrainPhysicalId(),
//					trainPhysicalAchievement.getTrainGroupId(), t.getProjectId(), t.getPoliceId(),
//					trainService.getTrainProjectById(t.getProjectId()).getSort());
//			t.setRank(rank);
//		}
		if (trainPhysicalAchievementDetailsList.size() > 0) {
			for (int i = 0; i < trainPhysicalAchievementDetailsList.size(); i++) {

				String sort = null;
				if (trainPhysicalAchievementDetailsList.get(i).getSortNum() == 1) {// ??????
					sort = "asc";
				} else if (trainPhysicalAchievementDetailsList.get(i).getSortNum() == 2) {// ??????
					sort = "desc";
				}
				// ??????policeId????????????
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
	 * ????????????id???????????????id????????????????????????????????????
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
	 * ??????????????????????????????
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
				// ???
				min = Integer.parseInt(jsonObj.get("achievementFirst").toString());
				trainPhysicalAchievementDetails.setAchievementFirst(min);
			}
			if (!jsonObj.get("achievementSecond").toString().equals("null")) {
				// ???
				sec = Double.parseDouble(jsonObj.get("achievementSecond").toString());
				trainPhysicalAchievementDetails.setAchievementSecond(sec);
			}

			// ??????
			if (null != min) {
				trainPhysicalAchievementDetails.setAchievementStr(min + "???");
				trainPhysicalAchievementDetails.setAchievement(min.doubleValue());
				if (null != sec) {
					trainPhysicalAchievementDetails.setAchievementStr(min + "???" + sec + "???");
					// ?????????+???
					String secMinStr = new DecimalFormat("#.00").format(sec / 60);
					trainPhysicalAchievementDetails.setAchievement(min + Double.parseDouble(secMinStr));
				}
			} else {
				// ?????????
				trainPhysicalAchievementDetails.setAchievementStr(
						trainPhysicalAchievementDetails.getAchievement() + jsonObj.get("unit").toString().trim());
			}

			// ????????????????????????????????????
			TrainProjectRule qualifiedAchievement = trainService
					.getQualifiedAchievement(trainPhysicalAchievementDetails.getId(), trainGroupId);

			// ??????????????????????????????
			// ??????
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

			// ????????????
			trainService.trainPhysicalAchievementDetailsUpdate(trainPhysicalAchievementDetails);

		}

		// ????????????????????????????????????
		TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, trainPhysicalId, policeId);
		aItem.setAchievementDate(new Date());
		aItem.setUpdateDate(new Date());
		// ????????????????????????????????????
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
	 * ??????BMI
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
	 * ?????????????????????????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_achievement_rank", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPhysicalAchievementRank(@Param("id") Integer id,
			@Param("projectId") Integer projectId, @Param("keyword") String keyword,
			@Param("pageSize") Integer pageSize) {
		// ????????????
		TrainPhysicalAchievement trainPhysicalAchievement = trainService.getTrainPhysicalAchievementById(id);
		int sort = trainService.getTrainProjectById(projectId).getSort();
		// ??????????????????????????????????????????
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
	 * ?????????????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_project_title", method = RequestMethod.POST)
	public ResponseEntity<?> getTrainPhysicalProjectTitle(@Param("id") Integer id) {

		// ????????????
		TrainPhysicalAchievement trainPhysicalAchievement = trainService.getTrainPhysicalAchievementById(id);

		// ?????????????????????
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
	 * ??????????????????
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
				// ????????????
				byte[] bytes = coverImgFile.getBytes();
				// ?????????????????? UUID
				String uuid = UUID.randomUUID().toString();
				// ?????????
				String fileName = coverImgFile.getOriginalFilename().substring(0,
						coverImgFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// ??????????????????
				String url = (root + coverImg);
				// ???????????????
				String suffix = coverImgFile.getOriginalFilename()
						.substring(coverImgFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
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
	 * ??????????????????
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
				// ????????????

				byte[] bytes = headImgFile.getBytes();

				// ?????????????????? UUID
				String uuid = UUID.randomUUID().toString();
				// ?????????
				String fileName = headImgFile.getOriginalFilename().substring(0,
						headImgFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// ??????????????????
				String url = (root + coverImg);
				// ???????????????
				String suffix = headImgFile.getOriginalFilename()
						.substring(headImgFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
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
	 * ??????????????????
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

				// ????????????
				byte[] bytes = coverImgFile.getBytes();

				// ?????????????????? UUID
				String uuid = UUID.randomUUID().toString();
				// ?????????
				String fileName = coverImgFile.getOriginalFilename().substring(0,
						coverImgFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// ??????????????????
				String url = (root + coverImg);
				// ???????????????
				String suffix = coverImgFile.getOriginalFilename()
						.substring(coverImgFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
				coverImgFileName = fileName + suffix;
				Path path = Paths.get(url, coverImgFileName);
				Files.write(path, bytes);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			if (null != filePathFile.getOriginalFilename() && !"".equals(filePathFile.getOriginalFilename())) {

				// ????????????
				byte[] bytes2 = filePathFile.getBytes();

				// ?????????????????? UUID
				String uuid2 = UUID.randomUUID().toString();
				// ?????????
				String fileName2 = filePathFile.getOriginalFilename().substring(0,
						filePathFile.getOriginalFilename().lastIndexOf(".")) + uuid2;
				// ??????????????????
				String url2 = (root + coverImg);
				// ???????????????
				String suffix2 = filePathFile.getOriginalFilename()
						.substring(filePathFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
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
	 * ??????????????????
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
				// ????????????

				byte[] bytes = coverImgFile.getBytes();

				// ?????????????????? UUID
				String uuid = UUID.randomUUID().toString();
				// ?????????
				String fileName = coverImgFile.getOriginalFilename().substring(0,
						coverImgFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// ??????????????????
				String url = (root + coverImg);
				// ???????????????
				String suffix = coverImgFile.getOriginalFilename()
						.substring(coverImgFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
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
	 * ??????????????????
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
				// ????????????

				byte[] bytes = headImgFile.getBytes();

				// ?????????????????? UUID
				String uuid = UUID.randomUUID().toString();
				// ?????????
				String fileName = headImgFile.getOriginalFilename().substring(0,
						headImgFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// ??????????????????
				String url = (root + coverImg);
				// ???????????????
				String suffix = headImgFile.getOriginalFilename()
						.substring(headImgFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
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
	 * ??????????????????
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

				// ????????????
				byte[] bytes = coverImgFile.getBytes();

				// ?????????????????? UUID
				String uuid = UUID.randomUUID().toString();
				// ?????????
				String fileName = coverImgFile.getOriginalFilename().substring(0,
						coverImgFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// ??????????????????
				String url = (root + coverImg);
				// ???????????????
				String suffix = coverImgFile.getOriginalFilename()
						.substring(coverImgFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
				coverImgFileName = fileName + suffix;
				Path path = Paths.get(url, coverImgFileName);
				Files.write(path, bytes);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			if (null != filePathFile.getOriginalFilename() && !"".equals(filePathFile.getOriginalFilename())) {

				// ????????????
				byte[] bytes2 = filePathFile.getBytes();

				// ?????????????????? UUID
				String uuid2 = UUID.randomUUID().toString();
				// ?????????
				String fileName2 = filePathFile.getOriginalFilename().substring(0,
						filePathFile.getOriginalFilename().lastIndexOf(".")) + uuid2;
				// ??????????????????
				String url2 = (root + coverImg);
				// ???????????????
				String suffix2 = filePathFile.getOriginalFilename()
						.substring(filePathFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
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
	 * ??????????????????
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
	 * ??????????????????
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
	 * ??????????????????
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
	 * ?????????????????????????????????
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
	 * ?????????????????????????????????
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
	 * ?????????????????????????????????
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
	 * ??????????????????
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
	 * ??????????????????
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
	 * ??????????????????
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
	 * ??????????????????
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
	 * ??????????????????
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
	 * ??????????????????
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
				// ????????????

				byte[] bytes = imageFile.getBytes();

				// ?????????????????? UUID
				String uuid = UUID.randomUUID().toString();
				// ?????????
				String fileName = imageFile.getOriginalFilename().substring(0,
						imageFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// ??????????????????
				String url = (root + coverImg);
				// ???????????????
				String suffix = imageFile.getOriginalFilename()
						.substring(imageFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
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
	 * ????????????
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
	 * ????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_leader_add", method = RequestMethod.POST)
	public ResponseEntity<?> trainLeaderAdd(@Param("departmentId") Integer departmentId,
			@Param("leaderId") String leaderId) {

		DataListReturn dataListReturn = new DataListReturn();

		// ??????????????????????????????
		TrainLeader trainLeaderItem = trainService.trainLeaderItem(null, leaderId, null);
		TrainLeader trainDepartmentItem = trainService.trainLeaderItem(null, null, departmentId);

		if (null != trainLeaderItem || null != trainDepartmentItem) {

			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("fail");
			dataListReturn.setStatus(true);
			dataListReturn.setResult("??????????????????????????????!");

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
	 * ????????????
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
	 * ????????????
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
	 * ????????????
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
	 * ??????????????????
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

				// ????????????
				byte[] bytes = coverImgFile.getBytes();
				// ?????????????????? UUID
				String uuid = UUID.randomUUID().toString();
				// ?????????
				String fileName = coverImgFile.getOriginalFilename().substring(0,
						coverImgFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// ??????????????????
				String url = (root + coverImg);
				// ???????????????
				String suffix = coverImgFile.getOriginalFilename()
						.substring(coverImgFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
				coverImgFileName = fileName + suffix;
				Path path = Paths.get(url, coverImgFileName);
				Files.write(path, bytes);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			if (null != trainImgFile && !"".equals(trainImgFile.getOriginalFilename())) {

				// ????????????
				byte[] bytes2 = trainImgFile.getBytes();
				// ?????????????????? UUID
				String uuid2 = UUID.randomUUID().toString();
				// ?????????
				String fileName2 = trainImgFile.getOriginalFilename().substring(0,
						trainImgFile.getOriginalFilename().lastIndexOf(".")) + uuid2;
				// ??????????????????
				String url2 = (root + contentImg);
				// ???????????????
				String suffix2 = trainImgFile.getOriginalFilename()
						.substring(trainImgFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
				trainImgFileName = fileName2 + suffix2;
				Path path2 = Paths.get(url2, trainImgFileName);
				Files.write(path2, bytes2);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ??????????????????
		TrainFirearm trainFirearm = new TrainFirearm();
		trainFirearm.setType(type);
		if (type == 1) {
			trainFirearm.setDepartmentId(departmentId);
			if (isAll != 2) {
				trainFirearm.setInvolvementPoliceIds(policeIds);
			} else {
				// ?????????????????????????????????
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
	 * ??????????????????
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

				// ????????????
				byte[] bytes = coverImgFile.getBytes();
				// ?????????????????? UUID
				String uuid = UUID.randomUUID().toString();
				// ?????????
				String fileName = coverImgFile.getOriginalFilename().substring(0,
						coverImgFile.getOriginalFilename().lastIndexOf(".")) + uuid;
				// ??????????????????
				String url = (root + coverImg);
				// ???????????????
				String suffix = coverImgFile.getOriginalFilename()
						.substring(coverImgFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
				coverImgFileName = fileName + suffix;
				Path path = Paths.get(url, coverImgFileName);
				Files.write(path, bytes);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			if (null != trainImgFile && !"".equals(trainImgFile.getOriginalFilename())) {

				// ????????????
				byte[] bytes2 = trainImgFile.getBytes();
				// ?????????????????? UUID
				String uuid2 = UUID.randomUUID().toString();
				// ?????????
				String fileName2 = trainImgFile.getOriginalFilename().substring(0,
						trainImgFile.getOriginalFilename().lastIndexOf(".")) + uuid2;
				// ??????????????????
				String url2 = (root + contentImg);
				// ???????????????
				String suffix2 = trainImgFile.getOriginalFilename()
						.substring(trainImgFile.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
				trainImgFileName = fileName2 + suffix2;
				Path path2 = Paths.get(url2, trainImgFileName);
				Files.write(path2, bytes2);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// ??????????????????
		TrainFirearm trainFirearm = new TrainFirearm();
		trainFirearm.setId(id);
		if (0 != departmentId) {
			// trainFirearm.setDepartmentId(departmentId);
			if (isAll == 1) {
				// ????????????
				trainFirearm.setInvolvementPoliceIds(policeIds);
			} else {
				// ????????????
				// ?????????????????????????????????
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
	 * ??????????????????
	 * 
	 * @return
	 */
	@DeleteMapping("/train/train_delete_firearm")
	public ResponseEntity<?> trainFirearmDelete(Integer id) {
		TrainFirearm trainFirearm = trainService.getTrainFirearmById(id);
		int count = trainService.trainFirearmDelete(id);
		trainService.deleteTrainFirearmAchievementByFirearmId(id);

		totalRiskDetailsService.skillRiskDetails(LocalDate.parse(DateUtils.formatDate(trainFirearm.getTrainStartDate(), "yyyy-MM-dd")));

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(count);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * ????????????????????????
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
	 * ????????????????????????
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
	 * ????????????????????????
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
	 * ??????????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_details_firearm", method = RequestMethod.POST)
	public ResponseEntity<?> trainFirearmDetails(@Param("id") Integer id) {

		TrainFirearm trainFirearm = trainService.getTrainFirearmById(id);

		// ??????????????????
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
	 * ??????????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train/train_data_firearm", method = RequestMethod.POST)
	public ResponseEntity<?> trainFirearmData(@Param("trainFirearmId") Integer trainFirearmId,
			@Param("departmentId") Integer departmentId, @Param("keyword") String keyword,
			@Param("pageSize") Integer pageSize) {

		List<TrainFirearmAchievement> trainFirearmAchievement = trainService.getFirearmAchievementData(trainFirearmId,
				departmentId, keyword, (pageSize - 1) * 10);

		// ????????????????????????????????????????????????
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
	 * ????????????????????????
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
	 * ??????id????????????????????????
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
	 * ????????????????????????
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
			// U??????
			String format = new DecimalFormat("#.00").format(firstValue / lastValue);
			trainFirearmAchievement.setAchievement(Double.parseDouble(format));
			trainFirearmAchievement.setAchievementFirst(firstValue);
			trainFirearmAchievement.setAchievementSecond(lastValue);
			trainFirearmAchievement.setAchievementStr(Double.parseDouble(format) + "??????");
			trainFirearmAchievement.setShootTime(lastValue);
		} else {
			trainFirearmAchievement.setAchievement(firstValue.doubleValue());
			trainFirearmAchievement.setAchievementStr(firstValue.doubleValue() + unit);
		}
		TrainFirearmAchievement achievementItem = trainService.trainFirearmAchievementItem(id, null, null);
		// ????????????id/????????????????????????
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
					// ??????????????????id??????U??????????????????
					List<TrainProjectURule> list = trainService.TrainProjectURuleList(ruleItem.getId());
					double num = 0.0;
					if (lastValue != 0.0 && lastValue != 0) {
						num = firstValue / lastValue;
					}
					if (list.get(0).getMinNum() <= num && num <= list.get(0).getMaxNum()) {// 1??????2????????????
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
	 * ??????????????????????????????
	 * 
	 * @param trainPhysicalId ????????????id
	 * @return
	 */
	@RequestMapping(value = "/train/train_physical_statistics", method = RequestMethod.POST)
	public ResponseEntity<?> trainPhysicalStatistics(@Param("trainPhysicalId") Integer trainPhysicalId) {

		Integer companyNum = trainService.companyNum(trainPhysicalId);
		Integer signUpNum = trainService.signUpNum(trainPhysicalId);
		Integer signInNum = trainService.signInNum(trainPhysicalId);
		Integer qualifiedNum = trainService.qualifiedNum(trainPhysicalId, 2);
		Integer unqualifiedNum = trainService.qualifiedNum(trainPhysicalId, 1);

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
	 * ????????????????????????
	 * 
	 * @param trainPhysicalId ????????????id
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

	// ???????????????/?????????/????????????/?????????/???????????????
	@RequestMapping(value = "/train/office/rate/statistics", method = RequestMethod.POST)
	public ResponseEntity<?> trainOfficeRateStatistics(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "objectId", required = true) Integer objectId,
			@RequestParam(value = "type", required = true) Integer type) {
		DataListReturn dataListReturn = new DataListReturn();
		if (objectId == 1) {// ????????????
			// ???????????????????????????/?????????/????????????/?????????/???????????????
			List<LeaveChart> list = trainService.trainOfficeRateStatisticsList(id, type);
			dataListReturn.setResult(list);
		} else if (objectId == 2) {// ??????
			// ?????????????????????/?????????/????????????/?????????/???????????????
			List<LeaveChart> list = trainService.trainOfficeFirRateStatisticsList(id, type);
			dataListReturn.setResult(list);
		}
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * ??????????????????????????????
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
		String fileName = "????????????????????????????????????";
		String excelHeader[] = { "??????????????????", "??????", "??????", "????????????", "????????????", "????????????" };

		String sheetName = "sheet";
		// ?????????
		map.put("fileName", fileName);
		// ??????
		map.put("excelHeader", excelHeader);
		// ??????
		map.put("keys", excelHeader);
		// sheet???
		map.put("sheetName", sheetName);

		list.add(map);

		List<TrainAchievementTemplate> trainAchievementTemplateList = trainService
				.getPhysicalTrainAchievementTemplateList(trainPhysicalId);

		for (int i = 0; i < trainAchievementTemplateList.size(); i++) {
			LinkedHashMap<String, Object> mapBody = new LinkedHashMap<String, Object>();
			TrainAchievementTemplate trainAchievementTemplate = trainAchievementTemplateList.get(i);
			mapBody.put("??????????????????", trainAchievementTemplate.getName());
			mapBody.put("??????", trainAchievementTemplate.getPoliceName());
			mapBody.put("??????", trainAchievementTemplate.getPoliceId());
			mapBody.put("????????????", trainAchievementTemplate.getProjectName());
			mapBody.put("????????????", trainAchievementTemplate.getAchievementStr());
			mapBody.put("????????????", trainAchievementTemplate.getUnit());
			list.add(mapBody);
		}

		return list;

	}

	/**
	 * ????????????????????????
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

		// ???????????????
		int index = 0;

		try {
			for (List<String> excel : readExcel) {
				// ???????????????
				index++;
				// ?????????????????????
				TrainPhysicalAchievementDetails trainPhysicalAchievementDetails = new TrainPhysicalAchievementDetails();
				trainPhysicalAchievementDetails.setTrainPhysicalId(trainPhysicalId);
				String policeId = excel.get(2).toString().substring(0, 6);
				trainPhysicalAchievementDetails.setPoliceId(policeId);

				String achievementStr = excel.get(4).toString();
				// ???????????????
				trainPhysicalAchievementDetails.setAchievementStr(achievementStr);
				String unit = excel.get(5).toString();
				Double achievement = null;
				// ???????????????????????????
				if (unit.equals("??????")) {
					// ?????????
					Double min = Double.parseDouble(achievementStr.substring(0, achievementStr.indexOf("???")));
					// ?????????
					Double sec = Double.parseDouble(
							achievementStr.substring(achievementStr.indexOf("???") + 1, achievementStr.indexOf("???")));

					// ??????????????????
					achievement = NumberUtil.round(min + sec / 60, 2).doubleValue();

				} else {
					// ??????
					achievement = Double.parseDouble(achievementStr.substring(0, achievementStr.indexOf(unit)));
				}
				trainPhysicalAchievementDetails.setAchievement(achievement);

				// ??????????????????????????????id
				Integer projectId = trainService.getTrainProjectByName(excel.get(3).toString());
				trainPhysicalAchievementDetails.setProjectId(projectId);

				// ????????????????????????
				// ??????????????????????????????????????????
				TrainPhysicalProjectRecord projectItem = trainService.projectNamesItem(trainPhysicalId, policeId);
				// ????????????id/????????????????????????
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

				// ?????????
				trainPhysicalAchievementDetails.setIsSign(2);
				trainPhysicalAchievementDetails.setUpdateDate(new Date());

				TrainPhysicalAchievement trainPhysicalAchievement = new TrainPhysicalAchievement();
				// ??????????????????
				trainPhysicalAchievement.setAchievementDate(new Date());
				// ????????????
				trainPhysicalAchievement.setAchievementGrade(trainPhysicalAchievementDetails.getAchievementGrade());
				// ??????
				trainPhysicalAchievement.setIsSign(2);
				// trainPhysicalId
				trainPhysicalAchievement.setTrainPhysicalId(trainPhysicalId);
				// ??????
				trainPhysicalAchievement.setPoliceId(policeId);

				list2.add(trainPhysicalAchievement);

				// ??????????????????????????????
				list.add(trainPhysicalAchievementDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("???" + index + "???????????????,????????????!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		}

		// ???????????????
		for (TrainPhysicalAchievementDetails t : list) {
			trainService.trainPhysicalAchievementDetailsUpdateCondition(t);
		}
		// ???????????????
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
	 * ????????????????????????
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
		String fileName = "??????????????????????????????";

		String excelHeader[] = { "????????????", "??????", "??????", "????????????", "U??????????????????", "????????????", "????????????" };

		String sheetName = "sheet";
		// ?????????
		map.put("fileName", fileName);
		// ??????
		map.put("excelHeader", excelHeader);
		// ??????
		map.put("keys", excelHeader);
		// sheet???
		map.put("sheetName", sheetName);

		list.add(map);

		// ????????????????????????
		List<TrainAchievementTemplate> trainAchievementTemplateList = trainService
				.getFirearmTrainAchievementTemplate(trainFirearmId);

		for (int i = 0; i < trainAchievementTemplateList.size(); i++) {
			LinkedHashMap<String, Object> mapBody = new LinkedHashMap<String, Object>();
			TrainAchievementTemplate trainAchievementTemplate = trainAchievementTemplateList.get(i);
			mapBody.put("????????????", trainAchievementTemplate.getName());
			mapBody.put("??????", trainAchievementTemplate.getPoliceName());
			mapBody.put("??????", trainAchievementTemplate.getPoliceId());
			mapBody.put("????????????", trainAchievementTemplate.getProjectName());
			mapBody.put("U??????????????????",
					trainAchievementTemplate.getIsU() == 1 ? "???U????????????" : trainAchievementTemplate.getShootTime());
			mapBody.put("????????????", trainAchievementTemplate.getAchievementStr());
			mapBody.put("????????????", trainAchievementTemplate.getUnit());
			list.add(mapBody);
		}

		return list;

	}

	/**
	 * ??????????????????
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/train/import_firearm_excel", method = RequestMethod.POST)
	public ResponseEntity<?> importFirearmExcel(@Param("file") MultipartFile file,
			@Param("trainFirearmId") Integer trainFirearmId) throws Exception {

		List<List<String>> readExcel = GetExcel.ReadExcel(file);

		List<TrainFirearmAchievement> list = new ArrayList<TrainFirearmAchievement>();

		// ???????????????
		int index = 0;

		try {
			for (List<String> excel : readExcel) {
				// ???????????????
				index++;
				// ?????????????????????
				TrainFirearmAchievement trainFirearmAchievement = new TrainFirearmAchievement();
				trainFirearmAchievement.setTrainFirearmId(trainFirearmId);
				String policeId = excel.get(2).toString().substring(0, 6);
				trainFirearmAchievement.setPoliceId(policeId);
				// U??????????????????
				Double shootTime = Double.parseDouble(excel.get(4).toString());

				String achievementStr = excel.get(5).toString();
				// ???????????????
				trainFirearmAchievement.setAchievementStr(achievementStr);
				String unit = excel.get(6).toString();
				Double achievement = null;
				// ???????????????????????????
				if (unit.equals("??????")) {
					// ?????????
					Double min = Double.parseDouble(achievementStr.substring(0, achievementStr.indexOf("???")));
					// ?????????
					Double sec = Double.parseDouble(
							achievementStr.substring(achievementStr.indexOf("???") + 1, achievementStr.indexOf("???")));

					// ??????????????????
					achievement = NumberUtil.round(min + sec / 60, 2).doubleValue();

				} else {
					// ??????
					achievement = Double.parseDouble(achievementStr.substring(0, achievementStr.indexOf(unit)));
				}
				trainFirearmAchievement.setAchievement(achievement);

				// ??????????????????
				trainFirearmAchievement.setAchievementDate(new Date());
				// ??????????????????
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
							// ??????????????????id??????U??????????????????
							List<TrainProjectURule> list2 = trainService.TrainProjectURuleList(ruleItem.getId());
							double num = achievement / shootTime;
							if (list2.get(0).getMinNum() < num && num <= list2.get(0).getMaxNum()) {// 1??????2????????????
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

				// ?????????
				trainFirearmAchievement.setIsSign(2);
				trainFirearmAchievement.setUpdateDate(new Date());

				// ??????????????????????????????
				list.add(trainFirearmAchievement);
			}
		} catch (Exception e) {
			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("???" + index + "???????????????,????????????!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		}

		// ??????
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
	 * ????????????????????????
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
		String fileName = "????????????????????????";
		String excelHeader[] = { "????????????", "??????", "??????", "????????????", "????????????", "????????????", "????????????" };

		String sheetName = "sheet";
		// ?????????
		map.put("fileName", fileName);
		// ??????
		map.put("excelHeader", excelHeader);
		// ??????
		map.put("keys", excelHeader);
		// sheet???
		map.put("sheetName", sheetName);

		list.add(map);

		// ????????????????????????
		List<TrainAchievementTemplate> trainAchievementTemplateList = trainService
				.getMatchTrainAchievementTemplate(trainFirearmId);

		for (int i = 0; i < trainAchievementTemplateList.size(); i++) {
			LinkedHashMap<String, Object> mapBody = new LinkedHashMap<String, Object>();
			TrainAchievementTemplate trainAchievementTemplate = trainAchievementTemplateList.get(i);
			mapBody.put("????????????", trainAchievementTemplate.getName());
			mapBody.put("??????", trainAchievementTemplate.getPoliceName());
			mapBody.put("??????", trainAchievementTemplate.getPoliceId());
			mapBody.put("????????????", trainAchievementTemplate.getProjectTypeName());
			mapBody.put("????????????", trainAchievementTemplate.getProjectName());
			mapBody.put("????????????", trainAchievementTemplate.getAchievementStr());
			mapBody.put("????????????", trainAchievementTemplate.getUnit());
			list.add(mapBody);
		}

		return list;

	}

	/**
	 * ??????????????????
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/train/import_match_excel", method = RequestMethod.POST)
	public ResponseEntity<?> importMatchExcel(@Param("file") MultipartFile file,
			@Param("trainMatchId") Integer trainMatchId) throws Exception {

		List<List<String>> readExcel = GetExcel.ReadExcel(file);

		List<TrainMatchAchievement> list = new ArrayList<TrainMatchAchievement>();

		// ???????????????
		int index = 0;

		try {
			for (List<String> excel : readExcel) {
				// ???????????????
				index++;
				// ?????????????????????
				TrainMatchAchievement trainMatchAchievement = new TrainMatchAchievement();
				trainMatchAchievement.setTrainMatchId(trainMatchId);
				// ??????
				trainMatchAchievement.setPoliceId(excel.get(2).toString().substring(0, 6));

				String achievementStr = excel.get(5).toString();
				// ???????????????
				trainMatchAchievement.setAchievementStr(achievementStr);
				String unit = excel.get(6).toString();
				Double achievement = null;
				// ???????????????????????????
				if (unit.equals("??????")) {
					// ?????????
					Double min = Double.parseDouble(achievementStr.substring(0, achievementStr.indexOf("???")));
					// ?????????
					Double sec = Double.parseDouble(
							achievementStr.substring(achievementStr.indexOf("???") + 1, achievementStr.indexOf("???")));

					// ??????????????????
					achievement = NumberUtil.round(min + sec / 60, 2).doubleValue();

				} else {
					// ??????
					achievement = Double.parseDouble(achievementStr.substring(0, achievementStr.indexOf(unit)));
				}
				trainMatchAchievement.setAchievement(achievement);

				// ??????????????????
				trainMatchAchievement.setAchievementDate(new Date());
				// ?????????
				trainMatchAchievement.setIsSign(2);
				trainMatchAchievement.setUpdateDate(new Date());
				// ??????????????????????????????
				list.add(trainMatchAchievement);
			}
		} catch (Exception e) {
			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("???" + index + "???????????????,????????????!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		}

		// ??????
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

	// ????????????
	private String getDatePoor(Date endDate, Date nowDate) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		long ns = 1000;
		String string = null;
		// ???????????????????????????????????????
		long diff = endDate.getTime() - nowDate.getTime();
		// ??????????????????
		long day = diff / nd;
		// ?????????????????????
		long hour = diff % nd / nh;
		// ?????????????????????
		long min = diff % nd % nh / nm;
		// ??????????????????//????????????
		long sec = diff % nd % nh % nm / ns;
		if (day == 0 && hour == 0 && min == 0 && sec == 0) {
			string = "?????????";
		} else if (day == 0 && hour == 0 && min == 0) {
			string = sec + "???";
		} else if (day == 0 && hour == 0) {
			string = min + "??????" + sec + "???";
		} else if (day == 0) {
			string = hour + "??????" + min + "??????" + sec + "???";
		} else {
			string = day + "???" + hour + "??????" + min + "??????" + sec + "???";
		}
		return string;
	}

	// ?????????????????????????????????????????????
	@RequestMapping(value = "/import/train/physical", method = RequestMethod.POST)
	public ResponseEntity<?> importAddTrainPhysical(@Param("name") String name,
			@Param("registrationStartDate") String registrationStartDate,
			@Param("json") String json, @Param("file") MultipartFile file, HttpServletRequest request)
			throws Exception {
		System.out.println("??????: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
		TrainPhysical physical = new TrainPhysical();
		physical.setName(name);
		physical.setPlace("??????");
		physical.setDepartmentId(1);
		physical.setRegistrationStartDate(DateUtil.parseDateTime(registrationStartDate +":00"));
		physical.setRegistrationEndDate(DateUtil.parseDateTime(registrationStartDate +":00"));
		physical.setTrainStartDate(DateUtil.parseDateTime(registrationStartDate +":00"));
		physical.setTrainEndDate(DateUtil.parseDateTime(registrationStartDate +":00"));
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
		// ?????????
		for (String i : map.keySet()) {
			trainGroupIds += i + ",";
		}
		trainGroupIds = trainGroupIds.substring(0, trainGroupIds.length() - 1);
		physical.setTrainGroupIds(trainGroupIds);
		// ??????????????????
		trainService.trainPhysicalCreat(physical);
		for (String i : map.keySet()) {
			// ???????????????????????????
			TrainPhysicalProjectRecord trainPhysicalProjectRecord = new TrainPhysicalProjectRecord();
			trainPhysicalProjectRecord.setTrainPhysicalId(physical.getId());
			trainPhysicalProjectRecord.setTrainGroupId(Integer.parseInt(i));
			trainPhysicalProjectRecord.setTrainProject(map.get(i));
			trainPhysicalProjectRecord.setCreationDate(new Date());
			trainService.addTrainPhysicalProjectRecord(trainPhysicalProjectRecord);
		}
		// ????????????????????????
		List<User> userList = userService.userTrainList();
		List<TrainPhysicalAchievement> addSignList = new ArrayList<TrainPhysicalAchievement>();
		List<TrainPhysicalAchievementDetails> deItemList = new ArrayList<TrainPhysicalAchievementDetails>();
		for (int i = 0; i < userList.size(); i++) {
			TrainPhysicalAchievement item = new TrainPhysicalAchievement();
			item.setTrainPhysicalId(physical.getId());
			item.setPoliceId(userList.get(i).getPoliceId());
			item.setRegistrationDate(physical.getRegistrationStartDate());
			// ??????????????????
			List<TrainGroupPolice> groupList = trainService.trainGroupPoliceList(userList.get(i).getPoliceId());
			item.setTrainGroupId(groupList.get(0).getId());
			item.setQrCode("/train-qrcode/physical-870c515b-6d06-445f-bf11-c143bdc6a878.jpg");
			item.setIsSign(2);
			item.setAchievementGrade(1);
			item.setCreationDate(new Date());
			item.setSignDate(physical.getRegistrationStartDate());
			trainService.trainPhysicalAchievementCreat(item);
			List<TrainProject> projectList = trainService.trainPoliceBelongToList(physical.getId(),
					groupList.get(0).getId());
			for (int j = 0; j < projectList.size(); j++) {
				// ??????????????????
				TrainPhysicalAchievementDetails deItem = new TrainPhysicalAchievementDetails();
				deItem.setTrainPhysicalId(physical.getId());
				deItem.setTrainPhysicalAchievementId(item.getId());
				deItem.setPoliceId(userList.get(i).getPoliceId());
				deItem.setProjectId(projectList.get(j).getId());
				deItem.setIsEntry(2);
				deItem.setIsSign(2);
				deItem.setSignDate(physical.getRegistrationStartDate());
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
		// ?????????????????????
		List<TrainPhysicalAchievementDetails> finalList = new ArrayList<TrainPhysicalAchievementDetails>();
		// ???????????????????????????????????????????????????
//		List<TrainPhysicalAchievement> updateAchievementList = new ArrayList<TrainPhysicalAchievement>();
		// ???????????????
		try {
			for (List<String> excel : readExcel) {
				// ???????????????
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
						train1.setSignDate(physical.getRegistrationStartDate());
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
						train2.setSignDate(physical.getRegistrationStartDate());
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
						train3.setSignDate(physical.getRegistrationStartDate());
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
						train4.setSignDate(physical.getRegistrationStartDate());
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
						train5.setSignDate(physical.getRegistrationStartDate());
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
						train6.setSignDate(physical.getRegistrationStartDate());
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
						train7.setSignDate(physical.getRegistrationStartDate());
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
						train8.setSignDate(physical.getRegistrationStartDate());
						finalList.add(train8);
					}
				}
			}
			// ????????????????????????
			trainService.trainPhysicalAchievementDetailsUpdateBatch(finalList);
			// ????????????id??????????????????list
			List<TrainPhysicalAchievement> updateGradeList = trainService.updateGradeList(physical.getId());
			if (updateGradeList.size() > 0) {
				for (int i = 0; i < updateGradeList.size(); i++) {
					achievementItem(physical.getId(), updateGradeList.get(i).getPoliceId());
//					if (achievementItem != null) {
//						updateAchievementList.add(achievementItem);
//					}
				}
			}
			// ??????????????????????????????
//			trainService.trainPhysicalAchievementUpdateBatch(updateGradeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		totalRiskDetailsService.skillRiskDetails(LocalDate.parse(registrationStartDate.substring(0, 10)));
		System.out.println("??????: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
//		System.out.println("???" + index + "?????????");
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setStatus(true);
		dataListReturn.setResult(1);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	// ??????????????????
	private TrainPhysicalAchievementDetails updateItem(Integer id, Double score, Integer achievementGrade,
			String projectName, Integer projectId) {
		TrainPhysicalAchievementDetails train = new TrainPhysicalAchievementDetails();
		TrainProject project = trainService.getTrainProjectById(projectId);
		train.setId(id);
		train.setAchievement(score);
		train.setAchievementGrade(achievementGrade);
		train.setAchievementDate(new Date());
		train.setUpdateDate(new Date());
		if (projectName.equals("2000??????")) {
			DecimalFormat df = new DecimalFormat("#.00");
			double x = score;
			int m = (int) x;
			double y = Double.valueOf(df.format(x - m));
			train.setAchievementStr(m + "???" + Double.valueOf(df.format(y * 60.0)) + "???");
			train.setAchievementFirst(m);
			train.setAchievementSecond(Double.valueOf(df.format(y * 60.0)));
		} else {
			train.setAchievementStr(score + project.getUnitName());
		}
		return train;
	}

	// ????????????id
	private TrainPhysicalAchievementDetails detailsItem(Integer trainPhysicalId, String policeId, String projectName) {
		TrainPhysicalAchievementDetails item = null;
		// ???????????????????????????id
		TrainProject pItem = trainService.trainProjectIdItem(projectName);
		Integer projectId = null;
		if (pItem != null) {
			projectId = pItem.getId();
			// ???????????????????????????
			item = trainService.physicalDetailsItem(null, trainPhysicalId, null, policeId, projectId);
		}
		return item;

	}

	// ????????????????????????
	private Integer detailsGrade(Integer trainPhysicalId, String policeId, Integer projectId, Double achievement) {
		Integer detailsGrade = null;
		// ??????????????????????????????????????????
		TrainPhysicalProjectRecord projectItem = trainService.projectNamesItem(trainPhysicalId, policeId);
		// ????????????id/????????????????????????
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

	// ??????????????????????????????
	private TrainPhysicalAchievement achievementItem(Integer trainPhysicalId, String policeId) {
		// ????????????????????????????????????
		TrainPhysicalAchievement aItem = trainService.trainPhysicalAchievementItem(null, trainPhysicalId, policeId);
		aItem.setAchievementDate(new Date());
		aItem.setUpdateDate(new Date());
		// ????????????????????????????????????
		List<TrainPhysicalAchievementDetails> dlist = trainService.detailsFailList(trainPhysicalId, policeId);
		if (dlist.size() > 0) {
			aItem.setAchievementGrade(1);
		} else {
			aItem.setAchievementGrade(2);
		}
		trainService.trainPhysicalAchievementUpdate(aItem);
		return aItem;
	}

	// ???????????????????????????
	@RequestMapping(value = "/import/train/firearm", method = RequestMethod.POST)
	public ResponseEntity<?> importAddTrainFirearm(@Param("name") String name,
			@Param("registrationStartDate") String registrationStartDate, @Param("type") Integer type,
			@Param("file") MultipartFile file, HttpServletRequest request) {
		TrainFirearm trainFirearm = new TrainFirearm();
		trainFirearm.setType(type);
		trainFirearm.setName(name);
		trainFirearm.setDepartmentId(1);
		trainFirearm.setPlace("??????");
		trainFirearm.setRegistrationStartDate(DateUtil.parseDateTime(registrationStartDate +":00"));
		trainFirearm.setRegistrationEndDate(DateUtil.parseDateTime(registrationStartDate +":00"));
		trainFirearm.setTrainStartDate(DateUtil.parseDateTime(registrationStartDate +":00"));
		trainFirearm.setTrainEndDate(DateUtil.parseDateTime(registrationStartDate +":00"));
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

					// ????????????????????????
					TrainFirearmAchievement item = new TrainFirearmAchievement();
					item.setTrainFirearmId(trainFirearm.getId());
					item.setPoliceId(policeId);
					item.setRegistrationDate(trainFirearm.getRegistrationStartDate());
					item.setSignDate(trainFirearm.getRegistrationStartDate());
					item.setIsSubmit(1);
					item.setQrCode("/train-qrcode/physical-870c515b-6d06-445f-bf11-c143bdc6a878.jpg\"");
					item.setTrainProjectType(2);
					item.setIsSign(2);
					item.setAchievement(score1);
					item.setAchievementStr(score1 + "???");
					item.setAchievementGrade(1);
					if (score1 >= 60) {
						item.setAchievementGrade(2);
					}
					item.setAchievementDate(new Date());
					item.setSignDate(trainFirearm.getRegistrationStartDate());
					item.setCreationDate(new Date());

					finalList.add(item);
				}
			}
			trainService.trainFirearmAchievementCreatBatch(finalList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		totalRiskDetailsService.skillRiskDetails(LocalDate.parse(registrationStartDate.substring(0, 10)));
		System.out.println("??????: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
//			System.out.println("???" + index + "?????????");
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
			throw new RuntimeException("????????????!");
		}
		physical.setUpdateDate(new Date());

		List<List<String>> readExcel = null;
		try {
			readExcel = GetExcel.ReadExcel(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ?????????????????????
		List<TrainPhysicalAchievementDetails> finalList = new ArrayList<TrainPhysicalAchievementDetails>();
		List<TrainPhysicalAchievementDetails> insertList = new ArrayList<>();

		// ???????????????
		try {
			for (List<String> excel : readExcel) {
				// ???????????????
				String policeId = excel.get(1).toString().replaceAll("\\s*", "");

				//??????????????????
				TrainPhysicalAchievement trainPhysicalAchievement = trainService.findTrainPhysicalAchievementByPolice(physical.getId(), policeId);
				if (trainPhysicalAchievement == null || trainPhysicalAchievement.getId() == null) {
					trainPhysicalAchievement = new TrainPhysicalAchievement();
					trainPhysicalAchievement.setTrainPhysicalId(physical.getId());
					trainPhysicalAchievement.setPoliceId(policeId);
					trainPhysicalAchievement.setRegistrationDate(physical.getRegistrationStartDate());
					trainPhysicalAchievement.setAchievementDate(physical.getTrainStartDate());
					trainPhysicalAchievement.setSignDate(physical.getRegistrationStartDate());
					// ??????????????????
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
						train1.setSignDate(physical.getRegistrationStartDate());
						finalList.add(train1);
					}else {
						//??????????????????
						TrainProject pItem = trainService.trainProjectIdItem(projectName1);
						//??????????????????
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, pItem.getId(), score1);
						//??????????????????
						detailsItem1 = updateItem(null, score1, achievementGrade, projectName1, pItem.getId());
						detailsItem1.setPoliceId(policeId);
						detailsItem1.setTrainPhysicalId(physical.getId());
						detailsItem1.setTrainPhysicalAchievementId(trainPhysicalAchievement.getId());
						detailsItem1.setProjectId(pItem.getId());
						detailsItem1.setCreationDate(new Date());
						detailsItem1.setUpdateDate(null);
						detailsItem1.setIsEntry(2);
						detailsItem1.setIsSign(2);
						detailsItem1.setSignDate(physical.getRegistrationStartDate());
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
						train2.setSignDate(physical.getRegistrationStartDate());
						finalList.add(train2);
					}else {
						//??????????????????
						TrainProject pItem = trainService.trainProjectIdItem(projectName2);
						//??????????????????
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, pItem.getId(), score2);
						//??????????????????
						detailsItem2 = updateItem(null, score2, achievementGrade, projectName2, pItem.getId());
						detailsItem2.setPoliceId(policeId);
						detailsItem2.setTrainPhysicalId(physical.getId());
						detailsItem2.setTrainPhysicalAchievementId(trainPhysicalAchievement.getId());
						detailsItem2.setProjectId(pItem.getId());
						detailsItem2.setCreationDate(new Date());
						detailsItem2.setUpdateDate(null);
						detailsItem2.setIsEntry(2);
						detailsItem2.setIsSign(2);
						detailsItem2.setSignDate(physical.getRegistrationStartDate());
						insertList.add(detailsItem2);
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
						train3.setSignDate(physical.getRegistrationStartDate());
						finalList.add(train3);
					}else {
						//??????????????????
						TrainProject pItem = trainService.trainProjectIdItem(projectName3);
						//??????????????????
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, pItem.getId(), score3);
						//??????????????????
						detailsItem3 = updateItem(null, score3, achievementGrade, projectName3, pItem.getId());
						detailsItem3.setPoliceId(policeId);
						detailsItem3.setTrainPhysicalId(physical.getId());
						detailsItem3.setTrainPhysicalAchievementId(trainPhysicalAchievement.getId());
						detailsItem3.setProjectId(pItem.getId());
						detailsItem3.setCreationDate(new Date());
						detailsItem3.setUpdateDate(null);
						detailsItem3.setIsEntry(2);
						detailsItem3.setIsSign(2);
						detailsItem3.setSignDate(physical.getRegistrationStartDate());
						insertList.add(detailsItem3);
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
						train4.setSignDate(physical.getRegistrationStartDate());
						finalList.add(train4);
					}else {
						//??????????????????
						TrainProject pItem = trainService.trainProjectIdItem(projectName4);
						//??????????????????
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, pItem.getId(), score4);
						//??????????????????
						detailsItem4 = updateItem(null, score4, achievementGrade, projectName4, pItem.getId());
						detailsItem4.setPoliceId(policeId);
						detailsItem4.setTrainPhysicalId(physical.getId());
						detailsItem4.setTrainPhysicalAchievementId(trainPhysicalAchievement.getId());
						detailsItem4.setProjectId(pItem.getId());
						detailsItem4.setCreationDate(new Date());
						detailsItem4.setUpdateDate(null);
						detailsItem4.setIsEntry(2);
						detailsItem4.setIsSign(2);
						detailsItem4.setSignDate(physical.getRegistrationStartDate());
						insertList.add(detailsItem4);
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
						train5.setSignDate(physical.getRegistrationStartDate());
						finalList.add(train5);
					}else {
						//??????????????????
						TrainProject pItem = trainService.trainProjectIdItem(projectName5);
						//??????????????????
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, pItem.getId(), score5);
						//??????????????????
						detailsItem5 = updateItem(null, score5, achievementGrade, projectName5, pItem.getId());
						detailsItem5.setPoliceId(policeId);
						detailsItem5.setTrainPhysicalId(physical.getId());
						detailsItem5.setTrainPhysicalAchievementId(trainPhysicalAchievement.getId());
						detailsItem5.setProjectId(pItem.getId());
						detailsItem5.setCreationDate(new Date());
						detailsItem5.setUpdateDate(null);
						detailsItem5.setIsEntry(2);
						detailsItem5.setIsSign(2);
						detailsItem5.setSignDate(physical.getRegistrationStartDate());
						insertList.add(detailsItem5);
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
						train6.setSignDate(physical.getRegistrationStartDate());
						finalList.add(train6);
					}else {
						//??????????????????
						TrainProject pItem = trainService.trainProjectIdItem(projectName6);
						//??????????????????
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, pItem.getId(), score6);
						//??????????????????
						detailsItem6 = updateItem(null, score6, achievementGrade, projectName6, pItem.getId());
						detailsItem6.setPoliceId(policeId);
						detailsItem6.setTrainPhysicalId(physical.getId());
						detailsItem6.setTrainPhysicalAchievementId(trainPhysicalAchievement.getId());
						detailsItem6.setProjectId(pItem.getId());
						detailsItem6.setCreationDate(new Date());
						detailsItem6.setUpdateDate(null);
						detailsItem6.setIsEntry(2);
						detailsItem6.setIsSign(2);
						detailsItem6.setSignDate(physical.getRegistrationStartDate());
						insertList.add(detailsItem6);
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
						train7.setSignDate(physical.getRegistrationStartDate());
						finalList.add(train7);
					}else {
						//??????????????????
						TrainProject pItem = trainService.trainProjectIdItem(projectName7);
						//??????????????????
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, pItem.getId(), score7);
						//??????????????????
						detailsItem7 = updateItem(null, score7, achievementGrade, projectName7, pItem.getId());
						detailsItem7.setPoliceId(policeId);
						detailsItem7.setTrainPhysicalId(physical.getId());
						detailsItem7.setTrainPhysicalAchievementId(trainPhysicalAchievement.getId());
						detailsItem7.setProjectId(pItem.getId());
						detailsItem7.setCreationDate(new Date());
						detailsItem7.setUpdateDate(null);
						detailsItem7.setIsEntry(2);
						detailsItem7.setIsSign(2);
						detailsItem7.setSignDate(physical.getRegistrationStartDate());
						insertList.add(detailsItem7);
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
						train8.setSignDate(physical.getRegistrationStartDate());
						finalList.add(train8);
					}else {
						//??????????????????
						TrainProject pItem = trainService.trainProjectIdItem(projectName8);
						//??????????????????
						Integer achievementGrade = detailsGrade(physical.getId(), policeId, pItem.getId(), score8);
						//??????????????????
						detailsItem8 = updateItem(null, score8, achievementGrade, projectName8, pItem.getId());
						detailsItem8.setPoliceId(policeId);
						detailsItem8.setTrainPhysicalId(physical.getId());
						detailsItem8.setTrainPhysicalAchievementId(trainPhysicalAchievement.getId());
						detailsItem8.setProjectId(pItem.getId());
						detailsItem8.setCreationDate(new Date());
						detailsItem8.setUpdateDate(null);
						detailsItem8.setIsEntry(2);
						detailsItem8.setIsSign(2);
						detailsItem8.setSignDate(physical.getRegistrationStartDate());
						insertList.add(detailsItem8);
					}
				}
			}
			// ????????????????????????
			if (finalList.size() > 0) {
				trainService.trainPhysicalAchievementDetailsUpdateBatch(finalList);
			}
			//????????????????????????
			if (insertList.size() > 0) {
				trainService.trainPhysicalAchievementDetailsCreatBatch(insertList);
			}
			totalRiskDetailsService.skillRiskDetails(LocalDate.parse(DateUtils.formatDate(physical.getCreationDate(), "yyyy-MM-dd")));
			// ????????????id??????????????????list
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
			throw new RuntimeException("????????????");
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
					// ????????????????????????
					TrainFirearmAchievement fItem = trainService.trainFirearmAchievementItem(null, trainFirearm.getId(),
							policeId);
					if (fItem != null) {
						fItem.setAchievement(score1);
						fItem.setAchievementStr(score1 + "???");
						fItem.setUpdateDate(new Date());
						fItem.setAchievementDate(new Date());

						if (score1 >= 60) {
							fItem.setAchievementGrade(2);
						} else {
							fItem.setAchievementGrade(1);
						}
						fItem.setSignDate(trainFirearm.getRegistrationStartDate());
						finalList.add(fItem);
					}else {
						fItem = new TrainFirearmAchievement();
						fItem.setPoliceId(policeId);
						fItem.setTrainFirearmId(id);
						fItem.setRegistrationDate(trainFirearm.getRegistrationStartDate());
						fItem.setAchievementDate(trainFirearm.getTrainStartDate());
						fItem.setIsSign(2);
						fItem.setSignDate(trainFirearm.getRegistrationStartDate());
						fItem.setTrainProjectType(id);
						fItem.setAchievement(score1);
						fItem.setAchievementStr(score1 + "???");
						fItem.setIsSubmit(1);
						fItem.setCreationDate(new Date());
						fItem.setQrCode("/train-qrcode/physical-870c515b-6d06-445f-bf11-c143bdc6a878.jpg");

						if (score1 >= 60) {
							fItem.setAchievementGrade(2);
						} else {
							fItem.setAchievementGrade(1);
						}
						insertList.add(fItem);
					}
				}
			}
			// ??????????????????????????????
			if(finalList.size() > 0) {
				trainService.trainFirearmAchievementUpdateBatch(finalList);
			}
			//??????????????????????????????
			if (insertList.size() > 0) {
				trainService.trainFirearmAchievementCreatBatch(insertList);
			}
			totalRiskDetailsService.skillRiskDetails(LocalDate.parse(DateUtils.formatDate(trainFirearm.getCreationDate(), "yyyy-MM-dd")));
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
