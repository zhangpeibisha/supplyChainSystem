package org.nix.service.imp;

import org.nix.dao.mapper.MaterialMerchantsMapper;
import org.nix.model.dto.LimitShowModel;
import org.nix.model.MaterialMerchantsModel;
import org.nix.model.UserModel;
import org.nix.service.BaseService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MaterialMerchantsService extends BaseService<MaterialMerchantsModel> {

    @Resource(name = "materialMerchantsMapper")
    private MaterialMerchantsMapper materialMerchantsMapper;

    /**
     *
     * 根据条件获取原料商的条数
     *
     */
    public long getCounts(MaterialMerchantsModel materialMerchantsModel){
        return materialMerchantsMapper.getCounts(materialMerchantsModel.getGoodsName(),
                materialMerchantsModel.getInventory(),
                materialMerchantsModel.getUnitPrice());
    }

}
