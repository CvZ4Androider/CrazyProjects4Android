package com.example.nowweatherapp.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;

public class DayWeatherBean implements Serializable {//包含data内的未来7日天气,用于主页面下方的滑动模块
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
    @SerializedName("date")
    private String date;

    @SerializedName("wea")
    private String weather;

    @SerializedName("wea_img")
    private String weatherImg;

    @SerializedName("tem_day")
    private String temMax;

    @SerializedName("tem_night")
    private String temMin;

    @SerializedName("win")
    private String wind;

    @SerializedName("win_speed")
    private String windSpeed;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatherImg() {
        return weatherImg;
    }

    public void setWeatherImg(String weatherImg) {
        this.weatherImg = weatherImg;
    }

    public String getTemMax() {
        return temMax;
    }

    public void setTemMax(String temMax) {
        this.temMax = temMax;
    }

    public String getTemMin() {
        return temMin;
    }

    public void setTemMin(String temMin) {
        this.temMin = temMin;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        return "DayWeatherBean{" +
                "date='" + date + '\'' +
                ", weather='" + weather + '\'' +
                ", weatherImg='" + weatherImg + '\'' +
                ", temMax='" + temMax + '\'' +
                ", temMin='" + temMin + '\'' +
                ", wind=" + wind + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                '}';
    }
}
