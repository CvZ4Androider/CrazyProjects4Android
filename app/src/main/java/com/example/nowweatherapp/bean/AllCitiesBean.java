package com.example.nowweatherapp.bean;
import java.util.List;

public class AllCitiesBean {
    private List<Provinces> provinces;
    public void setProvinces(List<Provinces> provinces) {
        this.provinces = provinces;
    }
    public List<Provinces> getProvinces() {
        return provinces;
    }
}

