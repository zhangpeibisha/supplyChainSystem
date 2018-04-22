package org.nix.controller;

import org.nix.model.UserModel;
import org.nix.service.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/registered")
    public Map<String,Object> registered(@ModelAttribute UserModel userModel) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            userService.add(userModel);
            resultMap.put("code",1);
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
                                    @RequestParam("passWord") String passWord) {
        Map<String,Object> resultMap = new HashMap<>();
        UserModel userModel = new UserModel();
        userModel.setNickName(nickName);
        userModel.setPassWord(passWord);
        UserModel model = userService.login(userModel);
        resultMap.put("code",1);
        resultMap.put("user",model);
        return resultMap;
    }

}
