package com.example.nowweatherapp.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeekUtil {
    //根据日期获得今天星期几的方法
    public static String getWeekday(String dateString) {
        Calendar calendar = Calendar.getInstance();
        try {
            // 将日期字符串解析为一个 Calendar 对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);
            calendar.setTime(date);
            // 获取该日期对应的星期几
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            String[] weekdays = {"星期日","星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
            return weekdays[dayOfWeek-1];
        } catch(ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
