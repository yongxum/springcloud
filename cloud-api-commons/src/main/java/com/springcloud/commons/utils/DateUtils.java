package com.springcloud.commons.utils;


//import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Slf4j
public class DateUtils {
    private static int FIRST_DATE_OF_WEEK = 1;
    private static Pattern p = Pattern.compile("\\d{4}+[-]\\d{1,2}+[-]\\d{1,2}+");
    public static final String FMT_DEFAULT_DATATIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FMT_FULL_SEQ = "yyyyMMddHHmmssSSS";
    public static final String FMT_YMD_HMS = "yyyyMMddHHmmss";
    public static final String FMT_HHMM = "HH:mm";
    public static final String FMT_YMD = "yyyyMMdd";
    public static final String FMT_YM_NO_SPLIT = "yyyyMM";
    public static final String FMT_YM = "yyyy-MM";
    public static final String FMT_DEFAULT_DATA = "yyyy-MM-dd";
    public static final String FMT_Y_M_D_HM_POINT = "yyyy-MM-dd HH:mm";
    private static String[] WEEK_DAY = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private static SimpleDateFormat DEFAULT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat DEFAULT_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DateUtils() {
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    public static String getFullFormatDate(Date date) {
        if (date == null) {
            return "";
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strDate = sdf.format(date);
                return strDate;
            } catch (Exception var3) {
//                log.error("日期格转换失败:" + var3);
                return "";
            }
        }
    }

    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(1);
    }

    public static Long getCurrentDateMill() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static String getCurrentDateStr(String pattern) {
        SimpleDateFormat sdf;
        if (pattern == null) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            sdf = new SimpleDateFormat(pattern);
        }

        return sdf.format(new Date());
    }

    public static Date addMinutes(int min) {
        Calendar cal = Calendar.getInstance();
        cal.add(12, min);
        return cal.getTime();
    }

    public static Date addMinutes(Date date, int min) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(12, min);
        return cal.getTime();
    }

    public static Date addMonth(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(2, month);
        return cal.getTime();
    }

    public static Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, day);
        return cal.getTime();
    }

    public static Date addHour(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(11, hour);
        return cal.getTime();
    }

    public static Date parseToDate(String str) {
        try {
            Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse(str);
            return date;
        } catch (Exception var2) {
//            log.info("日期格转换失败: " + var2);
            return null;
        }
    }

    public static Date parseToDate(String str, String pattern) {
        SimpleDateFormat sdf;
        if (pattern != null && pattern.length() != 0) {
            sdf = new SimpleDateFormat(pattern);
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        try {
            Date date = sdf.parse(str);
            return date;
        } catch (Exception var4) {
//            log.error("日期格转换失败: {}", var4.getMessage());
            return null;
        }
    }

    public static String getFormatDate(Date date) {
        if (date == null) {
            return "";
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strDate = sdf.format(date);
                return strDate;
            } catch (Exception var3) {
//                log.error("日期格转换失败: {}", var3.getMessage());
                return "";
            }
        }
    }

    public static String getFormatDate(Date date, String pattern) {
        if (pattern != null && pattern.length() != 0) {
            if (date == null) {
                return "";
            } else {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    String strDate = sdf.format(date);
                    return strDate;
                } catch (Exception var4) {
//                    log.error("日期格转换失败: {}", var4);
                    return "";
                }
            }
        } else {
            throw new IllegalArgumentException("pattern");
        }
    }

    public static boolean isMonthBegin() {
        Calendar cal = Calendar.getInstance();
        boolean isMonthBegin = false;
        if (cal.get(5) == 1) {
            isMonthBegin = true;
        }

        return isMonthBegin;
    }

    public static boolean isWeekBegin() {
        Calendar cal = Calendar.getInstance();
        boolean isWeekBegin = false;
        if (cal.get(7) == 2) {
            isWeekBegin = true;
        }

        return isWeekBegin;
    }

    public static boolean validateDate(String dateString) {
        Matcher m = p.matcher(dateString);
        if (!m.matches()) {
            return false;
        } else {
            String[] array = dateString.split("-");
            int year = Integer.valueOf(array[0]);
            int month = Integer.valueOf(array[1]);
            int day = Integer.valueOf(array[2]);
            if (month >= 1 && month <= 12) {
                int[] monthLengths = new int[]{0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                if (isLeapYear(year)) {
                    monthLengths[2] = 29;
                } else {
                    monthLengths[2] = 28;
                }

                int monthLength = monthLengths[month];
                return day >= 1 && day <= monthLength;
            } else {
                return false;
            }
        }
    }

    private static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static int compareDate(Date dt1, Date dt2) {
        String date1 = (new SimpleDateFormat("yyyy-MM-dd")).format(dt1);
        String date2 = (new SimpleDateFormat("yyyy-MM-dd")).format(dt2);
        int flag = date1.compareTo(date2);
        return flag;
    }

    public static boolean compareDateTime(Date dt1, Date dt2) {
        return dt1.getTime() > dt2.getTime();
    }

    public static String getChinaeseWeekDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(7) - 1 < 0 ? 0 : cal.get(7) - 1;
        return WEEK_DAY[w];
    }

    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static boolean isEndOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, 1);
        int weekDay = cal.get(7);
        return weekDay == FIRST_DATE_OF_WEEK;
    }

    public static boolean isMonthEnd(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        cal.add(5, 1);
        int day = cal.get(5);
        return day == 1;
    }

    public static boolean isQuarterEnd(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int month = cal.get(2);
        cal.add(5, 1);
        int day = cal.get(5);
        return day == 1 && (month == 2 || month == 5 || month == 8 || month == 11);
    }

    public static boolean isQuarterBegin(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int month = cal.get(2);
        int day = cal.get(5);
        return day == 1 && (month == 0 || month == 3 || month == 6 || month == 9);
    }

    public static boolean isHalfYearEnd(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int month = cal.get(2);
        cal.add(5, 1);
        int day = cal.get(5);
        return day == 1 && (month == 5 || month == 11);
    }

    public static boolean isHalfYearBegin(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int month = cal.get(2);
        int day = cal.get(5);
        return day == 1 && (month == 0 || month == 6);
    }

    public static int getDifferMinute(Date beginTime, Date endTime) {
        long seconds = endTime.getTime() - beginTime.getTime();
        return (int)seconds / 60 / 1000;
    }

    public static Date getTwoDateCenter(Date biginDate, Date endDate) {
        long millis = biginDate.getTime() + (endDate.getTime() - biginDate.getTime());
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        return c.getTime();
    }

    public static LocalDate Date2LocalDate(Date endTime) {
        Instant instant = endTime.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    public static String getStartOfDay(String date, String pattern) {
        return getStartOfDay(parseToDate(date, pattern));
    }

    public static String getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(1);
        int month = calendar.get(2);
        int day = calendar.get(5);
        calendar.set(year, month, day, 0, 0, 0);
        return format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getEndOfDay(String date, String pattern) {
        return getEndOfDay(parseToDate(date, pattern));
    }

    public static String getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(1);
        int month = calendar.get(2);
        int day = calendar.get(5);
        calendar.set(year, month, day, 23, 59, 59);
        return format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

//    public static void main(String[] args) throws ParseException {
//        log.info("{}", format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        log.info("{}", getCurrentDate());
//        log.info("{}", getCurrentDate());
//        log.info("{}", getCurrentYear());
//        log.info("{}", getCurrentDateStr("yyyy-MM-dd"));
//        log.info("{}", addMinutes(10));
//        log.info("{}", addMinutes(new Date(), 10));
//        log.info("{}", addMonth(new Date(), 10));
//        log.info("{}", addDay(new Date(), 10));
//        log.info("{}", addHour(new Date(), 10));
//        log.info("{}", parseToDate("2020-02-01"));
//        log.info("{}", parseToDate("2020-02-01", "yyyy-MM-dd"));
//        log.info("{}", getFormatDate(new Date()));
//        log.info("{}", getFormatDate(new Date(), "yyyy-MM-dd"));
//        log.info("{}", getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        log.info("{}", isMonthBegin());
//        log.info("{}", isWeekBegin());
//        log.info("{}", compareDate(new Date(), new Date()));
//        log.info("{}", getChinaeseWeekDay(new Date()));
//        log.info("{}", daysBetween(new Date(), new Date()));
//        log.info("{}", isEndOfWeek(new Date()));
//        log.info("{}", isMonthEnd(new Date()));
//        log.info("{}", isQuarterEnd(new Date()));
//        log.info("{}", isQuarterBegin(new Date()));
//        log.info("{}", isHalfYearEnd(new Date()));
//        log.info("{}", isHalfYearBegin(new Date()));
//        log.info("{}", getDifferMinute(new Date(), new Date()));
//        log.info("{}", Date2LocalDate(new Date()));
//        log.info("{}", localDateToDate(Date2LocalDate(new Date())));
//        log.info("{}", getStartOfDay(new Date()));
//        log.info("{}", getStartOfDay("2020-01-01 11:11:11", "yyyy-MM-dd HH:mm:ss"));
//        log.info("{}", getStartOfDay("2020-01-01", "yyyy-MM-dd"));
//        log.info("{}", getStartOfDay("2020-01-01 11:11:11", "yyyy-MM-dd"));
//        log.info("{}", getEndOfDay(new Date()));
//        log.info("{}", getEndOfDay("2020-01-01 11:11:11", "yyyy-MM-dd HH:mm:ss"));
//        log.info("{}", getEndOfDay("2020-01-01", "yyyy-MM-dd"));
//        log.info("{}", getEndOfDay("2020-01-01 11:11:11", "yyyy-MM-dd"));
//    }
}

