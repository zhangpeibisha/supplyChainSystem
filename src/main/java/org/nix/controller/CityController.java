package org.nix.controller;

import org.nix.annotation.Authority;
import org.nix.common.enums.RoleEnum;
import org.nix.service.imp.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/5/2.
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * 查找所有城市信息
     * @return 所有城市信息
     */
    @Authority(roles = {RoleEnum.ROLE_FACTORY,RoleEnum.ROLE_USER})
    @PostMapping(value = "/findCityAll")
    public Map<String,Object> findCityAll(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("data",cityService.findCityAll());
        return map;
    }

    /**
     * 通过id查找城市信息
     * @param id 城市id
     * @return 这个id的城市信息
     */
    @Authority(roles = {RoleEnum.ROLE_FACTORY,RoleEnum.ROLE_USER})
    @PostMapping(value = "/findCityById")
    public Map<String,Object> findCityById(@RequestParam("id")int id){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("data",cityService.findById(id));
        return map;
    }

}
