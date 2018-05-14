package city;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nix.dao.mapper.CityMapper;
import org.nix.dao.mapper.UserMapper;
import org.nix.model.UserModel;
import org.nix.model.city.City;
import org.nix.model.city.ShortPath;
import org.nix.service.imp.CityService;
import org.nix.utils.city.CityDijKstra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/5/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class InsertCityTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertUserTest() {
        UserModel userModel = new UserModel();
        userModel.setUserName("张三");
        userModel.setPhone("18203085236");
        userModel.setPassWord("e10adc3949ba59abbe56e057f20f883e"); // 明文：123456
        userModel.setNickName("长城有限公司");
        userMapper.insert(userModel);
    }

    @Autowired
    private CityMapper cityMapper;

    @Test
    public void insertCityListTest() {
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

        List<City> cities = new ArrayList<>();
        cities.add(chongqing);
        cities.add(guangzhou);
        cities.add(huizhou);
        cities.add(shanghai);
        cities.add(shenzheng);

        cityMapper.insertCityList(cities);
    }

    @Test
    public void insertCityDistanceListTest() {
        City chongqing = cityMapper.findCityById(1);

        City guangzhou = cityMapper.findCityById(2);

        City shenzheng = cityMapper.findCityById(3);

        City huizhou = cityMapper.findCityById(4);

        City shanghai = cityMapper.findCityById(5);

        chongqing.addDistance(guangzhou, 10.0)
                .addDistance(shenzheng)
                .addDistance(huizhou, 30.0)
                .addDistance(shanghai, 100.0);

        guangzhou.addDistance(chongqing)
                .addDistance(shenzheng, 50.0)
                .addDistance(huizhou)
                .addDistance(shanghai);

        shenzheng.addDistance(guangzhou)
                .addDistance(chongqing)
                .addDistance(huizhou)
                .addDistance(shanghai, 10.0);

        huizhou.addDistance(guangzhou)
                .addDistance(shenzheng, 20.0)
                .addDistance(chongqing)
                .addDistance(shanghai, 60.0);

        shanghai.addDistance(guangzhou)
                .addDistance(shenzheng)
                .addDistance(huizhou)
                .addDistance(chongqing);

        cityMapper.insertCityDistanceList(chongqing.getDistance());
        cityMapper.insertCityDistanceList(guangzhou.getDistance());
        cityMapper.insertCityDistanceList(shenzheng.getDistance());
        cityMapper.insertCityDistanceList(huizhou.getDistance());
        cityMapper.insertCityDistanceList(shanghai.getDistance());
    }

    @Autowired
    private CityService cityService;

    @Test
    public void shortPathTest() {
        City city = cityMapper.findCityById(1);
        City endCity = cityMapper.findCityById(5);
        List<City> cities =cityService .findCityAll();
        CityDijKstra cityDijKstra = new CityDijKstra(city, endCity, cities);
        ShortPath shortPath = cityDijKstra.getShortPath();
        System.out.println();
    }
}
