package org.nix.model;

import org.nix.model.base.BaseModel;

/**
 * Create by zhangpe0312@qq.com on 2018/4/29.
 *
 * 用户订单实体
 */
public class OrderModel extends BaseModel<OrderModel> {

    /**
     * 下订单的用户
     */
    private UserModel userModel;
    /**
     * 到货天数
     */
    private int timeLimit;
    /**
     * 需要产品数量
     */
    private int needAmount;
    /**
     * 合格率
     */
    private double percentOfPass;
    /**
     * 货物单价
     */
    private double unitPrice;
    /**
     * 货物名
     */
    private String goodsName;
    /**
     * 订单总价
     */
    private double cost;



    /**
     * get / set
     */

    public UserModel getUserModel() {
        return userModel;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public int getNeedAmount() {
        return needAmount;
    }

    public double getPercentOfPass() {
        return percentOfPass;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public double getCost() {
        return cost;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setNeedAmount(int needAmount) {
        this.needAmount = needAmount;
    }

    public void setPercentOfPass(double percentOfPass) {
        this.percentOfPass = percentOfPass;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
