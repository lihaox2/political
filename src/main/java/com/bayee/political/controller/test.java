package com.bayee.political.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bayee.political.domain.LeaveCompensatoryAlarm;
import com.bayee.political.domain.MatchRecordScore;
import com.bayee.political.domain.TrainMatch;
import com.bayee.political.domain.TrainMatchAchievement;
import com.bayee.political.domain.User;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import com.bayee.political.utils.QRCode;
import com.bayee.political.utils.StatusCode;

/**
 * @author shentuqiwei
 * @version 2020年9月17日 下午5:10:47
 */
public class test {
	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String s = "2020-02";
//		String s1 = "2020-11-30 15:06:32";
//		String daString = "[\"2020-10-20\",\"2020-10-24\",4,\"day\",\"年休假\",\"请假类型\"]";
//		String[] daStrin = daString.replaceAll("\\[", "").replaceAll("\\]", "")
//				.replaceAll("\"", "").split(",");
//		DateUtils.toDate("2020-10-20");

//		System.out.println(formatter.parse("2020-10-20"));
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date1 = format.parse(s);
//		Date date2 = format.parse(s1);
//		boolean isTrue = date1.before(date2);
//		System.out.println("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855".length());
		System.out.println(s.substring(5, 7));

//		System.out.println(isTrue);

//		double d = 3.8415926;
//		String result = String.format("%.0f", d);
//		System.out.println(result);
//		String number = "2563.2154";
//		int index = number.indexOf(".");
//		String intNumber = number.substring(0,index);
//		System.out.println("测试1："+intNumber); 
//		double endTimeDou = 1601049600000.0;
//		double startTimeDou = 1600963200000.0;
//		double currentScore = (endTimeDou - startTimeDou) / 86400000.00;// 时长(天);
//		System.out.println(currentScore);
//		List<Map<String, String>> list = queryWeek("2020","11");
//		List<String> startList = new ArrayList<String>();
//		List<String> endList = new ArrayList<String>();
//		for (Map<String, String> m :list){
//            for (String k :m.keySet()){
//                Object ob = m.get(k);
//                if (k.equals("start")) {
//                	startList.add(ob.toString());
//				}
//                if (k.equals("end")) {
//                	endList.add(ob.toString());
//				}
//                System.out.println(k + " : "+ob.toString());
//            }
//        }
//		System.out.println(startList);
//		System.out.println(endList);
		
		String k = "7.89";
	      double x = Double.parseDouble(k);
	      System.out.println("你输入的浮点数是："+x);
	      int m = (int)x;
	      double y = x - m;
	      System.out.println("整数部分："+m);
	      System.out.println("小数部分:"+y);
	}
	
	public static String getNumber(String str) {
        // 需要取整数和小数的字符串
        // 控制正则表达式的匹配行为的参数(小数)
        Pattern p = Pattern.compile("(\\d+\\.\\d+)");
        //Matcher类的构造方法也是私有的,不能随意创建,只能通过Pattern.matcher(CharSequence input)方法得到该类的实例.
        Matcher m = p.matcher(str);
        //m.find用来判断该字符串中是否含有与"(\\d+\\.\\d+)"相匹配的子串
        if (m.find()) {
            //如果有相匹配的,则判断是否为null操作
            //group()中的参数：0表示匹配整个正则，1表示匹配第一个括号的正则,2表示匹配第二个正则,在这只有一个括号,即1和0是一样的
            str = m.group(1) == null ? "" : m.group(1);
        } else {
            //如果匹配不到小数，就进行整数匹配
            p = Pattern.compile("(\\d+)");
            m = p.matcher(str);
            if (m.find()) {
                //如果有整数相匹配
                str = m.group(1) == null ? "" : m.group(1);
            } else {
                //如果没有小数和整数相匹配,即字符串中没有整数和小数，就设为空
                str = "";
            }
        }
        System.out.println();
        return str;
    }

	public static String Encrypt(String strSrc, String encName) {
		MessageDigest md = null;
		StringBuilder sb = new StringBuilder();

		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance(encName);
			byte[] result = md.digest(bt);
			for (byte b : result) {
				sb.append(String.format("%02x", b));
			}
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return sb.toString();
	}

	/**
	 * 根据年月返回本月共几周，每周开始与结束日期
	 */
	public static List<Map<String, String>> queryWeek(String year, String month) throws ParseException {
		/** 周 **/
		final String[] weeks = { "第一周", "第二周", "第三周", "第四周", "第五周", "第六周" };
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd");
		calendar.setTime(sdf.parse(year + "-" + month + "-01"));
		int i = calendar.get(Calendar.DAY_OF_WEEK);
		int months = calendar.get(Calendar.MONTH);
		switch (i) {
		case 1:
			calendar.add(Calendar.DATE, 1);
			break;
		case 2:
			calendar.add(Calendar.DATE, 0);
			break;
		case 3:
			calendar.add(Calendar.DATE, 6);
			break;
		case 4:
			calendar.add(Calendar.DATE, 5);
			break;
		case 5:
			calendar.add(Calendar.DATE, 4);
			break;
		case 6:
			calendar.add(Calendar.DATE, 3);
			break;
		case 7:
			calendar.add(Calendar.DATE, 2);
			break;
		default:
			break;
		}
		// 本月的第一个星期
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		int index = 0;
		while (true) {
			if (index++ > 0)
				calendar.add(Calendar.DATE, 1);
			Map<String, String> map = new HashMap<String, String>();
			map.put("startTime", sdf2.format(calendar.getTime()));
			map.put("start", sdf.format(calendar.getTime()));
			calendar.add(Calendar.DATE, 6);
			map.put("endTime", sdf2.format(calendar.getTime()));
			map.put("end", sdf.format(calendar.getTime()));
			map.put("week", weeks[index - 1]);
			list.add(map);
			// 判断是否跨月
			if (months != calendar.get(Calendar.MONTH))
				break;
		}
		return list;
	}
}

//个人赛事报名(单位)
//	@ResponseBody
//	@RequestMapping(value = "/match/personal/sign/up/creat", method = RequestMethod.POST)
//	public DataListReturn matchPersonalSignUpCreat(@RequestParam(value = "id", required = false) Integer id,
//			@RequestParam(value = "policeId", required = false) String policeId) throws IOException, ParseException {
//		DataListReturn dlr = new DataListReturn();
//		TrainMatchAchievement item = new TrainMatchAchievement();
//		// 个人赛事详情查询
//		TrainMatchAchievement aItem = trainMatchService.matchAchievementItem(null, id, policeId);
//		if (aItem == null) {
//			// 赛事详情查询
//			TrainMatch tItem = trainMatchService.matchItem(id);
//			long startTimeLong = tItem.getRegistrationStartDate().getTime();
//			long endTimeLong = tItem.getRegistrationEndDate().getTime();
//			long currentTime = new Date().getTime();
//			if (currentTime < startTimeLong) {
//				dlr.setStatus(false);
//				dlr.setMessage("报名未开始");
//				dlr.setResult(0);
//				dlr.setCode(StatusCode.getFailcode());
//				return dlr;
//			} else if (currentTime > endTimeLong) {
//				dlr.setStatus(false);
//				dlr.setMessage("报名已截止");
//				dlr.setResult(0);
//				dlr.setCode(StatusCode.getFailcode());
//				return dlr;
//			}
//			// 根据policeId查询部门
//			User user = userService.policeItem(policeId, null, null);
//			Integer sex = user.getGender();
//			// 查询当前项目男性报名人数
//			int manNum = trainMatchService.alreadySignUpPoliceNum(id, 1);
//			// 查询当前项目女性报名人数
//			int womanNum = trainMatchService.alreadySignUpPoliceNum(id, 2);
//
//			if (tItem.getManMaxNum() > 0 && tItem.getWomanMaxNum() == 0) {
//				if (sex == 1) {// 男
//					if (tItem.getMaxPeopleNum() <= manNum) {
//						dlr.setStatus(false);
//						dlr.setMessage("您来晚了，报名人数已满");
//						dlr.setResult(0);
//						dlr.setCode(StatusCode.getFailcode());
//						return dlr;
//					}
//				} else if (sex == 2) {// 女
//					dlr.setStatus(false);
//					dlr.setMessage("女性不可参加当前比赛");
//					dlr.setResult(0);
//					dlr.setCode(StatusCode.getFailcode());
//					return dlr;
//				}
//
//			} else if (tItem.getManMaxNum() > 0 && tItem.getWomanMaxNum() > 0) {
//				if (sex == 1) {// 男
//					if (tItem.getMaxPeopleNum() <= manNum) {
//						dlr.setStatus(false);
//						dlr.setMessage("您来晚了，报名人数已满");
//						dlr.setResult(0);
//						dlr.setCode(StatusCode.getFailcode());
//						return dlr;
//					}
//				} else if (sex == 2) {// 女
//					if (tItem.getWomanMaxNum() <= manNum) {
//						dlr.setStatus(false);
//						dlr.setMessage("您来晚了，报名人数已满");
//						dlr.setResult(0);
//						dlr.setCode(StatusCode.getFailcode());
//						return dlr;
//					}
//				}
//			} else if (tItem.getManMaxNum() == 0 && tItem.getWomanMaxNum() > 0) {
//				if (sex == 1) {// 男
//					dlr.setStatus(false);
//					dlr.setMessage("男性不可参加当前比赛");
//					dlr.setResult(0);
//					dlr.setCode(StatusCode.getFailcode());
//					return dlr;
//				} else if (sex == 2) {// 女
//					if (tItem.getWomanMaxNum() <= womanNum) {
//						dlr.setStatus(false);
//						dlr.setMessage("您来晚了，报名人数已满");
//						dlr.setResult(0);
//						dlr.setCode(StatusCode.getFailcode());
//						return dlr;
//					}
//				}
//			}
//
//			if (tItem.getLimitPeopleNum() == 1) {// 是否限制报名人数(1否2是)
//				if (tItem.getNature() == 1) { // 性质1个人赛
//					if (tItem.getManMaxNum() == 1 && tItem.getWomanMaxNum() == 0) {// 男的可参加
//						if (sex == 1) {// 男
//
//						} else if (sex == 2) {// 女
//							dlr.setStatus(false);
//							dlr.setMessage("女性不可参加当前比赛");
//							dlr.setResult(0);
//							dlr.setCode(StatusCode.getFailcode());
//							return dlr;
//						}
//					} else if (tItem.getManMaxNum() == 0 && tItem.getWomanMaxNum() == 1) {// 女的可参加
//						if (sex == 1) {// 男
//							dlr.setStatus(false);
//							dlr.setMessage("男性不可参加当前比赛");
//							dlr.setResult(0);
//							dlr.setCode(StatusCode.getFailcode());
//							return dlr;
//						} else if (sex == 2) {// 女
//
//						}
//					}
//				} else if (tItem.getNature() == 2) {// 2团体赛
//					if (tItem.getManMaxNum() > 0 && tItem.getWomanMaxNum() > 0) {// 男女均可报
//
//					} else if (tItem.getManMaxNum() > 0 && tItem.getWomanMaxNum() == 0) {// 男可报
//						if (sex == 1) {// 男
//
//						} else if (sex == 2) {// 女
//							dlr.setStatus(false);
//							dlr.setMessage("女性不可参加当前团体比赛");
//							dlr.setResult(0);
//							dlr.setCode(StatusCode.getFailcode());
//							return dlr;
//						}
//					} else if (tItem.getManMaxNum() == 0 && tItem.getWomanMaxNum() > 0) {// 女可报
//						if (sex == 1) {// 男
//							dlr.setStatus(false);
//							dlr.setMessage("男性不可参加当前团体比赛");
//							dlr.setResult(0);
//							dlr.setCode(StatusCode.getFailcode());
//							return dlr;
//						} else if (sex == 2) {// 女
//
//						}
//					}
//
//				}
//
//			} else if (tItem.getLimitPeopleNum() == 2) {
//				if (tItem.getNature() == 1) { // 性质：1个人赛
//					if (tItem.getManMaxNum() == 1 && tItem.getWomanMaxNum() == 0) {// 男的可参加
//						if (sex == 1) {// 男
//							if (tItem.getMaxPeopleNum() > manNum) {
//
//							} else if (tItem.getMaxPeopleNum() <= manNum) {
//								dlr.setStatus(false);
//								dlr.setMessage("您来晚了，报名人数已满");
//								dlr.setResult(0);
//								dlr.setCode(StatusCode.getFailcode());
//								return dlr;
//							}
//						} else if (sex == 2) {// 女
//							dlr.setStatus(false);
//							dlr.setMessage("女性不可参加当前比赛");
//							dlr.setResult(0);
//							dlr.setCode(StatusCode.getFailcode());
//							return dlr;
//						}
//					} else if (tItem.getManMaxNum() == 0 && tItem.getWomanMaxNum() == 1) {// 女的可参加
//						if (sex == 1) {// 男
//							dlr.setStatus(false);
//							dlr.setMessage("男性不可参加当前比赛");
//							dlr.setResult(0);
//							dlr.setCode(StatusCode.getFailcode());
//							return dlr;
//						} else if (sex == 2) {// 女
//							if (tItem.getMaxPeopleNum() > womanNum) {
//
//							} else if (tItem.getMaxPeopleNum() <= womanNum) {
//								dlr.setStatus(false);
//								dlr.setMessage("您来晚了，报名人数已满");
//								dlr.setResult(0);
//								dlr.setCode(StatusCode.getFailcode());
//								return dlr;
//							}
//						}
//					}
//				} else if (tItem.getNature() == 2) {// 性质:2团体赛
//					int needManNum = tItem.getManMaxNum() * tItem.getMaxPeopleNum();// 男的最多可报数量
//					int needWomanNum = tItem.getWomanMaxNum() * tItem.getMaxPeopleNum();// 男的最多可报数量
//					if (tItem.getManMaxNum() > 0 && tItem.getWomanMaxNum() > 0) {// 男女均可报
//						if (sex == 1) {// 男
//							if (needManNum > manNum) {
//
//							} else if (needManNum <= manNum) {
//								dlr.setStatus(false);
//								dlr.setMessage("当前团体比赛男性人数已满");
//								dlr.setResult(0);
//								dlr.setCode(StatusCode.getFailcode());
//								return dlr;
//							}
//						} else if (sex == 2) {// 女
//							if (needWomanNum > womanNum) {
//
//							} else if (needWomanNum <= womanNum) {
//								dlr.setStatus(false);
//								dlr.setMessage("当前团体比赛女性人数已满");
//								dlr.setResult(0);
//								dlr.setCode(StatusCode.getFailcode());
//								return dlr;
//							}
//						}
//					} else if (tItem.getManMaxNum() > 0 && tItem.getWomanMaxNum() == 0) {// 男可报
//						if (sex == 1) {// 男
//							if (needManNum > manNum) {
//
//							} else if (needManNum <= manNum) {
//								dlr.setStatus(false);
//								dlr.setMessage("当前团体比赛男性人数已满");
//								dlr.setResult(0);
//								dlr.setCode(StatusCode.getFailcode());
//								return dlr;
//							}
//						} else if (sex == 2) {// 女
//							dlr.setStatus(false);
//							dlr.setMessage("女性不可参加当前团体比赛");
//							dlr.setResult(0);
//							dlr.setCode(StatusCode.getFailcode());
//							return dlr;
//						}
//					} else if (tItem.getManMaxNum() == 0 && tItem.getWomanMaxNum() > 0) {// 女可报
//						if (sex == 1) {// 男
//							dlr.setStatus(false);
//							dlr.setMessage("男性不可参加当前团体比赛");
//							dlr.setResult(0);
//							dlr.setCode(StatusCode.getFailcode());
//							return dlr;
//						} else if (sex == 2) {// 女
//							if (needWomanNum > womanNum) {
//
//							} else if (needWomanNum <= womanNum) {
//								dlr.setStatus(false);
//								dlr.setMessage("当前团体比赛女性人数已满");
//								dlr.setResult(0);
//								dlr.setCode(StatusCode.getFailcode());
//								return dlr;
//							}
//						}
//					}
//				}
//			}
//			item.setDepartmentId(user.getDepartmentId());
//			item.setTrainMatchId(id);
//			item.setPoliceId(policeId);
//			item.setRegistrationDate(new Date());
//			item.setIsSign(1);
//			item.setCreationDate(new Date());
//			String qrName = "physical-" + UUID.randomUUID().toString() + ".jpg";
//			item.setQrCode(trainQRCode + qrName);
//			// 生成二维码
//			new QRCode(root + trainQRCodeIcon).encodeQRCode(id + "," + policeId, 500, 500, root + trainQRCode + qrName);
//			int count = trainMatchService.matchAchievementCreat(item);
//			if (count > 0) {
//				dlr.setStatus(true);
//				dlr.setMessage("报名成功");
//				dlr.setResult(item);
//				dlr.setCode(StatusCode.getSuccesscode());
//				return dlr;
//			} else {
//				dlr.setStatus(false);
//				dlr.setMessage("报名失败");
//				dlr.setResult(0);
//				dlr.setCode(StatusCode.getFailcode());
//				return dlr;
//			}
//		} else {
//			dlr.setStatus(false);
//			dlr.setMessage("您已报名该赛事");
//			dlr.setResult(0);
//			dlr.setCode(StatusCode.getFailcode());
//			return dlr;
//		}
//	}
//double ruleDay1 = ruleItem1.getDay();
//double ruleDay2 = ruleItem2.getDay();
//// 查询连续未打卡天数
//List<LeaveCompensatoryAlarm> list = leaveProcessService.notRecordList();
//// 查询打卡人员
////List<ClockRecord> clockList = clockService.clockRecordUserIdList();
//for (int j = 0; j < list.size(); j++) {
//	// 查询连续工作预警
//	LeaveCompensatoryAlarm continueItem = leaveProcessService.continuePoliceItem(2,
//			list.get(j).getPoliceId());
//	// 查询个人连续未打卡天数
//	LeaveCompensatoryAlarm notItem = leaveProcessService.notRecordPoliceItem(list.get(j).getPoliceId());
//	LeaveCompensatoryAlarm item = new LeaveCompensatoryAlarm();
//	if (continueItem == null) {
//		if (list.get(j).getWorkDays() >= ruleDay1) {
//			item.setType(2);
//			if (notItem.getWorkDays() >= ruleDay1 && notItem.getWorkDays() < ruleDay2) {
//				item.setRuleId(4);
//			} else {
//				item.setRuleId(5);
//			}
//			item.setPoliceId(list.get(j).getPoliceId());
//			// 根据policeId查询部门
//			User user = userService.policeItem(list.get(j).getPoliceId(), null, null);
//			if (user != null) {
//				item.setName(user.getName());
//				item.setDepartmentId(user.getDepartmentId());
//				item.setPositionId(user.getPositionId());
//			}
//			item.setYear(currentYearStr);
//			item.setWorkDays(list.get(j).getWorkDays());
//			item.setIsRest(0);
//			item.setIdentification(1);
//			item.setCreationDate(new Date());
//			leaveProcessService.leaveCompensatoryAlarmCreat(item);
//		}
//	} else {
//		if (notItem.getWorkDays() > 0) {
//			if (notItem.getRuleId() == 4) {
//				if (notItem.getWorkDays() >= ruleDay1 && notItem.getWorkDays() < ruleDay2) {
//					item.setType(2);
//					item.setRuleId(4);
//					item.setPoliceId(list.get(j).getPoliceId());
//					item.setWorkDays(notItem.getWorkDays());
//					item.setUpdateDate(new Date());
//					leaveProcessService.leaveCompensatoryAlarmTimeUpdate(item);
//				} else {
//					item.setType(2);
//					item.setRuleId(5);
//					item.setPoliceId(list.get(j).getPoliceId());
//					item.setWorkDays(notItem.getWorkDays());
//					item.setUpdateDate(new Date());
//					leaveProcessService.leaveCompensatoryAlarmTimeUpdate(item);
//				}
//			} else if (notItem.getRuleId() == 5) {
//				item.setType(2);
//				item.setRuleId(5);
//				item.setPoliceId(list.get(j).getPoliceId());
//				item.setWorkDays(notItem.getWorkDays());
//				item.setUpdateDate(new Date());
//				leaveProcessService.leaveCompensatoryAlarmTimeUpdate(item);
//			}
//		} else {
//			if (notItem.getWorkDays() >= ruleDay1 && notItem.getWorkDays() < ruleDay2) {
//				item.setRuleId(4);
//			} else {
//				item.setRuleId(5);
//			}
//			item.setType(2);
//			item.setPoliceId(list.get(j).getPoliceId());
//			item.setIdentification(2);
//			item.setUpdateDate(new Date());
//			leaveProcessService.leaveCompensatoryAlarmAllUpdate(item);
//		}
//	}
//}