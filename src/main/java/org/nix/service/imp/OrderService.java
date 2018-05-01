package org.nix.service.imp;

import org.nix.dao.mapper.MaterialMerchantsMapper;
import org.nix.dao.mapper.OrderMapper;
import org.nix.model.OrderModel;
import org.nix.model.dto.LimitShowModel;
import org.nix.model.MaterialMerchantsModel;
import org.nix.model.UserModel;
import org.nix.service.BaseService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class OrderService extends BaseService<OrderModel> {

    @Resource(name = "orderMapper")
    private OrderMapper orderMapper;

    /**
     *
     * 根据条件获取原料商的条数
     *
     */
    public long getCounts(OrderModel orderModel){
        return orderMapper.counts(orderModel.getNickName(),
                orderModel.getGoodsName(),
                orderModel.getUnitPrice());
    }

    /**
     * 根据用户名获取用户Id
     * @param nickName
     * @return
     */
    public Integer findUserId(String nickName){
        return orderMapper.findUserId(nickName);
    }

}
