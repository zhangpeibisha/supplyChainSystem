package org.nix.utils.city;

import org.nix.model.city.City;
import org.nix.model.city.Distance;
import org.nix.model.city.ShortPath;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Create by zhangpe0312@qq.com on 2018/4/22.
 * <p>
 * 具体算法
 */
public class CityDijKstra {

    // 起点城市
    private final City startCity;

    private final City endCity;

    // 使用邻接矩阵表示地图
    private List<City> map;

    // 求出来的最短路径结果保存，若为空则没有路径可达
    private ShortPath shortPath;

    public CityDijKstra(City startCity, City endCity, List<City> map) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.map = map;
        init();
    }
    public ShortPath shortPath() {
        return shortPath;
    }


    private void dijkstra(int citySize, int startIndex, int endIndex) {

        // 保存起点城市到其他城市的最短长度
        double[] shortPath = new double[citySize];
        // 标记城市是否被求的最短路径
        boolean[] marked = new boolean[citySize];
        // 保存最短路径访问
        Queue<City>[] paths = new LinkedList[citySize];
        // 起点和其他点的距离
        List<Distance> startDistance = map.get(startIndex).getDistance();

        //初始化paths
        for (int i = 0; i < citySize; i++) {
            Queue<City> queue = new LinkedList<>();
            queue.offer(startCity);
            queue.offer(map.get(i));
            paths[i] = queue;
        }

        // 自己访问自己距离为0 且不必在求最短路径，因此标记为true
        shortPath[startIndex] = 0;
        marked[startIndex] = true;

        for (int i = 1; i < citySize; i++) {

            /**
             * 此部分计算起点到其他为标记点中最短路径的那个点
             */
            // 记录顶点能到达点的最短距离的下标
            int k = -1;
            // 距离为Integer.MAX_VALUE表示不可达
            double mind = Integer.MAX_VALUE;

            for (int j = 0; j < citySize; j++) {

                double dis = startDistance.get(j).getDistance();

                if (!marked[j] && dis < mind) {
                    mind = dis;
                    k = j;
                }
            }

            shortPath[k] = mind;
            marked[k] = true;

            /**
             * 此部分根据k点修正起点到其他所有节点的前驱节点及距离
             */

            for (int j = 0; j < citySize; j++) {

                //起点到k点的最短距离 + k点到j点的最短距离
                double dis = startDistance.get(k).getDistance() +
                        map.get(k).getDistance().get(j).getDistance();

                // 判断j点是否被标记，若没有被标记，且dis小于直达距离，则修正最短距离
                if (!marked[j] && dis < startDistance.get(j).getDistance()) {

                    map.get(startIndex)
                            .getDistance()
                            .get(j).setDistance(dis);

                    Queue<City> queue = new LinkedList<>();
                    for (City city : paths[k]) {
                        queue.offer(city);
                    }
                    queue.offer(map.get(j));
                    paths[j] = queue;
                }
            }
        }
        display(shortPath, paths);
        this.shortPath.setDistance(shortPath[endIndex]);
        this.shortPath.setWays(paths[endIndex]);
    }


    private void init() {
        // 初始化最短路径结果中的起始城市和目的城市
        shortPath = new ShortPath(startCity, endCity);
        int citySize = map.size();
        int startIndex = getCityIndex(map,startCity);
        int endIndex = getCityIndex(map,endCity);
        dijkstra(citySize, startIndex, endIndex);
        display(map);
    }

    private void display(double[] dis, Queue<City>[] paths) {

        for (int i = 0; i < dis.length; i++) {
            System.out.print(startCity.getCityName() + "到" + map.get(i).getCityName());
            System.out.print("的距离为：" + dis[i]);
            System.out.println();
            System.out.print(startCity.getCityName() + "到" + map.get(i).getCityName());
            System.out.print("的路径为：");
            for (City city : paths[i]) {
                System.out.print(city.getCityName() + " ");
            }
            System.out.println();
        }
    }

    private void display(List<City> cities){
        System.out.println("==========================");
        for (City city : cities) {
            for (int i = 0; i <city.getDistance().size() ; i++) {
                System.out.print(city.getCityName() + "到");
                if (city.getDistance().get(i).getDistance() < Integer.MAX_VALUE/2)
                System.out.print(city.getDistance().get(i).getToCity().getCityName() +
                        "距离为" +
                        city.getDistance().get(i).getDistance());
                else
                    System.out.print(city.getDistance().get(i).getToCity().getCityName() +
                            "距离为不可达");
                System.out.println();
            }

        }
    }

    public ShortPath getShortPath() {
        return shortPath;
    }

    public void setShortPath(ShortPath shortPath) {
        this.shortPath = shortPath;
    }

    private int getCityIndex(List<City> cities , City city){
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getCityName().equals(city.getCityName())){
                return i;
            }
        }
        return -1;
    }
}
