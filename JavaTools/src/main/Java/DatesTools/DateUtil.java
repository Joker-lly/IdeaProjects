package DatesTools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 如果是JDK8的应用，可以使用Instant代替Date，LocalDateTime代替Calendar，
 * DateTimeFormatter代替Simpledateformatter，官方给出的解释：simple beautiful strong immutable thread-safe。
 */
public class DateUtil {
	/**
	 * 日期转字符串
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date, String formatStr) {
		String strTime = "";
		try {
			DateFormat df = new SimpleDateFormat(formatStr);
			strTime = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strTime;
	}

	/**
	 * 判断日期是null或空
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isDateNotNullOrEmpty(Date date) {
		String str = DateUtil.dateToString(date, "yyyy-MM-dd HH:mm:ss");
		if (str != null && !str.equals(""))
			return true;
		else
			return false;
	}

	/**
	 * 字符串转换到时间格式
	 * 
	 * @param dateStr
	 *            需要转换的字符串
	 * @param formatStr
	 *            需要格式的目标字符串 举例 yyyy-MM-dd
	 * @return Date 返回转换后的时间
	 * @throws ParseException
	 *             转换异常
	 */
	public static Date stringToDate(String dateStr, String formatStr) {
		DateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 自定义获得系统时间
	 * 线程安全
	 * @param Format 时间格式
	 * @return
	 */
	public static String getSystemTime(String Format){
		DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern(Format);
		LocalDateTime now = LocalDateTime.now();
		return dateTimeFormatter.format(now);
	}



	/**
	 * 系统时间加减
	 * 
	 * @param date
	 * @param dayNum
	 * @return
	 */
	public static String getOpeDate(String date, int dayNum) {

		Date dt = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dt = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.add(5, dayNum);
		// gc.set(gc.YEAR,gc.get(gc.MONTH),gc.get(gc.DATE));
		return String.valueOf(df.format(gc.getTime()));
	}

	public static String getOpeDate2(String date, int dayNum) {

		Date dt = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			dt = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.add(5, dayNum);
		// gc.set(gc.YEAR,gc.get(gc.MONTH),gc.get(gc.DATE));
		return String.valueOf(df.format(gc.getTime()));
	}

	/**
	 * 取得两个日期相差几天yyyy-MM-dd
	 */
	public static long getDateDifferent(String date1, String date2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		ParsePosition pos1 = new ParsePosition(0);
		Date dt1 = formatter.parse(date1, pos);
		Date dt2 = formatter.parse(date2, pos1);
		long l = (dt2.getTime() - dt1.getTime()) / (3600 * 24 * 1000);
		return l;
	}

	/**
	 * 取得两个日期相差几个月yyyy-MM-dd
	 */
	public static int getMonthDifferent(String date1, String date2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		ParsePosition pos = new ParsePosition(0);
		ParsePosition pos1 = new ParsePosition(0);
		Date dt1 = formatter.parse(date1, pos);
		Date dt2 = formatter.parse(date2, pos1);
		int l = (dt2.getMonth() - dt1.getMonth());
		return l;
	}

	   public static int getMonth(String date1, String date2) {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
	        ParsePosition pos = new ParsePosition(0);
	        ParsePosition pos1 = new ParsePosition(0);
	        Date dt1 = formatter.parse(date1, pos);
	        Date dt2 = formatter.parse(date2, pos1);
	        int l = (dt2.getMonth() - dt1.getMonth());
	        return l;
	    }
	   
	/**
	 * 取得两个日期相差几天yyyy-MM-dd
	 */
	public static long getDateDifferent2(String date1, String date2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		ParsePosition pos = new ParsePosition(0);
		ParsePosition pos1 = new ParsePosition(0);
		Date dt1 = formatter.parse(date1, pos);
		Date dt2 = formatter.parse(date2, pos1);
		long l = (dt2.getTime() - dt1.getTime()) / (3600 * 24 * 1000);
		return l;
	}

	/* 取得当月最后一天 */
	public static String getLastDayOfMonth() {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));// 年
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));// 月，因为Calendar里的月是从0开始，所以要减1
		cal.set(Calendar.DATE, 1);// 日，设为一号
		cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号
		cal.add(Calendar.DATE, -1);// 下一个月减一为本月最后一天
		String df = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return df;// 获得月末是几号
	}

	/**
	 * 获取年月日对应的当月最后一日
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);// 年
		cal.set(Calendar.MONTH, month - 1);// 月，因为Calendar里的月是从0开始，所以要减1

		int days = getDaysOfMonth(year, month);
		cal.set(Calendar.DATE, days);//

		return dateToString(cal.getTime(), "yyyyMMdd");
	}

	/**
	 * 获取 年月日对应当月第一天
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getFristDayOfMonth(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);// 年
		cal.set(Calendar.MONTH, month - 1);// 月，因为Calendar里的月是从0开始，所以要减1

		cal.set(Calendar.DATE, 1);//

		return dateToString(cal.getTime(), "yyyyMMdd");
	}

	/* 取得上月最后一天 */
	public static String getLastMonth() {
		String date = getSystemTime("yyyy-MM-dd");
		int year = Integer.parseInt(date.substring(0, 4));
		int mon = Integer.parseInt(date.substring(5, 7));
		if (mon == 1) {
			year = year - 1;
			mon = 12;
		} else {
			mon = mon - 1;
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);// 年
		cal.set(Calendar.MONTH, mon - 1);// 月，因为Calendar里的月是从0开始，所以要减1
		int days = getDaysOfMonth(year, mon);
		cal.set(Calendar.DATE, days);//
		return dateToString(cal.getTime(), "yyyy-MM-dd");

	}

	/* 取得上月第一天 */
	public static String getLastMonthFirstDay() {
		String date = getSystemTime("yyyy-MM-dd");
		int year = Integer.parseInt(date.substring(0, 4));
		int mon = Integer.parseInt(date.substring(5, 7));
		if (mon == 1) {
			year = year - 1;
			mon = 12;
		} else {
			mon = mon - 1;
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);// 年
		cal.set(Calendar.MONTH, mon - 1);// 月，因为Calendar里的月是从0开始，所以要减1
		cal.set(Calendar.DATE, 1);//
		return dateToString(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 获取指定月份天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);// 日，设为一号
		cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号
		cal.add(Calendar.DATE, -1);// 下一个月减一为本月最后一天
		return cal.get(Calendar.DATE);
	}

	/* 取得当月第一天 */
	public static String getFristDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));// 年
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));// 月，因为Calendar里的月是从0开始，所以要减1
		cal.set(Calendar.DATE, 1);// 日，设为一号
		String df = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return df;// 获得月初是几号
	}

	/**
	 * 取得系统时间
	 * 
	 * @return
	 */
	public static String getShortSystemTime() {
		String strTime = "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		strTime = df.format(new Date());
		return strTime;
	}

	/**
	 * 取得系统短日期，yymmdd
	 * 
	 * @return
	 */
	public static String getShortSystemDate() {
		String strTime = "";
		DateFormat df = new SimpleDateFormat("yyMMdd");
		strTime = df.format(new Date());
		return strTime;
	}

	/**
	 * 取得月份加减
	 * 
	 * @param month
	 * @param monthNum
	 * @return
	 */
	public static String getOperaMonth2(String month, int monthNum) {
		Date dt = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			dt = df.parse(month + "01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.add(2, monthNum);
		return String.valueOf(df.format(gc.getTime()).substring(0, 6));
	}

	/**
	 * 获取昨天日期
	 * 
	 * @return
	 */
	public static String getYesterday() {

		return getOpeDate(getSystemTime("yyyy-MM-dd"), -1);
	}

	/**
	 * 获取某个时间段的所有天数集合(包含起始日期与终止日期)
	 * 
	 * @param starDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getDayList(String starDate, String endDate) {
		String dateFormat = "yyyyMMdd";
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		List<String> dayList = new ArrayList<String>();
		if (starDate.equals(endDate)) {
			dayList.add(starDate);
		} else if (starDate.compareTo(endDate) < 0) {
			while (starDate.compareTo(endDate) <= 0) {
				dayList.add(starDate);
				try {
					long l = stringToDate(starDate, "yyyyMMdd").getTime();
					starDate = format.format(l + 3600 * 24 * 1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			dayList.add(endDate);
		}
		return dayList;
	}

	/**
	 * 获得时间范围内的月份
	 * @param starDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getMonthList(String starDate, String endDate) {
		List<String> monthList = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Calendar starCalendar = Calendar.getInstance();
		starCalendar.set(Calendar.YEAR, Integer.parseInt(starDate.substring(0, 4)));
		starCalendar.set(Calendar.MONTH, Integer.parseInt(starDate.substring(4, 6)) - 1);
		if (starDate.equals(endDate)) {
			monthList.add(starDate);
		} else if (starDate.compareTo(endDate) < 0) {
			while (starDate.compareTo(endDate) <= 0) {
				monthList.add(starDate);
				try {
					starCalendar.set(Calendar.MONTH, Integer.parseInt(starDate.substring(4, 6)));
					starDate = format.format(starCalendar.getTime());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			monthList.add(endDate);
		}
		return monthList;
	}

	/**
	 * 取得当前年月
	 * 
	 * @return
	 */
	public static String getSystemYearMonth() {
		String strDate = "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		strDate = df.format(new Date()).substring(0, 7);
		return strDate;
	}

	/**
	 * 获取一个日期的前一天
	 * 
	 * @param day
	 * @return
	 */
	public static String getYesterdayByDay(String day) {
		Date dt = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			dt = df.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.add(5, -1);
		return String.valueOf(df.format(gc.getTime()));
	}

	/**
	 * 获取年份季度
	 * 
	 * @param today
	 * @return
	 */
	public static String getYearQ(String today) {
		String year = today.substring(0, 4);
		String month = today.substring(4, 6);
		String q = "1";
		if (Integer.valueOf(month) > 1) {
			q = "1";
		}
		if (Integer.valueOf(month) > 3) {
			q = "2";
		}
		if (Integer.valueOf(month) > 6) {
			q = "3";
		}
		if (Integer.valueOf(month) > 9) {
			q = "4";
		}
		return year + "Q" + q;
	}

	public static String formatToYyyy_mm_dd(String date_yyyymmdd) {
		String result = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			result = df1.format(df.parse(date_yyyymmdd));
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println(
					"com.srie.util.date.DateUtil.fromatToYyyy_mm_dd(String date_yyyymmdd) 格式化日期出错，date_yyyymmdd="
							+ date_yyyymmdd);
		} finally {
			return result;
		}
	}

	public static String formatToYyyymmdd(String date_yyyy_mm_dd) {
		String result = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			result = df.format(df1.parse(date_yyyy_mm_dd));
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println(
					"com.srie.util.date.DateUtil.fromatToYyyy_mm_dd(String date_yyyymmdd) 格式化日期出错，date_yyyymmdd="
							+ date_yyyy_mm_dd);
		} finally {
			return result;
		}
	}
    /**
     * 系统日期相减
     * 
     * @param date
     * @param dayNum
     * @return
     */
    public static String getOperaDate2(String date, int dayNum) {
        Date dt = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            dt = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dt);
        gc.add(5, dayNum);
        return String.valueOf(df.format(gc.getTime()));
    }
    public static String safeDate(){

		DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy年MM月dd号");

		LocalDateTime now = LocalDateTime.now();
		String format = dateTimeFormatter.format(now);

		return format;
	}

	public static void main(String[] args) {
    	//如果是JDK8的应用，可以使用Instant代替Date，LocalDateTime代替Calendar，
		// DateTimeFormatter代替Simpledateformatter，官方给出的解释：simple beautiful strong immutable thread-safe。

		System.out.println(Instant.now());
		System.out.println(safeDate());

	}
	
	

}
