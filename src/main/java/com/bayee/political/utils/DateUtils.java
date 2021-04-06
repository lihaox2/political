/**
 * 
 */
package com.bayee.political.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.bayee.political.domain.ReportDataFillTime;

/**
 * @author seanguo
 *
 */
public class DateUtils {

	private final static DateFormat DATE_TIME_FORMAT1 = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

	public final static SimpleDateFormat DATE_TIME_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static long getUnixTime(String dateTime) {
		long epoch = 0l;
		try {
			epoch = DATE_TIME_FORMAT1.parse(dateTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return epoch;
	}

	public static Date toDate(String dateStr) {
		try {
			return DATE_TIME_FORMAT2.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date parseDate(String dateStr, String format) {
		if (StringUtils.isEmpty(dateStr))
			return null;
		try {
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(format);
			return DATE_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String formatDate(Date date, String format) {
		if (date == null)
			return null;
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	// 获得当天0点时间
	public static Date getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();

	}

	// 获得昨天0点时间
	public static Date getYesterdaymorning() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getTimesmorning().getTime() - 3600 * 24 * 1000);
		return cal.getTime();
	}

	// 获得当天近7天时间
	public static Date getWeekFromNow() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getTimesmorning().getTime() - 3600 * 24 * 1000 * 7);
		return cal.getTime();
	}

	// 获得当天24点时间
	public static Date getTimesnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	// 获得本周一0点时间
	public static Date getTimesWeekmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	// 获得本周日24点时间
	public static Date getTimesWeeknight() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesWeekmorning());
		cal.add(Calendar.DAY_OF_WEEK, 7);
		return cal.getTime();
	}

	// 获得本月第一天0点时间
	public static Date getTimesMonthmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	// 获得本月最后一天24点时间
	public static Date getTimesMonthnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		return cal.getTime();
	}

	public static Date getLastMonthStartMorning() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesMonthmorning());
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	public static Date getCurrentQuarterStartTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 当前季度的结束时间。即2012-03-31 23:59:59
	 *
	 * @return
	 */
	public static Date getCurrentQuarterEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentQuarterStartTime());
		cal.add(Calendar.MONTH, 3);
		return cal.getTime();
	}

	public static Date getCurrentYearStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.YEAR));
		return cal.getTime();
	}

	public static Date getCurrentYearEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentYearStartTime());
		cal.add(Calendar.YEAR, 1);
		return cal.getTime();
	}

	public static Date getLastYearStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentYearStartTime());
		cal.add(Calendar.YEAR, -1);
		return cal.getTime();
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

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	// 获取当前日期当前周,当前月,当前季度,当前半年,当前年的时间段
	public static ReportDataFillTime fillDate(String dataTime, Integer fillId) throws ParseException {
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
