package org.nix.dao.mapper;

import org.nix.model.city.City;

import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/4/28.
 */
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
}
