package org.nix.model;

import org.nix.model.base.BaseModel;
public class OrderModel  extends  BaseModel<OrderModel> {

    private Integer userId;

    private  String nickName;

    private Integer timeLimit;// 到货天数

    private long needAmount;// 所需要的产品数量

    private double percentOfPass;// 合格率；

    private double unitPrice;// 货物单价

    private String goodsName;// 货物名字

    private double cost;// 货物总价格

    private Integer addressId;// 城市ID

    private String cityName;// 城市名字

    public Integer getAddressId(){
         return addressId;
    }

    public String getCityName(){
        return cityName;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getNickName(){
        return nickName;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    };

    public long getNeedAmount() {
        return needAmount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getPercentOfPass() {
        return percentOfPass;
    }

    public double getCost() {
        return cost;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setAddressId(Integer addressId){
        this.addressId = addressId;
    }

    public void setCityName(String cityName){
        this.cityName = cityName;
    }
    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setPercentOfPass(double percentOfPass) {
        this.percentOfPass = percentOfPass;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setNeedAmount(long needAmount) {
        this.needAmount = needAmount;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }
}
