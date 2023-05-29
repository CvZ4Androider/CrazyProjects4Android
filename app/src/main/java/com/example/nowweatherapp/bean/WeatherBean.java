package com.example.nowweatherapp.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//cityid":"101160101",
// "city":"兰州",
// "update_time":"2023-05-23 22:33:47",
// "data":[{"date":"2023-05-23","wea":"多云转晴","wea_img":"yun","tem_day":"25","tem_night":"13","win":"西北风","win_speed":"3-4级转<3级"
public class WeatherBean {

    @SerializedName("city")
    private String city;

    @SerializedName("update_time")
    private String updateTime;

    @SerializedName("data")
    private List<DayWeatherBean> dayWeathers;
}
