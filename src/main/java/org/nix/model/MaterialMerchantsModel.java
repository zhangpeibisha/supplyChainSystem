package org.nix.model;

import org.nix.model.base.BaseModel;

public class MaterialMerchantsModel extends BaseModel<MaterialMerchantsModel> {

    private String materialMerchantsId;

    private String nickName;// 原料商名

    private String address;// 原料商地址

    private long inventory;// 原料库存

    private double percentOfPass;// 原料合格率

    private String goodsName;// 提供的原料名

    private double unitPrice;// 原料单价

    public String getMaterialMerchantsId(){
        return this.materialMerchantsId;
    }

    public String getNickName(){
        return this.nickName;
    }

    public String getAddress(){
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

    public void setMaterialMerchantsId(String materialMerchantsId){
        this.materialMerchantsId = materialMerchantsId;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public  void setAddress(String address){
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
