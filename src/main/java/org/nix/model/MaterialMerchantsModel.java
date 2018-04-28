package org.nix.model;

import org.nix.model.base.BaseModel;
import org.nix.model.city.City;

public class MaterialMerchantsModel extends BaseModel<MaterialMerchantsModel> {

    private String nickName;// 原料商名

    private City address;// 原料商地址

    private long inventory;// 原料库存

    private double percentOfPass;// 原料合格率

    private String goodsName;// 提供的原料名

    private double unitPrice;// 原料单价

    public String getNickName(){
        return this.nickName;
    }

    public City getAddress(){
        return this.address;
    }

    public long getInventory(){
        return this.inventory;
    }

    public double getPercentOfPass(){
        return this.percentOfPass;
    }

    public String getGoodsName(){
        return this.goodsName;
    }

    public double getUnitPrice(){
        return this.unitPrice;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public  void setAddress(City address){
        this.address = address;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setInventory(long inventory) {
        this.inventory = inventory;
    }

    public void setPercentOfPass(double percentOfPass) {
        this.percentOfPass = percentOfPass;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

}
