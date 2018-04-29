package org.nix.model.city;

import org.nix.model.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/4/22.
 */
public class City extends BaseModel<City> {

    // 城市名字
    private String cityName;

    // 与该城市邻接的城市
    private List<Distance> distance;

    public City() {
        init();
    }

    /**
     * 初始化这个对象，因为当前城市到当前城市的距离默认为0
     */
    public void init() {
        distance = new ArrayList<>();
        distance.add(new Distance(this, this, 0.0));
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Distance> getDistance() {
        return distance;
    }

    public void setDistance(List<Distance> distance) {
        this.distance = distance;
    }

    public City addDistance(City toCity) {
        this.distance.add(new Distance(this, toCity));
        return this;
    }

    public City addDistance(City toCity, double distance) {
        this.distance.add(new Distance(this, toCity, distance));
        return this;
    }


}
