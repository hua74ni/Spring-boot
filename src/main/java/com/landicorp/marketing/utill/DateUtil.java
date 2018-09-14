package com.landicorp.marketing.utill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具类
 * Created by jiangjt on 2017/9/25.
 */
public class DateUtil {
    private  static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private  static final SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
    private  static final SimpleDateFormat sdf3=new SimpleDateFormat("HH:mm:ss");
    /**
     * 格式化时间
     * @param date
     * @return
     */
    public static String formatDateTime(Date date){
        String time=sdf.format(date);
        return time;
    }

    public static String formatDay(Date date){
        String day=sdf2.format(date);
        return day;
    }

    public static String formatTime(Date date){
        String day=sdf3.format(date);
        return day;
    }

    /**
     * 获取当天时间段
     * @return
     */
    public static Map<String,String> getTodayTime(){
        Map<String,String> todayTimeMap=new HashMap<String,String>();
        String day = formatDay(new Date());
        todayTimeMap.put("startTime",day+" 00:00:00");
        todayTimeMap.put("endTime",day+" 23:59:59");
        return todayTimeMap;
    }

    /**
     * 获取当周时间段
     * @return
     */
    public static Map<String,String> getWeekTime(){
        Map<String,String> todayTimeMap=new HashMap<String,String>();
        Calendar c = Calendar.getInstance();
        Date today=new Date();
        c.setTime(today);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            dayOfWeek += 7;
        }
        c.add(Calendar.DATE, 2 - dayOfWeek);
        Date weekBef = c.getTime();
        todayTimeMap.put("startTime",formatDay(weekBef)+" 00:00:00");
        todayTimeMap.put("endTime",formatDay(today)+" 23:59:59");
        return todayTimeMap;
    }

    /**
     * 获取当月时间段
     * @return
     */
    public static Map<String,String> getMonthTime(){
        Map<String,String> monthTimeMap=new HashMap<String,String>();
        Calendar c = Calendar.getInstance();
        Date today=new Date();
        c.setTime(today);
        c.add(Calendar.MONTH, -1);
        Date monthBef = c.getTime();
        monthTimeMap.put("startTime",formatDay(monthBef)+" 00:00:00");
        monthTimeMap.put("endTime",formatDay(today)+" 23:59:59");
        return monthTimeMap;
    }

    /**
     * 获取十日时间段
     * @return
     */
    public static Map<String,String> getTenDays(){
        Map<String,String> tenDaysMap=new HashMap<String,String>();
        Calendar c = Calendar.getInstance();
        Date today=new Date();
        c.setTime(today);
        c.add(Calendar.DATE, -10);
        Date tenBef = c.getTime();
        tenDaysMap.put("startTime",formatDay(tenBef)+" 00:00:00");
        tenDaysMap.put("endTime",formatDay(today)+" 23:59:59");
        return tenDaysMap;
    }

    /**
     * 获取连续十日日期
     * @return
     */
    public static List<String> getTenDaysList(){
        List<String> dayList=new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        Date today=new Date();
        c.setTime(today);
        c.add(Calendar.DATE, -10);
        for (int i=0;i<9;i++){
            c.add(Calendar.DATE, +1);
            Date datBef=c.getTime();
            dayList.add(formatDay(datBef));
        }
        dayList.add(formatDay(today));
        return dayList;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        //                  "星期日" "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"
        //                  7位数 下划线为了sql语句通配
        String[] weekDays = {"______1", "1______", "_1_____", "__1____", "___1___", "____1__", "_____1_"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    public static String convertDate(String date){

        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        if(date.indexOf("Z") == -1){
            return date;
        }
        try {
            date = date.replace("Z", " UTC");//注意是空格+UTC
            Date d = df.parse(date);
            return formatDay(d);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

    }

    public static String convertTime(String date){

        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        if(date.indexOf("Z") == -1){
            return date;
        }
        try {
            date = date.replace("Z", " UTC");//注意是空格+UTC
            Date d = df.parse(date);
            return formatDateTime(d);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

    }

    public static void main(String[] args) {

        System.out.println((new Double(0.999999)).intValue());

    }

}
