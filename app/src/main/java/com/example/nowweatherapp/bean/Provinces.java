package com.example.nowweatherapp.bean;

import java.util.List;

public class Provinces {

    private List<Citys> citys;
    private String provinceName;

    public List<Citys> getCitys() {
        return citys;
    }

    public void setCitys(List<Citys> citys) {
        this.citys = citys;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceName() {
        return provinceName;
    }

}
