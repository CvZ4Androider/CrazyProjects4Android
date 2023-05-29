package com.example.nowweatherapp.bean;

import com.google.gson.annotations.SerializedName;

public class NowWeatherBean {
    //实况天气查询，用于主页面第一模块展示当前具体天气情况

    // "nums":0,
    //    "cityid":"101120201",
    //    "city":"青岛",
    //    "date":"2023-05-24",
    //    "week":"星期三",
    //    "update_time":"10:46",
    //    "wea":"多云",
    //    "wea_img":"yun",
    //    "tem":"23",
    //    "tem_day":"23",
    //    "tem_night":"17",
    //    "win":"西南风",
    //    "win_speed":"4级",
    //    "win_meter":"20km\/h",
    //    "air":"63",
    //    "pressure":"1004",
    //    "humidity":"38%"

    @SerializedName("city")
    private String city;

    @SerializedName("date")
    private String date;

    @SerializedName("week")
    private String week;

    @SerializedName("wea")
    private String weather;

    @SerializedName("wea_img")
    private String weatherImage;

    @SerializedName("tem")
    private String temperature;

    @SerializedName("tem_day")
    private String temMax;

    @SerializedName("tem_night")
    private String temMin;

    @SerializedName("win")
    private String wind;

    @SerializedName("win_speed")
    private String windSpeed;

    @SerializedName("air")
    private String air;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatherImage() {
        return weatherImage;
    }

    public void setWeatherImage(String weatherImage) {
        this.weatherImage = weatherImage;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
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

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    @Override
    public String toString() {
        return "NowWeatherBean{" +
                "city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", weather='" + weather + '\'' +
                ", weatherImage='" + weatherImage + '\'' +
                ", temperature='" + temperature + '\'' +
                ", temMax='" + temMax + '\'' +
                ", temMin='" + temMin + '\'' +
                ", wind='" + wind + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", air='" + air + '\'' +
                '}';
    }
}

