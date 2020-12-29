package com.lsy.wisdombuid.util;

import com.lsy.wisdombuid.tools.L;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by lsy on 2019/12/11
 * MODO :
 */
public class TimeUtils {
    public static String getTime(Date date) {//可根据需要自行截取数据显示
        L.log("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String timeslashData(String time) {
        if (time.length() < 10) {
            return "";
        }
        if (time.length() > 10) {
            time = "" + Long.parseLong(time) / 1000;
        }
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
//		int i = Integer.parseInt(time);
        String times = sdr.format(new Date(lcc * 1000L));
        return times;

    }

    public static String timeData(String time) {
        if (time.length() < 10) {
            return "";
        }
        if (time.length() > 10) {
            time = "" + Long.parseLong(time) / 1000;
        }
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy.MM.dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
//		int i = Integer.parseInt(time);
        String times = sdr.format(new Date(lcc * 1000L));
        return times;

    }

    public static String toDate(String time) {
        if (time.length() < 10) {
            return "";
        }
        if (time.length() > 10) {
            time = "" + Long.parseLong(time) / 1000;
        }
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
//		int i = Integer.parseInt(time);
        String times = sdr.format(new Date(lcc * 1000L));
        return times;

    }


    /**
     * 将毫秒转化为 分钟：秒 的格式
     *
     * @param millisecond 毫秒
     * @return
     */
    public static String formatTime(long millisecond) {
        int minute;//分钟
        int second;//秒数
        minute = (int) ((millisecond / 1000) / 60);
        second = (int) ((millisecond / 1000) % 60);
        if (minute < 10) {
            if (second < 10) {
                return "0" + minute + ":" + "0" + second;
            } else {
                return "0" + minute + ":" + second;
            }
        }else {
            if (second < 10) {
                return minute + ":" + "0" + second;
            } else {
                return minute + ":" + second;
            }
        }
    }

}
