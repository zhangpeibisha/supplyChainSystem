package org.nix.dao.mapper;

import org.nix.model.city.City;
import org.nix.model.city.Distance;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/4/28.
 */
@Transactional(rollbackFor = Exception.class)
public interface CityMapper {

    /**
     * todo: 通过城市名字查询到城市信息
     * @param city_name 城市名字
     * @return 需要查询的城市信息，若没有查询到，返回null
     */
     City findCityByName(String city_name);

    /**
     * todo: 查询所有城市信息
     * @return 需要查询的城市信息，若没有查询到，返回null
     */
     List<City> findCityAll();

    /**
     * todo: 通过id查询城市
     * @param id 城市id
     * @return 查询失败返回null
     */
     City findCityById(Integer id);

    /**
     * 批量导入城市距离
     * @param distances 距离集合
     */
     void insertCityDistanceList(List<Distance> distances);

    /**
     * 批量导入城市
     * @param cities
     */
     void insertCityList(List<City> cities);

    /**
     * 得到邻接城市
     * @param id 查询城市id
     * @return
     */
     List<Distance> getDistancesById(int id);
}
