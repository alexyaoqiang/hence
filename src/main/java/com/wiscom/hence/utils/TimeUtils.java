package com.wiscom.hence.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {
    public static Date stringToDate1(String strTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = formatter.parse(strTime);
        return date;
    }
    public static Date stringToDate4(String strTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(strTime);
        return date;
    }

    public static String dateToString1(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(date);
    }

    public static Date stringToDate2(String strTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = formatter.parse(strTime);
        return date;
    }

    public static String dateToString2(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String strTime = formatter.format(date);
        return strTime;
    }

    public static Date stringToDate3(String strTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse(strTime);
        return date;
    }

    public static String dateToString3(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = formatter.format(date);
        return strTime;
    }
    /*
        获取当天（今日）零点零分零秒
     */
    public static Date getTodayZeroDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        return date;
    }

    public static Date getYesterdayZeroDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        return date;
    }

    public static Long timeZoneTransfer(Long dateLong) {
        Date date=new Date(dateLong);
        date=changeTimeZone(date,TimeZone.getTimeZone("GMT"),TimeZone.getTimeZone("Asia/Shanghai"));
        return date.getTime();
    }

    public static Date timeZoneTransfer2(String dateString) {
        Date date= null;
        try {
            date = stringToDate2(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date=changeTimeZone(date,TimeZone.getTimeZone("GMT"),TimeZone.getTimeZone("Asia/Shanghai"));
        return date;
    }


    /**
     * 　　* 获取更改时区后的日期
     * <p>
     * 　　* @param date 日期
     * <p>
     * 　　* @param oldZone 旧时区对象
     * <p>
     * 　　* @param newZone 新时区对象
     * <p>
     * 　　* @return 日期
     * <p>
     *
     */
    public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {

        Date dateTmp = null;
        if (date != null) {

            int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();

            dateTmp = new Date(date.getTime() - timeOffset);

        }

        return dateTmp;

    }


    public static void main(String[] args) {

        System.out.println(TimeUtils.dateToString1(getYesterdayZeroDate()));

    }


}
