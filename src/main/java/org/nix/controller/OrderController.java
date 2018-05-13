package org.nix.controller;

import org.nix.model.MaterialMerchantsModel;
import org.nix.model.city.City;
import org.nix.model.dto.LimitShowModel;
import org.nix.model.OrderModel;
import org.nix.service.imp.CityService;
import org.nix.service.imp.MaterialMerchantsService;
import org.nix.service.imp.OrderService;
import org.nix.utils.city.CityDijKstra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Resource(name = "orderService")
    private OrderService orderService;

    @Resource(name = "materialMerchantsService")
    private MaterialMerchantsService materialMerchantsService;
    @Autowired
    private CityService cityService;

    /**
     * 根据条件获取订单信息
     *
     * @param orderModel
     *  @param limitShowModel
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Map<String,Object> getLimitList(@ModelAttribute OrderModel orderModel,
                                           @ModelAttribute LimitShowModel limitShowModel) {

        Map<String,Object> resultMap = new HashMap<>();

        // Sql条件查询语句
        String conditionSql =  "t_o.userId = t_u.id AND\nt_o.addressId = t_c.id ";
        if (orderModel.getNickName() != null){
            conditionSql += "AND  nickName like CONCAT ('%', \""+orderModel.getNickName()+"\", '%')\n";
        }
        if (orderModel.getGoodsName() != null){
            conditionSql += "AND  goodsName like CONCAT('%', \""+orderModel.getGoodsName()+"\", '%')\n";
        }
        if(orderModel.getUnitPrice() != 0) {
            conditionSql += "AND  unitPrice = "+orderModel.getUnitPrice()+"\n";
        }
        try {
            Integer page = limitShowModel.getCurPage();
            Integer size = limitShowModel.getLimit();
            List<OrderModel> list = orderService.list(page,
                    size,
                    "t_o.id",
                    "",
                    conditionSql
            );
            long rows = orderService.getCounts(orderModel);
            resultMap.put("list", list);
            resultMap.put("total", rows);

        }catch (Exception e) {
            resultMap.put("list", -1);
            resultMap.put("total", -1);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 添加一条订单
     *
     * @param orderModel
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String,Object> add(@ModelAttribute OrderModel orderModel) {

        Map<String,Object> resultMap = new HashMap<>();
        try {
            /**获取并验证用户是否存在**/
            if (orderModel.getUserId() == null || orderModel.getUserId() == 0){
                Integer userId = orderService.findUserId(orderModel.getNickName());
                if (userId == null || userId == 0){
                    resultMap.put("status", 2);// 用户不存在
                    return resultMap;
                }
                orderModel.setUserId(userId);
            }

            orderService.add(orderModel);
            resultMap.put("status", 1);// 成功添加
        }catch (Exception e) {
            resultMap.put("status", 0); // 添加失败
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 更新一条订单
     *
     * @param orderModel
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Map<String,Object> update(@ModelAttribute OrderModel orderModel) {

        Map<String,Object> resultMap = new HashMap<>();
        try {
            orderService.update(orderModel);
            resultMap.put("status", 1);
        }catch (Exception e) {
            resultMap.put("status", 0);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 删除一条订单
     *
     * @param id
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.DELETE)
    public Map<String,Object> deleteById(@RequestParam("id") Integer id) {

        Map<String,Object> resultMap = new HashMap<>();
        try {
            orderService.delete(id);
            resultMap.put("status", 1);
        }catch (Exception e) {
            resultMap.put("status", 0);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 删除多条订单
     *
     * @param ids
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.DELETE)
    public Map<String,Object> deleteByIds(@RequestParam("ids") Integer[] ids) {

        Map<String,Object> resultMap = new HashMap<>();
        try {
            orderService.delete(ids);
            resultMap.put("status", 1);
        }catch (Exception e) {
            resultMap.put("status", 0);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 根据id查找订单的详情
     *
     * @param id
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/findDetail", method = RequestMethod.GET)
    public Map<String,Object> findDetail(@RequestParam("id") Integer id) {

        Map<String,Object> resultMap = new HashMap<>();
        try {
            OrderModel object = orderService.findById(id);
            resultMap.put("object", object);
        }catch (Exception e) {
            resultMap.put("object", 0);
            e.printStackTrace();
        }
        return resultMap;
    }


    /**
     * 选配原料厂商
     * */
    @PostMapping("/best")
    public Map<String,Object> theBestVendor(@RequestParam("id") Integer id) {
        OrderModel order = orderService.findById(id);
        Map<String,Object> resultMap = new HashMap<>();
        String goodName = order.getGoodsName();
        Long number = order.getNeedAmount();
        List<MaterialMerchantsModel> list = materialMerchantsService.list(null,null,null,null,
                "goodName = '" + goodName + "' and inventory > " + number);
        if (list == null || list.size() == 0) {
            resultMap.put("object", 0);
            return resultMap;
        }
        MaterialMerchantsModel best = null;
        List<City> cities = cityService.findCityAll();
        Double min = null;
        for (MaterialMerchantsModel model:list) {
            CityDijKstra cityDijKstra = new CityDijKstra(cityService.findCityByName(order.getCityName()),model.getAddress(),cities);
            Double distance = cityDijKstra.getShortPath().getDistance();
            if (min == null || min > distance) {
                best = model;
                min = distance;
            }
        }
        resultMap.put("best",best);
        resultMap.put("way",min);
        resultMap.put("status", 1);
        return null;
    }
}
