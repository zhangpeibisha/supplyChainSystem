package org.nix.controller;

import org.nix.model.dto.LimitShowModel;
import org.nix.model.MaterialMerchantsModel;
import org.nix.service.imp.MaterialMerchantsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/materialMerchants")
public class MaterialMerchantsController {

    @Resource(name = "materialMerchantsService")
    private MaterialMerchantsService materialMerchantsService;

    /**
     * 根据条件获取用户信息
     *
     * @param materialMerchantsModel
     *  @param limitShowModel
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Map<String,Object> getLimitList(@ModelAttribute MaterialMerchantsModel materialMerchantsModel,
                                               @ModelAttribute LimitShowModel limitShowModel) {

        Map<String,Object> resultMap = new HashMap<>();

        // Sql条件查询语句
        String conditionSql =  " id IS NOT NULL\n";
        if (materialMerchantsModel.getGoodsName() != null){
            conditionSql += "AND  goodsName like CONCAT('%', #{"+materialMerchantsModel.getGoodsName()+"}, '%')\n";
        }
        if (materialMerchantsModel.getInventory() != 0){
            conditionSql += "AND  inventory like CONCAT('%', #{"+materialMerchantsModel.getInventory()+"}, '%')\n";
        }
        if(materialMerchantsModel.getUnitPrice() != 0) {
            conditionSql += "AND  unitPrice like CONCAT('%', #{"+materialMerchantsModel.getUnitPrice()+"}, '%')\n";
        }
        try {
            Integer page = limitShowModel.getCurPage();
            Integer size = limitShowModel.getLimit();
            List<MaterialMerchantsModel> list = materialMerchantsService.list(page,
                    size,
                    "id",
                    "",
                    conditionSql
            );
            long rows = materialMerchantsService.getCounts(materialMerchantsModel);
            resultMap.put("list", list);
            resultMap.put("rows", rows);

        }catch (Exception e) {
            resultMap.put("list", -1);
            resultMap.put("rows", -1);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 添加一个原料商
     *
     * @param materialMerchantsModel
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Map<String,Object> add(@ModelAttribute MaterialMerchantsModel materialMerchantsModel) {

        Map<String,Object> resultMap = new HashMap<>();
        try {
            materialMerchantsService.add(materialMerchantsModel);
            resultMap.put("status", 1);
        }catch (Exception e) {
            resultMap.put("status", 0);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 添加一个原料商
     *
     * @param materialMerchantsModel
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Map<String,Object> update(@ModelAttribute MaterialMerchantsModel materialMerchantsModel) {

        Map<String,Object> resultMap = new HashMap<>();
        try {
            materialMerchantsService.update(materialMerchantsModel);
            resultMap.put("status", 1);
        }catch (Exception e) {
            resultMap.put("status", 0);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 删除一个原料商
     *
     * @param id
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public Map<String,Object> deleteById(@RequestParam("id") Integer id) {

        Map<String,Object> resultMap = new HashMap<>();
        try {
            materialMerchantsService.delete(id);
            resultMap.put("status", 1);
        }catch (Exception e) {
            resultMap.put("status", 0);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 删除多个原料商
     *
     * @param ids
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.GET)
    public Map<String,Object> deleteByIds(@RequestParam("ids") Integer[] ids) {

        Map<String,Object> resultMap = new HashMap<>();
        try {
            materialMerchantsService.delete(ids);
            resultMap.put("status", 1);
        }catch (Exception e) {
            resultMap.put("status", 0);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 根据id查找原料商的详情
     *
     * @param id
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/findDetail", method = RequestMethod.GET)
    public Map<String,Object> findDetail(@RequestParam("id") Integer id) {

        Map<String,Object> resultMap = new HashMap<>();
        try {
            MaterialMerchantsModel object = materialMerchantsService.findById(id);
            resultMap.put("object", object);
        }catch (Exception e) {
            resultMap.put("object", 0);
            e.printStackTrace();
        }
        return resultMap;
    }

}
