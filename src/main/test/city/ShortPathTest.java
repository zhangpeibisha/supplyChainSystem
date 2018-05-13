package city;

import org.nix.model.city.City;
import org.nix.utils.city.CityDijKstra;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/4/22.
 * <p>
 * 最短路径算法测试
 */
public class ShortPathTest {

    public static void main(String[] args) {

        double MAX = Integer.MAX_VALUE;

        City chongqing = new City();
        chongqing.setCityName("重庆0");

        City guangzhou = new City();
        guangzhou.setCityName("广州1");

        City shenzheng = new City();
        shenzheng.setCityName("深圳2");

        City huizhou = new City();
        huizhou.setCityName("惠州3");

        City shanghai = new City();
        shanghai.setCityName("上海4");


        chongqing.addDistance(guangzhou,10.0)
                .addDistance(shenzheng)
                .addDistance(huizhou,30.0)
                .addDistance(shanghai,100.0);

        guangzhou.addDistance(chongqing)
                .addDistance(shenzheng,50.0)
                .addDistance(huizhou)
                .addDistance(shanghai);

        shenzheng.addDistance(guangzhou)
                .addDistance(chongqing)
                .addDistance(huizhou)
                .addDistance(shanghai,10.0);

        huizhou.addDistance(guangzhou)
                .addDistance(shenzheng,20.0)
                .addDistance(chongqing)
                .addDistance(shanghai,60.0);

        shanghai.addDistance(guangzhou)
                .addDistance(shenzheng)
                .addDistance(huizhou)
                .addDistance(chongqing);

        List<City> cities = new ArrayList<City>();
        cities.add(chongqing);
        cities.add(guangzhou);
        cities.add(shenzheng);
        cities.add(huizhou);
        cities.add(shanghai);

        CityDijKstra cityDijKstra = new CityDijKstra(chongqing,shenzheng,cities);
        System.out.println(cityDijKstra.getShortPath());
    }

}
