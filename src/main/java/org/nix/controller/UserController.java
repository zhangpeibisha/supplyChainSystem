package org.nix.controller;

import org.nix.annotation.Authority;
import org.nix.common.enums.RoleEnum;
import org.nix.common.enums.SessionHelper;
import org.nix.dao.mapper.CityMapper;
import org.nix.dao.mapper.RoleMapper;
import org.nix.model.RoleModel;
import org.nix.model.UserModel;
import org.nix.model.city.City;
import org.nix.service.imp.UserService;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kiss
 * @date 2018/04/22 15:15
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private RoleMapper roleMapper;

    @RequestMapping(value = "/registered")
    public Map<String,Object> registered(@ModelAttribute UserModel userModel,
                                         @RequestParam("addressId")int addressId) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            City city = cityMapper.findCityById(addressId);
            userModel.setAddress(city);
            userModel.setRoleModel(roleMapper.selectRoleByName(RoleEnum.ROLE_USER.getRoleName()));
            userService.add(userModel);
            resultMap.put("code",1);
            userModel.setPassWord("");
            resultMap.put("user",userModel);
        }catch (Exception e) {
            resultMap.put("code",0);
            resultMap.put("msg","注册失败");
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping(value = "/login")
    public Map<String,Object> login(@RequestParam("nickName") String nickName,
                                    @RequestParam("passWord") String passWord,
                                    HttpSession session) {
        Map<String,Object> resultMap = new HashMap<>();
        UserModel userModel = new UserModel();
        userModel.setNickName(nickName);
        userModel.setPassWord(passWord);
        UserModel model = userService.login(userModel);
        if (model == null){
            resultMap.put("code",-1);
            resultMap.put("msg","登陆失败，账号或者密码错误");
            return resultMap;
        }
        model.setPassWord(null);
        resultMap.put("code",1);
        resultMap.put("id",model.getId());
        resultMap.put("role",model.getRoleModel().getId());
        session.setAttribute(SessionHelper.SESSION_HELPER_USER.getSessionKey(),model);
        return resultMap;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Map<String,Object> add(@ModelAttribute UserModel userModel) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            userService.add(userModel);
            resultMap.put("code",1);
            userModel.setPassWord("");
            resultMap.put("user",userModel);
        } catch (Exception e) {
            resultMap.put("code",0);
            e.printStackTrace();
        }
        return resultMap;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Map<String,Object> userMsg(@PathVariable("id") Integer id) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            resultMap.put("user",userService.findById(id));
            resultMap.put("code",1);
        } catch (Exception e) {
            resultMap.put("code",0);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 用户和厂家登陆后，才能修改用户信息
     * @param userModel 用户对象
     * @return 操作结果
     */
    @Authority(roles = {RoleEnum.ROLE_USER,RoleEnum.ROLE_FACTORY})
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public Map<String,Object> update(@ModelAttribute UserModel userModel) {
        Map<String,Object> resultMap = new HashMap<>();
        if (userModel.getId() == null) {
            resultMap.put("code",0);
            return resultMap;
        }
        try {
            userService.update(userModel);
            resultMap.put("code",1);
            resultMap.put("user",userModel);
        } catch (Exception e) {
            resultMap.put("code",0);
            e.printStackTrace();
        }
        return resultMap;
    }
}
