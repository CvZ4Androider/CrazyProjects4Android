package com.example.nowweatherapp.bean;

public class Citys {
        private String cityName;
        public void setCityName(String cityName) {
            this.cityName = cityName;
        }
        public String getCityName() {
            return cityName;
        }

    @Override
    public String toString() {
        return "Citys{" +
                "cityName='" + cityName + '\'' +
                '}';
    }
}

