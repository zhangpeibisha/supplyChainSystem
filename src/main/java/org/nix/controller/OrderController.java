package org.nix.controller;

import org.nix.model.OrderModel;
import org.nix.model.UserModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/4/29.
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    /**
     * 用于测试订单列表
     * @return 订单列表
     */
    @PostMapping(value = "/orderListTest")
    public Map<String,Object> orderList(){

        Map<String,Object> map = new HashMap<>();
        List<OrderModel> orderModels = new ArrayList<>();

        for (int i = 0; i <10 ; i++) {
            OrderModel orderModel = new OrderModel();
            orderModel.setCost(getRandom(100,1000));
            orderModel.setGoodsName("订单啦啦啦");
            orderModel.setNeedAmount(getRandom(1000,20000));
            orderModel.setPercentOfPass(Math.random());
            orderModel.setTimeLimit(getRandom(1,10));
            orderModel.setUnitPrice(getRandom(5,20));

            UserModel userModel = new UserModel();
            userModel.setNickName("老板爷");
            userModel.setPassWord("啦啦啦");
            userModel.setPhone("123456789");
            userModel.setUserName("张沛大帅哥");
            orderModel.setUserModel(userModel);

            orderModels.add(orderModel);
        }

        map.put("orderList",orderModels);
        map.put("code",0);
        map.put("current",getRandom(1,20));
        map.put("total",getRandom(1,50));
        return map;
    }

    private int getRandom(int min , int max){
        int value = (int) ((Math.random()+min)*max);
        return value>max?max:value;
    }

}
