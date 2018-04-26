package org.nix.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.nix.dao.base.BaseMapper;
import org.nix.model.MaterialMerchantsModel;
import java.util.List;
import java.util.Map;


public interface MaterialMerchantsMapper extends BaseMapper<MaterialMerchantsModel> {

    // 条件搜索
    List<Map<String, Object>> getLimitList(@Param("goodsName") String goodsName,
                                           @Param("inventory") long inventory,
                                           @Param("unitPrice") double unitPrice,
                                           @Param("startIndex") double startIndex,
                                           @Param("limit") double limit);

    //获取数据条数
    long getCounts(@Param("goodsName") String goodsName,
                   @Param("inventory") long inventory,
                   @Param("unitPrice") double unitPrice);
}
