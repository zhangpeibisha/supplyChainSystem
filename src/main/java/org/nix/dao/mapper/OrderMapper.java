package org.nix.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.nix.dao.base.BaseMapper;
import org.nix.model.MaterialMerchantsModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
public interface OrderMapper extends BaseMapper<MaterialMerchantsModel> {

    //获取数据条数
    long counts(@Param("nickName") String nickName,
                @Param("goodsName") String goodsName,
                @Param("unitPrice") double unitPrice);

    //  根据用户昵称获取用的id
    Integer findUserId(@Param("nickName") String nickName);
}
