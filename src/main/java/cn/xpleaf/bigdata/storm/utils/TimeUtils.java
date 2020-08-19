package cn.xpleaf.bigdata.storm.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Author:zedd Li
 * @date:2020/8/4 获取时间工具类
 */
public class TimeUtils {

    //返回当前年月日
    public static String getCurrentTime() {
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curTime = sdf.format(time);
        return curTime;
    }

    //获取前一天的时间
    public static String getYesterdayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        String yesterDay = sdf.format(calendar.getTime());
        return yesterDay;
    }

   /* public static void main(String[] args) {
        String time = TimeUtils.getCurrentTime();
        System.out.println(time);
    }*/
}
