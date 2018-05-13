package org.nix.service.imp;

import org.nix.dao.mapper.CityMapper;
import org.nix.model.city.City;
import org.nix.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/5/2.
 */
@Service
public class CityService extends BaseService<City> {

    @Autowired
    private CityMapper cityMapper;

    /**
     * 返回所有城市信息
     *
     * @return
     */
    public List<City> findCityAll() {
        return cityMapper.findCityAll();
    }

    /**
     * 通过id查询城市信息
     * @param id 城市id
     * @return 城市信息
     * @exception NullPointerException 如果id为空，抛出异常
     */
    public City findCityById(Integer id){
        if (id == null)
            throw new NullPointerException("城市id为空");
        return cityMapper.findCityById(id);
    }


    public City findCityByName(String name){
        return cityMapper.findCityByName(name);
    }

}
