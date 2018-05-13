package org.nix.model.city;

import org.nix.model.base.BaseModel;

import java.util.Queue;

/**
 * Create by zhangpe0312@qq.com on 2018/4/22.
 *
 * 城市与城市之间的最短路径
 */
public class ShortPath extends BaseModel<City>{

    // 出发城市
    private City fromCity;

    // 目的城市
    private City toCity;

    // 途径地
    private Queue<City> ways;

    // 两个城市之间的需要走的距离
    private Double distance;

    public ShortPath(City fromCity, City toCity) {
        this.fromCity = fromCity;
        this.toCity = toCity;
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

    public Queue<City> getWays() {
        return ways;
    }

    public void setWays(Queue<City> ways) {
        this.ways = ways;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "ShortPath{" +
                "fromCity=" + fromCity +
                ", toCity=" + toCity +
                ", ways=" + ways +
                ", distance=" + distance +
                '}';
    }
}
