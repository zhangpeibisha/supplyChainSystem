package org.nix.model.city;

import org.nix.model.base.BaseModel;

/**
 * Create by zhangpe0312@qq.com on 2018/4/22.
 *
 * 此为原始路径距离
 */
public class Distance extends BaseModel<City> {

    // 起点城市
    private City fromCity;

    // 目的城市
    private City toCity;

    // 两个城市之间的距离  为空则为无穷大
    private double distance = Integer.MAX_VALUE-1;

    public Distance() {
    }

    public Distance(City fromCity, City toCity) {
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    public Distance(City fromCity, City toCity, double distance) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
