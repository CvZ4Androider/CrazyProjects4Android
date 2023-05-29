package com.example.nowweatherapp.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FutureWeatherBean {//用于解决json嵌套一层取不出的问题
    //{
//    "nums":2,
//    "cityid":"101120201",
//    "city":"青岛",
//    "update_time":"2023-05-24 10:13:55",
//    "data":[
//        {
//            "date":"2023-05-24",
//            "wea":"多云",
//            "wea_img":"yun",
//            "tem_day":"23",
//            "tem_night":"17",
//            "win":"南风",
//            "win_speed":"4-5级"
//        },
//        {
//            "date":"2023-05-25",
//            "wea":"小雨转多云",
//            "wea_img":"yun",
//            "tem_day":"21",
//            "tem_night":"18",
//            "win":"南风",
//            "win_speed":"4-5级转3-4级"
//        },.....
//    ]
//}

    @SerializedName("data")
    private List<DayWeatherBean> sevenDayWeather;

    public List<DayWeatherBean> getSevenDayWeather() {
        return sevenDayWeather;
    }

    public void setSevenDayWeather(List<DayWeatherBean> sevenDayWeather) {
        this.sevenDayWeather = sevenDayWeather;
    }

    @Override
    public String toString() {
        return "FutureWeatherBean{" +
                "sevenDayWeather=" + sevenDayWeather +
                '}';
    }
}
